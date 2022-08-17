package doyung;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Dytoday extends Frame implements ActionListener{
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Image img;
	Font f20 = new Font("SansSerif", Font.PLAIN, 20);
	Font f30 = new Font("SansSerif", Font.BOLD, 30);
	Font small = new Font("SansSerif", Font.PLAIN, 15);
	
	Label lbtitle = new Label("DoyungBucks");
	Button btn = new Button("출력");
	
	Label lb = new Label("------------------------------------------------------------------------------------------");
	Label lb2 = new Label("------------------------------------------------------------------------------------------");
	
	Label lbaddr = new Label("www.doyungbucks.co.kr");
	Label lbreceipt = new Label("||||| |||||| |||| ||||||||| |||||||||| ||||| ||||| |||");
	Label lbreceipt1 = new Label("220817031654134894621649684");
	
	TextArea tareceipt = new TextArea();
	Dytoday(){
		super("하루매출");
		this.setSize(450, 600);
		this.start();
		this.center();
		this.init();
		this.setVisible(true);
	}
	void init() {
		this.setLayout(null);
		this.add(lbtitle);		this.add(btn);
		this.add(lbaddr);		this.add(lbreceipt); this.add(lbreceipt1);
		this.add(lb); 			this.add(lb2); this.add(tareceipt);
		
		lb.setFont(small);	lb2.setFont(small);
		lbreceipt.setFont(f30);	lbaddr.setFont(small);	
		lbtitle.setFont(f30);	btn.setFont(small);
		tareceipt.setFont(f20); lbreceipt1.setFont(small);
		lb.setBounds(30,170,400,30); lb2.setBounds(30,390,400,30);
		
		lbreceipt.setBounds(50,450,350,50);	
		lbaddr.setBounds(150,410,400,50); 
		lbtitle.setBounds(170,50,200,50);	btn.setBounds(175,110,200,59);
		tareceipt.setBounds(30,200,400,200);
		lbreceipt1.setBounds(130,500,350,50);
		img = Toolkit.getDefaultToolkit().getImage("dybucks.jpg");
	}
	public void paint(Graphics g) {
		String date = new Date().toString();
		g.drawString(date, 140, 530);
		g.drawImage(img, 40, 60, 100, 100, this);
	}
	void start() {
		btn.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			searchAll();
		}
				
	}
	void searchAll()
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브가 연결안됨.");
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dybucks?"
				+ "useUnicode=true&characterEncoding=utf8";				
		String id = "root";
		String pass = "qwer";
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from coffeebucks";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			String result="";
			while (rs.next()) {
				result += rs.getInt(1) + " / " + rs.getString(2)
				+ " / " + rs.getString(3)
				+ " / " + rs.getString(4)
				+" / "+rs.getString(5)+"\n";			
			}
			tareceipt.setText(result);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}
	void center() {
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	void close()
	{
		this.setVisible(false);
	}
	
}
