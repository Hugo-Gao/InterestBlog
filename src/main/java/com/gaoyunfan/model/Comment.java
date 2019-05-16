package com.gaoyunfan.model;

import lombok.Data;

import java.util.Date;

/**
 * @author yunfan.gyf
 **/
@Data
public class Comment {
    private int id;
    private int blogId;
    private String nickname;
    private String content;
    private Date createTime;
    private String blogTitle;
}
