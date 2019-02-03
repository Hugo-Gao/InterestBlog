package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yunfan.gyf
 **/
@Controller
@RequestMapping("/backend")
public class BackendController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    public String backend(ModelMap modelMap) {
        User user = userService.getUser();
        modelMap.put("user", user);
        return "back/backend";
    }

    @RequestMapping("/write_blog")
    public String writeBlog(Blog blog, ModelMap modelMap) {
        User user = userService.getUser();
        modelMap.put("user", user);
        if (StringUtils.isEmpty(blog.getContent())) {
            return "back/backend_write_blog";
        }
//        ResultMsg msg = ResultMsg.successMsg("");
        blogService.postBlog(blog);
//        msg.setSuccessMsg("博客发表成功");
        modelMap.put("successMsg", "博客发表成功");
//        return "redirect:/backend/write_blog?" + msg.asUrlParams();
        return "back/backend_write_blog";

    }

    @RequestMapping("/aboutme")
    public String aboutme(ModelMap modelMap, User user) {
        if (user.getEmail() == null) {
            User oldUser = userService.getUser();
            modelMap.put("user", oldUser);
            return "back/aboutme";
        }else {
            ResultMsg msg = ResultMsg.successMsg("");
            userService.updateUser(user,msg);
            User updateUser = userService.getUser();
            modelMap.put("successMsg", "更新成功");
            modelMap.put("user", updateUser);
            return "back/aboutme";
        }
    }

}
