package gui;

import beans.User;
import dao.impl.ScreenUtils;
import dao.impl.UserDaoImpl;
import dbutils.DBHelper;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFrame {
	 JTextField userField;
	  JFrame jf = new JFrame("登录");
	
		final int WIDTH = 600;
		final int HEIGTH = 400;

//	public static void main(String[] args) throws Exception {
//
//		LoginFrame d1 = new LoginFrame();
//
//	}
//
		//组装视图
		public LoginFrame() throws Exception{
			JPanel pan = (JPanel)jf.getContentPane();
			pan.setOpaque(false);//透明
			pan.setLayout(new FlowLayout());
			
			JLabel l1 = new JLabel("产销存一体化信息系统",JLabel.CENTER);
	        Font font = new Font("宋体",Font.PLAIN,40);
			
			l1.setFont(font);
			l1.setForeground(Color.BLACK);
			
			//组装按钮组
			Box b4 = Box.createHorizontalBox();
			ButtonGroup buttonGroup = new ButtonGroup();// 创建按钮组
			JRadioButton rb1, rb2;
			rb1 = new JRadioButton("普通用户");
			rb2 = new JRadioButton("管理员");
			rb1.setFont(new Font("黑体", Font.BOLD, 14));
			rb2.setFont(new Font("黑体", Font.BOLD, 14));
			buttonGroup.add(rb1);
			buttonGroup.add(rb2);
			b4.add(rb1);
			b4.add(Box.createHorizontalStrut(40));
			b4.add(rb2);

			//组装账号文本框
			Box b1 = Box.createHorizontalBox();
			JLabel label2 = new JLabel("账 号：");
			label2.setFont(new Font("宋体",Font.BOLD,20));
			userField = new JTextField(20);
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

			//组装密码文本框
			Box b2 = Box.createHorizontalBox();
			JLabel label3 = new JLabel("密 码：");
			label3.setFont(new Font("宋体",Font.BOLD,20));
			JPasswordField passwordField = new JPasswordField(20);
			b2.add(label3);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(passwordField);
			
			//组装按钮
			Box b3 = Box.createHorizontalBox();
			JButton jb1Button = new JButton("注册");
			JButton jb2Button = new JButton("登录");
			JButton jb3Button = new JButton("重置");
			jb1Button.setFont(new Font("宋体",Font.BOLD,20));
			jb2Button.setFont(new Font("宋体",Font.BOLD,20));
			jb3Button.setFont(new Font("宋体",Font.BOLD,20));
			b3.add(jb1Button);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jb2Button);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jb3Button);
			
			//组装整体
			Box allBox = Box.createVerticalBox();
			allBox.add(Box.createVerticalStrut(20));
			allBox.add(b4);
			allBox.add(Box.createVerticalStrut(30));
			allBox.add(b1);
			allBox.add(Box.createVerticalStrut(30));
			allBox.add(b2);
			allBox.add(Box.createVerticalStrut(50));
			allBox.add(b3);

			pan.add(l1);
			
			pan.add(allBox);
			
			jf.setResizable(false);
			jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,WIDTH,HEIGTH);
			jf.setLocationRelativeTo(null);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setVisible(true);


			jb3Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					userField.setText("");
					passwordField.setText("");
				}
			});

			jb2Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!rb1.isSelected() && !rb2.isSelected()) {
						JOptionPane.showMessageDialog(null, "请选择普通用户或管理者进行登录!");
						return;
					}
					if ((rb1.isSelected()||rb2.isSelected())
							&& (userField.getText().equals("") || passwordField.getText().equals(""))) {
						JOptionPane.showMessageDialog(null, "请输入账号或密码!");
						return;
					}


					//用户
					if(rb1.isSelected()){
						try {
							if (checkByUnameAndPwd(userField.getText(), passwordField.getText())) {// 比对账号和密码
								User us =UserDaoImpl.findUserbyID(userField.getText());
								new yonghuFrame(us);
								JOptionPane.showMessageDialog(null, "登录成功");//
								jf.dispose();

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
					}else if(rb2.isSelected()){
						try {
							User us =UserDaoImpl.findUserbyID(userField.getText());
							if(us.getType().equals("管理员")){
								if (checkByUnameAndPwd(userField.getText(), passwordField.getText())) {
									new adminFrame(us);
									jf.dispose();
									JOptionPane.showMessageDialog(null, "登录成功");

								}else {
									JOptionPane.showMessageDialog(null, "账号或密码不正确");// 登录失败
								}
							}else{
								JOptionPane.showMessageDialog(null, "非管理员");
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
				}

				
			});

			jb1Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new registerFrame();
				}
			});
			

		}
		// 设置判断账号密码是否正确的方法
		public static boolean checkByUnameAndPwd(String sid, String pwd) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
			User us =UserDaoImpl.findUserbyID(sid);
			if(!us.getSid().equals("-1")){
				if(us.getPassword().equals(pwd)){
					return true;
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "密码错误");
					return false;
				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "该学号未注册");
				return false;
			}
		}
		



}
