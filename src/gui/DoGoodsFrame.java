package gui;

import beans.User;
import dao.impl.DoOrder;
import dao.impl.GetNewTime;
import dao.impl.SSGBook;
import dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;


public class DoGoodsFrame implements ActionListener{
	JFrame jf = new JFrame("���ҹ�����");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	Vector<String> columnNames;
	private JPopupMenu  m_popupMenu;
	private  int row;
	public  static Object mdseName;
	//��װ��ͼ
	public DoGoodsFrame(User user) throws SQLException {
		//�������
		JPanel panel = new JPanel();
		//���
		columnNames = new Vector<String>(Arrays.asList(new String[]{"�������", "�����ֻ�����", "����id", "��ҵ绰","���id",
				"����", "�鵥��", "��������","�ܼ�","����״̬","����ʱ��","��ҵ�ַ"}));//��ͷ

		final Vector<Vector<String>>[] data = new Vector[]{DoOrder.SellerSeekOrder(user)};//��ȡ������
//		SSGBook.SortBook_State_DOWN(data);
		DoOrder.SortOrderState_UP(data[0]);
//		DoOrder.SortOrderDELIVER_UP(data);
		model = new DefaultTableModel(data[0], columnNames);
		table = new JTable(model);//��ͷ�����ݷ����

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
		table.setRowHeight(25);//�����и�
		table.setPreferredScrollableViewportSize(new Dimension(800, 500));//���ű�Ĵ�С
		scrollPane = new JScrollPane(table);//������������
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, new BorderLayout().SOUTH);//�ѱ�������

		// �����������ӵ���������
		m_popupMenu = new JPopupMenu();

		JMenuItem delMenItem = new JMenuItem();
		delMenItem.setText("����");
		delMenItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mdseName=table.getValueAt(row,1);

			}
		});
		m_popupMenu.add(delMenItem);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});

		//��װ�˺������
		Box b2 = Box.createHorizontalBox();

		b2.add(Box.createHorizontalStrut(100));



		b2.add(Box.createHorizontalStrut(100));


		//��װ��ť
		Box b1 = Box.createHorizontalBox();

		table.setFont(new Font("����", Font.PLAIN, 18));



		//��װȫ��
		Box allBox = Box.createVerticalBox();
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		allBox.add(b1);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox, new BorderLayout().CENTER);

		jf.setContentPane(panel);
		//���ô�������
		jf.setSize(1000, 700);//��С
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);//����
		jf.setVisible(true);

		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				jf.dispose();
			}
		});

		m_popupMenu.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {
				if(data[0].get(row).get(DoOrder.YORN).equals("δ����")){
					delMenItem.setText("����");
				}else{
					delMenItem.setText("�ѷ���");
				}
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {

			}

			@Override
			public void ancestorMoved(AncestorEvent event) {

			}
		});
		delMenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(delMenItem.getText().equals("����")){
					System.out.println(row);
					DoOrder.NtoY(data[0].get(row).get(DoOrder.OID));
					DoOrder.SetDeliverGoods(data[0].get(row).get(DoOrder.OID));
					//��ȡ������
					try {
						data[0] = DoOrder.SellerSeekOrder(user);
						DoOrder.SortOrderState_UP(data[0]);
						RefreshTable(data[0]);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

					JOptionPane.showMessageDialog(jf, "���ĳɹ�");
				}
			}
		});
	}

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		User user = UserDaoImpl.findUserbyID("2240129107");
		DoGoodsFrame s1 = new DoGoodsFrame(user);

	}


	//����
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	//����data��ˢ��display
	private void RefreshTable(Vector<Vector<String>> data){

//			����һ��TableModel���󣬲������ͷ�ͱ�����
		TableModel tableModel=new DefaultTableModel(data,columnNames);
//			��TableModel������Table���
		table.setModel(tableModel);

	}





	private void tableMouseClicked(MouseEvent evt) {
		mouseRightButtonClick(evt);
	}
	private void mouseRightButtonClick(MouseEvent evt) {
		//�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�
		if (evt.getButton() == MouseEvent.BUTTON3) {
			//ͨ�����λ���ҵ����Ϊ����е���
			row = table.rowAtPoint(evt.getPoint());
			if (row == -1) {
				return;
			}
			//�������ѡ����Ϊ��ǰ�Ҽ��������
			table.setRowSelectionInterval(row,row);
			//�����˵�
			m_popupMenu.show(table, evt.getX(), evt.getY());
		}
	}



}


