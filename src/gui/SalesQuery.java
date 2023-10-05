package gui;

import dao.impl.GetNewTime;
import dao.impl.SSGBook;
import dbutils.DBHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;


public class SalesQuery implements ActionListener{
	JFrame jf = new JFrame("�鿴�鼮����");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	Vector<String> columnNames;


	//��װ��ͼ
	public SalesQuery() throws SQLException {
		//�������
		JPanel panel = new JPanel();
		//���
		columnNames = new Vector<String>(Arrays.asList(new String[]{"�鼮���۱��", "�鼮����", "�鼮״̬", "�鼮����", "���۵���(��)", "����"}));//��ͷ
		//data = new Demo01().table();//��ȡ������
		Vector<Vector<String>> data = SSGBook.GetBookAll();//��ȡ������
		SSGBook.SortBook_State_DOWN(data);
		model = new DefaultTableModel(data,columnNames);
		table = new JTable(model);//��ͷ�����ݷ����
//		table= new JTable(model) {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}// ��������༭
//		};
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
		table.setRowHeight(25);//�����и�
		table.setPreferredScrollableViewportSize(new Dimension(800,500));//���ű�Ĵ�С
	    scrollPane = new JScrollPane(table);//������������
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane,new BorderLayout().SOUTH);//�ѱ�������
		
		//��װ�˺������
		Box b2 = Box.createHorizontalBox();
		JLabel l1 = new JLabel("���ң�");
		JLabel l2 = new JLabel("��Ʒ���ƣ�");
		JLabel l3 = new JLabel("������ţ�");

		jtf1 = new JTextField(5);
		jtf2 = new JTextField(5);
		jtf3 = new JTextField(5);
		b2.add(Box.createHorizontalStrut(100));
		b2.add(l3);
		b2.add(jtf3);
		b2.add(l1);
		//b2.add(Box.createHorizontalStrut(20));
		b2.add(jtf1);
		//b2.add(Box.createHorizontalStrut(40));
		b2.add(l2);
		//b2.add(Box.createHorizontalStrut(20));
		b2.add(jtf2);


		b2.add(Box.createHorizontalStrut(100));

		
		//��װ��ť
		Box b1 = Box.createHorizontalBox();
		JButton adduser = new JButton("�鿴��Ʒ���");
		JButton GetBookSAll = new JButton("ȫ���鼮");
		JButton BuyBook = new JButton("����");

		b1.add(GetBookSAll);
		b1.add(adduser);
		b1.add(BuyBook);
		
		//���������С
		adduser.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		BuyBook.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		GetBookSAll.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		l1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		l2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		l3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		table.setFont(new Font("����",Font.PLAIN,18));
		jtf1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 15));
		jtf2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 15));
		
		
		//��װȫ��
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox,new BorderLayout().CENTER);

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
		//����Ϊȫ���鼮
		GetBookSAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Vector<String>> data = SSGBook.GetBookAll();
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		//�����鼮
		adduser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = jtf1.getText();
				String s2 = jtf2.getText();
				String s3 = jtf3.getText();

				if(!s3.equals("")){
					try {
					Vector<Vector<String>> data = SSGBook.SeekBooks_BookID(s3);
						RefreshTable(data);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
				else if(s1.equals("")&&s2.equals("")){
					JOptionPane.showMessageDialog(jf, "����д�����Ϣ");
				}
				//���Ҳ���
				else if(s1.equals("")&&!s2.equals("")){
						try {
							Vector<Vector<String>> data = SSGBook.SeekBooks_BookName(s2);
							RefreshTable(data);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

					}
				//��������
				else if(s2.equals("")&&!s1.equals("")){
						try {
							Vector<Vector<String>> data = SSGBook.SeekBooks_Vendor(s1);
							RefreshTable(data);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

					}//���Һ���������
				else if(!s2.equals("")&&!s1.equals("")){
						Vector<Vector<String>> data = null;
						try {
							data = SSGBook.SeekBooks_VendorAndBookName(s1,s2);
							RefreshTable(data);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

					}
				}

		});

		//�����鼮
		BuyBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = jtf1.getText();
				String s2 = jtf2.getText();
				String s3 = jtf3.getText();
				Vector<Vector<String>> data = null;


				if(!s3.equals("")){
					try {
						data = SSGBook.SeekBooks_BookID(s3);
						if(Integer.parseInt(data.get(0).get(NOWREPERTORY))<=0){
							JOptionPane.showMessageDialog(jf, "�޷����򣬸��鼮���¼�");
						}else {
							SaleBookFrame(data.get(0));
						}
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}else if(!s1.equals("")&&!s2.equals("")){
					try {
						data = SSGBook.SeekBooks_VendorAndBookName(s1,s2);
						if(Integer.parseInt(data.get(0).get(NOWREPERTORY))<=0){
							JOptionPane.showMessageDialog(jf, "�޷����򣬸��鼮���¼�");
						}else{
							SaleBookFrame(data.get(0));
						}

					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}else{
					JOptionPane.showMessageDialog(jf, "����д�����Ϣ");
				}

			}
		});
	}
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		SalesQuery s1 = new SalesQuery();

	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private void RefreshTable(Vector<Vector<String>> data){

//			����һ��TableModel���󣬲������ͷ�ͱ�����
		TableModel tableModel=new DefaultTableModel(data,columnNames);
//			��TableModel������Table���
		table.setModel(tableModel);
		ClearTextFile();
	}
	private void ClearTextFile(){
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
	}

	private void  SaleBookFrame(Vector<String> thisBook) {


		JFrame saleframe = new JFrame();
		String time =GetNewTime.GetTime();
		saleframe.setTitle("�����������");
		String type[] = {"΢��", "֧����", "����"};
		JComboBox jcb = new JComboBox(type);
		JPanel panel = new JPanel();

		// ����box
		Box box = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		//b1.setLayout(new FlowLayout());
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		// ���ñ�ǩ
		JLabel l1 = new JLabel("��Ʒ���:");
		JLabel l2 = new JLabel("��Ʒ����:");
		JLabel l3 = new JLabel("��Ʒ����:");
		JLabel l4 = new JLabel("��������:");
		JLabel l5 = new JLabel("ʵ�����:");
		JLabel l6 = new JLabel("֧������:");
		JLabel l7 = new JLabel("�鼮״̬:");
		JLabel l8 = new JLabel("����ʱ��:");

		// �����ı���
		JLabel jtf1 = new JLabel(thisBook.get(BOOKID));
		JLabel jtf2 = new JLabel(thisBook.get(BOOKNAME));
		JLabel jtf3 = new JLabel(thisBook.get(BOOKPRICE));
		String bugnum[] = {"1","2","3","4","5"};
		JComboBox jtf4 = new JComboBox(bugnum);
		JLabel jtf5 = new JLabel(thisBook.get(BOOKPRICE));
		JLabel jtf6 = new JLabel(thisBook.get(BOOKSTATE));
		JLabel jtf7 = new JLabel(time);
		// ���ð�ť
		JButton jb1 = new JButton("֧��");
		//JButton jb2 = new JButton("�鿴�������");
		{
			b1.add(l1);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(jtf1);
			b2.add(l2);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(jtf2);
			b3.add(l3);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jtf3);
			b4.add(l4);
			b4.add(Box.createHorizontalStrut(20));
			b4.add(jtf4);
			b5.add(l5);
			b5.add(Box.createHorizontalStrut(20));
			b5.add(jtf5);
			b6.add(l6);
			b6.add(Box.createHorizontalStrut(20));
			b6.add(jcb);
			b7.add(l7);
			b7.add(Box.createHorizontalStrut(20));
			b7.add(jtf6);
			b8.add(l8);
			b8.add(Box.createHorizontalStrut(20));
			b8.add(jtf7);
			b9.add(jb1);
			//b9.add(Box.createHorizontalStrut(130));
			//b9.add(jb2);
		}
		{
			box.add(Box.createVerticalStrut(30));
			box.add(b1);
			box.add(b2);
			box.add(b3);

			box.add(b7);
			box.add(b8);
			box.add(Box.createVerticalStrut(10));

			box.add(b6);
			box.add(Box.createVerticalStrut(10));

			box.add(b4);

			box.add(b5);
			box.add(Box.createVerticalStrut(10));

			box.add(b9);
			//box.add(Box.createVerticalStrut(30));
		}
		// ��������
		{
			l1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l6.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l7.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l8.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf6.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf7.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jcb.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 30));
			//jb2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		saleframe.setContentPane(panel);
		saleframe.setSize(577, 377);// ��С
		saleframe.setLocationRelativeTo(null);// ����
		saleframe.setVisible(true);
		saleframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				saleframe.dispose();
			}
		});
		jtf6.setText("�µ�");
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<String>> data = null;
				try {
					Integer i1 =Integer.parseInt((String) jtf4.getSelectedItem());
					if(SSGBook.SoldBook(thisBook.get(BOOKID),i1)){
						//������¼
						SSGBook.LogSoldBook();
						//ˢ��ԭҳ����
						data = SSGBook.GetBookAll();
						RefreshTable(data);
						//�رն���ҳ��
						saleframe.dispose();
						JOptionPane.showMessageDialog(jf, "����ɹ�");
					}



				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});
		jtf4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Float f1 =Float.parseFloat((String) jtf4.getSelectedItem());
				Float f2 =Float.parseFloat(thisBook.get(BOOKPRICE));
				jtf5.setText(Float.toString(f1*f2));
			}
		});
	}

	private final static int BOOKID = 0;
	private final static int BOOKNAME = 1;
	private final static int BOOKSTATE = 2;
	private final static int NOWREPERTORY = 3;
	private final static int BOOKPRICE = 4;
	private final static int VENDOR = 5;

}


