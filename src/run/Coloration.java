package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exception.VertexNotFoundException;

import graph.Graph;

public class Coloration {
	
	private ArrayList<Integer> listColor = new ArrayList<Integer>();
	private HashMap<Integer,Integer> colorVertex = new HashMap<Integer, Integer>();
	
	public int getColor(int v, Set<Integer> listN) {
		int[] colors = new int[this.listColor.size()];
		int cpt = 0;
		// initialise à -1 le tableau de couleurs
		for (int i= 0; i < colors.length; i++)
			colors[i] = -1;
		// met à 1 les couleurs utilisées par les sommets
		for (int n : listN) 
			colors[colorVertex.get(n)] = 1;
		// cherche la première couleur non utilisée
		while ( (colors[cpt] == -1) && (cpt < colors.length) )
			cpt++;
		// si elle existe la retourne
		if (cpt < colors.length)
			return cpt;
		// sinon en ajoute une nouvelle
		else {
			listColor.add(cpt);
			return cpt;
		}
	}
	
	/**
	 * Méthode naïve de coloration de des graphes
	 * @param g le graphe à colorer, pré-requis les poids sont à -1
	 * @return g le graphe coloré
	 * @throws VertexNotFoundException 
	 */
	public HashMap<Integer, Integer> naif(Graph g) throws VertexNotFoundException {
		this.listColor = new ArrayList<Integer>();
		this.colorVertex = new HashMap<Integer, Integer>();
		
		for (int v = 0; v < g.vertex.size(); v++) {
			Set<Integer> listN = g.getVertex(v).keySet();
			int color = getColor(v, listN);
			this.colorVertex.put(v, color);
		}
		
		return this.colorVertex;
	}
	
	public Graph runWelshPowell(Graph g)
	{
		/** Classer les sommets par ordres decroissant des degres 
		 * marquer le sommet ac une couleur, et tous les sommets non adjacents, non marques
		 * 
		 */
		
		List<Integer> neighbour = new ArrayList<Integer>();
		Map<Integer,Integer> color = new HashMap<Integer,Integer>();
		
		int[] listDegre = g.listDegre();
		for(int i=0;i<listDegre.length;i++)
		{
			int newColor = -1;
			
			//si ce sommet a des voisins colores
			for(int key :g.vertex.get(listDegre[i]).keySet())
			{
				if(g.vertex.get(listDegre[i]).containsKey(key))
					color.get(key);
			}
		}
		
		return null;
	}

}
