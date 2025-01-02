package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void testPage(){
        Page<User> userPage = new Page<>(2,3);
        Page<User> userPage1 = userMapper.selectPage(userPage, null);
        System.out.println(userPage1.getRecords());
        System.out.println(userPage1.getPages());
        System.out.println(userPage1.getTotal());
        System.out.println(userPage1.hasNext());
        System.out.println(userPage1.hasPrevious());
    }


    @Test
    public void testPageVo(){
        Page<User> userPage = new Page<>(2,3);
        userMapper.selectPageVo(userPage, 20);
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getPages());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }
}
