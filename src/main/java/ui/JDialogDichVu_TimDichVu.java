package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.Types;
import dao.LoaiSanPhamServer;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JDialogDichVu_TimDichVu extends JDialog implements ChangeListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductID;
	private JTextField txtProductName;
	private JRadioButton rdbStocking;
	private JRadioButton rdbOutStocks;
	private JButton btnTmKim;
	private JButton btnCancel;
	private JComboBox<String> cbbDay;
	private JComboBox<String> cbbMonth;
	private JComboBox<String> cbbYear;
	private JComboBox<String> cbbTypeProduct;
	private SpinnerNumberModel modelPriceStocking_LeftPrice;
	private SpinnerNumberModel modelPriceStocking_RightPrice;
	private SpinnerNumberModel modelPriceOutStock_LeftPrice;
	private SpinnerNumberModel modelPriceOutStock_RightPrice;
	private JSpinner spinnerPriceStocking_LeftPrice;
	private JSpinner spinnerPriceOutStocks_LeftPrice;
	
	private static final String URL = Types.URL;
	private LoaiSanPhamServer lsp_dao;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public JDialogDichVu_TimDichVu() {
		getContentPane().setBackground(new Color(222, 184, 135));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Tìm dịch vụ");
		setBounds((size.width / 8) * 2, (size.height / 7) * 2, 520, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(4, 4));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(Color.WHITE));
			panel.setBackground(Color.BLACK);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("TÌM DỊCH VỤ");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setPreferredSize(new Dimension(200, 40));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBackground(new Color(255, 255, 255));
			panel.setPreferredSize(new Dimension(300, 10));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 4));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setVgap(0);
				flowLayout.setHgap(0);
				flowLayout.setAlignment(FlowLayout.LEFT);
				panel.add(panel_1, BorderLayout.NORTH);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(Color.BLACK);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblThngTinCn = new JLabel("THÔNG TIN CẦN TÌM");
						lblThngTinCn.setBorder(new LineBorder(Color.WHITE));
						lblThngTinCn.setPreferredSize(new Dimension(200, 40));
						lblThngTinCn.setHorizontalAlignment(SwingConstants.CENTER);
						lblThngTinCn.setForeground(Color.WHITE);
						lblThngTinCn.setFont(new Font("Tahoma", Font.BOLD, 14));
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
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new GridLayout(0, 2, 10, 10));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new GridLayout(0, 1, 0, 10));
					{
						JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Loại sản phẩm");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_2 = new JLabel("Tình trạng");
						lblNewLabel_2.setOpaque(true);
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_2.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_2);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Giá bán");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Giá nhập");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Ngày nhập");
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_1.setBackground(Color.WHITE);
						panel_2.add(lblNewLabel_1);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new GridLayout(0, 1, 0, 10));
					{
						txtProductID = new JTextField();
						txtProductID.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtProductID.setColumns(10);
						panel_2.add(txtProductID);
					}
					{
						cbbTypeProduct = new JComboBox<String>();
						cbbTypeProduct.setFont(new Font("Tahoma", Font.BOLD, 16));
						panel_2.add(cbbTypeProduct);
					}
					{
						txtProductName = new JTextField();
						txtProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtProductName.setColumns(10);
						panel_2.add(txtProductName);
					}
					{
						JPanel panel_1_1 = new JPanel();
						panel_1_1.setOpaque(false);
						panel_2.add(panel_1_1);
						panel_1_1.setLayout(new GridLayout(1, 1, 4, 0));
						{
							rdbStocking = new JRadioButton("Còn hàng");
							rdbStocking.setHorizontalAlignment(SwingConstants.CENTER);
							rdbStocking.setFont(new Font("Tahoma", Font.BOLD, 16));
							rdbStocking.setBorderPainted(true);
							rdbStocking.setBorder(new LineBorder(new Color(0, 0, 0)));
							rdbStocking.setBackground(Color.WHITE);
							panel_1_1.add(rdbStocking);
						}
						{
							rdbOutStocks = new JRadioButton("Hết hàng");
							rdbOutStocks.setHorizontalAlignment(SwingConstants.CENTER);
							rdbOutStocks.setFont(new Font("Tahoma", Font.BOLD, 16));
							rdbOutStocks.setBorderPainted(true);
							rdbOutStocks.setBorder(new LineBorder(new Color(0, 0, 0)));
							rdbOutStocks.setBackground(Color.WHITE);
							panel_1_1.add(rdbOutStocks);

							ButtonGroup gr = new ButtonGroup();
							gr.add(rdbStocking);
							gr.add(rdbOutStocks);
						}
					}
					{
						JPanel panel_2_1 = new JPanel();
						panel_2_1.setOpaque(false);
						panel_2.add(panel_2_1);
						panel_2_1.setLayout(new BorderLayout(0, 0));
						{
							spinnerPriceStocking_LeftPrice = new JSpinner();
							spinnerPriceStocking_LeftPrice.addChangeListener(this);
							modelPriceStocking_LeftPrice = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
									Integer.valueOf(1000));
							spinnerPriceStocking_LeftPrice.setModel(modelPriceStocking_LeftPrice);
							spinnerPriceStocking_LeftPrice.setPreferredSize(new Dimension(100, 20));
							spinnerPriceStocking_LeftPrice.setMinimumSize(new Dimension(100, 20));
							spinnerPriceStocking_LeftPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(spinnerPriceStocking_LeftPrice, BorderLayout.WEST);
						}
						{
							JSpinner spinner = new JSpinner();
							modelPriceStocking_RightPrice = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
									Integer.valueOf(1000));
							spinner.setModel(modelPriceStocking_RightPrice);
							spinner.setPreferredSize(new Dimension(100, 20));
							spinner.setMinimumSize(new Dimension(100, 20));
							spinner.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(spinner, BorderLayout.EAST);
						}
						{
							JLabel lblNewLabel_3 = new JLabel("~");
							lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
							lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(lblNewLabel_3, BorderLayout.CENTER);
						}
					}
					{
						JPanel panel_2_1 = new JPanel();
						panel_2_1.setOpaque(false);
						panel_2.add(panel_2_1);
						panel_2_1.setLayout(new BorderLayout(0, 0));
						{
							spinnerPriceOutStocks_LeftPrice = new JSpinner();
							spinnerPriceOutStocks_LeftPrice.addChangeListener(this);
							spinnerPriceOutStocks_LeftPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
							modelPriceOutStock_LeftPrice = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
									Integer.valueOf(1000));
							spinnerPriceOutStocks_LeftPrice.setModel(modelPriceOutStock_LeftPrice);
							spinnerPriceOutStocks_LeftPrice.setPreferredSize(new Dimension(100, 20));
							spinnerPriceOutStocks_LeftPrice.setMinimumSize(new Dimension(100, 20));
							panel_2_1.add(spinnerPriceOutStocks_LeftPrice, BorderLayout.WEST);
						}
						{
							JSpinner spinner = new JSpinner();
							modelPriceOutStock_RightPrice = new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), null,
									Integer.valueOf(1000));
							spinner.setModel(modelPriceOutStock_RightPrice);
							spinner.setPreferredSize(new Dimension(100, 20));
							spinner.setMinimumSize(new Dimension(100, 20));
							spinner.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(spinner, BorderLayout.EAST);
						}
						{
							JLabel lblNewLabel_3 = new JLabel("~");
							lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
							lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
							panel_2_1.add(lblNewLabel_3, BorderLayout.CENTER);
						}
					}
					{
						JPanel panel_2_1 = new JPanel();
						panel_2_1.setOpaque(false);
						panel_2.add(panel_2_1);
						panel_2_1.setLayout(new GridLayout(1, 0, 4, 0));
						{
							cbbDay = new JComboBox<String>();
							cbbDay.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(cbbDay);
						}
						{
							cbbMonth = new JComboBox<String>();
							cbbMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(cbbMonth);
						}
						{
							cbbYear = new JComboBox<String>();
							cbbYear.setFont(new Font("Tahoma", Font.BOLD, 16));
							panel_2_1.add(cbbYear);
						}
					}
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
				btnTmKim = new JButton("TÌM KIẾM");
				btnTmKim.addActionListener(this);
				btnTmKim.setPreferredSize(new Dimension(200, 30));
				btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnTmKim.setActionCommand("OK");
				buttonPane.add(btnTmKim);
			}
			{
				btnCancel = new JButton("HỦY");
				btnCancel.addActionListener(e -> {this.hide();});
				btnCancel.setPreferredSize(new Dimension(100, 30));
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		updateViewCbbTypeProduct();
		setValueViewForCbbsDate();
	}

	private void setValueViewForCbbsDate() {
		for (int i = 2000; i <= LocalDate.now().getYear(); i++) {
			cbbYear.addItem(""+i);			
		}
		for (int i = 1; i < 32; i++) {
			cbbDay.addItem(i+"");
			if(i<=12) {
				cbbMonth.addItem(i+"");
			}
		}
		cbbYear.setSelectedItem(LocalDate.now().getYear()+"");
		cbbDay.setSelectedItem(LocalDate.now().getDayOfMonth()+"");
		cbbMonth.setSelectedItem(LocalDate.now().getMonthValue()+"");
	}

	private void updateViewCbbTypeProduct() {
		try {
			lsp_dao = (LoaiSanPhamServer) Naming.lookup(URL+"LoaiSanPhamServer");
			cbbTypeProduct.removeAllItems();
			cbbTypeProduct.addItem("TẤT CẢ");
			for (var lsp : lsp_dao.getAll()) {
				cbbTypeProduct.addItem(lsp.getTenLSP());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object src = e.getSource();
		if(src.equals(spinnerPriceStocking_LeftPrice)) {
			if (Integer.parseInt(modelPriceStocking_LeftPrice.getValue()+"") > Integer.parseInt(modelPriceStocking_RightPrice.getValue()+"")) {
				modelPriceStocking_RightPrice.setValue(modelPriceStocking_LeftPrice.getValue());
				modelPriceStocking_RightPrice.setMinimum(Integer.parseInt(modelPriceStocking_LeftPrice.getValue()+""));
			}else {
				modelPriceStocking_RightPrice.setMinimum(Integer.parseInt(modelPriceStocking_LeftPrice.getValue()+""));
			}
		}else if(src.equals(spinnerPriceOutStocks_LeftPrice)) {
			if (Integer.parseInt(modelPriceOutStock_LeftPrice.getValue()+"") > Integer.parseInt(modelPriceOutStock_RightPrice.getValue()+"")) {
				modelPriceOutStock_RightPrice.setValue(modelPriceOutStock_LeftPrice.getValue());
				modelPriceOutStock_RightPrice.setMinimum(Integer.parseInt(modelPriceOutStock_LeftPrice.getValue()+""));
			}else {
				modelPriceOutStock_RightPrice.setMinimum(Integer.parseInt(modelPriceOutStock_LeftPrice.getValue()+""));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnTmKim)) {
			timKiem();
		}
		
	}

	private void timKiem() {
		
	}

}
