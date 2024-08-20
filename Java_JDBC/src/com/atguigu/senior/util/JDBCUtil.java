package com.atguigu.senior.util;

/*JDBC工具类   1、维护一个连接池对象
* 注意：工具类仅对外提供共性的功能代码，所以方法均为静态方法！
* */

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtil {
    //创建一个连接池应用

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
    }
}
