package com.gaoyunfan.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yunfan.gyf
 **/
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
        registry.addInterceptor(authActionInterceptor).addPathPatterns("/backend/**");
        super.addInterceptors(registry);
    }
}
