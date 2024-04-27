package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import constant.Types;
import dao.ChiTietHoaDonServer;
import dao.ChiTietPhieuDatPhongServer;
import dao.HoaDonServer;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class JDialogHoaDon_TimHoaDon extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL = Types.URL;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_MaHD;
	private JTextField txt_MaNV;
	private JTextField txt_TenP;
	private JTable table;
	private JComboBox<Object> cbb_Ngay;
	private JComboBox<Object> cbb_Thang;
	private JComboBox<Object> cbb_Nam;
	private DefaultTableModel model;
	private JButton btn_DetailService;
	private GD_DichVu homeDV;
	@SuppressWarnings("unused")
	private GD_HoaDon homeHD;

	private ChiTietHoaDonServer cthd_dao;
	private HoaDonServer hd_dao;

	/**
	 * Create the dialog.
	 * 
	 * @param gd_HoaDon
	 */
	public JDialogHoaDon_TimHoaDon(GD_DichVu homeDV, GD_HoaDon gd_HoaDon) {
		this.homeDV = homeDV;
		this.homeHD = gd_HoaDon;
		setTitle("TÌM HÓA ĐƠN");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(size.width / 8, size.height / 5, size.width / 8 * 6, size.height / 5 * 3);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(4, 4));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.BLACK);
				panel_1.setPreferredSize(new Dimension(10, 30));
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel = new JLabel("TÌM HÓA ĐƠN");
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblNewLabel.setForeground(Color.WHITE);
					lblNewLabel.setBackground(Color.WHITE);
					panel_1.add(lblNewLabel);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel_1.setPreferredSize(new Dimension(700, 10));
				panel.add(panel_1, BorderLayout.EAST);
				panel_1.setLayout(new BorderLayout(0, 4));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(new Color(0, 0, 0));
					panel_1.add(panel_2, BorderLayout.NORTH);
					{
						JLabel lblNewLabel_2 = new JLabel("DANH SÁCH HÓA ĐƠN");
						lblNewLabel_2.setForeground(new Color(255, 255, 255));
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
						panel_2.add(lblNewLabel_2);
					}
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel_1.add(scrollPane, BorderLayout.CENTER);
					{
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								selectedOrder();
							}
						});
						table.setRowHeight(24);
						model = new DefaultTableModel(new Object[][] {}, new String[] { "MÃ HD", "NGÀY LẬP",
								"TÊN PHÒNG", "GIỜ VÀO", "GIỜ RA", "TIỀN THUÊ PHÒNG", "NV LẬP", "TÌNH TRẠNG" });
						table.setModel(model);
						table.getColumnModel().getColumn(0).setPreferredWidth(26);
						table.getColumnModel().getColumn(1).setPreferredWidth(40);
						table.getColumnModel().getColumn(2).setPreferredWidth(50);
						table.getColumnModel().getColumn(3).setPreferredWidth(40);
						table.getColumnModel().getColumn(4).setPreferredWidth(40);
						scrollPane.setViewportView(table);
					}
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel.add(panel_2, BorderLayout.CENTER);
				panel_2.setLayout(new BorderLayout(4, 4));
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1, BorderLayout.NORTH);
					panel_1.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblNewLabel_3 = new JLabel("NHẬP THÔNG TIN CẦN TÌM");
						lblNewLabel_3.setForeground(new Color(255, 255, 255));
						lblNewLabel_3.setBackground(new Color(0, 0, 0));
						lblNewLabel_3.setOpaque(true);
						lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_3.setPreferredSize(new Dimension(130, 30));
						lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
						panel_1.add(lblNewLabel_3);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.WHITE);
					panel_2.add(panel_1, BorderLayout.CENTER);
					panel_1.setLayout(new BorderLayout(4, 0));
					{
						JPanel panel_1_1 = new JPanel();
						panel_1.add(panel_1_1, BorderLayout.WEST);
						panel_1_1.setBackground(Color.WHITE);
						panel_1_1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
						panel_1_1.setPreferredSize(new Dimension(150, 10));
						panel_1_1.setLayout(new GridLayout(0, 1, 0, 10));
						{
							JLabel lblNewLabel_1 = new JLabel("");
							panel_1_1.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn");
							lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
							panel_1_1.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Phòng số");
							lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
							panel_1_1.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Ngày");
							lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
							panel_1_1.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
							lblNewLabel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
							panel_1_1.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("");
							panel_1_1.add(lblNewLabel_1);
						}
					}
					{
						JPanel panel_1_1 = new JPanel();
						panel_1.add(panel_1_1, BorderLayout.CENTER);
						panel_1_1.setBackground(Color.WHITE);
						panel_1_1.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
						panel_1_1.setLayout(new GridLayout(0, 1, 0, 10));
						{
							JLabel lblNewLabel_1 = new JLabel("");
							panel_1_1.add(lblNewLabel_1);
						}
						{
							txt_MaHD = new JTextField();
							txt_MaHD.setFont(new Font("Tahoma", Font.BOLD, 14));
							panel_1_1.add(txt_MaHD);
							txt_MaHD.setColumns(10);
						}
						{
							txt_TenP = new JTextField();
							txt_TenP.setFont(new Font("Tahoma", Font.BOLD, 14));
							panel_1_1.add(txt_TenP);
							txt_TenP.setColumns(10);
						}
						{
							JPanel panel_3 = new JPanel();
							panel_3.setBackground(Color.WHITE);
							panel_1_1.add(panel_3);
							panel_3.setLayout(new GridLayout(1, 0, 10, 0));
							{
								cbb_Ngay = new JComboBox<Object>();
								cbb_Ngay.setFont(new Font("Tahoma", Font.BOLD, 14));
								panel_3.add(cbb_Ngay);
							}
							{
								cbb_Thang = new JComboBox<Object>();
								cbb_Thang.setFont(new Font("Tahoma", Font.BOLD, 14));
								panel_3.add(cbb_Thang);
							}
							{
								cbb_Nam = new JComboBox<Object>();
								cbb_Nam.setFont(new Font("Tahoma", Font.BOLD, 14));
								panel_3.add(cbb_Nam);
							}
						}
						{
							txt_MaNV = new JTextField();
							txt_MaNV.setFont(new Font("Tahoma", Font.BOLD, 14));
							txt_MaNV.setColumns(10);
							panel_1_1.add(txt_MaNV);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("");
							panel_1_1.add(lblNewLabel_1);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 0, 5, 0));
				panel.setBackground(new Color(0, 0, 0));
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setHgap(20);
				flowLayout.setAlignment(FlowLayout.RIGHT);
				buttonPane.add(panel);
				{
					btn_DetailService = new JButton("SỬA MÓN ĂN");
					btn_DetailService.setEnabled(false);
					btn_DetailService.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							watchPGMDetailOfHD();
						}
					});
					panel.add(btn_DetailService);
					btn_DetailService.setPreferredSize(new Dimension(250, 30));
					btn_DetailService.setFont(new Font("Dialog", Font.BOLD, 14));
					btn_DetailService.setActionCommand("OK");
				}
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 0, 5, 0));
				panel.setBackground(new Color(0, 0, 0));
				buttonPane.add(panel, BorderLayout.WEST);
				{
					JButton okButton = new JButton("TÌM KIẾM");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							timHoaDon();
						}
					});
					panel.add(okButton);
					okButton.setPreferredSize(new Dimension(150, 30));
					okButton.setFont(new Font("Dialog", Font.BOLD, 16));
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("HỦY");
					panel.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							closeTim();
						}
					});
					cancelButton.setPreferredSize(new Dimension(150, 30));
					cancelButton.setFont(new Font("Dialog", Font.BOLD, 16));
					cancelButton.setActionCommand("Cancel");
				}
			}
		}
		addValueForCbb();
	}

	protected void timHoaDon() {
		try {
			hd_dao = (HoaDonServer) Naming.lookup(URL + HoaDonServer.class.getSimpleName());
			HoaDon hd = hd_dao.find(this.txt_MaHD.getText());
			if (hd != null) {
				ChiTietHoaDon cthd = getData(hd);
				updatetable(cthd);
			} else
				JOptionPane.showMessageDialog(null, "KHÔNG CÓ HÓA ĐƠN NÀY !");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	protected void selectedOrder() {
		btn_DetailService.setEnabled(true);

	}

	private void updatetable(ChiTietHoaDon cthd2) throws MalformedURLException, NotBoundException {
		try {
			ChiTietPhieuDatPhongServer ctpdp_dao = (ChiTietPhieuDatPhongServer) Naming
					.lookup(URL + ChiTietPhieuDatPhongServer.class.getSimpleName());

			var pdp = cthd2.getMaPhieuDP();
			
			var ctpdp = ctpdp_dao.find(pdp, cthd2.getMaPhong());
			var tgVao = ctpdp.getTgVao();
			var ngay = tgVao.getDayOfMonth() + "/" + tgVao.getMonthValue() + "/" + tgVao.getYear();
			var tgRa = ctpdp.getTgRa();
			hd_dao = (HoaDonServer) Naming.lookup(URL + HoaDonServer.class.getSimpleName());
			HoaDon hd = hd_dao.find(cthd2.getMaHD().getMaHD());
			// ĐÃ THANH TOÁN
			if (tgRa != null)
				model.addRow(new Object[] { cthd2.getMaHD().getMaHD(), ngay, cthd2.getMaPhong().getTenP(),
						tgVao.getHour() + ":" + tgVao.getMinute(), tgRa.getHour() + ":" + tgRa.getMinute(), 0,
						hd.getMaNV().getHoTenNV(), hd.getTinhTrang() });
			// CÒN SỬ DỤNG
			else
				model.addRow(new Object[] { cthd2.getMaHD().getMaHD(), ngay, cthd2.getMaPhong().getTenP(),
						tgVao.getHour() + ":" + tgVao.getMinute(), "Đang sử dụng", 0, hd.getMaNV().getHoTenNV(),
						hd.getTinhTrang() });

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private ChiTietHoaDon getData(HoaDon hd2) throws MalformedURLException, NotBoundException {
		try {
			cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + ChiTietHoaDonServer.class.getSimpleName());
			return cthd_dao.getAll().stream().filter(t -> t.getMaHD().getMaHD().equals(hd2.getMaHD())).findFirst()
					.get();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	protected void watchPGMDetailOfHD() {
		int n = table.getSelectedRow();
		String tenP = model.getValueAt(n, 2) + "";
		String maHD = model.getValueAt(n, 0) + "";
		JDialogDichVu_DatDichVu ddv;
		try {
			ddv = new JDialogDichVu_DatDichVu(homeDV, tenP, "SỬA HD", maHD, GD_Chinh.getNVWorking());
			ddv.show();
			if (!ddv.isShowing()) {
				btn_DetailService.setEnabled(false);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	protected void closeTim() {
		this.hide();
	}

	private void addValueForCbb() {
		cbb_Nam.addItem("NĂM");
		cbb_Thang.addItem("THÁNG");
		cbb_Ngay.addItem("NGÀY");
		for (int i = 1; i < 32; i++) {
			if (i < 13) {
				cbb_Thang.addItem(i);
				cbb_Ngay.addItem(i);
			} else {
				cbb_Ngay.addItem(i);
			}
		}
		for (int i = 2000; i <= LocalDate.now().getYear(); i++) {
			cbb_Nam.addItem(i);
			cbb_Nam.setSelectedItem(i);
		}
	}

}
