package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResponseController1 {

    //重定向方式
    @GetMapping("/res1")
    public String res1(){
        return "redirect:/index.jsp";
    }

    //转发方式
    @RequestMapping("/res2")
    public String res2(){
        return "forward:/index.jsp";
    }

    //转发方式
    @RequestMapping("/res3")
    public ModelAndView res3(ModelAndView modelAndView){
        //ModelAndView封装模型数据和视图名
        //设置模型数据
        User user = new User();
        user.setUsername("haohao");
        user.setAge(18);
        modelAndView.addObject("user",user);
        //设置视图名称，在页面中展示模型数据
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }

    //直接回写字符串
    @RequestMapping("/res4")
    @ResponseBody //告诉SpringMVC返回的字符串不是视图名 是以响应体方式响应的数据
    public String res4(){
        return "hello haohao!";
    }

}
