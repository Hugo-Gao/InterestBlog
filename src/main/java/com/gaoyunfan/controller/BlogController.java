package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @RequestMapping("/blog/{blogId}")
    public String getMessage(@PathVariable("blogId") int blogId, ModelMap modelMap) {
//        Blog blogDetail = blogService.getBlogDetail(blogId);
//        if(blogDetail==null){
//            ResultMsg msg = ResultMsg.errorMsg("该博客不存在");
//            return "redirect:/?" + msg.asUrlParams();
//        }
//        List<String> tagList = blogService.getTagList(blogId);
//        User user = userService.getUser();
//        modelMap.put("blog", blogDetail);
//        modelMap.put("tagList", tagList);
//        modelMap.put("user", user);
        return "blog/blog_detail";
    }
}
