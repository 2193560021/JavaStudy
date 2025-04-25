package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
@ComponentScan({"com.itheima.controller","com.itheima.ex"})
@EnableWebMvc
public class SpringMVCConfig {

    //配置简单的异常处理器类
    /*@Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        //不管是什么异常，统一的响应一个友好页面
        simpleMappingExceptionResolver.setDefaultErrorView("/error1.html");
        //区分异常类型，根据不同的异常类型，可以跳转不同的视图
        Properties properties = new Properties();//键值对，key：异常对象全限定名，vlaue：跳转的视图
        properties.setProperty("java.lang.RuntimeException","/error1.html");
        properties.setProperty("java.io.FileNotFoundException","/error2.html");
        simpleMappingExceptionResolver.setExceptionMappings(properties);

        return simpleMappingExceptionResolver;
    }*/

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }


}
