package gui;

import beans.User;
import dao.impl.ScreenUtils;
import dao.impl.UserDaoImpl;
import dbutils.DBHelper;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class registerFrame {

	 JFrame jf = new JFrame("ע��");
		
		final int WIDTH = 600;
		final int HEIGTH = 400;
	JTextField userField;
	JPasswordField passwordField;
	JPasswordField passwordField2;
		//��װ��ͼ
		public registerFrame(){
			JPanel pan = (JPanel)jf.getContentPane();
			pan.setOpaque(false);//͸��
			pan.setLayout(new FlowLayout());
			
			JLabel l1 = new JLabel("������һ�廯��Ϣϵͳ",JLabel.CENTER);
	        Font font = new Font("����",Font.PLAIN,40);
			
			l1.setFont(font);
			l1.setForeground(Color.BLACK);
			
			
			
			//��װ�˺��ı���
			Box b1 = Box.createHorizontalBox();
			JLabel label2 = new JLabel("ѧ    �ţ�");
			label2.setFont(new Font("����",Font.BOLD,20));
			userField = new JTextField(20);
			b1.add(label2);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(userField);
			
			//��װ�����ı���
			Box b2 = Box.createHorizontalBox();
			JLabel label3 = new JLabel("��    �룺");
			label3.setFont(new Font("����",Font.BOLD,20));
			passwordField = new JPasswordField(20);
			b2.add(label3);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(passwordField);
			
			//��װȷ�������ı���
			Box b3 = Box.createHorizontalBox();
			JLabel label4 = new JLabel("ȷ�����룺");
			label4.setFont(new Font("����",Font.BOLD,20));
			passwordField2 = new JPasswordField(6);
			b3.add(label4);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(passwordField2);
			
			//��װ��ť
			Box b4 = Box.createHorizontalBox();
			JButton jb1Button = new JButton("ȷ��");
			JButton jb2Button = new JButton("����");
			
			jb1Button.setFont(new Font("����",Font.BOLD,20));
			jb2Button.setFont(new Font("����",Font.BOLD,20));

			b4.add(jb1Button);
			b4.add(Box.createHorizontalStrut(100));
			b4.add(jb2Button);
			
			
			
			//��װ����
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
			pan.add(l1);
			pan.add(allBox);

			jf.setResizable(false);
			jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,WIDTH,HEIGTH);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);


			//==========================================================================
			jb2Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clearText();

				}
			});

			userField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if(!(keyChar >= '0' && keyChar <= '9')){
						e.consume(); //ȱ�㣬���ܿ��Ƹ�ֵ���������
					}
					if(userField.getText().length()>10){
						e.consume();
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

			jb1Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
	                
						String u1 = userField.getText();
						String p1 = passwordField.getText();
						String p2 = passwordField2.getText();

						if(!u1.equals("")&&p1.equals(p2)){
							try {
								User user = UserDaoImpl.findUserbyID(u1);
//								sidΪ-1���ѱ�ע��
								if(user.getSid().equals("-1")){
									UserDaoImpl.addUser(u1,p1);


									JOptionPane.showMessageDialog(new JFrame(), "ע��ɹ�");
									jf.dispose();
								}else{
									JOptionPane.showMessageDialog(new JFrame(), "ѧ���ѱ�ע��");
									clearText();
								}
							} catch (ClassNotFoundException classNotFoundException) {
								classNotFoundException.printStackTrace();
							} catch (SQLException throwables) {
								throwables.printStackTrace();
							} catch (InstantiationException instantiationException) {
								instantiationException.printStackTrace();
							} catch (IllegalAccessException illegalAccessException) {
								illegalAccessException.printStackTrace();
							} catch (Exception exception) {
								exception.printStackTrace();
							}
						}else if(!p1.equals(p2)){
							JOptionPane.showMessageDialog(new JFrame(), "�������벻һ��");
						}else{
							JOptionPane.showMessageDialog(new JFrame(), "��ȷ���Ƿ������д");
						}



				}
			});
		}


	private  void clearText(){
		userField.setText("");
		passwordField.setText("");
		passwordField2.setText("");
	}
	public static void main(String[] args) {
		registerFrame RF = new registerFrame();
	}

}
