package com.lyy.allbadminton.controller;


import cn.hutool.core.util.BooleanUtil;
import com.lyy.allbadminton.dto.UserLoginDTO;
import com.lyy.allbadminton.entity.User;
import com.lyy.allbadminton.result.Result;
import com.lyy.allbadminton.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@RestController
@RequestMapping("/allbadminton/user")
@RequiredArgsConstructor
@Api("用户测试")
public class UserController {


    private final IUserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Boolean register(Long id){
        User user = userService.getById(id);
        if (user != null) return BooleanUtil.isTrue(true);
        return BooleanUtil.isTrue(false);
    }


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserLoginDTO userLoginDTO){
        Result login = userService.login(userLoginDTO);
        return login;
    }






}
