package com.gaoyunfan.service;

import com.gaoyunfan.dao.CommentDao;
import com.gaoyunfan.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
        commentDao.addCommentIdAndBlogId(comment.getId(), comment.getBlogId());
    }

    public List<Comment> queryComment(int blogId) {

        return commentDao.selectComment(blogId);
    }

    public int getCommentNum(int blogId) {
        return commentDao.selectCommentNum(blogId);
    }
}
