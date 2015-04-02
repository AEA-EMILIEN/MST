package utils;

import exception.VertexNotFoundException;
import graph.Edge;
import graph.Graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Parser {

	@SuppressWarnings("resource")
	public Graph FileToGraph (String path) {
		Graph graph = new Graph();
		try{
			int e = -1, v = -1, w = -1 ;
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			String[] tab = null;
			while ((line=br.readLine())!=null) {
				System.out.println(line);
				tab = line.split(" ");
				if (tab.length%2 == 0) 
					throw new Exception("incomplet file"); // Create the exception
				System.out.println(tab[0]);
				v = Integer.parseInt(tab[0]);
				graph.addVertex(v);
				for (int i = 1; i < tab.length; i+=2) {
					e = Integer.parseInt(tab[i]);
					w = Integer.parseInt(tab[i+1]);
					System.out.println(tab[i] + " "+ tab[i+1]);
					graph.addEdge(v, e, w);
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return graph;
	}

	public void GraphToFile (Graph graph,String file) throws FileNotFoundException, VertexNotFoundException {
		final OutputStream os = new FileOutputStream("data/" + file);
		final PrintStream printStream = new PrintStream(os);
		String edg ="", buf = "";
		Edge edge = null ;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int v : graph.vertex.keySet()) {
			edg = "";
			for (int e : graph.getVertex(v).keySet()) {
				edge = new Edge(v,e,graph.getVertex(v).get(e));
				if (! edges.contains(edge)) {
					edges.add(edge);
					edg += e + " " + graph.getVertex(v).get(e) + " ";
				}
			}
			if (edg.length() > 0) {
				buf = v + " "+ edg + "\n";
				printStream.print(buf);
			}
		}
		printStream.close();
	}
}
