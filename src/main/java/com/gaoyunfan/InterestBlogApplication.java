package com.gaoyunfan;

import com.gaoyunfan.dao.BlogElasticDao;
import com.gaoyunfan.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yunfan.gyf
 **/

@SpringBootApplication
@EnableTransactionManagement
public class InterestBlogApplication  {



    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(InterestBlogApplication.class, args);
    }


}
