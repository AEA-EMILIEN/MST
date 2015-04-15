package graph;

import java.util.Comparator;

public class Vertex implements Comparator<Vertex>  {

	
	public int id;
	public int weight;
	public int pere;
	
	public Vertex(int id, int val)
	{
		this.id=id;
		this.weight=val;
		this.pere=-1; 
	}
	
	@Override
	public int compare(Vertex v1, Vertex v2)
	{
		return Integer.compare(v1.id,v2.id);
	}

	public String toString()
	{
		return "("+id+","+weight+","+pere+")";
	}
	

}
