package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import constant.Types;
import dao.KhachHangServer;
import entity.KhachHang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class JDialogKhachHang_TimKhachHang extends JDialog {
	@SuppressWarnings("unused")
	private static GD_KhachHang gd_KhachHang;

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblSDT;
	private JLabel lblTinhTrang;
	private JLabel lblHoTen;
	private JLabel lblEmail;
	private JLabel lblDiaChi;
	private JLabel lblNgaySinh;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JLabel lblKhachHang_9;
	private JLabel lblKhachHang_10;
	private JPanel panel_4;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JTextField txtHoTen;
	private JComboBox<String> comboBoxTinhTrang;

	private KhachHangServer kh_dao;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("static-access")
	public JDialogKhachHang_TimKhachHang(GD_KhachHang gd_KhachHang) {
		this.gd_KhachHang = gd_KhachHang;
		setTitle("KHÁCH HÀNG");
		setModal(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(size.width/3, size.height/9, size.width/3, size.height/9*7);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(4);
		borderLayout.setHgap(4);
		getContentPane().setLayout(borderLayout);
		{
			JPanel pnl_South = new JPanel();
			pnl_South.setBorder(new LineBorder(Color.WHITE));
			pnl_South.setBackground(Color.BLACK);
			getContentPane().add(pnl_South, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("TÌM KIẾM");
			btnNewButton.addActionListener(new ActionListener() {
				private String URL = Types.URL;

				public void actionPerformed(ActionEvent e) {
					String sdt = txtSDT.getText();
					String hoTen = txtHoTen.getText();
					
//					Date chonDate = (Date) dateChooserNgaySinh.getDate();
//					int nam = LocalDate.ofInstant(chonDate.toInstant(), ZoneId.systemDefault()).getYear();
//					//Chuyển đổi năm thành định dạng yyyy
//					String convertNam = String.valueOf(nam);
					
					String diaChi = txtDiaChi.getText();
					String tinhTrang = (String) comboBoxTinhTrang.getSelectedItem();
					
					if( sdt.isBlank() && hoTen.isBlank() && diaChi.isBlank() && (tinhTrang == null)){
						JOptionPane.showMessageDialog(null, "Vui Lòng nhập thông tin cần tìm kiếm!");
						return;
						
					}else{
						
						//Tìm kiếm theo trường dữ kiệu nhập vào 
						List<KhachHang> resultList = new ArrayList<KhachHang>();
						try {
							kh_dao = (KhachHangServer) Naming.lookup(URL  + KhachHangServer.class.getSimpleName());
							resultList = kh_dao.getAll();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if( !sdt.isEmpty()) {
							resultList = resultList.stream()
									.filter(khachHang  -> khachHang.getSdt().contains(sdt))
									.collect(Collectors.toList());
						}if(!hoTen.isEmpty()) {
							resultList = resultList.stream()
									.filter(khachHang -> khachHang.getHoTenKH().toLowerCase().contains(hoTen.toLowerCase()))
									.collect(Collectors.toList());
						}if(!diaChi.isEmpty()) {
							resultList = resultList.stream()
									.filter(khachHang -> khachHang.getDiaChi().toLowerCase().contains(diaChi.toLowerCase()))
									.collect(Collectors.toList());
						}if(tinhTrang != null && !tinhTrang.isEmpty()) {
							resultList = resultList.stream()
									.filter(khachHang -> khachHang.getTinhTrang().equalsIgnoreCase(tinhTrang))
									.collect(Collectors.toList());
//						}if(!convertNam.isEmpty()) {
//							resultList = resultList.stream()
//									.filter(khachHang_-> khachHang_.getNgaySinh().equals(convertNam))
//									.collect(Collectors.toList());
						}
						
						//Hiển thị kết quả tìm được
						JOptionPane.showMessageDialog(null, "Tìm thành công!");
						gd_KhachHang.displayDataInTable(resultList);
						
					}
				}
			});
			btnNewButton.setFocusable(false);
//			btnNewButton.setIcon(new ImageIcon(JDialogKhachHang_TimKhachHang.class.getResource("/img_tacvu/search11.png")));
			
			JButton btnNewButton_2 = new JButton("THOÁT");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_2.setFocusable(false);
//			btnNewButton_2.setIcon(new ImageIcon(JDialogKhachHang_TimKhachHang.class.getResource("/img_tacvu/thoat.png")));
			pnl_South.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
			pnl_South.add(btnNewButton);
			pnl_South.add(btnNewButton_2);
		}
		
		lblNewLabel = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblNewLabel.setBorder(new LineBorder(Color.WHITE));
//		lblNewLabel.setIcon(new ImageIcon(JDialogKhachHang_TimKhachHang.class.getResource("/img_tacvu/search11.png")));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setPreferredSize(new Dimension(200, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.setPreferredSize(new Dimension(300, 10));
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(4, 4));
		
		panel = new JPanel();
		panel_4.add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setLayout(new BorderLayout(4, 4));
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 10));
		
		lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSDT.setHorizontalAlignment(SwingConstants.CENTER);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblSDT);
		
		lblHoTen = new JLabel("Họ và tên");
		lblHoTen.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblHoTen);
		
		lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblDiaChi);
		
		lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNgaySinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNgaySinh);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmail);
		
		lblTinhTrang = new JLabel("Tình Trạng");
		lblTinhTrang.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTinhTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblTinhTrang);
		
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
		
		txtDiaChi = new JTextField(30);
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtDiaChi);
		
	
		
		txtEmail = new JTextField(30);
		txtEmail.setEnabled(false);
		txtEmail.setFocusable(false);
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtEmail);
		
		comboBoxTinhTrang = new JComboBox<String>();
		comboBoxTinhTrang.setModel(new DefaultComboBoxModel<String>(new String[] {"", "KHÁCH HÀNG CHƯA ĐĂNG KÍ", "KHÁCH HÀNG HẠN CHẾ", "KHÁCH HÀNG THÀNH VIÊN"}));
		panel_2.add(comboBoxTinhTrang);
		
		lblKhachHang_10 = new JLabel("");
		lblKhachHang_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblKhachHang_10);
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 150));
		panel_4.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("");
//		lblNewLabel_1.setIcon(new ImageIcon(JDialogKhachHang_TimKhachHang.class.getResource("/img_tacvu/KhachHang14.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1, BorderLayout.CENTER);

	}

//	public JDialogKhachHang_TimKhachHang() {
//		// TODO Auto-generated constructor stub
//	}
}
