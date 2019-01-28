package com.gaoyunfan.dao;

import com.gaoyunfan.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Mapper
public interface TagDao {
    public List<Tag> selectTags(@Param("blogId") int blogId);

    String selectTagById(int id);

    void insertTags(List<String> insertTags);

    void insertBlogTag(@Param("list") List<String> insertTags, @Param("blogId") Integer blogId);
}
