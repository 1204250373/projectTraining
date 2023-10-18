package gui;

import beans.User;
import dao.impl.DoOrder;
import dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class userQuery {
    JFrame jf = new JFrame("用户信息查询");
    DefaultTableModel model;
    JScrollPane scrollPane;
    JTable table;
    Vector<String> columnNames;
    private JPopupMenu m_popupMenu;
    private int row;
    public static Object mdseName;

    //组装视图
    public userQuery(User user) throws SQLException {
        //创建面板
        JPanel panel = new JPanel();
        //表格
        columnNames = new Vector<String>(Arrays.asList(new String[]{"用户id", "用户手机号码", "用户密码", "余额"}));//表头
        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.SellerSeekOrder(user)};//获取表内容

    }

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        User user = UserDaoImpl.findUserbyID("2240129107");
        userQuery s1 = new userQuery(user);
    }
}

