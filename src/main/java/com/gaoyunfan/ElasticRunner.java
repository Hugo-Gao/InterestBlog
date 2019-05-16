package com.gaoyunfan;

import com.gaoyunfan.dao.BlogElasticDao;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.service.BlogService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author yunfan.gyf
 **/
@Component
public class ElasticRunner implements ApplicationRunner {
    @Autowired
    private BlogElasticDao blogElasticDao;

    @Autowired
    private BlogService blogService;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;    //es工具

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        testSave();
//        testQuery();
//        SaveAllToES();
    }

    private void SaveAllToES() {
        List<Blog> blogList = blogService.queryAllBlog();
        for (Blog blog : blogList) {
            Blog saveBlog = new Blog();
            saveBlog.setId(blog.getId());
            saveBlog.setTitle(blog.getTitle());
            saveBlog.setContent(blog.getContent());
            blogElasticDao.save(blog);
            System.out.println("已存入"+blog.getId()+"号博客");
        }
    }

    private void testQuery() {
        AggregatedPage<Blog> blogs = getBlogListBySrt("虚拟机", 0, 10);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    public void testSave() {
        Blog blog = new Blog();
        blog.setId(1000);
        blog.setTitle("这是一个测试");
        blog.setContent("ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。\n" + "我们建立一个网站或应用程序，并要添加搜索功能，但是想要完成搜索工作的创建是非常困难的。我们希望搜索解决方案要运行速度快，我们希望能有一个零配置和一个完全免费的搜索模式，我们希望能够简单地使用JSON通过HTTP来索引数据，我们希望我们的搜索服务器始终可用，我们希望能够从一台开始并扩展到数百台，我们要实时搜索，我们要简单的多租户，我们希望建立一个云的解决方案。因此我们利用Elasticsearch来解决所有这些问题及可能出现的更多其它问题。\n" + "\n" + "作者：dalaoyang\n" + "链接：https://juejin.im/post/5aec0b386fb9a07abb23784d\n" + "来源：掘金\n" + "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。");
        blogElasticDao.save(blog);
    }

    /**
     * 从es检索数据
     *
     * @param content  搜索关键字
     * @param pageNum  页
     * @param pageSize 条
     * @return
     */
    public AggregatedPage<Blog> getBlogListBySrt(String content, Integer pageNum, Integer pageSize) {
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
