package QL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import QL.Chuyenbay;
import QL.Khachhang;
import QL.Ve;;

 
public class Menu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//thuoc tinh
	private JPanel p;
		// constructor
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Khởi tạo thuộc tính
		JLabel tieude = new JLabel("HỆ THỐNG QUẢN LÍ BÁN VÉ MÁY BAY ONLINE ", JLabel.CENTER);
		JButton khachhang = new JButton("Thông tin khách hàng");
		JButton chuyenbay = new JButton("Quản lí chuyến bay");
		JButton ve = new JButton("Quản lí vé");
		JButton ghe = new JButton("Quản lí ghế");
		JButton thongke = new JButton("Thống kê");
		p = new JPanel();
		
		p.add(khachhang);
		p.add(chuyenbay);
		p.add(tieude);
		p.add(ve);
		p.add(tieude);
		p.add(ghe);
		p.add(thongke);
		setBounds(100, 100, 797, 530);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		// Tiêu đề
		tieude.setBounds(127, 33, 494, 110);
		// Khách hàng
		khachhang.setBounds(10, 300, 155, 49);
		khachhang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				new	Khachhang();
			}
		});
		p.setLayout(null);	
		// chuyến bay
	chuyenbay.setBounds(178, 300, 155, 49);
	chuyenbay.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new	Chuyenbay("");
		}	
	});
	// Vé
	ve.setBounds(345, 300, 133, 49);
	ve.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new	Ve();
		}	
	});
	// Ghế
	ghe.setBounds(488, 300, 125, 49);
	ghe.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new	Ghe();
		}	
	});
	// Thống kê
	thongke.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new Thongke();
		}
	});
	thongke.setBounds(623, 300, 115, 49);
	setVisible(true);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}