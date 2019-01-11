package com.gaoyunfan.dao;

import com.gaoyunfan.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author yunfan.gyf
 **/
@Mapper
public interface BlogDao {

    public void insertBlog(Blog blog);
}
