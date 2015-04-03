package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import exception.VertexNotFoundException;

public class Graph {

	public HashMap<Integer, HashMap<Integer, Integer>> vertex;
	
	public Graph() {
		this.vertex = new HashMap<Integer, HashMap<Integer,Integer>>();
	}
	
	/**
	 * Graphe d'Erdos
	 * @param n
	 * @param p
	 * @throws IllegalArgumentException
	 * @throws VertexNotFoundException 
	 */
	public Graph(int n, float p, int N) throws IllegalArgumentException, VertexNotFoundException{
		
		if(p<0.000 || p>1.000 || n<0)
			throw new IllegalArgumentException();
		
		this.vertex = new HashMap<Integer, HashMap<Integer,Integer>>();
		Random rand = new Random();
		boolean[][] matrix = matriceAlea(n, p, rand);
		for (int i = 0; i < n; i++) {
			this.addVertex(i);
			for (int j = 0; j < i ; j++) {
				if (matrix[i][j]) {
					this.addEdge(i, j, rand.nextInt(N));
				}
					
			}
		}
	}
	
	private boolean[][] matriceAlea(int n,  float p, Random rand) {
		boolean[][] matrix = new boolean[n][n];
		float r ;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j<i; j++) {
				r = rand.nextFloat();
				if (r <= p) 
					matrix[i][j] = true; 
				else
					matrix[i][j] = false;
			}
		}
		return matrix;
	}
	public HashMap<Integer, Integer> getVertex(int v) throws VertexNotFoundException {
		if (this.vertex.containsKey(v)) 
			return this.vertex.get(v);
		else
			throw new VertexNotFoundException();
	}
	
	public void addVertex(int v) {
		if (! this.vertex.containsKey(v))
			this.vertex.put(v, new HashMap<Integer, Integer>());
	}

	public void addEdge(int v, int e, int w) throws VertexNotFoundException {
		
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
		
		h = this.getVertex(v);
		if (!h.containsKey(e)) {
			this.vertex.get(v).put(e, w);
		}
	
		this.addVertex(e);
		
		h = this.getVertex(e);
		if (!h.containsKey(v)) {
		this.vertex.get(e).put(v, w);
		}
	}

	
	public List<Edge> getEdges() throws VertexNotFoundException {
		Edge edge ;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int v : this.vertex.keySet()) {
			
			for (int e : this.getVertex(v).keySet()) {
				
				if (e > v) {
					edge = new Edge(v,e,this.getVertex(v).get(e));
					edges.add(edge);
				}
				
			}
		}
		return edges;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder() ;
		for (int v : this.vertex.keySet()) {
			str.append("V_");
			str.append(v);
			str.append(" : ");
			for (int e : this.vertex.get(v).keySet()) {
				str.append("(");
				str.append(e);
				str.append(" - ");
				str.append(this.vertex.get(v).get(e));
				str.append(") ; ") ;
			}
			str.append("\n") ;
		}
		
		return str.toString() ;
	}

	
	
	/**
	 * renvoi une liste des sommets, trié par ordre decroissant de leurs degre
	 * 
	 */
	public int[] listDegre()
	{
		Set<Integer> s = this.vertex.keySet();
		Degre[] tab = new Degre[s.size()];
		int j=0;
		for (Integer i : s)
		{
			tab[j] = new Degre(i,this.vertex.get(i).size());
			j++;
		}
		
		Arrays.sort(tab,Collections.reverseOrder(new Degre()));
		int[] res = new int[tab.length];
		for(int i=0;i<tab.length;i++)
		{
			res[i] = tab[i].vertex;
		}
			
		return res;
	}
	
	public List<Integer> getListVertex()
	{
		List<Integer> res = new ArrayList<Integer>();
		for(int i : this.vertex.keySet())
		{
			res.add(i);
		}
		
		return res;
	}

	public Iterator<Edge> getSortedEdgeIterator(){
		List<Edge> l = null;
		try {
			l = this.getEdges();
			System.out.println("ya sousi la");
			Collections.sort(l, new EdgeComparator());
			System.out.println("yésousipasla");
			return l.iterator();
		} catch (VertexNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void constructGraph(List<Integer> l, List<Edge> a) {
		
		this.vertex = new HashMap<Integer, HashMap<Integer,Integer>>();
		Edge e = null;
		// on ajoute les sommets
		for (int i = 0; i < l.size(); i++) {
			this.addVertex(l.get(i));
		}
		
		//on ajoute les aretes
		for (int j = 0; j < a.size() ; j++) {
			e=a.get(j);
			try {
				this.addEdge(e.start, e.end, e.weigth);
			} catch (VertexNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
	
}
