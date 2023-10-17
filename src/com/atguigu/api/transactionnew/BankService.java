package com.atguigu.api.transactionnew;

import com.atguigu.api.utils.JdbcUtils;
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
    public void transfer(String addAccount, String subAccount, int money) throws Exception {
        BankDao bankDao = new BankDao();
        Connection connection = JdbcUtils.getConnection();
        try {
            connection.setAutoCommit(false);

            bankDao.add(addAccount, money);
            System.out.println("----------------------");
            bankDao.sub(subAccount, money);

            connection.commit();
        } catch (Exception e) {
            connection.rollback();

            throw e;
        }finally {
            JdbcUtils.closeConnection();
        }
    }

    @Test
    public void test() throws Exception {
        transfer("lvdandan","ergouzi",  500);
    }
}
