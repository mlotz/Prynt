
public class Modul_ewolucyjny {
	
	double[][] wagi;
	double[] wagi_out;
	int n,m;
	int kroki;
	double c1,c2,sigma ;
	long evolutionTimeLimit; //seconds
	Pret pretSymulacyjny;
	
	public Modul_ewolucyjny(int iloscArgumentow, int iloscNeuronow)
	{
		wagi = new double [iloscNeuronow][iloscArgumentow+1];
		wagi_out = new double[iloscNeuronow+1];
		n= iloscArgumentow;
		m= iloscNeuronow;
		//parametry evo , roboczo, potem arg konstrukotra?
		kroki = 10;
		c1 = 0.82;
		c2 = 1.2;
		evolutionTimeLimit = 2; //seconds
		sigma = 1.0; // ??? inne? dane ? losowe ?
		
		pretSymulacyjny = new Pret(iloscArgumentow, iloscNeuronow);
	}
	
	
	public void losujWagi()
	{
		for (int i = 0; i<m ; ++i)
			for (int j = 0; j<n+1 ; ++j)
				wagi[i][j]=Math.random() ;
		for (int j = 0; j<m+1 ; ++j)
			wagi_out[j]=Math.random();
	}
	
	private void przypiszWagi(double[][] waga , double[] waga_out)
	{
		for (int i = 0; i<m ; ++i)
			for (int j = 0; j<n+1 ; ++j)
				wagi[i][j]=waga[i][j] ;
		for (int j = 0; j<m+1 ; ++j)
			wagi_out[j]=waga_out[j];
	}
	
	public double[][] podajWagi()
	{
		return wagi;
	}
	public double[] podajWagiOut()
	{
		return wagi_out;
	}
	
	public int ewoluuj(double zad_sigma)
	{
		double[][] y_wagi;
		double[] y_wagi_out;
		double fi;
		//double nx=0;
		double ny=0;
		y_wagi = new double [m][n+1];
		y_wagi_out = new double[m+1];
		
		long start = System.currentTimeMillis();
		long end = start + (evolutionTimeLimit)*1000; // 60 seconds * 1000 ms/sec
		while (System.currentTimeMillis() < end)
		{
		
			for (int kroki_counter=0; kroki_counter<kroki; ++kroki_counter )
			{
				
				for (int i = 0; i<m ; ++i)
					for (int j = 0; j<n+1 ; ++j)
						y_wagi[i][j]=wagi[i][j]+ sigma * Math.random() * 2 - 1 ;
				for (int j = 0; j<m+1 ; ++j)
					y_wagi_out[j]=wagi_out[j] + sigma *  Math.random() * 2 - 1;
				
				// asumming: Pret subobject of Modul_ewolucyjny; SiecNeuronowaPretu subobject of Pret
				
				//if (Pret.symuluj(n, m, wagi,wagi_out)>Pret.symuluj(y_wagi,y_wagi_out))
				if (true)
				{
					//x
					
				}
				else
				{
					//y
					++ny;
					przypiszWagi(y_wagi,y_wagi_out);
				}
			}
			fi = ny/kroki;
			if (fi<(1/5))
			{
			sigma = c1*sigma;
			}
			else if(fi>(1/5))
			{
			sigma = c2 * sigma;
			}
			else
			{
			//sigma = sigma;
			}
			
			if (sigma < zad_sigma) return 0; //Success
		}
		return 1; //exceeded time limit;
	}
	
}
