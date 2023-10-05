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
	JFrame jf = new JFrame("查看书籍界面");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	Vector<String> columnNames;


	//组装视图
	public SalesQuery() throws SQLException {
		//创建面板
		JPanel panel = new JPanel();
		//表格
		columnNames = new Vector<String>(Arrays.asList(new String[]{"书籍销售编号", "书籍名称", "书籍状态", "书籍存量", "销售单价(￥)", "卖家"}));//表头
		//data = new Demo01().table();//获取表内容
		Vector<Vector<String>> data = SSGBook.GetBookAll();//获取表内容
		SSGBook.SortBook_State_DOWN(data);
		model = new DefaultTableModel(data,columnNames);
		table = new JTable(model);//表头和内容放入表
//		table= new JTable(model) {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}// 表格不允许被编辑
//		};
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);//表示所有的列都不可以拖动
		table.setRowHeight(25);//设置行高
		table.setPreferredScrollableViewportSize(new Dimension(800,500));//整张表的大小
	    scrollPane = new JScrollPane(table);//给表整个滑轮
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane,new BorderLayout().SOUTH);//把表放入面板
		
		//组装账号密码框
		Box b2 = Box.createHorizontalBox();
		JLabel l1 = new JLabel("卖家：");
		JLabel l2 = new JLabel("商品名称：");
		JLabel l3 = new JLabel("售卖序号：");

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

		
		//组装按钮
		Box b1 = Box.createHorizontalBox();
		JButton adduser = new JButton("查看商品情况");
		JButton GetBookSAll = new JButton("全部书籍");
		JButton BuyBook = new JButton("购买");

		b1.add(GetBookSAll);
		b1.add(adduser);
		b1.add(BuyBook);
		
		//设置字体大小
		adduser.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		BuyBook.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		GetBookSAll.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		l1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		l3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
		table.setFont(new Font("宋体",Font.PLAIN,18));
		jtf1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 15));
		jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 15));
		
		
		//组装全部
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox,new BorderLayout().CENTER);

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
		//更新为全部书籍
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
		//查找书籍
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
					JOptionPane.showMessageDialog(jf, "请填写相关信息");
				}
				//卖家查找
				else if(s1.equals("")&&!s2.equals("")){
						try {
							Vector<Vector<String>> data = SSGBook.SeekBooks_BookName(s2);
							RefreshTable(data);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

					}
				//书名查找
				else if(s2.equals("")&&!s1.equals("")){
						try {
							Vector<Vector<String>> data = SSGBook.SeekBooks_Vendor(s1);
							RefreshTable(data);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

					}//卖家和书名查找
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

		//购买书籍
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
							JOptionPane.showMessageDialog(jf, "无法购买，该书籍已下架");
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
							JOptionPane.showMessageDialog(jf, "无法购买，该书籍已下架");
						}else{
							SaleBookFrame(data.get(0));
						}

					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}else{
					JOptionPane.showMessageDialog(jf, "请填写相关信息");
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

//			创建一个TableModel对象，并传入表头和表内容
		TableModel tableModel=new DefaultTableModel(data,columnNames);
//			将TableModel对象传入Table表格
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
		saleframe.setTitle("订单处理界面");
		String type[] = {"微信", "支付宝", "银行"};
		JComboBox jcb = new JComboBox(type);
		JPanel panel = new JPanel();

		// 设置box
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
		// 设置标签
		JLabel l1 = new JLabel("商品编号:");
		JLabel l2 = new JLabel("商品名称:");
		JLabel l3 = new JLabel("商品单价:");
		JLabel l4 = new JLabel("订单数量:");
		JLabel l5 = new JLabel("实付金额:");
		JLabel l6 = new JLabel("支付渠道:");
		JLabel l7 = new JLabel("书籍状态:");
		JLabel l8 = new JLabel("订单时间:");

		// 设置文本框
		JLabel jtf1 = new JLabel(thisBook.get(BOOKID));
		JLabel jtf2 = new JLabel(thisBook.get(BOOKNAME));
		JLabel jtf3 = new JLabel(thisBook.get(BOOKPRICE));
		String bugnum[] = {"1","2","3","4","5"};
		JComboBox jtf4 = new JComboBox(bugnum);
		JLabel jtf5 = new JLabel(thisBook.get(BOOKPRICE));
		JLabel jtf6 = new JLabel(thisBook.get(BOOKSTATE));
		JLabel jtf7 = new JLabel(time);
		// 设置按钮
		JButton jb1 = new JButton("支付");
		//JButton jb2 = new JButton("查看订单情况");
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
		// 设置字体
		{
			l1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l6.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l7.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l8.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf6.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf7.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jcb.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jb1.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 30));
			//jb2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 30));
		}
		panel.add(box);


		saleframe.setContentPane(panel);
		saleframe.setSize(577, 377);// 大小
		saleframe.setLocationRelativeTo(null);// 居中
		saleframe.setVisible(true);
		saleframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				saleframe.dispose();
			}
		});
		jtf6.setText("下单");
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<String>> data = null;
				try {
					Integer i1 =Integer.parseInt((String) jtf4.getSelectedItem());
					if(SSGBook.SoldBook(thisBook.get(BOOKID),i1)){
						//创建记录
						SSGBook.LogSoldBook();
						//刷新原页数据
						data = SSGBook.GetBookAll();
						RefreshTable(data);
						//关闭订单页面
						saleframe.dispose();
						JOptionPane.showMessageDialog(jf, "购买成功");
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


