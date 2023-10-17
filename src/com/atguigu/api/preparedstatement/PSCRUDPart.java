package com.atguigu.api.preparedstatement;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: PSCRUDPart
 * Package: com.atguigu.api.preparedstatement
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 16:39
 * @Version 1.0
 */
public class PSCRUDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu","root","root");
        String sql = "INSERT INTO t_user(account, PASSWORD, nickname) VALUES (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "测试");
        int rows = preparedStatement.executeUpdate();
        if (rows>0){
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu","root","root");
        String sql = "UPDATE t_user SET nickname = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, 3);
        int rows = preparedStatement.executeUpdate();
        if (rows>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu","root","root");
        String sql = "DELETE FROM t_user WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,3);
        int rows = preparedStatement.executeUpdate();
        if (rows>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testSelect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "root");
        String sql = "SELECT id,account,password,nickname FROM t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            Map map = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }

//            map.put("id",resultSet.getInt("id"));
//            map.put("account", resultSet.getString("account"));
//            map.put("password", resultSet.getString("password"));
//            map.put("nickname", resultSet.getString("nickname"));
            list.add(map);
        }
        System.out.println(list);

        preparedStatement.close();
        connection.close();
    }
}
