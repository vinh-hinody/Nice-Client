package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.Types;
import dao.ChiTietHoaDonServer;
import dao.ChiTietPhieuDatPhongServer;
import dao.HoaDonServer;
import dao.KhachHangServer;
import dao.NhanVienServer;
import dao.PhieuDatPhongServer;
import dao.PhongServer;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class JDialogPhong_DatPhong extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL = Types.URL;
	private JPanel contentPanel = new JPanel();
	private GD_Phong home;
	private JTextField txt_KH;
	private Phong room;

	private PhieuDatPhongServer pdp_dao;
	private ChiTietPhieuDatPhongServer ctpdp_dao;
	private KhachHangServer kh_dao;
	private PhongServer p_dao;
	private NhanVienServer nv_dao;
	private HoaDonServer hd_dao;
	private ChiTietHoaDonServer cthd_dao;

	/**
	 * Create the dialog.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public JDialogPhong_DatPhong(GD_Phong home, Phong room)
			throws RemoteException, MalformedURLException, NotBoundException {

		hd_dao = (HoaDonServer) Naming.lookup(URL + HoaDonServer.class.getSimpleName());
		kh_dao = (KhachHangServer) Naming.lookup(URL + KhachHangServer.class.getSimpleName());
		pdp_dao = (PhieuDatPhongServer) Naming.lookup(URL + PhieuDatPhongServer.class.getSimpleName());
		ctpdp_dao = (ChiTietPhieuDatPhongServer) Naming.lookup(URL + ChiTietPhieuDatPhongServer.class.getSimpleName());
		cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + ChiTietHoaDonServer.class.getSimpleName());
		nv_dao = (NhanVienServer) Naming.lookup(URL + NhanVienServer.class.getSimpleName());
		p_dao = (PhongServer) Naming.lookup(URL + PhongServer.class.getSimpleName());

		this.room = room;
		System.out.println("Phòng hiện tại nè: " + room);
		this.home = home;
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("ĐẶT PHÒNG");
		setBounds(size.width / 5 * 2, size.height / 3, 400, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 4, 4));
			{
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				JLabel lblThiGianVo = new JLabel(now.format(dtf).formatted(dtf));
				lblThiGianVo.setForeground(Color.WHITE);
				lblThiGianVo.setBackground(Color.BLACK);
				lblThiGianVo.setOpaque(true);
				lblThiGianVo.setHorizontalAlignment(SwingConstants.CENTER);
				lblThiGianVo.setFont(new Font("Dialog", Font.BOLD, 18));
				lblThiGianVo.setBorder(new LineBorder(Color.WHITE));
				panel.add(lblThiGianVo);
			}
			{
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				JLabel lblA = new JLabel(now.format(df));
				lblA.setForeground(Color.WHITE);
				lblA.setBackground(Color.BLACK);
				lblA.setOpaque(true);
				lblA.setHorizontalAlignment(SwingConstants.CENTER);
				lblA.setFont(new Font("Dialog", Font.BOLD, 18));
				lblA.setBorder(new LineBorder(Color.WHITE));
				panel.add(lblA);
			}
			{
				JLabel lblNewLabel = new JLabel("Tên Phòng");
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel(room.getTenP());
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblLoiPhng = new JLabel("Loại Phòng");
				lblLoiPhng.setBackground(Color.WHITE);
				lblLoiPhng.setOpaque(true);
				lblLoiPhng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblLoiPhng.setHorizontalAlignment(SwingConstants.CENTER);
				lblLoiPhng.setFont(new Font("Dialog", Font.PLAIN, 15));
				panel.add(lblLoiPhng);
			}
			{
				JLabel lblNewLabel = new JLabel(room.getMaLP().getTenLP());
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblSNgi = new JLabel("Số Người");
				lblSNgi.setBackground(Color.WHITE);
				lblSNgi.setOpaque(true);
				lblSNgi.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblSNgi.setHorizontalAlignment(SwingConstants.CENTER);
				lblSNgi.setFont(new Font("Dialog", Font.PLAIN, 15));
				panel.add(lblSNgi);
			}
			{
				JLabel lblNewLabel = new JLabel(room.getSucChua() + "");
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblGiPhng = new JLabel("Giá Phòng");
				lblGiPhng.setBackground(Color.WHITE);
				lblGiPhng.setOpaque(true);
				lblGiPhng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblGiPhng.setHorizontalAlignment(SwingConstants.CENTER);
				lblGiPhng.setFont(new Font("Dialog", Font.PLAIN, 15));
				panel.add(lblGiPhng);
			}
			{
				JLabel lblNewLabel = new JLabel(room.getGiaP() + "");
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblTnhTrng = new JLabel("Tình Trạng");
				lblTnhTrng.setBackground(Color.WHITE);
				lblTnhTrng.setOpaque(true);
				lblTnhTrng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblTnhTrng.setHorizontalAlignment(SwingConstants.CENTER);
				lblTnhTrng.setFont(new Font("Dialog", Font.PLAIN, 15));
				panel.add(lblTnhTrng);
			}
			{
				JLabel lblNewLabel = new JLabel(room.getTinhTrang());
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblSdtKhchHng = new JLabel("Sdt Khách Hàng");
				lblSdtKhchHng.setBackground(Color.WHITE);
				lblSdtKhchHng.setOpaque(true);
				lblSdtKhchHng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				lblSdtKhchHng.setForeground(new Color(178, 34, 34));
				lblSdtKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
				lblSdtKhchHng.setFont(new Font("Dialog", Font.BOLD, 15));
				panel.add(lblSdtKhchHng);
			}
			{
				txt_KH = new JTextField();
				txt_KH.setBackground(Color.WHITE);
				txt_KH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				txt_KH.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel.add(txt_KH);
				txt_KH.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setHgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Đặt phòng");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							okDatPhong();
						} catch (HeadlessException | RemoteException e1) {
							e1.printStackTrace();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				okButton.setPreferredSize(new Dimension(100, 30));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						cancelDatPhong();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				cancelButton.setPreferredSize(new Dimension(100, 30));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{

			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 0));

			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel_1 = new JLabel("ĐẶT PHÒNG");
				lblNewLabel_1.setForeground(new Color(255, 250, 250));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel.add(lblNewLabel_1);
			}
		}

	}

	protected void cancelDatPhong() {
		this.setVisible(false);

	}

	@SuppressWarnings("deprecation")
	public void okDatPhong() throws HeadlessException, RemoteException, MalformedURLException, NotBoundException {
		if (!txt_KH.getText().matches("0[0-9]{9}")) {
			if (txt_KH.getText().length() < 10) {
				JOptionPane.showMessageDialog(null, "Số diện thoại khách hàng phải đủ 10 số");
			} else if (txt_KH.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "Số diện thoại khách hàng quá dài");
			} else
				JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu từ 0 !");
		} else {
			if (kh_dao.findA(txt_KH.getText()) == null) {
				int khtv = JOptionPane.showConfirmDialog(null, "Đăng ký khách hàng thành viên", "Đăng ký thành viên",
						JOptionPane.YES_NO_OPTION);
				if (khtv == JOptionPane.NO_OPTION) {
					// KHÁCH HÀNG VÃNG LAI
					KhachHang kh = new KhachHang();
					kh.setSdt(txt_KH.getText());
					kh_dao.add(kh);
					datPhong();
				} else {
					try {
						JDialogKhachHang_ThemKhachHang hang = new JDialogKhachHang_ThemKhachHang(null);
						hang.setTextWhenRegisterNewCustomer(txt_KH.getText());
						hang.setupWhenAddFromBookRoom();
						hang.show();
						JOptionPane.showMessageDialog(this, "TRỞ LẠI MÀN HÌNH ĐĂNG KÝ");
						hang.hide();
						this.show();
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				if (datPhong())
					JOptionPane.showMessageDialog(null, "ĐẶT PHÒNG THÀNH CÔNG !");
				else
					JOptionPane.showMessageDialog(null, "ĐẶT PHÒNG THẤT BẠI !");
			}
		}
	}

	public String laySo(String str) {
		Pattern pattern = Pattern.compile("\\d+"); // Biểu thức chính quy để tìm số
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group(); // Trả về chuỗi số đầu tiên tìm thấy
		}
		return ""; // Trả về chuỗi rỗng nếu không tìm thấy số
	}

	@SuppressWarnings("deprecation")
	private boolean datPhong() throws RemoteException, MalformedURLException, NotBoundException {
		int n = JOptionPane.showConfirmDialog(null, "ĐẶT " + this.room.getTenP() + " ?", "Xác nhận đặt phòng",
				JOptionPane.YES_NO_OPTION);

		try {
			// CẬP NHẬT TRẠNG THÁI PHÒNG
			p_dao = (PhongServer) Naming.lookup(URL + PhongServer.class.getSimpleName());
			if (p_dao.find(this.room.getMaP()).getTinhTrang().equalsIgnoreCase("ĐANG SỬ DỤNG"))
				return false;

			this.room.setTinhTrang("ĐANG SỬ DỤNG");
			p_dao.updateStatius(this.room.getMaP(), this.room.getTinhTrang());

			// TẠO PHIẾU ĐẶT PHÒNG (PDP) MỚI
			String manv_view = GD_Chinh.getNVWorking();
			int manv = Integer.parseInt(laySo(manv_view));
			int total = pdp_dao.getAll().size();
			NhanVien nv_lap = nv_dao.find(manv);
			pdp_dao.add(new PhieuDatPhong(total + "", nv_lap, LocalDateTime.now(), "CHƯA THANH TOÁN"));

			// TẠO CHI TIẾT PDP
			PhieuDatPhong pdp = pdp_dao.find(total + "");
			ctpdp_dao.add(new ChiTietPhieuDatPhong(pdp, this.room, LocalDateTime.now(), null, "CHƯA THANH TOÁN"));

			// TAO HÓA ĐƠN
			int hd_size = hd_dao.getAll().size() + 1;
			hd_dao.add(new HoaDon(hd_size + "", nv_lap, kh_dao.findA(txt_KH.getText()), LocalDateTime.now(),
					"CHƯA THANH TOÁN"));

			// TẠO CHI TIẾT HÓA ĐƠN
			cthd_dao.add(new ChiTietHoaDon(hd_dao.find(hd_size + ""), pdp, null, this.room, "CHƯA THANH TOÁN"));

			// CẬP NHẬT GIAO DIỆN PHÒNG
			this.home.updateRoomView(p_dao.getAll());
			this.home.hide();
			this.home.show();
			this.hide();
			return true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.hide();
		return false;

	}

	public String covertPriceToSizeRoom(double price) {
		if (price > 200000)
			return "20 NGƯỜI";
		else if (price > 100000)
			return "10 NGƯỜI";
		else
			return "5 NGƯỜI";
	}

}
