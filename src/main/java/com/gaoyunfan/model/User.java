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
    private MultipartFile avatarFile;
    private String avaterPath;
    private String aboutme;
}
