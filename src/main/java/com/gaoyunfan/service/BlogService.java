package com.gaoyunfan.service;

import com.alibaba.fastjson.JSON;
import com.gaoyunfan.dao.BlogDao;
import com.gaoyunfan.dao.BlogElasticDao;
import com.gaoyunfan.dao.CommentDao;
import com.gaoyunfan.dao.TagDao;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.Tag;
import com.google.common.collect.Lists;
import com.youbenzi.mdtool.tool.MDTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author yunfan.gyf
 **/
@Service
@Slf4j
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CommentDao commentDao;


    @Value("${pic.url}")
    private String picUrl;

    @Autowired
    private BlogElasticDao blogElasticDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;    //es工具

    /**
     * ZSET的键名
     */
    private final static String VIEW_KEY = "INTEREST_VIEW";

    /**
     * ZSET中的对象前缀
     */
    private final static String BLOG_VIEW_PRE = "INTEREST_BLOG_VIEW_";

    private final static String BLOG_DETAIL_CACHE_KEY = "INTEREST_BLOG_DETAIL_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存博客
     * @param blog
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void postBlog(Blog blog) {
        blog.setId(null);
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setDigest(getDigest(blog.getContent()));
        blog.setTags(Lists.newArrayList(blog.getTagString().split(";")));
        try {
            //存入blog
            blogDao.insertBlog(blog);
            //存入tag
            List<Tag> tagList = tagDao.selectTags(-1);
            List<String> insertTags = Lists.newArrayList();
            for (String tag : blog.getTags()) {
                if (!containsTag(tagList, tag)) {
                    insertTags.add(tag);
                }
            }
            if (insertTags.size() > 0) {
                tagDao.insertTags(insertTags);
                tagDao.insertBlogTag(insertTags, blog.getId());
            }
            //存入ElasticSearch
            saveBlogToES(blog);
        } catch (Exception e) {
            throw new RuntimeException("博客储存出错");
        }

    }

    /**
     * 获取文章摘要
     *
     * @param content
     * @return
     */
    private String getDigest(String content) {
        return (content.length() > 50 ? content.substring(0, 50) : content).trim();
    }

    /**
     * 按照一定的条件和页面查询博客列表
     *
     * @param pagination
     * @return
     */
    public List<Blog> selectPreBlogList(Pagination pagination) {
        List<Blog> blogList = blogDao.selectBlogList(pagination.getPageIndex(), pagination.getPageNum());
        for (int i = 1; i <= blogList.size(); i++) {
            blogList.get(i - 1).setPicUrl(picUrl + i);
        }
        return blogList;
    }

    public int getBlogNum() {
        return blogDao.selectBlogNum();
    }

    public int getPageSum() {
        int blogNum = getBlogNum();
        return blogNum % 6 == 0 ? blogNum / 6 : blogNum / 6 + 1;
    }

    public Blog getBlogDetail(int blogId) {
        String blogStr = redisTemplate.opsForValue().get(BLOG_DETAIL_CACHE_KEY + blogId);
        if (!StringUtils.isBlank(blogStr)) {
            log.info("找到了"+blogId+"博客的缓存");
            return JSON.parseObject(blogStr, Blog.class);
        }else {
            log.info("没找到"+blogId+"博客的缓存");
            Blog blog = blogDao.selectOneBlog(blogId);
            if (blog == null) {
                return null;
            }
            blog.setMdContent(renderMD(blog.getContent()));
            String blogCache = JSON.toJSONString(blog);
            redisTemplate.opsForValue().set(BLOG_DETAIL_CACHE_KEY + blogId, blogCache);
            log.info("将" + blogId + "存入缓存");
            return blog;
        }
    }

    /**
     * 将博客内容渲染成markdown
     *
     * @param content
     */
    private String renderMD(String content) {
        return MDTool.markdown2Html(content);
    }

    public List<Tag> getTagList(int blogId) {
        String[] classes = {"btn-blue", "btn-azure", "btn-indigo", "btn-purple", "btn-pink", "btn-red", "btn-orange", "btn-yellow", "btn-lime", "btn-green", "btn-teal", "btn-cyan", "btn-gray", "btn-gray-dark"};
        List<Tag> tagList = tagDao.selectTags(blogId);
        Random random = new Random();
        for (Tag tag : tagList) {
            tag.setStyle(classes[random.nextInt(classes.length)]);
        }
        return tagList;
    }

    private boolean containsTag(List<Tag> tagList, String tag) {
        for (Tag dbTag : tagList) {
            if (dbTag.getTag().equalsIgnoreCase(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * -1即取出所有博客
     * @param tagId
     * @return
     */
    public List<Blog> getBlogByTag(int tagId) {
        return blogDao.selectBlogListByTag(tagId);
    }

    /**
     * blog的浏览量存储在redis中，键为（年_月_日_blogId）,方便后面做几个时间维度的数据统计
     *
     * @param blogId
     * @return
     */
    @Transactional
    public int increAndGetBlogView(int blogId) {
        String key = BLOG_VIEW_PRE + blogId;
        Double view = redisTemplate.opsForZSet().incrementScore(VIEW_KEY, key, 1);
        if (view == null) {
            throw new RuntimeException("Redis 浏览量服务出错");
        }
        return view.intValue();
    }

    /**
     * @param blogId 博客ID
     * @return 所有匹配的键名的值的和
     */
    public int getBlogView(int blogId) {
        String key = BLOG_VIEW_PRE + blogId;
        Double view = redisTemplate.opsForZSet().score(VIEW_KEY, key);
        if (view == null) {
            return 0;
        }
        return view.intValue();
    }

    public int getBlogSumView() {
        int sum = 0;
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(VIEW_KEY, 0, -1);
        if (typedTuples != null) {
            for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
                if (typedTuple.getScore() != null) {
                    sum += typedTuple.getScore().intValue();
                }
            }
        }
        return sum;
    }

    public void deleteBlogView(int blogId) {
        String key = BLOG_VIEW_PRE + blogId;
        redisTemplate.opsForZSet().remove(VIEW_KEY, key);
    }

    @Transactional
    public void deleteBlog(String blogId) {
        blogDao.deleteBlog(blogId);
        tagDao.deleteBlogTag(blogId);
        commentDao.deleteComment(blogId);
        commentDao.deleteCommentBlog(blogId);
        deleteBlogView(Integer.parseInt(blogId));
        redisTemplate.delete(BLOG_DETAIL_CACHE_KEY + blogId);
    }

    public int getTagNum() {
        return tagDao.selectTagNum();
    }

    /**
     * 从redis中根据点击数给博客排序
     * @return
     */
    public List<Blog> getBlogByViews() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(VIEW_KEY, 0, -1);
        if (typedTuples == null) {
            return Lists.newArrayList();
        }
        List<Blog> blogList = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            Blog blog = new Blog();
            String[] splits = Objects.requireNonNull(typedTuple.getValue()).split("_");
            blog.setId(Integer.parseInt(splits[splits.length-1]));
            blog.setViews(Objects.requireNonNull(typedTuple.getScore()).intValue());
            blog.setTitle(blogDao.selectOneBlog(blog.getId()).getTitle());
            blogList.add(blog);
            if(blogList.size()==6){
                break;
            }
        }
        return blogList;
    }


    /**
     * 返回根据评论数量排序的博客列表
     * @return
     */
    public List<Blog> getBlogByComment() {
        return blogDao.selectBlogListByComment();
    }

    private void saveBlogToES(Blog blog) {
        Blog saveBlog = new Blog();
        saveBlog.setId(blog.getId());
        saveBlog.setTitle(blog.getTitle());
        saveBlog.setContent(blog.getContent());
        blogElasticDao.save(saveBlog);
    }

    /**
     * 临时方法，不要使用
     * @return
     */
    public List<Blog> queryAllBlog() {
      return  blogDao.selectAllBlog();
    }

    public List<Blog> getBlogByLike(String content) {
        AggregatedPage<Blog> aggrBlogs = getBlogListByContentLikeFromES(content, 0, 3);
        if (aggrBlogs == null) {
            return Lists.newArrayList();
        }
        return aggrBlogs.getContent();
    }

    /**
     * 从es检索数据相似的博客，返回高亮内容
     * @param content  搜索关键字
     * @param pageNum  页
     * @param pageSize 条
     * @return
     */
    private AggregatedPage<Blog> getBlogListByContentLikeFromES(String content, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(matchQuery("content", content)).
                withHighlightFields(new HighlightBuilder.Field("content").preTags(preTag).postTags(postTag)).build();
        searchQuery.setPageable(pageable);

        // 高亮字段
        AggregatedPage<Blog> blogs = elasticsearchTemplate.queryForPage(searchQuery, Blog.class, new SearchResultMapper() {

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Blog> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    Blog blog = new Blog();
                    HighlightField blogContent = searchHit.getHighlightFields().get("content");
                    if (blogContent != null) {
                        blog.setContent(blogContent.fragments()[0].toString());
                    }
                    blog.setId(Integer.parseInt(searchHit.getId()));
                    blog.setTitle((String) searchHit.getSourceAsMap().get("title"));
                    chunk.add(blog);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) chunk);
                }
                return null;
            }
        });
        return blogs;
    }

}
