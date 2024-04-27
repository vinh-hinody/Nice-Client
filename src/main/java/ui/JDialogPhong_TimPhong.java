package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Dimension;

import dao.PhongServer;
import entity.Phong;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

public class JDialogPhong_TimPhong extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_MaP;
	private JTextField txt_TenP;
	private GD_Phong home;
	private JCheckBox ck_Vip;
	private JCheckBox ck_Normal;
	private JCheckBox ck_Empty;
	private JCheckBox ck_Maintenance;
	private JSpinner spn_Count;
	private PhongServer phong_Dao;
	private static final String URL = "rmi://192.168.3.2:7878/";
	/**
	 * Create the dialog.
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public JDialogPhong_TimPhong(GD_Phong home) throws RemoteException, MalformedURLException, NotBoundException {

		phong_Dao = (PhongServer) Naming.lookup(URL + "PhongServer");
		this.home = home;
		this.home.updateRoomView(phong_Dao.getAll());
		getContentPane().setBackground(new Color(222, 184, 135));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Tìm kiếm phòng");
		setBounds((size.width / 8) * 2, (size.height / 7) * 2, 612, 448);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(222, 184, 135));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(4, 4));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("TÌM PHÒNG");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setPreferredSize(new Dimension(200, 30));
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
						lblThngTinCn.setPreferredSize(new Dimension(200, 30));
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
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(5, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setPreferredSize(new Dimension(130, 10));
					panel_2.setOpaque(false);
					panel_1.add(panel_2, BorderLayout.WEST);
					panel_2.setLayout(new GridLayout(0, 1, 0, 10));
					{
						JLabel lblNewLabel_1 = new JLabel("MÃ PHÒNG");
						panel_2.add(lblNewLabel_1);
						lblNewLabel_1.setBackground(Color.CYAN);
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					{
						JLabel lblNewLabel_1 = new JLabel("TÊN PHÒNG");
						panel_2.add(lblNewLabel_1);
						lblNewLabel_1.setBackground(Color.CYAN);
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					{
						JLabel lblNewLabel_1 = new JLabel("LOẠI PHÒNG");
						panel_2.add(lblNewLabel_1);
						lblNewLabel_1.setBackground(Color.CYAN);
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					{
						JLabel lblNewLabel_1 = new JLabel("SỐ LƯỢNG");
						panel_2.add(lblNewLabel_1);
						lblNewLabel_1.setBackground(Color.CYAN);
						lblNewLabel_1.setOpaque(true);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					{
						JLabel lblNewLabel_2 = new JLabel("TÌNH TRẠNG");
						panel_2.add(lblNewLabel_2);
						lblNewLabel_2.setBackground(Color.CYAN);
						lblNewLabel_2.setOpaque(true);
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2, BorderLayout.CENTER);
					panel_2.setLayout(new GridLayout(0, 1, 0, 10));
					{
						txt_MaP = new JTextField();
						txt_MaP.setHorizontalAlignment(SwingConstants.CENTER);
						panel_2.add(txt_MaP);
						txt_MaP.setBackground(Color.WHITE);
						txt_MaP.setFont(new Font("Tahoma", Font.BOLD, 14));
						txt_MaP.setColumns(10);
					}
					{
						txt_TenP = new JTextField();
						panel_2.add(txt_TenP);
						txt_TenP.setBackground(Color.WHITE);
						txt_TenP.setFont(new Font("Tahoma", Font.BOLD, 14));
						txt_TenP.setColumns(10);
					}
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3);
						panel_3.setLayout(new GridLayout(1, 0, 4, 0));
						{
							ck_Vip = new JCheckBox("VIP");
							ck_Vip.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									try {
										click_Type();
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
								}
							});
							ck_Vip.setHorizontalAlignment(SwingConstants.CENTER);
							ck_Vip.setFont(new Font("Tahoma", Font.BOLD, 16));
							ck_Vip.setBackground(Color.WHITE);
							panel_3.add(ck_Vip);
						}
						{
							ck_Normal = new JCheckBox("THƯỜNG");
							ck_Normal.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									try {
										click_Type();
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
								}
							});
							ck_Normal.setHorizontalAlignment(SwingConstants.CENTER);
							ck_Normal.setFont(new Font("Tahoma", Font.BOLD, 16));
							ck_Normal.setBackground(Color.WHITE);
							panel_3.add(ck_Normal);
						}
					}
					{
						JPanel panel_1_1 = new JPanel();
						panel_1_1.setOpaque(false);
						panel_2.add(panel_1_1);
						panel_1_1.setLayout(new BorderLayout(2, 0));
						{
							spn_Count = new JSpinner();
							spn_Count.setFont(new Font("Tahoma", Font.BOLD, 18));
							spn_Count.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null,
									Integer.valueOf(1)));
							panel_1_1.add(spn_Count);
						}
					}
					{
						JPanel panel_1_1 = new JPanel();
						panel_2.add(panel_1_1);
						panel_1_1.setOpaque(false);
						panel_1_1.setLayout(new GridLayout(1, 1, 4, 0));
						{
							ck_Empty = new JCheckBox("TRỐNG");
							ck_Empty.addActionListener(this);
							ck_Empty.setPreferredSize(new Dimension(150, 21));
							ck_Empty.setHorizontalAlignment(SwingConstants.CENTER);
							ck_Empty.setFont(new Font("Tahoma", Font.BOLD, 16));
							ck_Empty.setBackground(Color.WHITE);
							panel_1_1.add(ck_Empty);
						}
						{
							ck_Maintenance = new JCheckBox("BẢO TRÌ");
							ck_Maintenance.addActionListener(this);
							ck_Maintenance.setPreferredSize(new Dimension(150, 21));
							ck_Maintenance.setHorizontalAlignment(SwingConstants.CENTER);
							ck_Maintenance.setFont(new Font("Tahoma", Font.BOLD, 16));
							ck_Maintenance.setBackground(Color.WHITE);
							panel_1_1.add(ck_Maintenance);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			buttonPane.setBorder(new EmptyBorder(2, 0, 2, 0));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnTmKim = new JButton("TÌM KIẾM");
				btnTmKim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							okTimKiem();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnTmKim.setPreferredSize(new Dimension(200, 30));
				btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnTmKim.setActionCommand("OK");
				buttonPane.add(btnTmKim);
			}
			{
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeDialog();
					}
				});
				cancelButton.setPreferredSize(new Dimension(70, 30));
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	protected void timPhongTrong() throws RemoteException {
		var list = phong_Dao.getAll().stream()
				.filter(t->t.getTinhTrang().equalsIgnoreCase("TRỐNG"))
				.toList();
		this.home.updateRoomView(list);
	}


	protected void click_Type() throws RemoteException {
		List<Phong> list = null;
		if (ck_Vip.isSelected() && ck_Normal.isSelected())
			list = phong_Dao.getAll();
		else if (ck_Vip.isSelected()) {
				list = phong_Dao.getAll()
						.stream()
						.filter(t -> t.getMaLP().getMaLP().equalsIgnoreCase("VIP"))
						.toList();
		} else if (ck_Normal.isSelected()) {
				list = phong_Dao.getAll()
						.stream()
						.filter(t -> t.getMaLP().getMaLP().equalsIgnoreCase("THUONG"))
						.toList();
		}
		this.home.updateRoomView(list);
	}

	protected void okTimKiem() throws RemoteException {
		boolean check_ma = false;
		boolean check_ten = false;
		if(txt_MaP.getText().length() > 0)
			check_ma = true;
		if (txt_TenP.getText().length() > 0)
			check_ten = true;
		if( !check_ma && !check_ten)
			try {
				var source = phong_Dao.getAll();
				var list = source.stream().
						filter(
						t -> (t.getMaP().toLowerCase().contains(txt_MaP.getText().toLowerCase())) && 
						(t.getTenP().toLowerCase().contains(txt_TenP.getText().toLowerCase())) &&
						( ck_Vip.isSelected() && t.getMaLP().getMaLP().equalsIgnoreCase(ck_Vip.getText())) &&
						( ck_Normal.isSelected() && t.getMaLP().getMaLP().equalsIgnoreCase(ck_Normal.getText())) &&
						(t.getSucChua() <= Integer.parseInt(spn_Count.getValue()+"")))
						.toList();
				this.home.updateRoomView(list);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		else if(check_ma || check_ten) {
			var source = phong_Dao.getAll();
			var list = source.stream().
					filter(
					t -> (t.getMaP().toLowerCase().contains(txt_MaP.getText().toLowerCase())) && 
					(t.getTenP().toLowerCase().contains(txt_TenP.getText().toLowerCase())))
					.toList();
			this.home.updateRoomView(list);
		}
			
	}

	@SuppressWarnings("deprecation")
	protected void closeDialog() {
		this.hide();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		if( (ck_Empty.isSelected() && ck_Maintenance.isSelected()) || (!ck_Empty.isSelected() && !ck_Maintenance.isSelected()) ) {
			try {
				tatCaPhong();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if((a.equals(ck_Empty) && !ck_Maintenance.isSelected()) || (a.equals(ck_Maintenance) && ck_Empty.isSelected())) {
			try {
				timPhongTrong();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if((a.equals(ck_Maintenance) && !ck_Empty.isSelected()) || (a.equals(ck_Empty) && ck_Maintenance.isSelected())) {
			try {
				timPhongDangBaoTri();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}


	private void tatCaPhong() throws RemoteException {
		var list = phong_Dao.getAll();
		this.home.updateRoomView(list);
		
	}


	private void timPhongDangBaoTri() throws RemoteException {
		var list = phong_Dao.getAll().stream()
				.filter(t->t.getTinhTrang().equalsIgnoreCase("BẢO TRÌ"))
				.toList();
		this.home.updateRoomView(list);
	}

}
