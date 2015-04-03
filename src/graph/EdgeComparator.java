package graph;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge e0, Edge e1) {
		
		return Integer.compare(e0.weigth,e1.weigth);
	}

}
