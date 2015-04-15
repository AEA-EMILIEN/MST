package run;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;





import java.util.Set;

import set.DisjointSets;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import heap.Heap;

public class Algos implements MSTTools {

	@Override
	public Graph runPrim(Graph g) {
		return runPrim_(g,4);
		
	}
	
	public Graph runPrim_(Graph g,int d) {
		/*
		1) Create a Min Heap of size V where V is the number of vertices
		in the given graph. Every node of min heap contains vertex number
		and key value of the vertex.
		2) Initialize Min Heap with first vertex as root (the key value assigned
				to first vertex is 0). The key value assigned to all other vertices
				is INF (infinite).
		3) While Min Heap is not empty, do following
		..a) Extract the min value node from Min Heap. Let the extracted vertex be u.
		..b) For every adjacent vertex v of u, check if v is in Min Heap (not yet 
				included in MST). If v is in Min Heap and its key value is more than 
				weight of u-v, then update the key value of v as weight of u-v.
		*/
		
		List<Integer> l = g.getListVertex();
		List<Edge> a = new ArrayList<Edge>();
		
		Set<Integer> s = ((HashMap<Integer, HashMap<Integer, Integer>>)(g.vertex.clone())).keySet();
		Set<Vertex> res = new HashSet<Vertex>();
		Heap h = new Heap(g.vertex.size(),d);
		h.build_heap_prim(l);
		
		Set<Integer> tmp= null;
		
		Vertex u = null;
		while(!s.isEmpty())
		{
			//on trouve le plus petit sommet
			u = h.extract_min();
			s.remove(u.id);
			res.add(u);
			
			if(g.vertex.containsKey(u.id))
			{
				for(int v : g.vertex.get(u.id).keySet())
				{
					if(s.contains(v) && g.vertex.get(u.id).get(v)<h.get(v).weight)
					{
						h.set(v,g.vertex.get(u.id).get(v),u.id);
					}
				}
				System.out.println(h.toString());
			}
		}
		a = constructArete(res);
		Graph g1 = new Graph();
		g1.constructGraph(l, a);
		
		return g1;
	}

	private List<Edge> constructArete(Set<Vertex> res) {
		List<Edge> a = new ArrayList<Edge>();
		
		for(Vertex v : res)
		{
			if(v.pere!=-1)
				a.add(new Edge(v.id, v.pere, v.weight));
		}
		return a;
	}

	private void initKey(int[] key, int s) {
		for(int i=0;i<key.length;i++)
			key[i]=-1;
		key[s]=0;
		
	}

	
	public Graph runKruskal(Graph g){
		/*KRUSKAL(G):
			1 A = ∅
			2 foreach v ∈ G.V:
			3   MAKE-SET(v)
			4 foreach (u, v) ordered by weight(u, v), increasing:
			5    if FIND-SET(u) ≠ FIND-SET(v):
			6       A = A ∪ {(u, v)}
			7       UNION(u, v)
			8 return A
			*/
		List<Edge> a = new ArrayList<Edge>(1000000);
		List<Integer> l = g.getListVertex();
		Collections.sort(l);
		DisjointSets ds = new DisjointSets(l.size());
		Iterator<Edge> iterator = g.getSortedEdgeIterator();
		Edge e = null;
		
		int start=0;
		int end=0;
		
		while(iterator.hasNext())
		{
			e = iterator.next();
			
			start = ds.find(e.start);
			end = ds.find(e.end);
			if (start != end)
			{
				a.add(e);
				ds.union(start, end);
			}
			
		}
		Graph g1=new Graph();
		g1.constructGraph(l, a);
		return g1;
	}

	public long timeKruskal(Graph g)
	{
		long startTime = System.nanoTime();
		runKruskal(g);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		return duration; 
	}
	
	public String meanTimeKruskal(Graph g)
	{
		int t=5;
		long mean=0;
		Graph g_test = null;
		for(int j=0;j<t;j++)
		{
			g_test = g;
			long toto = timeKruskal(g_test);
			mean=mean + toto;
			System.out.println(toto);
			System.out.println(mean);
		}
		mean/=t;
		
		return "kruskal time:"+mean+"ns, "+(mean/1000000)+"ms or "+mean/1000000000+"s"; 
	}
	
}
