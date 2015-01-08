
import javax.swing.JFrame;
import java.awt.*;

public class Program extends JFrame
{
	public Program() 
	{
		add(new GUI());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 560);
		setLocationRelativeTo(null);
		setTitle("Prent");
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new Program();
		SiecNeuronowaPretu pret = new SiecNeuronowaPretu(2, 4);
		double[][] wagi = new double [4][3];
		double[] wagi_out = new double[5];
		for (int i = 0; i<4 ; ++i)
		for (int j = 0; j<3 ; ++j)
		wagi[i][j]=1.0;
		wagi_out[0]= 1.0 ;
		wagi_out[1]= 1.0 ;
		wagi_out[2]= 1.0 ;
		wagi_out[3]= 1.0 ;
		wagi_out[4]= 1.0 ;
		//Modul_ewolucyjny evo = new Modul_ewolucyjny(2,4);
		//evo.losujWagi();
		//pret.przypiszWagi(wagi, wagi_out);
		//evo.ewoluuj(0.1);
		//pret.przypiszWagi(evo.podajWagi(), evo.podajWagiOut());
		//pret.pokazWagi();
		double[] input = new double[2];
		input[0]= 1.0;
		input[1]= 1.0;
		//Pret pret = new Pret(4, 2);
		double wynik = pret.obliczWartosc(input);
		//System.out.println(alfy[1]);
		System.out.print("\nWYNIK : " + wynik);
	}
	
}