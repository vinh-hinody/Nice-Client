package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.Types;
import dao.LoaiDichVuServer;
import dao.LoaiSanPhamServer;
import dao.SanPhamServer;
import entity.LoaiDichVu;
import entity.LoaiSanPham;
import entity.SanPham;

import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class JDialogDichVu_SuaDichVu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_ProductID;
	private JTextField txt_DatePubblish;
	private JTextField txt_GiaNhap;
	private JTextField txt_GiaBan;
	private JTextField txt_TSP;
	private GD_DichVu home;
	private SanPham product;
	private JComboBox<String> cbb_LSP;
	private JComboBox<String> cbb_LDV;
	private JRadioButton rdb_ConHang;
	private JRadioButton rdb_HetHang;
	private JSpinner spn_SLuong;
	private SpinnerNumberModel modelCount;
	private JButton btn_Save;

	private LoaiSanPhamServer lsp_dao;
	private LoaiDichVuServer ldv_dao;
	private SanPhamServer sp_dao;
	private JButton btn_Cancel;
	private static final String URL = Types.URL;
	/**
	 * Create the dialog.
	 * 
	 * @param gd_DichVu
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public JDialogDichVu_SuaDichVu(GD_DichVu gd_DichVu, SanPham sanPham) throws RemoteException, MalformedURLException, NotBoundException {

		lsp_dao = (LoaiSanPhamServer) Naming.lookup(URL+"LoaiSanPhamServer");
		ldv_dao = (LoaiDichVuServer) Naming.lookup(URL+"LoaiDichVuServer");
		sp_dao = (SanPhamServer) Naming.lookup(URL+"SanPhamServer");

		this.home = gd_DichVu;
		this.product = sanPham;

		getContentPane().setBackground(new Color(222, 184, 135));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Sửa dịch vụ");
		setBounds((size.width / 8) * 2, (size.height / 7) * 2, 520, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(4, 4));
		{
			JPanel pnl_Title = new JPanel();
			pnl_Title.setBorder(new LineBorder(Color.WHITE));
			pnl_Title.setBackground(Color.BLACK);
			FlowLayout fl_pnl_Title = (FlowLayout) pnl_Title.getLayout();
			fl_pnl_Title.setVgap(0);
			fl_pnl_Title.setHgap(0);
			contentPanel.add(pnl_Title, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("SỬA DỊCH VỤ");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setPreferredSize(new Dimension(200, 40));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pnl_Title.add(lblNewLabel);
			}
		}
		{
			JPanel pnl_Centrel = new JPanel();
			pnl_Centrel.setOpaque(false);
			pnl_Centrel.setBackground(new Color(255, 255, 255));
			pnl_Centrel.setPreferredSize(new Dimension(300, 10));
			contentPanel.add(pnl_Centrel, BorderLayout.CENTER);
			pnl_Centrel.setLayout(new BorderLayout(0, 4));
			{
				JPanel pnl_InformationTitle = new JPanel();
				pnl_InformationTitle.setBackground(Color.WHITE);
				pnl_InformationTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
				FlowLayout fl_pnl_InformationTitle = (FlowLayout) pnl_InformationTitle.getLayout();
				fl_pnl_InformationTitle.setVgap(0);
				fl_pnl_InformationTitle.setHgap(0);
				fl_pnl_InformationTitle.setAlignment(FlowLayout.LEFT);
				pnl_Centrel.add(pnl_InformationTitle, BorderLayout.NORTH);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(Color.BLACK);
					pnl_InformationTitle.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblThngTinCn = new JLabel("NHẬP THÔNG TIN MỚI");
						lblThngTinCn.setBorder(new LineBorder(Color.WHITE));
						lblThngTinCn.setPreferredSize(new Dimension(250, 40));
						lblThngTinCn.setHorizontalAlignment(SwingConstants.CENTER);
						lblThngTinCn.setForeground(Color.WHITE);
						lblThngTinCn.setFont(new Font("Tahoma", Font.BOLD, 16));
						panel_2.add(lblThngTinCn);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel_1.setBackground(new Color(255, 250, 250));
				panel_1.setPreferredSize(new Dimension(250, 10));
				panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
				pnl_Centrel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new GridLayout(0, 2, 10, 10));
				{
					JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					txt_ProductID = new JTextField(product.getMaSP());
					txt_ProductID.setHorizontalAlignment(SwingConstants.CENTER);
					txt_ProductID.setFont(new Font("Dialog", Font.BOLD, 16));
					txt_ProductID.setColumns(10);
					txt_ProductID.setEditable(false);
					panel_1.add(txt_ProductID);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Loại sản phẩm");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					cbb_LSP = new JComboBox<String>();
					cbb_LSP.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(cbb_LSP);
					updateViewForCBB_TypeProduct();
					cbb_LSP.setSelectedItem(sanPham.getMaLSP().getTenLSP().toUpperCase());
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Loại dịch vụ");
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					lblNewLabel_1.setBackground(Color.WHITE);
					panel_1.add(lblNewLabel_1);
				}
				{
					cbb_LDV = new JComboBox<String>();
					cbb_LDV.setFont(new Font("Dialog", Font.BOLD, 16));
					updateViewForCBB_TypeService();
					panel_1.add(cbb_LDV);
					cbb_LDV.setSelectedItem(sanPham.getMaLSP().getMaLDV().getTen());
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					txt_TSP = new JTextField(product.getTenSP());
					txt_TSP.setMargin(new Insets(2, 10, 2, 2));
					txt_TSP.setHorizontalAlignment(SwingConstants.LEFT);
					txt_TSP.setFont(new Font("Dialog", Font.BOLD, 16));
					txt_TSP.setColumns(10);
					panel_1.add(txt_TSP);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Tình trạng");
					lblNewLabel_2.setBackground(Color.WHITE);
					lblNewLabel_2.setOpaque(true);
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_2);
				}
				{
					JPanel panel_1_1 = new JPanel();
					panel_1_1.setOpaque(false);
					panel_1.add(panel_1_1);
					panel_1_1.setLayout(new GridLayout(1, 1, 4, 0));
					{
						rdb_ConHang = new JRadioButton("Còn hàng");
						rdb_ConHang.setBorderPainted(true);
						rdb_ConHang.setBorder(new LineBorder(new Color(0, 0, 0)));
						rdb_ConHang.setHorizontalAlignment(SwingConstants.CENTER);
						rdb_ConHang.setBackground(Color.WHITE);
						rdb_ConHang.setFont(new Font("Dialog", Font.BOLD, 16));
						panel_1_1.add(rdb_ConHang);
					}
					{
						rdb_HetHang = new JRadioButton("Hết hàng");
						rdb_HetHang.setBorderPainted(true);
						rdb_HetHang.setBorder(new LineBorder(new Color(0, 0, 0)));
						rdb_HetHang.setHorizontalAlignment(SwingConstants.CENTER);
						rdb_HetHang.setBackground(Color.WHITE);
						rdb_HetHang.setFont(new Font("Dialog", Font.BOLD, 16));
						panel_1_1.add(rdb_HetHang);
					}
					ButtonGroup gr = new ButtonGroup();
					gr.add(rdb_ConHang);
					gr.add(rdb_HetHang);
					if (product.getTinhTrang().equalsIgnoreCase(Types.PRODUCT_STATIC_STOCKING))
						rdb_ConHang.setSelected(true);
					else
						rdb_HetHang.setSelected(true);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Số lượng tồn");
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					lblNewLabel_1.setBackground(Color.WHITE);
					panel_1.add(lblNewLabel_1);
				}
				{
					spn_SLuong = new JSpinner();
					modelCount = new SpinnerNumberModel(1, 0, 10000, 1);
					spn_SLuong.setModel(modelCount);
					spn_SLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
					panel_1.add(spn_SLuong);
					modelCount.setValue(product.getSoLuongTon());
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Giá nhập");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					txt_GiaNhap = new JTextField(product.getGiaNhap() + "");
					txt_GiaNhap.setMargin(new Insets(2, 10, 2, 2));
					txt_GiaNhap.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(txt_GiaNhap);
					txt_GiaNhap.setColumns(10);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Giá bán");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					txt_GiaBan = new JTextField(product.getGiaBan() + "");
					txt_GiaBan.setMargin(new Insets(2, 10, 2, 2));
					txt_GiaBan.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(txt_GiaBan);
					txt_GiaBan.setColumns(10);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Ngày nhập");
					lblNewLabel_1.setBackground(Color.WHITE);
					lblNewLabel_1.setOpaque(true);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
					panel_1.add(lblNewLabel_1);
				}
				{
					txt_DatePubblish = new JTextField(product.getNgayNhap() + "");
					txt_DatePubblish.setHorizontalAlignment(SwingConstants.CENTER);
					txt_DatePubblish.setFont(new Font("Dialog", Font.BOLD, 16));
					txt_DatePubblish.setColumns(10);
					panel_1.add(txt_DatePubblish);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			buttonPane.setBorder(new LineBorder(Color.WHITE));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
			{
				btn_Save = new JButton("LƯU");
				btn_Save.addActionListener(this);
				btn_Save.setPreferredSize(new Dimension(200, 30));
				btn_Save.setFont(new Font("Tahoma", Font.BOLD, 17));
				btn_Save.setActionCommand("OK");
				buttonPane.add(btn_Save);
			}
			{
				btn_Cancel = new JButton("HỦY");
				btn_Cancel.addActionListener(this);
				btn_Cancel.setPreferredSize(new Dimension(100, 30));
				btn_Cancel.setFont(new Font("Tahoma", Font.BOLD, 17));
				btn_Cancel.setActionCommand("Cancel");
				buttonPane.add(btn_Cancel);
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected void close() {
		this.hide();
	}

	private void updateViewForCBB_TypeService() {
		try {
			cbb_LDV.removeAllItems();
			for (LoaiDichVu a : ldv_dao.getAll()) {
				cbb_LDV.addItem(a.getTen());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void updateViewForCBB_TypeProduct(){
		try {
			cbb_LSP.removeAllItems();
			for (LoaiSanPham l : lsp_dao.getAll()) {
				cbb_LSP.addItem(l.getTenLSP().toUpperCase());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn_Save)) {
			if (suaSanPham()) {
				JOptionPane.showMessageDialog(this.home, "CẬP NHẬT THÔNG TIN THÀNH CÔNG");
				this.close();
			}else
				JOptionPane.showMessageDialog(this, "CẬP NHẬT THẤT BẠI");
			this.home.updateView_DanhSachSP();
		} else if (e.getSource().equals(btn_Cancel)) {
			close();
		}
	}

	private boolean suaSanPham() {
		var a = new SanPham();
		try {
			var dv = ldv_dao.findName(cbb_LDV.getSelectedItem() + "");
			var lsp = lsp_dao.findName(cbb_LSP.getSelectedItem() + "");

			if (dv == null) {
				dv = new LoaiDichVu((ldv_dao.getAll().size() + 1) + "", cbb_LDV.getSelectedItem() + "");
				ldv_dao.add(dv);
			}

			if (lsp == null) {
				lsp = new LoaiSanPham((lsp_dao.getAll().size() + 1) + "", cbb_LSP.getSelectedItem() + "", dv);
				lsp_dao.add(lsp);
			
				a.setMaSP(this.product.getMaSP());
				a.setTenSP(txt_TSP.getText());
				a.setMaLSP(lsp);
				a.setSoLuongTon(Integer.valueOf(modelCount.getValue() + ""));
				a.setGiaNhap(Double.valueOf(txt_GiaNhap.getText()));
				a.setGiaBan(Double.valueOf(txt_GiaBan.getText()));
				a.setNgayNhap(LocalDate.now());

				if (rdb_ConHang.isSelected())
					a.setTinhTrang(Types.PRODUCT_STATIC_STOCKING);
				else
					a.setTinhTrang(Types.PRODUCT_STATIC_OUTSTOCK);
				System.out.println(a);
				int n = JOptionPane.showConfirmDialog(null, "Xác nhận sửa phòng ?", "Cập nhật thông tin sản phẩm",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					if(sp_dao.update(a)) {
						this.home.updateView_DanhSachSP();
						return true;
					}
				}
			} else {
				a.setMaSP(this.product.getMaSP());
				a.setTenSP(txt_TSP.getText());
				a.setMaLSP(lsp);
				a.setSoLuongTon(Integer.valueOf(modelCount.getValue() + ""));
				a.setGiaNhap(Double.valueOf(txt_GiaNhap.getText()));
				a.setGiaBan(Double.valueOf(txt_GiaBan.getText()));
				a.setNgayNhap(LocalDate.now());

				if (rdb_ConHang.isSelected())
					a.setTinhTrang(Types.PRODUCT_STATIC_STOCKING);
				else
					a.setTinhTrang(Types.PRODUCT_STATIC_OUTSTOCK);
				System.out.println(a);
				int n = JOptionPane.showConfirmDialog(null, "Xác nhận sửa phòng ?", "Cập nhật thông tin sản phẩm",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					if(sp_dao.update(a)) {
						this.home.updateView_DanhSachSP();
						return true;
					}
				}
			}
				

			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

}
