package com.maomorn.datasync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author: MaoMorn
 * Date: 2017/10/9
 * Time: 13:11
 * Description:
 * postgresql修改表设置自增字段：
 * CREATE SEQUENCE [table_name]_[pk]_seq
 * START WITH 1
 * INCREMENT BY 1
 * NO MINVALUE
 * NO MAXVALUE
 * CACHE 1;
 * ALTER table_name ALTER COLUMN column_name set DEFAULT NEXTVAL("seq_name");
 * 重置更新自增列：DBCC CHECKIDENT(student,reseed,0);
 * 基础插入SQL: "INSERT INTO student(name,age,sex) VALUES (?,?,?)"
 *
 *
 *
 *
 *  数据库类型   mysql  postgresql sqlserver
 *  1w
 *  2w
 *  10w
 */


public class Test {
    static int count = 100000;
    static String sql="INSERT INTO student(name,age,sex) VALUES (?,?,?)";

    public void mysql() {
        try {
            Class.forName("om.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < count; i++) {
                statement.setString(1, "" + i);
                statement.setInt(2, 20);
                statement.setInt(3, 1);
                statement.addBatch();
            }
            statement.executeBatch();
            conn.commit();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postgresql() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "test", "test");
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < count; i++) {
                statement.setString(1, "" + i);
                statement.setInt(2, 20);
                statement.setInt(3, 1);
                statement.addBatch();
            }
            statement.executeBatch();
            conn.commit();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sqlserver() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "test", "test");
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < count; i++) {
                statement.setString(1, "" + i);
                statement.setInt(2, 20);
                statement.setInt(3, 1);
                statement.addBatch();
            }
            statement.executeBatch();
            conn.commit();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        /**
         * 执行时间检测
         */
        System.out.println("花费时间：" + (System.currentTimeMillis() - start) + "ms");

    }
}
