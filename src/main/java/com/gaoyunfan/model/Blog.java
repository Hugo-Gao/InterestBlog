package com.gaoyunfan.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Data
@Document(indexName = "interestblog",type = "blog")
public class Blog {
    private Integer id;
    private String title;
    private String digest;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private List<Comment> comments;
    private List<String> tags;
    private List<Tag> tagList;
    private String tagString;
    private String picUrl;
    private String mdContent;
    private int views;
    private int commentsView;
}
