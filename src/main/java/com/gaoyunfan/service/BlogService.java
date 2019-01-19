package com.gaoyunfan.service;

import com.gaoyunfan.dao.BlogDao;
import com.gaoyunfan.dao.TagDao;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${pic.url}")
    private String picUrl;

    @Transactional(rollbackFor = RuntimeException.class)
    public void postBlog(Blog blog) {
        blog.setId(null);
        blog.setCreateTime(new Date());
        blog.setModifyTime(new Date());
        blog.setDigest(getDigest(blog.getContent()));
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

    /**
     * 获取文章摘要
     * @param content
     * @return
     */
    private String getDigest(String content) {
        return (content.length() > 50 ? content.substring(0, 50) : content).trim();
    }

    /**
     * 按照一定的条件和页面查询博客列表
     * @param pagination
     * @return
     */
    public List<Blog> selectPreBlogList( Pagination pagination) {
        List<Blog> blogList = blogDao.selectBlogList(pagination.getPageIndex(), pagination.getPageNum());
        for (int i = 1; i <= blogList.size(); i++) {
            blogList.get(i-1).setPicUrl(picUrl + i);
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
}
