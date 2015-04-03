package graph;

import java.util.Comparator;

public class Degre implements Comparator<Degre> {

	
	
	public int vertex;
	public int degree;
	
	public Degre()
	{
		;
	}
	
	public Degre(int i,int j)
	{
		this.vertex = i;
		this.degree = j;
		
	}

	
	@Override
	public int compare(Degre e0, Degre e1) {
		
		return Integer.compare(e0.degree,e1.degree);
	}
	
}
