package gui;


import dao.impl.GetNewTime;
import dao.impl.SSGBook;
import dao.impl.ScreenUtils;
import dbutils.DBHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class yonghuFrame {
	JFrame jf = new JFrame("用户主界面");
	
	final int WIDTH = 1300;
	final int HEIGTH = 700;
	
	//组装视图
	public yonghuFrame(){
		//给窗口设置属性
	    jf.setResizable(false);
	    //把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
	    //ImageIcon bg = new ImageIcon("src/com/hua/6.jpg");
		//JLabel label = new JLabel(bg);
		//label.setSize(bg.getIconWidth(),bg.getIconHeight());
		//jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		
		//把窗口面板设为内容面板并设为透明、流动布局。
		JPanel pan = (JPanel)jf.getContentPane();
		pan.setOpaque(false);//透明
		pan.setLayout(new FlowLayout());
		
		
		JLabel l1 = new JLabel("二手书交易平台",JLabel.CENTER);
        Font font1 = new Font("宋体",Font.PLAIN,100);
		
		l1.setFont(font1);
		l1.setForeground(Color.black);
		pan.setLayout(new BorderLayout());
		pan.add(l1,new BorderLayout().CENTER);
		//设置菜单栏
		JMenuBar jmb = new JMenuBar();
		JMenu jm1 = new JMenu("生产系统");
		JMenuItem jmi1 = new JMenuItem("生产商品");
		JMenuItem jmi2 = new JMenuItem("查看商品生产信息");
		
		
		
		JMenu jm2 = new JMenu("出售管理");
		JMenuItem jmi3 = new JMenuItem("出售商品");
		JMenuItem jmi4 = new JMenuItem("管理出售商品");
		//JMenuItem jmi5 = new JMenuItem("查看商品库存信息");
		//JMenuItem jmi9 = new JMenuItem("添加新商品");
		
		JMenu jm5 = new JMenu("销售系统");
		JMenuItem jmi6 = new JMenuItem("销售书籍");
		
		JMenu jm6 = new JMenu("订单系统");
		JMenuItem jmi7 = new JMenuItem("查看商品订单信息");
		JMenuItem jmi8 = new JMenuItem("订单处理");
		
		JMenu jm3 = new JMenu("修改密码");		
		JMenu jm4 = new JMenu("切换账号");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jmb.add(jm1);
		
		jm2.add(jmi3);
		jm2.add(jmi4);
		//jm2.add(jmi5);
		//jm2.add(jmi9);
		jmb.add(jm2);
		
		jm5.add(jmi6);
		jmb.add(jm5);
		
		jm6.add(jmi7);
		jm6.add(jmi8);
		jmb.add(jm6);
		jmb.add(jm3);
		jmb.add(jm4);
		
		//修改菜单栏字体
		Font font2 = new Font("宋体",Font.ITALIC,25);
		jm1.setFont(font2);
		jm2.setFont(font2);
		jm3.setFont(font2);
		jm4.setFont(font2);
		jm5.setFont(font2);
		jm6.setFont(font2);
		jmi1.setFont(font2);
		jmi2.setFont(font2);
		jmi3.setFont(font2);
		jmi4.setFont(font2);
	//	jmi5.setFont(font2);
		jmi6.setFont(font2);
		jmi7.setFont(font2);
		jmi8.setFont(font2);
	//	jmi9.setFont(font2);
		

		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,/*bg.getIconWidth(),bg.getIconHeight()*/1300,777);
		jf.setLocationRelativeTo(null);
		jf.setJMenuBar(jmb);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		jm4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new dengluFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jf.dispose();
			}
		});
		jm3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//new changePassword();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jmi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new produceGoods();

			}
		});
		jmi2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		new produceQuery();

			}
		});
		jmi7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		new orderQuery();
			}
		});
		jmi4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new newOn("wyjyh");
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});
		jmi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SoldBookFrame("wyjyh");

			}
		});

		jmi6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SalesQuery();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});
		jmi8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		new OrderProcess();

			}
		});

	}
	
	public static void main(String[] args) {
//		try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        }catch(Exception e) {
//         System.out.println(e);
//        }
		yonghuFrame y1 = new yonghuFrame();

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
		JLabel l2 = new JLabel("商品名称:");
		JLabel l3 = new JLabel("商品单价:");
		JLabel l4 = new JLabel("上架存量:");
		JLabel l5 = new JLabel("最低存量:");

		// 设置文本框
		JTextField jtf2 = new JTextField(20);
		JTextField jtf3 = new JTextField(20);
		JTextField jtf4 = new JTextField(20);
		JTextField jtf5 = new JTextField(20);

		// 设置按钮
		JButton jb1 = new JButton("添加");
		{

			b7.add(Box.createHorizontalStrut(20));
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
			l2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			l5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf2.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf3.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf4.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
			jtf5.setFont(new Font("仿宋", Font.BOLD + Font.ITALIC, 20));
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
				soldBookFrame.dispose();
				JOptionPane.showMessageDialog(jf, "添加成功");



			}
		});

	}
}
