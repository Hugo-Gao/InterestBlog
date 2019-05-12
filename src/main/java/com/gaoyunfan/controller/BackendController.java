package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Pagination;
import com.gaoyunfan.model.Tag;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.CommentService;
import com.gaoyunfan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

    @RequestMapping("")
    public String backend(ModelMap modelMap) {
        User user = userService.getUser();
        modelMap.put("user", user);
        int blogNum = blogService.getBlogNum();
        int blogViews = blogService.getBlogSumView();
        int commentNum = commentService.getCommentNum(-1);
        int tagNum = blogService.getTagNum();
        modelMap.put("blogNum", blogNum);
        modelMap.put("blogViews", blogViews);
        modelMap.put("commentNum", commentNum);
        modelMap.put("tagNum", tagNum);
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

    @RequestMapping(value = "/articles")
    public String articles(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex, ModelMap modelMap) {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        User oldUser = userService.getUser();
        modelMap.put("user", oldUser);
        int pageSum = blogService.getPageSum();
        Pagination pagination = new Pagination(pageIndex);
        List<Blog> blogList = blogService.selectPreBlogList(pagination);
        for (Blog blog : blogList) {
            blog.setViews(blogService.getBlogView(blog.getId()));
            blog.setComments(commentService.queryComment(blog.getId()));
            List<Tag> tagList = blogService.getTagList(blog.getId());
            blog.setTagList(tagList);
        }
        modelMap.put("page",pageIndex);
        modelMap.put("pageSum", pageSum);
        modelMap.put("blogList", blogList);
        return "back/articles";
    }

    @RequestMapping(value = "deleteBlog", method = RequestMethod.POST)
    public String deleteBlog(HttpServletRequest request) {
        String blogId = request.getParameter("blogId");
        blogService.deleteBlog(blogId);
        return "back/articles";
    }

}
