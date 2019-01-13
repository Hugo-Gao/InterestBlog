package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.service.BlogService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author yunfan.gyf
 **/
@Controller
@RequestMapping("backend")
public class BackendController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    public String backend() {
        System.out.println("---backend----");
        return "back/backend";
    }

    @RequestMapping("write_blog")
    public String writeBlog(Blog blog, ModelMap modelMap) {
        if (StringUtils.isEmpty(blog.getContent())) {
            return "back/backend_write_blog";
        }
        ResultMsg msg = ResultMsg.successMsg("");
        blogService.postBlog(blog);
        msg.setSuccessMsg("博客发表成功");
        return "redirect:/backend/write_blog?" + msg.asUrlParams();
    }

}
