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

public class Dycoffee extends Frame implements ActionListener{
	public static void main(String[] args) {
		Dycoffee d = new Dycoffee();	
	}
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	Font font20 = new Font("SansSerif", Font.PLAIN, 20);
	Font font30 = new Font("SansSerif", Font.BOLD, 30);
	
	int coin=0; //�������Աݾ�
	int totCoin=0; //�Ѱ����ݾ�
	
	//Ŀ�� �ݾ� ���� Ŀ�Ǹ�
	int coffeePrice[] = {4500,5500,5800,6500,5500,4000};
	int coffeeCnt[] = {1,2,3,4,5,6};
	String coffeeName[] = {"�Ƹ޸�ī��","��īĿ��","īǪġ��","ī��� ��Ű�߶�","�ٴҶ� ��","����������"};
	
	//������ Ŀ�� ����
	int selCoffee = 0; //0~5�� ���ʴ��Ŀ�� ������ �迭�� �����س��� 
	
	boolean makeCheck=true;
	
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private Image img5;
	private Image img6;	
	private Image imgSel;// ���� ������ Ŀ�ǻ���
	
	
	Label lbTitle = new Label("DoyungBucks");
	Label lbCoffee1Cnt = new Label("���� "+coffeeCnt[0]+"��",Label.CENTER);
	Label lbCoffee2Cnt = new Label("���� "+coffeeCnt[1]+"��",Label.CENTER);
	Label lbCoffee3Cnt = new Label("���� "+coffeeCnt[2]+"��",Label.CENTER);
	Label lbCoffee4Cnt = new Label("���� "+coffeeCnt[3]+"��",Label.CENTER);
	Label lbCoffee5Cnt = new Label("���� "+coffeeCnt[4]+"��",Label.CENTER);
	Label lbCoffee6Cnt = new Label("���� "+coffeeCnt[5]+"��",Label.CENTER);
	Label lbCoffeeSelCnt = new Label("0��",Label.CENTER);
	Label lbCoffee1Price = new Label(coffeePrice[0]+"��",Label.CENTER);
	Label lbCoffee2Price = new Label(coffeePrice[1]+"��",Label.CENTER);
	Label lbCoffee3Price = new Label(coffeePrice[2]+"��",Label.CENTER);
	Label lbCoffee4Price = new Label(coffeePrice[3]+"��",Label.CENTER);
	Label lbCoffee5Price = new Label(coffeePrice[4]+"��",Label.CENTER);
	Label lbCoffee6Price = new Label(coffeePrice[5]+"��",Label.CENTER);
	Label lbSelTitle = new Label("DoyungBucks");
	Label lbJan1 = new Label("�����ܾ�  : ");
	Label lbJan2 = new Label(coin+"��",Label.RIGHT);
	Label lbCoin1 = new Label("�����ݾ�  : ");
	Label lbCoin2 = new Label("0��",Label.RIGHT);
	
	Label lbCoin = new Label("< ���Աݾ� >");
	Label lbSel = new Label("< ���� ��ǰ >");
	Label lbSelPrice = new Label("���� :    0��");

	Button btnCofee1 = new Button(coffeeName[0]);
	Button btnCofee2 = new Button(coffeeName[1]);
	Button btnCofee3 = new Button(coffeeName[2]);
	Button btnCofee4 = new Button(coffeeName[3]);
	Button btnCofee5 = new Button(coffeeName[4]);
	Button btnCofee6 = new Button(coffeeName[5]);
	Button btn1000 = new Button("1000��");
	Button btn5000 = new Button("5000��");
	Button btn10000 = new Button("10000��");
	Button btnBuy = new Button("�����ϱ�");
	
	Button btbefore = new Button("����");
	Button btreset = new Button("�ٽ�");
	
	Dycoffee()
	{
		super("Ŀ���ֹ�ȭ��");
		this.setSize(900,800);
		this.center();
		this.init();
		this.startEvent();
		this.setVisible(true);
	}
	void init() {	
		//�Ƹ޸�ī��
		img1  = Toolkit.getDefaultToolkit().getImage("americano.jpg");
		//��īĿ��
		img2 = Toolkit.getDefaultToolkit().getImage("mocha.jpg");
		//īǪġ��
		img3 = Toolkit.getDefaultToolkit().getImage("cappuccino.jpg");
		//ī��ḶŰ�߶�
		img4 = Toolkit.getDefaultToolkit().getImage("caramel.jpg");
		//�ٴҶ��
		img5 = Toolkit.getDefaultToolkit().getImage("vanilla.jpg");
		//����������
		img6 = Toolkit.getDefaultToolkit().getImage("espresso.jpg");
		//���û���
		imgSel = Toolkit.getDefaultToolkit().getImage("dybucks.jpg");
		
		this.setLayout(null);
		this.add(lbTitle);
		this.add(lbCoffee1Cnt);this.add(lbCoffee1Price);this.add(btnCofee1);
		this.add(lbCoffee2Cnt);this.add(lbCoffee2Price);this.add(btnCofee2);
		this.add(lbCoffee3Cnt);this.add(lbCoffee3Price);this.add(btnCofee3);
		this.add(lbCoffee4Cnt);this.add(lbCoffee4Price);this.add(btnCofee4);
		this.add(lbCoffee5Cnt);this.add(lbCoffee5Price);this.add(btnCofee5);
		this.add(lbCoffee6Cnt);this.add(lbCoffee6Price);this.add(btnCofee6);
		this.add(lbCoffeeSelCnt);
		this.add(btn1000);this.add(btn5000);this.add(btn10000);
		this.add(btnBuy); 
		this.add(lbCoin);
		this.add(lbCoin1);
		this.add(lbCoin2);
		this.add(lbSelTitle);
		this.add(lbJan1);
		this.add(lbJan2);
		this.add(lbSel);
		this.add(lbSelPrice);
		this.add(btbefore); this.add(btreset);
		
		lbTitle.setBounds(50,50,200,50);lbTitle.setFont(font30);
		//�Ƹ޸�ī��
		lbCoffee1Cnt.setBounds(70,285,100,30);lbCoffee1Cnt.setFont(font20);
		lbCoffee1Price.setBounds(80,320,100,30);lbCoffee1Price.setFont(font20);
		btnCofee1.setBounds(50,250,150,30);btnCofee1.setFont(font20);

		//��īĿ��
		lbCoffee2Cnt.setBounds(275,285,100,30);lbCoffee2Cnt.setFont(font20);
		lbCoffee2Price.setBounds(280,320,100,30);lbCoffee2Price.setFont(font20);
		btnCofee2.setBounds(280,250,100,30);btnCofee2.setFont(font20);

		//īǪġ��
		lbCoffee3Cnt.setBounds(475,285,100,30);lbCoffee3Cnt.setFont(font20);
		lbCoffee3Price.setBounds(480,320,100,30);lbCoffee3Price.setFont(font20);
		btnCofee3.setBounds(480,250,100,30);btnCofee3.setFont(font20);
		
		//ī��� ��Ű�߶�
		lbCoffee4Cnt.setBounds(65,550,100,30);lbCoffee4Cnt.setFont(font20);
		lbCoffee4Price.setBounds(75,580,100,30);lbCoffee4Price.setFont(font20);
		btnCofee4.setBounds(50,520,150,30);btnCofee4.setFont(font20);

		//�ٴҶ� ��
		lbCoffee5Cnt.setBounds(275,550,100,30);lbCoffee5Cnt.setFont(font20);
		lbCoffee5Price.setBounds(280,580,100,30);lbCoffee5Price.setFont(font20);
		btnCofee5.setBounds(250,520,150,30);btnCofee5.setFont(font20);

		//����������
		lbCoffee6Cnt.setBounds(475,550,100,30);lbCoffee6Cnt.setFont(font20);
		lbCoffee6Price.setBounds(480,580,100,30);lbCoffee6Price.setFont(font20);
		btnCofee6.setBounds(450,520,150,30);btnCofee6.setFont(font20);
		
		lbCoin.setBounds(45,620,150,30);lbCoin.setFont(font20);
		btn1000.setBounds(45,650,150,50);btn1000.setFont(font20);
		btn5000.setBounds(210,650,150,50);btn5000.setFont(font20);
		btn10000.setBounds(370,650,150,50);btn10000.setFont(font20);
		
		lbJan1.setBounds(640,550,100,30);lbJan1.setFont(font20);
		lbJan2.setBounds(750,550,100,30);lbJan2.setFont(font20);
		lbCoin1.setBounds(640,600,100,30);lbCoin1.setFont(font20);
		lbCoin2.setBounds(750,600,100,30);lbCoin2.setFont(font20);
		

		lbSel.setBounds(660,100,200,50);lbSel.setFont(font30);
		lbSelTitle.setBounds(700,350,150,30);lbSelTitle.setFont(font20);
		lbSelPrice.setBounds(690,400,200,30);lbSelPrice.setFont(font20);
		btnBuy.setBounds(650,650,150,50);btnBuy.setFont(font20);
		btbefore.setFont(font20); btreset.setFont(font20);
		btbefore.setBounds(780,50,100,30); btreset.setBounds(650,50,100,30);

		
	}	
	public void paint(Graphics g) {	
		
			g.drawImage(img1, 50, 100, 150, 150, this);
			g.drawImage(img2, 250, 100, 150, 150, this);
			g.drawImage(img3, 450, 100, 150, 150, this);
			g.drawImage(img4, 50, 360, 150, 150, this);
			g.drawImage(img5, 250, 360, 150, 150, this);
			g.drawImage(img6, 450, 360, 150, 150, this);
			
			g.drawImage(imgSel, 680, 180, 150, 150, this); //���û�ǰ
			
			
			
	}
	void startEvent()
	{
		btnCofee1.addActionListener(this);
		btnCofee2.addActionListener(this);
		btnCofee3.addActionListener(this);
		btnCofee4.addActionListener(this);
		btnCofee5.addActionListener(this);
		btnCofee6.addActionListener(this);
		btn1000.addActionListener(this);
		btn5000.addActionListener(this);
		btn10000.addActionListener(this);
		btnBuy.addActionListener(this);
		btbefore.addActionListener(this);
		btreset.addActionListener(this);
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

	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==btnpay) {
//			msg("���� â���� �Ѿ�ϴ�.");
//			Dyreceipt dyr = new Dyreceipt();
//		}//����â
		if(e.getSource()==btbefore) {
			Dymain Dy = new Dymain();
			this.setVisible(false);
		}//����
		if(e.getSource()==btreset) {
			
		}//����
		if(e.getSource() == btnCofee1)
		{
			selCoffee=0;//1��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("americano.jpg");
			totCoin = coffeePrice[0];
			lbCoin2.setText(totCoin+"��");	
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[0]);
			this.repaint();
					
		}
		else if(e.getSource() == btnCofee2)
		{
			selCoffee=1;//2��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("mocha.jpg");
			totCoin = coffeePrice[1];
			lbCoin2.setText(totCoin+"��");
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[1]);
			this.repaint();
		}
		else if(e.getSource() == btnCofee3)
		{
			selCoffee=2;//3��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("cappuccino.jpg");
			totCoin = coffeePrice[2];
			lbCoin2.setText(totCoin+"��");
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[2]);
			this.repaint();
		}
		else if(e.getSource() == btnCofee4)
		{
			selCoffee=3;//4��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("caramel.jpg");
			totCoin = coffeePrice[3];
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[3]);
			lbCoin2.setText(totCoin+"��");
			
			this.repaint();
		}
		else if(e.getSource() == btnCofee5)
		{
			selCoffee=4;//5��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("vanilla.jpg");
			totCoin = coffeePrice[4];
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[4]);
			lbCoin2.setText(totCoin+"��");
			this.repaint();
		}
		else if(e.getSource() == btnCofee6)
		{
			selCoffee=5;//6��Ŀ�Ǽ���
			imgSel = Toolkit.getDefaultToolkit().getImage("espresso.jpg");
			totCoin = coffeePrice[5];
			lbSelPrice.setText(totCoin+"��");
			lbSelTitle.setText(coffeeName[5]);
			lbCoin2.setText(totCoin+"��");
			this.repaint();
		}
		else if(e.getSource() == btn1000)
		{
			coin+=1000;
			lbJan2.setText(coin+"��");
		}
		else if(e.getSource() == btn5000)
		{
			coin+=5000;
			lbJan2.setText(coin+"��");
		}
		else if(e.getSource() == btn10000)
		{
			coin+=10000;
			lbJan2.setText(coin+"��");
		}
		else if(e.getSource() == btnBuy)
		{
			//�ܾ��� �ּ� 4000���̻� ������쿡�� ����
			if(coin < 4000)
			{
				msg("�ּ�4000���̻� �ݾ��������ϼ���.");
				return;
			}
			//��ǰ���ø޼��� ���
			if(totCoin==0)
			{
				msg("������ Ŀ�Ǹ� �������ּ���.");
				return;
			}
			if(totCoin>coin) {
				msg("��� �ʰ� �Դϴ�.");
				return;
			}
			//����üũ �޼���
			countCheck(selCoffee);
			
			if(makeCheck==true)
			{			
				//������ Ŀ�� ����
				coin-=totCoin;
				//�����ȱݾ�ǥ��
				lbJan2.setText(coin+"��");		
				
				
				//����ǥ���ϱ�			
				try {
					lbSelTitle.setText("���ִ�");
					Thread.sleep(100);
				} catch (InterruptedException e1) {	}
				try {
					lbSelTitle.setText("Ŀ�Ǹ�");
					Thread.sleep(100);
				} catch (InterruptedException e1) {	}
				try {
					lbSelTitle.setText("�����մϴ�.");
					Thread.sleep(100);
				} catch (InterruptedException e1) {	}
				msg("���ְ� �弼��.");			
				
				imgSel = Toolkit.getDefaultToolkit().getImage("dybucks.jpg");
				this.repaint();
			}
			else
			{
				makeCheck=true;
			}
			
			
		}	
	}
	void countCheck(int selCoffee)
	{
		if(coffeeCnt[selCoffee]==0)
		{
			
			msg("ǰ���Ǿ����ϴ�.");			
			makeCheck=false;
			return;
		}		
		if(selCoffee==0)
		{
			coffeeCnt[0]--;
			lbCoffee1Cnt.setText("���� "+coffeeCnt[0]+"��");
			//ǰ���� 
			if(coffeeCnt[0]==0) {
				img1 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
			
		}
		else if(selCoffee==1)
		{
			
			coffeeCnt[1]--;
			//ǰ���� 
			lbCoffee2Cnt.setText("���� "+coffeeCnt[1]+"��");
			if(coffeeCnt[1]==0) {
				img2 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
		}
		else if(selCoffee==2)
		{
			coffeeCnt[2]--;
			lbCoffee3Cnt.setText("���� "+coffeeCnt[2]+"��");
			//ǰ���� 
			if(coffeeCnt[2]==0) {
				img3 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
			
		}
		else if(selCoffee==3)
		{
			coffeeCnt[3]--;
			lbCoffee4Cnt.setText("���� "+coffeeCnt[3]+"��");
			//ǰ���� 
			if(coffeeCnt[3]==0) {
				img4 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
		}
		else if(selCoffee==4)
		{
			coffeeCnt[4]--;
			lbCoffee5Cnt.setText("���� "+coffeeCnt[4]+"��");
			//ǰ���� 
			if(coffeeCnt[4]==0) {
				img5 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
		}
		else if(selCoffee==5)
		{
			coffeeCnt[5]--;
			lbCoffee6Cnt.setText("���� "+coffeeCnt[5]+"��");
			//ǰ���� 
			if(coffeeCnt[5]==0) {
				img6 = Toolkit.getDefaultToolkit().getImage("soldout.png");
				this.repaint();
			}
		}
	}
	void msg(String msg)
	{
		final Dialog dlg = new Dialog(this, "�˸� �޼���â", true);
		dlg.setLayout(null);
		Label lbMsg = new Label(msg);
		
		dlg.add(lbMsg);	lbMsg.setFont(font20);
		lbMsg.setBounds(100, 100, 450, 30);
		
		dlg.setSize(500, 250);
		dlg.setLocation(450, 250);
		
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dlg.setVisible(false);
			}
		});
		dlg.setVisible(true);
	}
	
	void search() {}
	
	void searchAll() {}
	

}


