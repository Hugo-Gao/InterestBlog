package com.gaoyunfan.dao;

import com.gaoyunfan.model.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yunfan.gyf
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogElasticDaoTest {
    @Autowired
    private BlogElasticDao blogElasticDao;

    @Test
    public void test() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        Blog blog = new Blog();
        blog.setId(1001);
        blog.setTitle("testTitle");
        blog.setContent("testContent");
        blogElasticDao.save(blog);

    }
}