package gui;

import beans.User;
import dao.impl.DoOrder;
import dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class userQuery {
    JFrame jf = new JFrame("卖家管理订单");
    DefaultTableModel model;
    JScrollPane scrollPane;
    JTable table;
    Vector<String> columnNames;
    private JPopupMenu  m_popupMenu;
    private  int row;
    public  static Object mdseName;
    //组装视图
    public userQuery(User user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //创建面板
        JPanel panel = new JPanel();
        //表格
        columnNames = new Vector<String>(Arrays.asList(new String[]{"用户id","学号","用户手机号码", "用户类型", "余额"}));//表头
        Vector<Vector<String>>[] data = new Vector[]{UserDaoImpl.AllUserby()};//获取表内容
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//表头和内容放入表

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//表示所有的列都不可以拖动
        table.setRowHeight(25);//设置行高
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//整张表的大小
        scrollPane = new JScrollPane(table);//给表整个滑轮
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().SOUTH);//把表放入面板

        // 将滚动面板添加到窗体中央
        m_popupMenu = new JPopupMenu();

        JMenuItem delMenItem = new JMenuItem("删除用户");
        JMenuItem delMenItem2 = new JMenuItem("查看售卖情况");
        JMenuItem delMenItem3 = new JMenuItem("查看购买情况");
        delMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mdseName=table.getValueAt(row,1);

            }
        });

        m_popupMenu.add(delMenItem);
        m_popupMenu.add(delMenItem2);
        m_popupMenu.add(delMenItem3);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        //组装账号密码框
        Box b2 = Box.createHorizontalBox();

        b2.add(Box.createHorizontalStrut(100));



        b2.add(Box.createHorizontalStrut(100));


        //组装按钮
        Box b1 = Box.createHorizontalBox();

        table.setFont(new Font("宋体", Font.PLAIN, 18));



        //组装全部
        Box allBox = Box.createVerticalBox();
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b2);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b1);
        allBox.add(Box.createVerticalStrut(20));
        panel.add(allBox, new BorderLayout().CENTER);

        jf.setContentPane(panel);
        //设置窗口属性
        jf.setSize(1000, 700);//大小
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//居中
        jf.setVisible(true);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.dispose();
            }
        });

        m_popupMenu.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                if(data[0].get(row).get(UserDaoImpl.TYEPE).equals("管理员")){
                    delMenItem.setText("无法删除");
                }else{
                    delMenItem.setText("删除");
                }
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        delMenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(data[0].get(row).get(UserDaoImpl.TYEPE).equals("管理员")){
                    JOptionPane.showMessageDialog(new JFrame(), "无法删除");
                }else{

                    UserDaoImpl.DeleteUser(user.getType(),data[0].get(row).get(UserDaoImpl.SID));
                    JOptionPane.showMessageDialog(new JFrame(), "删除成功");
                    Vector<Vector<String>>[] data = new Vector[0];//获取表内容
                    try {
                        data = new Vector[]{UserDaoImpl.AllUserby()};
                        RefreshTable(data[0]);
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (InstantiationException instantiationException) {
                        instantiationException.printStackTrace();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }

                }

            }
        });
        delMenItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = null;
                try {
                    user = UserDaoImpl.findUserbyID(data[0].get(row).get(UserDaoImpl.SID));
                    adminSeekUser(user);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }


            }
        });
        delMenItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = null;
                try {
                    user = UserDaoImpl.findUserbyID(data[0].get(row).get(UserDaoImpl.SID));
                    adminSeekUser2(user);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }


            }
        });
    }



    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        User user = UserDaoImpl.findUserbyID("2240129107");
        userQuery s1 = new userQuery(user);
    }

    //接受data，刷新display
    private void RefreshTable(Vector<Vector<String>> data){

//			创建一个TableModel对象，并传入表头和表内容
        TableModel tableModel=new DefaultTableModel(data,columnNames);
//			将TableModel对象传入Table表格
        table.setModel(tableModel);

    }

    private void tableMouseClicked(MouseEvent evt) {
        mouseRightButtonClick(evt);
    }
    private void mouseRightButtonClick(MouseEvent evt) {
        //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
        if (evt.getButton() == MouseEvent.BUTTON3) {
            //通过点击位置找到点击为表格中的行
            row = table.rowAtPoint(evt.getPoint());
            if (row == -1) {
                return;
            }
            //将表格所选项设为当前右键点击的行
            table.setRowSelectionInterval(row,row);
            //弹出菜单
            m_popupMenu.show(table, evt.getX(), evt.getY());
        }
    }

    //卖家处理销售情况
    public void adminSeekUser(User user) throws SQLException {
        JFrame jf = new JFrame("卖家管理订单");
        DefaultTableModel model;
        JScrollPane scrollPane;
        JTable table;
        Vector<String> columnNames;
        JPopupMenu  m_popupMenu;
        int row = 0 ;
        //创建面板
        JPanel panel = new JPanel();
        //表格
        columnNames = new Vector<String>(Arrays.asList(new String[]{"订单编号", "卖家手机号码", "卖家id", "买家电话","买家id",
                "书名", "书单价", "购买数量","总价","订单状态","购买时间","买家地址"}));//表头

        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.SellerSeekOrder(user)};//获取表内容
//		SSGBook.SortBook_State_DOWN(data);
        DoOrder.SortOrderState_UP(data[0]);
//		DoOrder.SortOrderDELIVER_UP(data);
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//表头和内容放入表

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//表示所有的列都不可以拖动
        table.setRowHeight(25);//设置行高
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//整张表的大小
        scrollPane = new JScrollPane(table);//给表整个滑轮
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().SOUTH);//把表放入面板

        // 将滚动面板添加到窗体中央



        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        //组装账号密码框
        Box b2 = Box.createHorizontalBox();

        b2.add(Box.createHorizontalStrut(100));



        b2.add(Box.createHorizontalStrut(100));


        //组装按钮
        Box b1 = Box.createHorizontalBox();

        table.setFont(new Font("宋体", Font.PLAIN, 18));



        //组装全部
        Box allBox = Box.createVerticalBox();
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b2);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b1);
        allBox.add(Box.createVerticalStrut(20));
        panel.add(allBox, new BorderLayout().CENTER);

        jf.setContentPane(panel);
        //设置窗口属性
        jf.setSize(1000, 700);//大小
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//居中
        jf.setVisible(true);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.dispose();
            }
        });

    }

    //购买情况
    public void adminSeekUser2(User user) throws SQLException {
        JFrame jf = new JFrame("买家查看订单");
        DefaultTableModel model;
        JScrollPane scrollPane;
        JTable table;
        Vector<String> columnNames;
        JPopupMenu  m_popupMenu;
        int row;
        Object mdseName;

        //创建面板
        JPanel panel = new JPanel();
        //表格
        columnNames = new Vector<String>(Arrays.asList(new String[]{"订单编号", "卖家手机号码", "卖家id", "买家电话","买家id",
                "书名", "书单价", "购买数量","总价","订单状态","购买时间","买家地址","发货时间","收货情况"}));//表头

        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.BuyerSeekOrder(user)};//获取表内容
        DoOrder.SortOrderrece_UP(data[0]);
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//表头和内容放入表

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//表示所有的列都不可以拖动
        table.setRowHeight(25);//设置行高
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//整张表的大小
        scrollPane = new JScrollPane(table);//给表整个滑轮
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().NORTH);//把表放入面板


        // 将滚动面板添加到窗体中央
        m_popupMenu = new JPopupMenu();

        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("发货");

        m_popupMenu.add(delMenItem);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });


        //组装账号密码框
        Box b2 = Box.createHorizontalBox();


        //设置字体大小


        table.setFont(new Font("宋体", Font.PLAIN, 18));



        //组装全部
        Box allBox = Box.createVerticalBox();
//		allBox.add(Box.createVerticalStrut(20));
        allBox.add(b2);
//		allBox.add(Box.createVerticalStrut(20));
//		allBox.add(b1);
//		allBox.add(Box.createVerticalStrut(20));
//		allBox.add(b3);
        allBox.add(Box.createVerticalStrut(20));
        panel.add(allBox, new BorderLayout().CENTER);

        jf.setContentPane(panel);
        //设置窗口属性
        jf.setSize(1000, 700);//大小
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//居中
        jf.setVisible(true);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.dispose();
            }
        });

    }
}

