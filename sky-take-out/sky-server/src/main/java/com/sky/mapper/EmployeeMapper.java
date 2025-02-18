package com.sky.mapper;

import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    @Insert("insert into employee (name, username, password, phone, sex, id_number) " +
            "values (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber})")
    Integer insert(Employee employee);

    @Select("select * from employee limit #{page}, #{pageSize}")
    PageResult page(Integer page, Integer pageSize);
}
