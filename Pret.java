
public class Pret 
{
	SiecNeuronowaPretu siecNeuronowa;
	public double[] alfy;
	public double ostatniWynik;
	
	public Pret(int ileWejsc, int ileNeuronow)
	{
		siecNeuronowa = new SiecNeuronowaPretu(ileWejsc, ileNeuronow);
		alfy = new double[1000];
	}
	
	public double symuluj(double[][] wagiWejsciowej, double[] wagiWyjsciowej)
	{
		double sredniKat;
		double alfa, omega, epsilon;	// wartosci kata, predkosci katowej oraz przyspieszenia katowego
		double dt, aktualnyCzas;	// okres probkowania oraz aktualny czas symulacji
		
		sredniKat = 0.0;
		alfa = 330;
		omega = 0;
		epsilon = 0;
		dt = 0.01;
		aktualnyCzas = 0.0;
		
		siecNeuronowa.przypiszWagi(wagiWejsciowej, wagiWyjsciowej);
		
		while(aktualnyCzas < 10)	
		{
			double[] dane = {alfa, omega};
			
			epsilon = 100 * Math.sin(Math.toRadians(alfa)) + siecNeuronowa.obliczWartosc(dane);
			
			alfa = alfa + dt * omega + 0.5 * dt * dt * epsilon;
			omega = omega + dt * epsilon;
			
			
			while(alfa > 360)
				alfa = alfa - 360;
			
			sredniKat += Math.abs(alfa);
			alfy[(int)(aktualnyCzas * 100)] = alfa;
			
			aktualnyCzas += dt;
		}
		
		sredniKat /= 1000;
		ostatniWynik = sredniKat;
		
		
		return sredniKat;
	}
	
}
