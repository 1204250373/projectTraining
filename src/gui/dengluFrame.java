package gui;

import beans.User;
import dao.impl.ScreenUtils;
import dao.impl.UserDaoImpl;
import dbutils.DBHelper;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dengluFrame {
	 JTextField userField;
	  JFrame jf = new JFrame("��¼");
	
		final int WIDTH = 600;
		final int HEIGTH = 400;
		
		//��װ��ͼ
		public dengluFrame() throws Exception{
			//ImageIcon bg = new ImageIcon("src/com/hua/8.jpg");
			//JLabel label = new JLabel(bg);
			//label.setSize(bg.getIconWidth(),bg.getIconHeight());
			//jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
			JPanel pan = (JPanel)jf.getContentPane();
			pan.setOpaque(false);//͸��
			pan.setLayout(new FlowLayout());
			
			JLabel l1 = new JLabel("������һ�廯��Ϣϵͳ",JLabel.CENTER);
	        Font font = new Font("����",Font.PLAIN,40);
			
			l1.setFont(font);
			l1.setForeground(Color.BLACK);
			
			//��װ��ť��
			Box b4 = Box.createHorizontalBox();
			ButtonGroup buttonGroup = new ButtonGroup();// ������ť��
			JRadioButton rb1, rb2;
			rb1 = new JRadioButton("��ͨ�û�");
			rb2 = new JRadioButton("����Ա");
			rb1.setFont(new Font("����", Font.BOLD, 14));
			rb2.setFont(new Font("����", Font.BOLD, 14));
			buttonGroup.add(rb1);
			buttonGroup.add(rb2);
			b4.add(rb1);
			b4.add(Box.createHorizontalStrut(40));
			b4.add(rb2);
		
			
			//��װ�˺��ı���
			Box b1 = Box.createHorizontalBox();
			JLabel label2 = new JLabel("�� �ţ�");
			label2.setFont(new Font("����",Font.BOLD,20));
			userField = new JTextField(20);
			b1.add(label2);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(userField);
			
			//��װ�����ı���
			Box b2 = Box.createHorizontalBox();
			JLabel label3 = new JLabel("�� �룺");
			label3.setFont(new Font("����",Font.BOLD,20));
			JPasswordField passwordField = new JPasswordField(20);
			b2.add(label3);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(passwordField);
			
			//��װ��ť
			Box b3 = Box.createHorizontalBox();
			JButton jb1Button = new JButton("ע��");
			JButton jb2Button = new JButton("��¼");
			JButton jb3Button = new JButton("����");
			jb1Button.setFont(new Font("����",Font.BOLD,20));
			jb2Button.setFont(new Font("����",Font.BOLD,20));
			jb3Button.setFont(new Font("����",Font.BOLD,20));
			b3.add(jb1Button);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jb2Button);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(jb3Button);
			
			//��װ����
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
			
			//jf.setIconImage(ImageIO.read(new File("src/com/hua/1.png")));
			jf.setResizable(false);
			//jf.setLocationRelativeTo(null);
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
						JOptionPane.showMessageDialog(null, "��ѡ����ͨ�û�������߽��е�¼!");
						return;
					}
					if ((rb1.isSelected()||rb2.isSelected())
							&& (userField.getText().equals("") || passwordField.getText().equals(""))) {
						JOptionPane.showMessageDialog(null, "�������˺Ż�����!");
						return;
					}

					//String queryCommonID = "SELECT commonID FROM `common` WHERE commonID IS NOT NULL ";

					//�û�
					if(rb1.isSelected()){
						try {
							if (checkByUnameAndPwd(userField.getText(), passwordField.getText())) {// �ȶ��˺ź�����
								User us =UserDaoImpl.findUserbyID(userField.getText());
								new yonghuFrame(us);
								JOptionPane.showMessageDialog(null, "��¼�ɹ�");//
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
						if (userField.getText().equals("admin") && passwordField.getText().equals("123456")) {// �����˺ź�����
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");

					}else {
						JOptionPane.showMessageDialog(null, "�˺Ż����벻��ȷ");// ��¼ʧ��
					}
					}
				}

				private boolean isSelected(JRadioButton rb2) {
					// TODO Auto-generated method stub
					return false;
				}
				
			});

			jb1Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new zhuceFrame();
				}
			});
			

		}
		// �����ж��˺������Ƿ���ȷ�ķ���
		public static boolean checkByUnameAndPwd(String sid, String pwd) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
			User us =UserDaoImpl.findUserbyID(sid);
			if(!us.getSid().equals("-1")){
				if(us.getPassword().equals(pwd)){
					return true;
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "�������");
					return false;

				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "��ѧ��δע��");
				return false;

			}
		}
		

	public static void main(String[] args) throws Exception {

			dengluFrame d1 = new dengluFrame();

	}

}
