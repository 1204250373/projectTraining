package dao;

import beans.User;

public interface UserDao {
    public User findUserbyID(String id);
    public void creatNewUser(); //²åÈë¿ÕÕËºÅ£¬ÓÃÓÚ×¢²á
    public void addUser(String id,int serialNum);
    public void updateUser(User user);
}
