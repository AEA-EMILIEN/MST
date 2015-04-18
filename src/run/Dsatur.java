package run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import exception.VertexNotFoundException;
import graph.Degre;
import graph.Graph;

public class Dsatur extends Coloration {
	
	// hashMap sommet -> set de couleurs des voisins
	private HashMap<Integer,Set<Integer>> neigthColor = new HashMap<Integer, Set<Integer>>();
	private int nbVertex;
	private int[] vertexDD;
	// hashMap sommet -> degré de saturation
	private int[] vertexDS;
	// liste des sommets de degré de saturation maximal
	private ArrayList<Integer> maxSatVertex = new ArrayList<Integer>();
	// compteur de sommets restant à colorer
	private int cptToColor;
	// degré de saturation maximal
	private int maxSat = -1;
	
public HashMap<Integer,Integer> dsatur (Graph g) throws VertexNotFoundException {
		/// PHASE D'INITIALISATION
		this.listColor = new ArrayList<Integer>();
		nbVertex = g.vertex.size();
		this.vertexDS = new int[nbVertex];
		initColorVertex(nbVertex);		
		cptToColor = nbVertex;
		
		for (int i = 0; i < nbVertex; i++) {
			neigthColor.put(i, new HashSet<Integer>());
		}
		// liste de sommets rangés dans l'ordre décroissant pour l'instant 
		vertexDD = g.listDegre();
		
		for (int v = 0; v < nbVertex; v++) 
			vertexDS[v] = 0;
		
		// couleur courante
		int color = 0;
		
		//relève le sommet avec le plus grand degré, le colore et met à jour les données
		int vertex = vertexDD[0];
		
		// vertexDD donne le degré des sommets selon leur indice maintenant
		vertexDD = getDegre(g);
		
		this.colorVertex.put(vertex, color);
		this.listColor.add(color);
		this.cptToColor--;
		this.vertexDD[vertex] = -1; //pour ne plus être comparé
		reloadMaxSat();
		
		// mise à jour des degré de saturation des voisins
		majDS(g.getVertex(vertex).keySet(), color);
		
		while (cptToColor > 0) {
			vertex = chooseMaxSat();
			majMaxSat(vertex);
			Set<Integer> listN = g.getVertex(vertex).keySet();
			color = color(vertex, listN);
			cptToColor--;
			vertexDD[vertex] = -1;
			majDS(listN, color);
		}
		
		return this.colorVertex;
	}
	
	/**
	 * Défait vertex de MaxSatVertex et le met à -1 dans vertexDS. Recalcule MaxSatVertex si c'était le seul
	 * @param vertex
	 */
	private void majMaxSat(int vertex) {
		if (maxSatVertex.size() == 1) {
			maxSatVertex.remove(maxSatVertex.indexOf(vertex));
			maxSat = -1 ;
			vertexDS[vertex] = -1;
			reloadMaxSat();
		}
		else if (maxSatVertex.size() > 1) {
			maxSatVertex.remove(maxSatVertex.indexOf(vertex));
			vertexDS[vertex] = -1;
		}
		else
			System.out.println(""+vertex + "inexistant dans maxSatVertex");
}

	/**
	 * Dans le cas où MaxSat est vide il faut rechercher le maximum de saturaion
	 */
	private void reloadMaxSat() {
		this.maxSat = 0;
		for ( int i = 0; i < nbVertex; i++) {
			if (vertexDS[i] > maxSat) {
				maxSatVertex = new ArrayList<Integer>();
				maxSatVertex.add(i);
				maxSat = vertexDS[i];
			}
			else if (vertexDS[i] == maxSat) 
				maxSatVertex.add(i);
		}
		return;
	}
		

	/**
	 * Renvoie le tableau des degrés des sommets, l'indice étant le numéro de sommet et la valeur étant le degré
	 * @param g
	 * @return
	 * @throws VertexNotFoundException
	 */
	private int[] getDegre(Graph g) throws VertexNotFoundException {
		int[] tab = new int[nbVertex];
		for (int i = 0; i < nbVertex; i++) 
			tab[i] = g.getVertex(i).size();
		return tab;
	}

	/**
	 * Colore le sommet selon ses voisins avec la plus petite couleur et met à jour les données
	 * @param vertex le sommet à colorier
	 * @param listN la liste de ses voisins
	 * @return la couleur utilisée
	 */
	private int color(int vertex, Set<Integer> listN) {
		int color = getColor(listN);
		this.colorVertex.put(vertex, color);
		return color;
	}
	
	/**
	 * Cherche le sommet avec le degré de saturation le plus élevé
	 * @return le sommet avec le plus grand degré de saturation
	 */
	private int chooseMaxSat() {
		if (maxSatVertex.size() == 1)
			return maxSatVertex.get(0);
		else 
			return searchMaxDegre();
	}

	/**
	 * Dans le cas où plusieurs sommets ont un degré de saturation max, cherche celui qui à le degré max
	 * @param maxSatVertex
	 * @param vertexDD
	 * @return le sommet de degré et degré de saturation maximaux
	 */
	private int searchMaxDegre() {
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
	 * Incrémente le degré et met à jour le tableau de couleurs des voisins
	 * @param g
	 * @param vertex
	 * @param vertexDS
	 * @param maxSat 
	 * @param maxSatVertex 
	 * @return
	 * @throws VertexNotFoundException
	 */
	private void majDS(Set<Integer> listN, int color) throws VertexNotFoundException {
		int tmp = 0;
		for (int n : listN) { // pour chaque voisin ...
			if ( ( ! neigthColor.get(n).contains(color) ) && (vertexDS[n] != -1) ) { // si aucun de ses voisins ne contient la couleur ajoutée
				vertexDS[n] = tmp++; // incrémente son DS
				neigthColor.get(n).add(color); //ajoute la couleur dans la table du voisin
			}
		}
		return;
	} 
}
