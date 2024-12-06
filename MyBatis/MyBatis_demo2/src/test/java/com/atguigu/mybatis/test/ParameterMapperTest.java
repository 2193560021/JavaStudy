package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {


    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lyy = mapper.checkLoginByParam("LYY","123");

        if(lyy != null) System.out.println("Success");
        else System.out.println("Failed");
    }


    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int i = mapper.insertUser(new User(null, "LXX", "123", 23, "Female", "lxx@qq.com"));
        System.out.println(i);

    }

    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username","LYY");
        map.put("password","123");
        User lyy = mapper.checkLoginByMap(map);

        if(lyy != null) System.out.println("Success");
        else System.out.println("Failed");
    }

    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lyy = mapper.checkLogin("LYY","123");

        if(lyy != null) System.out.println("Success");
        else System.out.println("Failed");
    }

    @Test
    public void testGetByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lyy = mapper.getByUsername("LYY");

        System.out.println(lyy);
    }


    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
//        List<User> list = mapper.getAllUser();
        allUser.forEach(user -> System.out.println(user));
    }

    @Test
    public void testJDBC() throws Exception {
        String username = "LYY";
        Class.forName("");
        Connection connection = DriverManager.getConnection("", "", "");
        PreparedStatement ps = connection.prepareStatement("select * from t_user where username = ?");
        ps.setString(1,username);



    }
}
