package com.gaoyunfan.service;

import com.alibaba.fastjson.JSON;
import com.gaoyunfan.dto.ResultMsg;
import com.gaoyunfan.model.User;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Service
public class UserService {

    @Autowired
    private FileService fileService;

    private String USER_KEY = "INTEREST_USER";
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private StringRedisTemplate template;

    @Value("${nginx.url}")
    private String nginxUrl;

    /**
     * 从redis中获取已经注册的用户
     *
     * @return
     */
    public User getUser() {
        String userStr = template.opsForValue().get(USER_KEY);
        if (userStr == null) {
            return null;
        } else {
            User user = JSON.parseObject(userStr, User.class);
            user.setAvaterPath(nginxUrl + user.getAvaterPath());
            return user;
        }
    }


    public boolean addUser(User user, ResultMsg msg) {
        msg.setSuccessMsg(null);
        if (getUser() != null) {
            msg.setErrorMsg("您已经注册，请直接登录,");
            return false;
        }
        List<String> imgPath = fileService.getImgPath(Lists.newArrayList(user.getAvatarFile()));
        if (imgPath.size() > 0) {
            user.setAvaterPath(imgPath.get(0));
        } else {
            msg.setErrorMsg("头像储存失败");
            logger.debug("头像储存失败");
            return false;
        }
        user.setAvatarFile(null);
        String userStr = JSON.toJSONString(user);
        template.opsForValue().set(USER_KEY, userStr);
        return true;
    }

    public void deleteUser() {
        template.delete(USER_KEY);
    }


    /**
     * 从新得的user中获取新属性值设置回老数据中去
     * 删除旧数据
     * 设置新数据
     *
     * @param user
     */
    public void updateUser(User user,ResultMsg msg) {
        User saveUser = getUser();
        for (Field field : User.class.getDeclaredFields()) {
            try {

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object o = field.get(user);
                if (o != null) {
                    field.set(saveUser, o);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("User 更新失败");
            }
        }
        logger.debug("user 已经更新为" + saveUser);
        deleteUser();
        addUser(saveUser, msg);
    }

    public boolean validateUser(User user, ResultMsg msg) {
        User saveUser = getUser();
        if (saveUser == null) {
            msg.setErrorMsg("请先注册");
            return false;
        }
        if (saveUser.getEmail().equals(user.getEmail()) && saveUser.getPassword().equals(user.getPassword())) {
            return true;
        }else {
            msg.setErrorMsg("帐号密码错误");
            return false;
        }
    }
}
