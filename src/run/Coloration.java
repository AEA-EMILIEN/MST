package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Graph;

public class Coloration {
	
	
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
