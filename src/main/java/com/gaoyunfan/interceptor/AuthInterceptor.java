package com.gaoyunfan.interceptor;

import com.gaoyunfan.model.User;
import com.gaoyunfan.service.UserService;
import com.gaoyunfan.util.CookieUtil;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yunfan.gyf
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;

    /**
     * 将User对象从Cookie中提取出来注入UserContext中
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if ("errorMsg".equals(entry.getKey()) || "successMsg".equals(entry.getKey()) || "target".equals(entry.getKey())) {
                httpServletRequest.setAttribute(entry.getKey(), Joiner.on(",").join(entry.getValue()));
            }
        }
        String reqUri = httpServletRequest.getRequestURI();
        if (reqUri.startsWith("/static") || reqUri.startsWith("/error")) {
            return true;
        }

        String email = CookieUtil.getCookie(httpServletRequest, "user_cookie");
        if (email != null) {
            User user = userService.getUser();
            if (user.getEmail().equals(email)) {
                UserContext.setUser(user);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserContext.remove();
    }
}
