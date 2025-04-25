package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@ResponseBody
@RestController
public class ResponseController2 {


    @GetMapping("/ajax/req3")
    //@ResponseBody
    public User res3() throws JsonProcessingException {
        System.out.println("/ajax/req3 runnging...");
        User user = new User();
        user.setUsername("haohao");
        user.setAge(20);

        return user;
    }


    @GetMapping("/ajax/req2")
    @ResponseBody
    public String res2() throws JsonProcessingException {
        User user = new User();
        user.setUsername("haohao");
        user.setAge(19);
        //json转换工具
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(user);

        return jsonStr;
    }

    @GetMapping("/ajax/req1")
    @ResponseBody
    public String res1(){
        return "{\"username\":\"haohao\",\"age\":18}";
    }





}
