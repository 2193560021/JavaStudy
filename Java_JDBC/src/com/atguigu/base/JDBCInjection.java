package com.atguigu.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        //1.注册驱动

        //2.获取连接对象
        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        //3.获取SQL语句对象
        Statement statement = root.createStatement();

        System.out.println("请输入姓名");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        //4.
        String sql = "select * from t_emp where emp_name = '"+ name +"'";
        ResultSet resultSet = statement.executeQuery(sql);


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
        root.close();


    }
}
