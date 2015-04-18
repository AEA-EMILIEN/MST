package run;

import exception.VertexNotFoundException;
import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Naif extends Coloration {

	/**
	 * Méthode naïve de coloration de des graphes
	 * @param g le graphe à colorer, pré-requis les poids sont à -1
	 * @return g le graphe coloré
	 * @throws VertexNotFoundException 
	 */
	public HashMap<Integer, Integer> naif(Graph g) throws VertexNotFoundException {
		this.listColor = new ArrayList<Integer>();
		int nbVertex = g.vertex.size();
		initColorVertex(nbVertex);
		
		for (int v = 0; v < nbVertex; v++) {
			Set<Integer> listN = g.getVertex(v).keySet();
			int color = getColor(listN);
			this.colorVertex.put(v, color);
		}
		
		return this.colorVertex;
	}


}
