package com.gaoyunfan.service;

import com.gaoyunfan.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        User user = userService.getUser();
        assert user != null;
        System.out.println(user);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("598335769@qq.com");
        user.setPassword("123456");
        user.setAboutme("This is a test");
        userService.addUser(user, null);
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setAboutme("This is a new test ");
        userService.updateUser(user,null);
    }
}