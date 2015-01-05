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
	
	public GUI()
	{
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setSize(900, 560);
		t= 100;
		u = 0;
		timer = new Timer(10, this);
		timer.start();
		///
		Modul_ewolucyjny evo = new Modul_ewolucyjny(2,4);
		pr = evo.pretSymulacyjny;
		evo.losujWagi();
		pr.symuluj(evo.wagi, evo.wagiWyjscie);
	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.fillRect(t, 1, 10, 10);
		g2d.drawString("Obiekt 14 - Prent", 15, 40);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g2d.drawString("t:"+ t, t+20, 10);
		int centerX,centerY,radius ;
		centerX = centerY = 200;
		radius = 100;
		Shape theCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
		g2d.draw(theCircle);
		double pX, pY,pR;
		//double alfa;
		//alfa = 180;
		//alfa = u;
		double rads ;
		//rads = Math.toRadians(alfa);
		rads = Math.toRadians(pr.alfy[t]);
		pR = 20;
		pX = centerX + (int)(radius * Math.sin(rads));
		pY = centerY - (int)(radius * Math.cos(rads));
		g.setColor(Color.red);
		g.fillRect(50+u, 50, 10, 10);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g2d.drawString("angle:"+ u, 70+u, 50);
		Shape pointer= new Ellipse2D.Double(pX - pR, pY - pR, 2.0 * pR, 2.0 * pR);
		g2d.draw(pointer);
		g2d.draw(new Line2D.Double(centerX,centerY,pX,pY));
		g.dispose();
	}
	public void actionPerformed(ActionEvent e)
	{
		if (t >= 999) t = 0; else t++;
		if (u >= 360) u = 0; else u++;
		System.out.print(t + " " + pr.alfy[t] + "\n");
		repaint();
	}
}
