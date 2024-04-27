package ui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constant.Types;
import dao.LoaiDichVuServer;
import dao.LoaiSanPhamServer;
import dao.PhongServer;
import dao.SanPhamServer;
import entity.LoaiDichVu;
import entity.LoaiSanPham;
import entity.Phong;
import entity.SanPham;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;
import javax.swing.SpinnerModel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ScrollPaneConstants;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import java.awt.Scrollbar;

import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GD_DichVu extends JPanel implements MouseListener, DocumentListener, AdjustmentListener, ActionListener,
		ChangeListener, ListDataListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Object> listIndex;
	private List<Phong> list;
	private JRadioButton rdb_TypeAll;
	private JRadioButton rdb_TypeVip;
	private JRadioButton rdb_TypeNormal;
	private JButton btn_Renew;
	private JTextField txt_SearchNumberRoom;
	private JPanel pnl_ContainRooms;
	private int indexRoom;
	private JLabel lbl_TitleMain;
	private JLabel lbl_Title;
	private JPanel pnl_ServiceManagement;
	private JPanel pnl_BookService;
	private JTextField txt_Date;
	private JTextField txt_CountProduct;
	private JTextField txt_ProductCode;
	private JTable tbl_ProductList;
	private JLabel lbl_MainTitle2;
	private JComboBox<String> cbb_TypeService;
	private JLabel lbl_TitleListService;
	private JLabel lbl_TitleFeatureMng;
	private JButton btn_Feature;
	private Scrollbar scb_CountProduct;
	private JPanel res;
	private JPanel pnl_Status;
	private JComboBox<String> cbb_ProductType;
	private JComboBox<String> cbb_ProductName;
	private SpinnerModel spModel_PriceSale;
	private JSpinner sp_PriceSale;
	private SpinnerModel spModel_PriceBuy;
	private JSpinner sp_PriceBuy;
	private JComboBox<String> cbb_Status;
	private DefaultTableModel model;
	private JRadioButton rdb_AllCount;
	private JRadioButton rdb_Count5;
	private JRadioButton rdb_Count10;
	private JRadioButton rdb_Count20;
	private JLabel lbl_Message;
	private JButton btn_RoomFixing;
	private JButton btn_RoomUsing;
	private JButton btn_RoomEmpty;
	private JButton btn_SeachRoom;
	private JPanel pnl_West_AddService;
	private String maNV;

	private PhongServer p_dao;
	private SanPhamServer sp_dao;
	private LoaiSanPhamServer lsp_Dao;
	private LoaiDichVuServer ldv_Dao;
	private JButton btn_LamMoi;
	private static final String URL = Types.URL;

	/**
	 * Create the panel.
	 * 
	 * @param maNV
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public GD_DichVu(String maNV) throws MalformedURLException, RemoteException, NotBoundException {

		p_dao = (PhongServer) Naming.lookup(URL + "PhongServer");
		sp_dao = (SanPhamServer) Naming.lookup(URL + "SanPhamServer");
		lsp_Dao = (LoaiSanPhamServer) Naming.lookup(URL + "LoaiSanPhamServer");
		ldv_Dao = (LoaiDichVuServer) Naming.lookup(URL + "LoaiDichVuServer");

		listIndex = new ArrayList<>();

		setOpaque(false);
		setLayout(new CardLayout(0, 0));

		pnl_BookService = new JPanel();
		pnl_BookService.setPreferredSize(new Dimension(300, 10));
		pnl_BookService.setOpaque(false);
		add(pnl_BookService, "name_BookS");
		pnl_BookService.setLayout(new BorderLayout(4, 4));

		JPanel pnl_TitleMain = new JPanel();
		pnl_BookService.add(pnl_TitleMain, BorderLayout.NORTH);
		pnl_TitleMain.setBackground(Color.WHITE);
		pnl_TitleMain.setLayout(new GridLayout(0, 1, 0, 4));

		JPanel pnl_1 = new JPanel();
		pnl_1.setPreferredSize(new Dimension(10, 40));
		pnl_1.setBackground(Color.BLACK);
		pnl_TitleMain.add(pnl_1);
		pnl_1.setLayout(new BorderLayout(0, 4));

		lbl_TitleMain = new JLabel("DỊCH VỤ");
		lbl_TitleMain.setPreferredSize(new Dimension(41, 40));
		lbl_TitleMain.setBorder(new LineBorder(new Color(255, 255, 255)));
		lbl_TitleMain.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_1.add(lbl_TitleMain);
		lbl_TitleMain.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_TitleMain.setForeground(Color.WHITE);

		JPanel pnl_SearchAndListRoom = new JPanel();
		pnl_BookService.add(pnl_SearchAndListRoom, BorderLayout.CENTER);
		pnl_SearchAndListRoom.setOpaque(false);
		pnl_SearchAndListRoom.setBackground(Color.WHITE);
		pnl_SearchAndListRoom.setPreferredSize(new Dimension(10, 350));
		pnl_SearchAndListRoom.setLayout(new BorderLayout(4, 4));

		JPanel pnl_Center = new JPanel();
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

		lbl_Title = new JLabel("DANH SÁCH PHÒNG ĐANG SỬ DỤNG");
		lbl_Title.setBorder(new LineBorder(Color.WHITE));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_Title.setForeground(Color.WHITE);
		pnl_8.add(lbl_Title);

		lbl_Message = new JLabel("");
		lbl_Message.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Message.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Message.setBorder(new EmptyBorder(0, 20, 0, 0));
		pnl_TitleListRoom.add(lbl_Message, BorderLayout.CENTER);

		JPanel pnl_Rooms = new JPanel();
		pnl_Rooms.setOpaque(false);
		pnl_Center.add(pnl_Rooms, BorderLayout.CENTER);
		pnl_Rooms.setLayout(new BorderLayout(0, 0));

		JScrollPane scr_ListRoom = new JScrollPane();
		scr_ListRoom.setOpaque(false);
		scr_ListRoom.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr_ListRoom.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr_ListRoom.setPreferredSize(new Dimension(660, 764));
		pnl_Rooms.add(scr_ListRoom, BorderLayout.CENTER);

		pnl_ContainRooms = new JPanel();
		pnl_ContainRooms.setBackground(new Color(224, 255, 255));
		scr_ListRoom.setViewportView(pnl_ContainRooms);
		pnl_ContainRooms.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

//////////

//////////
		JPanel pnl_North = new JPanel();
		pnl_North.setOpaque(false);
		pnl_North.setPreferredSize(new Dimension(10, 80));
		pnl_SearchAndListRoom.add(pnl_North, BorderLayout.NORTH);
		pnl_North.setLayout(new GridLayout(2, 1, 0, 4));

		JPanel pnl_Search = new JPanel();
		pnl_Search.setPreferredSize(new Dimension(10, 40));
		pnl_Search.setOpaque(false);
		pnl_Search.setBackground(new Color(0, 0, 0));
		pnl_North.add(pnl_Search);
		pnl_Search.setLayout(new BorderLayout(4, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		pnl_Search.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(4, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_5.add(panel_11);
		panel_11.setLayout(new BorderLayout(4, 0));

		JLabel lblNewLabel_1_1_1 = new JLabel("TÌNH TRẠNG");
		lblNewLabel_1_1_1.setPreferredSize(new Dimension(120, 0));
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1_1_1.setBackground(new Color(255, 140, 0));
		panel_11.add(lblNewLabel_1_1_1, BorderLayout.WEST);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_11.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(1, 0, 5, 0));

		btn_RoomEmpty = new JButton("PHÒNG TRỐNG");
		btn_RoomEmpty.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/xanh_mini.png")));
		btn_RoomEmpty.setPreferredSize(new Dimension(250, 30));
		btn_RoomEmpty.setForeground(new Color(0, 191, 255));
		btn_RoomEmpty.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomEmpty.setBackground(Color.WHITE);
		btn_RoomEmpty.addActionListener(this);
		panel_12.add(btn_RoomEmpty);

		btn_RoomUsing = new JButton("PHÒNG ĐANG SỬ DỤNG");
		btn_RoomUsing.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/vang_mini.png")));
		btn_RoomUsing.setPreferredSize(new Dimension(250, 30));
		btn_RoomUsing.setForeground(new Color(255, 69, 0));
		btn_RoomUsing.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomUsing.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btn_RoomUsing.addActionListener(this);

		panel_12.add(btn_RoomUsing);

		btn_RoomFixing = new JButton("PHÒNG ĐANG BẢO TRÌ");
		btn_RoomFixing.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/do_mini.png")));
		btn_RoomFixing.setPreferredSize(new Dimension(250, 30));
		btn_RoomFixing.setForeground(Color.RED);
		btn_RoomFixing.setFont(new Font("Dialog", Font.BOLD, 14));
		btn_RoomFixing.setBorder(new LineBorder(Color.RED, 2, true));
		btn_RoomFixing.setBackground(Color.WHITE);
		btn_RoomFixing.addActionListener(this);
		panel_12.add(btn_RoomFixing);

		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		panel_13.setPreferredSize(new Dimension(600, 20));
		pnl_Search.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new BorderLayout(4, 0));

		btn_SeachRoom = new JButton("TÌM KIẾM");
		panel_13.add(btn_SeachRoom, BorderLayout.EAST);
		btn_SeachRoom.addActionListener(this);
		btn_SeachRoom.setBackground(UIManager.getColor("Button.light"));
		btn_SeachRoom.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_SeachRoom.setPreferredSize(new Dimension(120, 0));

		JPanel panel_1 = new JPanel();
		panel_13.add(panel_1, BorderLayout.CENTER);
		panel_1.setOpaque(false);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setLayout(new BorderLayout(4, 0));

		JLabel lblNewLabel_1_1 = new JLabel("SỐ PHÒNG");
		lblNewLabel_1_1.setBackground(new Color(255, 140, 0));
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1_1.setPreferredSize(new Dimension(120, 0));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_1, BorderLayout.WEST);

		txt_SearchNumberRoom = new JTextField();
		txt_SearchNumberRoom.setPreferredSize(new Dimension(500, 30));
		txt_SearchNumberRoom.setHorizontalAlignment(SwingConstants.CENTER);
		txt_SearchNumberRoom.setFont(new Font("Dialog", Font.BOLD, 16));
		txt_SearchNumberRoom.setColumns(10);
		panel_1.add(txt_SearchNumberRoom, BorderLayout.CENTER);

		JPanel pnl_Filter = new JPanel();
		pnl_Filter.setOpaque(false);
		pnl_Filter.setBackground(new Color(0, 0, 0));
		pnl_North.add(pnl_Filter);
		pnl_Filter.setLayout(new BorderLayout(4, 0));

		btn_Renew = new JButton("LÀM MỚI");
		btn_Renew.setBackground(UIManager.getColor("Button.highlight"));
		btn_Renew.addActionListener(this);
		btn_Renew.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_Renew.setPreferredSize(new Dimension(120, 0));
		pnl_Filter.add(btn_Renew, BorderLayout.EAST);

		JPanel pnl_34 = new JPanel();
		pnl_34.setOpaque(false);
		pnl_34.setPreferredSize(new Dimension(400, 30));
		pnl_Filter.add(pnl_34, BorderLayout.CENTER);
		pnl_34.setLayout(new BorderLayout(4, 0));

		JPanel panel_3 = new JPanel();
		pnl_34.add(panel_3);
		panel_3.setOpaque(false);
		panel_3.setBackground(new Color(255, 140, 0));
		panel_3.setLayout(new BorderLayout(4, 0));

		JLabel lblNewLabel_3_2 = new JLabel("LOẠI PHÒNG");
		lblNewLabel_3_2.setBackground(new Color(255, 140, 0));
		lblNewLabel_3_2.setOpaque(true);
		lblNewLabel_3_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_3_2.setPreferredSize(new Dimension(120, 0));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblNewLabel_3_2, BorderLayout.WEST);

		JPanel pnl_35 = new JPanel();
		pnl_35.setOpaque(false);
		pnl_35.setBackground(Color.WHITE);
		panel_3.add(pnl_35, BorderLayout.CENTER);
		pnl_35.setLayout(new GridLayout(1, 0, 4, 0));

		ButtonGroup btnGroup = new ButtonGroup();

		rdb_TypeAll = new JRadioButton(Types.ROOM_TYPE_ALL);
		rdb_TypeAll.addActionListener(this);
		rdb_TypeAll.setBackground(Color.WHITE);
		rdb_TypeAll.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnGroup.add(rdb_TypeAll);
		rdb_TypeAll.setSelected(true);
		rdb_TypeAll.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeAll.setFont(new Font("Dialog", Font.BOLD, 16));
		pnl_35.add(rdb_TypeAll);

		rdb_TypeVip = new JRadioButton(Types.ROOM_TYPE_VIP);
		rdb_TypeVip.addActionListener(this);
		rdb_TypeVip.setBackground(Color.WHITE);
		rdb_TypeVip.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnGroup.add(rdb_TypeVip);
		rdb_TypeVip.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeVip.setFont(new Font("Dialog", Font.BOLD, 16));
		pnl_35.add(rdb_TypeVip);

		rdb_TypeNormal = new JRadioButton(Types.ROOM_TYPE_NORMAL);
		rdb_TypeNormal.addActionListener(this);
		rdb_TypeNormal.setBackground(Color.WHITE);
		rdb_TypeNormal.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnGroup.add(rdb_TypeNormal);
		rdb_TypeNormal.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_TypeNormal.setFont(new Font("Dialog", Font.BOLD, 16));
		pnl_35.add(rdb_TypeNormal);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(750, 10));
		panel_4.setOpaque(false);
		pnl_34.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BorderLayout(4, 0));

		JLabel lblNewLabel_3_1 = new JLabel("SỐ NGƯỜI");
		lblNewLabel_3_1.setBackground(new Color(255, 140, 0));
		lblNewLabel_3_1.setOpaque(true);
		lblNewLabel_3_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_3_1.setPreferredSize(new Dimension(120, 0));
		panel_4.add(lblNewLabel_3_1, BorderLayout.WEST);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.BOLD, 16));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBackground(Color.WHITE);
		panel_4.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 1, 4, 0));

		ButtonGroup gr = new ButtonGroup();

		rdb_AllCount = new JRadioButton(Types.ROOM_COUNT_PEOPLE_ALL);
		rdb_AllCount.addActionListener(this);
		gr.add(rdb_AllCount);
		rdb_AllCount.setSelected(true);
		rdb_AllCount.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_AllCount.setFont(new Font("Dialog", Font.BOLD, 16));
		rdb_AllCount.setBorder(new LineBorder(new Color(0, 0, 0)));
		rdb_AllCount.setBackground(Color.WHITE);
		panel_2.add(rdb_AllCount);

		rdb_Count5 = new JRadioButton(Types.ROOM_COUNT_PEOPLE_FIVE);
		rdb_Count5.addActionListener(this);
		gr.add(rdb_Count5);
		rdb_Count5.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count5.setFont(new Font("Dialog", Font.BOLD, 16));
		rdb_Count5.setBorder(new LineBorder(new Color(0, 0, 0)));
		rdb_Count5.setBackground(Color.WHITE);
		panel_2.add(rdb_Count5);

		rdb_Count10 = new JRadioButton(Types.ROOM_COUNT_PEOPLE_TEN);
		rdb_Count10.addActionListener(this);
		gr.add(rdb_Count10);
		rdb_Count10.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count10.setFont(new Font("Dialog", Font.BOLD, 16));
		rdb_Count10.setBorder(new LineBorder(new Color(0, 0, 0)));
		rdb_Count10.setBackground(Color.WHITE);
		panel_2.add(rdb_Count10);

		rdb_Count20 = new JRadioButton(Types.ROOM_COUNT_PEOPLE_TWENTY);
		rdb_Count20.addActionListener(this);
		gr.add(rdb_Count20);
		rdb_Count20.setHorizontalAlignment(SwingConstants.CENTER);
		rdb_Count20.setFont(new Font("Dialog", Font.BOLD, 16));
		rdb_Count20.setBorder(new LineBorder(new Color(0, 0, 0)));
		rdb_Count20.setBackground(Color.WHITE);
		panel_2.add(rdb_Count20);

		pnl_ServiceManagement = new JPanel();
		pnl_ServiceManagement.setBorder(null);
		pnl_ServiceManagement.setOpaque(false);
		pnl_ServiceManagement.setBackground(new Color(222, 184, 135));
		add(pnl_ServiceManagement, "name_Smng");
		pnl_ServiceManagement.setLayout(new BorderLayout(4, 4));

		pnl_West_AddService = new JPanel();
		pnl_West_AddService.setOpaque(false);
		pnl_West_AddService.setBorder(new EmptyBorder(0, 4, 0, 0));
		pnl_West_AddService.setPreferredSize(new Dimension(400, 10));
		pnl_West_AddService.setBackground(Color.WHITE);
		pnl_ServiceManagement.add(pnl_West_AddService, BorderLayout.WEST);
		pnl_West_AddService.setLayout(new BorderLayout(4, 4));

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		pnl_West_AddService.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(4, 4));

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel.add(panel_7, BorderLayout.CENTER);
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setLayout(new BorderLayout(4, 4));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_8.setBackground(Color.WHITE);
		panel_8.setPreferredSize(new Dimension(150, 10));
		panel_7.add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new GridLayout(0, 1, 0, 5));

		JLabel lbl_LoaiDichVu = new JLabel("LOẠI DỊCH VỤ");
		panel_8.add(lbl_LoaiDichVu);
		lbl_LoaiDichVu.setBorder(new LineBorder(new Color(255, 255, 255)));
		lbl_LoaiDichVu.setBackground(new Color(0, 0, 0));
		lbl_LoaiDichVu.setOpaque(true);
		lbl_LoaiDichVu.setForeground(Color.WHITE);
		lbl_LoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_LoaiDichVu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_LoaiDichVu.setPreferredSize(new Dimension(200, 30));

		JLabel lblNewLabel_4 = new JLabel("Mã sản phẩm");
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4);

		JLabel lblNewLabel_4_6 = new JLabel("Loại sản phẩm");
		lblNewLabel_4_6.setBackground(Color.WHITE);
		lblNewLabel_4_6.setOpaque(true);
		lblNewLabel_4_6.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_6);

		JLabel lblNewLabel_4_5 = new JLabel("Tên sản phẩm");
		lblNewLabel_4_5.setBackground(Color.WHITE);
		lblNewLabel_4_5.setOpaque(true);
		lblNewLabel_4_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_5);

		JLabel lblNewLabel_4_1 = new JLabel("Số lượng");
		lblNewLabel_4_1.setBackground(Color.WHITE);
		lblNewLabel_4_1.setOpaque(true);
		lblNewLabel_4_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Giá nhập");
		lblNewLabel_4_2.setBackground(Color.WHITE);
		lblNewLabel_4_2.setOpaque(true);
		lblNewLabel_4_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_3 = new JLabel("Giá bán");
		lblNewLabel_4_3.setBackground(Color.WHITE);
		lblNewLabel_4_3.setOpaque(true);
		lblNewLabel_4_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_4 = new JLabel("Tình trạng");
		lblNewLabel_4_4.setBackground(Color.WHITE);
		lblNewLabel_4_4.setOpaque(true);
		lblNewLabel_4_4.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_4);

		JLabel lblNewLabel_4_3_1 = new JLabel("Ngày nhập");
		lblNewLabel_4_3_1.setBackground(Color.WHITE);
		lblNewLabel_4_3_1.setOpaque(true);
		lblNewLabel_4_3_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_4_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8.add(lblNewLabel_4_3_1);

		JPanel panel_8_1 = new JPanel();
		panel_8_1.setOpaque(false);
		panel_8_1.setPreferredSize(new Dimension(100, 10));
		panel_8_1.setBackground(Color.WHITE);
		panel_7.add(panel_8_1, BorderLayout.CENTER);
		panel_8_1.setLayout(new GridLayout(0, 1, 0, 5));

		cbb_TypeService = new JComboBox<String>();
		panel_8_1.add(cbb_TypeService);
		cbb_TypeService.setBorder(new LineBorder(new Color(0, 0, 0)));
		cbb_TypeService.setBackground(new Color(255, 215, 0));
		cbb_TypeService.setForeground(Color.BLUE);
		cbb_TypeService.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbb_TypeService.addItem("ĐỒ ĂN");
		cbb_TypeService.addItem("ĐỒ UỐNG");
		cbb_TypeService.addActionListener(this);

		txt_ProductCode = new JTextField();
		txt_ProductCode.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ProductCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_ProductCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_ProductCode.setColumns(10);
		panel_8_1.add(txt_ProductCode);

		cbb_ProductType = new JComboBox<String>();
		cbb_ProductType.addActionListener(this);
		cbb_ProductType.setBorder(new LineBorder(new Color(0, 0, 0)));
		cbb_ProductType.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbb_ProductType.setEditable(true);
		panel_8_1.add(cbb_ProductType);

		cbb_ProductName = new JComboBox<String>();
		cbb_ProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbb_ProductName.addItem("<Nhập tên sản phẩm>");
		cbb_ProductName.setEditable(true);
		cbb_ProductName.addActionListener(this);
		panel_8_1.add(cbb_ProductName);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_9.setBackground(Color.WHITE);
		panel_8_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(4, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setPreferredSize(new Dimension(60, 10));
		panel_9.add(panel_10, BorderLayout.EAST);
		panel_10.setLayout(new BorderLayout(0, 0));

		txt_CountProduct = new JTextField("1");
		txt_CountProduct.setEnabled(false);
		txt_CountProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_CountProduct.setHorizontalAlignment(SwingConstants.CENTER);
		txt_CountProduct.getDocument().addDocumentListener(this);
		txt_CountProduct.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_CountProduct.getDocument().addDocumentListener(this);
		panel_10.add(txt_CountProduct, BorderLayout.CENTER);
		txt_CountProduct.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.setBackground(new Color(0, 0, 0));
		panel_9.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		scb_CountProduct = new Scrollbar();
		scb_CountProduct.setEnabled(false);
		panel_6.add(scb_CountProduct);
		scb_CountProduct.setIgnoreRepaint(true);
		scb_CountProduct.setBackground(Color.WHITE);
		scb_CountProduct.setVisibleAmount(1);
		scb_CountProduct.setMinimum(1);
		scb_CountProduct.setMaximum(9999);
		scb_CountProduct.addAdjustmentListener(this);
		scb_CountProduct.setOrientation(Scrollbar.HORIZONTAL);

		sp_PriceBuy = new JSpinner();
		sp_PriceBuy.setEnabled(false);
		spModel_PriceBuy = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
				Integer.valueOf(1000));
		sp_PriceBuy.setModel(spModel_PriceBuy);
		sp_PriceBuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		sp_PriceBuy.setBorder(new LineBorder(new Color(0, 0, 0)));
		sp_PriceBuy.addChangeListener(this);

		panel_8_1.add(sp_PriceBuy);

		sp_PriceSale = new JSpinner();
		sp_PriceSale.setEnabled(false);
		spModel_PriceSale = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
				Integer.valueOf(1000));
		sp_PriceSale.setModel(spModel_PriceSale);
		sp_PriceSale.setFont(new Font("Tahoma", Font.BOLD, 16));
		sp_PriceSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		sp_PriceSale.addChangeListener(this);
		panel_8_1.add(sp_PriceSale);

		cbb_Status = new JComboBox<String>();
		cbb_Status.setEnabled(false);
		cbb_Status.addItem(Types.PRODUCT_STATIC_STOCKING);
		cbb_Status.addItem(Types.PRODUCT_STATIC_OUTSTOCK);
		cbb_Status.setBorder(new LineBorder(new Color(0, 0, 0)));
		cbb_Status.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8_1.add(cbb_Status);

		txt_Date = new JTextField(now.format(df));
		txt_Date.setEditable(false);
		txt_Date.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Date.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_Date.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_8_1.add(txt_Date);
		txt_Date.setColumns(10);

		lbl_TitleFeatureMng = new JLabel("NHẬP THÔNG TIN DỊCH VỤ");
		lbl_TitleFeatureMng.setBackground(new Color(0, 0, 0));
		lbl_TitleFeatureMng.setOpaque(true);
		pnl_West_AddService.add(lbl_TitleFeatureMng, BorderLayout.NORTH);
		lbl_TitleFeatureMng.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		lbl_TitleFeatureMng.setPreferredSize(new Dimension(200, 30));
		lbl_TitleFeatureMng.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TitleFeatureMng.setForeground(Color.WHITE);
		lbl_TitleFeatureMng.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel pnl_Center_AddService = new JPanel();
		pnl_Center_AddService.setBorder(new EmptyBorder(0, 0, 0, 4));
		pnl_Center_AddService.setOpaque(false);
		pnl_Center_AddService.setBackground(Color.WHITE);
		pnl_ServiceManagement.add(pnl_Center_AddService, BorderLayout.CENTER);
		pnl_Center_AddService.setLayout(new BorderLayout(4, 4));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btn_Feature.getText().equalsIgnoreCase("LƯU")) {
					if (tbl_ProductList.getSelectedRow() > 0) {
						btn_Feature.setEnabled(true);
					}
				}
			}
		});
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new LineBorder(new Color(0, 191, 255), 4, true));
		scrollPane.setBackground(Color.WHITE);
		pnl_Center_AddService.add(scrollPane, BorderLayout.CENTER);

		tbl_ProductList = new JTable();
		tbl_ProductList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00C3 S\u1EA2N PH\u1EA8M", "LO\u1EA0I S\u1EA2N PH\u1EA8M",
						"T\u00CAN S\u1EA2N PH\u1EA8M", "S\u1ED0 L\u01AF\u1EE2NG", "GI\u00C1 NH\u1EACP",
						"GI\u00C1 B\u00C1N", "T\u00CCNH TR\u1EA0NG", "NG\u00C0Y NH\u1EACP" });
		tbl_ProductList.setModel(model);
		tbl_ProductList.getColumnModel().getColumn(0).setPreferredWidth(1);
		tbl_ProductList.getColumnModel().getColumn(1).setPreferredWidth(1);
		tbl_ProductList.getColumnModel().getColumn(2).setPreferredWidth(1);
		tbl_ProductList.getColumnModel().getColumn(3).setPreferredWidth(3);
		tbl_ProductList.getColumnModel().getColumn(4).setPreferredWidth(1);
		tbl_ProductList.getColumnModel().getColumn(5).setPreferredWidth(2);
		tbl_ProductList.getColumnModel().getColumn(6).setPreferredWidth(2);
		tbl_ProductList.getColumnModel().getColumn(7).setPreferredWidth(1);
		tbl_ProductList.setRowHeight(23);
		scrollPane.setViewportView(tbl_ProductList);

		lbl_TitleListService = new JLabel("CÁC DỊCH VỤ HIỆN TẠI");
		lbl_TitleListService.setBackground(new Color(0, 0, 0));
		lbl_TitleListService.setOpaque(true);
		pnl_Center_AddService.add(lbl_TitleListService, BorderLayout.NORTH);
		lbl_TitleListService.setBorder(new LineBorder(new Color(255, 255, 255)));
		lbl_TitleListService.setPreferredSize(new Dimension(250, 30));
		lbl_TitleListService.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TitleListService.setForeground(Color.WHITE);
		lbl_TitleListService.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel pnl_Feature = new JPanel();
		pnl_Feature.setBorder(new LineBorder(Color.WHITE));
		pnl_Feature.setBackground(new Color(0, 0, 0));
		FlowLayout fl_pnl_Feature = (FlowLayout) pnl_Feature.getLayout();
		fl_pnl_Feature.setVgap(2);
		fl_pnl_Feature.setHgap(20);
		fl_pnl_Feature.setAlignment(FlowLayout.LEADING);
		pnl_ServiceManagement.add(pnl_Feature, BorderLayout.SOUTH);

		btn_Feature = new JButton("THÊM");
		btn_Feature.setEnabled(false);
		btn_Feature.addActionListener(this);
		btn_Feature.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Feature.setPreferredSize(new Dimension(200, 40));
		pnl_Feature.add(btn_Feature);

		JButton btnNewButton_4 = new JButton("HỦY");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setPreferredSize(new Dimension(200, 40));
		pnl_Feature.add(btnNewButton_4);

		btn_LamMoi = new JButton("LÀM MỚI");
		btn_LamMoi.addActionListener(this);
		btn_LamMoi.setPreferredSize(new Dimension(200, 40));
		btn_LamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnl_Feature.add(btn_LamMoi);

		lbl_MainTitle2 = new JLabel("THÊM DỊCH VỤ");
		lbl_MainTitle2.setBackground(new Color(0, 0, 0));
		lbl_MainTitle2.setOpaque(true);
		pnl_ServiceManagement.add(lbl_MainTitle2, BorderLayout.NORTH);
		lbl_MainTitle2.setBorder(new LineBorder(new Color(255, 255, 255)));
		lbl_MainTitle2.setForeground(Color.WHITE);
		lbl_MainTitle2.setPreferredSize(new Dimension(71, 40));
		lbl_MainTitle2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_MainTitle2.setHorizontalAlignment(SwingConstants.CENTER);

		lamMoi();
		updateView_DanhSachSP();
		cbb_ProductType.getModel().addListDataListener(this);
	}

	@SuppressWarnings("deprecation")
	public void updateRoomView(List<Phong> list) {
		this.list = list;
		listIndex.clear();
		if (this.list != null) {
			int hang = this.list.size() / 6;
			for (int i = 1; i <= hang; i++) {
				for (int y = 1; y <= 6; y++) {
					Rectangle a = new Rectangle(50 + (y - 1) * 250, 20 + (i - 1) * 240, 200, 220);
					listIndex.add(a);
				}
				if (i == hang) {
					for (int s = 1; s <= this.list.size() % 6; s++) {
						Rectangle a = new Rectangle(50 + (s - 1) * 250, 20 + (hang) * 240, 200, 220);
						listIndex.add(a);
					}
				}
			}
			if (hang == 0) {
				for (int s = 1; s <= this.list.size(); s++) {
					Rectangle a = new Rectangle(50 + (s - 1) * 250, 20 + (hang) * 240, 200, 220);
					listIndex.add(a);
				}
			}

			pnl_ContainRooms.removeAll();
			pnl_ContainRooms.setPreferredSize(new Dimension(70, 230 * (this.list.size() / 6) + 270));
			pnl_ContainRooms.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
			pnl_ContainRooms.hide();
			pnl_ContainRooms.show();

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
		lbl_Xanh.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/xanh.png")));
		lbl_Xanh.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_Status.add(lbl_Xanh, "name_xanh");

		JLabel lbl_Đo = new JLabel("");
		lbl_Đo.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/do.png")));
		lbl_Đo.setOpaque(true);
		lbl_Đo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Đo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_Đo.setBackground(Color.WHITE);
		pnl_Status.add(lbl_Đo, "name_do");

		JLabel lbl_Vang = new JLabel("");
		lbl_Vang.setIcon(new ImageIcon(GD_DichVu.class.getResource("/img/vang.png")));
		lbl_Vang.setOpaque(true);
		lbl_Vang.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vang.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_Vang.setBackground(Color.WHITE);
		pnl_Status.add(lbl_Vang, "name_vang");

		if (tt.equalsIgnoreCase("ĐANG SỬ DỤNG")) {
			CardLayout c = (CardLayout) pnl_Status.getLayout();
			c.show(pnl_Status, "name_vang");
		} else if (tt.equalsIgnoreCase("TRỐNG")) {
			CardLayout c = (CardLayout) pnl_Status.getLayout();
			c.show(pnl_Status, "name_xanh");
		} else {
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
		Phong a = this.list.get(indexRoom - 1);

		if (lbl_TitleMain.getText().equalsIgnoreCase("ĐẶT ĐỒ ĂN")) {
			if (a.getTinhTrang().equalsIgnoreCase(Types.ROOM_STATIC_USING)) {
				try {
					JDialogDichVu_DatDichVu datDoAn;
					datDoAn = new JDialogDichVu_DatDichVu(this, a.getTenP(), Types.PRODUCT_FOOD, "",
							GD_Chinh.getNVWorking());
					datDoAn.show();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null, "Phòng này chưa có người !");

		} else if (lbl_TitleMain.getText().equalsIgnoreCase("ĐẶT NƯỚC UỐNG")) {
			if (a.getTinhTrang().equalsIgnoreCase(Types.ROOM_STATIC_USING)) {
				try {
					JDialogDichVu_DatDichVu datDoUong = new JDialogDichVu_DatDichVu(this, a.getTenP(),
							Types.PRODUCT_DRINK, "", GD_Chinh.getNVWorking());
					datDoUong.show();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null, "Phòng này chưa có người !");
		} else if (lbl_TitleMain.getText().equals("SỬA ĐẶT DỊCH VỤ")) {
			int choose = JOptionPane.showConfirmDialog(null, "Bạn cần sửa đồ ăn ?", "Chọn sửa dịch vụ",
					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {

				try {
					JDialogDichVu_DatDichVu an = new JDialogDichVu_DatDichVu(this, a.getTenP(), Types.PRODUCT_FOOD, "",
							GD_Chinh.getNVWorking());
					an.show();
					if (!an.isShowing()) {
						int n = JOptionPane.showConfirmDialog(null, "Bạn cần sửa đồ uống ?", "Sửa dịch vụ",
								JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							JDialogDichVu_DatDichVu datDoUong = new JDialogDichVu_DatDichVu(this, a.getTenP(),
									Types.PRODUCT_DRINK, "", maNV);
							datDoUong.show();
						}
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				try {
					JDialogDichVu_DatDichVu datDoUong = new JDialogDichVu_DatDichVu(this, a.getTenP(),
							Types.PRODUCT_DRINK, "", GD_Chinh.getNVWorking());
					datDoUong.show();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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

	public void changeTitleWhenSearchService() {

		btn_Feature.setText("CHỌN");
		lbl_TitleFeatureMng.setText("THÔNG TIN ĐỒ ĂN");
		lbl_MainTitle2.setText("TÌM DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("KẾT QUẢ TÌM KIẾM");
	}

	public void changeTitleWhenFixService() {
		btn_Feature.setText("LƯU");
		lbl_TitleFeatureMng.setText("THÔNG TIN DỊCH VỤ");
		lbl_MainTitle2.setText("SỬA DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("DANH SÁCH DỊCH VỤ");
	}

	public void changeTitleWhenBookFood() {
		lbl_TitleMain.setText("ĐẶT ĐỒ ĂN");
		lbl_Title.setText("DANH SÁCH PHÒNG ĐANG SỬ DỤNG");
	}

	public void changeTitleWhenBookWater() {
		lbl_TitleMain.setText("ĐẶT NƯỚC UỐNG");
		lbl_Title.setText("DANH SÁCH PHÒNG ĐANG SỬ DỤNG");

	}

	public void changeTitleWhenFixFood() {
		cbb_TypeService.setSelectedIndex(0);
		btn_Feature.setText("SỬA");
		lbl_TitleFeatureMng.setText("THÔNG TIN SẢN PHẨM");
		lbl_MainTitle2.setText("SỬA DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("DANH SÁCH DỊCH VỤ");
	}

	public void changeTitleWhenFixWater() {
		cbb_TypeService.setSelectedIndex(1);
		btn_Feature.setText("SỬA");
		lbl_TitleFeatureMng.setText("THÔNG TIN ĐỒ UỐNG");
		lbl_MainTitle2.setText("SỬA DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("DANH SÁCH ĐỒ UỐNG");
	}

	public void changeTitleWhenAddService() {
		cbb_TypeService.setSelectedIndex(0);
		btn_Feature.setText("THÊM");
		lbl_TitleFeatureMng.setText("NHẬP THÔNG TIN DỊCH VỤ");
		lbl_MainTitle2.setText("THÊM DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("DANH SÁCH DỊCH VỤ");

	}

	public void changeTitleWhenAddWater() {
		cbb_TypeService.setSelectedIndex(1);
		btn_Feature.setText("THÊM");
		lbl_TitleFeatureMng.setText("NHẬP THÔNG TIN DỊCH VỤ");
		lbl_MainTitle2.setText("THÊM DỊCH VỤ");
		lbl_Title.setText("LOẠI DỊCH VỤ");
		lbl_TitleListService.setText("DANH SÁCH DỊCH VỤ");

	}

	public void changeScreenManagentService() {
		CardLayout c = (CardLayout) (this.getLayout());
		c.show(this, "name_Smng");
	}

	public void changeScreenBookService() {
		CardLayout c = (CardLayout) (this.getLayout());
		c.show(this, "name_BookS");
	}

	public void changeTitleWhenFixBookService() {
		lbl_TitleMain.setText("SỬA ĐẶT DỊCH VỤ");
		lbl_Title.setText("CHỌN PHÒNG ĐỂ SỬA");
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		Object src = e.getDocument();

		if (src.equals(txt_CountProduct.getDocument())) {

		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Object src = e.getDocument();

		if (src.equals(txt_CountProduct.getDocument())) {

		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		Object src = e.getDocument();

		if (src.equals(txt_CountProduct.getDocument())) {

		}
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		txt_CountProduct.setText(scb_CountProduct.getValue() + "");

	}

	public void setValueForComponentWhenAddService() throws RemoteException {
		String code = sp_dao.getAll().size() + 1 + "";
		txt_ProductCode.setText("SP" + code);

	}

	@SuppressWarnings("unused")
	private String staicRoom = Types.ROOM_STATIC_ALL;
	@SuppressWarnings("unused")
	private String roomType = Types.ROOM_TYPE_ALL;
	@SuppressWarnings("unused")
	private String countPeople = Types.ROOM_COUNT_PEOPLE_ALL;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		// Search room
		if (rdb_AllCount.equals(src) || rdb_Count10.equals(src) || rdb_Count20.equals(src) || rdb_Count5.equals(src)
				|| rdb_TypeAll.equals(src) || rdb_TypeNormal.equals(src) || rdb_TypeVip.equals(src)) {
			if (rdb_TypeAll.isSelected())
				roomType = Types.ROOM_TYPE_ALL;
			else if (rdb_TypeVip.isSelected())
				roomType = Types.ROOM_TYPE_VIP;
			else if (rdb_TypeNormal.isSelected())
				roomType = Types.ROOM_TYPE_NORMAL;

			if (rdb_AllCount.isSelected()) {
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
		if (src.equals(btn_LamMoi)) {
			lamMoi();
		} else if (src.equals(cbb_TypeService) && btn_Feature.getText().equalsIgnoreCase("THÊM")) {
			String item = cbb_TypeService.getSelectedItem() + "";
			try {
				item = searchService_ServiceID(item);
				setProductType(item);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (src.equals(cbb_ProductType) && btn_Feature.getText().equalsIgnoreCase("THÊM")) {
			String item = cbb_ProductType.getSelectedItem() + "";
			if (!item.equalsIgnoreCase("<Nhập loại sản phẩm>")) {
				cbb_ProductName.setEnabled(true);
			} else {
				resetValueInput();
				cbb_ProductName.setEnabled(false);
				cbb_Status.setEnabled(false);
				scb_CountProduct.setEnabled(false);
				txt_CountProduct.setEnabled(false);
			}
			try {
				item = searchTypeProduct_ID(item);
				setNameProduct(item);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if (src.equals(cbb_ProductName) && btn_Feature.getText().equalsIgnoreCase("THÊM")) {
			String item = cbb_ProductName.getSelectedItem() + "";
			if (!item.equalsIgnoreCase("<Nhập tên sản phẩm>")) {
				scb_CountProduct.setEnabled(true);
				txt_CountProduct.setEnabled(true);
				cbb_Status.setEnabled(true);
				sp_PriceBuy.setEnabled(true);
				sp_PriceSale.setEnabled(true);
				btn_Feature.setEnabled(true);
			} else {
				scb_CountProduct.setEnabled(false);
				txt_CountProduct.setEnabled(false);
				cbb_Status.setEnabled(false);
				sp_PriceBuy.setEnabled(false);
				sp_PriceSale.setEnabled(false);

			}
		} else if (src.equals(btn_Feature) && btn_Feature.getText().equalsIgnoreCase("THÊM")) {
			// THÊM SẢN PHẨM
			boolean res = addProduct();
			if (res)
				JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG !");
			else
				JOptionPane.showMessageDialog(this, "THÊM THAT BAI !");
			try {
				updateView_MaSP();
				updateView_DanhSachSP();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (src.equals(btn_Feature) && btn_Feature.getText().equalsIgnoreCase("SỬA")) {
			fixProduct();
			if (tbl_ProductList.getSelectedRow() > 0) {
				btn_Feature.setEnabled(true);
			}
			// SỬA SẢN PHẨM
		} else if (src.equals(btn_RoomEmpty)) {
//			staicRoom = Types.ROOM_STATIC_EMPTY;
//			updateRoomView(Phong_Dao.getRoomTypeAndCount(staicRoom, Types.ROOM_TYPE_ALL, Types.ROOM_COUNT_PEOPLE_ALL));
			rdb_AllCount.setSelected(true);
			rdb_TypeAll.setSelected(true);
		} else if (src.equals(btn_RoomFixing)) {
//			staicRoom = Types.ROOM_STATIC_FIXING;
//			updateRoomView(Phong_Dao.getRoomsWithStatic(staicRoom));
			rdb_AllCount.setSelected(true);
			rdb_TypeAll.setSelected(true);
		} else if (src.equals(btn_RoomUsing)) {
//			staicRoom = Types.ROOM_STATIC_USING;
//			updateRoomView(Phong_Dao.getRoomsWithStatic(staicRoom));
			rdb_AllCount.setSelected(true);
			rdb_TypeAll.setSelected(true);
		} else if (src.equals(btn_Renew)) {
			txt_SearchNumberRoom.setText("");
			rdb_TypeAll.setSelected(true);
			rdb_AllCount.setSelected(true);
			staicRoom = Types.ROOM_STATIC_ALL;
			roomType = Types.ROOM_TYPE_ALL;
			countPeople = Types.ROOM_COUNT_PEOPLE_ALL;
			try {
				p_dao = (PhongServer) Naming.lookup(URL + "PhongServer");
				updateRoomView(p_dao.getAll());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (src.equals(btn_SeachRoom)) {
//			var list = Phong_Dao.searchPhong_Ten(txt_SearchNumberRoom.getText());
//			updateRoomView(list);
		}

	}

	private void lamMoi() {
		try {
			p_dao = (PhongServer) Naming.lookup(Types.URL + "PhongServer");
			List<Phong> list = p_dao.getAll();
			this.updateRoomView(list);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void fixProduct() {
		try {
			int index = tbl_ProductList.getSelectedRow();
			String maSP = model.getValueAt(index, 0) + "";
			SanPham sanPham = sp_dao.find(maSP);
			JDialogDichVu_SuaDichVu dichVu = new JDialogDichVu_SuaDichVu(this, sanPham);
			dichVu.show();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	private boolean addProduct() {
		try {
			SanPham sp = getProductPresent();
			if (sp == null)
				return false;
			var sp_cu = sp_dao.findName(sp.getTenSP());
			if (sp_cu == null) {
				sp_dao.add(sp);
			} else {
				sp.setMaSP(sp_cu.getMaSP());
				sp.setSoLuongTon(sp_cu.getSoLuongTon() + sp.getSoLuongTon());
				sp_dao.update(sp);
			}
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}

	private void updateView_MaSP() throws RemoteException {
		cbb_TypeService.setSelectedIndex(0);
		int n = sp_dao.getAll().size() + 1;
		txt_ProductCode.setText("SP" + n);
	}

	public void updateView_DanhSachSP() {
		model.setRowCount(0);
		List<SanPham> list = new ArrayList<SanPham>();
		try {
			list = sp_dao.getAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (SanPham a : list) {
			System.out.println(a);
			model.addRow(new Object[] { a.getMaSP(), a.getMaLSP().getTenLSP(), a.getTenSP(), a.getSoLuongTon(),
					a.getGiaNhap(), a.getGiaBan(), a.getTinhTrang(), a.getNgayNhap() });
		}
	}

	private SanPham getProductPresent() {

		try {
			String idSP = null;
			String tenSP = null;
			double giaNhap = 0;
			double giaBan = 0;
			int sl = 0;
			String tt = null;
			idSP = txt_ProductCode.getText();
			// check name product
			String regexNameProduct = "[a-zàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựýỳỷỹỵ]+"
					+ "( [A-Z0-9a-zàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựýỳỷỹỵ()_]+)*";
			tenSP = cbb_ProductName.getSelectedItem() + "";
			if (!tenSP.toUpperCase().matches(regexNameProduct.toUpperCase()) || tenSP.length() < 3) {
				if (tenSP.length() < 3)
					JOptionPane.showMessageDialog(null, "\"" + tenSP + "\"" + " Đây không phải tên sản phẩm");
				else
					JOptionPane.showMessageDialog(null, "Tên chỉ chứa chữ và số và ký tự \"(, ), _\"  !");
				return null;
			} else
				tenSP = tenSP.toUpperCase();
			// check name type product
			String regexNameTypeProduct = "[A-Za-zàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựýỳỷỹỵ]+"
					+ "( [A-Za-zàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựýỳỷỹỵ]+)*";
			String nameTypeProduct = cbb_ProductType.getSelectedItem() + "";
			if (!nameTypeProduct.toUpperCase().matches(regexNameTypeProduct.toUpperCase())
					|| nameTypeProduct.length() < 3) {
				if (nameTypeProduct.length() < 3)
					JOptionPane.showMessageDialog(null,
							"\"" + nameTypeProduct + "\"" + " Đây không phải tên loại sản phẩm");
				else
					JOptionPane.showMessageDialog(null, "Loại sản phẩm chỉ chứa chứ và số !");
				return null;
			} else
				nameTypeProduct = nameTypeProduct.toUpperCase();

			var dv = ldv_Dao.findName(cbb_TypeService.getSelectedItem() + "");
			if (dv == null) {
				dv = new LoaiDichVu((ldv_Dao.getAll().size() + 1) + "", cbb_TypeService.getSelectedItem() + "");
				ldv_Dao.add(dv);
			}

			var lsp = lsp_Dao.findName(cbb_ProductType.getSelectedItem() + "");
			if (lsp == null) {
				lsp = new LoaiSanPham((lsp_Dao.getAll().size() + 1) + "", cbb_ProductType.getSelectedItem() + "", dv);
				lsp_Dao.add(lsp);
			}

			giaNhap = (double) Integer.valueOf(spModel_PriceBuy.getValue() + "");
			giaBan = (double) Integer.valueOf(spModel_PriceSale.getValue() + "");
			sl = Integer.valueOf(txt_CountProduct.getText());
			tt = cbb_Status.getSelectedItem() + "";

			return new SanPham(idSP, tenSP, lsp, LocalDate.now(), giaNhap, giaBan, sl, tt);

		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void resetValueInput() {
		spModel_PriceBuy = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
				Integer.valueOf(1000));
		spModel_PriceSale = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
				Integer.valueOf(1000));
		sp_PriceBuy.setModel(spModel_PriceBuy);
		sp_PriceSale.setModel(spModel_PriceSale);
		scb_CountProduct.setValue(1);
		txt_CountProduct.setText("1");

	}

	private void setNameProduct(String id_LSP) throws RemoteException {
		cbb_ProductName.removeAllItems();
		cbb_ProductName.addItem("<Nhập tên sản phẩm>");
		for (SanPham a : sp_dao.getAll()) {
			if (a.getMaLSP().getMaLSP().equalsIgnoreCase(id_LSP))
				cbb_ProductName.addItem(a.getTenSP().toUpperCase());
		}
	}

	private String searchTypeProduct_ID(String nameTypeProduct) throws RemoteException {
		for (LoaiSanPham a : lsp_Dao.getAll()) {
			if (a.getTenLSP().equalsIgnoreCase(nameTypeProduct))
				return a.getMaLSP();
		}
		return null;
	}

	private void setProductType(String codeTypeService) throws RemoteException {
		cbb_ProductType.removeAllItems();
		cbb_ProductType.addItem("<Nhập loại sản phẩm>");
		for (LoaiSanPham a : lsp_Dao.getAll()) {
			if (a.getMaLDV().getMa().equalsIgnoreCase(codeTypeService))
				cbb_ProductType.addItem(a.getTenLSP().toUpperCase());
		}
	}

	private String searchService_ServiceID(String nameService)
			throws RemoteException, MalformedURLException, NotBoundException {
		ldv_Dao = (LoaiDichVuServer) Naming.lookup(URL + "LoaiDichVuServer");
		for (LoaiDichVu a : ldv_Dao.getAll()) {
			if (a.getTen().equalsIgnoreCase(nameService)) {
				return a.getMa();
			}
		}

		return null;
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Object a = e.getSource();
		if (a.equals(sp_PriceBuy)) {
			setPriceBuy();
		} else if (a.equals(sp_PriceSale)) {
			setPriceSale();
		}

	}

	private void setPriceBuy() {
		int buy = Integer.valueOf(spModel_PriceBuy.getValue() + "");
		int sale = Integer.valueOf(spModel_PriceSale.getValue() + "");
		if (buy > sale) {
			spModel_PriceSale = new SpinnerNumberModel(buy, buy, null, 1000);
			sp_PriceSale.setModel(spModel_PriceSale);
		}

	}

	private void setPriceSale() {
		int priceBuy = Integer.valueOf(spModel_PriceBuy.getValue() + "");
		int pricePrsent = Integer.valueOf(spModel_PriceSale.getValue() + "");
		spModel_PriceSale = new SpinnerNumberModel(pricePrsent, priceBuy, null, 1000);
		sp_PriceSale.setModel(spModel_PriceSale);

	}

	@SuppressWarnings("deprecation")
	public void allowShowDisplay(boolean values) {
		if (values)
			pnl_West_AddService.show();
		else
			pnl_West_AddService.hide();
	}

	public void allowEnableButtonFeature() {
		btn_Feature.setEnabled(true);

	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_ProductType.getModel())) {
			if ((cbb_ProductType.getSelectedItem() + "").equalsIgnoreCase("<Nhập loại sản phẩm>")) {
				cbb_ProductName.setEnabled(false);
			} else {
				cbb_ProductName.setEnabled(true);
			}
		}
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_ProductType.getModel())) {
			if ((cbb_ProductType.getSelectedItem() + "").equalsIgnoreCase("<Nhập loại sản phẩm>")) {
				cbb_ProductName.setEnabled(false);
			} else {
				cbb_ProductName.setEnabled(true);
			}
		}
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_ProductType.getModel())) {
			if ((cbb_ProductType.getSelectedItem() + "").equalsIgnoreCase("<Nhập loại sản phẩm>")) {
				cbb_ProductName.setEnabled(false);
			} else {
				cbb_ProductName.setEnabled(true);
			}

		}
	}
}
