package com.gaoyunfan.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yunfan.gyf
 **/
@Data
public class User {
    private Long id;
    private String email;
    private String password;
    private String blogName;
    private MultipartFile avatarFile;
    private String avaterPath;
    private String aboutme;
    private String sinaUrl;
    private String githubUrl;
    private String qq;
}
