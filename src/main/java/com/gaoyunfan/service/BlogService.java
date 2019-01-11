package com.gaoyunfan.service;

import com.gaoyunfan.dao.BlogDao;
import com.gaoyunfan.model.Blog;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yunfan.gyf
 **/
@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public void postBlog(Blog blog) {
        blog.setId(null);
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setTags(Lists.newArrayList(blog.getTagString().split(";")));
        blogDao.insertBlog(blog);
    }
}
