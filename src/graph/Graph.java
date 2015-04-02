package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import exception.VertexNotFoundException;

public class Graph {

	public HashMap<Integer, HashMap<Integer, Integer>> vertex;
	
	public Graph() {
		this.vertex = new HashMap<Integer, HashMap<Integer,Integer>>();
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
				edge = new Edge(v,e,this.getVertex(v).get(e));
				if (! edges.contains(edge)) {
					edges.add(edge);
					System.out.println(edge.toString());
				}
			}
		}
		return edges;
	}
	
	public String toString() {
		String str = "" ;
		for (int v : this.vertex.keySet()) {
			str += "V_" + v + " : ";
			for (int e : this.vertex.get(v).keySet()) {
				str += "(" + e + " - " + this.vertex.get(v).get(e) + ") ; " ;
			}
			str += "\n" ;
		}
		
		return str ;
	}

}
