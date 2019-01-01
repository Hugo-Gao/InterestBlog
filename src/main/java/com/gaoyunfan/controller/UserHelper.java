package com.gaoyunfan.controller;

import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yunfan.gyf
 **/
public class UserHelper {
    public static ResultMsg validate(User account) {
        if (StringUtils.isBlank(account.getEmail())) {
            return ResultMsg.errorMsg("Email有误");
        }
        if (StringUtils.isBlank(account.getPassword())) {
            return ResultMsg.errorMsg("密码有误");
        }
        if (StringUtils.isBlank(account.getBlogName())) {
            return ResultMsg.errorMsg("博客名称有误");
        }
        if (StringUtils.isBlank(account.getAboutme())) {
            return ResultMsg.errorMsg("个人详情有误");
        }
        if (account.getAvatarFile().isEmpty()) {
            return ResultMsg.errorMsg("头像不能为空");
        }
        return ResultMsg.successMsg("");
    }
}
