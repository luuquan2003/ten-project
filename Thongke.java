package QL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.*; 
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset; 
public class Thongke extends data { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnl; 
	private JFrame f;
	Connection conn = null;
	ResultSet rs = null;
	public Thongke() {
		super();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		try {
			conn = getConnect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		Statement stmt = null;
		pnl = new JPanel(new BorderLayout());
	    f.setContentPane(pnl); 
	    f.setSize(400, 400); 
	    String sql = "select sum(sl)'Số Lượng',TenVL'Tên Vật Liệu'\r\n"+"from kho \r\n"+"group by TenVL";
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				//dataset.addValue(rs.getInt(1),"KHO",rs.getString(2));
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    try {
	    	String sql_kh = "select sum(sluong)'Số Lượng',TenVL'Tên Vật Liệu'\r\n"+"from KHACHHANG \r\n"+"group by TenVL";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql_kh);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
			//	dataset.addValue(rs.getInt(1),"KHACHHANG",rs.getString(2));
			}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // JFreeChart barChart = ChartFactory.createBarChart("Biểu Đồ Cột Thể Hiện Kho Và Lượng Mua Vật Liệu Của Cửa Hàng", "Vật Liệu", 
	   //   "Số Lượng", dataset, PlotOrientation.VERTICAL, true, true, false); 
	   // ChartPanel cPanel = new ChartPanel(barChart); 
	   // pnl.add(cPanel); 
		
		f.setVisible(true);
  }
}
