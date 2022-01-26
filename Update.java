package QL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class Update extends JFrame implements ActionListener {
	private JLabel MA,HOVATEN,GIOITINH,DIACHI,CMND,SDT;
	private JTextField MAF, HOVATENF, GIOITINHF, DIACHIF,CMNDF,SDTF;
	private JButton ok, cancel;
	private Khachhang kh;
	
	public Update(Khachhang kh, String title,String ma, String hovaten, String gioitinh, String diachi, String cmnd, String sdt) {
		super(title);
		this.kh = kh;
		
		MA = new JLabel("Mã khách hàng:");
		HOVATEN = new JLabel("Họ và tên:");
		GIOITINH = new JLabel("Giới tính:");
		DIACHI = new JLabel("Địa chỉ:");
		CMND = new JLabel("CMND/CCCD:");
		SDT = new JLabel("SDT:");
		MAF = new JTextField(ma);
		if(title == "Edit Form") MAF.setEditable(false);
		
		HOVATENF = new JTextField(hovaten);
		GIOITINHF = new JTextField(gioitinh);
		DIACHIF = new JTextField(diachi);
		CMNDF = new JTextField(cmnd);
		SDTF = new JTextField(sdt);
		ok = new JButton("OK");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		this.setLayout(new GridLayout(7,2));
		this.add(MA);
		this.add(MAF);
		this.add(HOVATEN);
		this.add(HOVATENF);
		this.add(GIOITINH);
		this.add(GIOITINHF);
		this.add(DIACHI);
		this.add(DIACHIF);
		this.add(CMND);
		this.add(CMNDF);
		this.add(SDT);
		this.add(SDTF);
		this.add(ok);
		this.add(cancel);
		this.setVisible(true);
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel) {
			this.dispose();
		}
		if(e.getSource() == ok) {
			if(this.getTitle() == "Update Form") {
				boolean check = true;
				for(int i = 0; i < kh.vData.size(); i++) {
					if(MAF.getText().equals(kh.vData.elementAt(i).elementAt(0))) {
						check = false;
						break;
					}
				}
				if(check) {
					kh.them(MAF.getText(), HOVATENF.getText(), GIOITINHF.getText(), DIACHIF.getText(),CMNDF.getText(),SDTF.getText());
					this.dispose();
					JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");	
				}
				else JOptionPane.showMessageDialog(this , "ID đã tồn tại.");
			} else{
				kh.sua(MAF.getText(), HOVATENF.getText(), GIOITINHF.getText(), DIACHIF.getText(),CMNDF.getText(),SDTF.getText());
				this.dispose();
				kh.them(getName(), getName(), getName(), getTitle(), getWarningString(), getName());
			}
		}
	}
}
