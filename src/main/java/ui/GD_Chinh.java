package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Insets;
import javax.swing.border.SoftBevelBorder;

import dao.ChucVuSerer;
import dao.NhanVienServer;
import dao.PhongServer;
import dao.TaiKhoanServer;
import constant.Types;

import javax.swing.border.EmptyBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GD_Chinh implements ActionListener, MouseListener, KeyListener {

	private JFrame frame;
	private Dimension size;
	private JPanel pnl_Content;
	private JPanel pnl_Login;
	private GD_KhachHang pnl_KhachHang;
	private GD_NhanVien pnl_NhanVien;
	private GD_Phong pnl_Phong;
	private GD_BangChamCong pnl_BangPhanCong;
	private GD_DichVu pnl_DichVu;
	private GD_HoaDon pnl_HoaDon;
	private JPanel pnl_ThongKe;
	private JPanel pnl_TitleAndTime;
	private JLabel dateNow;
	private JLabel timeNow;
	private JTextField AccID;
	private JPasswordField AccPass;
	private int widthScreen;
	private JMenuBar mnu;
	private JMenu mn_Room;
	private JMenu mnDchV;
	private JMenu mn_Orders;
	private JMenu mnNhnVin;
	private JMenu mnKhchHng;
	private JMenu mnThngK;
	private JMenu mnHThng;
	private JMenu mnTrGip;
	private JMenuItem mni_BookRoom;
	private JMenuItem mni_FixBookRoom;
	private JMenuItem mni_AddRoom;
	private JMenuItem mni_FixRoom;
	private JMenuItem mni_SearchRoom;
	private JMenuItem mni_BookFood;
	private JMenuItem mni_BookWater;
	private JMenuItem mni_SearchService;
	private JMenuItem mni_AddService;
	private JMenuItem mni_FixService;
	private JMenuItem mni_PaymentOrders;
	private JMenuItem mni_SearchOrders;
	private JMenuItem mni_AddStaff;
	private JMenuItem mni_FixStaff;
	private JMenuItem mni_SearchStaff;
	private JMenuItem mni_CheckInToday;
	private JMenuItem mni_Timesheet;
	private JMenuItem mni_AddCustomers;
	private JMenuItem mni_SearchCustomers;
	private JMenuItem mni_FixCustomers;
	private JMenuItem mni_BestSellFood;
	private JMenuItem mni_RevenueStatistical;
	private JMenuItem mni_InforPersonal;
	private JMenuItem mni_UpdatePassword;
	private JMenuItem mni_SignUp;
	private JMenuItem mni_LogOut;
	private JMenuItem mni_HowUse;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btn_CloseHome;
	private static JLabel lbl_MaNV;
	protected LocalDateTime now;
	protected DateTimeFormatter df;
	protected DateTimeFormatter dtf;
	private JPanel pnl_Windows;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JComboBox<String> cbb_Background;
	private JLabel lbl_Background;
	private JMenuItem mni_FixBookService;

	private static ChucVuSerer chucVu_Dao;
	private static NhanVienServer nhanVien_Dao;
	private static TaiKhoanServer taiKhoan_Dao;
	private PhongServer phong_dao;
	/**
	 * Launch the application.
	 * 
	 * @throws RemoteException
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_Chinh window = new GD_Chinh();
					window.frame.setVisible(true);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public GD_Chinh() throws RemoteException, MalformedURLException, NotBoundException {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		initialize();
		clock();
		updateCBBGD();
	}

	private void updateCBBGD() {
		cbb_Background.addItem("MẶC ĐỊNH");
		for (int i = 1; i <= 11; i++) {
			cbb_Background.addItem(i + "");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	@SuppressWarnings("deprecation")
	private void initialize() throws RemoteException, MalformedURLException, NotBoundException {

		chucVu_Dao = (ChucVuSerer) Naming.lookup(Types.URL + "ChucVuServer");
		nhanVien_Dao = (NhanVienServer) Naming.lookup(Types.URL + "NhanVienServer");
		taiKhoan_Dao = (TaiKhoanServer) Naming.lookup(Types.URL + "TaiKhoanServer");
		
		if (chucVu_Dao.find("ADMIN") == null)
			chucVu_Dao.add(new ChucVu("ADMIN", "ADMIN"));
		if (nhanVien_Dao.find(1) == null )
			nhanVien_Dao.add(new NhanVien("Lê Quang Vinh", new ChucVu("ADMIN", "ADMIN"), "0985189541", "012345678999", null,
					"Quang Trung, GV, HCM", 10.0, null, "ADMIN"));
		if(nhanVien_Dao.find(2) == null)
			nhanVien_Dao.add(new NhanVien("Nguyễn Bảo Đại", new ChucVu("ADMIN", "ADMIN"), "0123123123", "012345678999", null,
					"Quang Trung, GV, HCM", 10.0, null, "ADMIN"));
		if (taiKhoan_Dao.find("ADMIN") == null)
			taiKhoan_Dao.add(new TaiKhoan("ADMIN", "ADMIN", nhanVien_Dao.find(1)));
		if (taiKhoan_Dao.find("ADMIN2") == null)
			taiKhoan_Dao.add(new TaiKhoan("ADMIN2", "ADMIN2", nhanVien_Dao.find(2)));

		lbl_MaNV = new JLabel();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setResizable(true);

		frame.setBackground(Color.WHITE);
		frame.setTitle("Karaoke Nice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
//		pnl_Menu = create_Menu();
//		frame.getContentPane().add((JPanel) pnl_Menu);
		size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setMaximumSize(new Dimension(size.width, size.height));
		frame.setMinimumSize(new Dimension(size.width, size.height));
		pnl_Windows = new JPanel();
		pnl_Windows.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_Windows.setOpaque(false);
		pnl_Windows.setBounds(0, 0, size.width, size.height - 74);
		frame.getContentPane().add(pnl_Windows);
		pnl_Windows.setLayout(new BorderLayout(4, 4));

		pnl_TitleAndTime = new JPanel();
		pnl_Windows.add(pnl_TitleAndTime, BorderLayout.NORTH);
		pnl_TitleAndTime.setPreferredSize(new Dimension(10, 50));
		pnl_TitleAndTime.setOpaque(false);
		pnl_TitleAndTime.setBackground(new Color(152, 251, 152));
		pnl_TitleAndTime.setLayout(new BorderLayout(10, 0));

		JLabel lblTitle = new JLabel("KARAOKE NICE MANAGEMENT");
		lblTitle.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblTitle.setForeground(new Color(245, 255, 250));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		pnl_TitleAndTime.add(lblTitle);

		JPanel pnl_dateTime = new JPanel();
		pnl_dateTime.setPreferredSize(new Dimension(300, 50));
		pnl_dateTime.setOpaque(false);
		pnl_dateTime.setForeground(new Color(255, 255, 255));
		pnl_dateTime.setBackground(new Color(0, 0, 0));
		pnl_TitleAndTime.add(pnl_dateTime, BorderLayout.WEST);
		pnl_dateTime.setLayout(new GridLayout(0, 2, 4, 0));

		dateNow = new JLabel("date");
		dateNow.setBorder(new LineBorder(new Color(255, 255, 255)));
		dateNow.setBackground(new Color(0, 0, 0));
		dateNow.setOpaque(true);
		dateNow.setForeground(new Color(255, 255, 255));
		dateNow.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		dateNow.setPreferredSize(new Dimension(150, 28));
		pnl_dateTime.add(dateNow);
		dateNow.setHorizontalAlignment(SwingConstants.CENTER);

		timeNow = new JLabel("time");
		timeNow.setBorder(new LineBorder(new Color(255, 255, 255)));
		timeNow.setBackground(new Color(0, 0, 0));
		timeNow.setOpaque(true);
		timeNow.setForeground(new Color(255, 255, 255));
		timeNow.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		timeNow.setHorizontalAlignment(SwingConstants.CENTER);
		timeNow.setPreferredSize(new Dimension(150, 28));
		pnl_dateTime.add(timeNow);

		JPanel pnl_UserLogin = new JPanel();
		pnl_UserLogin.setOpaque(false);
		pnl_UserLogin.setBackground(Color.BLACK);
		pnl_UserLogin.setPreferredSize(new Dimension(350, 10));
		pnl_TitleAndTime.add(pnl_UserLogin, BorderLayout.EAST);
		pnl_UserLogin.setLayout(new GridLayout(0, 2, 4, 0));

		JLabel lblNewLabel_24 = new JLabel("NHÂN VIÊN:");
		lblNewLabel_24.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblNewLabel_24.setBackground(new Color(0, 0, 0));
		lblNewLabel_24.setOpaque(true);
		lblNewLabel_24.setPreferredSize(new Dimension(130, 13));
		lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_24.setForeground(Color.WHITE);
		lblNewLabel_24.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		pnl_UserLogin.add(lblNewLabel_24);

		JPanel panel_31 = new JPanel();
		panel_31.setOpaque(false);
		panel_31.setBackground(Color.BLACK);
		pnl_UserLogin.add(panel_31);
		panel_31.setLayout(new BorderLayout(4, 0));

		lbl_MaNV = new JLabel("Chưa đăng nhập");
		lbl_MaNV.setBackground(new Color(0, 0, 0));
		lbl_MaNV.setOpaque(true);
		lbl_MaNV.setPreferredSize(new Dimension(100, 13));
		lbl_MaNV.setForeground(Color.WHITE);
		lbl_MaNV.setBorder(new LineBorder(Color.WHITE));
		lbl_MaNV.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MaNV.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		panel_31.add(lbl_MaNV);

		btn_CloseHome = new JButton("");
		btn_CloseHome.setPreferredSize(new Dimension(40, 9));
		btn_CloseHome.addActionListener(this);
		btn_CloseHome.setIcon(new ImageIcon(GD_Chinh.class.getResource("/img/Logout.png")));
		btn_CloseHome.setFocusable(false);

		panel_31.add(btn_CloseHome, BorderLayout.EAST);

		pnl_Content = new JPanel();
		pnl_Windows.add(pnl_Content, BorderLayout.CENTER);
		pnl_Content.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_Content.setOpaque(false);
		pnl_Content.setLayout(new CardLayout(0, 0));

		pnl_Login = new JPanel(new BorderLayout());
		pnl_Login.setOpaque(false);
		pnl_Content.add(pnl_Login, "name_login");

		pnl_KhachHang = new GD_KhachHang();
		pnl_Content.add(pnl_KhachHang, "name_kh");

		pnl_NhanVien = new GD_NhanVien();
		pnl_Content.add(pnl_NhanVien, "name_nv");

		pnl_Phong = new GD_Phong();
		pnl_Content.add(pnl_Phong, "name_p");

		pnl_BangPhanCong = new GD_BangChamCong();
		pnl_Content.add(pnl_BangPhanCong, "name_bpc");

		pnl_DichVu = new GD_DichVu(null);
		pnl_Content.add(pnl_DichVu, "name_dv");

		pnl_ThongKe = new JPanel();
		pnl_Content.add(pnl_ThongKe, "name_tk");

		pnl_HoaDon = new GD_HoaDon(this.pnl_DichVu, this.pnl_HoaDon);
		pnl_Content.add(pnl_HoaDon, "name_hd");

		JPanel pnl_Log = new JPanel();
		pnl_Login.add(pnl_Log);
		pnl_Log.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		pnl_Log.setOpaque(false);
		pnl_Log.setLayout(new BorderLayout(0, 0));

		JPanel panelMain = new JPanel();
		panelMain.setOpaque(false);
		pnl_Log.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelMain.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(55);
		flowLayout.setHgap(100);
		panel_5.setPreferredSize(new Dimension(150, 150));
		panel_4.add(panel_5, BorderLayout.SOUTH);

		btnLogin = new JButton("ĐĂNG NHẬP");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setPreferredSize(new Dimension(200, 40));
		panel_5.add(btnLogin);

		btnLogout = new JButton("THOÁT");
		btnLogout.addActionListener(this);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogout.setPreferredSize(new Dimension(200, 40));
		panel_5.add(btnLogout);

		panel = new JPanel();
		panel.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(10);
		panel_5.add(panel);

		lblNewLabel_2 = new JLabel("HÌNH NỀN");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(new Color(240, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(211, 211, 211));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_2.setPreferredSize(new Dimension(120, 40));
		panel.add(lblNewLabel_2);

		cbb_Background = new JComboBox<String>();
		cbb_Background.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				changeBackGround(cbb_Background.getSelectedItem() + "");
			}
		});
		cbb_Background.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbb_Background.setPreferredSize(new Dimension(130, 40));
		panel.add(cbb_Background);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setPreferredSize(new Dimension(10, 250));
		panel_4.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setBorder(new EmptyBorder(70, 0, 0, 0));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_4.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_7.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(4, 0));

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_7.add(panel_9);
		panel_9.setLayout(new BorderLayout(4, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EmptyBorder(70, 100, 70, 90));
		panel_11.setOpaque(false);
		panel_9.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new GridLayout(0, 1, 0, 30));

		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new EmptyBorder(0, 100, 0, 100));
		panel_14.setOpaque(false);
		panel_11.add(panel_14);
		panel_14.setLayout(new BorderLayout(10, 0));

		JLabel lblNewLabel_1_1 = new JLabel("Tài khoản:");
		lblNewLabel_1_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1_1.setFocusable(false);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1_1.setOpaque(true);
		panel_14.add(lblNewLabel_1_1, BorderLayout.WEST);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setPreferredSize(new Dimension(150, 13));

		AccID = new JTextField();
		AccID.setText("ADMIN");
		AccID.addKeyListener(this);
		AccID.setMargin(new Insets(2, 10, 2, 2));
		AccID.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_14.add(AccID, BorderLayout.CENTER);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new EmptyBorder(0, 100, 0, 100));
		panel_15.setOpaque(false);
		panel_11.add(panel_15);
		panel_15.setLayout(new BorderLayout(10, 0));

		JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setFocusable(false);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_15.add(lblNewLabel_1, BorderLayout.WEST);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(150, 13));

		AccPass = new JPasswordField();
		AccPass.setText("ADMIN");
		AccPass.setMargin(new Insets(2, 10, 2, 2));
		AccPass.addKeyListener(this);
		AccPass.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_15.add(AccPass, BorderLayout.CENTER);

		mnu = new JMenuBar();
		mnu.setLayout(new GridLayout(1, 0, 0, 0));
		mnu.setPreferredSize(new Dimension(widthScreen, 50));
		frame.setJMenuBar(mnu);

		mn_Room = new JMenu("PHÒNG HÁT");
		mn_Room.addMouseListener(this);
		mn_Room.setForeground(new Color(0, 0, 0));
		mn_Room.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mn_Room.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mn_Room.setMnemonic('p');
		mn_Room.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mn_Room);

		mni_BookRoom = new JMenuItem("Thêm đặt phòng");
		mni_BookRoom.addActionListener(this);
		mn_Room.add(mni_BookRoom);

		mni_FixBookRoom = new JMenuItem("Sửa đặt phòng");
		mn_Room.add(mni_FixBookRoom);
		mni_FixBookRoom.addActionListener(this);

		JMenu mnNewMenu = new JMenu("Quản lý phòng");
		mn_Room.add(mnNewMenu);

		mni_AddRoom = new JMenuItem("Thêm phòng");
		mnNewMenu.add(mni_AddRoom);
		mni_AddRoom.addActionListener(this);

		mni_FixRoom = new JMenuItem("Sửa phòng");
		mnNewMenu.add(mni_FixRoom);
		mni_FixRoom.addActionListener(this);

		mni_SearchRoom = new JMenuItem("Tìm phòng");
		mnNewMenu.add(mni_SearchRoom);
		mni_SearchRoom.addActionListener(this);

		mnDchV = new JMenu("DỊCH VỤ");
		mnDchV.addMouseListener(this);
		mnDchV.setForeground(new Color(0, 0, 0));
		mnDchV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDchV.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnDchV.setMnemonic('d');
		mnDchV.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnDchV);

		mni_BookFood = new JMenuItem("Đặt dịch vụ đồ ăn");
		mni_BookFood.addActionListener(this);
		mnDchV.add(mni_BookFood);

		mni_BookWater = new JMenuItem("Đặt dịch vụ đồ uống");
		mni_BookWater.addActionListener(this);
		mnDchV.add(mni_BookWater);

		mni_FixBookService = new JMenuItem("Sửa đặt dịch vụ");
		mni_FixBookService.addActionListener(this);
		mnDchV.add(mni_FixBookService);

		JMenu mnNewMenu_3 = new JMenu("Quản lý dịch vụ");
		mnDchV.add(mnNewMenu_3);

		mni_AddService = new JMenuItem("Thêm dịch vụ");
		mni_AddService.addActionListener(this);
		mnNewMenu_3.add(mni_AddService);

		mni_FixService = new JMenuItem("Sửa dịch vụ");
		mni_FixService.addActionListener(this);
		mnNewMenu_3.add(mni_FixService);

		mni_SearchService = new JMenuItem("Tìm kiếm dịch vụ");
		mni_SearchService.addActionListener(this);
		mnNewMenu_3.add(mni_SearchService);

		mn_Orders = new JMenu("HÓA ĐƠN");
		mn_Orders.addMouseListener(this);
		mn_Orders.setForeground(new Color(0, 0, 0));
		mn_Orders.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mn_Orders.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mn_Orders.setMnemonic('h');
		mn_Orders.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mn_Orders);

		mni_PaymentOrders = new JMenuItem("Thanh toán hóa đơn");
		mni_PaymentOrders.addActionListener(this);
		mn_Orders.add(mni_PaymentOrders);

		mni_SearchOrders = new JMenuItem("Tìm kiếm hóa đơn");
		mni_SearchOrders.addActionListener(this);
		mn_Orders.add(mni_SearchOrders);

		mnNhnVin = new JMenu("NHÂN VIÊN");
		mnNhnVin.addMouseListener(this);
		mnNhnVin.setForeground(new Color(0, 0, 0));
		mnNhnVin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnNhnVin.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnNhnVin.setMnemonic('n');
		mnNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnNhnVin);

		mni_AddStaff = new JMenuItem("Thêm nhân viên");
		mni_AddStaff.addActionListener(this);
		mnNhnVin.add(mni_AddStaff);

		mni_FixStaff = new JMenuItem("Sửa nhân viên");
		mni_FixStaff.addActionListener(this);
		mnNhnVin.add(mni_FixStaff);

		mni_SearchStaff = new JMenuItem("Tìm kiếm nhân viên");
		mni_SearchStaff.addActionListener(this);
		mnNhnVin.add(mni_SearchStaff);

		JMenu mnNewMenu_1 = new JMenu("Chấm công");
		mnNhnVin.add(mnNewMenu_1);

		mni_CheckInToday = new JMenuItem("Chấm công hôm nay");
		mni_CheckInToday.addActionListener(this);
		mnNewMenu_1.add(mni_CheckInToday);

		mni_Timesheet = new JMenuItem("Lập bảng chấm công");
		mni_Timesheet.addActionListener(this);
		mnNewMenu_1.add(mni_Timesheet);

		mnKhchHng = new JMenu("KHÁCH HÀNG");
		mnKhchHng.addMouseListener(this);
		mnKhchHng.setBackground(new Color(0, 191, 255));
		mnKhchHng.setHorizontalTextPosition(SwingConstants.CENTER);
		mnKhchHng.setForeground(new Color(0, 0, 0));
		mnKhchHng.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnKhchHng.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnKhchHng.setMnemonic('k');
		mnKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnKhchHng);

		mni_AddCustomers = new JMenuItem("Thêm khách hàng");
		mni_AddCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) pnl_Content.getLayout();
				c.show(pnl_Content, "name_kh");
				pnl_KhachHang.changeFeatureWhenThemKH();
			}
		});
		mnKhchHng.add(mni_AddCustomers);

		mni_SearchCustomers = new JMenuItem("Tìm kiếm khách hàng");
		mni_SearchCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) pnl_Content.getLayout();
				c.show(pnl_Content, "name_kh");
				pnl_KhachHang.changeFeatureWhenSuaKH();
				JDialogKhachHang_TimKhachHang hang = new JDialogKhachHang_TimKhachHang(pnl_KhachHang);
				hang.show();
			}
		});
		mnKhchHng.add(mni_SearchCustomers);

		mni_FixCustomers = new JMenuItem("Sửa khách hàng");
		mni_FixCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_kh");
				pnl_KhachHang.changeFeatureWhenSuaKH();
			}
		});
		mnKhchHng.add(mni_FixCustomers);

		mnThngK = new JMenu("THỐNG KÊ");
		mnThngK.addMouseListener(this);
		mnThngK.setForeground(new Color(0, 0, 0));
		mnThngK.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnThngK.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnThngK.setMnemonic('t');
		mnThngK.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnThngK);

		mni_BestSellFood = new JMenuItem("Thống kê mặt hàng bán chạy");
		mni_BestSellFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open_GD_DoanhThu();
			}
		});
		mnThngK.add(mni_BestSellFood);

		mni_RevenueStatistical = new JMenuItem("Thống kê doanh thu");
		mni_RevenueStatistical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tuThang = Integer.parseInt(JOptionPane.showInputDialog("TỪ THÁNG ?"));
				int denThang = Integer.parseInt(JOptionPane.showInputDialog("ĐẾN THÁNG ?"));
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(GD_ThongKeDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE,
							null, ex);
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(GD_ThongKeDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE,
							null, ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(GD_ThongKeDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE,
							null, ex);
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					java.util.logging.Logger.getLogger(GD_ThongKeDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE,
							null, ex);
				}
				
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GD_ThongKeDoanhThu tk = new GD_ThongKeDoanhThu(LocalDate.now().getYear(), tuThang, denThang);
							tk.setAlwaysOnTop(true);
							tk.setVisible(true);
							tk.show();
						} catch (RemoteException e) {
							e.printStackTrace();
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (NotBoundException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnThngK.add(mni_RevenueStatistical);

		mnHThng = new JMenu("HỆ THỐNG");
		mnHThng.addMouseListener(this);
		mnHThng.setForeground(new Color(0, 0, 0));
		mnHThng.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHThng.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnHThng.setMnemonic('h');
		mnHThng.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnHThng);

		mni_InforPersonal = new JMenuItem("Thông tin cá nhân");
		mni_InforPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JdialogHeThong_ThongTinCaNhan caNhan = new JdialogHeThong_ThongTinCaNhan();
				caNhan.show();
			}
		});
		mnHThng.add(mni_InforPersonal);

		mni_UpdatePassword = new JMenuItem("Đổi mật khẩu");
		mni_UpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogDoiMatKhau matKhau = new JDialogDoiMatKhau();
				matKhau.show();
			}
		});
		mnHThng.add(mni_UpdatePassword);

		mni_SignUp = new JMenuItem("Đăng nhập");
		mnHThng.add(mni_SignUp);

		mni_LogOut = new JMenuItem("Đăng xuất");
		mni_LogOut.addActionListener(this);
		mnHThng.add(mni_LogOut);

		mnTrGip = new JMenu("TRỢ GIÚP");
		mnTrGip.addMouseListener(this);
		mnTrGip.setForeground(new Color(0, 0, 0));
		mnTrGip.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnTrGip.setPreferredSize(new Dimension(widthScreen / 10, 50));
		mnTrGip.setMnemonic('g');
		mnTrGip.setHorizontalAlignment(SwingConstants.CENTER);
		mnu.add(mnTrGip);

		mni_HowUse = new JMenuItem("Hướng dẫn sử dụng");
		mni_HowUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("document\\HuongDanSuDung.pdf"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnTrGip.add(mni_HowUse);

		mnu.hide();

		lbl_Background = new JLabel("");
		lbl_Background.setOpaque(true);
		lbl_Background.setIcon(new ImageIcon(GD_Chinh.class.getResource("/img/SongNhac.jpg")));
		lbl_Background.setBounds(-50, -200, 2200, 1500);
		frame.getContentPane().add(lbl_Background);
	}

	protected void changeBackGround(String stt) {
		if (stt.equals("MẶC ĐỊNH"))
			lbl_Background.setIcon(new ImageIcon(GD_Chinh.class.getResource("/img/SongNhac.jpg")));
		else {
			lbl_Background.setIcon(new ImageIcon(GD_Chinh.class.getResource("/img/bg" + stt + ".jpg")));
		}
		this.frame.setVisible(true);

	}

	@SuppressWarnings("deprecation")
	protected void open_GD_DoanhThu() {
		GD_ThongKeMatHangBanChay frame = new GD_ThongKeMatHangBanChay();
		frame.show();

	}

	protected void checkLogin(String maTK, String matkhau) throws RemoteException {
		JLabel notifical = new JLabel();
		notifical.setFont(new Font("Tahoma", Font.BOLD, 18));
		NhanVien maNV = taiKhoan_Dao.checkLogin(maTK, matkhau);
		if (maNV != null) {
			showMenu();
			CardLayout c = (CardLayout) (pnl_Content.getLayout());
			c.show(pnl_Content, "name_p");
			setBoundsForPanel();
			lbl_MaNV.setText("NV" + maNV.getMaNV());
			notifical.setText("Đăng nhập thành công !");
			Icon icon = new ImageIcon(GD_Chinh.class.getResource("/img/Ok.png"));
			JOptionPane.showMessageDialog(null, notifical, "Thông báo", JOptionPane.OK_CANCEL_OPTION, icon);
		} else {
			notifical.setText("Nhập tài khoản hoặc mật khẩu !");
			JOptionPane.showInternalMessageDialog(null, notifical, "Thông báo", JOptionPane.OK_CANCEL_OPTION);
		}
	}

	@SuppressWarnings("deprecation")
	private void showMenu() {
		mnu.show();

	}

	private void clock() {

		Thread clock = new Thread() {
			public void run() {
				for (;;) {
					now = LocalDateTime.now();
					df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					timeNow.setText(now.format(dtf));
					dateNow.setText(now.format(df));
				}
			}
		};
		clock.start();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		updateViewRoom();
		Object src = e.getSource();
		try {
			phong_dao = (PhongServer) Naming.lookup(Types.URL + PhongServer.class.getSimpleName());
			var list = phong_dao.getAll();
			pnl_Phong.updateRoomView(list);
			pnl_DichVu.updateRoomView(list);
			pnl_HoaDon.updateRoomView(list);
			if (src.equals(btnLogin)) {

				checkLogin(AccID.getText() + "", AccPass.getText() + "");

			} else if (src.equals(btnLogout)) {

				System.exit(1);

			} else if (src.equals(mni_AddRoom) || src.equals(mni_BookRoom) || src.equals(mni_FixRoom)
					|| src.equals(mni_SearchRoom) || src.equals(mni_FixBookRoom) || src.equals(mni_SearchRoom)) {

				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_p");

				if (src.equals(mni_AddRoom)) {
					try {
						JDialogPhong_ThemPhong dialog = new JDialogPhong_ThemPhong(pnl_Phong);
						dialog.show();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						e1.printStackTrace();
					}
				} else if (src.equals(mni_FixRoom)) {
					pnl_Phong.changeTitleWhenFixPhong();
				} else if (src.equals(mni_BookRoom)) {
					pnl_Phong.changeTitleWhenBookRoom();
				} else if (src.equals(mni_FixBookRoom)) {
					pnl_Phong.changeTitleWhenFixBookRoom();
				} else if (src.equals(mni_SearchRoom)) {
					pnl_Phong.changeTitleWhenSearch();
					try {
						JDialogPhong_TimPhong timPhong = new JDialogPhong_TimPhong(pnl_Phong);
						timPhong.show();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} else if (src.equals(mni_BookFood) || src.equals(mni_BookWater) || src.equals(mni_FixBookService)
					|| src.equals(mni_AddService) || src.equals(mni_SearchService) || src.equals(mni_FixService)) {

				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_dv");

				if (src.equals(mni_BookFood)) {
					pnl_DichVu.changeTitleWhenBookFood();
					pnl_DichVu.changeScreenBookService();
					pnl_DichVu.allowShowDisplay(true);
				} else if (src.equals(mni_BookWater)) {
					pnl_DichVu.changeTitleWhenBookWater();
					pnl_DichVu.changeScreenBookService();
					pnl_DichVu.allowShowDisplay(true);
				} else if (src.equals(mni_AddService)) {
					pnl_DichVu.changeTitleWhenAddService();
					pnl_DichVu.changeScreenManagentService();
					pnl_DichVu.setValueForComponentWhenAddService();
					pnl_DichVu.allowShowDisplay(true);
				} else if (src.equals(mni_FixBookService)) {
					pnl_DichVu.changeTitleWhenFixBookService();
					pnl_DichVu.changeScreenBookService();
					pnl_DichVu.allowShowDisplay(true);
				} else if (src.equals(mni_SearchService)) {
					pnl_DichVu.changeTitleWhenSearchService();
					pnl_DichVu.changeScreenManagentService();
					pnl_DichVu.allowShowDisplay(false);
					JDialogDichVu_TimDichVu timDichVu = new JDialogDichVu_TimDichVu();
					timDichVu.show();
				} else if (src.equals(mni_FixService)) {
					pnl_DichVu.changeTitleWhenFixFood();
					pnl_DichVu.changeScreenManagentService();
					pnl_DichVu.allowShowDisplay(false);
					pnl_DichVu.allowEnableButtonFeature();
				}

			} else if (src.equals(mni_PaymentOrders) || src.equals(mni_SearchOrders)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_hd");

				if (src.equals(mni_SearchOrders)) {
					pnl_HoaDon.changeTitleWhenSearchOrders();
					JDialogHoaDon_TimHoaDon timHoaDon = new JDialogHoaDon_TimHoaDon(this.pnl_DichVu, this.pnl_HoaDon);
					timHoaDon.show();
				} else if (src.equals(mni_PaymentOrders)) {
					pnl_HoaDon.changeTitleWhenPaymentOrders();
				}
			} else if (src.equals(mni_AddStaff)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_nv");
				;
			} else if (src.equals(mni_FixStaff)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_nv");
				JDialogNhanVien_SuaNhanVien vien_SuaNhanVien = new JDialogNhanVien_SuaNhanVien();
				vien_SuaNhanVien.show();
			} else if (src.equals(mni_SearchStaff)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_nv");
				JDialogNhanVien_TimNhanVien vien = new JDialogNhanVien_TimNhanVien();
				vien.show();
			} else if (src.equals(mni_CheckInToday)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_nv");
				JDialogNhanVien_ChamCong chamCong = new JDialogNhanVien_ChamCong();
				chamCong.show();
			} else if (src.equals(mni_Timesheet)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_bpc");
			} else if (src.equals(mni_LogOut)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_login");
				lbl_MaNV.setText("Chưa đăng nhập");
				AccID.setText("");
				AccPass.setText("");
			} else if (src.equals(btn_CloseHome)) {
				CardLayout c = (CardLayout) (pnl_Content.getLayout());
				c.show(pnl_Content, "name_login");
				lbl_MaNV.setText("Chưa đăng nhập");
			}

		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private void updateViewRoom() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JMenu cmd = (JMenu) e.getSource();
		setForegroundBlackForMenu();
		if (cmd.equals(mnDchV))
			setBgWhenHoverMenuDichVu();
		else if (cmd.equals(mn_Room))
			setBgWhenHoverMenuPhongHat();
		else if (cmd.equals(mn_Orders))
			setBgWhenHoverMenuHoaDon();
		else if (cmd.equals(mnNhnVin))
			setBgWhenHoverMenuNhanVien();
		else if (cmd.equals(mnKhchHng))
			setBgWhenHoverMenuKhachHang();
		else if (cmd.equals(mnThngK))
			setBgWhenHoverMenuThongKe();
		else if (cmd.equals(mnHThng))
			setBgWhenHoverMenuHeThong();
		else if (cmd.equals(mnTrGip))
			setBgWhenHoverMenuTroGiup();

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	private void setForegroundBlackForMenu() {
		mnDchV.setForeground(Color.black);
		mn_Orders.setForeground(Color.black);
		mnHThng.setForeground(Color.black);
		mnKhchHng.setForeground(Color.black);
		mnNhnVin.setForeground(Color.black);
		mnThngK.setForeground(Color.black);
		mnTrGip.setForeground(Color.black);
		mn_Room.setForeground(Color.black);
	}

	private void setBgWhenHoverMenuPhongHat() {

		mn_Room.setOpaque(true);
		mn_Room.setBackground(Color.black);
		mn_Room.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuDichVu() {

		mnDchV.setOpaque(true);
		mnDchV.setBackground(Color.black);
		mnDchV.setForeground(Color.white);

		mn_Room.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuTroGiup() {

		mnTrGip.setOpaque(true);
		mnTrGip.setBackground(Color.black);
		mnTrGip.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mn_Room.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);

	}

	private void setBgWhenHoverMenuHeThong() {
		mnHThng.setOpaque(true);
		mnHThng.setBackground(Color.black);
		mnHThng.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mn_Room.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuThongKe() {
		mnThngK.setOpaque(true);
		mnThngK.setBackground(Color.black);
		mnThngK.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mn_Room.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuKhachHang() {

		mnKhchHng.setOpaque(true);
		mnKhchHng.setBackground(Color.black);
		mnKhchHng.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mn_Room.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuNhanVien() {
		mnNhnVin.setOpaque(true);
		mnNhnVin.setBackground(Color.black);
		mnNhnVin.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Orders.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mn_Room.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	private void setBgWhenHoverMenuHoaDon() {

		mn_Orders.setOpaque(true);
		mn_Orders.setBackground(Color.black);
		mn_Orders.setForeground(Color.white);

		mnDchV.setOpaque(false);
		mn_Room.setOpaque(false);
		mnHThng.setOpaque(false);
		mnKhchHng.setOpaque(false);
		mnNhnVin.setOpaque(false);
		mnThngK.setOpaque(false);
		mnTrGip.setOpaque(false);

	}

	void setBoundsForPanel() {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		pnl_Windows.setBounds(0, 0, pnl_Windows.getWidth(), pnl_Windows.getHeight() - mnu.getPreferredSize().height);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				checkLogin(AccID.getText(), AccPass.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static String getNVWorking() {
		return lbl_MaNV.getText().replaceAll("\\D+", "");
	}
}
