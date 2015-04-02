package main;

import java.io.FileNotFoundException;

import exception.VertexNotFoundException;
import graph.Graph;
import utils.Parser;

public class Main {

	public static void main(String[] args) throws VertexNotFoundException, FileNotFoundException {
		//Graph g = new Parser().FileToGraph("data/graph_demo.txt");
		//System.out.println(g.toString());
		//Parser p = new Parser();
		//p.GraphToFile(g, "test.txt");
		Graph g = new Graph(5, (float)0.5, 100);
		Parser p = new Parser();
		p.GraphToFile(g, "alea.txt");
	}
}
