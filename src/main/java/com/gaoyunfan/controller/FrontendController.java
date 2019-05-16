package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.User;
import com.gaoyunfan.rest.RestCode;
import com.gaoyunfan.rest.RestResponse;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("aboutme")
    public String aboutme(ModelMap modelMap) {
        User user = userService.getUser();
        modelMap.put("user", user);
        return "frontend/aboutme";
    }

    @RequestMapping("search")
    @ResponseBody
    public RestResponse<List<Blog>> search(@RequestParam("keyWord") String keyWord) {
        if (StringUtils.isBlank(keyWord)) {
            return RestResponse.error(RestCode.KEY_WORD_NOT_NULL);
        }
        List<Blog> blogList = blogService.getBlogByLike(keyWord);
        if (blogList.size() == 0) {
            return RestResponse.error(RestCode.BLOG_NOT_FOUND);
        }
        return RestResponse.success(blogList);
    }

}
