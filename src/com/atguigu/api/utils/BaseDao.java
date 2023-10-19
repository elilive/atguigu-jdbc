package com.atguigu.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: com.atguigu.api.utils
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/18 00:37
 * @Version 1.0
 */
public abstract class BaseDao {
    public int executeUpdate(String sql,Object...params) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params != null && params.length>0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }

        int rows = preparedStatement.executeUpdate();

        if (connection.getAutoCommit()) {
            JdbcUtils.closeConnection();
        }

        return rows;
    }

    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object...params) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(sql != null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<T> list = new ArrayList<>();
        while (resultSet.next()){
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                String propertyName = metaData.getColumnLabel(i);

                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.closeConnection();
        }

        return list;
    }
}