package com.gaoyunfan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author gaoyunfan
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static");
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css");
        registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/img");
        registry.addResourceHandler("/static/layui/**").addResourceLocations("classpath:/static/layui");
        super.addResourceHandlers(registry);
    }
}
