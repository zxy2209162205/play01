package cn.zxy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "123");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123");
        String sql = "update account set balance = 2000 where id =1";
        Statement stmt = conn.createStatement();
        int i = stmt.executeUpdate(sql);
        System.out.println(i);
        stmt.close();
        conn.close();
    }
}
