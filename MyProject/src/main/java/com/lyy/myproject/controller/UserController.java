package com.lyy.myproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(String username, String password) {
        // 调用用户服务的登录方法
        // ...
        return "登录成功";
    }
}
