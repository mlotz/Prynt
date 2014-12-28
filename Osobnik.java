
public class Osobnik 
{
	double[] wagi;
	
	public Osobnik(int n)
	{
		wagi = new double[n];
	}
	
	public Osobnik(double[] noweWagi)
	{
		wagi = noweWagi;
	}
	
	public int rozmiar()
	{
		return wagi.length;
	}
	
	public Osobnik reprodukuj()
	{
		Osobnik nowyOsobnik;
		
		// nalezy zrobic generator liczb o rozkladzie normalnym - transformacja Boxa-Mullera, szczegolnie eng.wiki
		// nastepnie trzeba utworzyc osobnika z zadana zmutowana tablica wag
		
		return nowyOsobnik;
	}
}
