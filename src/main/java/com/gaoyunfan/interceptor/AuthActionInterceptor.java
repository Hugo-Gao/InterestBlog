package com.gaoyunfan.interceptor;

import com.gaoyunfan.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author yunfan.gyf
 **/
@Component
public class AuthActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = UserContext.getUser();
        if(user==null) {
            String msg = URLEncoder.encode("请先登录", "utf-8");
            String target = URLEncoder.encode(httpServletRequest.getRequestURI(),"utf-8");
            if ("GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
                httpServletResponse.sendRedirect("/user/login?errorMsg=" + msg + "&target=" + target);
            }else {
                httpServletResponse.sendRedirect("/user/login?errorMsg=" + msg);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
