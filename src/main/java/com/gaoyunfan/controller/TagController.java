package com.gaoyunfan.controller;

import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Tag;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.TagService;
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
@RequestMapping("tags")
public class TagController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @RequestMapping("")
    public String tagPage(ModelMap modelMap) {
        User user = userService.getUser();
        List<Tag> tagList = blogService.getTagList(-1);
        modelMap.put("user", user);
        modelMap.put("tagList", tagList);
        return "frontend/tag";
    }

    @RequestMapping("{tagId}")
    public String tagDetail(@PathVariable int tagId,ModelMap modelMap) {
        User user = userService.getUser();
        List<Blog> blogList = blogService.getBlogByTag(tagId);
        String tag = tagService.getTagById(tagId);
        modelMap.put("user", user);
        modelMap.put("blogList", blogList);
        modelMap.put("tag", tag);
        return "frontend/tag_detail";
    }
}
