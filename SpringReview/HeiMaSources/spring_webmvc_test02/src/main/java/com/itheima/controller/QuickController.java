package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.QuickService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/quick")
public class QuickController {

    //直接注入Service进行使用
    @Autowired
    private QuickService quickService;


    //@RequestMapping(value = {"/show","/showxxx","/showyyy"},method = RequestMethod.GET)
    //@PostMapping("/show")

    //http://localhost:8080/appName/quick/show
    @GetMapping("/show")
    public String show() throws HttpRequestMethodNotSupportedException {
        System.out.println("show running...."+quickService);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/show2")
    public String show2(){
        System.out.println("show222 running...."+quickService);
        return "forward:/index.jsp";
    }


}
