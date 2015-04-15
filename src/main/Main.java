package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import run.Algos;
import run.Coloration;
import exception.VertexNotFoundException;
import graph.Vertex;
import graph.Graph;
import heap.Heap;
import utils.Parser;

public class Main {

	public static void main(String[] args) throws VertexNotFoundException, FileNotFoundException, InterruptedException {
		//Graph g = new Parser().FileToGraph("data/1000x1000.gph");
		//System.out.println(g.toString());
		//Parser p = new Parser();
		//p.GraphToFile(g, "test.txt");
		Graph g = new Graph(1000, (float)0.5, 10000);
		//System.out.println("graphe gÃ©nÃ©rÃ© !");
		//Parser p = new Parser();
		//p.GraphToFile(g, "alea.txt");
		//System.out.println(g.toString());
		//Algos alg = new Algos();
		
		//g = (alg.runKruskal(g));
		/*
		Coloration col = new Coloration();
		
		HashMap<Integer, Integer> h = col.naif(g);
		System.out.println("Coloration");
		System.out.println(h.toString());
		HashMap<Integer, Integer> h1 = col.runWelshPowell(g);
		System.out.println("Coloration");
		System.out.println(h1.toString());
		*/
	//	p.GraphToFile(g, "testk.txt");
		/*
		------------------------------------------------------------------------------------
		prim et kruskal
		-------------------------------------------------------------------------------------
		*/
		/*
		Algos alg = new Algos();
		//System.out.println(g.toString());
		
		//Graph g1 = alg.runKruskal(g);
		//System.out.println("kruskal:"+g1.getWeightTotal());
		//System.out.println(g1.toString());
		
		//g1 = alg.runPrim(g);
		System.out.println("prim:"+g1.getWeightTotal());
		//System.out.println(g1.toString());
		-------------------------------------------------------------------------------------
		*/
		/*
		 -------------------------------------------------------------------------------------
		 time prim and kruskal
		 -----------------------------------------------------------------------------------
		 */
		Algos alg = new Algos();
		System.out.println(alg.meanTimeKruskal(g));
		System.out.println(alg.meanTimePrimVariableD(g, 10));
		 
		 
		
		
		
	}
}