package run;

import exception.VertexNotFoundException;
import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WelshPowell extends Coloration {

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

	public long timeWP(Graph g) {
		long startTime = System.nanoTime();
		try {
			runWelshPowell(g);
		} catch (VertexNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		return duration/1000000; 
	}


	
}
