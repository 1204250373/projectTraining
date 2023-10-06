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
	JFrame jf = new JFrame("管理出售书籍");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	JTextField jtf1;
	//JTextField jtf2;
	Vector<String> columnNames;
	Vector<Vector<String>> data;

	// 组装视图
	public newOn(String user) throws SQLException {
		// 创建面板
		JPanel panel = new JPanel();
		// 表格
		columnNames =  new Vector<String>(Arrays.asList("出售编号", "书籍名称", "出售状态", "存量",
				"价格","卖家", "最低库存"));// 表头
		jtf1 = new JTextField(5);
		table = new JTable(model);//表头和内容放入表
		data = SSGBook.SeekBooks_Vendor(user);// 获取表内容
		SSGBook.SortBook_State_DOWN(data);
		RefreshTable(data);
//		table = new JTable(model) {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}// 表格不允许被编辑
//		};
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);// 表示所有的列都不可以拖动
		table.setRowHeight(25);// 设置行高
		table.setPreferredScrollableViewportSize(new Dimension(800, 500));// 整张表的大小
		scrollPane = new JScrollPane(table);// 给表整个滑轮
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.SOUTH);// 把表放入面板

		// 组装账号密码框
		Box b2 = Box.createHorizontalBox();
		JLabel l1 = new JLabel("商品编号：");
		//JLabel l2 = new JLabel("商品名称：");
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

		// 组装按钮
		Box b1 = Box.createHorizontalBox();
		JButton adduser = new JButton("出售商品");
		JButton adduser2 = new JButton("上架商品");
		JButton adduser4 = new JButton("修改商品");
		JButton adduser3= new JButton("删除商品");
		//adduser.addActionListener(this);
		//adduser2.addActionListener(this);
		b1.add(adduser);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(adduser2);
		b1.add(adduser4);
		b1.add(adduser3);


		// 设置字体大小
		adduser.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		adduser2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		adduser3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		adduser4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		l1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		//l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		jtf1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 15));
		//jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 15));
		table.setFont(new Font("宋体", Font.PLAIN, 18));

		// 组装全部
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox, new BorderLayout().CENTER);


		jf.setContentPane(panel);
		// 设置窗口属性
		jf.setSize(1000, 700);// 大小
		jf.setLocationRelativeTo(null);// 居中
		jf.setVisible(true);

		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jf.dispose();
			}
		});

		//出售商品
		adduser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoldBookFrame(user);
			}
		});
		//重新所有符合上架的商品
		adduser2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					data = SSGBook.SeekBooks_Vendor(user);
					int count = data.size();
					for(int i = 0 ; i<count ;++i) {
						SSGBook.UpShelfBook(data.get(i).get(BOOKID));
					}
					data = SSGBook.SeekBooks_Vendor(user);// 获取表内容
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		//删除商品（完成）
		adduser3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String BookId = jtf1.getText();
				Vector<Vector<String>> book = null;
				try {
					book = SSGBook.SeekBooks_BookID(BookId);
					if(!book.isEmpty()){
						if(SSGBook.DeleteBook(BookId)){
							JOptionPane.showMessageDialog(jf, "删除成功");
							data = SSGBook.SeekBooks_Vendor(user);// 获取表内容
							SSGBook.SortBook_State_DOWN(data);
							RefreshTable(data);
						}
					}else{
						JOptionPane.showMessageDialog(jf, "找不到所要删除的书籍，请重新确认编号");
					}
				} catch (SQLException throwables) {
					JOptionPane.showMessageDialog(jf, "删除失败");
				}
			}
		});
		//修改商品(完成）
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
						JOptionPane.showMessageDialog(jf, "找不到所要修改的书籍，请重新确认编号");
					}

				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			});



	}
	//接受data，刷新display书籍
	private void RefreshTable(Vector<Vector<String>> data){

//			创建一个TableModel对象，并传入表头和表内容
		TableModel tableModel=new DefaultTableModel(data,columnNames);
//			将TableModel对象传入Table表格
		table.setModel(tableModel);
		ClearTextFile();
	}
	//清空文本框
	private void ClearTextFile(){
		jtf1.setText("");
	}

	public static void main(String[] args) throws SQLException {

		newOn n1 = new newOn("wyjyh");

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		}
	//订单窗口
	private void  AlterBookFrame(Vector<String> thisBook,String user) {


		JFrame alterBookFrame = new JFrame();
		String time = GetNewTime.GetTime();
		alterBookFrame.setTitle("订单处理界面");
		JPanel panel = new JPanel();

		// 设置box
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
		// 设置标签
		JLabel l1 = new JLabel("商品编号:");
		JLabel l2 = new JLabel("商品名称:");
		JLabel l3 = new JLabel("商品单价:");
		JLabel l4 = new JLabel("上架存量:");
		JLabel l5 = new JLabel("最低存量:");
		JLabel l7 = new JLabel("书籍状态:");

		// 设置文本框
		JLabel jtf1 = new JLabel(thisBook.get(BOOKID));
		JTextField jtf2 = new JTextField(thisBook.get(BOOKNAME));
		JTextField jtf3 = new JTextField(thisBook.get(BOOKPRICE));
		JTextField jtf4 = new JTextField(thisBook.get(NOWREPERTORY));
		JTextField jtf5 = new JTextField(thisBook.get(MINREPERTORY));
		JLabel jtf6 = new JLabel(thisBook.get(BOOKSTATE));

		// 设置按钮
		JButton jb1 = new JButton("修改");
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
		// 设置字体
		{
			l1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l7.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf6.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		alterBookFrame.setContentPane(panel);
		alterBookFrame.setSize(577, 377);// 大小
		alterBookFrame.setLocationRelativeTo(null);// 居中
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
					JOptionPane.showMessageDialog(new JFrame(), "修改成功");

				}
				try {
					data = SSGBook.SeekBooks_Vendor(user);// 获取表内容
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}


			}
		});

	}

	//出售窗口
	private void SoldBookFrame(String user){

		JFrame soldBookFrame = new JFrame();
		String time = GetNewTime.GetTime();
		soldBookFrame.setTitle("出售界面");
		JPanel panel = new JPanel();

		// 设置box
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
		// 设置标签
		//JLabel l1 = new JLabel("商品编号:");
		JLabel l2 = new JLabel("商品名称:");
		JLabel l3 = new JLabel("商品单价:");
		JLabel l4 = new JLabel("上架存量:");
		JLabel l5 = new JLabel("最低存量:");
		//JLabel l7 = new JLabel("书籍状态:");

		// 设置文本框
		//JTextField jtf1 = new JTextField(20);
		JTextField jtf2 = new JTextField(20);
		JTextField jtf3 = new JTextField(20);
		JTextField jtf4 = new JTextField(20);
		JTextField jtf5 = new JTextField(20);
		//JTextField jtf6 = new JTextField(20);

		// 设置按钮
		JButton jb1 = new JButton("添加");
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
		// 设置字体
		{
			//l1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			//l7.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			//jtf1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			//jtf6.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		soldBookFrame.setContentPane(panel);
		soldBookFrame.setSize(577, 377);// 大小
		soldBookFrame.setLocationRelativeTo(null);// 居中
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
					data = SSGBook.SeekBooks_Vendor(user);// 获取表内容
					SSGBook.SortBook_State_DOWN(data);
					RefreshTable(data);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				soldBookFrame.dispose();
				JOptionPane.showMessageDialog(jf, "添加成功");



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

