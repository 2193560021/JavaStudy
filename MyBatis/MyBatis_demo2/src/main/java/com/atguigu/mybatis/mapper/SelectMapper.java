package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    User getUserById(@Param("id") Integer id);

    List<User> AllUser();

    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);


    @MapKey("id")
    Map<String, Object> getAllUserToMap();
}
