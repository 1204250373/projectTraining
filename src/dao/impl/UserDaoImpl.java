package dao.impl;

import beans.User;
import dbutils.DBHelper;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class UserDaoImpl  {

    //��ȡ�����û���Ϣ
    public static Vector<Vector<String>> AllUserby() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        String sql = "select * from myuser;";
        ResultSet rs =  DBHelper.query(sql);
       //�޲ι���ʱʹsid=-1�����ڼ�����޴��û�
        Vector<Vector<String>> users =new Vector<Vector<String>>();
        try{
            while (rs.next()){
                Vector<String> u = new Vector<String>();
                u.add(rs.getString("uid"));
                u.add(rs.getString("sid"));
//                u.add(rs.getString("password"));
                u.add(rs.getString("phone"));
                u.add(rs.getString("userType"));
                u.add(rs.getString("userBalance"));
                users.add(u);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    /*
    @param id
    @return ���ض�Ӧid�˺ŵ�User������������ڷ���null
     */
    public static User findUserbyID(String sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        String sql = "select * from myuser WHERE sid='" + sid +"';";
        ResultSet rs =  DBHelper.query(sql);
        User u = new User();//�޲ι���ʱʹsid=-1�����ڼ�����޴��û�
        try{
            if (rs.next()){
                u.setId(rs.getString("uid"));
                u.setPassword(rs.getString("password"));
                u.setBalance(rs.getDouble("userBalance"));
                u.setPhone(rs.getString("phone"));
                u.setSid(rs.getString("sid"));
                u.setType(rs.getString("userType"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }
    //true �������sid��
    public static boolean isUserSid(String sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "select * from myuser WHERE sid='" + sid +"';";
        ResultSet rs =  DBHelper.query(sql);
        try{
            if (rs.next()){
                return true;//true�����д�Phone
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //true �������phone��
    public static boolean isUserPhone(String phone) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "select * from myuser WHERE phone='" + phone +"';";
        ResultSet rs =  DBHelper.query(sql);
        try{
            if (rs.next()){
                return true;//true�����д�Phone
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
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

    /* ɾ���û�*/
    public static void DeleteUser(String admin,String sid){
        String sql ="delete from myuser where sid ="+sid+";";
        DBHelper.update(sql);
        DoOrder.Deleteorder(admin,sid);
    }
    /*�޸ĵ绰���� */
    public static void ChangePhone(String sid ,String phone){
        String sql ="update myuser set phone="+phone+" where sid ="+sid+";";
        DBHelper.update(sql);
    }
    /* �޸�����*/
    public static void ChangePassword(String sid ,String password){
        String sql ="update myuser set password="+password+" where sid ="+sid+";";
        DBHelper.update(sql);
    }



//    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
////        UserDaoImpl.addUser("1010101010","123456");
//        User us = UserDaoImpl.findUserbyID("10101010");
//        System.out.println(us.getSid().equals("-1"));
//        ChangePhone("2","1234");
//
//    }

    public final static int UID = 0;
    public final static int SID = 1;
    public final static int PHONE = 2;
    public final static int TYEPE = 3;
    public final static int BALANCE = 4;
}
