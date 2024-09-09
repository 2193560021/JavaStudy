package com.atguigu.senior;

import com.atguigu.senior.dao.iBankDao;
import com.atguigu.senior.dao.iEmployeeDao;
import com.atguigu.senior.dao.impl.BankDaoImpl;
import com.atguigu.senior.dao.impl.EmployeeDaoImpl;
import com.atguigu.senior.pojo.Employee;
import com.atguigu.senior.util.JDBCUtil;
import com.atguigu.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtilTest {
    @Test
    public void testGetConnection(){
        Connection connection = JDBCUtil.getConnection();
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        System.out.println(connection);
        System.out.println(connection1);
        System.out.println(connection2);

        JDBCUtil.release(connection);
    }

    @Test
    public void testJDBCV2(){
//        Connection connection1 = JDBCUtil.getConnection();
//        Connection connection2 = JDBCUtil.getConnection();
//        Connection connection3 = JDBCUtil.getConnection();
//        System.out.println(connection1);
//        System.out.println(connection2);
//        System.out.println(connection3);


        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);

        JDBCUtilV2.release();
    }

    @Test
    public void testEmployeeDao(){
        iEmployeeDao employeeDao =  new EmployeeDaoImpl();

        List<Employee> employeeList = employeeDao.selectAll();

        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }


    @Test
    public void testEmployeeBeanDao(){
        iEmployeeDao employeeDao =  new EmployeeDaoImpl();

        Employee employee1 = employeeDao.selectByEmId(2);

        System.out.println("employee = " + employee1);

    }

    @Test
    public void testEmployeeDaoInsert(){
        iEmployeeDao employeeDao =  new EmployeeDaoImpl();

        Employee employee = new Employee();
        employee.setEmpName("ZJQ");
        employee.setEmpSalary(123.45);
        employee.setEmpAge(23);

        int insert = employeeDao.insert(employee);
        if(insert > 0){
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }

    }

    @Test
    public void testEmployeeDaoUpdate(){
        iEmployeeDao employeeDao =  new EmployeeDaoImpl();

        Employee employee = new Employee();
        employee.setEmpName("ZJQ");
        employee.setEmpSalary(321.45);

        int update = employeeDao.update(employee);
        if(update > 0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }

    }

    @Test
    public void testEmployeeDaoDelete(){
        iEmployeeDao employeeDao =  new EmployeeDaoImpl();

        int delete = employeeDao.delete(3);
        if(delete > 0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }

    }

    @Test
    public void testBankDaoTransaction() {
        iBankDao bankDao =  new BankDaoImpl();
        Connection connection = null;
        try {
            connection = JDBCUtilV2.getConnection();
            connection.setAutoCommit(false);

            bankDao.subMoney(1,100);
            bankDao.addMoney(2,100);

            connection.commit();


        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            JDBCUtilV2.release();
        }


    }
}
