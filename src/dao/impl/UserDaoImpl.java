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

    //获取所有用户信息
    public static Vector<Vector<String>> AllUserby() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        String sql = "select * from myuser;";
        ResultSet rs =  DBHelper.query(sql);
       //无参构造时使sid=-1；用于检测有无此用户
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
    @return 返回对应id账号的User对象，如果不存在返回null
     */
    public static User findUserbyID(String sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        String sql = "select * from myuser WHERE sid='" + sid +"';";
        ResultSet rs =  DBHelper.query(sql);
        User u = new User();//无参构造时使sid=-1；用于检测有无此用户
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
    //true 代表此有sid了
    public static boolean isUserSid(String sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "select * from myuser WHERE sid='" + sid +"';";
        ResultSet rs =  DBHelper.query(sql);
        try{
            if (rs.next()){
                return true;//true代表有此Phone
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //true 代表此有phone了
    public static boolean isUserPhone(String phone) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "select * from myuser WHERE phone='" + phone +"';";
        ResultSet rs =  DBHelper.query(sql);
        try{
            if (rs.next()){
                return true;//true代表有此Phone
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /*
    设置空账户ID，用于注册，正式添加用户
    @param id        根据ID序列号生成的ID账号
    @param serialNum
     */
    public static void addUser(String sid, String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "SELECT * from myuser where sid ='"+sid+"';";
        ResultSet rs = DBHelper.query(sql);
        if(rs.next()){
            JOptionPane.showMessageDialog(new JFrame(), "该学号已被注册");
        }else{
            sql = "INSERT into myuser (phone,sid,password,userType,userBalance) VALUE(null,'"+sid+"','"+password+"','用户',0);";
            DBHelper.update(sql);
        }

    }

    /* 删除用户*/
    public static void DeleteUser(String admin,String sid){
        String sql ="delete from myuser where sid ="+sid+";";
        DBHelper.update(sql);
        DoOrder.Deleteorder(admin,sid);
    }
    /*修改电话号码 */
    public static void ChangePhone(String sid ,String phone){
        String sql ="update myuser set phone="+phone+" where sid ="+sid+";";
        DBHelper.update(sql);
    }
    /* 修改密码*/
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
