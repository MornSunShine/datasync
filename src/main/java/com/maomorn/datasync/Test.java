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
 */


public class Test {
    public static void sqlserver(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "test", "test");
            conn.setAutoCommit(false);
            int count=100000;
            String sql="INSERT INTO student(name,age,sex) VALUES (?,?,?)";
            PreparedStatement statement=conn.prepareStatement(sql);
            long start=System.currentTimeMillis();
            for(int i=0;i<count;i++){
                statement.setString(1,""+i);
                statement.setInt(2,20);
                statement.setInt(3,1);
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

    public static void postgresql(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "postgres123");
            conn.setAutoCommit(false);
            int count=10000;
            String sql="INSERT INTO student(name,age,sex) VALUES (?,?,?)";
            PreparedStatement statement=conn.prepareStatement(sql);
            long start=System.currentTimeMillis();
            for(int i=0;i<count;i++){
                statement.setString(1,""+i);
                statement.setInt(2,20);
                statement.setInt(3,1);
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
    public static void main(String [] args){
            long start=System.currentTimeMillis();
//            sqlserver();
        postgresql();
            System.out.println("花费时间："+(System.currentTimeMillis()-start)+"ms");

    }
}
