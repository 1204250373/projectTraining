package gui;

import beans.User;
import dao.impl.ScreenUtils;
import dao.impl.UserDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class adminFrame {
    JFrame jf = new JFrame("����Ա������");

    final int WIDTH = 1300;
    final int HEIGTH = 700;

    public adminFrame(User user) {
        //��������������
        jf.setResizable(false);
        //�Ѵ��������Ϊ������岢��Ϊ͸�����������֡�
        JPanel pan = (JPanel)jf.getContentPane();
        pan.setOpaque(false);//͸��
        pan.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("����Աϵͳ",JLabel.CENTER);
        Font font1 = new Font("����",Font.PLAIN,100);

        l1.setFont(font1);
        l1.setForeground(Color.black);
        pan.setLayout(new BorderLayout());
        pan.add(l1,new BorderLayout().CENTER);

        //���ò˵���
        JMenuBar jmb = new JMenuBar();
        JMenu jm1 = new JMenu("�û�����ϵͳ");
        JMenuItem jmi1 = new JMenuItem("�û���Ϣ��ѯ");
        JMenuItem jmi2 = new JMenuItem("�û���Ϣ�޸�");

        jm1.add(jmi1);
        jm1.add(jmi2);
        jmb.add(jm1);

        //�޸Ĳ˵�������
        Font font2 = new Font("����",Font.ITALIC,25);
        jm1.setFont(font2);
        jmi1.setFont(font2);
        jmi2.setFont(font2);

        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,/*bg.getIconWidth(),bg.getIconHeight()*/1300,777);
        jf.setLocationRelativeTo(null);
        jf.setJMenuBar(jmb);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        jmi1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DoGoodsFrame(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        jmi2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new BuyerSeeGoodsFrame(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) throws Exception {
        User user = UserDaoImpl.findUserbyID("2240129516");
        adminFrame y1 = new adminFrame(user);

    }
}

