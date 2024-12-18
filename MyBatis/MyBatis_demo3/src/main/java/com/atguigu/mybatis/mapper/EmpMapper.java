package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper {

    List<Emp> getAllEmp();

    Emp getEmpAndDept(@Param("eid") Integer eid);
}
