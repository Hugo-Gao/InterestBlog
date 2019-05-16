package com.gaoyunfan.service;

import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogServiceTest {

    @Autowired
    private BlogService blogService;


    @Test
    public void postBlog() {
        Blog blog = new Blog();
        blog.setTagString("java;python");
        blog.setTitle("testSave");
        blog.setContent("testSave");
        blog.setModifyTime(new Date());
        blog.setCreateTime(new Date());
        blogService.postBlog(blog);
    }

    @Test
    public void selectPreBlogList() {
        List<Blog> blogs = blogService.selectPreBlogList(new Pagination(1));
        System.out.println(blogs);
    }

    @Test
    public void getPageSum() {
        int pageSum = blogService.getPageSum();
        assert pageSum == 2;
    }

    @Test
    public void getBlogDetail() {
        Blog blogDetail = blogService.getBlogDetail(13);
        assertNotNull(blogDetail);
    }

    @Test
    public void getTagList() {
        List<Tag> tagList = blogService.getTagList(-1);
        assert tagList.size() > 0;
        System.out.println(tagList);
        tagList = blogService.getTagList(13);
        assert tagList.size() > 0;
        System.out.println(tagList);
    }

    @Test
    public void getBlogByTag() {
        List<Blog> blogByTag = blogService.getBlogByTag(9);
        System.out.println(blogByTag);
    }

    @Test
    public void increAndGetBlogView() {
        String selectKey = "*_*_*_" + 21;
//         blogService.getBlogView(selectKey);
    }

    @Test
    public void deleteBlog() {
        blogService.deleteBlogView(19);
        int blogView = blogService.getBlogView(19);
        Assert.assertEquals(blogView,0);
    }

    @Test
    public void getSumView() {
        int blogSumView = blogService.getBlogSumView();
        Assert.assertNotEquals(blogSumView, 0);
    }

    @Test
    public void getBlogNum() {
    }

    @Test
    public void getBlogView() {

    }

    @Test
    public void getBlogSumView() {
    }

    @Test
    public void deleteBlogView() {
    }

    @Test
    public void getTagNum() {
        int tagNum = blogService.getTagNum();
        System.out.println(tagNum);
        Assert.assertNotEquals(tagNum,0);
    }

    @Test
    public void zsetTest() {

    }

    @Test
    public void blogCommentsView() {
        List<Blog> blogByComment = blogService.getBlogByComment();
        System.out.println(blogByComment);
        Assert.assertNotEquals(blogByComment.size(), 0);
    }
}