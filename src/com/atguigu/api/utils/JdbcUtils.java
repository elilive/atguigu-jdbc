package com.atguigu.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.ha.selector.DataSourceSelectorFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtils
 * Package: com.atguigu.api.utils
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 23:20
 * @Version 1.0
 */
public class JdbcUtils {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            threadLocal.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
