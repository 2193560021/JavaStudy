package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {

    List<Emp> getEmpByCondition(Emp emp);
}
