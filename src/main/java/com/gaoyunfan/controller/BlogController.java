package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.Blog;
import com.gaoyunfan.model.Comment;
import com.gaoyunfan.model.Tag;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.BlogService;
import com.gaoyunfan.service.CommentService;
import com.gaoyunfan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
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

    @Autowired
    private CommentService commentService;


    /**
     * 获取博客文章及评论内容
     * @param blogId
     * @param modelMap
     * @return
     */
    @RequestMapping("/blog/{blogId}")
    public String getBlog(@PathVariable("blogId") int blogId, ModelMap modelMap) {
        Blog blogDetail = blogService.getBlogDetail(blogId);
        if(blogDetail==null){
            ResultMsg msg = ResultMsg.errorMsg("该博客不存在");
            return "redirect:/?" + msg.asUrlParams();
        }
        //给博客查询tag
        List<Tag> tagList = blogService.getTagList(blogId);
        //给博客增加点击量
        int views = blogService.increAndGetBlogView(blogId);
        User user = userService.getUser();
        //查询博客评论
        List<Comment> comments = commentService.queryComment(blogId);
        modelMap.put("blog", blogDetail);
        modelMap.put("tagList", tagList);
        modelMap.put("user", user);
        modelMap.put("views", views);
        modelMap.put("blogId", blogId);
        modelMap.put("commentList", comments);
        return "blog/blog_detail";
    }

    /**
     * 给某篇博客增加评论
     *
     * @param blogId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/blog/{blogId}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable("blogId") int blogId, Comment comment, ModelMap modelMap) {
        if (StringUtils.isBlank(comment.getContent()) || StringUtils.isBlank(comment.getNickname())) {
            return "redirect:/blog/" + blogId;
        }
        comment.setBlogId(blogId);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);
        return "redirect:/blog/" + blogId;
    }


}
