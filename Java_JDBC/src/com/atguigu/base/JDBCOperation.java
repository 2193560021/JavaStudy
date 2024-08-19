package com.atguigu.base;

import org.junit.Test;

import java.sql.*;

public class JDBCOperation {

    @Test
    public void testQuerySingleRowAndCol() throws SQLException {
        //1.ZhuCe


        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        PreparedStatement preparedStatement = root.prepareStatement("select count(*) as count from t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int count = resultSet.getInt("count");
            System.out.println(count);
        }

        resultSet.close();
        preparedStatement.close();
        root.close();



    }

    @Test
    public void testQuerySingleRow() throws SQLException {


        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        PreparedStatement preparedStatement = root.prepareStatement("select * from t_emp where emp_id = ?");

        preparedStatement.setInt(1,4);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            Double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        resultSet.close();
        preparedStatement.close();
        root.close();
    }

    @Test
    public void testQueryMoreRow() throws Exception{

        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        PreparedStatement preparedStatement = root.prepareStatement("select * from t_emp where emp_age > ?");

        preparedStatement.setInt(1,25);
        ResultSet resultSet = preparedStatement.executeQuery();



        while (resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            Double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        resultSet.close();
        preparedStatement.close();
        root.close();


    }



    @Test
    public void testInsert() throws Exception{

        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        PreparedStatement preparedStatement = root.prepareStatement("INSERT INTO t_emp(emp_name, emp_salary, emp_age) values (?,?,?)");

        preparedStatement.setString(1,"刘璇");
        preparedStatement.setDouble(2,345.67);
        preparedStatement.setInt(3,28);
        int i = preparedStatement.executeUpdate();


        if(i > 0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }


        preparedStatement.close();
        root.close();


    }



    @Test
    public void testUpdate() throws Exception{

        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        PreparedStatement preparedStatement = root.prepareStatement("update t_emp set emp_salary = 2333 where emp_name = ?");

        preparedStatement.setString(1,"刘璇");
        int i = preparedStatement.executeUpdate();


        if(i > 0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }


        preparedStatement.close();
        root.close();


    }

}
