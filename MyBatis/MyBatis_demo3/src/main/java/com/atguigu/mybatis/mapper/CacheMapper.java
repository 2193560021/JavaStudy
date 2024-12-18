package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper  {
    Emp getEmpByEid(@Param("eid") int eid);

    void insertEmp(Emp emp);
}
