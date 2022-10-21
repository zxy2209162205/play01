package cn.zxy.jdbc;

import cn.zxy.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo4 {
    public static void main(String[] args) {
        List<Emp> all = new JdbcDemo4().findAll2();
        for (Emp emp : all) {
            System.out.println(emp);
        }
    }
   /* public List<Emp> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rS = null;
        List<Emp> l = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "select * from account";
             conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123");
             stmt = conn.createStatement();
             rS = stmt.executeQuery(sql);
             Emp emp;
            while (rS.next()){
                int id = rS.getInt("id");
                String name = rS.getString("name");
                int balance = rS.getInt("balance");
                emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setBalance(balance);
                l.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return l;
    }*/


    public List<Emp> findAll2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rS = null;
        List<Emp> l = new ArrayList<>();
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = JdbcUtils.getConnection();
            String sql = "select * from account";
            //conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123");
            stmt = conn.createStatement();
            rS = stmt.executeQuery(sql);
            Emp emp;
            while (rS.next()) {
                int id = rS.getInt("id");
                String name = rS.getString("name");
                int balance = rS.getInt("balance");
                emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setBalance(balance);
                l.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rS, stmt, conn);
        }
        return l;
    }
}
