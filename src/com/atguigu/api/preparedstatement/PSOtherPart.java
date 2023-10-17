package com.atguigu.api.preparedstatement;

import org.junit.Test;

import java.sql.*;

/**
 * ClassName: PSOtherPart
 * Package: com.atguigu.api.preparedstatement
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 18:08
 * @Version 1.0
 */
public class PSOtherPart {

    @Test
    public void testReturnPrimaryKey() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "root");
        String sql = "INSERT INTO t_user(account, PASSWORD, nickname) VALUES (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, "tttt");
        preparedStatement.setObject(2, "tttt");
        preparedStatement.setObject(3, "tttt");
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("插入成功");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            System.out.println(id);
        }else {
            System.out.println("插入失败");
        }

        preparedStatement.close();
        connection.close();
    }
}
