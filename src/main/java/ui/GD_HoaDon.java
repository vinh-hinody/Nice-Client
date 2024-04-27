package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import constant.Types;
import dao.PhongServer;
import entity.Phong;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class GD_HoaDon extends JPanel implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel pnl_ContainRooms;
	private JPanel pnl_33;
	private JPanel pnl_34;
	private JPanel pnl_35;
	private JLabel lbl_TitleMain;
	private JLabel lbl_Title;
	private JButton btn_RoomEmpty;
	private JButton btn_RoomUsing;
	private JLabel lblNewLabel_49;
	private JTextField txt_SearchNumberRoom;
	private JButton btn_Search;
	private JButton btn_RoomFix;
	private List<Phong> list;
	private List<Rectangle> listIndex;
	private int indexRoom;
	private JButton btn_Renew;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel pnl_SearchAndListRoom;
	private JScrollPane scr_ListRoom;
	private Container pnl_Rooms;
	private JPanel pnl_Center;
	private JPanel pnl_Main;
	private JPanel pnl_Status;
	private JLabel lbl_Message;
	private JRadioButton rdb_TypeAll, rdb_TypeThuong, rdb_TypeVip;
	private JRadioButton rdb_CountAll, rdb_Count5, rdb_Count10, rdb_Count20;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;

	private JPanel res;
	private JDialogHoaDon_ThanhToan thanhToan;
	private JDialogHoaDon_TimHoaDon timKiem;
	@SuppressWarnings("unused")
	private String staicRoom = Types.ROOM_STATIC_ALL;
	@SuppressWarnings("unused")
	private String roomType;
	@SuppressWarnings("unused")
	private String countPeople;
	private GD_DichVu homeDV;
	private GD_HoaDon homeHD;

	private PhongServer phong_Dao;
	
	private static final String URL = Types.URL;

	/**
	 * Create the panel.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public GD_HoaDon(GD_DichVu homeDV, GD_HoaDon homeHD)
			throws RemoteException, MalformedURLException, NotBoundException {

		phong_Dao = (PhongServer) Naming.lookup(URL + "PhongServer");

		setSize(new Dimension(1528, 746));
		setMinimumSize(new Dimension(1528, 746));
		setPreferredSize(new Dimension(1528, 746));
		setBounds(new Rectangle(0, 0, 1528, 746));
		listIndex = new ArrayList<Rectangle>();

		list = phong_Dao.getAll();

		setOpaque(false);
		setLayout(new BorderLayout(0, 4));

		pnl_Main = new JPanel();
		pnl_Main.setBorder(null);
		pnl_Main.setPreferredSize(new Dimension(300, 10));
		pnl_Main.setOpaque(false);
		add(pnl_Main, BorderLayout.CENTER);
		pnl_Main.setLayout(new BorderLayout(4, 4));

		pnl_SearchAndListRoom = new JPanel();
		pnl_Main.add(pnl_SearchAndListRoom, BorderLayout.CENTER);
		pnl_SearchAndListRoom.setOpaque(false);
		pnl_SearchAndListRoom.setBackground(Color.WHITE);
		pnl_SearchAndListRoom.setPreferredSize(new Dimension(10, 350));
		pnl_SearchAndListRoom.setLayout(new BorderLayout(4, 4));

		pnl_Center = new JPanel();
		pnl_Center.setOpaque(false);
		pnl_Center.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_Center.setBackground(new Color(0, 255, 127));
		pnl_SearchAndListRoom.add(pnl_Center, BorderLayout.CENTER);
		pnl_Center.setLayout(new BorderLayout(4, 4));

		JPanel pnl_TitleListRoom = new JPanel();
		pnl_TitleListRoom.setPreferredSize(new Dimension(10, 40));
		pnl_TitleListRoom.setBackground(Color.WHITE);
		pnl_Center.add(pnl_TitleListRoom, BorderLayout.NORTH);
		pnl_TitleListRoom.setLayout(new BorderLayout(4, 0));

		JPanel pnl_8 = new JPanel();
		pnl_8.setPreferredSize(new Dimension(400, 10));
		pnl_8.setBackground(Color.BLACK);
		pnl_TitleListRoom.add(pnl_8, BorderLayout.WEST);
		pnl_8.setLayout(new BorderLayout(0, 0));

		lbl_Title = new JLabel("DANH SÁCH PHÒNG");
		lbl_Title.setBorder(new LineBorder(Color.WHITE));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_Title.setForeground(Color.WHITE);
		pnl_8.add(lbl_Title);

		lbl_Message = new JLabel("");
		lbl_Message.setBorder(new EmptyBorder(0, 20, 0, 0));
		lbl_Message.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Message.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnl_TitleListRoom.add(lbl_Message, BorderLayout.CENTER);

		pnl_Rooms = new JPanel();
		((JComponent) pnl_Rooms).setOpaque(false);
		pnl_Center.add(pnl_Rooms, BorderLayout.CENTER);
		pnl_Rooms.setLayout(new BorderLayout(0, 0));

		scr_ListRoom = new JScrollPane();
		scr_ListRoom.setOpaque(false);
		scr_ListRoom.setPreferredSize(new Dimension(660, 764));
		pnl_Rooms.add(scr_ListRoom, BorderLayout.CENTER);

		pnl_ContainRooms = new JPanel();
		pnl_ContainRooms.setBackground(new Color(224, 255, 255));
		scr_ListRoom.setViewportView(pnl_ContainRooms);
		pnl_ContainRooms.setPreferredSize(new Dimension(70, 230 * (list.size() / 6) + 220));
		pnl_ContainRooms.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

//////////

//////////
		JPanel pnl_North = new JPanel();
		pnl_North.setOpaque(false);
		pnl_North.setBackground(new Color(255, 255, 255));
		pnl_North.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_North.setPreferredSize(new Dimension(10, 80));
		pnl_SearchAndListRoom.add(pnl_North, BorderLayout.NORTH);
		pnl_North.setLayout(new GridLayout(2, 1, 0, 4));

		JPanel pnl_Search = new JPanel();
		pnl_Search.setOpaque(false);
		pnl_Search.setBackground(Color.WHITE);
		pnl_North.add(pnl_Search);
		pnl_Search.setLayout(new BorderLayout(4, 0));

		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		pnl_Search.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_3.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(4, 0));

		lblNewLabel_1 = new JLabel("TÌNH TRẠNG");
		lblNewLabel_1.setPreferredSize(new Dimension(120, 0));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setBackground(new Color(255, 140, 0));
		panel_2.add(lblNewLabel_1, BorderLayout.WEST);

		panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new GridLayout(1, 3, 4, 0));

		pnl_33 = new JPanel();
		pnl_33.setOpaque(false);
		pnl_33.setBackground(new Color(255, 255, 255));
		pnl_33.setPreferredSize(new Dimension(600, 30));
		pnl_Search.add(pnl_33, BorderLayout.EAST);
		pnl_33.setLayout(new BorderLayout(5, 0));

		JLabel lblNewLabel_50 = new JLabel("PHỒNG SỐ");
		lblNewLabel_50.setBackground(new Color(255, 140, 0));
		lblNewLabel_50.setOpaque(true);
		lblNewLabel_50.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_50.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_50.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_50.setPreferredSize(new Dimension(120, 0));
		pnl_33.add(lblNewLabel_50, BorderLayout.WEST);

		txt_SearchNumberRoom = new JTextField();
		txt_SearchNumberRoom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txt_SearchNumberRoom.getText().length() <= 0)
						JOptionPane.showConfirmDialog(null,
								"Không tìm thấy phòng số: \"" + txt_SearchNumberRoom.getText() + "\" !", "Tìm mã phòng",
								JOptionPane.OK_CANCEL_OPTION);

				}
			}
		});
		txt_SearchNumberRoom.setBorder(new LineBorder(new Color(171, 173, 179)));
		txt_SearchNumberRoom.setHorizontalAlignment(SwingConstants.CENTER);
		txt_SearchNumberRoom.setFont(new Font("Dialog", Font.BOLD, 16));
		pnl_33.add(txt_SearchNumberRoom, BorderLayout.CENTER);
		txt_SearchNumberRoom.setColumns(10);

		btn_Search = new JButton("TÌM KIẾM");
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_SearchNumberRoom.getText().length() <= 0)
					JOptionPane.showConfirmDialog(null,
							"Không tìm thấy phòng số: \"" + txt_SearchNumberRoom.getText() + "\" !", "Tìm mã phòng",
							JOptionPane.OK_CANCEL_OPTION);

			}
		});
		pnl_33.add(btn_Search, BorderLayout.EAST);
		btn_Search.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_Search.setPreferredSize(new Dimension(120, 30));

		btn_RoomEmpty = new JButton("PHÒNG TRỐNG");
		btn_RoomEmpty.addActionListener(this);
		btn_RoomEmpty.setIcon(new ImageIcon(GD_Phong.class.getResource("/img/xanh_mini.png")));
		btn_RoomEmpty.setForeground(new Color(0, 191, 255));
		panel.add(btn_RoomEmpty);
		btn_RoomEmpty.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomEmpty.setBackground(Color.WHITE);
		btn_RoomEmpty.setPreferredSize(new Dimension(250, 30));

		btn_RoomUsing = new JButton("PHÒNG ĐANG SỬ DỤNG");
		btn_RoomUsing.addActionListener(this);
		btn_RoomUsing.setIcon(new ImageIcon(GD_Phong.class.getResource("/img/vang_mini.png")));
		btn_RoomUsing.setForeground(new Color(255, 69, 0));
		panel.add(btn_RoomUsing);
		btn_RoomUsing.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomUsing.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btn_RoomUsing.setPreferredSize(new Dimension(250, 30));

		btn_RoomFix = new JButton("PHÒNG ĐANG BẢO TRÌ");
		btn_RoomFix.addActionListener(this);
		btn_RoomFix.setIcon(new ImageIcon(GD_Phong.class.getResource("/img/do_mini.png")));
		btn_RoomFix.setForeground(new Color(255, 0, 0));
		panel.add(btn_RoomFix);
		btn_RoomFix.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomFix.setPreferredSize(new Dimension(250, 30));
		btn_RoomFix.setBorder(new LineBorder(Color.RED, 2, true));
		btn_RoomFix.setBackground(Color.WHITE);

		JPanel pnl_Filter = new JPanel();
		pnl_Filter.setOpaque(false);
		pnl_Filter.setBackground(Color.WHITE);
		pnl_North.add(pnl_Filter);
		pnl_Filter.setLayout(new GridLayout(1, 4, 4, 0));

		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		pnl_Filter.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel pnl_32 = new JPanel();
		pnl_32.setOpaque(false);
		panel_4.add(pnl_32);
		pnl_32.setPreferredSize(new Dimension(250, 30));
		pnl_32.setLayout(new BorderLayout(5, 0));

		lblNewLabel_49 = new JLabel("LOẠI PHÒNG");
		lblNewLabel_49.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_49.setBackground(new Color(255, 140, 0));
		lblNewLabel_49.setOpaque(true);
		lblNewLabel_49.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_49.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_49.setPreferredSize(new Dimension(120, 0));
		pnl_32.add(lblNewLabel_49, BorderLayout.WEST);

		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		pnl_32.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 0, 4, 0));

		ButtonGroup grType = new ButtonGroup();

		rdb_TypeAll = new JRadioButton("TẤT CẢ");
		rdb_TypeAll.addActionListener(this);
		grType.add(rdb_TypeAll);
		rdb_TypeAll.setSelected(true);
		rdb_TypeAll.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeAll.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_TypeAll.setBackground(Color.WHITE);
		panel_5.add(rdb_TypeAll);

		rdb_TypeVip = new JRadioButton("VIP");
		rdb_TypeVip.addActionListener(this);
		grType.add(rdb_TypeVip);
		rdb_TypeVip.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeVip.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_TypeVip.setBackground(Color.WHITE);
		panel_5.add(rdb_TypeVip);

		rdb_TypeThuong = new JRadioButton("THƯỜNG");
		rdb_TypeThuong.addActionListener(this);
		grType.add(rdb_TypeThuong);
		rdb_TypeThuong.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeThuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_TypeThuong.setBackground(Color.WHITE);
		panel_5.add(rdb_TypeThuong);

		pnl_34 = new JPanel();
		pnl_34.setOpaque(false);
		pnl_34.setBackground(new Color(255, 255, 255));
		pnl_34.setPreferredSize(new Dimension(400, 30));
		pnl_Filter.add(pnl_34);
		pnl_34.setLayout(new BorderLayout(5, 0));

		JLabel lbl_RoomType = new JLabel("LOẠI PHÒNG");
		lbl_RoomType.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_RoomType.setBackground(new Color(255, 140, 0));
		lbl_RoomType.setOpaque(true);
		lbl_RoomType.setPreferredSize(new Dimension(120, 0));
		lbl_RoomType.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_RoomType.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_34.add(lbl_RoomType, BorderLayout.WEST);

		pnl_35 = new JPanel();
		pnl_35.setOpaque(false);
		pnl_34.add(pnl_35, BorderLayout.CENTER);
		pnl_35.setLayout(new GridLayout(1, 0, 0, 0));

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_35.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 4, 0));

		ButtonGroup gr = new ButtonGroup();
		rdb_CountAll = new JRadioButton("TẤT CẢ");
		gr.add(rdb_CountAll);
		rdb_CountAll.addActionListener(this);
		rdb_CountAll.setBackground(Color.WHITE);
		rdb_CountAll.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_CountAll.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_CountAll.setSelected(true);
		panel_1.add(rdb_CountAll);

		rdb_Count5 = new JRadioButton("5 NGƯỜI");
		gr.add(rdb_Count5);
		rdb_Count5.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count5.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_Count5.setBackground(Color.WHITE);
		rdb_Count5.addActionListener(this);
		panel_1.add(rdb_Count5);

		rdb_Count10 = new JRadioButton("10 NGƯỜI");
		rdb_Count10.addActionListener(this);
		panel_1.add(rdb_Count10);
		rdb_Count10.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count10.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_Count10.setBackground(Color.WHITE);
		gr.add(rdb_Count10);

		rdb_Count20 = new JRadioButton("20 NGƯỜI");
		gr.add(rdb_Count20);
		rdb_Count20.addActionListener(this);
		rdb_Count20.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count20.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdb_Count20.setBackground(Color.WHITE);
		panel_1.add(rdb_Count20);

		btn_Renew = new JButton("LÀM MỚI");
		btn_Renew.addActionListener(this);
		pnl_34.add(btn_Renew, BorderLayout.EAST);
		btn_Renew.setPreferredSize(new Dimension(120, 30));
		btn_Renew.setFont(new Font("Dialog", Font.BOLD, 16));

		lbl_TitleMain = new JLabel("THANH TOÁN HÓA ĐƠN");
		lbl_TitleMain.setBackground(new Color(0, 0, 0));
		lbl_TitleMain.setPreferredSize(new Dimension(58, 40));
		lbl_TitleMain.setOpaque(true);
		pnl_Main.add(lbl_TitleMain, BorderLayout.NORTH);
		lbl_TitleMain.setBorder(new LineBorder(new Color(255, 255, 255)));
		lbl_TitleMain.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TitleMain.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_TitleMain.setForeground(Color.WHITE);
		this.list = phong_Dao.getAll();

		phong_Dao = (PhongServer) Naming.lookup(URL + "PhongServer");
		updateRoomView(phong_Dao.getAll());
	}

	public JPanel createViewRoom(Phong items, String tt) {
		res = new JPanel();
		res.setOpaque(false);
		res.setLayout(new BorderLayout(4, 4));

		JPanel pnl_Name = new JPanel();
		res.add(pnl_Name, BorderLayout.NORTH);
		pnl_Name.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(items.getTenP());
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setPreferredSize(new Dimension(35, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.GRAY);
		pnl_Name.add(lblNewLabel);

		JPanel pnl_Price = new JPanel();
		pnl_Price.setOpaque(false);
		res.add(pnl_Price, BorderLayout.SOUTH);
		pnl_Price.setLayout(new GridLayout(0, 2, 4, 0));

		JLabel lblGiPhng = new JLabel("GIÁ PHÒNG");
		lblGiPhng.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGiPhng.setPreferredSize(new Dimension(35, 30));
		lblGiPhng.setOpaque(true);
		lblGiPhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiPhng.setForeground(Color.WHITE);
		lblGiPhng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiPhng.setBackground(Color.GRAY);
		pnl_Price.add(lblGiPhng);

		JLabel lblGiPhng_1 = new JLabel(items.getGiaP() + "");
		lblGiPhng_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGiPhng_1.setPreferredSize(new Dimension(35, 30));
		lblGiPhng_1.setOpaque(true);
		lblGiPhng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiPhng_1.setForeground(Color.WHITE);
		lblGiPhng_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiPhng_1.setBackground(Color.GRAY);
		pnl_Price.add(lblGiPhng_1);

		pnl_Status = new JPanel();
		res.add(pnl_Status, BorderLayout.CENTER);
		pnl_Status.setLayout(new CardLayout(0, 0));

		JLabel lbl_Xanh = new JLabel("");
		lbl_Xanh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_Xanh.setBackground(Color.WHITE);
		lbl_Xanh.setOpaque(true);
		lbl_Xanh.setIcon(new ImageIcon(GD_HoaDon.class.getResource("/img/xanh.png")));
		lbl_Xanh.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_Status.add(lbl_Xanh, "name_xanh");

		JLabel lbl_Đo = new JLabel("");
		lbl_Đo.setIcon(new ImageIcon(GD_HoaDon.class.getResource("/img/do.png")));
		lbl_Đo.setOpaque(true);
		lbl_Đo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Đo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_Đo.setBackground(Color.WHITE);
		pnl_Status.add(lbl_Đo, "name_do");

		JLabel lbl_Vang = new JLabel("");
		lbl_Vang.setIcon(new ImageIcon(GD_HoaDon.class.getResource("/img/vang.png")));
		lbl_Vang.setOpaque(true);
		lbl_Vang.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vang.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_Vang.setBackground(Color.WHITE);
		pnl_Status.add(lbl_Vang, "name_vang");

		if (tt.equalsIgnoreCase("Đang sử dụng")) {
			CardLayout c = (CardLayout) pnl_Status.getLayout();
			c.show(pnl_Status, "name_vang");
		} else if (tt.equalsIgnoreCase("Trống")) {
			CardLayout c = (CardLayout) pnl_Status.getLayout();
			c.show(pnl_Status, "name_xanh");
		} else if (tt.equalsIgnoreCase("BẢO TRÌ")) {
			CardLayout c = (CardLayout) pnl_Status.getLayout();
			c.show(pnl_Status, "name_do");
		}

		return res;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int cot = 1; cot <= (int) list.size() / 6; cot++) {
			for (int hang = 1; hang <= 6; hang++) {
				Rectangle a = new Rectangle(51 + (hang - 1) * 250, 21 + (cot - 1) * 240, 200, 220);
				listIndex.add(a);
			}
		}

		Rectangle src = ((JPanel) e.getSource()).getBounds();
		indexRoom = listIndex.indexOf(src) + 1;
		Phong room = this.list.get(indexRoom - 1);

		if (lbl_TitleMain.getText().equals("THANH TOÁN HÓA ĐƠN")) {
			if (room.getTinhTrang().equals(Types.ROOM_STATIC_USING)) {
				try {
					if (thanhToan != null)
						thanhToan.hide();
					thanhToan = new JDialogHoaDon_ThanhToan(this, room);
					thanhToan.show();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (room.getTinhTrang().equalsIgnoreCase(Types.ROOM_STATIC_FIXING)) {
				JOptionPane.showMessageDialog(null, room.getTenP() + " CHƯA ĐANG ĐƯỢC SỬA CHỮA");
			} else if (room.getTinhTrang().equalsIgnoreCase(Types.ROOM_STATIC_EMPTY)) {
				JOptionPane.showMessageDialog(null, room.getTenP() + " CHƯA ĐƯỢC SỬ DỤNG");
			}
		} else if (lbl_TitleMain.getText().equals("TÌM KIẾM HÓA ĐƠN ĐẶT PHÒNG")) {
			if (timKiem != null)
				timKiem.show();
			else {
				timKiem = new JDialogHoaDon_TimHoaDon(homeDV, homeHD);
				timKiem.show();
			}
			if (thanhToan != null)
				thanhToan.hide();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void usingFeaturePaymentOrders() {
		changeTitleWhenPaymentOrders();

	}

	public void changeTitleWhenPaymentOrders() {
		lbl_TitleMain.setText("THANH TOÁN HÓA ĐƠN");

	}

	public void changeTitleWhenSearchOrders() {
		lbl_TitleMain.setText("TÌM KIẾM HÓA ĐƠN");
	}

	@SuppressWarnings("deprecation")
	public void usingFeatureSearchORders() {
		changeTitleWhenSearchOrders();
		if (timKiem != null)
			timKiem.show();
		else {
			timKiem = new JDialogHoaDon_TimHoaDon(this.homeDV, this.homeHD);
			timKiem.show();
		}
		if (thanhToan != null)
			thanhToan.hide();
	}

	public void usingFeatureFixOrdersBookRoom() {
		lbl_TitleMain.setText("SỬA HÓA ĐƠN ĐẶT PHÒNG");
		lbl_Title.setText("DANH SÁCH PHÒNG");
	}

	@SuppressWarnings("deprecation")
	public void updateRoomView(List<Phong> list) {
		this.list = list;
		listIndex.clear();

		if (this.list != null) {
			showMess("Danh sách phòng hiện tại");
			int hang = list.size() / 6;
			for (int i = 1; i <= hang; i++) {
				for (int y = 1; y <= 6; y++) {
					Rectangle a = new Rectangle(50 + (y - 1) * 250, 20 + (i - 1) * 240, 200, 220);
					listIndex.add(a);
				}
				if (i == hang) {
					for (int s = 1; s <= list.size() % 6; s++) {
						Rectangle a = new Rectangle(50 + (s - 1) * 250, 20 + (hang) * 240, 200, 220);
						listIndex.add(a);
					}
				}
			}
			if (hang == 0) {
				for (int s = 1; s <= list.size(); s++) {
					Rectangle a = new Rectangle(50 + (s - 1) * 250, 20 + (hang) * 240, 200, 220);
					listIndex.add(a);
				}
			}
			pnl_ContainRooms.hide();
			pnl_ContainRooms.show();
			pnl_ContainRooms.removeAll();
			pnl_ContainRooms.setPreferredSize(new Dimension(70, 230 * (list.size() / 6) + 270));
			pnl_ContainRooms.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

			for (Phong items : list) {
				JPanel panelItems = new JPanel(new BorderLayout());
				panelItems.setOpaque(false);
				panelItems.setPreferredSize(new Dimension(200, 220));
				panelItems.add(createViewRoom(items, items.getTinhTrang()));
				pnl_ContainRooms.add(panelItems);
				panelItems.addMouseListener(this);
			}
		} else {
			showMess("Không tìm thấy phòng phù hợp !");
		}
	}

	private void showMess(String mess) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lbl_Message.setText(mess);
					Thread.sleep(3000);
					lbl_Message.setText("");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (rdb_CountAll.equals(src) || rdb_Count10.equals(src) || rdb_Count20.equals(src) || rdb_Count5.equals(src)
				|| rdb_TypeAll.equals(src) || rdb_TypeThuong.equals(src) || rdb_TypeVip.equals(src)) {
			if (rdb_TypeAll.isSelected())
				roomType = Types.ROOM_TYPE_ALL;
			else if (rdb_TypeVip.isSelected())
				roomType = Types.ROOM_TYPE_VIP;
			else if (rdb_TypeThuong.isSelected())
				roomType = Types.ROOM_TYPE_NORMAL;

			if (rdb_CountAll.isSelected()) {
				countPeople = Types.ROOM_COUNT_PEOPLE_ALL;
			} else if (rdb_Count5.isSelected()) {
				countPeople = Types.ROOM_COUNT_PEOPLE_FIVE;
			} else if (rdb_Count10.isSelected()) {
				countPeople = Types.ROOM_COUNT_PEOPLE_TEN;
			} else if (rdb_Count20.isSelected()) {
				countPeople = Types.ROOM_COUNT_PEOPLE_TWENTY;
			}
//			updateRoomView(Phong_Dao.getRoomTypeAndCount(staicRoom, roomType, countPeople));
		}

		if (src.equals(btn_Renew)) {
			txt_SearchNumberRoom.setText("");
			rdb_TypeAll.setSelected(true);
			rdb_CountAll.setSelected(true);
			staicRoom = Types.ROOM_STATIC_ALL;
			roomType = Types.ROOM_TYPE_ALL;
			countPeople = Types.ROOM_COUNT_PEOPLE_ALL;
			try {
				phong_Dao = (PhongServer) Naming.lookup(URL + "PhongServer");
				updateRoomView(phong_Dao.getAll());
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
		} else if (src.equals(btn_RoomEmpty)) {
			staicRoom = Types.ROOM_STATIC_EMPTY;
//			updateRoomView(phong_Dao.getRoomTypeAndCount(staicRoom, Types.ROOM_TYPE_ALL, Types.ROOM_COUNT_PEOPLE_ALL));
			rdb_CountAll.setSelected(true);
			rdb_TypeAll.setSelected(true);
		} else if (src.equals(btn_RoomFix)) {
			staicRoom = Types.ROOM_STATIC_FIXING;
//			updateRoomView(Phong_Dao.getRoomsWithStatic(staicRoom));
			rdb_CountAll.setSelected(true);
			rdb_TypeAll.setSelected(true);
		} else if (src.equals(btn_RoomUsing)) {
			staicRoom = Types.ROOM_STATIC_USING;
//			updateRoomView(Phong_Dao.getRoomsWithStatic(staicRoom));
			rdb_CountAll.setSelected(true);
			rdb_TypeAll.setSelected(true);
		}

	}

}
