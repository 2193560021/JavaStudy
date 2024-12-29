package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.pojo.User;
import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){

        long count = userService.count();

        System.out.println(count);


    }

    @Test
    public void testInsertMore(){

        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10;i++){
            User user = new User();
            user.setName("刘宇阳" + i);
            user.setAge(23 + i);
            user.setEmail("liuyuyng@gmail.com" + i);
            list.add(user);
        }

        boolean b = userService.saveBatch(list);

        System.out.println(b);
    }
}
