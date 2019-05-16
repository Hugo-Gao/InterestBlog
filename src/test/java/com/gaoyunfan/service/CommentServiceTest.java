package com.gaoyunfan.service;

import com.gaoyunfan.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void getCommentNum() {
        int num = commentService.getCommentNum(-1);
        System.out.println(num);
        Assert.assertNotEquals(num,0);
    }

    @Test
    public void addComment() {
    }

    @Test
    public void queryComment() {
    }

    @Test
    public void getNewestComments() {
        List<Comment> newestComments = commentService.getNewestComments(6);
        System.out.println(newestComments);
        Assert.assertNotEquals(newestComments.size(),0);
    }
}