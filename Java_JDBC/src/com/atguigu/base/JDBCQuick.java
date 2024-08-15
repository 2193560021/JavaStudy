package com.atguigu.base;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuick {
    public static void main(String[] args) throws Exception {

        //1.注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
//        DriverManager.registerDriver(new Driver());

        //2.获取连接对象
        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);

        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();

        //4.编写sql语句

        String sql = "select * from t_emp";

        ResultSet resultSet = statement.executeQuery(sql);

        String result =  resultSet.toString();

        System.out.println(result);

        //5.处理结果

        while (resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            Double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);

        }

        //6.释放资源（先开后关原则）
        resultSet.close();
        statement.close();
        connection.close();

    }
}
