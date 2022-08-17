package doyung;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class Dymain extends Frame implements ActionListener{
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Image img;
	Font f20 = new Font("SansSerif", Font.PLAIN, 20);
	Font f30 = new Font("SansSerif", Font.BOLD, 30);
	
	Label lbtitle = new Label("Doyung");
	Label lbtitle2 = new Label("Bucks");

	Button btorder = new Button("Order");
	Button bttake = new Button("TakeOut");
	Dymain(){
		super("메인화면");
		this.setSize(450, 600);
		this.start();
		this.center();
		this.init();
		this.setVisible(true);
	}
	void init() {
		this.setLayout(null);
		this.add(lbtitle);	this.add(btorder);	
		this.add(lbtitle2);	this.add(bttake);
		
		
		lbtitle.setFont(f30);	btorder.setFont(f30);	
		lbtitle2.setFont(f30);	bttake.setFont(f30);
		
		
		lbtitle.setBounds(160,50,150,50);	btorder.setBounds(60,470,150,50); 
		lbtitle2.setBounds(170,100,150,50);	bttake.setBounds(250,470,150,50);
		img = Toolkit.getDefaultToolkit().getImage("dybucks.jpg");
	}
	public void paint(Graphics g) {
		String date = new Date().toString();
		g.drawString(date, 140, 40);
		g.drawImage(img, 100, 170, 250, 250, this);
	}
	void start() {
		btorder.addActionListener(this);
		bttake.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btorder) {
			
			Dycoffee d = new Dycoffee();
			this.setVisible(false);
		}
		if(e.getSource()==bttake) {
			
			Dycoffee d = new Dycoffee();
			this.setVisible(false);
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
				System.exit(0);
			}
		});
	}
	
}
