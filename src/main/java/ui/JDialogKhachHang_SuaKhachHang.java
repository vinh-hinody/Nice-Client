package ui;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import entity.KhachHang;
import ui.GD_KhachHang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.lang.model.util.Elements.Origin;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JDialogKhachHang_SuaKhachHang extends JDialog {
	private static GD_KhachHang gd_KhachHang;
	
	private KhachHang khachHang;

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblSDT;
	private JLabel lblDiaChi;
	private JLabel lblHoTen;
	private JLabel lblEmail;
	private JLabel lblTinhTrang;
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
	private JComboBox comboBox;

	/**
	 * Create the dialog.
	 */
	public JDialogKhachHang_SuaKhachHang(GD_KhachHang gd_KhachHang) {
		this.gd_KhachHang = gd_KhachHang;
		setTitle("KHÁCH HÀNG");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(size.width/3, size.height/9, size.width/3, size.height/9*7);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(4);
		borderLayout.setHgap(4);
		setModal(true);
		getContentPane().setLayout(borderLayout);
		{
			JPanel pnl_South = new JPanel();
			pnl_South.setBorder(new LineBorder(Color.WHITE));
			pnl_South.setBackground(Color.BLACK);
			getContentPane().add(pnl_South, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("SỬA");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					KhachHang_Dao khachHang_Dao = new KhachHang_Dao();
					if(kiemTraDuLieuDaThayDoi()) {
						if(!txtSDT.getText().matches("0\\d{9}")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại đủ 10 số và bắt đầu từ số 0!");
							txtSDT.setText("");
							txtSDT.requestFocus();
							return;
						}else if(!txtHoTen.getText().matches("^[\\p{L}\\s_-]+$")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên khách không chứa kí tự đặt biệt và số!");
							return;
						}else if(!txtEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email: textInput.@gmail.com!");
						}
//						String sdtMoi = txtSDT.getText();
//						String hoTenMoi =  txtHoTen.getText();
//						Date ngaySinhMoi = new Date(dateChooser_NgaySinh.getDate().getTime());
//						String diaChiMoi =  txtDiaChi.getText();
//						String emailMoi = txtEmail.getText();
//						String tinhTrangMoi = (String) comboBox.getSelectedItem();
//						
//						KhachHang khachHang = new KhachHang(sdtMoi, hoTenMoi, ngaySinhMoi, diaChiMoi, emailMoi, tinhTrangMoi);
//						System.out.println(khachHang);
//						khachHang_Dao.updateKhachHang(khachHang);
//						List<KhachHang> khList = khachHang_Dao.getAllList();
//						JDialogKhachHang_SuaKhachHang.gd_KhachHang.displayDataInTable(khList);
						suaThongTinKhachHang();
						
						JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!");
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Dữ liệu còn nguyên vẹn chưa được sửa!");
					}
						
				}
			});
			btnNewButton.setFocusable(false);
			btnNewButton.setIcon(null);
			
			JButton btnNewButton_2 = new JButton("THOÁT");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_2.setFocusable(false);
			btnNewButton_2.setIcon(null);
			pnl_South.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
			pnl_South.add(btnNewButton);
			pnl_South.add(btnNewButton_2);
		}
		
		lblNewLabel = new JLabel("SỬA KHÁCH HÀNG");
		lblNewLabel.setBorder(new LineBorder(Color.WHITE));
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
		
		lblTinhTrang = new JLabel("Địa chỉ");
		lblTinhTrang.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTinhTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblTinhTrang);
		
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
		
		lblDiaChi = new JLabel("Tình trạng");
		lblDiaChi.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblDiaChi);
		
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
		
//		dateChooser_NgaySinh = new JDateChooser();
//		dateChooser_NgaySinh.setDateFormatString("yyyy-MM-dd");
//		dateChooser_NgaySinh.setToolTipText("");
//		panel_2.add(dateChooser_NgaySinh);
//		
		txtEmail = new JTextField(30);
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtEmail);
		
		comboBox = new JComboBox();
		comboBox.setOpaque(false);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "KHÁCH HÀNG CHƯA ĐĂNG KÍ", "KHÁCH HÀNG HẠN CHẾ", "KHÁCH HÀNG THÀNH VIÊN"}));
		panel_2.add(comboBox);
		
		lblKhachHang_10 = new JLabel("");
		lblKhachHang_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblKhachHang_10);
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 150));
		panel_4.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1, BorderLayout.CENTER);

	}
    /**
     * @wbp.parser.constructor
     */
    public JDialogKhachHang_SuaKhachHang(GD_KhachHang gd_KhachHang, KhachHang khachHang) {
        this(gd_KhachHang);
        this.khachHang = khachHang;
        if (khachHang != null) {
            loadDataToFields();
        }
    }
	private void loadDataToFields() {
        txtSDT.setText(khachHang.getSdt());
        txtHoTen.setText(khachHang.getHoTenKH());
        txtDiaChi.setText(khachHang.getDiaChi());     
//        dateChooser_NgaySinh.setDate((java.sql.Date) khachHang.getNgaySinh());
        txtEmail.setText(khachHang.getEmail());
        comboBox.setSelectedItem(khachHang.getTinhTrang());
    }
    private boolean kiemTraDuLieuDaThayDoi() {
        String sdtMoi = txtSDT.getText();
        String hoTenMoi = txtHoTen.getText();
        String diaChiMoi = txtDiaChi.getText();
//        java.sql.Date ngaySinhMoi = new java.sql.Date(dateChooser_NgaySinh.getDate().getTime());
        String emailMoi = txtEmail.getText();
        String tinhTrangMoi = (String) comboBox.getSelectedItem();

        // So sánh với dữ liệu hiện tại của khách hàng
        return !sdtMoi.equals(khachHang.getSdt()) ||
               !hoTenMoi.equals(khachHang.getHoTenKH()) ||
               !diaChiMoi.equals(khachHang.getDiaChi()) ||
//               !Objects.equals(ngaySinhMoi, khachHang.getNgaySinh()) ||
               !emailMoi.equals(khachHang.getEmail()) ||
               !tinhTrangMoi.equals(khachHang.getTinhTrang());
    }
    public void suaThongTinKhachHang() {
    	 String sdtMoi = txtSDT.getText();
    	 String hoTenMoi = txtHoTen.getText();
    	 String diaChiMoi = txtDiaChi.getText();
//    	 java.sql.Date ngaySinhMoi = new java.sql.Date(dateChooser_NgaySinh.getDate().getTime());
    	 String emailMoi = txtEmail.getText();
       String tinhTrangMoi = (String) comboBox.getSelectedItem();
         
         KhachHang khachHang = new KhachHang(sdtMoi, hoTenMoi, null, diaChiMoi, emailMoi, tinhTrangMoi);
         
//         KhachHang_Dao khachHang_Dao = new KhachHang_Dao();
//         khachHang_Dao.updateKhachHang(khachHang);
//         List<KhachHang> khachHangList = khachHang_Dao.getAllList();
//         gd_KhachHang.displayDataInTable(khachHangList);
    }
}
	

