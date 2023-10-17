package com.atguigu.api.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: BankDao
 * Package: com.atguigu.api.transaction
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 21:53
 * @Version 1.0
 */
public class BankDao {
    public void add(String account,int money,Connection connection) throws Exception {
        String sql = "UPDATE t_bank SET money = ? + money WHERE account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("add成功");
    }

    public void sub(String account,int money,Connection connection) throws Exception {
        String sql = "UPDATE t_bank SET money = money - ? WHERE account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("sub成功");
    }
}
