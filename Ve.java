package QL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Ve extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel c;
	private JTextField tfKH;
	private JTextField tfDT;
	private JTextField tfTenSB;
	private JTextField tfTenSBD;
	private JTextField  tfSL;
	private JTextField tfDVTinh;
	private JTextField tfGT;
	private JTextField tftongtien;
	private JTextField tfTK;
	DefaultTableModel model;
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	JScrollPane s;
	Statement stmt = null;
	ResultSet rs = null;
	Connection conn ;
	
	public Ve() {
		try {
			conn = getConnect();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};

		JLabel lblTD = new JLabel("VÉ MÁY BAY");
		JLabel lblKH = new JLabel("Tên Khách Hàng : ");
		JLabel lblSDT = new JLabel("Số Điện Thoại : ");
		JLabel lblTenSB = new JLabel("Sân bay đi : ");
		JLabel lblTenSBD = new JLabel("Sân bay đến : ");
		JLabel lblSL = new JLabel("Số Lượng : ");
		JLabel lblDVTinh = new JLabel("Đơn Vị Tính : ");
		JLabel lblGT = new JLabel("Giá Tiền : ");
		JLabel lblTTien = new JLabel("Tổng Tiền : ");
		JButton lblThem = new JButton("Thêm");
		JButton lblXoa = new JButton("Xoá");
		JButton lblSua = new JButton("Sửa");
		JButton btnLui = new JButton("Quay Lùi");
		JButton btnTK = new JButton("Tìm Kiếm ");
		 s = new JScrollPane();
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 620);
		c = new JPanel();
		c.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(c);
		c.setLayout(null);
		s.setBounds(0, 322, 679, 190);
		c.add(s);
		lblTD.setBounds(227, 10, 266, 58);
		c.add(lblTD);
		lblKH.setBounds(0, 61, 122, 33);
		c.add(lblKH);
		tfKH = new JTextField();
		tfKH.setBounds(121, 65, 372, 26);
		c.add(tfKH);
		tfKH.setColumns(10);
		lblSDT.setBounds(0, 100, 122, 33);
		c.add(lblSDT);
		tfDT = new JTextField();
		tfDT.setBounds(121, 104, 372, 26);
		tfDT.setColumns(10);
		c.add(tfDT);
		tfTenVT = new JTextField();
		tfTenVT.setBounds(121, 140, 372, 26);
		tfTenVT.setColumns(10);
		c.add(tfTenVT);
		tfSL = new JTextField();
		tfSL.setBounds(121, 176, 372, 26);
		tfSL.setColumns(10);
		c.add(tfSL);
		tfDVTinh = new JTextField();
		tfDVTinh.setBounds(121, 212, 372, 26);
		tfDVTinh.setColumns(10);
		c.add(tfDVTinh);
		
		tfGT = new JTextField();
		tfGT.setBounds(121, 248, 372, 26);
		tfGT.setColumns(10);
		c.add(tfGT);
		
		tftongtien= new JTextField();
		tftongtien.setBounds(121, 283, 372, 26);
		tftongtien.setColumns(10);
		c.add(tftongtien);
		
		lblTenVT.setBounds(0, 136, 122, 33);
		c.add(lblTenVT);
		
		lblSL.setBounds(0, 176, 122, 33);
		
		c.add(lblSL);
		

		lblDVTinh.setBounds(0, 212, 122, 33);
	
		c.add(lblDVTinh);
		
		lblGT.setBounds(0, 244, 122, 33);
	
		c.add(lblGT);
		

		
		lblTTien.setBounds(0, 279, 122, 33);
		c.add(lblTTien);
		Object[] row = new Object[7]; 
		//bảng
		loadKhachHang(); //1
		scrollPane = new JScrollPane();
		s.setColumnHeaderView(scrollPane);
		scrollPane.setViewportView(table);
		lblThem.setBounds(30, 530, 106, 33);
		lblThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						if(tfKH.getText().equals("")||tfDT.getText().equals("")||tfDVTinh.getText().equals("")||tfTenVT.getText().equals("")||tfSL.getText().equals("")||tfSL.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"vui lòng nhập dữ liệu vào!!!!!");
						}else {
							String tenkh = tfKH.getText();
							String dt = tfDT.getText();
							String tenvt = tfTenVT.getText();
							String dv = tfDVTinh.getText();
							double sl =Double.parseDouble(tfSL.getText());
							double gt = Double.parseDouble(tfGT.getText());
							double tt =0;
							tt=0;
							tt = sl*gt;
							String sqlInsert = "Insert into KHACHHANG values(N'" + tenkh + "','" + dt
									+ "',N'" + tenvt + "',N'" + dv + "'," + sl + "," + gt +","+tt+")";
							tfKH.setText("");
							tfTenVT.setText("");
							tfDVTinh.setText("");
							tftongtien.setText("");
							tfGT.setText("");
							tfSL.setText("");
							tfDT.setText("");
							JOptionPane.showConfirmDialog(null,"xác nhận!");
							stmt = conn.createStatement();
							stmt.execute(sqlInsert);
							loadKhachHang();
							JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");	
						}
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, g.getMessage());
						g.printStackTrace();
					}
				lblThem.setText(String.valueOf("Thêm"));
			}

		});
		c.add(lblThem);
		//xoá
		lblXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				String kh = model.getValueAt(i, 0).toString();
				String sqlxoa = "DELETE FROM KHACHHANG where TenKH = N'" + kh + "'"; 
				if (JOptionPane.showConfirmDialog(null, "bạn có chắc chắn xoá tên khách hàng đó không là "
						+ model.getValueAt(i, 0) + " không ? ") == JOptionPane.YES_NO_OPTION) {
					// yes option
					try {
						stmt = conn.createStatement();
						boolean countdelete = stmt.execute(sqlxoa);
						loadKhachHang();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// no option 
				}		
			}
		});
		lblXoa.setBounds(215, 530, 91, 33);
		c.add(lblXoa);
		// sửa
		lblSua.setBounds(382, 530, 85, 33);
		
		lblSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
					String kh =tfKH.getText();
					String sdt = tfDT.getText();
					String tenvl = tfTenVT.getText();
					String dv = tfDVTinh.getText();
					double sl =Double.parseDouble(tfSL.getText());
					double gt = Double.parseDouble(tfGT.getText());
					double tt = Double.parseDouble(tftongtien.getText());
					tt=0;
					tt = sl*gt;
				if(kh.equals(""))
				{	
					JOptionPane.showMessageDialog(null, "Phải chọn một vật liệu trước khi sửa!");
					return;
				}
				String sqlUpdate = "update KHACHHANG set  SDT= "+sdt +", TenVL = N'"+tenvl+ "', DVTinh = N'"+dv+ "', SLuong="+sl+" ,GiaTien= "
				+gt+", TongTien= "+tt+" where TenKH=N'"+kh+"'";
				try {
					stmt = conn.createStatement();
					boolean countdelete = stmt.execute(sqlUpdate);
					loadKhachHang();
				} catch (SQLException g) {
					// TODO Auto-generated catch block
					g.printStackTrace();
				}

			}
		});
		c.add(lblSua);
		//quay lùi 
		btnLui.setBounds(0, 0, 97, 26);
		btnLui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menu();
			}
		});
		c.add(btnLui);
		// tìm kiếm 
		btnTK.setBounds(530, 530, 106, 33);
		c.add(btnTK);
		btnTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int select = table.getSelectedRow();
				String input = JOptionPane.showInputDialog(null,"Nhập nội dung muốn tìm(để trống nếu muốn tất cả):");
				String sqlseach = "select * from KHACHHANG where TenKH like N'%"+input+"%' " ;
				sqlseach += " or SDT like N'%"+input+"%' " ;
				sqlseach += " or TenVL like N'%"+input+"%' " ; 
				sqlseach += " or DVTinh like N'%"+input+"%' " ; 
				try {
					stmt= conn.createStatement();
					ResultSet resultSet = stmt.executeQuery(sqlseach);
					ResultSetMetaData rsmd = resultSet.getMetaData();
					int colNo = rsmd.getColumnCount();
					Vector vtColumn = new Vector(colNo);
					String[] tableTitle =  {"Khách Hàng","Số ĐT","Tên Vật Liệu","Đơn Vị Khối Lượng","Số Lượng","Giá Tiền/SP","Tổng Tiền"};
					for (String s : tableTitle) {
						vtColumn.add(s);
					}
					Vector vtData = new Vector();
					Vector vtRows = new Vector();
					while(resultSet.next()){
						vtRows = new Vector(colNo);
			            for(int i=0;i<colNo;i++){
			                vtRows.add(resultSet.getString(i+1));
			            }
			            vtData.add(vtRows);           
			        } 
					table = new JTable(vtData,vtColumn);
					table.setBackground(new Color(255, 255, 255));
					table.setFont(new Font("Arial", Font.PLAIN, 14));
					scrollPane.setViewportView(table);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});		
		setVisible(true);
	}
	private void loadKhachHang() {
		try {
		String SQL = "SELECT * FROM KHACHHANG";
		 stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsmd = rs.getMetaData();
		int colNo = rsmd.getColumnCount();
		Vector vtColumn = new Vector(colNo);
		String[] tableTitle =  {"Khách Hàng","Số ĐT","Tên Vật Tư","Đơn Vị Tính","Số Lượng","Giá Tiền/SP","Tổng Tiền"};
		for (String s : tableTitle) {
			vtColumn.add(s);
		}
		Vector vtData = new Vector();
		Vector vtRows = new Vector();
		while(rs.next()){
			vtRows = new Vector(colNo);
            for(int i=0;i<colNo;i++){
                vtRows.add(rs.getString(i+1));
            }
            vtData.add(vtRows);           
        } 
		table = new JTable(vtData,vtColumn);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					model = (DefaultTableModel) table.getModel(); 
					int i= table.getSelectedRow();
					tfKH.setText(model.getValueAt(i, 0).toString());
					tfDT.setText(model.getValueAt(i, 1).toString());
					tfTenVT.setText(model.getValueAt(i, 2).toString());
					tfDVTinh.setText(model.getValueAt(i, 3).toString()); 
					tfSL.setText(model.getValueAt(i, 4).toString());
					tfGT.setText(model.getValueAt(i, 5).toString());
					tftongtien.setText(model.getValueAt(i, 6).toString());
				}
			}
		});
		
		scrollPane.setViewportView(table);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}
}
