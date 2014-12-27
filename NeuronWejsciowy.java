import java.util.Arrays;

import Interfejs.*;

public class NeuronWejsciowy implements Neuron
{
	private double[] waga;
	private int n;
	
	public NeuronWejsciowy(int iloscWejsc)
	{
		n = iloscWejsc;
		waga = new double[n+1];
	}
	
	@Override
	public void przypiszWagi(double[] noweWagi) 
	{
		waga = Arrays.copyOf(noweWagi, n);
	}

	@Override
	public void pokazWagi() 
	{
		for(int i = 0; i < n+1; ++i)
			System.out.print(waga[i] + " ");
		System.out.println();
	}

	@Override
	public double obliczWyjscie(double[] wejscie) 
	{
		double suma = 0, wyjscie;
		
		for(int i = 0; i < wejscie.length; ++i)
		{
			suma += wejscie[i] * waga[i];
		}
		
		suma += waga[n];
		wyjscie = Math.exp(suma) / (1 + Math.exp(suma));
		
		return wyjscie;
	}
	
}