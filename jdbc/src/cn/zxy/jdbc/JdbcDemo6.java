package cn.zxy.jdbc;

import cn.zxy.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo6 {
    public static void main(String[] args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql1 = "update account set balance = balance - ? where id = ?";
        String sql2 = "update account set balance = balance + ? where id = ?";
        try {
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setInt(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);
            pstmt1.executeUpdate();
            int a = 999 / 0;
            pstmt2.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            try {
                if (conn !=null){
                    conn.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
           JdbcUtils.close(pstmt1,conn);
           JdbcUtils.close(pstmt2,null);
        }
    }
}
