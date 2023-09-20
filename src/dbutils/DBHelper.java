package dbutils;

import dao.DBconfig;

import  java.sql.*;
//���ڽ������ݿ����ӵ���
public class DBHelper implements DBconfig {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static Statement  st = null;
    static ResultSet rs = null;

    /**
     * �õ����ݿ�����
     */
    public static  Connection  getConnection() throws ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException {
        try {
            // ָ����������
            Class.forName(driver);
            // �������ݿ�����
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (Exception e) {
//			// ������ӹ��̳����쳣���׳��쳣��Ϣ
//						throw new SQLException("�������������ʧ�ܣ�");
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * �õ�Statement
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
     * ��̬update(String sql)�������÷�������ֵΪint�ͣ�����������ݵ�����insert����ɾ��delete�����ģ�update��
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
     * query(String sql)�������÷�������ֵΪResultSet���͵Ķ�������������ݵĲ�ѯ��select��
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
     * �ͷ���Դ
     */
    public static void close() {
        // ���rs���գ��ر�rs
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // ���pstmt���գ��ر�pstmt
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // ���st���գ��ر�pstmt
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // ���conn���գ��ر�conn
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
