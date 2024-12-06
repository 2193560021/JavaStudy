package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {



    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        Map<String, Object> userById = mapper.getAllUserToMap();

        System.out.println(userById);
    }

    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        Map<String, Object> userById = mapper.getUserByIdToMap(4);

        System.out.println(userById);
    }


    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        User userById = mapper.getUserById(4);

        System.out.println(userById);
    }


    @Test
    public void testAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        List<User> userList = mapper.AllUser();

        System.out.println(userList);
    }
}
