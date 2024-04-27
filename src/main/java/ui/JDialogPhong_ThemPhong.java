package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;

import constant.Types;
import dao.LoaiPhongServer;
import dao.PhongServer;
import entity.LoaiPhong;
import entity.Phong;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;

public class JDialogPhong_ThemPhong extends JDialog implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_TenPhong;
	private JComboBox<String> cbb_Status;
	private JComboBox<String> cbb_PeopleNum;
	private ButtonGroup group;
	private JRadioButton rdb_RoomVip;
	private JRadioButton rdbtnThng;
	private JButton okButton;
	private GD_Phong home;
	private SpinnerNumberModel modelGiaPhong;
	private JSpinner spn_GiaPhong;

	private PhongServer phong_Dao; 
	private LoaiPhongServer loaiPhong_Dao;
	private static final String URL = Types.URL;
	/**
	 * Create the dialog.
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public JDialogPhong_ThemPhong(GD_Phong home) throws RemoteException, MalformedURLException, NotBoundException {
		
		phong_Dao = (PhongServer) Naming.lookup(URL + "PhongServer");
		loaiPhong_Dao = (LoaiPhongServer) Naming.lookup(URL + "LoaiPhongServer");
		
		this.home = home;
		setPreferredSize(new Dimension(200, 300));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Thêm phòng mới");
		setBounds(size.width / 4, size.height / 4, size.width / 2, size.height / 4 * 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(new Color(222, 184, 135));
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(4, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setPreferredSize(new Dimension(200, 10));
				panel_1.setBorder(new CompoundBorder(
						new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)), null));
				panel_1.setBackground(new Color(255, 255, 255));
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new GridLayout(0, 1, 0, 10));
				{
					JLabel lblNewLabel_5 = new JLabel("");
					panel_1.add(lblNewLabel_5);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Tên phòng");
					lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Số người");
					lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblNewLabel_3);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Loại phòng");
					lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Giá phòng");
					lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
					lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_1.add(lblNewLabel_3);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Tình trạng");
					lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblNewLabel_4);
				}
				{
					JLabel lblNewLabel_5 = new JLabel("");
					panel_1.add(lblNewLabel_5);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 4, 0, 10)));
				panel_2.setBackground(new Color(255, 250, 250));
				panel.add(panel_2, BorderLayout.CENTER);
				panel_2.setLayout(new GridLayout(0, 1, 0, 10));
				{
					JLabel lblNewLabel_5 = new JLabel("");
					panel_2.add(lblNewLabel_5);
				}
				{
					txt_TenPhong = new JTextField();
					txt_TenPhong.setEditable(false);
					txt_TenPhong.setHorizontalAlignment(SwingConstants.CENTER);
					txt_TenPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
					txt_TenPhong.setFont(new Font("Dialog", Font.BOLD, 14));
					txt_TenPhong.setColumns(10);
					panel_2.add(txt_TenPhong);

					int index = phong_Dao.getAll().size() + 1;
					txt_TenPhong.setText("PHÒNG " + index);
				}
				{
					cbb_PeopleNum = new JComboBox<String>();
					cbb_PeopleNum.setMaximumRowCount(20);
					cbb_PeopleNum.setEditable(true);
					cbb_PeopleNum.setBorder(new LineBorder(new Color(0, 0, 0)));
					cbb_PeopleNum.setFont(new Font("Dialog", Font.BOLD, 16));
					cbb_PeopleNum.addItem("5");
					cbb_PeopleNum.addItem("10");
					cbb_PeopleNum.addItem("20");
					cbb_PeopleNum.setSelectedIndex(0);
					panel_2.add(cbb_PeopleNum);

					cbb_PeopleNum.addItemListener(this);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setOpaque(false);
					panel_2.add(panel_1);
					panel_1.setLayout(new GridLayout(1, 2, 5, 0));
					{
						group = new ButtonGroup();
						rdb_RoomVip = new JRadioButton(Types.ROOM_TYPE_VIP);
						rdb_RoomVip.setBorderPainted(true);
						rdb_RoomVip.setBorder(new LineBorder(new Color(0, 0, 0)));
						rdb_RoomVip.setSelected(true);
						group.add(rdb_RoomVip);
						rdb_RoomVip.setOpaque(false);
						rdb_RoomVip.setHorizontalAlignment(SwingConstants.CENTER);
						rdb_RoomVip.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(rdb_RoomVip);

						rdb_RoomVip.addActionListener(this);
					}
					{
						rdbtnThng = new JRadioButton("THƯỜNG");
						rdbtnThng.setBorderPainted(true);
						rdbtnThng.setBorder(new LineBorder(new Color(0, 0, 0)));
						group.add(rdbtnThng);
						rdbtnThng.setOpaque(false);
						rdbtnThng.setHorizontalAlignment(SwingConstants.CENTER);
						rdbtnThng.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(rdbtnThng);

						rdbtnThng.addActionListener(this);
					}
				}
				{
					cbb_Status = new JComboBox<String>();
					cbb_Status.setBorder(new LineBorder(new Color(0, 0, 0)));
					cbb_Status.addItem("TRỐNG");
					cbb_Status.addItem("BẢO TRÌ");
					{
						JPanel panel_1 = new JPanel();
						panel_1.setOpaque(false);
						panel_2.add(panel_1);
						panel_1.setLayout(new BorderLayout(5, 0));
						{
							spn_GiaPhong = new JSpinner();
							spn_GiaPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
							modelGiaPhong = new SpinnerNumberModel(Integer.valueOf(10000), Integer.valueOf(10000), null,
									Integer.valueOf(10000));
							spn_GiaPhong.setModel(modelGiaPhong);
							spn_GiaPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_1.add(spn_GiaPhong);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("VND");
							lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
							lblNewLabel_1.setPreferredSize(new Dimension(50, 13));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
							panel_1.add(lblNewLabel_1, BorderLayout.EAST);
						}
					}
					cbb_Status.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_2.add(cbb_Status);

					cbb_Status.addItemListener(this);
				}
				{
					JLabel lblNewLabel_5 = new JLabel("");
					panel_2.add(lblNewLabel_5);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel_6 = new JLabel("THÊM PHÒNG MỚI");
				lblNewLabel_6.setForeground(Color.WHITE);
				lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel.add(lblNewLabel_6);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 10));
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setVgap(10);
				panel.setOpaque(false);
				buttonPane.add(panel, BorderLayout.EAST);
				{
					okButton = new JButton("THÊM");
					okButton.addActionListener(this);
					okButton.setPreferredSize(new Dimension(200, 30));
					panel.add(okButton);
					okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("HỦY");
					cancelButton.setPreferredSize(new Dimension(100, 30));
					panel.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							close();
						}
					});
					cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
					cancelButton.setActionCommand("Cancel");
				}
			}
		}
		modelGiaPhong.setValue(convertSizeRoomToPrice(cbb_PeopleNum.getSelectedItem() + "", "VIP"));
		
	}

	protected void close() {
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(okButton)) {
			Phong room = new Phong();
			int index = 0;
			try {
				index = phong_Dao.getAll().size() + 1;
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				room.setMaP(index + "");
				room.setTenP("Phòng " + index);
				room.setGiaP(Double.parseDouble(spn_GiaPhong.getValue()+""));
				int succhuc = Integer.parseInt(cbb_PeopleNum.getSelectedItem()+"");
				room.setSucChua(succhuc);
				if (rdb_RoomVip.isSelected()) {
					if(loaiPhong_Dao.findName(Types.ROOM_TYPE_VIP) != null)
						room.setMaLP(loaiPhong_Dao.findName(Types.ROOM_TYPE_VIP));
					else {
						loaiPhong_Dao.add(new LoaiPhong("VIP", Types.ROOM_TYPE_VIP));
						room.setMaLP(loaiPhong_Dao.findName(Types.ROOM_TYPE_VIP));						
					}

				} else {
					if(loaiPhong_Dao.findName(Types.ROOM_TYPE_NORMAL) != null)
						room.setMaLP(loaiPhong_Dao.findName(Types.ROOM_TYPE_NORMAL));
					else {
						loaiPhong_Dao.add(new LoaiPhong("THUONG", Types.ROOM_TYPE_NORMAL));
						room.setMaLP(loaiPhong_Dao.findName(Types.ROOM_TYPE_NORMAL));						
					}
				}
				
				
				room.setTinhTrang(cbb_Status.getSelectedItem() + "");
			} catch (Exception e2) {
				e2.printStackTrace();
				room = null;
			}
			boolean addDB = false;
			try {
				addDB = phong_Dao.add(room);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Phòng chưa thêm vào DB: " + room);
			if (addDB)
				JOptionPane.showMessageDialog(null, "Thêm " + room.getTenP() + " thành công !");
			else
				JOptionPane.showMessageDialog(null, "Thêm phòng mới thất bại !");
			try {
				this.home.updateRoomView(phong_Dao.getAll());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.hide();

		} else if (src.equals(rdb_RoomVip))
			modelGiaPhong.setValue(convertSizeRoomToPrice(cbb_PeopleNum.getSelectedItem() + "", "VIP"));
		else if (src.equals(rdbtnThng))
			modelGiaPhong.setValue(convertSizeRoomToPrice(cbb_PeopleNum.getSelectedItem() + "", "THƯỜNG"));
	}

	public static double convertSizeRoomToPrice(String numPeople, String loaiPhong) {
		if (loaiPhong.equalsIgnoreCase(Types.ROOM_TYPE_VIP)) {
			if (numPeople.equalsIgnoreCase("5 NGƯỜI")) {
				return 66000.0;
			} else if (numPeople.equalsIgnoreCase("10 NGƯỜI")) {
				return 110000.0;
			} else {
				return 220000.0;
			}
		} else {
			if (numPeople.equalsIgnoreCase("5 NGƯỜI")) {
				return 60000.0;
			} else if (numPeople.equalsIgnoreCase("10 NGƯỜI")) {
				return 100000.0;
			} else {
				return 200000.0;
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_PeopleNum)) {
			if (rdb_RoomVip.isSelected())
				modelGiaPhong.setValue(convertSizeRoomToPrice(cbb_PeopleNum.getSelectedItem() + "", "VIP"));
			else {
				modelGiaPhong.setValue(convertSizeRoomToPrice(cbb_PeopleNum.getSelectedItem() + "", "THƯỜNG"));
			}
		}

	}

}
