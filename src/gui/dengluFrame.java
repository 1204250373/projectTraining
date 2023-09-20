

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
			l1.setForeground(Color.lightGray);
			
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
			jf.setLocationRelativeTo(null);
			//jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGTH)/2,WIDTH,HEIGTH);
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
					if (rb1.isSelected()
							&& (userField.getText().equals("") || ( passwordField)
									.getPassword().equals(""))) {
						JOptionPane.showMessageDialog(null, "�������˺Ż�����!");
						return;
					}

					if (rb2.isSelected()
							&& (userField.getText().equals("") || ( passwordField)
									.getPassword().equals(""))) {
						JOptionPane.showMessageDialog(null, "�������˺Ż�����!");
						return;
					}

					String queryCommonID = "SELECT commonID FROM `common` WHERE commonID IS NOT NULL ";

					if(rb1.isSelected()){
					if (checkByUnameAndPwd(userField.getText(), new String(
							passwordField.getPassword()))) {// �ȶ��˺ź�����
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");//
						//new yonghuFrame();
					} else {
						JOptionPane.showMessageDialog(null, "�˺Ż����벻��ȷ");// ��¼ʧ��
						try {
							new dengluFrame();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}else if(rb2.isSelected()){
					if (userField.getText().trim().equals("admin")
							&& new String(passwordField.getPassword())
									.equals("123456") && rb2.isSelected()) {// �����˺ź�����
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						try {// ��¼�ɹ���ת�´���
							//new manageFrame();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "�˺Ż����벻��ȷ");// ��¼ʧ��
						try {
							new dengluFrame();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					jf.dispose();
				}

				private boolean isSelected(JRadioButton rb2) {
					// TODO Auto-generated method stub
					return false;
				}
				
			});

			jb1Button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//new zhuceFrame();
					jf.dispose();
				}
			});
			

		}
		// �����ж��˺������Ƿ���ȷ�ķ���
		public static boolean checkByUnameAndPwd(String uname, String pwd) {
			String sql = "select * from user where commonID='" + uname
					+ "' and commonPassword='" + pwd + "' ";
			ResultSet rSet1 = DBHelper.query(sql);
			boolean flag = false;
			try {
				while (rSet1.next()) {
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
         System.out.println(e);
        }
		Connection coon = DBHelper.getConnection();
		if (coon != null) {
			System.out.println("���ݿ����ӳɹ�");
		}
		try {
			dengluFrame d1 = new dengluFrame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close();

	}

}
