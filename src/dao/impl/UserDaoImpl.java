package dao.impl;

import beans.User;
import dao.UserDao;
import dbutils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /*
    @param id
    @return 返回对应id账号的User对象，如果不存在返回null
     */
    @Override
    public User findUserbyID(String id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        if (id == null){
            return null;
        }
        Connection conn = DBHelper.getConnection();
        String sql = "select * from User WHERE id='" + id +"';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        User u = new User();
        try{
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery(); //executeQuery 该方法用来执行查询语句
            while (rs.next()){
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setBalance(rs.getDouble("balance"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //DBHelper.closeAll(conn,stat,rs);
        }
        if (u.getId().equals(""))
            return null;
        else
            return u;
    }

    /*
    插入空账户，用于注册
     */
    @Override
    public void creatNewUser() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        Connection conn = DBHelper.getConnection();
        String sql = "INSERT USER (id,name,password,balance) VALUE(null,null,null,null);";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
    }
    /*
    设置空账户ID，用于注册，正式添加用户
    @param id        根据ID序列号生成的ID账号
    @param serialNum
     */
    @Override
    public void addUser(String id, int serialNum) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = DBHelper.getConnection();
        String sql = "UPDATE User SET id ='" + id + "'WHERE serialNum ='" + serialNum + "';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
    }
    /*
    更新制定user的所有数据
    @param user 已设定好数据的user对象
     */
    @Override
    public void updateUser(User user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //TODO Auto-generated method stub
        Connection conn = DBHelper.getConnection();
        String sql = "UPDATE User SET name ='" + user.getName() +
                "',password = '" + user.getPassword() + "',balance =" +
                user.getBalance() + "WHERE id ='" + user.getId() + "';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
    }

}
