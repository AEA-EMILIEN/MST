package run;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;



import set.DisjointSets;
import graph.Edge;
import graph.Graph;

public class Algos implements MSTTools {

	
	
	@Override
	public Graph runPrim(Graph g) {
		/*
		 
		
		Random rand = new Random();
		ArrayList<Integer> q = (ArrayList<Integer>) g.getListVertex();
		int[] key = new int[q.size()];
		int s = rand.nextInt(q.size()); //choix du sommet de depart aleatoirement
		
		initKey(key, s);
		
		while(!q.isEmpty())
		{
			int u = extract_min(q);
			
			int[] adj = adjacent(g,u);
			
			for(int i=0;i<adj.length;i++)
			{
				int v = adj[i];
				if(q.contains(v) && c(u,v) < key[v])
				{
					key[v] = c(u,v);
					pere(v) = u;
				}
			}
			
			
		}
		
		*/
		return null;
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
		System.out.println("debut");
		List<Edge> a = new ArrayList<Edge>(1000000);
		List<Integer> l = g.getListVertex();
		System.out.println("1");
		Collections.sort(l);
		System.out.println("2");
		DisjointSets ds = new DisjointSets(l.size());
		System.out.println("3");
		Iterator<Edge> iterator = g.getSortedEdgeIterator();
		Edge e = null;
		System.out.println("4");
		
		int start=0;
		int end=0;
		
		System.out.println("22");
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
		System.out.println("miaou");
		g.constructGraph(l, a);
		return g;
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
