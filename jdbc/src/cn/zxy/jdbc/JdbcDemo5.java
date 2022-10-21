package cn.zxy.jdbc;

import cn.zxy.utils.JdbcUtils;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名： ");
        String username = sc.nextLine();
        System.out.println("请输入密码： ");
        String password = sc.nextLine();
        System.out.println(new JdbcDemo5().login(username, password));
    }
    public boolean login(String name,String password){
        if (name == null || password == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rS = null;
        try {
             conn = JdbcUtils.getConnection();
             String sql = "select * from user where name = ? and password = ?";
             //stmt = conn.createStatement();
             preStmt = conn.prepareStatement(sql);
            preStmt.setString(1,name);
            preStmt.setString(2,password);
            rS = preStmt.executeQuery();
            //代码写的多垃圾
           /* if (rS.next()){
                return true;
            } else {
                return false;
            }*/
            return rS.next();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
            JdbcUtils.close(rS,preStmt,conn);
        }
        return false;
    }
}

//a' or 'a' = 'a
