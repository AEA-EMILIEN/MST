package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import exception.VertexNotFoundException;


import graph.Graph;

public class Coloration {
	
	private ArrayList<Integer> listColor = new ArrayList<Integer>();
	private HashMap<Integer,Integer> colorVertex = new HashMap<Integer, Integer>();
	
	/**
	 * Donne la plus petite couleur du sommet en fonction de la couleur de ses voisins, l'ajoute si elle n'existe pas
	 * @param listN les voisins
	 * @return la couleur
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
		while ( (cpt < colors.length) && (colors[cpt] != -1) )
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
	 * Initialise la couleur des sommets
	 * @param numberVertex la map de stockage des couleurs
	 */
	public void initColorVertex(int numberVertex) {
		this.colorVertex = new HashMap<Integer, Integer>();
		for (int v = 0; v < numberVertex; v++) 
			this.colorVertex.put(v, -1);
		return;
	}
	
	/**
	 * Méthode naïve de coloration de des graphes
	 * @param g le graphe à colorer, pré-requis les poids sont à -1
	 * @return g le graphe coloré
	 * @throws VertexNotFoundException 
	 */
	public HashMap<Integer, Integer> naif(Graph g) throws VertexNotFoundException {
		this.listColor = new ArrayList<Integer>();
		initColorVertex(g.vertex.size());
		
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
		
		initColorVertex(g.vertex.size());
		List<Integer> VertexToBeColoredMaybe = new ArrayList<Integer>(); 
		
		//liste les couleurs utilisées jusqu'ici
		this.listColor = new ArrayList<Integer>();
		
		
		int couleurActuelle = 0 ;
		
		int[] listDegre = g.listDegre();
		for(int i=0;i<listDegre.length;i++)
		{
			//on rencontre un sommet qu'on a deja colorier
			if(listDegre[i]==-1)
				continue;
			
			//je trouve sa couleur
			//couleurActuelle = getColor(g.getVertex(listDegre[i]).keySet());
			
			//on assigen la couleur pour le sommet i
			this.colorVertex.put(listDegre[i],couleurActuelle);
			
			
			//on cree un ensemble des sommets pouvant eventuellement prendre cette couleur aussi
			for(int j=i+1;j<listDegre.length;j++)
			{
				if(listDegre[j]!=-1 && !g.getVertex(listDegre[i]).containsKey(listDegre[j]))
					VertexToBeColoredMaybe.add(listDegre[j]);
			}
			
			
			//on assigne cette couleur a tous les sommets non adjacent a cette couleur
			while(!VertexToBeColoredMaybe.isEmpty())
			{		
				int sommet = VertexToBeColoredMaybe.get(0);
				this.colorVertex.put(sommet, couleurActuelle);
				VertexToBeColoredMaybe.remove(0);
				for(int k=i+1;k<listDegre.length;k++)
					if (listDegre[k]==sommet)
						listDegre[k] = -1;
				
				for(int k : g.vertex.get(sommet).keySet())
					if(VertexToBeColoredMaybe.contains(k))
						VertexToBeColoredMaybe.remove(VertexToBeColoredMaybe.indexOf(k));
			
			}
			couleurActuelle++;
		}
		
		return colorVertex;
	}

	
	
	
}
