package main;

import java.io.FileNotFoundException;
import run.Timer;
import exception.VertexNotFoundException;

public class Main {

	public static void main(String[] args) throws VertexNotFoundException, FileNotFoundException, InterruptedException {

		//Parser p = new Parser();
		//p.GraphToFile(g, "data/test.txt");
		//Graph g = new Graph(3000, (float)0.9, 100000);

		
		//System.out.println("graphe généré !");
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
		
		/*
		Graph g = new Graph(10, (float)0.7, 10);
		System.out.println(g.toString());
		Coloration col = new Naif();
		HashMap<Integer, Integer> h = ((Naif) col).naif(g);
		System.out.println("Coloration Naif");
		System.out.println(h.toString());
		
		col = new Dsatur();
		h = ((Dsatur) col).dsatur(g);
		System.out.println("Coloration Dsature");
		System.out.println(h.toString());
		
		col = new WelshPowell();
		h = ((WelshPowell) col).runWelshPowell(g);
		System.out.println("Coloration WelshPowel");
		System.out.println(h.toString());
	//	p.GraphToFile(g, "testk.txt");
		*//*
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
		//System.out.println(g.toString());
		//System.out.println(alg.meanTimeKruskal(g));
		//System.out.println(alg.meanTimePrim(g));
		 
		 //------------------------------------------------
		
		Timer t = new Timer();
		t.timeAndNumberColoration();
		
		
		
	}
}