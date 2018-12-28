package com.gaoyunfan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yunfan.gyf
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(Model model) {
        System.out.println("login");
        model.addAttribute("name", "hugo");
        return "user/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        System.out.println("----register-----");
        return "user/register";
    }

}
