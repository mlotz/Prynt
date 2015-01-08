import java.util.Random;


public class Modul_ewolucyjny {
	
	double[][] wagi;
	double[] wagiWyjscie;
	int n,m;
	int kroki;
	double c1,c2,sigma ;
	long evolutionTimeLimit; //seconds
	public Pret pretSymulacyjny, testowyPretSymulacyjny;
	
	public Modul_ewolucyjny(int iloscArgumentow, int iloscNeuronow)
	{
		wagi = new double [iloscNeuronow][iloscArgumentow+1];
		wagiWyjscie = new double[iloscNeuronow+1];
		n = iloscArgumentow;
		m = iloscNeuronow;
		//parametry evo , roboczo, potem arg konstrukotra?
		kroki = 10;
		c1 = 0.82;
		c2 = 1.2;
		evolutionTimeLimit = 10; //seconds
		sigma = 1.0; // ??? inne? dane ? losowe ?
		
		pretSymulacyjny = new Pret(iloscArgumentow, iloscNeuronow);
		testowyPretSymulacyjny = new Pret(iloscArgumentow, iloscNeuronow);
		
	}
	
	public void losujWagi()
	{
		for (int i = 0; i<m ; ++i)
			for (int j = 0; j<n+1 ; ++j)
				wagi[i][j]=Math.random() ;
		for (int j = 0; j<m+1 ; ++j)
			wagiWyjscie[j]=Math.random();
	}
	
	private void przypiszWagi(double[][] waga , double[] wagaWyjscie)
	{
		for (int i = 0; i<m ; ++i)
			for (int j = 0; j<n+1 ; ++j)
				wagi[i][j]=waga[i][j] ;
		for (int j = 0; j<m+1 ; ++j)
			wagiWyjscie[j]=wagaWyjscie[j];
	}
	
	public double[][] podajWagi()
	{
		return wagi;
	}
	public double[] podajWagiWyjscia()
	{
		return wagiWyjscie;
	}
	
	public int ewoluuj(double zad_sigma)
	{
		double[][] y_wagi;
		double[] y_wagiWyjscie;
		double fi;
		//double nx=0;
		double ny = 0;
		y_wagi = new double [m][n+1];
		y_wagiWyjscie = new double[m+1];
 
		while (true)
		{
			pretSymulacyjny.siecNeuronowa.pokazWagi();
			
			for (int licznikKrokow = 0; licznikKrokow < kroki; ++licznikKrokow )
			{
				
				Random gen = new Random();
				
				for (int i = 0; i<m ; ++i)
					for (int j = 0; j<n+1 ; ++j)			
						y_wagi[i][j]=wagi[i][j]+ sigma * gen.nextGaussian() ;
				for (int j = 0; j<m+1 ; ++j)
					y_wagiWyjscie[j]=wagiWyjscie[j] + sigma *  gen.nextGaussian();
				
				// asumming: Pret subobject of Modul_ewolucyjny; SiecNeuronowaPretu subobject of Pret
				
				if (pretSymulacyjny.symuluj(wagi,wagiWyjscie) > testowyPretSymulacyjny.symuluj(y_wagi,y_wagiWyjscie) )
				{
					++ny;
					pretSymulacyjny.siecNeuronowa.przypiszWagi(y_wagi,y_wagiWyjscie);
					przypiszWagi(y_wagi, y_wagiWyjscie);
				}
			}
			fi = ny/kroki;
			if (fi < (1/5))
			{
				sigma = c1 * sigma;
				//System.out.println("fi < 1/5");
			}
			else if(fi > (1/5))
			{
				sigma = c2 * sigma;
				//System.out.println("fi > 1/5");
			}
			
			if (sigma < zad_sigma) return 0; //Success
			
			ny = 0;
			System.out.println("\n\n\nSIGMA: " + sigma + "\n\n\n");
		}
	}	
}
