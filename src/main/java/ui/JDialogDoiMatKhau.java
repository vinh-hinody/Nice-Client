package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import constant.Types;
import dao.NhanVienServer;
import dao.TaiKhoanServer;

import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JDialogDoiMatKhau extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenTK;
	private JPasswordField txtMatKhauCu;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtXNMatKhauMoi;
	private JTextField txtCaptcha;
	private JTextField textField_5;

	private TaiKhoanServer tk_dao;
	private NhanVienServer nv_dao;
	/**
	 * Create the dialog.
	 */
	public JDialogDoiMatKhau() {
		setTitle("Đổi Mật Khẩu");
		var size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(size.width/6*2, size.height/6*2+20, size.width/6*2-80, size.height/6*3-50);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Label lblTenTK = new Label("Tên tài khoản:");
		lblTenTK.setBounds(73, 40, 103, 28);
		contentPanel.add(lblTenTK);
		
		Label lblMatKhauCu = new Label("Mật khẩu cũ:");
		lblMatKhauCu.setBounds(73, 81, 103, 28);
		contentPanel.add(lblMatKhauCu);
		
		Label lblMatKhauMoi = new Label("Mật khẩu mới:");
		lblMatKhauMoi.setBounds(73, 122, 103, 28);
		contentPanel.add(lblMatKhauMoi);
		
		Label lblXNMatKhauMoi = new Label("Xác nhận mật khẩu:");
		lblXNMatKhauMoi.setBounds(44, 167, 132, 28);
		contentPanel.add(lblXNMatKhauMoi);
		
		txtTenTK = new JTextField();
		txtTenTK.setBounds(182, 49, 183, 19);
		contentPanel.add(txtTenTK);
		txtTenTK.setColumns(10);
		
		txtMatKhauCu =  new JPasswordField();
		txtMatKhauCu.setColumns(10);
		txtMatKhauCu.setBounds(182, 90, 183, 19);
		contentPanel.add(txtMatKhauCu);
		
		txtMatKhauMoi =  new JPasswordField();
		txtMatKhauMoi.setColumns(10);
		txtMatKhauMoi.setBounds(182, 131, 183, 19);
		contentPanel.add(txtMatKhauMoi);
		
		txtXNMatKhauMoi =  new JPasswordField();
		txtXNMatKhauMoi.setColumns(10);
		txtXNMatKhauMoi.setBounds(182, 176, 183, 19);
		contentPanel.add(txtXNMatKhauMoi);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hiển thị mật khẩu");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (chckbxNewCheckBox.isSelected()) {
	                    // Hiển thị mật khẩu nếu checkbox được chọn
					 	txtMatKhauCu.setEchoChar((char) 0);
	                    txtMatKhauMoi.setEchoChar((char) 0); 
	                    txtXNMatKhauMoi.setEchoChar((char) 0); 
	                } else {
	                    // Ẩn mật khẩu nếu checkbox không được chọn
	                    txtMatKhauCu.setEchoChar('*'); 
	                    txtMatKhauMoi.setEchoChar('*'); 
	                    txtXNMatKhauMoi.setEchoChar('*'); 
	                }
			}
		});
		chckbxNewCheckBox.setBounds(182, 201, 124, 21);
		contentPanel.add(chckbxNewCheckBox);
		
		txtCaptcha = new JTextField();
		txtCaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		txtCaptcha.setColumns(10);
		txtCaptcha.setBounds(182, 229, 183, 19);
		contentPanel.add(txtCaptcha);
		
		Label lblMaCaptcha = new Label("Mã xác nhận:");
		lblMaCaptcha.setBounds(62, 220, 103, 28);
		contentPanel.add(lblMaCaptcha);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setOpaque(false);
		textField_5.setColumns(10);
		textField_5.setEditable(false);
		textField_5.setBounds(182, 270, 183, 19);
		contentPanel.add(textField_5);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateCapcha();
			}
		});
		btnNewButton.setBounds(30, 269, 124, 21);
		contentPanel.add(btnNewButton);
		generateCapcha();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Đồng ý ");
				okButton.addActionListener(new ActionListener() {
					private String URL = Types.URL;

					public void actionPerformed(ActionEvent e) {
						String tenTk = txtTenTK.getText();
						String mkCu  = txtMatKhauCu.getText();
						String mkMoi = txtMatKhauMoi.getText();
						String mkMoiXN = txtXNMatKhauMoi.getText();
						String maCaptcha = txtCaptcha.getText();
						
						if(!mkMoiXN.equals(mkMoi)) {
							int option = JOptionPane.showConfirmDialog(null, "Mật khẩu mới và xác nhận không khớp!Bạn có muốn nhập lại không?", "Lỗi", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
							if(option == JOptionPane.YES_OPTION) {
								txtMatKhauMoi.setText("");
								txtXNMatKhauMoi.setText("");
								txtMatKhauMoi.requestFocus();
								generateCapcha();
								return;
							}else {
								return;
							}
						}else if(!maCaptcha.equals(textField_5.getText())) {
							int option = JOptionPane.showConfirmDialog(null, "Mã captcha không đúng.Bạn vui lòng nhập lại!", "Lỗi", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
							if(option == JOptionPane.YES_OPTION) {
								txtCaptcha.setText("");
								txtCaptcha.requestFocus();
								generateCapcha();
								return;
							}else {
								return;
							}
						}else {
							//							boolean success = TaiKhoan_Dao.changePassword(tenTk, mkCu, mkMoi);
							boolean success = false;
							try {
								tk_dao = (TaiKhoanServer) Naming.lookup(URL + "TaiKhoanServer");
								nv_dao = (NhanVienServer) Naming.lookup(URL + "NhanVienServer");
								TaiKhoan tk = tk_dao.find(mkCu);
								tk.setMatKhau(mkMoi);
								NhanVien nv = nv_dao.find(Integer.parseInt(GD_Chinh.getNVWorking()));
								tk.setMaNV(nv);
								success = tk_dao.update(tk);
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (RemoteException e1) {
								e1.printStackTrace();
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (NotBoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(success) {
								JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công vui lòng kiểm tra lại!","Thành công", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng vui lòng kiểm tra lại!","Thất bại", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Thoát");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	//Hàm hiển thị mã capcha
	public void  generateCapcha() {
		String captcha = generateRandomCapcha();
		textField_5.setText(captcha);
		textField_5.setForeground(generateRandomColor());
	}
	public String generateRandomCapcha() {
		int length = 4;
		String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder captcha = new StringBuilder();
		for(int i = 0; i < length; i++) {
			 int index = random.nextInt(character.length());
	            captcha.append(character.charAt(index));
		}
		
		return captcha.toString();
}
	public Color generateRandomColor() {
		Random random = new Random();
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		
		return new Color(r, g, b);
		
	}
	
}
