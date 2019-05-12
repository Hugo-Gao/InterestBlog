package com.gaoyunfan.service;

import com.gaoyunfan.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}