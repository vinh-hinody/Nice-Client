package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.KhachHangServer;
import entity.KhachHang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import constant.Types;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JDialogKhachHang_ThemKhachHang extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String URL = Types.URL;

	private GD_KhachHang home;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblKhachHang_1;
	private JLabel lblKhachHang_2;
	private JLabel lblKhachHang_3;
	private JLabel lblKhachHang_5;
	private JLabel lblKhachHang_6;
	private JLabel lblKhachHang_7;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JLabel lblKhachHang_9;
	private JLabel lblKhachHang_10;
	private JPanel panel_4;
	private JLabel lblNewLabel_1;
	private JTextField txtHoTen;
	private JComboBox<String> cbb_TinhTrang;

	private boolean nguon = true;

	private KhachHangServer kh_dao;
	private JPanel panel_5;
	private JComboBox<String> cbb_Ngay;
	private JComboBox<String> cbb_Thang;
	private JTextField txt_Nam;
	private JPanel panel_3;

	/**
	 * Create the dialog.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public JDialogKhachHang_ThemKhachHang(GD_KhachHang home) throws RemoteException, MalformedURLException, NotBoundException {

		this.kh_dao = (KhachHangServer) Naming.lookup(URL + KhachHangServer.class.getSimpleName());
		this.home = home;
		setTitle("KHÁCH HÀNG");
		setModal(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(size.width / 3, size.height / 9, size.width / 3, size.height / 9 * 7);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(4);
		borderLayout.setHgap(4);
		getContentPane().setLayout(borderLayout);
		{
			JPanel pnl_South = new JPanel();
			pnl_South.setPreferredSize(new Dimension(10, 80));
			pnl_South.setBorder(new LineBorder(Color.WHITE));
			pnl_South.setBackground(Color.BLACK);
			getContentPane().add(pnl_South, BorderLayout.SOUTH);

			JButton btnNewButton = new JButton("THÊM");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnNewButton.setPreferredSize(new Dimension(120, 60));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						themKhachHang();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setFocusable(false);
//			btnNewButton.setIcon(new ImageIcon(JDialogKhachHang_ThemKhachHang.class.getResource("/img_tacvu/them.png")));

			JButton btnNewButton_2 = new JButton("THOÁT");
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnNewButton_2.setPreferredSize(new Dimension(120, 60));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_2.setFocusable(false);
//			btnNewButton_2.setIcon(new ImageIcon(JDialogKhachHang_ThemKhachHang.class.getResource("/img_tacvu/thoat.png")));
			pnl_South.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
			pnl_South.add(btnNewButton);
			pnl_South.add(btnNewButton_2);
		}

		lblNewLabel = new JLabel("THÊM KHÁCH HÀNG");
		lblNewLabel.setIcon(new ImageIcon("F:\\JAVA\\Couse Project\\Nice_JPA1\\src\\main\\java\\img\\Plus.png"));
		lblNewLabel.setBorder(new LineBorder(Color.WHITE));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setPreferredSize(new Dimension(200, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(300, 10));
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(10, 10));

		panel = new JPanel();
		panel_4.add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setLayout(new BorderLayout(4, 4));

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 10));

		lblKhachHang_1 = new JLabel("Số điện thoại");
		lblKhachHang_1.setOpaque(true);
		lblKhachHang_1.setBackground(Color.CYAN);
		lblKhachHang_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_1);

		lblKhachHang_3 = new JLabel("Họ và tên");
		lblKhachHang_3.setOpaque(true);
		lblKhachHang_3.setBackground(Color.CYAN);
		lblKhachHang_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_3);

		lblKhachHang_7 = new JLabel("Ngày sinh");
		lblKhachHang_7.setOpaque(true);
		lblKhachHang_7.setBackground(Color.CYAN);
		lblKhachHang_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_7);

		lblKhachHang_6 = new JLabel("Địa chỉ");
		lblKhachHang_6.setOpaque(true);
		lblKhachHang_6.setBackground(Color.CYAN);
		lblKhachHang_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_6);

		lblKhachHang_5 = new JLabel("Email");
		lblKhachHang_5.setOpaque(true);
		lblKhachHang_5.setBackground(Color.CYAN);
		lblKhachHang_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_5);

		lblKhachHang_2 = new JLabel("Tình trạng");
		lblKhachHang_2.setOpaque(true);
		lblKhachHang_2.setBackground(Color.CYAN);
		lblKhachHang_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblKhachHang_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblKhachHang_2);

		lblKhachHang_9 = new JLabel("");
		lblKhachHang_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblKhachHang_9);

		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 10));

		txtSDT = new JTextField(30);
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtSDT);

		txtHoTen = new JTextField();
		txtHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtHoTen);
		txtHoTen.setColumns(10);

		panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 5, 5));

		cbb_Ngay = new JComboBox<String>();
		cbb_Ngay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbb_Ngay.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		panel_5.add(cbb_Ngay);

		cbb_Thang = new JComboBox<String>();
		cbb_Thang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbb_Thang.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		panel_5.add(cbb_Thang);

		txt_Nam = new JTextField();
		txt_Nam.setText("2024");
		txt_Nam.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Nam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(txt_Nam);
		txt_Nam.setColumns(10);

		txtDiaChi = new JTextField(30);
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtDiaChi);

		txtEmail = new JTextField(30);
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtEmail);

		cbb_TinhTrang = new JComboBox<String>();
		cbb_TinhTrang.setOpaque(false);
		cbb_TinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbb_TinhTrang.setModel(new DefaultComboBoxModel<String>(new String[] { "HỘI VIÊN", "VÃNG LAI", "HẠN CHẾ" }));
		panel_2.add(cbb_TinhTrang);

		lblKhachHang_10 = new JLabel("");
		lblKhachHang_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblKhachHang_10);

		panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 5, 0, 5));
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("F:\\JAVA\\Couse Project\\Nice_JPA1\\src\\main\\java\\img\\Customer.png"));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setPreferredSize(new Dimension(0, 100));
		// lblNewLabel_1.setIcon(new
		// ImageIcon(JDialogKhachHang_ThemKhachHang.class.getResource("/img_tacvu/KhachHang14.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

	}

	protected void themKhachHang() throws RemoteException {
		String sdt = txtSDT.getText();
		String hoTenKH = txtHoTen.getText();
		String diaChi = txtDiaChi.getText();
		String email = txtEmail.getText();
		String tinhTrang = (String) cbb_TinhTrang.getSelectedItem();

		if (sdt.isBlank() || hoTenKH.isBlank() || diaChi.isBlank() || email.isBlank() || tinhTrang.isBlank()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin khách hàng!");
			return;
		} else {
			if (kh_dao.findB(sdt) != null) {
				JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại vui lòng nhập lại!");
				txtSDT.setText("");
				txtSDT.requestFocus();
				return;
			} else if (!sdt.matches("0\\d{9}")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại đủ 10 số và bắt đầu từ số 0!");
				txtSDT.setText("");
				txtSDT.requestFocus();
				return;
			} else if (!hoTenKH.matches("^[\\p{L}\\s_-]+$")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên khách không chứa kí tự đặt biệt và số!");
				return;
			} else if (!email.matches(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email: textInput.@gmail.com!");
			} else {
				KhachHang khachHangNew = new KhachHang(sdt, hoTenKH, null, diaChi, email, tinhTrang);
				kh_dao.add(khachHangNew);
				List<KhachHang> khList = kh_dao.getAll();
				if (nguon) {
					home.displayDataInTable(khList);
					nguon = true;
				}
				this.hide();
				JOptionPane.showInternalMessageDialog(null, "Thêm Thành công");
			}
		}

	}

	public void setTextWhenRegisterNewCustomer(String sdt) {
		txtSDT.setText(sdt);
	}

	public void setupWhenAddFromBookRoom() {
		this.nguon = false;

	}

}
