package com.atguigu.advanced.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    @Test

    public void testHardCodeDruid() throws Exception{
        /*
        硬编码

         */
        DruidDataSource druidDataSource = new DruidDataSource();

        //1.必须配置
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/atguigu?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        //2.非必须配置
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);

        //3.通过连接池获取连接对象
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);

        //4.


        //5.回收链接
        connection.close();

    }

    @Test
    public void testResourcesDruid() throws Exception{
        /*
        硬编码

         */

        //1.创建properties集合
        Properties properties = new Properties();

        //2.非必须配置
        InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");

        properties.load(inputStream);

        //3.通过连接池获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //4.
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //5.回收链接
        connection.close();

    }
}
