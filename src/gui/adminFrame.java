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
    JFrame jf = new JFrame("管理员主界面");

    final int WIDTH = 1300;
    final int HEIGTH = 700;

    public adminFrame(User user) {
        //给窗口设置属性
        jf.setResizable(false);
        //把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan = (JPanel)jf.getContentPane();
        pan.setOpaque(false);//透明
        pan.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("管理员系统",JLabel.CENTER);
        Font font1 = new Font("宋体",Font.PLAIN,100);

        l1.setFont(font1);
        l1.setForeground(Color.black);
        pan.setLayout(new BorderLayout());
        pan.add(l1,new BorderLayout().CENTER);

        //设置菜单栏
        JMenuBar jmb = new JMenuBar();
        JMenu jm1 = new JMenu("用户管理系统");
        JMenuItem jmi1 = new JMenuItem("用户信息查询");
//        JMenuItem jmi2 = new JMenuItem("用户信息修改");

        JMenu jm2 = new JMenu("用户界面");
        JMenuItem jmi3 = new JMenuItem("切换");

        jm1.add(jmi1);
//        jm1.add(jmi2);
        jmb.add(jm1);

        jm2.add(jmi3);
        jmb.add(jm2);

        //修改菜单栏字体
        Font font2 = new Font("宋体",Font.ITALIC,25);
        jm1.setFont(font2);
        jm2.setFont(font2);
        jmi1.setFont(font2);
//        jmi2.setFont(font2);
        jmi3.setFont(font2);

        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,/*bg.getIconWidth(),bg.getIconHeight()*/1300,777);
        jf.setLocationRelativeTo(null);
        jf.setJMenuBar(jmb);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        jmi1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new userQuery(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });
//        jmi2.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    new BuyerSeeGoodsFrame(user);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//            }
//        });
        jmi3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new yonghuFrame(user);

            }
        });
    }


    public static void main(String[] args) throws Exception {
        User user = UserDaoImpl.findUserbyID("2240129516");
        adminFrame y1 = new adminFrame(user);

    }
}

