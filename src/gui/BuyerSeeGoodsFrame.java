package gui;

import beans.User;
import dao.impl.DoOrder;
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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;


public class BuyerSeeGoodsFrame implements ActionListener{
	JFrame jf = new JFrame("��Ҳ鿴����");
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	Vector<String> columnNames;
	private JPopupMenu  m_popupMenu;
	private  int row;
	public  static Object mdseName;

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		User user = UserDaoImpl.findUserbyID("2240129516");
		BuyerSeeGoodsFrame s1 = new BuyerSeeGoodsFrame(user);

	}


	//��װ��ͼ
	public BuyerSeeGoodsFrame(User user) throws SQLException {
		//�������
		JPanel panel = new JPanel();
		//���
		columnNames = new Vector<String>(Arrays.asList(new String[]{"�������", "�����ֻ�����", "����id", "��ҵ绰","���id",
				"����", "�鵥��", "��������","�ܼ�","����״̬","����ʱ��","��ҵ�ַ","����ʱ��","�ջ����"}));//��ͷ

		final Vector<Vector<String>>[] data = new Vector[]{DoOrder.BuyerSeekOrder(user)};//��ȡ������
		DoOrder.SortOrderrece_UP(data[0]);
		model = new DefaultTableModel(data[0], columnNames);
		table = new JTable(model);//��ͷ�����ݷ����

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);//��ʾ���е��ж��������϶�
		table.setRowHeight(25);//�����и�
		table.setPreferredScrollableViewportSize(new Dimension(800, 500));//���ű�Ĵ�С
		scrollPane = new JScrollPane(table);//������������
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, new BorderLayout().NORTH);//�ѱ�������


		// �����������ӵ���������
		m_popupMenu = new JPopupMenu();

		JMenuItem delMenItem = new JMenuItem();
		delMenItem.setText("����");

		m_popupMenu.add(delMenItem);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});


		//��װ�˺������
		Box b2 = Box.createHorizontalBox();

		//��װ��ť
		JButton Refresh = new JButton("ˢ��");
		b2.add(Refresh);


		//���������С
		table.setFont(new Font("����", Font.PLAIN, 18));

		//��װȫ��
		Box allBox = Box.createVerticalBox();
		allBox.add(b2);
		allBox.add(Box.createVerticalStrut(20));
		panel.add(allBox, new BorderLayout().CENTER);

		jf.setContentPane(panel);
		//���ô�������
		jf.setSize(1000, 700);//��С
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
				if(data[0].get(row).get(DoOrder.RECEIVEGOODS).equals("δ�ջ�")){
					delMenItem.setText("ȷ���ջ�");
				}else{
					delMenItem.setText("���ջ�");
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
				mdseName=table.getValueAt(row,1);
				if(delMenItem.getText().equals("ȷ���ջ�")){
					if(data[0].get(row).get(DoOrder.YORN).equals("�ѷ���")){
						DoOrder.ChangeReceive(data[0].get(row).get(DoOrder.OID));
						//��ȡ������
						try {
							data[0] = DoOrder.BuyerSeekOrder(user);
							DoOrder.SortOrderrece_UP(data[0]);
							RefreshTable(data[0]);
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

						JOptionPane.showMessageDialog(jf, "���ĳɹ�");
					}else{
						JOptionPane.showMessageDialog(jf, "δ�����޷��ջ�");
					}
					}

			}
		});
		Refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					data[0] = DoOrder.BuyerSeekOrder(user);
					DoOrder.SortOrderrece_UP(data[0]);
					RefreshTable(data[0]);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
	}



	//����
	@Override
	public void actionPerformed(ActionEvent e) {

	}


	//����data��ˢ��display�鼮
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


