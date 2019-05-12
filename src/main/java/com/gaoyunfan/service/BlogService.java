package com.gaoyunfan.service;

import cn.hutool.core.date.DateUtil;
import com.gaoyunfan.dao.BlogDao;
import com.gaoyunfan.dao.CommentDao;
import com.gaoyunfan.dao.TagDao;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.Tag;
import com.google.common.collect.Lists;
import com.youbenzi.mdtool.tool.MDTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author yunfan.gyf
 **/
@Service
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
    private StringRedisTemplate template;

    @Transactional(rollbackFor = RuntimeException.class)
    public void postBlog(Blog blog) {
        blog.setId(null);
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setDigest(getDigest(blog.getContent()));
        blog.setTags(Lists.newArrayList(blog.getTagString().split(";")));
        try {
            blogDao.insertBlog(blog);
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
        Blog blog = blogDao.selectOneBlog(blogId);
        if (blog == null) {
            return null;
        }
        blog.setMdContent(renderMD(blog.getContent()));
        return blog;
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
        String key = DateUtil.thisYear() + "_" + (DateUtil.thisMonth() + 1) + "_" + DateUtil.thisDayOfMonth() + "_" + blogId;
        template.opsForValue().increment(key, 1);
        return getBlogView(blogId);
    }

    /**
     * @param blogId 博客ID
     * @return 所有匹配的键名的值的和
     */
    public int getBlogView(int blogId) {
        String key = "*_*_*_" + blogId;
        RedisConnection connection = template.getConnectionFactory().getConnection();
        ScanOptions options = ScanOptions.scanOptions().match(key).count(Integer.MAX_VALUE).build();
        Cursor<byte[]> cursor = connection.scan(options);
        int views = 0;
        while (cursor.hasNext()) {
            byte[] next = cursor.next();
            views += Integer.parseInt(template.opsForValue().get(new String(next, StandardCharsets.UTF_8)));
        }
        connection.close();
        return views;
    }
    public int getBlogSumView() {
        String key = "*_*_*_" + "*";
        RedisConnection connection = template.getConnectionFactory().getConnection();
        ScanOptions options = ScanOptions.scanOptions().match(key).count(Integer.MAX_VALUE).build();
        Cursor<byte[]> cursor = connection.scan(options);
        int views = 0;
        while (cursor.hasNext()) {
            byte[] next = cursor.next();
            System.out.println(new String(next, StandardCharsets.UTF_8));
            views += Integer.parseInt(template.opsForValue().get(new String(next, StandardCharsets.UTF_8)));
        }
        connection.close();
        return views;
    }

    public void deleteBlogView(int blogId) {
        String key = "*_*_*_" + blogId;
        RedisConnection connection = template.getConnectionFactory().getConnection();
        ScanOptions options = ScanOptions.scanOptions().match(key).count(Integer.MAX_VALUE).build();
        Cursor<byte[]> cursor = connection.scan(options);
        while (cursor.hasNext()) {
            byte[] next = cursor.next();
            template.delete(new String(next, StandardCharsets.UTF_8));
        }
        connection.close();

    }

    @Transactional
    public void deleteBlog(String blogId) {
        blogDao.deleteBlog(blogId);
        tagDao.deleteBlogTag(blogId);
        commentDao.deleteComment(blogId);
        commentDao.deleteCommentBlog(blogId);
        deleteBlogView(Integer.parseInt(blogId));
    }

    public int getTagNum() {
        return tagDao.selectTagNum();
    }
}
