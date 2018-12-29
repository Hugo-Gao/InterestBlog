package com.gaoyunfan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yunfan.gyf
 **/
@Controller
@RequestMapping("backend")
public class BackendController {

    @RequestMapping("")
    public String backend() {
        System.out.println("---backend----");
        return "back/backend";
    }

}
