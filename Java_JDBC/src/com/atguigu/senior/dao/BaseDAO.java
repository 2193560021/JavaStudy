package com.atguigu.senior.dao;

import com.atguigu.senior.util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO  {

    public int executeUpdate(String sql,Object... params) throws Exception{

        Connection connection = JDBCUtilV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1,params[i]);
            }
        }

        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilV2.release();
        }

        return i;


    }

    /**
     * 通用查询：多行多列
     */

    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object... params) throws Exception{
        Connection connection = JDBCUtilV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        if(params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1,params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<>();
        while(resultSet.next()){
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);

                String fieldName = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(t,value);

            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtilV2.release();
        }
        return list;
    }


    /**
     * 获取第一个结果
     */
    public <T> T executeQueryBean(Class<T> clazz,String sql,Object... params) throws Exception{
        List<T> list = this.executeQuery(clazz, sql, params);

        if(list == null || list.size() == 0){
            return null;
        }

        return list.get(0);
    }


}
