import java.awt.*;
import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;


public class GUI extends JPanel implements ActionListener
{
	private Timer timer;
	int t;
	int u;
	Pret pr;
	static Modul_ewolucyjny evo;
	double wynik;
	
	public GUI()
	{
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setSize(900, 560);
		t= 100;
		u = 0;
		evo = new Modul_ewolucyjny(2,4);
		pr = evo.pretSymulacyjny;
		evo.losujWagi();
		wynik = pr.symuluj(evo.wagi, evo.wagiWyjscie);
		evo.ewoluuj(0.005);
		Runnable r1 = new watekEwolucyjny();
		Thread th = new Thread(r1);
		th.start();
		timer = new Timer(10, this);
		timer.start();
		///
		
		
	}
	
	
	public void paint(Graphics g)
	{
	super.paint(g);
	//asd
	Graphics2D g2d = (Graphics2D)g;
	g.setColor(Color.white);
	g.setFont(new Font("Arial", Font.BOLD, 40));
	g2d.drawString("PSZT - Pret", 15, 40);
	g.setFont(new Font("Arial", Font.BOLD, 10));
	int centerX,centerY,radius ;
	centerX= 450;
	centerY = 280;
	radius = 180;
	Shape theCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
	g2d.draw(theCircle);
	double pX, pY,pR;
	double rads ;
	rads = Math.toRadians(pr.alfy[t]);
	pR = 20;
	pX = centerX + (int)(radius * Math.sin(rads));
	pY = centerY - (int)(radius * Math.cos(rads));
	g.setColor(Color.red);
	g.setFont(new Font("Arial", Font.BOLD, 10));
	Shape pointer= new Ellipse2D.Double(pX - pR, pY - pR, 2.0 * pR, 2.0 * pR);
	g2d.draw(pointer);
	g2d.draw(new Line2D.Double(centerX,centerY,pX,pY));

	//Lines
	g.setColor(Color.white);
	g2d.draw(new Line2D.Double (0,50, 900,50));
	g2d.draw(new Line2D.Double (200,50, 200,560));
	g2d.draw(new Line2D.Double (700,50, 700,560));
	g.setFont(new Font("Arial", Font.BOLD, 15));
	//Left
	g2d.drawString("Postêp symulacji:", 10, 70);
	g2d.drawRect(10,90 , 180, 20);
	g2d.fillRect(10,90 ,(int)(t*(180.0/999.0)) , 20);
	g2d.drawString("probka:"+ t +" (" +(int)((t/999.0)*100.0) + " %)", 10, 140);
	//angle
	g.setColor(Color.red);
	g2d.drawString("Angle:"+ pr.alfy[t], 210, 80);
	g.dispose();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (t >= 999) {t = 0; System.out.println(pr.ostatniWynik);} else t++;
		if (u >= 360) u = 0; else u++;
		System.out.print(t + " " + pr.alfy[t] + "\n");
		repaint();
	}
	
	private class watekEwolucyjny implements Runnable
	{	
		public watekEwolucyjny (){
		}
		
		public void run (){
			evo.ewoluuj(0.5);
		}
	}
}
