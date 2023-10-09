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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class newOn implements ActionListener {
	JFrame jf = new JFrame("��������鼮");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	JTextField jtf1;
	//JTextField jtf2;
	Vector<String> columnNames;
	Vector<Vector<String>> data;

	// ��װ��ͼ
	public newOn(String user) throws SQLException {
		// �������
		JPanel panel = new JPanel();
		// ���
		columnNames =  new Vector<String>(Arrays.asList("���۱��", "�鼮����", "����״̬", "����",
				"�۸�","����", "��Ϳ��"));// ��ͷ
		jtf1 = new JTextField(5);
		table = new JTable(model);//��ͷ�����ݷ����
		data = SSGBook.SeekBooks_Vendor(user);// ��ȡ������
		SSGBook.SortBook_State_DOWN(data);
		RefreshTable(data);
//		table = new JTable(model) {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}// ��������༭
//		};
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);// ��ʾ���е��ж��������϶�
		table.setRowHeight(25);// �����и�
		table.setPreferredScrollableViewportSize(new Dimension(800, 500));// ���ű�Ĵ�С
		scrollPane = new JScrollPane(table);// ������������
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.SOUTH);// �ѱ�������

		// ��װ�˺������
		Box b2 = Box.createHorizontalBox();
		JLabel l1 = new JLabel("��Ʒ��ţ�");
		//JLabel l2 = new JLabel("��Ʒ���ƣ�");
		//jtf2 = new JTextField(5);
		b2.add(Box.createHorizontalStrut(300));
		b2.add(l1);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(jtf1);
		//b2.add(Box.createHorizontalStrut(40));
		//b2.add(l2);
		//b2.add(Box.createHorizontalStrut(20));
		//b2.add(jtf2);
		b2.add(Box.createHorizontalStrut(300));

		// ��װ��ť
		Box b1 = Box.createHorizontalBox();
		JButton adduser = new JButton("������Ʒ");
		JButton adduser2 = new JButton("�ϼ���Ʒ");
		JButton adduser4 = new JButton("�޸���Ʒ");
		JButton adduser3= new JButton("ɾ����Ʒ");
		//adduser.addActionListener(this);
		//adduser2.addActionListener(this);
		b1.add(adduser);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(adduser2);
		b1.add(adduser4);
		b1.add(adduser3);


		// ���������С
		adduser.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		adduser2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		adduser3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		adduser4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		l1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		//l2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
		jtf1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 15));
		//jtf2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 15));
		table.setFont(new Font("����", Font.PLAIN, 18));

		// ��װȫ��
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox, new BorderLayout().CENTER);


		jf.setContentPane(panel);
		// ���ô�������
		jf.setSize(1000, 700);// ��С
		jf.setLocationRelativeTo(null);// ����
		jf.setVisible(true);

		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jf.dispose();
			}
		});

		//������Ʒ
		adduser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoldBookFrame(user);
			}
		});
		//�������з����ϼܵ���Ʒ
		adduser2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					data = SSGBook.SeekBooks_Vendor(user);
					int count = data.size();
					for(int i = 0 ; i<count ;++i) {
						SSGBook.UpShelfBook(data.get(i).get(BOOKID));
					}
					data = SSGBook.SeekBooks_Vendor(user);// ��ȡ������
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		//ɾ����Ʒ����ɣ�
		adduser3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String BookId = jtf1.getText();
				Vector<Vector<String>> book = null;
				try {
					book = SSGBook.SeekBooks_BookID(BookId);
					if(!book.isEmpty()){
						if(SSGBook.DeleteBook(BookId)){
							JOptionPane.showMessageDialog(jf, "ɾ���ɹ�");
							data = SSGBook.SeekBooks_Vendor(user);// ��ȡ������
							SSGBook.SortBook_State_DOWN(data);
							RefreshTable(data);
						}
					}else{
						JOptionPane.showMessageDialog(jf, "�Ҳ�����Ҫɾ�����鼮��������ȷ�ϱ��");
					}
				} catch (SQLException throwables) {
					JOptionPane.showMessageDialog(jf, "ɾ��ʧ��");
				}
			}
		});
		//�޸���Ʒ(��ɣ�
		adduser4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String BookId = jtf1.getText();
				Vector<Vector<String>> book = null;
				try {
					book = SSGBook.SeekBooks_BookID(BookId);
					if(!book.isEmpty()){
						AlterBookFrame(book.get(0),user);
					}else{
						JOptionPane.showMessageDialog(jf, "�Ҳ�����Ҫ�޸ĵ��鼮��������ȷ�ϱ��");
					}

				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			});



	}
	//����data��ˢ��display�鼮
	private void RefreshTable(Vector<Vector<String>> data){

//			����һ��TableModel���󣬲������ͷ�ͱ�����
		TableModel tableModel=new DefaultTableModel(data,columnNames);
//			��TableModel������Table���
		table.setModel(tableModel);
		ClearTextFile();
	}
	//����ı���
	private void ClearTextFile(){
		jtf1.setText("");
	}

	public static void main(String[] args) throws SQLException {

		newOn n1 = new newOn("wyjyh");

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		}
	//��������
	private void  AlterBookFrame(Vector<String> thisBook,String user) {


		JFrame alterBookFrame = new JFrame();
		String time = GetNewTime.GetTime();
		alterBookFrame.setTitle("�����������");
		JPanel panel = new JPanel();

		// ����box
		Box box = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
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
		JLabel l4 = new JLabel("�ϼܴ���:");
		JLabel l5 = new JLabel("��ʹ���:");
		JLabel l7 = new JLabel("�鼮״̬:");

		// �����ı���
		JLabel jtf1 = new JLabel(thisBook.get(BOOKID));
		JTextField jtf2 = new JTextField(thisBook.get(BOOKNAME));
		JTextField jtf3 = new JTextField(thisBook.get(BOOKPRICE));
		JTextField jtf4 = new JTextField(thisBook.get(NOWREPERTORY));
		JTextField jtf5 = new JTextField(thisBook.get(MINREPERTORY));
		JLabel jtf6 = new JLabel(thisBook.get(BOOKSTATE));

		// ���ð�ť
		JButton jb1 = new JButton("�޸�");
		{
			b1.add(l1);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(jtf1);
			b7.add(l7);
			b7.add(Box.createHorizontalStrut(20));
			b7.add(jtf6);
			b2.add(l2);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(jtf2);
			b3.add(l3);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jtf3);
			b4.add(l4);
			b4.add(Box.createHorizontalStrut(20));
			b4.add(jtf4);
			b5.add(Box.createHorizontalStrut(20));
			b5.add(l5);
			b6.add(Box.createHorizontalStrut(20));
			b5.add(jtf5);
			b8.add(Box.createHorizontalStrut(20));
			b9.add(jb1);
		}
		{
			box.add(Box.createVerticalStrut(30));
			box.add(b1);
			box.add(Box.createVerticalStrut(10));
			box.add(b7);
			box.add(Box.createVerticalStrut(10));
			box.add(b2);
			box.add(Box.createVerticalStrut(10));
			box.add(b3);
			box.add(Box.createVerticalStrut(10));
			box.add(b8);
			box.add(b6);
			box.add(b4);
			box.add(Box.createVerticalStrut(10));

			box.add(b5);
			box.add(Box.createVerticalStrut(10));

			box.add(b9);
		}
		// ��������
		{
			l1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l7.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf6.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		alterBookFrame.setContentPane(panel);
		alterBookFrame.setSize(577, 377);// ��С
		alterBookFrame.setLocationRelativeTo(null);// ����
		alterBookFrame.setVisible(true);
		alterBookFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				alterBookFrame.dispose();
			}
		});

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String Id = jtf1.getText();
				String Name = jtf2.getText();
				String Price = jtf3.getText();
				String NowRepretory = jtf4.getText();
				String MinRepretory = jtf5.getText();
				if(SSGBook.ChangeBook(Id,Name,Price,NowRepretory,MinRepretory)==true){
					alterBookFrame.dispose();
					JOptionPane.showMessageDialog(new JFrame(), "�޸ĳɹ�");

				}
				try {
					data = SSGBook.SeekBooks_Vendor(user);// ��ȡ������
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}


			}
		});

	}

	//���۴���
	private void SoldBookFrame(String user){

		JFrame soldBookFrame = new JFrame();
		String time = GetNewTime.GetTime();
		soldBookFrame.setTitle("���۽���");
		JPanel panel = new JPanel();

		// ����box
		Box box = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		// ���ñ�ǩ
		//JLabel l1 = new JLabel("��Ʒ���:");
		JLabel l2 = new JLabel("��Ʒ����:");
		JLabel l3 = new JLabel("��Ʒ����:");
		JLabel l4 = new JLabel("�ϼܴ���:");
		JLabel l5 = new JLabel("��ʹ���:");
		//JLabel l7 = new JLabel("�鼮״̬:");

		// �����ı���
		//JTextField jtf1 = new JTextField(20);
		JTextField jtf2 = new JTextField(20);
		JTextField jtf3 = new JTextField(20);
		JTextField jtf4 = new JTextField(20);
		JTextField jtf5 = new JTextField(20);
		//JTextField jtf6 = new JTextField(20);

		// ���ð�ť
		JButton jb1 = new JButton("���");
		{
			//b1.add(l1);
			//b1.add(Box.createHorizontalStrut(20));
			//b1.add(jtf1);
			//b7.add(l7);
			b7.add(Box.createHorizontalStrut(20));
			//b7.add(jtf6);
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
			//b6.add(Box.createHorizontalStrut(20));
			b5.add(jtf5);
			b8.add(Box.createHorizontalStrut(20));
			b9.add(jb1);
		}
		{
			box.add(Box.createVerticalStrut(30));
			box.add(b1);
			box.add(Box.createVerticalStrut(10));
			box.add(b7);
			box.add(Box.createVerticalStrut(10));
			box.add(b2);
			box.add(Box.createVerticalStrut(10));
			box.add(b3);
			box.add(Box.createVerticalStrut(10));
			box.add(b8);
			box.add(b6);
			box.add(b4);
			box.add(Box.createVerticalStrut(10));

			box.add(b5);
			box.add(Box.createVerticalStrut(10));

			box.add(b9);
		}
		// ��������
		{
			//l1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			//l7.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			//jtf1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			//jtf6.setFont(new Font("����", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("����", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		soldBookFrame.setContentPane(panel);
		soldBookFrame.setSize(577, 377);// ��С
		soldBookFrame.setLocationRelativeTo(null);// ����
		soldBookFrame.setVisible(true);
		soldBookFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				soldBookFrame.dispose();
			}
		});

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String Name = jtf2.getText();
				String Price = jtf3.getText();
				String NowRepretory = jtf4.getText();
				String MinRepretory = jtf5.getText();

				SSGBook.SetBook(user,Name,NowRepretory,MinRepretory,Price);

				try {
					data = SSGBook.SeekBooks_Vendor(user);// ��ȡ������
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				soldBookFrame.dispose();
				JOptionPane.showMessageDialog(jf, "��ӳɹ�");



			}
		});

	}

	private final static int BOOKID = 0;
	private final static int BOOKNAME = 1;
	private final static int BOOKSTATE = 2;
	private final static int NOWREPERTORY = 3;
	private final static int BOOKPRICE = 4;
	private final static int VENDOR = 5;
	private final static int MINREPERTORY = 6;
	}

