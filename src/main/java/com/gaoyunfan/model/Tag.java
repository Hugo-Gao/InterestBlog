package com.gaoyunfan.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author yunfan.gyf
 **/
@Data
public class Tag {
    private String tag;
    private String style;
    private int id;

}
