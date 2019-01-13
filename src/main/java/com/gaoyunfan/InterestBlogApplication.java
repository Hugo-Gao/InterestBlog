package com.gaoyunfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yunfan.gyf
 **/

@SpringBootApplication
@EnableTransactionManagement
public class InterestBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestBlogApplication.class, args);
    }
}
