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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constant.Types;
import dao.ChiTietHoaDonServer;
import dao.ChiTietPhieuGoiMonServer;
import dao.HoaDonServer;
import dao.LoaiDichVuServer;
import dao.LoaiSanPhamServer;
import dao.NhanVienServer;
import dao.PhieuGoiMonServer;
import dao.PhongServer;
import dao.SanPhamServer;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuGoiMon;
import entity.HoaDon;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.PhieuGoiMon;
import entity.Phong;
import entity.SanPham;

import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.MatteBorder;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.awt.event.MouseAdapter;

public class JDialogDichVu_DatDichVu extends JDialog implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8744021118934064542L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private GD_DichVu home;
	private String tenP;
	private JComboBox<String> cbb_TypeProducts;
	private String tenLDV;
	private JLabel lbl_MaPGM;
	private List<String> list_OrderedItem;
	private JTable tableOrderedItems;
	private DefaultTableModel modelTableOrderedItems;
	private JTable tableProduct;
	private DefaultTableModel modelProduct;
	private SpinnerNumberModel modelSprCount;
	private JSpinner spinner;
	private JButton btn_AddProduct;
	private JButton btn_Reduce;
	private JButton btn_Cancel;
	private JLabel lbl_Title;
	private JComboBox<String> cbb_TypeService;
	private String maNV;

	private PhongServer p_dao;
	private SanPhamServer sp_dao;
	private ChiTietHoaDonServer cthd_dao;
	private PhieuGoiMonServer pgm_dao;
	private ChiTietPhieuGoiMonServer ctpgm_dao;
	private LoaiSanPhamServer lsp_dao;
	private LoaiDichVuServer ldv_dao;
	private NhanVienServer nv_dao;
	private String maHD;
	private static final String URL = Types.URL;

	/**
	 * Create the dialog.
	 * 
	 * @param tenP
	 * @param gd_DichVu
	 * @param tenLDV
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public JDialogDichVu_DatDichVu(GD_DichVu gd_DichVu, String tenP, String tenLDV, String maHD, String maNV)
			throws RemoteException, MalformedURLException, NotBoundException {

		this.tenP = tenP;
		this.home = gd_DichVu;
		this.tenLDV = tenLDV;
		this.maNV = maNV;
		this.maHD = maHD;

		this.list_OrderedItem = new ArrayList<String>();

		setTitle("ĐẶT " + tenLDV);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screen.width / 8, screen.height / 6 - 30, screen.width / 8 * 6, screen.height * 2 / 3);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(4, 4));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setPreferredSize(new Dimension(10, 30));
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				lbl_Title = new JLabel("ĐẶT " + tenLDV);
				lbl_Title.setForeground(Color.WHITE);
				lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 18));
				lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lbl_Title);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(4, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel_1.setBackground(Color.WHITE);
				panel_1.setPreferredSize(new Dimension(120, 10));
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new BorderLayout(0, 4));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
					panel_2.setPreferredSize(new Dimension(10, 200));
					panel_1.add(panel_2, BorderLayout.NORTH);
					panel_2.setLayout(new GridLayout(0, 1, 0, 0));
					{
						JLabel lbl_TitleExtra = new JLabel(tenLDV);
						lbl_TitleExtra.setForeground(Color.WHITE);
						lbl_TitleExtra.setOpaque(true);
						lbl_TitleExtra.setBackground(Color.BLACK);
						lbl_TitleExtra.setFont(new Font("Tahoma", Font.BOLD, 14));
						lbl_TitleExtra.setHorizontalAlignment(SwingConstants.CENTER);
						panel_2.add(lbl_TitleExtra);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("VÀO PHÒNG");
						lblNewLabel_4.setOpaque(true);
						lblNewLabel_4.setBackground(Color.WHITE);
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						panel_2.add(lblNewLabel_4);
					}
					{
						JLabel lblNewLabel_4 = new JLabel(tenP);
						lblNewLabel_4.setOpaque(true);
						lblNewLabel_4.setBackground(Color.WHITE);
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_2.add(lblNewLabel_4);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("MÃ PHIẾU");
						lblNewLabel_4.setForeground(Color.RED);
						lblNewLabel_4.setBorder(new EmptyBorder(5, 0, 0, 0));
						lblNewLabel_4.setOpaque(true);
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_4.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_4);
					}
					{
						lbl_MaPGM = new JLabel("PGM???");
						lbl_MaPGM.setForeground(Color.RED);
						lbl_MaPGM.setOpaque(true);
						lbl_MaPGM.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_MaPGM.setFont(new Font("Tahoma", Font.BOLD, 14));
						lbl_MaPGM.setBackground(Color.WHITE);
						panel_2.add(lbl_MaPGM);
					}
				}
				{
					{
						JPanel panel_2 = new JPanel();
						panel_2.setOpaque(false);
						panel_2.setBorder(new MatteBorder(0, 4, 4, 4, (Color) new Color(0, 0, 0)));
						panel_2.setPreferredSize(new Dimension(10, 160));
						panel_1.add(panel_2, BorderLayout.SOUTH);
						panel_2.setLayout(new GridLayout(0, 1, 0, 0));
						{
							JLabel lblNewLabel_4 = new JLabel("SỐ LƯỢNG");
							lblNewLabel_4.setForeground(Color.WHITE);
							lblNewLabel_4.setOpaque(true);
							lblNewLabel_4.setBackground(new Color(0, 0, 0));
							lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
							panel_2.add(lblNewLabel_4);
						}
						{
							spinner = new JSpinner();
							modelSprCount = new SpinnerNumberModel(1, 1, 11110, 1);
							spinner.setModel(modelSprCount);
							spinner.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2.add(spinner);
						}
						{
							JPanel panel_3 = new JPanel();
							panel_3.setBackground(new Color(0, 191, 255));
							panel_3.setBorder(new EmptyBorder(4, 4, 4, 4));
							panel_2.add(panel_3);
							{
								btn_AddProduct = new JButton("THÊM >>");
								btn_AddProduct.addActionListener(this);
								panel_3.setLayout(new BorderLayout(0, 0));
								btn_AddProduct.setFont(new Font("Tahoma", Font.BOLD, 16));
								btn_AddProduct.setBorder(new EmptyBorder(3, 3, 3, 3));
								btn_AddProduct.setBackground(Color.GREEN);
								panel_3.add(btn_AddProduct);
							}
						}
						{
							JPanel panel_3 = new JPanel();
							panel_3.setBackground(Color.RED);
							panel_3.setBorder(new EmptyBorder(4, 4, 4, 4));
							panel_2.add(panel_3);
							panel_3.setLayout(new BorderLayout(0, 0));
							{
								btn_Reduce = new JButton("<< BỚT");
								btn_Reduce.addActionListener(this);
								btn_Reduce.setFont(new Font("Tahoma", Font.BOLD, 16));
								btn_Reduce.setBackground(Color.ORANGE);
								panel_3.add(btn_Reduce);
							}
						}
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(4, 4));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_1.add(panel_2, BorderLayout.NORTH);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setBackground(Color.BLACK);
						panel_3.setPreferredSize(new Dimension(10, 30));
						panel_2.add(panel_3);
						panel_3.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblNewLabel_2 = new JLabel("CÁC MÓN ĐÃ GỌI");
							lblNewLabel_2.setForeground(Color.WHITE);
							lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
							lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
							panel_3.add(lblNewLabel_2);
						}
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, BorderLayout.CENTER);
					panel_2.setLayout(new BorderLayout(4, 4));
					{
						JScrollPane scrollPane = new JScrollPane();
						panel_2.add(scrollPane, BorderLayout.CENTER);
						{
							modelTableOrderedItems = new DefaultTableModel(new Object[][] {},
									new String[] { "MÃ SP", "TÊN SP", "LOẠI SP", "SỐ LƯỢNG", "GIÁ BÁN", "TÌNH TRẠNG" });

							tableOrderedItems = new JTable(modelTableOrderedItems);
							tableOrderedItems.setFont(new Font("Tahoma", Font.PLAIN, 15));
							tableOrderedItems.getColumnModel().getColumn(0).setPreferredWidth(50);
							tableOrderedItems.getColumnModel().getColumn(1).setPreferredWidth(150);
							tableOrderedItems.getColumnModel().getColumn(2).setPreferredWidth(100);
							tableOrderedItems.getColumnModel().getColumn(3).setPreferredWidth(50);
							tableOrderedItems.getColumnModel().getColumn(4).setPreferredWidth(50);
							tableOrderedItems.getColumnModel().getColumn(5).setPreferredWidth(100);
							tableOrderedItems.getColumnModel().setColumnMargin(2);
							tableOrderedItems.setRowHeight(30);

							scrollPane.setViewportView(tableOrderedItems);
						}
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setPreferredSize(new Dimension(350, 10));
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new BorderLayout(4, 4));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(Color.BLACK);
					panel_2.setPreferredSize(new Dimension(10, 30));
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblNewLabel_1 = new JLabel("SẢN PHẨM ĐANG CÓ");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						panel_2.add(lblNewLabel_1);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 4));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_2.setBackground(Color.BLACK);
					panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_1.add(panel_2, BorderLayout.NORTH);
					panel_2.setLayout(new BorderLayout(5, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3, BorderLayout.CENTER);
						panel_3.setLayout(new GridLayout(0, 1, 0, 4));
						{
							JPanel panel_3_1 = new JPanel();
							panel_3_1.setOpaque(false);
							panel_3.add(panel_3_1);
							panel_3_1.setLayout(new BorderLayout(4, 0));
							{
								JLabel lbl_TypeProduct = new JLabel("LOẠI DỊCH VỤ");
								lbl_TypeProduct.setBackground(Color.WHITE);
								lbl_TypeProduct.setOpaque(true);
								lbl_TypeProduct.setPreferredSize(new Dimension(120, 30));
								lbl_TypeProduct.setHorizontalAlignment(SwingConstants.CENTER);
								lbl_TypeProduct.setForeground(Color.BLACK);
								lbl_TypeProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
								panel_3_1.add(lbl_TypeProduct, BorderLayout.WEST);
							}
							{
								cbb_TypeService = new JComboBox<String>();
								cbb_TypeService.setFont(new Font("Dialog", Font.BOLD, 16));
								cbb_TypeService.addActionListener(this);
								panel_3_1.add(cbb_TypeService, BorderLayout.CENTER);
							}
						}
						{
							JPanel panel_4 = new JPanel();
							panel_4.setOpaque(false);
							panel_3.add(panel_4);
							panel_4.setLayout(new BorderLayout(4, 4));
							{
								JLabel lbl_TypeProduct = new JLabel("LOẠI SẢN PHẨM");
								lbl_TypeProduct.setBackground(Color.WHITE);
								lbl_TypeProduct.setOpaque(true);
								panel_4.add(lbl_TypeProduct, BorderLayout.WEST);
								lbl_TypeProduct.setForeground(Color.BLACK);
								lbl_TypeProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
								lbl_TypeProduct.setHorizontalAlignment(SwingConstants.CENTER);
								lbl_TypeProduct.setPreferredSize(new Dimension(120, 30));
							}
							{
								cbb_TypeProducts = new JComboBox<String>();
								cbb_TypeProducts.addActionListener(this);
								panel_4.add(cbb_TypeProducts, BorderLayout.CENTER);
								cbb_TypeProducts.addItemListener(this);
								cbb_TypeProducts.setFont(new Font("Dialog", Font.BOLD, 16));
								cbb_TypeProducts.setPreferredSize(new Dimension(100, 30));
							}
						}
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_2.setPreferredSize(new Dimension(10, 100));
					panel_1.add(panel_2, BorderLayout.CENTER);
					panel_2.setLayout(new BorderLayout(0, 4));
					{
						JScrollPane scrollPane = new JScrollPane();
						panel_2.add(scrollPane, BorderLayout.CENTER);
						{
							modelProduct = new DefaultTableModel(new Object[][] {}, new String[] { "MÃ", "SL", "TÊN" });

							tableProduct = new JTable();
							tableProduct.addMouseListener(this);
							tableProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
							tableProduct.setModel(modelProduct);
							tableProduct.getColumnModel().getColumn(0).setPreferredWidth(20);
							tableProduct.getColumnModel().getColumn(1).setPreferredWidth(20);
							tableProduct.getColumnModel().getColumn(2).setPreferredWidth(200);
							tableProduct.setRowHeight(30);
							scrollPane.setViewportView(tableProduct);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("LƯU");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addBookService();
					}
				});
				okButton.setPreferredSize(new Dimension(200, 30));
				okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btn_Cancel = new JButton("THOÁT");
				btn_Cancel.addActionListener(this);
				btn_Cancel.setPreferredSize(new Dimension(150, 30));
				btn_Cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
				btn_Cancel.setActionCommand("Cancel");
				buttonPane.add(btn_Cancel);
			}
		}
		update_Cbb_TypeService();
		if (tenLDV.equals("SỬA HD")) {
			cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + ChiTietHoaDonServer.class.getSimpleName());
			var cthd = cthd_dao.find(this.maHD);
			this.lbl_MaPGM.setText(cthd.getMaPhieuGM().getMaPGM());
		} else
			updateLabel_MaPGM(tenP);
		update_DanhSachSanPhamDaGoi(lbl_MaPGM.getText());
	}

	private void updateLabel_MaPGM(String tenP2) throws MalformedURLException, NotBoundException {
		try {
			cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + ChiTietHoaDonServer.class.getSimpleName());
			pgm_dao = (PhieuGoiMonServer) Naming.lookup(URL + PhieuGoiMonServer.class.getSimpleName());
			var list = cthd_dao.getAll();
			var pgms = pgm_dao.getAll();
			for (ChiTietHoaDon pgm : list) {
				if (pgm.getMaPhong().getTenP().equals(tenP2) && pgm.getTinhTrang().equalsIgnoreCase("CHƯA THANH TOÁN"))
					if (pgm.getMaPhieuGM() == null)
						lbl_MaPGM.setText(pgms.size() + 1 + "");
					else
						lbl_MaPGM.setText(pgm.getMaPhieuGM().getMaPGM());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected void update_Cbb_TypeProduct(String TenLDV)
			throws RemoteException, MalformedURLException, NotBoundException {

		lsp_dao = (LoaiSanPhamServer) Naming.lookup(URL + "LoaiSanPhamServer");
		cbb_TypeProducts.removeAllItems();
		var list = lsp_dao.getAll();
		for (LoaiSanPham lsp : list) {
			if (lsp.getMaLDV().getTen().equalsIgnoreCase(TenLDV))
				cbb_TypeProducts.addItem(lsp.getTenLSP());
		}
	}

	private void update_Cbb_TypeService() throws RemoteException, MalformedURLException, NotBoundException {

		ldv_dao = (LoaiDichVuServer) Naming.lookup(URL + "LoaiDichVuServer");
		var list_ldv = ldv_dao.getAll();
		cbb_TypeService.removeAll();
		for (var ldv : list_ldv) {
			cbb_TypeService.addItem(ldv.getTen());
		}
		for (var ldv : list_ldv) {
			if (ldv.getTen().equalsIgnoreCase(tenLDV))
				cbb_TypeService.setSelectedItem(ldv.getTen());
		}
	}

	public void updateViewProducts(String typeService) throws RemoteException {
		modelProduct.setRowCount(0);
		for (SanPham sp : sp_dao.getAllProductHaveTypeService(typeService)) {
			modelProduct.addRow(new Object[] { sp.getMaSP(), sp.getSoLuongTon() });
		}
	}

	protected void addBookService() {
		this.hide();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_TypeService)) {
			try {
				update_Cbb_TypeProduct(cbb_TypeService.getSelectedItem() + "");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
		} else if (src.equals(cbb_TypeProducts)) {
			String tenLSP = cbb_TypeProducts.getSelectedItem() + "";
			try {
				update_DanhSachSanPhamDangCo(tenLSP);
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// THÊM GỌI MÓN MỚI
		else if (src.equals(btn_AddProduct)) {
			try {
				themMonMoiVaoHoaDon();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
		} else if (src.equals(btn_Reduce)) {
			try {
				giamSoLuongSP();
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void giamSoLuongSP() throws RemoteException, MalformedURLException, NotBoundException {

		int n = tableOrderedItems.getSelectedRow();
		String maSp = modelTableOrderedItems.getValueAt(n, 0) + "";
		sp_dao = (SanPhamServer) Naming.lookup(URL + SanPhamServer.class.getSimpleName());
		SanPham sp = sp_dao.find(maSp);
		int soLuong = Integer.valueOf(spinner.getValue() + "");
	
		// CẬP NHẬT SỐ LƯỢNG CHI TIẾT PGM
		String maPGM = lbl_MaPGM.getText();
		pgm_dao = (PhieuGoiMonServer) Naming.lookup(URL + PhieuGoiMonServer.class.getSimpleName());
		PhieuGoiMon pgm = pgm_dao.find(maPGM);
		ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL + ChiTietPhieuGoiMonServer.class.getSimpleName());

		ChiTietPhieuGoiMon ctpgm = ctpgm_dao.find(pgm, maSp);

		if (ctpgm.getSoLuong() - soLuong <= 0) {

			ctpgm_dao.remove(ctpgm.getMaPGM().getMaPGM(), ctpgm.getMaSP().getMaSP());
			sp.setSoLuongTon(sp.getSoLuongTon() + ctpgm.getSoLuong());
		} else {
			ctpgm.setSoLuong(ctpgm.getSoLuong() - soLuong);
			ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL + ChiTietPhieuGoiMonServer.class.getSimpleName());
			ctpgm_dao.update(ctpgm);

			sp.setSoLuongTon(sp.getSoLuongTon() + soLuong);
		}

		// CAP NHAT SO LUONG SAN PHAM
		sp_dao.update(sp);
		update_DanhSachSanPhamDaGoi(lbl_MaPGM.getText());
		update_DanhSachSanPhamDangCo(cbb_TypeProducts.getSelectedItem() + "");
		JOptionPane.showMessageDialog(this, "ĐÃ CẬP NHẬT SỐ LƯỢNG !");

	}

	private void themMonMoiVaoHoaDon() throws RemoteException, MalformedURLException, NotBoundException {

		sp_dao = (SanPhamServer) Naming.lookup(URL + "SanPhamServer");
		ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL + "ChiTietPhieuGoiMonServer");
		nv_dao = (NhanVienServer) Naming.lookup(URL + "NhanVienServer");
		p_dao = (PhongServer) Naming.lookup(URL + PhongServer.class.getSimpleName());
		cthd_dao = (ChiTietHoaDonServer) Naming.lookup(URL + "ChiTietHoaDonServer");
		pgm_dao = (PhieuGoiMonServer) Naming.lookup(URL + "PhieuGoiMonServer");

		int select = tableProduct.getSelectedRow();
		if (select < 0)
			JOptionPane.showMessageDialog(this, "MỜI CHỌN MÓN !");
		else {
			// LẤY DANH SÁCH SẢN PHẨM ĐÃ GỌI
			SanPham monCanThem = sp_dao.find(modelProduct.getValueAt(select, 0) + "");
			String maPGM = lbl_MaPGM.getText();
			int soLuong = Integer.valueOf(spinner.getValue() + "");

			boolean capNhat = false;

			var list = ctpgm_dao.getProducts(maPGM);
			for (SanPham sp : list) {
				if (sp.getMaSP().equals(monCanThem.getMaSP()))
					capNhat = true;
			}
			if (monCanThem.getSoLuongTon() - soLuong >= 0) {
				// THÊM MÓN MỚI
				if (!capNhat) {

					// CẬP NHẬT SỐ LƯỢNG SẢN PHẨM ĐÃ THÊM VÀO CHI TIẾT PGM
					monCanThem.setSoLuongTon(monCanThem.getSoLuongTon() - soLuong);
					if (monCanThem.getSoLuongTon() <= 0)
						monCanThem.setTinhTrang("HẾT HÀNG");
					sp_dao.update(monCanThem);

					// THÊM PHIẾU GỌI MÓN MỚI
					NhanVien nv_DangLamViec = nv_dao.find(Integer.parseInt(GD_Chinh.getNVWorking()));
					Phong phong = p_dao.findName(tenP);
					PhieuGoiMon pgm_new = new PhieuGoiMon(maPGM, nv_DangLamViec, LocalDate.now(), phong,
							"CHƯA THANH TOÁN");
					if (list.size() <= 0)
						pgm_dao.add(pgm_new);

					// CẬP NHẬT SỐ LƯỢNG MÓN KHI THÊM VÀO CHI TIẾT PHIẾU GỌI MÓN
					monCanThem.setSoLuongTon(soLuong);

					// THÊM CHI TIẾT PGM
					ctpgm_dao.add(new ChiTietPhieuGoiMon(pgm_new, monCanThem, soLuong, "CHƯA THANH TOÁN"));

					// TÌM KIẾM HÓA ĐƠN
					ChiTietHoaDon cthd = cthd_dao.timHoaDonCuaPhong(phong.getMaP(), "CHƯA THANH TOÁN");
					cthd.setMaPhieuGM(pgm_new);

					// CẬP NHẬT "PHIẾU GỌI MÓN" CHO "CHI TIẾT HÓA ĐƠN"
					cthd_dao.update(cthd);
					JOptionPane.showMessageDialog(this, "THÊM MÓN MỚI THÀNH CÔNG !");
				}
				// CẬP NHẬT SỐ LƯỢNG MÓN CŨ
				else {
					ChiTietPhieuGoiMon ctpgm = ctpgm_dao.find(pgm_dao.find(maPGM), monCanThem.getMaSP());
					ctpgm.setSoLuong(ctpgm.getSoLuong() + soLuong);
					ctpgm_dao.update(ctpgm);

					// CẬP NHẬT SỐ LƯỢNG SẢN PHẨM ĐÃ THÊM VÀO CHI TIẾT PGM
					monCanThem.setSoLuongTon(monCanThem.getSoLuongTon() - soLuong);
					if (monCanThem.getSoLuongTon() <= 0)
						monCanThem.setTinhTrang("HẾT HÀNG");
					sp_dao.update(monCanThem);
					JOptionPane.showMessageDialog(this, "ĐÃ CẬP NHẬT SỐ LƯỢNG !");
				}
			} else {
				JOptionPane.showMessageDialog(null, monCanThem.getTenSP().toUpperCase() + " HIỆN HẾT HÀNG !");
			}
			// CẬP NHẬT 2 BẢNG (VIEW)
			update_DanhSachSanPhamDangCo(cbb_TypeProducts.getSelectedItem() + "");
			update_DanhSachSanPhamDaGoi(lbl_MaPGM.getText());
		}
	}

	private void update_DanhSachSanPhamDaGoi(String maPGM) {
		try {
			ctpgm_dao = (ChiTietPhieuGoiMonServer) Naming.lookup(URL + ChiTietPhieuGoiMonServer.class.getSimpleName());
			var list = ctpgm_dao.getAll();
			modelTableOrderedItems.setRowCount(0);
			for (ChiTietPhieuGoiMon ctpgm : list) {
				if (ctpgm.getMaPGM().getMaPGM().equalsIgnoreCase(maPGM))
					modelTableOrderedItems.addRow(new Object[] { ctpgm.getMaSP().getMaSP(), ctpgm.getMaSP().getTenSP(),
							ctpgm.getMaSP().getMaLSP().getTenLSP(), ctpgm.getSoLuong(), ctpgm.getMaSP().getGiaBan(),
							ctpgm.getTinhTrang() });
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void update_DanhSachSanPhamDangCo(String tenLSP)
			throws RemoteException, MalformedURLException, NotBoundException {
		sp_dao = (SanPhamServer) Naming.lookup(URL + SanPhamServer.class.getSimpleName());
		modelProduct.setRowCount(0);
		var list = sp_dao.getAll();
		for (SanPham sp : list) {
			if (sp.getMaLSP().getTenLSP().equalsIgnoreCase(tenLSP) && sp.getTinhTrang().equalsIgnoreCase("CÒN HÀNG"))
				modelProduct.addRow(new Object[] { sp.getMaSP(), sp.getSoLuongTon(), sp.getTenSP().toUpperCase() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (src.equals(tableProduct)) {
			int index = tableProduct.getSelectedRow();
			int count = Integer.parseInt(modelProduct.getDataVector().get(index).toArray()[1] + "");
			setMaxvalueForSpinnerCount(count);
			tableOrderedItems.setSelectionMode(0);
		} else if (src.equals(tableOrderedItems)) {
			int index = tableProduct.getSelectedRow();
			int count = Integer.parseInt(modelTableOrderedItems.getDataVector().get(index).toArray()[3] + "");
			setMaxvalueForSpinnerCount(count);
			tableProduct.setSelectionMode(0);
		}

	}

	private void setMaxvalueForSpinnerCount(int count) {
		modelSprCount.setValue(1);
		modelSprCount.setMaximum(count);
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

	@Override
	public void itemStateChanged(ItemEvent e) {
//		Object src = e.getSource();
//		ArrayList<Vector<String>> list = SanPham_Dao.getProductJoinTypeProduct();
//		if (src.equals(cbb_TypeProducts)) {
//			modelProduct.setRowCount(0);
//			String item = cbb_TypeProducts.getSelectedItem() + "";
//			if (item.equalsIgnoreCase("<TẤT CẢ>")) {
//				if (lbl_Title.getText().equalsIgnoreCase("ĐẶT ĐỒ ĂN"))
//					for (Vector<String> v : list) {
//						if ((v.get(4) + "").equalsIgnoreCase("DOAN")) {
//							modelProduct.addRow(v);
//						}
//					}
//				else if (lbl_Title.getText().equalsIgnoreCase("ĐẶT ĐỒ UỐNG"))
//					for (Vector<String> v : list) {
//						if ((v.get(4) + "").equalsIgnoreCase("DOAN")) {
//							modelProduct.addRow(v);
//						}
//					}
//				else {
//					for (Vector<String> v : list)
//						modelProduct.addRow(v);
//				}
//				updateViewTypeProduct();
//			} else {
//				for (Vector<String> v : list) {
//					if ((v.get(3) + "").equalsIgnoreCase(item)) {
//						modelProduct.addRow(v);
//					}
//				}
//			}
//		}

	}
}
