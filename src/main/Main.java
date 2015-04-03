package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import run.Algos;

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
		Graph g = new Graph(1000, (float)1.0, 100);
		//System.out.println("graphe généré !");
		//Parser p = new Parser();
		//p.GraphToFile(g, "alea.txt");
		//System.out.println(g.toString());
		Algos alg = new Algos();
		
		g = (alg.runKruskal(g));
		
	//	p.GraphToFile(g, "testk.txt");
		
	}
}
