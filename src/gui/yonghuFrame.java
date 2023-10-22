package gui;


import beans.User;
import dao.impl.ScreenUtils;
import dao.impl.UserDaoImpl;
import dbutils.DBHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;


public class yonghuFrame {
	JFrame jf = new JFrame("用户主界面");
	
	final int WIDTH = 1300;
	final int HEIGTH = 700;


//	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//
//		User user = UserDaoImpl.findUserbyID("1234567891");
//		yonghuFrame y1 = new yonghuFrame(user);
//
//	}

	//组装视图
	public yonghuFrame(User user){
		//给窗口设置属性
	    jf.setResizable(false);
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

		
		
		JMenu jm2 = new JMenu("出售管理");
		JMenuItem jmi3 = new JMenuItem("处理订单");
		JMenuItem jmi4 = new JMenuItem("管理出售商品");
		JMenu jm5 = new JMenu("销售系统");
		JMenuItem jmi5 = new JMenuItem("处理购买订单");
		JMenuItem jmi6 = new JMenuItem("销售书籍");
		JMenu jm6 = new JMenu("管理员界面");
		JMenuItem jmi7 = new JMenuItem("切换");
		JMenu jm3 = new JMenu("修改密码");		
		JMenu jm4 = new JMenu("切换账号");
		jm2.add(jmi3);
		jm2.add(jmi4);
		jmb.add(jm2);
		
		jm5.add(jmi6);
		jm5.add(jmi5);
		jmb.add(jm5);
		
		jm6.add(jmi7);
		jmb.add(jm3);
		jmb.add(jm4);

		if(user.getType().equals("管理员")){
			jmb.add(jm6);
		}

		//修改菜单栏字体
		Font font2 = new Font("宋体",Font.ITALIC,25);

		jm2.setFont(font2);
		jm3.setFont(font2);
		jm4.setFont(font2);
		jm5.setFont(font2);
		jm6.setFont(font2);
		jmi3.setFont(font2);
		jmi4.setFont(font2);
		jmi5.setFont(font2);
		jmi6.setFont(font2);
		jmi7.setFont(font2);

		

		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,/*bg.getIconWidth(),bg.getIconHeight()*/1300,777);
		jf.setLocationRelativeTo(null);
		jf.setJMenuBar(jmb);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		jm4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new LoginFrame();
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
					changePassword(user);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jmi7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				new adminFrame(user);
			}
		});
		jmi4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(user.getPhone()!=null){
					try {
						new newOn(user.getSid());
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}else{
//					System.out.println("123");
					//通过认证，给账号添加phone才能出售商品

					try {
						JOptionPane.showMessageDialog(jf, "需通过认证");
						renzheng(user);
					} catch (Exception exception) {
						exception.printStackTrace();
					}


				}


			}
		});
		//订单管理
		jmi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(user.getPhone()!=null){
					try {
						new DoGoodsFrame(user);

					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}else{
//					System.out.println("123");
					//通过认证，给账号添加phone才能出售商品

					try {
						JOptionPane.showMessageDialog(jf, "需通过认证");
						renzheng(user);
					} catch (Exception exception) {
						exception.printStackTrace();
					}


				}
			}
		});
		//买家订单记录
		jmi5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new BuyerSeeGoodsFrame(user);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});

		jmi6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SalesQuery(user);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});


	}


	// 组装视图
	public void changePassword(User user) throws Exception {
		JFrame jf = new JFrame("修改密码界面");

		final int WIDTH = 600;
		final int HEIGTH = 327;

		JPanel pan = (JPanel) jf.getContentPane();
		pan.setOpaque(false);// 透明
		pan.setLayout(new FlowLayout());

		Font font = new Font("宋体", Font.PLAIN, 40);


		// 组装账号文本框
		Box b1 = Box.createHorizontalBox();
		JLabel label2 = new JLabel("原 密 码：");
		label2.setFont(new Font("宋体", Font.BOLD, 20));
		JTextField userField = new JTextField(20);
		b1.add(label2);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(userField);

		// 组装密码文本框
		Box b2 = Box.createHorizontalBox();
		JLabel label3 = new JLabel("新 密 码: ");
		label3.setFont(new Font("宋体", Font.BOLD, 20));
		JPasswordField passwordField = new JPasswordField(20);
		b2.add(label3);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(passwordField);

		// 组装确认密码文本框
		Box b3 = Box.createHorizontalBox();
		JLabel label4 = new JLabel("确认密码: ");
		label4.setFont(new Font("宋体", Font.BOLD, 20));
		JPasswordField passwordField2 = new JPasswordField(6);
		b3.add(label4);
		b3.add(Box.createHorizontalStrut(20));
		b3.add(passwordField2);

		// 组装按钮
		Box b4 = Box.createHorizontalBox();
		JButton jb1Button = new JButton("确认");
		JButton jb2Button = new JButton("重置");

		jb1Button.setFont(new Font("宋体", Font.BOLD, 20));
		jb2Button.setFont(new Font("宋体", Font.BOLD, 20));

		b4.add(jb1Button);
		b4.add(Box.createHorizontalStrut(100));
		b4.add(jb2Button);

		// 组装整体
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(30));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(30));
		allBox.add(b3);
		allBox.add(Box.createVerticalStrut(50));
		allBox.add(b4);
		allBox.add(Box.createVerticalStrut(50));


		pan.add(allBox);

		jf.setResizable(false);
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGTH) / 2, WIDTH, HEIGTH);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		jb2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
				passwordField2.setText("");

			}
		});

		jb1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				try {
					User user1 = UserDaoImpl.findUserbyID(user.getSid());

					String p1 = passwordField2.getText();
					String p2 = passwordField.getText();
					String p3 = userField.getText();

					if(p3.equals(user1.getPassword())){
						if(p1.equals(p2)){
							UserDaoImpl.ChangePassword(user.getSid(),p2);
							JOptionPane.showMessageDialog(new JFrame(), "修改成功");
							jf.dispose();
						}else{
							JOptionPane.showMessageDialog(new JFrame(), "俩次密码不一致");

						}
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "原密码错误");

					}
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
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jf.dispose();
			}
		});

	}

	// 组装视图
	public void renzheng(User user) throws Exception {
		JFrame jf = new JFrame("认证界面");

		final int WIDTH = 600;
		final int HEIGTH = 327;

		JPanel pan = (JPanel) jf.getContentPane();
		pan.setOpaque(false);// 透明
		pan.setLayout(new FlowLayout());

		Font font = new Font("宋体", Font.PLAIN, 40);


		// 组装账号文本框
		Box b1 = Box.createHorizontalBox();
		JLabel label2 = new JLabel("手机号码 ：");
		label2.setFont(new Font("宋体", Font.BOLD, 20));
		JTextField userField = new JTextField(20);
		b1.add(label2);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(userField);


		userField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(!(keyChar >= '0' && keyChar <= '9')){
					e.consume(); //缺点，不能控制赋值黏贴的内容
				}
				if(userField.getText().length()>10){
					e.consume();
				}
			}
		});


		// 组装密码文本框
		Box b2 = Box.createHorizontalBox();
		JLabel label3 = new JLabel("验证码: ");
		label3.setFont(new Font("宋体", Font.BOLD, 20));
		JTextField passwordField = new JTextField(20);
		b2.add(label3);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(passwordField);



		// 组装按钮
		Box b4 = Box.createHorizontalBox();
		JButton jb1Button = new JButton("确认");
		JButton jb2Button = new JButton("重置");

		jb1Button.setFont(new Font("宋体", Font.BOLD, 20));
		jb2Button.setFont(new Font("宋体", Font.BOLD, 20));

		b4.add(jb1Button);
		b4.add(Box.createHorizontalStrut(100));
		b4.add(jb2Button);

		// 组装整体
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(30));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(30));
		allBox.add(Box.createVerticalStrut(50));
		allBox.add(b4);
		allBox.add(Box.createVerticalStrut(50));


		pan.add(allBox);

		jf.setResizable(false);
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGTH) / 2, WIDTH, HEIGTH);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		jb2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");

			}
		});

		jb1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				try {

					String p2 = passwordField.getText();
					String p3 = userField.getText();

					if(!p2.equals("")&&!p3.equals("")){
						if(!UserDaoImpl.isUserPhone(p2)){
								UserDaoImpl.ChangePhone(user.getSid(),p3);
								jf.dispose();
							JOptionPane.showMessageDialog(new JFrame(), "认证成功");

						}else{

						}
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "请确认输入");
					}



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
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jf.dispose();
			}
		});

	}
}
