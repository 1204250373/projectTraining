package dao;

import beans.User;

public interface UserDao {
    public User findUserbyID(String id);
    public void creatNewUser(); //������˺ţ�����ע��
    public void addUser(String id,int serialNum);
    public void updateUser(User user);
}
