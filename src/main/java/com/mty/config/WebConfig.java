package com.mty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    //不注册这个在过滤器中 service将报空
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    //添加拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截路径
        String[] addPathPatters={
                "/**"
        };
        //添加不拦截路径
        String[] excludePathPatters={
                "/","/login","/register","/file/imgUpload"
        };
        //注册登录拦截器
        registry.addInterceptor(loginInterceptor()).addPathPatterns(addPathPatters).excludePathPatterns(excludePathPatters);
        //如果多条拦截器则增加多条
    }



}