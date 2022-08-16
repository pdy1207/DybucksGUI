package doyung;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dymanager extends Frame implements ActionListener {
	
	String coffee[][] = new String[6][4];
	
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	Font font20 = new Font("SansSerif", Font.PLAIN, 20);
	Font font30 = new Font("SansSerif", Font.BOLD, 30);
	
	private Image imgSel;
	
	Label lbTitle = new Label("DyBucks manager");
	
	Label lbProduct  = new Label("상품",Label.CENTER);
	Label lbProduct2 = new Label("상품2",Label.CENTER);
	Label lbProduct3 = new Label("상품3",Label.CENTER);
	Label lbProduct4 = new Label("상품4",Label.CENTER);
	Label lbProduct5 = new Label("상품5",Label.CENTER);
	Label lbProduct6 = new Label("상품6",Label.CENTER);
	
	Label lbName  = new Label("이름 : ",Label.CENTER);
	Label lbName2 = new Label("이름 : ",Label.CENTER);
	Label lbName3 = new Label("이름 : ",Label.CENTER);
	Label lbName4 = new Label("이름 : ",Label.CENTER);
	Label lbName5 = new Label("이름 : ",Label.CENTER);
	Label lbName6 = new Label("이름 : ",Label.CENTER);
	
	Label lbCoffeeCnt  = new Label("수량 : ",Label.CENTER);
	Label lbCoffeeCnt2 = new Label("수량 : ",Label.CENTER);
	Label lbCoffeeCnt3 = new Label("수량 : ",Label.CENTER);
	Label lbCoffeeCnt4 = new Label("수량 : ",Label.CENTER);
	Label lbCoffeeCnt5 = new Label("수량 : ",Label.CENTER);
	Label lbCoffeeCnt6 = new Label("수량 : ",Label.CENTER);
	
	Label lbCoffeePrice  = new Label("가격 : ",Label.CENTER);
	Label lbCoffeePrice2 = new Label("가격 : ",Label.CENTER);
	Label lbCoffeePrice3 = new Label("가격 : ",Label.CENTER);
	Label lbCoffeePrice4 = new Label("가격 : ",Label.CENTER);
	Label lbCoffeePrice5 = new Label("가격 : ",Label.CENTER);
	Label lbCoffeePrice6 = new Label("가격 : ",Label.CENTER);
	
	TextField tfCoffeeName  = new TextField();
	TextField tfCoffeeName2 = new TextField();
	TextField tfCoffeeName3 = new TextField();
	TextField tfCoffeeName4 = new TextField();
	TextField tfCoffeeName5 = new TextField();
	TextField tfCoffeeName6 = new TextField();
	
	TextField tfCnt  = new TextField();
	TextField tfCnt2 = new TextField();
	TextField tfCnt3 = new TextField();
	TextField tfCnt4 = new TextField();
	TextField tfCnt5 = new TextField();
	TextField tfCnt6 = new TextField();
	
	TextField tfPrice  = new TextField();
	TextField tfPrice2 = new TextField();
	TextField tfPrice3 = new TextField();
	TextField tfPrice4 = new TextField();
	TextField tfPrice5 = new TextField();
	TextField tfPrice6 = new TextField();
	
	Button btnCoffee  = new Button("적용");
	Button btnCoffee2 = new Button("적용");
	Button btnCoffee3 = new Button("적용");
	Button btnCoffee4 = new Button("적용");
	Button btnCoffee5 = new Button("적용");
	Button btnCoffee6 = new Button("적용");
	
	Button btbefore = new Button("처음으로");
	Button btbefore2 = new Button("이전");
	Button btLookup = new Button("조회");
	
	Dymanager(){
		super("관리자화면");
		this.setSize(900,650);
		this.setVisible(true);
		this.center();
		this.start();
		this.init();
		//DB연동
		getData();
	}
	void start() {
		btnCoffee.addActionListener(this);
		btnCoffee2.addActionListener(this);
		btnCoffee3.addActionListener(this);
		btnCoffee4.addActionListener(this);
		btnCoffee5.addActionListener(this);
		btnCoffee6.addActionListener(this);
		
		btbefore.addActionListener(this);
		btbefore2.addActionListener(this);
		btLookup.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btbefore) {
			msg("처음으로 돌아갑니다.");
			Dymain Dy = new Dymain();
			this.setVisible(false);
		}//처음으로
		if(e.getSource()==btbefore2) {
			Dycoffee Dy = new Dycoffee();
			this.setVisible(false);
		}//이전
		
		String coffeename  = tfCoffeeName.getText();
		String coffeename2 = tfCoffeeName2.getText();
		String coffeename3 = tfCoffeeName3.getText();
		String coffeename4 = tfCoffeeName4.getText();
		String coffeename5 = tfCoffeeName5.getText();
		String coffeename6 = tfCoffeeName6.getText();
		
		String coffeeCnt  = tfCnt.getText();
		String coffeeCnt2 = tfCnt2.getText();
		String coffeeCnt3 = tfCnt3.getText();
		String coffeeCnt4 = tfCnt4.getText();
		String coffeeCnt5 = tfCnt5.getText();
		String coffeeCnt6 = tfCnt6.getText();
		
		String coffeePrice  = tfPrice.getText();
		String coffeePrice2 = tfPrice2.getText();
		String coffeePrice3 = tfPrice3.getText();
		String coffeePrice4 = tfPrice4.getText();
		String coffeePrice5 = tfPrice5.getText();
		String coffeePrice6 = tfPrice6.getText();
		
		
		
		if(e.getSource()==btnCoffee) {
			if(spaceChcek(coffeename,coffeeCnt,coffeePrice)) {return;}
			else{
				update(1, coffeename,coffeeCnt,coffeePrice);
			}
		}
		if(e.getSource()==btnCoffee2) {
			if(spaceChcek(coffeename2,coffeeCnt2,coffeePrice2)) {return;}
			else{
				update(2, coffeename2,coffeeCnt2,coffeePrice2);
			}
		}
		if(e.getSource()==btnCoffee3) {
			if(spaceChcek(coffeename3,coffeeCnt3,coffeePrice3)) {return;}
			else{
				update(3, coffeename3,coffeeCnt3,coffeePrice3);
			}
		}
		if(e.getSource()==btnCoffee4) {
			if(spaceChcek(coffeename4,coffeeCnt4,coffeePrice4)) {return;}
			else{
				update(4, coffeename4,coffeeCnt4,coffeePrice4);
			}
		}
		if(e.getSource()==btnCoffee5) {
			if(spaceChcek(coffeename5,coffeeCnt5,coffeePrice5)) {return;}
			else{
				update(5, coffeename5,coffeeCnt5,coffeePrice5);
			}
		}
		if(e.getSource()==btnCoffee6) {
			if(spaceChcek(coffeename6,coffeeCnt6,coffeePrice6)) {return;}
			else{
				update(6, coffeename6,coffeeCnt6,coffeePrice6);
			}
		}
		
	}
	boolean update(int idx, String name, String amount, String price) { //업데이트
		Connection dc = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("드라이브가 있습니당~! ^_^v");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브 없음!!");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dybucks?"
				+ "useUnicode=true&characterEncoding=utf8";
		String user = "root";// ID
		String password = "qwer";// 비밀번호
		try {
			dc = DriverManager.getConnection(url, user, password);
		} catch (SQLException ee) {
		}
		String query = "update coffeebucks set name = ?, amount = ?, price = ? where idx = ?";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, amount);
			pstmt.setString(3, price);
			pstmt.setInt(4, idx);
			pstmt.executeUpdate();
			pstmt.close();
			msg("수정완료!");
		} catch (SQLException ee) {
			System.err.println("회원 정보수정 실패!!"+ee.getMessage());
			return false;
		}
		return true;
	}
	boolean spaceChcek(String name, String amount, String price) {  //메세지 정리
		if(name.equals("")) {msg("커피명을 적어주세요.");     return true;}
		if(amount.equals("")) {msg("수량을 적어주세요."); 	  return true;}
		if(price.equals("")) {msg("가격을 적어주세요.");  	  return true;}
		//공백이 아니면 false
		return false;
	}
	
	void getData() { //화면에 뿌려주기
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dybucks?"
				+ "useUnicode=true&characterEncoding=utf8";
		String id = "root"; //
		String pass = "qwer";
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from coffeebucks";
		try {
		conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int count=0;
			while (rs.next()) {
				coffee[count][0] = rs.getInt("idx")+"";
				coffee[count][1] = rs.getString("name");
				coffee[count][2] = rs.getString("amount");
				coffee[count][3] = rs.getString("price");
				count++;
				}
				//화면 뿌리기
					tfCoffeeName.setText(coffee[0][1]);
					tfCnt.setText(coffee[0][2]);
					tfPrice.setText(coffee[0][3]);
					
					tfCoffeeName2.setText(coffee[1][1]);
					tfCnt2.setText(coffee[1][2]);
					tfPrice2.setText(coffee[1][3]);
					
					tfCoffeeName3.setText(coffee[2][1]);
					tfCnt3.setText(coffee[2][2]);
					tfPrice3.setText(coffee[2][3]);
					
					tfCoffeeName4.setText(coffee[3][1]);
					tfCnt4.setText(coffee[3][2]);
					tfPrice4.setText(coffee[3][3]);
					
					tfCoffeeName5.setText(coffee[4][1]);
					tfCnt5.setText(coffee[4][2]);
					tfPrice5.setText(coffee[0][3]);
					
					tfCoffeeName6.setText(coffee[5][1]);
					tfCnt6.setText(coffee[5][2]);
					tfPrice6.setText(coffee[5][3]);	
				
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}
	void init() {
		imgSel = Toolkit.getDefaultToolkit().getImage("dybucks.jpg");
		
		this.setLayout(null);
		this.add(lbTitle); 
		this.add(lbProduct);  this.add(lbName);
		this.add(lbProduct2); this.add(lbName2);
		this.add(lbProduct3); this.add(lbName3);
		this.add(lbProduct4); this.add(lbName4);
		this.add(lbProduct5); this.add(lbName5);
		this.add(lbProduct6); this.add(lbName6);
		
		this.add(lbCoffeeCnt);  this.add(lbCoffeePrice); 
		this.add(lbCoffeeCnt2); this.add(lbCoffeePrice2); 
		this.add(lbCoffeeCnt3); this.add(lbCoffeePrice3); 
		this.add(lbCoffeeCnt4); this.add(lbCoffeePrice4); 
		this.add(lbCoffeeCnt5); this.add(lbCoffeePrice5); 
		this.add(lbCoffeeCnt6); this.add(lbCoffeePrice6); 
		
		this.add(tfCoffeeName);  this.add(btnCoffee);
		this.add(tfCoffeeName2); this.add(btnCoffee2);
		this.add(tfCoffeeName3); this.add(btnCoffee3);
		this.add(tfCoffeeName4); this.add(btnCoffee4);
		this.add(tfCoffeeName5); this.add(btnCoffee5);
		this.add(tfCoffeeName6); this.add(btnCoffee6);
		
		this.add(tfCnt);  this.add(tfPrice);
		this.add(tfCnt2); this.add(tfPrice2);
		this.add(tfCnt3); this.add(tfPrice3);
		this.add(tfCnt4); this.add(tfPrice4);
		this.add(tfCnt5); this.add(tfPrice5);
		this.add(tfCnt6); this.add(tfPrice6);
		
		this.add(btbefore); this.add(btbefore2); this.add(btLookup);
		
		btbefore.setFont(font20);btbefore.setBounds(600,80,110,50);
		btbefore2.setFont(font20);btbefore2.setBounds(750,80,110,50);
		btLookup.setFont(font20);btLookup.setBounds(450,80,110,50);
		
		lbTitle.setFont(font30); lbTitle.setBounds(130,70,300,50);
		//커피 1.
		lbProduct.setFont(font20); lbProduct.setBounds(50,180,80,50);
		lbName.setFont(font20); lbName.setBounds(130,180,80,50);
		tfCoffeeName.setFont(font20); tfCoffeeName.setBounds(210,190,160,30);
		lbCoffeeCnt.setFont(font20); lbCoffeeCnt.setBounds(380,190,80,30);
		tfCnt.setFont(font20); tfCnt.setBounds(470,190,50,30);
		lbCoffeePrice.setFont(font20); lbCoffeePrice.setBounds(530,190,80,30);
		tfPrice.setFont(font20); tfPrice.setBounds(610,190,110,30);
		btnCoffee.setFont(font20); btnCoffee.setBounds(750,180,110,50);
		//커피 2.
		lbProduct2.setFont(font20); lbProduct2.setBounds(50,250,80,50);
		lbName2.setFont(font20); lbName2.setBounds(130,250,80,50);
		tfCoffeeName2.setFont(font20); tfCoffeeName2.setBounds(210,260,160,30);
		lbCoffeeCnt2.setFont(font20); lbCoffeeCnt2.setBounds(380,260,80,30);
		tfCnt2.setFont(font20); tfCnt2.setBounds(470,260,50,30);
		lbCoffeePrice2.setFont(font20); lbCoffeePrice2.setBounds(530,260,80,30);
		tfPrice2.setFont(font20); tfPrice2.setBounds(610,260,110,30);
		btnCoffee2.setFont(font20); btnCoffee2.setBounds(750,250,110,50);
		//커피 3.
		lbProduct3.setFont(font20); lbProduct3.setBounds(50,320,80,50);
		lbName3.setFont(font20); lbName3.setBounds(130,320,80,50);
		tfCoffeeName3.setFont(font20); tfCoffeeName3.setBounds(210,330,160,30);
		lbCoffeeCnt3.setFont(font20); lbCoffeeCnt3.setBounds(380,330,80,30);
		tfCnt3.setFont(font20); tfCnt3.setBounds(470,330,50,30);
		lbCoffeePrice3.setFont(font20); lbCoffeePrice3.setBounds(530,330,80,30);
		tfPrice3.setFont(font20); tfPrice3.setBounds(610,330,110,30);
		btnCoffee3.setFont(font20); btnCoffee3.setBounds(750,320,110,50);
		//커피 4.
		lbProduct4.setFont(font20); lbProduct4.setBounds(50,390,80,50);
		lbName4.setFont(font20); lbName4.setBounds(130,390,80,50);
		tfCoffeeName4.setFont(font20); tfCoffeeName4.setBounds(210,400,160,30);
		lbCoffeeCnt4.setFont(font20); lbCoffeeCnt4.setBounds(380,400,80,30);
		tfCnt4.setFont(font20); tfCnt4.setBounds(470,400,50,30);
		lbCoffeePrice4.setFont(font20); lbCoffeePrice4.setBounds(530,400,80,30);
		tfPrice4.setFont(font20); tfPrice4.setBounds(610,400,110,30);
		btnCoffee4.setFont(font20); btnCoffee4.setBounds(750,390,110,50);
		//커피 5.
		lbProduct5.setFont(font20); lbProduct5.setBounds(50,460,80,50);
		lbName5.setFont(font20); lbName5.setBounds(130,460,80,50);
		tfCoffeeName5.setFont(font20); tfCoffeeName5.setBounds(210,470,160,30);
		lbCoffeeCnt5.setFont(font20); lbCoffeeCnt5.setBounds(380,470,80,30);
		tfCnt5.setFont(font20); tfCnt5.setBounds(470,470,50,30);
		lbCoffeePrice5.setFont(font20); lbCoffeePrice5.setBounds(530,470,80,30);
		tfPrice5.setFont(font20); tfPrice5.setBounds(610,470,110,30);
		btnCoffee5.setFont(font20); btnCoffee5.setBounds(750,460,110,50);
		//커피 6.
		lbProduct6.setFont(font20); lbProduct6.setBounds(50,530,80,50);
		lbName6.setFont(font20); lbName6.setBounds(130,530,80,50);
		tfCoffeeName6.setFont(font20); tfCoffeeName6.setBounds(210,540,160,30);
		lbCoffeeCnt6.setFont(font20); lbCoffeeCnt6.setBounds(380,540,80,30);
		tfCnt6.setFont(font20); tfCnt6.setBounds(470,540,50,30);
		lbCoffeePrice6.setFont(font20); lbCoffeePrice6.setBounds(530,540,80,30);
		tfPrice6.setFont(font20); tfPrice6.setBounds(610,540,110,30);
		btnCoffee6.setFont(font20); btnCoffee6.setBounds(750,530,110,50);
		
		
	}
	void center() {
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public void paint(Graphics g) {	
		g.drawImage(imgSel, 30, 50, 80, 80, this); //선택상품
		
}
	void msg(String msg)
	{
		final Dialog dlg = new Dialog(this, "알림 메세지창", true);
		dlg.setLayout(null);
		Label lbMsg = new Label(msg);
		
		dlg.add(lbMsg);	lbMsg.setFont(font20);
		lbMsg.setBounds(100, 100, 450, 30);
		
		dlg.setSize(500, 250);
		dlg.setLocation(650, 350);
		
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dlg.setVisible(false);
			}
		});
		dlg.setVisible(true);
	}
	
	
}
