package ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class GD_ThongKeMatHangBanChay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Panel panel_BieuDo;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public GD_ThongKeMatHangBanChay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 590, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Title = new JPanel();
		panel_Title.setBorder(new LineBorder(Color.WHITE));
		panel_Title.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel_Title, BorderLayout.NORTH);
		panel_Title.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ MẶT HÀNG BÁN CHẠY");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Title.add(lblNewLabel);
		
		JPanel panel_Content = new JPanel();
		panel_Content.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(panel_Content, BorderLayout.CENTER);
		panel_Content.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_TacVu = new JPanel();
		panel_TacVu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_TacVu.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10) );
		panel_Content.add(panel_TacVu, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Lựa chọn:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		panel_TacVu.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Xem Thống kê");
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showThongKe(2024);
			}
		});
		
		panel_TacVu.add(btnNewButton);
		
		Panel panel_ThongKe = new Panel();
		panel_Content.add(panel_ThongKe, BorderLayout.CENTER);
		panel_ThongKe.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPane.setForeground(new Color(0, 0, 0));
		panel_ThongKe.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ma SP", "T\u00EAn SP", "S\u1ED1 L\u01B0\u1EE3n B\u00E1n", "Gi\u00E1 Nh\u1EADp", "Ng\u00E0y Nh\u1EADp", "Gi\u00E1 B\u00E1n", "T\u1ED5ng Doanh Thu"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin thống kê:");
		lblNewLabel_2.setBorder(new EmptyBorder(20, 0, 10, 0));
		panel_ThongKe.add(lblNewLabel_2, BorderLayout.NORTH);
		
		 panel_BieuDo = new Panel();
	     panel_BieuDo.revalidate();
	     panel_BieuDo.repaint();
		panel_Content.add(panel_BieuDo, BorderLayout.SOUTH);
	}
	private void showThongKe(int nam) {
		try {
//			Vector<Object> kqThongke = ThongKe_Dao.thongKeMatHangBanChayTheoNam(nam);
			Vector<Object> kqThongke = new Vector<Object>();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (Object rowThongKe : kqThongke) {
				model.addRow((Vector<?>) rowThongKe);
			};
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
