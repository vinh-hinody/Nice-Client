package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.LoaiPhongServer;
import dao.PhongServer;
import entity.Phong;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class JDialogPhong_SuaPhong extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final GD_Phong home;
	private JTextField txt_TenP;
	private JComboBox<String> cbb_TypeRoom;
	private JComboBox<String> cbb_PeopleNum;
	private JLabel lbl_Price;
	private JComboBox<String> cbb_Status;
	private Phong room;

	private PhongServer phong_Dao;
	private LoaiPhongServer loaiPhong_Dao;

	/**
	 * Create the dialog.
	 * 
	 * @throws RemoteException
	 */
	public JDialogPhong_SuaPhong(GD_Phong homeFrm2, Phong a){

		this.home = homeFrm2;
		this.room = a;

		getContentPane().setBackground(new Color(222, 184, 135));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("SỬA PHÒNG");
		setBounds(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
		setLocationRelativeTo(null);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 4));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 0));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setPreferredSize(new Dimension(100, 10));
			panel.setLayout(new BorderLayout(4, 4));
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.NORTH);
				panel_2.setBackground(Color.WHITE);
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
				FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
				fl_panel_2.setVgap(0);
				fl_panel_2.setHgap(0);
				fl_panel_2.setAlignment(FlowLayout.LEFT);
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.BLACK);
					panel_2.add(panel_1);
					panel_1.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblNewLabel_1 = new JLabel("THÔNG TIN PHÒNG");
						lblNewLabel_1.setPreferredSize(new Dimension(200, 30));
						lblNewLabel_1.setForeground(Color.WHITE);
						panel_1.add(lblNewLabel_1);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setLayout(new GridLayout(0, 2, 4, 4));
				{
					JLabel lblNewLabel = new JLabel("Tên Phòng");
					lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblNewLabel);
				}
				{
					txt_TenP = new JTextField(a.getTenP());
					txt_TenP.setHorizontalAlignment(SwingConstants.CENTER);
					txt_TenP.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(txt_TenP);
					txt_TenP.setColumns(10);
				}
				{
					JLabel lblLoiPhng = new JLabel("Loại Phòng");
					lblLoiPhng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					lblLoiPhng.setHorizontalAlignment(SwingConstants.CENTER);
					lblLoiPhng.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblLoiPhng);
				}
				{
					cbb_TypeRoom = new JComboBox<String>();
					cbb_TypeRoom.addItem("VIP");
					cbb_TypeRoom.addItem("THƯỜNG");
					cbb_TypeRoom.setFont(new Font("Dialog", Font.BOLD, 14));
					if (a.getMaLP().getMaLP().equals("THUONG"))
						cbb_TypeRoom.setSelectedItem("THƯỜNG");
					else
						cbb_TypeRoom.setSelectedItem("VIP");
					cbb_TypeRoom.addActionListener(this);
					panel_1.add(cbb_TypeRoom);
				}
				{
					cbb_PeopleNum = new JComboBox<String>();
					cbb_PeopleNum.setEditable(true);
					cbb_PeopleNum.addItem(a.getSucChua()+"");
					cbb_PeopleNum.addItem("5");
					cbb_PeopleNum.addItem("10");
					cbb_PeopleNum.addItem("20");
					
					cbb_PeopleNum.setFont(new Font("Dialog", Font.BOLD, 14));
					cbb_PeopleNum.addActionListener(this);
					{
						JLabel lblSNgi = new JLabel("Số Người");
						lblSNgi.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
						lblSNgi.setHorizontalAlignment(SwingConstants.CENTER);
						lblSNgi.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(lblSNgi);
					}
					panel_1.add(cbb_PeopleNum);
				}
				{
					JLabel lblGiPhng = new JLabel("Giá Phòng");
					lblGiPhng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					lblGiPhng.setHorizontalAlignment(SwingConstants.CENTER);
					lblGiPhng.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblGiPhng);
				}
				{
					lbl_Price = new JLabel("100.000 VND");
					lbl_Price.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							String price = JOptionPane.showInputDialog("Nhập giá mới");
							if (price != null) {
								if (price.matches("[0-9]+"))
									lbl_Price.setText(price + " VND");
								else
									JOptionPane.showMessageDialog(null, "Giá tiền phòng là chuỗi số ! VD: '66000' !");
							}
						}
					});
					lbl_Price.setFont(new Font("Tahoma", Font.BOLD, 14));
					lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lbl_Price);

					updatePrice();
				}
				{
					JLabel lblTnhTrng = new JLabel("Tình Trạng");
					lblTnhTrng.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					lblTnhTrng.setHorizontalAlignment(SwingConstants.CENTER);
					lblTnhTrng.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblTnhTrng);
				}
				{
					cbb_Status = new JComboBox<String>();
					cbb_Status.addItem("TRỐNG");
					cbb_Status.addItem("BẢO TRÌ");
					cbb_Status.setFont(new Font("Dialog", Font.BOLD, 14));
					cbb_Status.setSelectedItem(a.getTinhTrang());
					panel_1.add(cbb_Status);
				}
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
				JButton okButton = new JButton("LƯU");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
								okSuaPhong();
							} catch (RemoteException e1) {
								e1.printStackTrace();
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (NotBoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				okButton.setPreferredSize(new Dimension(200, 30));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelDatPhong();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
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
				JLabel lblNewLabel_2 = new JLabel("CẬP NHẬT THÔNG TIN PHÒNG");
				panel.add(lblNewLabel_2);
				lblNewLabel_2.setForeground(new Color(255, 255, 255));
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		}
		updateNewPrice();
	}

	private void updateNewPrice() {
		lbl_Price.setText((int) this.room.getGiaP() + "");

	}

	protected void cancelDatPhong() {
		this.setVisible(false);

	}
	private static final String URL = "rmi://192.168.3.2:7878/";
	
	public void okSuaPhong() throws RemoteException, MalformedURLException, NotBoundException {
		phong_Dao = (PhongServer) Naming.lookup("PhongServer");
		loaiPhong_Dao = (LoaiPhongServer) Naming.lookup(URL+"LoaiPhongServer");
		Phong old = null;
		try {
			old = phong_Dao.findName(txt_TenP.getText());
			String price = lbl_Price.getText();
			Double priceCovert = Double.valueOf(price);
			Integer soluong = Integer.valueOf(cbb_PeopleNum.getSelectedItem()+"");
			old.setGiaP(priceCovert);
			old.setMaLP(loaiPhong_Dao.findName(cbb_TypeRoom.getSelectedItem()+""));
			old.setTinhTrang(cbb_Status.getSelectedItem()+"");
			System.out.println(old);
			if(soluong > 0)
				old.setSucChua(soluong);

			if (phong_Dao.update(old)) {
				this.hide();
				this.home.updateRoomView(phong_Dao.getAll());
				JOptionPane.showMessageDialog(null, "Cập nhật " + old.getTenP() + " thành công !");
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(cbb_TypeRoom)) {
			if (cbb_TypeRoom.getSelectedIndex() == 0) {
				if (cbb_PeopleNum.getSelectedIndex() == 0) {
					lbl_Price.setText("66000 VND");
				} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
					lbl_Price.setText("110000 VND");
				} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
					lbl_Price.setText("220000 VND");
				}
			} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
				if (cbb_PeopleNum.getSelectedIndex() == 0) {
					lbl_Price.setText("60000 VND");
				} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
					lbl_Price.setText("100000 VND");
				} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
					lbl_Price.setText("200000 VND");
				}
			}
		} else if (src.equals(cbb_PeopleNum)) {
			if (cbb_PeopleNum.getSelectedIndex() == 0) {
				if (cbb_TypeRoom.getSelectedIndex() == 0) {
					lbl_Price.setText("66000 VND");
				} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
					lbl_Price.setText("60000 VND");
				}
			} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
				if (cbb_TypeRoom.getSelectedIndex() == 0) {
					lbl_Price.setText("110000 VND");
				} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
					lbl_Price.setText("100000 VND");
				}
			} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
				if (cbb_TypeRoom.getSelectedIndex() == 0) {
					lbl_Price.setText("220000 VND");
				} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
					lbl_Price.setText("200000 VND");
				}
			}
		}
	}

	void updatePrice() {
		if (cbb_TypeRoom.getSelectedIndex() == 0) {
			if (cbb_PeopleNum.getSelectedIndex() == 0) {
				lbl_Price.setText("66000 VND");
			} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
				lbl_Price.setText("110000 VND");
			} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
				lbl_Price.setText("220000 VND");
			}
		} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
			if (cbb_PeopleNum.getSelectedIndex() == 0) {
				lbl_Price.setText("60000 VND");
			} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
				lbl_Price.setText("100000 VND");
			} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
				lbl_Price.setText("200000 VND");
			}
		}
		if (cbb_PeopleNum.getSelectedIndex() == 0) {
			if (cbb_TypeRoom.getSelectedIndex() == 0) {
				lbl_Price.setText("66000 VND");
			} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
				lbl_Price.setText("60000 VND");
			}
		} else if (cbb_PeopleNum.getSelectedIndex() == 1) {
			if (cbb_TypeRoom.getSelectedIndex() == 0) {
				lbl_Price.setText("110000 VND");
			} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
				lbl_Price.setText("100000 VND");
			}
		} else if (cbb_PeopleNum.getSelectedIndex() == 2) {
			if (cbb_TypeRoom.getSelectedIndex() == 0) {
				lbl_Price.setText("220000 VND");
			} else if (cbb_TypeRoom.getSelectedIndex() == 1) {
				lbl_Price.setText("200000 VND");
			}
		}
	}

}
