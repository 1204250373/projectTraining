package gui;

import dao.impl.ScreenUtils;
import dbutils.DBHelper;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class zhuceFrame {
	 JFrame jf = new JFrame("ע��");
		
		final int WIDTH = 600;
		final int HEIGTH = 400;
		
		//��װ��ͼ
		public zhuceFrame(){
			ImageIcon bg = new ImageIcon("src/com/hua/8.jpg");
			JLabel label = new JLabel(bg);
			label.setSize(bg.getIconWidth(),bg.getIconHeight());
			jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
			
			JPanel pan = (JPanel)jf.getContentPane();
			pan.setOpaque(false);//͸��
			pan.setLayout(new FlowLayout());
			
			JLabel l1 = new JLabel("������һ�廯��Ϣϵͳ",JLabel.CENTER);
	        Font font = new Font("����",Font.PLAIN,40);
			
			l1.setFont(font);
			l1.setForeground(Color.LIGHT_GRAY);
			
			
			
			//��װ�˺��ı���
			Box b1 = Box.createHorizontalBox();
			JLabel label2 = new JLabel("��    �ţ�");
			label2.setFont(new Font("����",Font.BOLD,20));
			JTextField userField = new JTextField(20);
			b1.add(label2);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(userField);
			
			//��װ�����ı���
			Box b2 = Box.createHorizontalBox();
			JLabel label3 = new JLabel("��    �룺");
			label3.setFont(new Font("����",Font.BOLD,20));
			JPasswordField passwordField = new JPasswordField(20);
			b2.add(label3);
			b2.add(Box.createHorizontalStrut(20));
			b2.add(passwordField);
			
			//��װȷ�������ı���
			Box b3 = Box.createHorizontalBox();
			JLabel label4 = new JLabel("ȷ�����룺");
			label4.setFont(new Font("����",Font.BOLD,20));
			JPasswordField passwordField2 = new JPasswordField(6);
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
			
//			try {
//				//jf.setIconImage(ImageIO.read(new File("src/com/hua/1.png")));
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
			jf.setResizable(false);
			jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,WIDTH,HEIGTH);
			jf.setLocationRelativeTo(null);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	                
						String u1 = userField.getText();
						String p1 = passwordField.getText();
						String sql0 = "select * from user where commonID='"+u1+"'";
						if ((userField.getText().equals("") || passwordField.getPassword().equals(""))) {
							JOptionPane.showMessageDialog(null, "�������˺Ż�����!");
							return;
						}
						Object[][] date = new String[50][9];
						ResultSet rs = DBHelper.query(sql0);//ִ��
							try {
								for(int i=0;rs.next();i++){
									date[i][0] = rs.getString("id");
									date[i][1] = rs.getString("commonID");
									date[i][2] = rs.getString("commonPassword");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if(u1.equals(date[0][1])){
							JOptionPane.showMessageDialog(null, "�û��Ѵ���");
							return;
						}else if (passwordField2.getText().equals(passwordField.getText())) {
							String sql = "insert into user(commonID,commonPassword) values('" +u1+  "','" + p1 + "')";
							if(DBHelper.update(sql)!= -1) {
								JOptionPane.showMessageDialog(null, "ע��ɹ�!");
								try {
									new dengluFrame();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								jf.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "��ȷ������!");
						}

					
				
				
				}

				private void addData(String sql) {
					// TODO Auto-generated method stub

				}

				private void addData() {
					// TODO Auto-generated method stub

				}
			});

		}

		protected void connection(String string, String string2, String string3) {
			// TODO Auto-generated method stub

		}
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
         System.out.println(e);
        }
		zhuceFrame z1 = new zhuceFrame();
	}

}
