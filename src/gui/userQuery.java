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
    JFrame jf = new JFrame("�û���Ϣ��ѯ");
    DefaultTableModel model;
    JScrollPane scrollPane;
    JTable table;
    Vector<String> columnNames;
    private JPopupMenu m_popupMenu;
    private int row;
    public static Object mdseName;

    //��װ��ͼ
    public userQuery(User user) throws SQLException {
        //�������
        JPanel panel = new JPanel();
        //���
        columnNames = new Vector<String>(Arrays.asList(new String[]{"�û�id", "�û��ֻ�����", "�û�����", "���"}));//��ͷ
        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.SellerSeekOrder(user)};//��ȡ������

    }

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        User user = UserDaoImpl.findUserbyID("2240129107");
        userQuery s1 = new userQuery(user);
    }
}

