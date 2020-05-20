package com.bjpowernode.springboot.config;

import com.bjpowernode.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot拦截器配置
 *
 */
//@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对来自 /api/** 链接的请求进行拦截，对登录请求/api/login不进行拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/boot/**") //要拦截哪些路径
                .excludePathPatterns("/boot/login", "/boot/logout"); //不拦截哪些路径
    }*/
}
