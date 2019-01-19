package com.gaoyunfan.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Data
public class Blog {
    private Integer id;
    private String title;
    private String digest;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private List<Comment> comments;
    private List<String> tags;
    private String tagString;
    private String picUrl;

}
