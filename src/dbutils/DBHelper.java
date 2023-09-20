package dbutils;

import dao.DBconfig;

import  java.sql.*;
//用于建立数据库连接的类
public class DBHelper implements DBconfig {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static Statement  st = null;
    static ResultSet rs = null;

    /**
     * 得到数据库连接
     */
    public static  Connection  getConnection() throws ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException {
        try {
            // 指定驱动程序
            Class.forName(driver);
            // 建立数据库连结
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (Exception e) {
//			// 如果连接过程出现异常，抛出异常信息
//						throw new SQLException("驱动错误或连接失败！");
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 得到Statement
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static  Statement  openStatement() {
        try {
            Connection connection=getConnection();
            st=connection.createStatement();
        } catch (ClassNotFoundException |InstantiationException |IllegalAccessException |SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }
    /**
     * 静态update(String sql)方法，该方法返回值为int型，用于完成数据的增（insert）、删（delete）、改（update）
     */
    public static  int  update(String sql){
        int n=0;
        try {
            Statement st=openStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  n;
    }


    /**
     * query(String sql)方法，该方法返回值为ResultSet类型的对象，用于完成数据的查询（select）
     */
    public static  ResultSet  query(String sql){;
        try {
            Statement st=openStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  rs;
    }



    /**
     * 释放资源
     */
    public static void close() {
        // 如果rs不空，关闭rs
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果pstmt不空，关闭pstmt
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果st不空，关闭pstmt
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果conn不空，关闭conn
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
