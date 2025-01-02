package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.Product;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

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

    @Test
    public void testConcurrentUpdate() {
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());
        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());
        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);
        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        if(result2 == 0){
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }
        System.out.println("小王修改结果：" + result2);
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
