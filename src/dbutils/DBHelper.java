package dbutils;

import  java.sql.*;
//���ڽ������ݿ����ӵ���
public class DBHelper {
    private static Connection conn = null;
    private static String DRIVE = "com.mysql.jdbc.Driver"; //���ݿ�������
    private static final String DB_CONN_RUL = "jdbc:mysql://localhost:3306/projecttraining?useSSL=false";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    /*
        ���һ�����ӣ��������ݿ�
        @return connection�����
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//��������
            connection = DriverManager.getConnection(DB_CONN_RUL, DB_USER_NAME, DB_PASSWORD);//��������
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
