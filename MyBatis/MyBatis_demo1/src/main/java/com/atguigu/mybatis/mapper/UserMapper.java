package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {

    int insertUser();

    void updateUser();

    User getUserById();

    List<User> getAllUser();
}
