package com.gaoyunfan.service;

import com.gaoyunfan.model.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        blog.setTitle("test");
        blog.setContent("test");
        blog.setModifyTime(new Date());
        blog.setCreateTime(new Date());
        blogService.postBlog(blog);
    }
}