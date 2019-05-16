package com.gaoyunfan.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisService {
    @Autowired
    private StringRedisTemplate template;

    @Test
    public void testZset() {
        Double view = template.opsForZSet().incrementScore("INTERESTBLOG_VIEW", "19", 1);
        Assert.assertNotEquals(view,0);
    }
}
