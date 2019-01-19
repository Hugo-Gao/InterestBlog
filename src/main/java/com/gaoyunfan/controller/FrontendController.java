package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Controller
public class FrontendController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String homepage(@RequestParam(value = "page", defaultValue = "1") int page, ModelMap modelMap) {
        User user = userService.getUser();
        if (user.getEmail() == null) {
            ResultMsg msg = ResultMsg.errorMsg("清先注册后使用");
            return "redirect:/user/register?" + msg.asUrlParams();
        }
        Pagination pagination = new Pagination(page);
        List<Blog> blogList = blogService.selectPreBlogList(pagination);
        int pageSum = blogService.getPageSum();
        modelMap.put("user", user);
        modelMap.put("blogList", blogList);
        modelMap.put("page", page);
        modelMap.put("pageSum", pageSum);
        return "frontend/home";
    }


}
