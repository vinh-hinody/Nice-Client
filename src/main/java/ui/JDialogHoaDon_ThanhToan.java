package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constant.Types;
import dao.ChiTietHoaDonServer;
import dao.ChiTietPhieuDatPhongServer;
import dao.ChiTietPhieuGoiMonServer;
import dao.HoaDonServer;
import dao.NhanVienServer;
import dao.PhieuDatPhongServer;
import dao.PhieuGoiMonServer;
import dao.PhongServer;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuGoiMon;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.PhieuGoiMon;
import entity.Phong;
import entity.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class JDialogHoaDon_ThanhToan extends JDialog {

	private static final String URL = Types.URL;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbl_TongTienDichVu;
	private JLabel lbl_GiaPhong;
	private JLabel lbl_TongTienThuePhong;
	private JLabel lbl_ThoiGianSuDung;
	private ArrayList<Vector<Object>> listProductUsing;
	private JLabel lbl_KhachHang;
	private JLabel lbl_maHD;
	private JLabel lbl_TongTienThanhToan;
	private JLabel lbl_GiaSauKhiGiam;
	private JLabel lbl_GioVao;
	private JLabel lbl_GioRa;
	private JLabel lbl_tenPhong;

	private GD_HoaDon home;
	private Phong room;

	private HoaDonServer hd_dao;
	private ChiTietHoaDonServer cthd_dao;
	private PhieuDatPhongServer pdp_dao;
	private ChiTietPhieuDatPhongServer ctpdp_dao;
	private PhieuGoiMonServer pgm_dao;
	private ChiTietPhieuGoiMonServer ctpgm_dao;
	private PhongServer p_dao;
	private NhanVienServer nv_dao;

	/**
	 * Create the dialog.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 * 
	 * @throws ParseException
	 */
	public JDialogHoaDon_ThanhToan(GD_HoaDon homeHD, Phong room) throws RemoteException, MalformedURLException, NotBoundException {
		
		hd_dao = (HoaDonServer) Naming.lookup(URL + "HoaDonServer");
		cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + "ChiTietHoaDonServer");
		pdp_dao = (PhieuDatPhongServer) Naming.lookup(URL + "PhieuDatPhongServer");
		ctpdp_dao = (ChiTietPhieuDatPhongServer) Naming.lookup(URL+ "ChiTietPhieuDatPhongServer");
		pgm_dao = (PhieuGoiMonServer) Naming.lookup(URL + "PhieuGoiMonServer");
		ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL+ "ChiTietPhieuGoiMonServer");
		p_dao = (PhongServer) Naming.lookup(URL+PhongServer.class.getSimpleName());
		nv_dao = (NhanVienServer) Naming.lookup(URL + NhanVienServer.class.getSimpleName());

		this.home = homeHD;
		this.room = room;

		var cthds = cthd_dao.getAll();
		ChiTietHoaDon cthd = findHoaDonCuaPhong(cthds, this.room);
		HoaDon hd = hd_dao.find(cthd.getMaHD().getMaHD());
		NhanVien nv = nv_dao.find(Integer.parseInt(GD_Chinh.getNVWorking()));

		int soPhut = getThoiGianSuDung(hd.getThoiGianLap(), LocalDateTime.now());
		int giaP = (int) cthd.getMaPhong().getGiaP();
		int tienThuePhong = (int) (giaP * (soPhut / 60.0));
		double tienDichVu= 0;
		if(cthd.getMaPhieuGM() != null)
			tienDichVu = tinhTienDichVu(cthd.getMaPhieuGM());
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(200, 300));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thanh toán hóa đơn");
		setBounds(size.width / 5, size.height / 8, size.width / 5 * 3, size.height / 8 * 6);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 4));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("THÔNG TIN THANH TOÁN");
				lblNewLabel_1.setPreferredSize(new Dimension(40, 40));
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(10, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel_1.setPreferredSize(new Dimension(220, 10));
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new GridLayout(0, 1, 0, 10));
				{
					JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Khách hàng");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Nhân viên lập");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Thời gian vào");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Thời gian ra");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Phòng số");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Thời gian hát (phút)");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Giá phòng");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Tổng tiền thuê phòng");
					lblNewLabel_2.setBackground(new Color(224, 255, 255));
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Tổng tiền dịch vụ");
					lblNewLabel_2.setBackground(new Color(255, 218, 185));
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					panel_1.add(lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Tổng cộng");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 19));
					panel_1.add(lblNewLabel_2);
				}
			}
			{
				{
					if (hd.getMaKH().getHoTenKH() == null)
						lbl_KhachHang = new JLabel(hd.getMaKH().getSdt());
					else
						lbl_KhachHang = new JLabel(hd.getMaKH().getHoTenKH());
					lbl_KhachHang.setBackground(Color.WHITE);
					lbl_KhachHang.setOpaque(true);
					lbl_KhachHang.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_KhachHang.setFont(new Font("Tahoma", Font.BOLD, 16));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 0));
				JPanel panel_1_1 = new JPanel();
				panel_1.add(panel_1_1, BorderLayout.CENTER);
				panel_1_1.setOpaque(false);
				panel_1_1.setPreferredSize(new Dimension(100, 10));
				panel_1_1.setLayout(new GridLayout(0, 1, 0, 10));
				{
					lbl_maHD = new JLabel();
					lbl_maHD.setText(cthd.getMaHD().getMaHD());
					lbl_maHD.setBackground(Color.WHITE);
					lbl_maHD.setOpaque(true);
					lbl_maHD.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_maHD.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_maHD);
				}
				panel_1_1.add(lbl_KhachHang);
				{
					JLabel lblNewLabel_2 = new JLabel(nv.getHoTenNV());
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lblNewLabel_2);
				}
				{

					lbl_GioVao = new JLabel(dinhDangLocalDateTime(hd.getThoiGianLap()));
					lbl_GioVao.setBackground(Color.WHITE);
					lbl_GioVao.setOpaque(true);
					lbl_GioVao.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_GioVao.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_GioVao);
				}
				lbl_GioRa = new JLabel(dinhDangLocalDateTime(LocalDateTime.now()));
				lbl_GioRa.setBackground(Color.WHITE);
				lbl_GioRa.setOpaque(true);
				lbl_GioRa.setHorizontalAlignment(SwingConstants.LEFT);
				lbl_GioRa.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1_1.add(lbl_GioRa);
				{
					lbl_tenPhong = new JLabel(cthd.getMaPhong().getTenP());
					lbl_tenPhong.setBackground(Color.WHITE);
					lbl_tenPhong.setOpaque(true);
					lbl_tenPhong.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_tenPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_tenPhong);
				}
				lbl_ThoiGianSuDung = new JLabel(soPhut + "");
				lbl_ThoiGianSuDung.setBackground(Color.WHITE);
				lbl_ThoiGianSuDung.setOpaque(true);
				lbl_ThoiGianSuDung.setHorizontalAlignment(SwingConstants.LEFT);
				lbl_ThoiGianSuDung.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1_1.add(lbl_ThoiGianSuDung);
				{
					lbl_GiaPhong = new JLabel(giaP + "");
					lbl_GiaPhong.setBackground(Color.WHITE);
					lbl_GiaPhong.setOpaque(true);
					lbl_GiaPhong.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_GiaPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_GiaPhong);
				}
				{

					lbl_TongTienThuePhong = new JLabel(tienThuePhong + "");
					lbl_TongTienThuePhong.setBackground(new Color(224, 255, 255));
					lbl_TongTienThuePhong.setOpaque(true);
					lbl_TongTienThuePhong.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_TongTienThuePhong.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_TongTienThuePhong);
				}
				{

					lbl_TongTienDichVu = new JLabel(tienDichVu + "");
					lbl_TongTienDichVu.setBackground(new Color(255, 218, 185));
					lbl_TongTienDichVu.setOpaque(true);
					lbl_TongTienDichVu.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_TongTienDichVu.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1_1.add(lbl_TongTienDichVu);
				}
				{
					lbl_TongTienThanhToan = new JLabel(tienDichVu + tienThuePhong + "");
					lbl_TongTienThanhToan.setBackground(Color.WHITE);
					lbl_TongTienThanhToan.setOpaque(true);
					lbl_TongTienThanhToan.setForeground(Color.BLUE);
					lbl_TongTienThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
					lbl_TongTienThanhToan.setFont(new Font("Dialog", Font.BOLD, 19));
					panel_1_1.add(lbl_TongTienThanhToan);
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_2.setPreferredSize(new Dimension(50, 10));
					panel_1.add(panel_2, BorderLayout.WEST);
					panel_2.setLayout(new GridLayout(0, 1, 10, 10));
					{
						JLabel lbl_GioRa_1 = new JLabel();
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel();
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(new Color(224, 255, 255));
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(new Color(255, 218, 185));
						panel_2.add(lbl_GioRa_1);
					}
					{
						JLabel lbl_GioRa_1 = new JLabel((String) null);
						lbl_GioRa_1.setOpaque(true);
						lbl_GioRa_1.setBackground(Color.WHITE);
						panel_2.add(lbl_GioRa_1);
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setPreferredSize(new Dimension(10, 30));
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(10, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.BLACK);
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("SAU KHI GIẢM GIÁ");
					lblNewLabel_1.setPreferredSize(new Dimension(250, 13));
					panel_1.add(lblNewLabel_1);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setForeground(Color.WHITE);
					lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
				}
			}
			{
				lbl_GiaSauKhiGiam = new JLabel();
				lbl_GiaSauKhiGiam.setBackground(Color.WHITE);
				lbl_GiaSauKhiGiam.setOpaque(true);
				lbl_GiaSauKhiGiam.setForeground(Color.RED);
				lbl_GiaSauKhiGiam.setFont(new Font("Dialog", Font.PLAIN, 20));
				lbl_GiaSauKhiGiam.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lbl_GiaSauKhiGiam, BorderLayout.CENTER);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("VND");
				lblNewLabel_2.setPreferredSize(new Dimension(70, 13));
				lblNewLabel_2.setSize(new Dimension(70, 0));
				lblNewLabel_2.setOpaque(true);
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel.add(lblNewLabel_2, BorderLayout.EAST);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(5, 0, 5, 0));
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnInHan = new JButton("IN HÓA ĐƠN");
				btnInHan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						inhoaDon();
					}
				});
				btnInHan.setPreferredSize(new Dimension(200, 30));
				btnInHan.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnInHan.setActionCommand("OK");
				buttonPane.add(btnInHan);
			}
			{
				JButton okButton = new JButton("THANH TOÁN");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(thanhToan(lbl_maHD.getText())) {
								JOptionPane.showMessageDialog(null, "THANH TOÁN THÀNH CÔNG !");
							}
							else
								JOptionPane.showMessageDialog(null, "THANH TOÁN THẤT BẠI !");
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				okButton.setPreferredSize(new Dimension(200, 30));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeFrame();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				cancelButton.setPreferredSize(new Dimension(200, 30));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private String dinhDangLocalDateTime(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dateTime.format(formatter);
	}

	private double tinhTienDichVu(PhieuGoiMon maPhieuGM) throws RemoteException {
		var ctpgms = ctpgm_dao.getAll();
		double price = 0;
		for (ChiTietPhieuGoiMon ctpgm : ctpgms) {
			if (ctpgm.getMaPGM().getMaPGM().equals(maPhieuGM.getMaPGM()))
				price += ctpgm.getMaSP().getGiaBan() * ctpgm.getSoLuong();
		}
		return price;
	}

	private int getThoiGianSuDung(LocalDateTime thoiGianLap, LocalDateTime now) {
		return (int) ChronoUnit.MINUTES.between(thoiGianLap, now);
	}

	private ChiTietHoaDon findHoaDonCuaPhong(List<ChiTietHoaDon> cthds, Phong room2) {
		for (ChiTietHoaDon cthd : cthds) {
			if (cthd.getMaPhong().getMaP().equals(room2.getMaP())
					&& cthd.getTinhTrang().equalsIgnoreCase("CHƯA THANH TOÁN"))
				return cthd;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	protected void closeFrame() {
		this.hide();
	}

	protected void inhoaDon() {
		String hd = "";
		hd += "\n\nMÃ HÓA ĐƠN: " + lbl_maHD.getText() + "\n";
		hd += "SDT KHÁCH HÀNG: " + lbl_KhachHang.getText() + "\n";
		hd += "NHÂN VIÊN LẬP: " + GD_Chinh.getNVWorking() + "\n";
		hd += "PHÒNG: " + lbl_tenPhong.getText() + "\n";
		hd += "GIÁ PHÒNG: " + lbl_GiaPhong.getText() + "\n";
		hd += "GIỜ VÀO: " + lbl_GioVao.getText() + "\n";
		hd += "GIỜ RA: " + lbl_GioRa.getText() + "\n";
		hd += "THỜI GIAN SỬ DỤNG: " + lbl_ThoiGianSuDung.getText() + "\n";
		hd += "TỔNG TIỀN THUÊ PHÒNG: " + lbl_TongTienThuePhong.getText() + "\n\n";
		hd += "SẢN PHẨM ĐÃ GỌI:     SỐ LƯỢNG      GIÁ BÁN" + "\n";
		// TODO
		System.out.println(hd);
	}

	@SuppressWarnings("deprecation")
	protected boolean thanhToan(String maHd) throws RemoteException, MalformedURLException, NotBoundException {
		hd_dao = (HoaDonServer) Naming.lookup(URL + "HoaDonServer");
		cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + "ChiTietHoaDonServer");
		pdp_dao = (PhieuDatPhongServer) Naming.lookup(URL + "PhieuDatPhongServer");
		ctpdp_dao = (ChiTietPhieuDatPhongServer) Naming.lookup(URL+ "ChiTietPhieuDatPhongServer");
		pgm_dao = (PhieuGoiMonServer) Naming.lookup(URL + "PhieuGoiMonServer");
		ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL+ "ChiTietPhieuGoiMonServer");
		p_dao = (PhongServer) Naming.lookup(URL+PhongServer.class.getSimpleName());
		nv_dao = (NhanVienServer) Naming.lookup(URL + NhanVienServer.class.getSimpleName());
		
		var hd = hd_dao.find(maHd);
		int n = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán PHÒNG "+this.room.getTenP()+", Hóa Đơn: " + hd.getMaHD() + " ?" , "Xác nhận thanh toán",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			 
			/* CẬP NHẬT TRANG THÁI */
			String tinhTrang = "ĐÃ THANH TOÁN";
//			 HÓA ĐƠN
			hd.setTinhTrang(tinhTrang);
			hd_dao.update(hd);
			
//			CHI TIẾT HÓA ĐƠN
			var cthd = cthd_dao.find(hd.getMaHD());
			cthd.setTinhTrang(tinhTrang);
			cthd.getMaPhong().setTinhTrang(tinhTrang);
			cthd_dao.update(cthd);

			// PHÒNG 
			Phong phong = cthd.getMaPhong();
			phong.setTinhTrang("TRỐNG");
			p_dao.update(phong);
			
			// PHIẾU ĐẶT PHÒNG
			PhieuDatPhong pdp = cthd.getMaPhieuDP();
			pdp.setTinhTrang(tinhTrang);
			pdp_dao.update(pdp);
			
			// CHI TIẾT PHIẾU ĐẶT PHÒNG
			ChiTietPhieuDatPhong ctpdp = ctpdp_dao.find(pdp, phong);
			ctpdp.setTgRa(LocalDateTime.now());
			ctpdp.setTinhTrang(tinhTrang);
			ctpdp_dao.update(ctpdp);
			System.out.println(ctpdp);
			
			PhieuGoiMon pgm = cthd.getMaPhieuGM();
			if(pgm != null) {

				// PHIẾU GỌI MÓN
				pgm.setTinhTrang(tinhTrang);
				pgm_dao.update(pgm);

				// CHI TIẾT PHIẾU GỌI MÓN
				ctpgm_dao.updateWhenPayment(pgm);
			}
			
			// CẬP NHẬT HIỂN THỊ
			p_dao = (PhongServer) Naming.lookup(URL + PhongServer.class.getSimpleName());
			this.home.updateRoomView(p_dao.getAll());
			this.hide();
			return true;
		}
		return false;
	}
	

}
