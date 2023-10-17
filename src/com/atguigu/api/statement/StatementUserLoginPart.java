package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: StatementUserLoginPart
 * Package: com.atguigu.api.statement
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 14:06
 * @Version 1.0
 */
public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.键盘输入事件，收集账号和密码信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();

        System.out.println("请输入密码");
        String password = scanner.nextLine();
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 3.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "root");
        //4.创建statement
        Statement statement = connection.createStatement();
        //5.发送查询SQL语句，并获取返回结果
        String sql = "select * from t_user where account = '"+account+"' and password = '"+password+"' ;";
        ResultSet resultSet = statement.executeQuery(sql);
        //6.结果判断，显示登录成功还是失败
        if (resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登录失败");
        }
        //7.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
        scanner.close();
    }
}
