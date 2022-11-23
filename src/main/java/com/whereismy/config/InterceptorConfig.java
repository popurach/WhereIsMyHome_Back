package com.whereismy.config;

import com.whereismy.interceptor.JwtInterceptor;
import com.whereismy.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(new JwtUtil()))
                .addPathPatterns("/qna/**")
                .excludePathPatterns("/login","/swagger-ui/**");
    }
}
