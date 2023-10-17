package com.atguigu.api.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ClassName: BankService
 * Package: com.atguigu.api.transaction
 * Description:
 *
 * @Author Eli-Chen
 * @Create 2023/10/17 21:55
 * @Version 1.0
 */
public class BankService {
    public void transfer(String addAccount,String subAccount,int money) throws Exception {
        BankDao bankDao = new BankDao();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "root");

        try {
            connection.setAutoCommit(false);

            bankDao.add(addAccount, money,connection);
            System.out.println("----------------------");
            bankDao.sub(subAccount, money,connection);

            connection.commit();
        } catch (Exception e) {
            connection.rollback();

            throw e;
        }finally {
            connection.close();
        }
    }

    @Test
    public void test() throws Exception {
        transfer("lvdandan","ergouzi",  500);
    }
}
