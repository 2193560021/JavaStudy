package com.atguigu.advanced;

import com.atguigu.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdvanced {
    @Test
    public void testORM() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);

        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();

        //4.编写sql语句

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp where emp_id = ?");

        preparedStatement.setInt(1,4);
        ResultSet resultSet = preparedStatement.executeQuery();


        //5.处理结果

        Employee employee = null;
        while (resultSet.next()){
            employee = new Employee();
            employee.setEmpId(resultSet.getInt("emp_id"));
            employee.setEmpName(resultSet.getString("emp_name"));
            employee.setEmpSalary(resultSet.getDouble("emp_salary"));
            employee.setEmpAge(resultSet.getInt("emp_age"));

        }

        System.out.println(employee);

        //6.释放资源（先开后关原则）
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testORMList() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);

        //3.获取执行sql语句的对象
        Statement statement = connection.createStatement();

        //4.编写sql语句

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp");

//        preparedStatement.setInt(1,4);
        ResultSet resultSet = preparedStatement.executeQuery();


        //5.处理结果

        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()){
            employee = new Employee();
            employee.setEmpId(resultSet.getInt("emp_id"));
            employee.setEmpName(resultSet.getString("emp_name"));
            employee.setEmpSalary(resultSet.getDouble("emp_salary"));
            employee.setEmpAge(resultSet.getInt("emp_age"));

            employeeList.add(employee);

        }
        for(Employee emp: employeeList)
            System.out.println(emp);


        //6.释放资源（先开后关原则）
        resultSet.close();
        statement.close();
        connection.close();
    }



    @Test
    public void testReturnPK() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);

        //3.获取执行sql语句的对象
//        Statement statement = connection.createStatement();

        //4.编写sql语句

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_emp(emp_name, emp_salary, emp_age) values (?,?,?);",Statement.RETURN_GENERATED_KEYS);

//        preparedStatement.setInt(1,4);
//        preparedStatement.executeUpdate();

        Employee employee = new Employee(null,"LYY",123.45,22);
        preparedStatement.setString(1,employee.getEmpName());
        preparedStatement.setDouble(2,employee.getEmpSalary());
        preparedStatement.setInt(3,employee.getEmpAge());

        int result = preparedStatement.executeUpdate();

        ResultSet generatedKeys = null;
        //5.处理结果

        if(result > 0){
            System.out.println("成功");

            generatedKeys = preparedStatement.getGeneratedKeys();

            if(generatedKeys.next()){
                int empId = generatedKeys.getInt(1);
                employee.setEmpId(empId);
            }

            System.out.println(employee);
        }
        else
            System.out.println("失败");
        //6.释放资源（先开后关原则）
//        resultSet.close();
        if(generatedKeys != null){
            generatedKeys.close();
        }
        preparedStatement.close();
        connection.close();
    }



    @Test
    public void testMoreInsert() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);


        //4.编写sql语句

        String sql = "INSERT INTO t_emp(emp_name, emp_salary, emp_age) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        long start = System.currentTimeMillis();
        for(int i = 0;i < 10000;i++){
            preparedStatement.setString(1,"小奶酪" + i);
            preparedStatement.setDouble(2,100.00 + i);
            preparedStatement.setInt(3,20 + i);
            int result = preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();

        System.out.println("消耗时间" + (end - start));

        preparedStatement.close();
        connection.close();
    }



    @Test
    public void testBatch() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true";
        String userName = "root";
        String passWord = "123456";
        Connection connection = DriverManager.getConnection(url, userName, passWord);


        //4.编写sql语句

        String sql = "INSERT INTO t_emp(emp_name, emp_salary, emp_age) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        long start = System.currentTimeMillis();
        for(int i = 0;i < 10000;i++){
            preparedStatement.setString(1,"小奶酪" + i);
            preparedStatement.setDouble(2,100.00 + i);
            preparedStatement.setInt(3,20 + i);

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();

        System.out.println("消耗时间" + (end - start));

        preparedStatement.close();
        connection.close();
    }



}
