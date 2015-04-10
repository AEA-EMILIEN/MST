package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import run.Algos;
import run.Coloration;

import exception.VertexNotFoundException;
import graph.Edge;
import graph.Graph;
import utils.Parser;

public class Main {

	public static void main(String[] args) throws VertexNotFoundException, FileNotFoundException, InterruptedException {
		//Graph g = new Parser().FileToGraph("data/1000x1000.gph");
		//System.out.println(g.toString());
		//Parser p = new Parser();
		//p.GraphToFile(g, "test.txt");
		Graph g = new Graph(10, (float)0.5, 100);
		System.out.println("graphe généré !");
		//Parser p = new Parser();
		//p.GraphToFile(g, "alea.txt");
		//System.out.println(g.toString());
		//Algos alg = new Algos();
		
		//g = (alg.runKruskal(g));
		
		Coloration col = new Coloration();
		
		HashMap<Integer, Integer> h = col.naif(g);
		System.out.println("Coloration");
		System.out.println(h.toString());
		
	//	p.GraphToFile(g, "testk.txt");
		
	}
}
