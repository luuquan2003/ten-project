package QL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Chuyenbay extends data implements ActionListener, MouseListener {
	private JFrame manaFrame;
	private JPanel dataPanel, fucntionPanel;
	private JButton them,xoa,sua,timkiem,quaylui;
	private DefaultTableModel DT;
	private JScrollPane scroll;
	private JTable table;
	protected Vector<Vector<String>> vData = new Vector<Vector<String>>();
	private Vector<String> vTitle = new Vector<>();
	private int select = -1;
	
	public Chuyenbay(String title) {
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
	            ResultSet rs = statement.executeQuery("Select * from Chuyenbay");
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
				System.out.println(e.getMessage());
			}
	    }
		public void setDoDaiCot() {
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(30);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
			table.getColumnModel().getColumn(5).setPreferredWidth(20);
		}
		public void xoa() {
			if(vData.size() == 0) JOptionPane.showMessageDialog(manaFrame, "Bang khong co du lieu");
			else if(select == -1) JOptionPane.showMessageDialog(manaFrame, "Bạn cần chọn một hàng");
			else {
				try {
		    		Connection conn = getConnect();
		            
		            Statement statement = conn.createStatement();
		            String query = "Delete From Chuyenbay where ID = " + vData.elementAt(select).elementAt(0);
		            
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
			
		}
		if(e.getSource() == sua)  {
		
		}
		if(e.getSource() == timkiem) {
			
		}
		if(e.getSource() == quaylui) {
			new Menu();
			manaFrame.dispose();
		}
	}		
}

