package QL;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Giaodien.UpdateForm;
import QL.Dangnhap;
import QL.Update;
import QL.data;
import QL.Menu;

public class Khachhang extends data implements ActionListener, MouseListener {
	private JFrame manaFrame;
	private JPanel dataPanel, fucntionPanel;
	private JButton them,xoa,sua,timkiem,quaylui;
	private DefaultTableModel DT;
	private JScrollPane scroll;
	private JTable table;
	protected Vector<Vector<String>> vData = new Vector<Vector<String>>();
	private Vector<String> vTitle = new Vector<>();
	private int select = -1;
	
	public Khachhang() {
		manaFrame = new JFrame();
		them = new JButton("Thêm");
		them.addActionListener(this);
		xoa = new JButton("Xóa");
		xoa.addActionListener(this);
		sua = new JButton("Sửa");
		sua.addActionListener(this);
		timkiem = new JButton("Tìm kiếm");
		timkiem.addActionListener(this);
		quaylui = new JButton("Quay lại");
		quaylui.addActionListener(this);
		
		setDataTable();
		DT = new DefaultTableModel(vData, vTitle);
		table = new JTable(DT);
		scroll = new JScrollPane(table);
		
		dataPanel = new JPanel();
		dataPanel.add(scroll);
		
		fucntionPanel = new JPanel();
		fucntionPanel.add(them);
		fucntionPanel.add(xoa);
		fucntionPanel.add(sua);
		fucntionPanel.add(timkiem);
		fucntionPanel.add(quaylui);
		
		
		manaFrame.add(dataPanel, "North");
		manaFrame.add(fucntionPanel, "South");
		manaFrame.setVisible(true);
		manaFrame.setSize(520,520);
		manaFrame.setLocationRelativeTo(null);
		manaFrame.setResizable(false);		
		}
		public void setDataTable(){
	    	try {
	    		Connection conn = getConnect();
	            
	            Statement statement = conn.createStatement();
	            ResultSet rs = statement.executeQuery("Select * from Thongtin");
	            ResultSetMetaData rsmd = rs.getMetaData();
	            
	            while(rs.next()) {
	                Vector<String> temp = new Vector<>();
	                        
	                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
	                    temp.add(rs.getString(i));
	                }
	                
	                vData.add(temp);
	            }
	            
	            vTitle.add(rsmd.getColumnName(1));
	            vTitle.add(rsmd.getColumnName(2));
	            vTitle.add(rsmd.getColumnName(3));
	            vTitle.add(rsmd.getColumnName(4));
	            vTitle.add(rsmd.getColumnName(5));
	            vTitle.add(rsmd.getColumnName(6));
	            conn.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
	    }	
		
		public void setDoDaiCot() {
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(30);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
		}
		
		public void xoa() {
			if(vData.size() == 0) JOptionPane.showMessageDialog(manaFrame, "Bang khong co du lieu");
			else if(select == -1) JOptionPane.showMessageDialog(manaFrame, "Bạn cần chọn một hàng");
			else {
				try {
		    		Connection conn = getConnect();
		            
		            Statement statement = conn.createStatement();
		            String query = "Delete From Thongtin where ID = " + vData.elementAt(select).elementAt(0);
		            
		            PreparedStatement PS = conn.prepareStatement(query);
		            PS.executeUpdate();
		            conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				vData.remove(select);
				DT.fireTableDataChanged();
				select = -1;
			}
		
		}
		public void them(String ma, String hovaten, String gioitinh, String diachi, String cmnd, String sdt) {
			try {
				Connection conn = getConnect();
            
				Statement statement = conn.createStatement();
				String query = "Insert into Thongtin values(" + ma + ", N'" + hovaten + "', " + gioitinh + ", N'" + diachi + "', N '" + cmnd + "', N '" + sdt +")";
				JOptionPane.showConfirmDialog(null,"xác nhận!");
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Vector<String> s = new Vector<>();
			s.add(ma);
			s.add(hovaten);
			s.add(gioitinh);
			s.add(diachi);
			s.add(cmnd);
			s.add(sdt);
			vData.add(s);
			DT.fireTableDataChanged();
	}
		public void sua(String ma, String hovaten, String gioitinh, String diachi, String cmnd, String sdt) {
				try {
				Connection conn = getConnect();
	        
				Statement statement = conn.createStatement();
				String query = "Update Thongtin set Họ và tên= N'" + hovaten + "', Giới tính = " + cmnd + ", Địa chỉ = N'" + sdt + "', CMND= N'" + cmnd + "', SDT= N'"+ sdt + " where Mã khách hàng = " + ma;
	        
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			Vector<String> s = new Vector<>();
			s.add(ma);
			s.add(hovaten);
			s.add(gioitinh);
			s.add(diachi);
			s.add(cmnd);
			s.add(sdt);
			vData.remove(select);
			vData.add(select, s);
			DT.fireTableDataChanged();
		}
		public void timkiem(String hovaten, String diachi, String cmnd, String sdt) throws Exception  {
			try {
				int select = table.getSelectedRow();
				String input = JOptionPane.showInputDialog(null,"Nhập nội dung muốn tìm(để trống nếu muốn tất cả):");
				String sqlseach = "select * from Thongtin where Họ và tên like N'%"+input+"%' " ;
				sqlseach += " or Địa chỉ like N'%"+input+"%' " ;
				sqlseach += " or CMND like N'%"+input+"%' " ; 
				sqlseach += " or SDT like N'%"+input+"%' " ; 
				
				Connection conn = getConnect();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlseach);
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int colNo = rsmd.getColumnCount();
				Vector vtColumn = new Vector(colNo);
				String[] tableTitle =  {"Họ và tên","Giới tính","Địa chỉ","CMND","SDT"};
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
				scroll.setViewportView(table);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == xoa) {
			select = table.getSelectedRow();
			System.out.println(select);
			xoa();
		}
		if(e.getSource() == them) {
			new Update(this, "Update Form", "", "", "", "","","");
		}
		if(e.getSource() == sua)  {
			select = table.getSelectedRow();
			if(select != -1) {
				Vector<String> v = vData.elementAt(select);
				new Update(this, "Edit Form", v.elementAt(0), v.elementAt(1), v.elementAt(2), v.elementAt(3),v.elementAt(4),v.elementAt(5));
			}
		}
		if(e.getSource() == timkiem) {
			Vector<String> v = vData.elementAt(select);
				new Update(this, ".....", "", "", "", "","","");
		}
		if(e.getSource() == quaylui) {
			new Menu();
			manaFrame.dispose();
		}
	}
}

