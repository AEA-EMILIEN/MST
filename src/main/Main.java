package main;

import java.io.FileNotFoundException;
import java.util.HashMap;

import run.Coloration;
import run.Dsatur;
import run.Naif;
import run.WelshPowell;
import utils.Parser;
import exception.VertexNotFoundException;
import graph.Graph;

public class Main {
	
	/**
	 * crée un graphe depuis un fichier ou aléatoirement et lance les 3 algos de coloriage
	 * @param args path ou le nombre sommet, probabilité et taille des poids
	 * @throws VertexNotFoundException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws VertexNotFoundException, FileNotFoundException, InterruptedException {

		Graph g = null;
		if (args.length == 1) {
			Parser p = new Parser();
			g = p.FileToGraph(args[0]);
		} else if (args.length == 3) {
			g = new Graph(Integer.parseInt(args[0]), Float.parseFloat(args[1]), Integer.parseInt(args[2]));
		}
		else 
			System.out.println("usage : main [-path] or [-nbVertex -proba -size] ");
		System.out.println("graphe généré !");
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
		
		// ----------------------Chargement d'un graphe depuis un fichier-----------------------
		//Parser p = new Parser();
		//p.FileToGraph("data/test.txt");
		
		// ---------------------- Génération de graphe aléatoire --------------------------------
		//Graph g = new Graph(3000, (float)0.9, 100000);

		//----------------------- Stocke les données du graphe dans un fichier -------------------
		//Parser p = new Parser();
		//p.GraphToFile(g, "alea.txt");
		
		//---------------------- Imprime les données du graphe ----------------------------------
		// sommet : (voisin1 - poids1); (.... 
		//System.out.println(g.toString());
		
		/*
		------------------------------------------------------------------------------------
		prim , kruskal et dsatur
		-------------------------------------------------------------------------------------
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
		p.GraphToFile(g, "testk.txt");
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
		
		//Timer t = new Timer();
		//t.timeAndNumberColoration();
		
		
		
	}
}