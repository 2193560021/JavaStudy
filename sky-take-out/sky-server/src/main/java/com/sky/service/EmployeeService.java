package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    Integer save(EmployeeDTO employeeDTO);


    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    void setStatus(Integer status, Long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);
}
