package com.atguigu.senior.util;

/*JDBC工具类(V2.0)   1、维护一个连接池对象 维护了一个线程

* 注意：工具类仅对外提供共性的功能代码，所以方法均为静态方法！
* */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtilV2 {
    //创建一个连接池应用

    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {

            Properties properties = new Properties();
            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }

            return connection;
//            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void release(){
        try {
            Connection connection = threadLocal.get();
            if(connection != null){
                threadLocal.remove();

                connection.setAutoCommit(true);
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
