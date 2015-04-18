package run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.VertexNotFoundException;
import graph.Graph;

public class Coloration {
	
	private ArrayList<Integer> listColor = new ArrayList<Integer>();
	// la liste des couleurs utilisées
	private HashMap<Integer,Integer> colorVertex = new HashMap<Integer, Integer>();
	// map : associe à chaque sommet une couleur
	
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
		int nbVertex = g.vertex.size();
		initColorVertex(nbVertex);
		
		for (int v = 0; v < nbVertex; v++) {
			Set<Integer> listN = g.getVertex(v).keySet();
			int color = getColor(listN);
			this.colorVertex.put(v, color);
		}
		
		return this.colorVertex;
	}
	
	
public HashMap<Integer,Integer> dsatur (Graph g) throws VertexNotFoundException {
		/// PHASE D'INITIALISATION
		this.listColor = new ArrayList<Integer>();
		int nbVertex = g.vertex.size();
		initColorVertex(nbVertex);		
		
		// hashMap sommet -> set de couleurs des voisins
		HashMap<Integer,Set<Integer>> neigthColor = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < nbVertex; i++) {
			neigthColor.put(i, new HashSet<Integer>());
		}
		// liste de sommets rangés dans l'ordre décroissant pour l'instant 
		int[] vertexDD = g.listDegre();
		
		// hashMap sommet -> degré de saturation
		HashMap<Integer,Integer> vertexDS = new HashMap<Integer, Integer>() ;
		for (int v = 0; v < nbVertex; v++) 
			this.colorVertex.put(v, -1);
		
		// compteur de sommets restant à colorer
		int cptToColor = nbVertex;
		
		// liste des sommets de degré de saturation maximal
		ArrayList<Integer> maxSatVertex = new ArrayList<Integer>();
		
		// degré de saturation maximal
		int maxSat = -1;
		
		// couleur courante
		int color = 0;
		
		//relève le sommet avec le plus grand degré, le colore et met à jour les données
		int vertex = vertexDD[0];
		
		// vertexDD donne le degré des sommets selon leur indice maintenant
		vertexDD = transform(vertexDD);
		
		this.colorVertex.put(vertex, color);
		this.listColor.add(color);
		cptToColor--;
		vertexDD[vertex] = -1; //pour ne plus être comparé
		
		// mise à jour des degré de saturation des voisins
		majDS(g.getVertex(vertex).keySet(), vertexDS, neigthColor, color, maxSatVertex, maxSat);
		
		while (cptToColor > 0) {
			vertex = chooseMaxSat(maxSatVertex, vertexDD);
			Set<Integer> listN = g.getVertex(vertex).keySet();
			color = color(vertex, listN, neigthColor);
			cptToColor--;
			vertexDD[vertex] = -1;
			majDS(listN, vertexDS, neigthColor, color, maxSatVertex, maxSat);
		}
		
		return this.colorVertex;
	}

	/**
	 * Colore le sommet selon ses voisins avec la plus petite couleur et met à jour les données
	 * @param vertex le sommet à colorier
	 * @param listN la liste de ses voisins
	 * @param neigthColor tableau de couleurs voisines de chaque sommet
	 * @return la couleur utilisée
	 */
	private int color(int vertex, Set<Integer> listN, HashMap<Integer, Set<Integer>> neigthColor) {
		int color = getColor(listN);
		this.colorVertex.put(vertex, color);
		for (int n: listN) 
			neigthColor.get(n).add(color);
		return color;
	}
	
	/**
	 * Cherche le sommet avec le degré de saturation le plus élevé
	 * @param maxSatVertex
	 * @param vertexDD
	 * @return le sommet avec le plus grand degré de saturation
	 */
	private int chooseMaxSat(ArrayList<Integer> maxSatVertex, int[] vertexDD) {
		if (maxSatVertex.size() == 0)
			return maxSatVertex.get(0);
		else 
			return serchMaxDegre(maxSatVertex, vertexDD);
	}

	/**
	 * Dans le cas où plusieurs sommets ont un degré de saturation max, cherche celui qui à le degré max
	 * @param maxSatVertex
	 * @param vertexDD
	 * @return le sommet de degré et degré de saturation maximaux
	 */
	private int serchMaxDegre(ArrayList<Integer> maxSatVertex, int[] vertexDD) {
		int max = 0;
		int indice = -1;
		for (int vertex : maxSatVertex) {
			if (vertexDD[vertex] > max) {
				max = vertexDD[vertex];
				indice = vertex;
			}
		}
		return indice;
	}

	/**
	 * Incrémente le degré
	 * @param g
	 * @param vertex
	 * @param vertexDS
	 * @param maxSat 
	 * @param maxSatVertex 
	 * @return
	 * @throws VertexNotFoundException
	 */
	private void majDS(Set<Integer> listN, HashMap<Integer, Integer> vertexDS, HashMap<Integer,Set<Integer>> neigthColor, int color, ArrayList<Integer> maxSatVertex, int maxSat) throws VertexNotFoundException {
		int tmp = 0;
		for (int n : listN) { // pour chaque voisin ...
			if ( ! neigthColor.get(n).contains(color)) { // si aucun de ses voisins ne contient la couleur ajoutée
				tmp = vertexDS.get(n) + 1; // incrémente son DS
				vertexDS.put(n, tmp);
				
				if (tmp > maxSat) {
					maxSat = tmp;
					maxSatVertex = new ArrayList<Integer>();
					maxSatVertex.add(n);
				}
				else if (tmp == maxSat) 
					maxSatVertex.add(n);
			}
		}
	return;
}

	/**
	 * range le tableau l'indice du tableau étant le numéro du sommet, et sa valeur son degré
	 * @param vertexDD le tableau d'entrée avec les sommets rangé par ordre décroissant de degré
	 * @return le tableau modifié
	 */
	private int[] transform(int[] vertexDD) {
		int[] newDD = new int[vertexDD.length];
		int i = 0;
		for (int v : vertexDD) {
			newDD[i] = v;
			i++;
		}
	return newDD;
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
