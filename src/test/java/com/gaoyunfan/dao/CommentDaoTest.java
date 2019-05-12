package com.gaoyunfan.dao;

import com.gaoyunfan.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setBlogId(19);
        comment.setContent("测试");
        comment.setNickname("Dev");
        comment.setCreateTime(new Date());
        commentDao.addComment(comment);
        assertTrue(comment.getId() != 0);
        commentDao.addCommentIdAndBlogId(comment.getId(), comment.getBlogId());
    }

    @Test
    public void addCommentIdAndBlogId() {

    }

    @Test
    public void selectComment() {
        List<Comment> comments = commentDao.selectComment(19);
        assertTrue(comments.size()>0);
    }
}