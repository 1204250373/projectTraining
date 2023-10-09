package dao;

import beans.User;

import java.sql.SQLException;

public interface UserDao {
    public User findUserbyID(String id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
    public void creatNewUser() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException; //≤Â»Îø’’À∫≈£¨”√”⁄◊¢≤·
    public void addUser(String id,int serialNum) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
    public void updateUser(User user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
