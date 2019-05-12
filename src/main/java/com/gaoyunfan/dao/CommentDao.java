package com.gaoyunfan.dao;

import com.gaoyunfan.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Mapper
public interface CommentDao {
    void addComment(Comment comment);

    void addCommentIdAndBlogId(@Param("id") int id,@Param("blogId") int blogId);

    List<Comment> selectComment(int blogId);

    void deleteComment(String blogId);

    void deleteCommentBlog(String blogId);

    /**
     *
     * @param blogId >0 查询某个博客评论数，<0查询所有博客评论和
     * @return
     */
    int selectCommentNum(@Param("blogId")int blogId);
}
