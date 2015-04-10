package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exception.VertexNotFoundException;


import graph.Graph;

public class Coloration {
	
	private ArrayList<Integer> listColor = new ArrayList<Integer>();
	private HashMap<Integer,Integer> colorVertex = new HashMap<Integer, Integer>();
	
	/**
	 * cool op tagada tsointsoin
	 * @param listN
	 * @return
	 */
	public int getColor(Set<Integer> listN) {
		int[] colors = new int[this.listColor.size()];
		int cpt = 0;
		// initialise à -1 le tableau de couleurs
		for (int i= 0; i < colors.length; i++)
			colors[i] = -1;
		// met à 1 les couleurs utilisées par les sommets
		for (int n : listN) 
			if (colorVertex.get(n)!=-1)
				colors[colorVertex.get(n)] = 1;
		// cherche la première couleur non utilisée
		while ( (colors[cpt] != -1) && (cpt < colors.length) )
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
			int color = getColor(listN);
			this.colorVertex.put(v, color);
		}
		
		return this.colorVertex;
	}
	
	public HashMap<Integer,Integer> runWelshPowell(Graph g) throws VertexNotFoundException
	{
		/** Classer les sommets par ordres decroissant des degres 
		 * marquer le sommet ac une couleur, et tous les sommets non adjacents, non marques
		 * 
		 */
		
		Set<Integer> VertexToBeColoredMaybe = new HashSet<Integer>(g.vertex.size()*2);
		
		//liste les couleurs utilisées jusqu'ici
		this.listColor = new ArrayList<Integer>();
		
		List<Integer> neighbour = new ArrayList<Integer>();
		
		int maxCouleur = -1;
		int couleurActuelle;
		
		int[] listDegre = g.listDegre();
		for(int i=0;i<listDegre.length;i++)
		{
			//on rencontre un sommet qu'on a deja colorier
			if(listDegre[i]==-1)
				continue;
			
			//je trouve sa couleur
			couleurActuelle = getColor(g.getVertex(listDegre[i]).keySet());
			//si on ajoute une couleur supplementaire 
			if(maxCouleur<couleurActuelle)
				maxCouleur=couleurActuelle;
			
			//on assigen la couleur pour le sommet i
			this.colorVertex.put(i,couleurActuelle);
			
			
			
			
			
			
		 
			
		}
		
		return null;
	}

	
	
	
}
