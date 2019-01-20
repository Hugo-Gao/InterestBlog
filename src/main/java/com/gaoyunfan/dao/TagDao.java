package com.gaoyunfan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Mapper
public interface TagDao {
    public List<String> selectTags(@Param("blogId") int blogId);

    void insertTags(List<String> insertTags);

    void insertBlogTag(@Param("list") List<String> insertTags, @Param("blogId") Integer blogId);
}
