package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * ClassName: StatementQueryPart
 * Package: com.atguigu.api.statement
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 13:36
 * @Version 1.0
 */
public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        //依赖：
        //    驱动版本 8+ com.mysql.cj.jdbc.Driver
        //    驱动版本 5+ com.mysql.jdbc.Driver
        DriverManager.registerDriver(new Driver());
        //2.获取连接
        //jdbc:数据库厂商名://ip地址:port/数据库名
        //jdbc:mysql://127.0.0.1:3306/atguigu
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "root");
        //3.创建statement
        Statement statement = connection.createStatement();
        //4.发送sql语句，并且获取返回结果
        String sql = "SELECT * FROM t_user;";
        ResultSet resultSet = statement.executeQuery(sql);
        //5.进行结果集解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"--"+account+"--"+password+"--"+nickname);
        }
        //6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
