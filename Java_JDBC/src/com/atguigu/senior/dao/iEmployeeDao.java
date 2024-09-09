package com.atguigu.senior.dao;

import com.atguigu.senior.pojo.Employee;

import java.util.List;

public interface iEmployeeDao {

    List<Employee> selectAll();

    Employee selectByEmId(Integer empid);

    /**12321
     *
     * @param employee
     * @return
     */
    int insert(Employee employee);

    int update(Employee employee);

    int delete(Integer empId);


}
