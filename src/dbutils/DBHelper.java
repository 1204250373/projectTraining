package dbutils;

import  java.sql.*;
//用于建立数据库连接的类
public class DBHelper {
    private static Connection conn = null;
    private static String DRIVE = "com.mysql.jdbc.Driver"; //数据库驱动类
    private static final String DB_CONN_RUL = "jdbc:mysql://localhost:3306/projecttraining?useSSL=false";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    /*
        获得一个连接，连接数据库
        @return connection类对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            connection = DriverManager.getConnection(DB_CONN_RUL, DB_USER_NAME, DB_PASSWORD);//生成连接
        } catch (SQLException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeAll(Connection conn,
                                PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
