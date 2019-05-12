package com.gaoyunfan.dao;

import com.gaoyunfan.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Mapper
public interface BlogDao {

    public void insertBlog(Blog blog);

    List<Blog> selectBlogList(@Param("pageIndex") int pageIndex,@Param("pageNum") int pageNum);

    int selectBlogNum();

    Blog selectOneBlog(int blogId);

    List<Blog> selectBlogListByTag(int tagId);

    void deleteBlog(String blogId);
}
