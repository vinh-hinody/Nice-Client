package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import constant.Types;
import dao.ChiTietHoaDonServer;
import dao.ChiTietPhieuDatPhongServer;
import dao.PhieuDatPhongServer;
import dao.PhongServer;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.PhieuDatPhong;
import entity.PhieuGoiMon;
import entity.Phong;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JDialogPhong_SuaDatPhong extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbl_TenP;
	private JLabel lbl_GiaMoi;
	private JLabel lbl_GiaCu;
	private JButton okButton;
	private JComboBox<String> cbb_ListRoom;
	private Phong room;
	private GD_Phong home;
	private JLabel lbl_SoNguoi;

	private ChiTietHoaDonServer cthd_dao;
	private PhongServer p_dao;
	private PhieuDatPhongServer pdp_dao;
	private ChiTietPhieuDatPhongServer ctpdp_dao;
	private JLabel lbl_MaHD;
	private static final String URL = Types.URL;
	/**
	 * Create the dialog.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public JDialogPhong_SuaDatPhong(GD_Phong home, Phong room) throws RemoteException, MalformedURLException, NotBoundException {
		pdp_dao = (PhieuDatPhongServer) Naming.lookup(URL+PhieuDatPhongServer.class.getSimpleName());
		p_dao = (PhongServer) Naming.lookup(URL + PhongServer.class.getSimpleName());
		ctpdp_dao = (ChiTietPhieuDatPhongServer) Naming.lookup(URL + ChiTietPhieuDatPhongServer.class.getSimpleName());
		cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + ChiTietHoaDonServer.class.getSimpleName());
		
		this.room = room;
		this.home = home;

		setTitle("SỬA ĐẶT PHÒNG");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screenSizr = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSizr.width / 4, screenSizr.height / 4, screenSizr.width / 2, screenSizr.height / 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(4, 4));
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 40));
			panel.setBackground(Color.BLACK);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel = new JLabel("SỬA ĐẶT PHÒNG");
				lblNewLabel.setPreferredSize(new Dimension(82, 40));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(5, 5));
			{
				JPanel pnl_TitleWest = new JPanel();
				pnl_TitleWest.setBackground(Color.WHITE);
				pnl_TitleWest.setBorder(new LineBorder(new Color(0, 0, 0)));
				pnl_TitleWest.setPreferredSize(new Dimension(200, 10));
				panel.add(pnl_TitleWest, BorderLayout.WEST);
				pnl_TitleWest.setLayout(new GridLayout(0, 1, 0, 10));
				{
					JLabel lblNewLabel_1 = new JLabel("MÃ HÓA ĐƠN");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
					pnl_TitleWest.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("PHÒNG CŨ");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					pnl_TitleWest.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("PHÒNG MỚI");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					pnl_TitleWest.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("GIÁ CŨ");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					pnl_TitleWest.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("GIÁ MỚI");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					pnl_TitleWest.add(lblNewLabel_1);
				}
			}
			{
				JPanel pnl_ContentCenter = new JPanel();
				pnl_ContentCenter.setBackground(Color.WHITE);
				pnl_ContentCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.add(pnl_ContentCenter, BorderLayout.CENTER);
				pnl_ContentCenter.setLayout(new GridLayout(0, 1, 0, 10));
				{
					lbl_MaHD = new JLabel("Chưa");
					timHoaDonCuaPhong();
					lbl_MaHD.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_MaHD.setFont(new Font("Dialog", Font.BOLD, 18));
					pnl_ContentCenter.add(lbl_MaHD);
				}
				{
					cbb_ListRoom = new JComboBox<String>();
					cbb_ListRoom.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setPriceFroLabel();
						}
					});
					// CẬP NHẬT DANH SÁCH PHÒNG TRỐNG
					setListRoomForCBB("Trống");
					{
						JPanel panel_1 = new JPanel();
						panel_1.setOpaque(false);
						pnl_ContentCenter.add(panel_1);
						panel_1.setLayout(new GridLayout(1, 0, 5, 5));
						{
							lbl_TenP = new JLabel(room.getTenP());
							lbl_TenP.setOpaque(true);
							lbl_TenP.setBackground(Color.WHITE);
							panel_1.add(lbl_TenP);
							lbl_TenP.setForeground(Color.BLUE);
							lbl_TenP.setHorizontalAlignment(SwingConstants.CENTER);
							lbl_TenP.setFont(new Font("Dialog", Font.BOLD, 16));
						}
						{
							lbl_SoNguoi = new JLabel("" + room.getSucChua() + " người");
							lbl_SoNguoi.setOpaque(true);
							lbl_SoNguoi.setBackground(Color.WHITE);
							lbl_SoNguoi.setHorizontalAlignment(SwingConstants.CENTER);
							lbl_SoNguoi.setFont(new Font("Dialog", Font.BOLD, 16));
							panel_1.add(lbl_SoNguoi);
						}
					}
					cbb_ListRoom.setFont(new Font("Dialog", Font.BOLD, 16));
					pnl_ContentCenter.add(cbb_ListRoom);

				}
				{
					lbl_GiaMoi = new JLabel("");
					lbl_GiaMoi.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_GiaMoi.setFont(new Font("Dialog", Font.BOLD, 16));
					pnl_ContentCenter.add(lbl_GiaMoi);
				}
				{
					lbl_GiaCu = new JLabel("");
					lbl_GiaCu.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_GiaCu.setFont(new Font("Dialog", Font.BOLD, 16));
					pnl_ContentCenter.add(lbl_GiaCu);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setHgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("LƯU");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 18));
				okButton.setPreferredSize(new Dimension(150, 40));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(e -> {
					this.hide();
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 18));
				cancelButton.setPreferredSize(new Dimension(150, 40));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	protected void setPriceFroLabel() {
		if(!cbb_ListRoom.getSelectedItem().equals("Tất cả phòng trống")) {
			try {
				double giaCu = this.room.getGiaP();
				String maPMoi = getMaPhongFromChuoi(cbb_ListRoom.getSelectedItem()+"");
				double giaMoi = p_dao.find(maPMoi).getGiaP();
				lbl_GiaMoi.setText(giaCu+"");
				lbl_GiaCu.setText(giaMoi+"");
				if(giaMoi <= giaCu)
					lbl_GiaCu.setForeground(Color.GREEN);
				else
					lbl_GiaCu.setForeground(Color.RED);
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void timHoaDonCuaPhong() throws RemoteException {
		
		// PHÒNG CHUẨN BỊ CẬP NHẬT
		Phong p_cu = this.room;

		ChiTietHoaDon cthd = cthd_dao.timHoaDonCuaPhong(p_cu.getMaP(), "CHƯA THANH TOÁN");
		// CẬP NHẬT MÃ HÓA ĐƠN (VIEW)
		lbl_MaHD.setText("HD"+cthd.getMaHD().getMaHD());
	}

	protected void setListRoomForCBB(String ttype) {
		try {
			List<Phong> list = p_dao.getAll().stream().filter(t -> (t.getTinhTrang().equalsIgnoreCase(ttype))).toList();
			cbb_ListRoom.addItem("Tất cả phòng trống");
			for (Phong p : list) {
				cbb_ListRoom.addItem(p.getTenP() + "  |  " + p.getSucChua() + " Người");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (okButton.equals(e.getSource())) {
			suaDatPhong();
		}
	}

	public String getMaPhongFromChuoi(String str) {
		String[] words = str.split("\\s+");
		if (words.length > 1) {
			String secondWord = words[1];
			return secondWord;
		}
		return "";
	}

	private void suaDatPhong() {
		try {
			Phong p_cu = p_dao.find(this.room.getMaP());
			String maPMoi = getMaPhongFromChuoi(cbb_ListRoom.getSelectedItem() + "");
			int choose = JOptionPane.showConfirmDialog(null,
					"Xác nhận chuyển " + p_cu.getTenP().toLowerCase() + " sang phòng" + maPMoi, "Thay đổi đặt phòng",
					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {
				
				// CẬP NHẬT TẤT CẢ CÁC LỚP LIÊN QUAN ĐẾN VIỆC THAY ĐỔI PHÒNG
				// TÌM KIẾM CHI TIẾT HÓA ĐƠN
				ChiTietHoaDon cthd = cthd_dao.timHoaDonCuaPhong(p_cu.getMaP(), "CHƯA THANH TOÁN");
				
				// PHÒNG CHUẨN BỊ CẬP NHẬT
				Phong p_moi = p_dao.find(maPMoi);
				p_moi.setTinhTrang("ĐANG SỬ DỤNG");

				//CẬP NHẬT CHI TIẾT PHIẾU ĐẶT PHÒNG
				ChiTietPhieuDatPhong ctpdp = ctpdp_dao.find(cthd.getMaPhieuDP(), p_cu);
				System.out.println(ctpdp.getMaPDP().getMaPhieuDP() + "|| " + ctpdp.getMaP().getMaP());
				ctpdp_dao.update(ctpdp, p_moi);

				// CẬP NHẬT TÌNH TRẠNG PHÒNG
				p_cu.setTinhTrang("TRỐNG");
				p_dao.update(p_cu);
				p_dao.update(p_moi);
				
				// CAP NHAT PHONG CHO CHI TIET HOA DON
				cthd.setMaPhong(p_moi);
				cthd_dao.update(cthd);
				
				if(cthd.getMaPhieuGM()!=null) {
					PhieuGoiMon pgm = cthd.getMaPhieuGM();
					pgm.setMaP(p_moi);
				}
				
				// update view
				this.hide();
				this.home.updateRoomView(p_dao.getAll());
				JOptionPane.showMessageDialog(null, "CHUYỂN PHÒNG THÀNH CÔNG ! TỪ:" + p_cu.getTenP() + "sang Phong" + maPMoi);
			}

		} catch (

		RemoteException e) {
			e.printStackTrace();
		}
	}

}
