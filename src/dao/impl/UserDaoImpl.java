package dao.impl;

import beans.User;
import dbutils.DBHelper;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl  {
    /*
    @param id
    @return ���ض�Ӧid�˺ŵ�User������������ڷ���null
     */
    public static User findUserbyID(String sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        String sql = "select * from myuser WHERE sid='" + sid +"';";
        ResultSet rs =  DBHelper.query(sql);
        User u = new User();
        try{
            if (rs.next()){
                u.setId(rs.getString("uid"));
                u.setPassword(rs.getString("password"));
                u.setBalance(rs.getDouble("userBalance"));
                u.setPhone(rs.getString("phone"));
                u.setSid(rs.getString("sid"));
                u.setType(rs.getString("userType"));
                return u;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
            return null;

    }

    /*
    ���ÿ��˻�ID������ע�ᣬ��ʽ����û�
    @param id        ����ID���к����ɵ�ID�˺�
    @param serialNum
     */
    public static void addUser(String sid, String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "SELECT * from myuser where sid ='"+sid+"';";
        ResultSet rs = DBHelper.query(sql);
        if(rs.next()){
            JOptionPane.showMessageDialog(new JFrame(), "��ѧ���ѱ�ע��");
        }else{
            sql = "INSERT into myuser (phone,sid,password,userType,userBalance) VALUE(null,'"+sid+"','"+password+"','�û�',0);";
            DBHelper.update(sql);

        }

    }
    /*
    �����ƶ�user����������
    @param user ���趨�����ݵ�user����
     */
    public void updateUser(User user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//        //TODO Auto-generated method stub
//        Connection conn = DBHelper.getConnection();
//        String sql = "UPDATE User SET name ='" + user.getName() +
//                "',password = '" + user.getPassword() + "',balance =" +
//                user.getBalance() + "WHERE id ='" + user.getId() + "';";
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try{
//            stat = conn.prepareStatement(sql);
//            stat.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            DBHelper.closeAll(conn,stat,rs);
//        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//        UserDaoImpl.addUser("1010101010","123456");
        User us = UserDaoImpl.findUserbyID("1010101010");
        System.out.println(us.getPhone());
        System.out.println(us.getSid());
        System.out.println(us.getType());
    }
}
