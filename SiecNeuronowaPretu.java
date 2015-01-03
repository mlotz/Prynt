import Interfejs.*;

public class SiecNeuronowaPretu implements SiecNeuronowa
{
	private int n, m;	// n - ilosc argumentow funkcji - wejsc neuronu, m - ilosc neuronow w pierwszej warstwie
	private NeuronWejsciowy[] neuron;
	private double[] wagaNeuronuWyjsciowego;
	
	public SiecNeuronowaPretu(int iloscArgumentow, int iloscNeuronow)
	{
		n = iloscArgumentow;
		m = iloscNeuronow;
		neuron = new NeuronWejsciowy[m];
		for(int i = 0; i < m; ++i)
		{
			neuron[i] = new NeuronWejsciowy(n);
		}
		wagaNeuronuWyjsciowego = new double[m+1];
	}

	//@Override
	public void przypiszWagi(double[][] waga , double[] wagaWyjsciowego) 
	{
		for(int i = 0; i < m; ++i)
		{
			neuron[i].przypiszWagi(waga[i]);
		}
		for (int i=0 ; i< m+1 ; ++i)
		{
			wagaNeuronuWyjsciowego[i] = wagaWyjsciowego [i];
		}
	}

	//@Override
	public void pokazWagi() 
	{
		for(int i = 0; i < m; ++i)
		{
			System.out.println("Wagi neuronu nr " + (i+1));
			neuron[i].pokazWagi();
		}
		System.out.println("Wagi neuronu wyjœciowego");
		for(int i = 0; i < m+1; ++i)
		{
			System.out.print(wagaNeuronuWyjsciowego[i] + " ");
		}
	}

	//@Override
	public double obliczWartosc(double[] wejscie) 
	{
		double suma = 0;
		
		for(int i = 0; i < m; ++i)
		{
			suma += neuron[i].obliczWyjscie(wejscie) * wagaNeuronuWyjsciowego[i];
		}
		
		suma += wagaNeuronuWyjsciowego[m];
		
		return suma;		// poniewaz neuron wyjsciowy jest liniowy, zwracamy tylko sume
	}
	
}