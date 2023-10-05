package gui;


import dao.impl.ScreenUtils;
import dbutils.DBHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		
		
		
		JMenu jm2 = new JMenu("库存系统");
		JMenuItem jmi3 = new JMenuItem("商品上架");
		JMenuItem jmi4 = new JMenuItem("商品下架");
		JMenuItem jmi5 = new JMenuItem("查看商品库存信息");
		JMenuItem jmi9 = new JMenuItem("添加新商品");
		
		JMenu jm5 = new JMenu("销售系统");
		JMenuItem jmi6 = new JMenuItem("查看商品销售信息");
		
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
		jm2.add(jmi5);
		jm2.add(jmi9);
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
		jmi5.setFont(font2);
		jmi6.setFont(font2);
		jmi7.setFont(font2);
		jmi8.setFont(font2);
		jmi9.setFont(font2);
		

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
		//		new newOff();

			}
		});
		jmi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		new newOn();

			}
		});
		jmi5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sql1 = "update mdseinfo set mdseState = '下架' where CurrentStorage <= 0";
				if(DBHelper.update(sql1)!=-1){
					System.out.println("已有商品自动下架");
				}
		//		new mdseQuery();
				String sql2 = "select * from mdseinfo where CurrentStorage<=3";
				if(DBHelper.query(sql2)!=null){
					JOptionPane.showMessageDialog(null, "警报！有商品库存不足，请及时补充！");
				}
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
		jmi9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		new addNewproduct();

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

}
