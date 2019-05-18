package com.gaoyunfan.dao;

import com.gaoyunfan.model.Blog;
import org.junit.Assert;
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
public class BlogDaoTest {
    @Autowired
    private BlogDao blogDao;

    @Test
    public void testUpdate() {
        Blog blog = new Blog();
        blog.setId(13);
        blog.setContent("测试内容");
        blog.setTitle("Java虚拟机总结中篇-更新测试");
        blog.setTagString("Java;虚拟机");
        blog.setModifyTime(new Date());
        int i = blogDao.updateBlog(blog);
        Assert.assertNotEquals(i, 0);
    }
}