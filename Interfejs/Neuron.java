package Interfejs;

public interface Neuron 
{
	public void przypiszWagi(double[] waga  , double[] waga_out);
	public void pokazWagi();
	public double obliczWyjscie(double[] wejscie);
}