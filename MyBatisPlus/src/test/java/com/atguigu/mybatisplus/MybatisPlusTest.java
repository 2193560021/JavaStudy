package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> users = userMapper.selectList(null);

        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("刘宇阳");
        user.setAge(23);
        user.setEmail("2193@qq.com");
        int insert = userMapper.insert(user);

        System.out.println(insert);

    }

    @Test
    public void delById(){
//        int i = userMapper.deleteById(1872268618859810817L);
//        System.out.println(i);

//        Map<String,Object> map = new HashMap<>();
//
//        map.put("name","刘宇阳");
//        map.put("age",23);
//
//        userMapper.deleteByMap(map);

        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int i = userMapper.deleteBatchIds(list);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){

        User user = new User();

        user.setId(1872272128544350209L);
        user.setEmail("liuyuyng@gmail.com");

        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testSelect(){

//        User user = userMapper.selectById(1L);
//
//        System.out.println(user);

        List<Long> list = Arrays.asList(1L,2L,3L);

        List<User> users = userMapper.selectBatchIds(list);

        System.out.println(users);

        userMapper.selectMapById();

    }
}
