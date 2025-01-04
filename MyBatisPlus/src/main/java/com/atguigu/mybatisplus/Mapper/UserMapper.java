package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    /**
     *
     * @param page
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);

    int deleteByAgeAndEmail(@Param("age") Integer age, @Param("email") String email);

    int insertSelective(User user);

}
