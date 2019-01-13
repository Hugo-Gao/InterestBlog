package com.gaoyunfan.service;

import com.gaoyunfan.dao.BlogDao;
import com.gaoyunfan.dao.TagDao;
import com.gaoyunfan.model.Blog;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private TagDao tagDao;

    @Transactional(rollbackFor = RuntimeException.class)
    public void postBlog(Blog blog) {
        blog.setId(null);
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setTags(Lists.newArrayList(blog.getTagString().split(";")));
        try {
            blogDao.insertBlog(blog);
            List<String> tagList = tagDao.selectTags();
            List<String> insertTags = Lists.newArrayList();
            for (String tag : blog.getTags()) {
                if (!tagList.contains(tag)) {
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
}
