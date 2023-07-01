package com.example.myseckill.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebHandlerInteceptor())
                .addPathPatterns("/**")
//                .excludePathPatterns("/login", "/", "login.html", "/css/**", "/fonts/**", "/images/**", "/js/**");
                .excludePathPatterns("/login/*", "/", "login.html", "/css/**", "/fonts/**", "/images/**", "/js/**");
    }
}
