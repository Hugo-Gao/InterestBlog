package com.gaoyunfan.model;

import lombok.Data;

/**
 * @author yunfan.gyf
 **/
@Data
public class Comment {
    private int id;
    private String email;
    private String content;
    private String createTime;
    private int replyId;
}
