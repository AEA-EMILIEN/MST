package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe abstraite posant les bases pour une coloration
 * @author Emilie & Julien
 *
 */
public abstract class Coloration {
	
	// la liste des couleurs utilisées
	protected ArrayList<Integer> listColor = new ArrayList<Integer>();
	
	// map : associe à chaque sommet une couleur
	protected HashMap<Integer,Integer> colorVertex = new HashMap<Integer, Integer>();
	
	
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
	 * Indique le nombre de couleurs utilisées
	 * @return le nombre de couleur
	 */
	public int nbColor() {
		return this.listColor.size();
	}

	
	
}
