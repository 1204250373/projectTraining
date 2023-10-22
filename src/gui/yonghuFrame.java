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
	JFrame jf = new JFrame("�û�������");
	
	final int WIDTH = 1300;
	final int HEIGTH = 700;


//	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//
//		User user = UserDaoImpl.findUserbyID("1234567891");
//		yonghuFrame y1 = new yonghuFrame(user);
//
//	}

	//��װ��ͼ
	public yonghuFrame(User user){
		//��������������
	    jf.setResizable(false);
		//�Ѵ��������Ϊ������岢��Ϊ͸�����������֡�
		JPanel pan = (JPanel)jf.getContentPane();
		pan.setOpaque(false);//͸��
		pan.setLayout(new FlowLayout());
		
		
		JLabel l1 = new JLabel("�����齻��ƽ̨",JLabel.CENTER);
        Font font1 = new Font("����",Font.PLAIN,100);
		
		l1.setFont(font1);
		l1.setForeground(Color.black);
		pan.setLayout(new BorderLayout());
		pan.add(l1,new BorderLayout().CENTER);
		//���ò˵���
		JMenuBar jmb = new JMenuBar();

		
		
		JMenu jm2 = new JMenu("���۹���");
		JMenuItem jmi3 = new JMenuItem("������");
		JMenuItem jmi4 = new JMenuItem("���������Ʒ");
		JMenu jm5 = new JMenu("����ϵͳ");
		JMenuItem jmi5 = new JMenuItem("�����򶩵�");
		JMenuItem jmi6 = new JMenuItem("�����鼮");
		JMenu jm6 = new JMenu("����Ա����");
		JMenuItem jmi7 = new JMenuItem("�л�");
		JMenu jm3 = new JMenu("�޸�����");		
		JMenu jm4 = new JMenu("�л��˺�");
		jm2.add(jmi3);
		jm2.add(jmi4);
		jmb.add(jm2);
		
		jm5.add(jmi6);
		jm5.add(jmi5);
		jmb.add(jm5);
		
		jm6.add(jmi7);
		jmb.add(jm3);
		jmb.add(jm4);

		if(user.getType().equals("����Ա")){
			jmb.add(jm6);
		}

		//�޸Ĳ˵�������
		Font font2 = new Font("����",Font.ITALIC,25);

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
					//ͨ����֤�����˺����phone���ܳ�����Ʒ

					try {
						JOptionPane.showMessageDialog(jf, "��ͨ����֤");
						renzheng(user);
					} catch (Exception exception) {
						exception.printStackTrace();
					}


				}


			}
		});
		//��������
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
					//ͨ����֤�����˺����phone���ܳ�����Ʒ

					try {
						JOptionPane.showMessageDialog(jf, "��ͨ����֤");
						renzheng(user);
					} catch (Exception exception) {
						exception.printStackTrace();
					}


				}
			}
		});
		//��Ҷ�����¼
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


	// ��װ��ͼ
	public void changePassword(User user) throws Exception {
		JFrame jf = new JFrame("�޸��������");

		final int WIDTH = 600;
		final int HEIGTH = 327;

		JPanel pan = (JPanel) jf.getContentPane();
		pan.setOpaque(false);// ͸��
		pan.setLayout(new FlowLayout());

		Font font = new Font("����", Font.PLAIN, 40);


		// ��װ�˺��ı���
		Box b1 = Box.createHorizontalBox();
		JLabel label2 = new JLabel("ԭ �� �룺");
		label2.setFont(new Font("����", Font.BOLD, 20));
		JTextField userField = new JTextField(20);
		b1.add(label2);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(userField);

		// ��װ�����ı���
		Box b2 = Box.createHorizontalBox();
		JLabel label3 = new JLabel("�� �� ��: ");
		label3.setFont(new Font("����", Font.BOLD, 20));
		JPasswordField passwordField = new JPasswordField(20);
		b2.add(label3);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(passwordField);

		// ��װȷ�������ı���
		Box b3 = Box.createHorizontalBox();
		JLabel label4 = new JLabel("ȷ������: ");
		label4.setFont(new Font("����", Font.BOLD, 20));
		JPasswordField passwordField2 = new JPasswordField(6);
		b3.add(label4);
		b3.add(Box.createHorizontalStrut(20));
		b3.add(passwordField2);

		// ��װ��ť
		Box b4 = Box.createHorizontalBox();
		JButton jb1Button = new JButton("ȷ��");
		JButton jb2Button = new JButton("����");

		jb1Button.setFont(new Font("����", Font.BOLD, 20));
		jb2Button.setFont(new Font("����", Font.BOLD, 20));

		b4.add(jb1Button);
		b4.add(Box.createHorizontalStrut(100));
		b4.add(jb2Button);

		// ��װ����
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
							JOptionPane.showMessageDialog(new JFrame(), "�޸ĳɹ�");
							jf.dispose();
						}else{
							JOptionPane.showMessageDialog(new JFrame(), "�������벻һ��");

						}
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "ԭ�������");

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

	// ��װ��ͼ
	public void renzheng(User user) throws Exception {
		JFrame jf = new JFrame("��֤����");

		final int WIDTH = 600;
		final int HEIGTH = 327;

		JPanel pan = (JPanel) jf.getContentPane();
		pan.setOpaque(false);// ͸��
		pan.setLayout(new FlowLayout());

		Font font = new Font("����", Font.PLAIN, 40);


		// ��װ�˺��ı���
		Box b1 = Box.createHorizontalBox();
		JLabel label2 = new JLabel("�ֻ����� ��");
		label2.setFont(new Font("����", Font.BOLD, 20));
		JTextField userField = new JTextField(20);
		b1.add(label2);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(userField);


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


		// ��װ�����ı���
		Box b2 = Box.createHorizontalBox();
		JLabel label3 = new JLabel("��֤��: ");
		label3.setFont(new Font("����", Font.BOLD, 20));
		JTextField passwordField = new JTextField(20);
		b2.add(label3);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(passwordField);



		// ��װ��ť
		Box b4 = Box.createHorizontalBox();
		JButton jb1Button = new JButton("ȷ��");
		JButton jb2Button = new JButton("����");

		jb1Button.setFont(new Font("����", Font.BOLD, 20));
		jb2Button.setFont(new Font("����", Font.BOLD, 20));

		b4.add(jb1Button);
		b4.add(Box.createHorizontalStrut(100));
		b4.add(jb2Button);

		// ��װ����
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
							JOptionPane.showMessageDialog(new JFrame(), "��֤�ɹ�");

						}else{

						}
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "��ȷ������");
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
