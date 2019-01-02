package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.User;
import com.gaoyunfan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yunfan.gyf
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public String login(ModelMap modelMap, User user) {
        if (user == null || StringUtils.isBlank(user.getEmail())) {
            return "user/login";
        }
        if (StringUtils.isBlank(user.getPassword())) {
            ResultMsg msg = ResultMsg.errorMsg("请填写密码");
            return "redirect:/user/login?" + msg.asUrlParams();
        }
        ResultMsg msg = ResultMsg.successMsg("");
        boolean isLogin = userService.validateUser(user, msg);
        if (isLogin) {
            modelMap.put("avaterPath", user.getAvaterPath());
            modelMap.put("email", user.getEmail());
            logger.debug("user " + user + " 登录成功");
            return "back/backend";
        } else {
            return "redirect:/user/login?" + msg.asUrlParams();
        }
    }

    @RequestMapping("/register")
    public String register(ModelMap modelMap, User user) {
        //如果没有携带user，则直接返回注册界面
        if (user == null || user.getEmail() == null) {
            System.out.println("----register-----");
            return "user/register";
        }
        if (userService.getUser() != null) {
            ResultMsg msg = ResultMsg.errorMsg("该服务器已经注册，请直接登录");
            return "redirect:/user/register?" + msg.asUrlParams();
        }
        //如果携带有user，则进行注册
        ResultMsg msg = UserHelper.validate(user);
        if (msg.isSuccess() && userService.addUser(user, msg)) {
            modelMap.put("avaterPath", user.getAvaterPath());
            modelMap.put("email", user.getEmail());
            logger.debug("user " + user + " 储存成功");
            return "back/backend";
        } else {
            return "redirect:/user/register?" + msg.asUrlParams();
        }
    }

}
