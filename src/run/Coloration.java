package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;

public class Coloration {
	
	
	public HashMap<Integer,Integer> runWelshPowell(Graph g)
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
			couleurActuelle = getColor(g.vertex.get(listDegre[i]));
			//si on ajoute une couleur supplementaire 
			if(maxCouleur<couleurActuelle)
				maxCouleur=couleurActuelle;
			
			//on assigen la couleur pour le sommet i
			this.colorVertex.put(i,couleurActuelle);
			
			
			
			
			
			
		 
			
		}
		
		return null;
	}

	
	
	
}
