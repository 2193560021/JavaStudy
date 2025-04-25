package com.itheima.ex;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //简单的响应一个友好提示页面error1.html
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("/error1.html");

        //前后端分离开发，响应json格式的字符串 {"code":0,"message":"","data":""}
        String resultJson = "{\"code\":0,\"message\":\"\",\"data\":\"\"}";
        try {
            response.getWriter().write(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
