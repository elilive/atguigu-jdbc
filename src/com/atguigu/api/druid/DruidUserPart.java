package com.atguigu.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: DruidUserPart
 * Package: com.atguigu.api.druid
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 22:54
 * @Version 1.0
 */
public class DruidUserPart {
    public void testHard() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/atguigu");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        Connection connection = druidDataSource.getConnection();

        connection.close();
    }

    public void testSoft() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = DruidUserPart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(inputStream);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        connection.close();
    }
}
