package com.gaoyunfan.controller;

import com.gaoyunfan.model.User;
import com.gaoyunfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yunfan.gyf
 **/
@Controller
public class InitController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String init() {
        userService.getUser();
        return "init";
    }

}
