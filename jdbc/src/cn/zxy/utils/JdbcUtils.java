package cn.zxy.utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;
    //private static String driver;

    static {
        try {
            Properties prop = new Properties();
            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbcUtils.properties");
            String path = Objects.requireNonNull(resource).getPath();
            prop.load(new FileReader(path));
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            String driver = prop.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt,Connection conn) {
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
        }
    }
    public static void close(ResultSet resultSet,Statement stmt, Connection conn) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt,conn);
    }
}
