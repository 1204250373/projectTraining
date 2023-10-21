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
    JFrame jf = new JFrame("���ҹ�����");
    DefaultTableModel model;
    JScrollPane scrollPane;
    JTable table;
    Vector<String> columnNames;
    private JPopupMenu  m_popupMenu;
    private  int row;
    public  static Object mdseName;
    //��װ��ͼ
    public userQuery(User user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //�������
        JPanel panel = new JPanel();
        //���
        columnNames = new Vector<String>(Arrays.asList(new String[]{"�û�id","ѧ��","�û��ֻ�����", "�û�����", "���"}));//��ͷ
        Vector<Vector<String>>[] data = new Vector[]{UserDaoImpl.AllUserby()};//��ȡ������
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//��ͷ�����ݷ����

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
        table.setRowHeight(25);//�����и�
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//���ű�Ĵ�С
        scrollPane = new JScrollPane(table);//������������
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().SOUTH);//�ѱ�������

        // �����������ӵ���������
        m_popupMenu = new JPopupMenu();

        JMenuItem delMenItem = new JMenuItem("ɾ���û�");
        JMenuItem delMenItem2 = new JMenuItem("�鿴�������");
        JMenuItem delMenItem3 = new JMenuItem("�鿴�������");
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

        //��װ�˺������
        Box b2 = Box.createHorizontalBox();

        b2.add(Box.createHorizontalStrut(100));



        b2.add(Box.createHorizontalStrut(100));


        //��װ��ť
        Box b1 = Box.createHorizontalBox();

        table.setFont(new Font("����", Font.PLAIN, 18));



        //��װȫ��
        Box allBox = Box.createVerticalBox();
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b2);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b1);
        allBox.add(Box.createVerticalStrut(20));
        panel.add(allBox, new BorderLayout().CENTER);

        jf.setContentPane(panel);
        //���ô�������
        jf.setSize(1000, 700);//��С
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//����
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
                if(data[0].get(row).get(UserDaoImpl.TYEPE).equals("����Ա")){
                    delMenItem.setText("�޷�ɾ��");
                }else{
                    delMenItem.setText("ɾ��");
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
                if(data[0].get(row).get(UserDaoImpl.TYEPE).equals("����Ա")){
                    JOptionPane.showMessageDialog(new JFrame(), "�޷�ɾ��");
                }else{

                    UserDaoImpl.DeleteUser(user.getType(),data[0].get(row).get(UserDaoImpl.SID));
                    JOptionPane.showMessageDialog(new JFrame(), "ɾ���ɹ�");
                    Vector<Vector<String>>[] data = new Vector[0];//��ȡ������
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

    //����data��ˢ��display
    private void RefreshTable(Vector<Vector<String>> data){

//			����һ��TableModel���󣬲������ͷ�ͱ�����
        TableModel tableModel=new DefaultTableModel(data,columnNames);
//			��TableModel������Table���
        table.setModel(tableModel);

    }

    private void tableMouseClicked(MouseEvent evt) {
        mouseRightButtonClick(evt);
    }
    private void mouseRightButtonClick(MouseEvent evt) {
        //�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�
        if (evt.getButton() == MouseEvent.BUTTON3) {
            //ͨ�����λ���ҵ����Ϊ����е���
            row = table.rowAtPoint(evt.getPoint());
            if (row == -1) {
                return;
            }
            //�������ѡ����Ϊ��ǰ�Ҽ��������
            table.setRowSelectionInterval(row,row);
            //�����˵�
            m_popupMenu.show(table, evt.getX(), evt.getY());
        }
    }

    //���Ҵ����������
    public void adminSeekUser(User user) throws SQLException {
        JFrame jf = new JFrame("���ҹ�����");
        DefaultTableModel model;
        JScrollPane scrollPane;
        JTable table;
        Vector<String> columnNames;
        JPopupMenu  m_popupMenu;
        int row = 0 ;
        //�������
        JPanel panel = new JPanel();
        //���
        columnNames = new Vector<String>(Arrays.asList(new String[]{"�������", "�����ֻ�����", "����id", "��ҵ绰","���id",
                "����", "�鵥��", "��������","�ܼ�","����״̬","����ʱ��","��ҵ�ַ"}));//��ͷ

        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.SellerSeekOrder(user)};//��ȡ������
//		SSGBook.SortBook_State_DOWN(data);
        DoOrder.SortOrderState_UP(data[0]);
//		DoOrder.SortOrderDELIVER_UP(data);
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//��ͷ�����ݷ����

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
        table.setRowHeight(25);//�����и�
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//���ű�Ĵ�С
        scrollPane = new JScrollPane(table);//������������
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().SOUTH);//�ѱ�������

        // �����������ӵ���������



        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        //��װ�˺������
        Box b2 = Box.createHorizontalBox();

        b2.add(Box.createHorizontalStrut(100));



        b2.add(Box.createHorizontalStrut(100));


        //��װ��ť
        Box b1 = Box.createHorizontalBox();

        table.setFont(new Font("����", Font.PLAIN, 18));



        //��װȫ��
        Box allBox = Box.createVerticalBox();
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b2);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(b1);
        allBox.add(Box.createVerticalStrut(20));
        panel.add(allBox, new BorderLayout().CENTER);

        jf.setContentPane(panel);
        //���ô�������
        jf.setSize(1000, 700);//��С
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//����
        jf.setVisible(true);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.dispose();
            }
        });

    }

    //�������
    public void adminSeekUser2(User user) throws SQLException {
        JFrame jf = new JFrame("��Ҳ鿴����");
        DefaultTableModel model;
        JScrollPane scrollPane;
        JTable table;
        Vector<String> columnNames;
        JPopupMenu  m_popupMenu;
        int row;
        Object mdseName;

        //�������
        JPanel panel = new JPanel();
        //���
        columnNames = new Vector<String>(Arrays.asList(new String[]{"�������", "�����ֻ�����", "����id", "��ҵ绰","���id",
                "����", "�鵥��", "��������","�ܼ�","����״̬","����ʱ��","��ҵ�ַ","����ʱ��","�ջ����"}));//��ͷ

        final Vector<Vector<String>>[] data = new Vector[]{DoOrder.BuyerSeekOrder(user)};//��ȡ������
        DoOrder.SortOrderrece_UP(data[0]);
        model = new DefaultTableModel(data[0], columnNames);
        table = new JTable(model);//��ͷ�����ݷ����

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
        table.setRowHeight(25);//�����и�
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));//���ű�Ĵ�С
        scrollPane = new JScrollPane(table);//������������
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, new BorderLayout().NORTH);//�ѱ�������


        // �����������ӵ���������
        m_popupMenu = new JPopupMenu();

        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("����");

        m_popupMenu.add(delMenItem);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });


        //��װ�˺������
        Box b2 = Box.createHorizontalBox();


        //���������С


        table.setFont(new Font("����", Font.PLAIN, 18));



        //��װȫ��
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
        //���ô�������
        jf.setSize(1000, 700);//��С
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);//����
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

