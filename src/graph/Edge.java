package graph;

public class Edge {
	public int start ;
	public int end ;
	public int weigth ;
	
	public Edge (int s, int e, int w) {
		this.start = s;
		this.end = e;
		this.weigth = w;
	}

	public boolean equals (Object o) {
		if (o instanceof Edge ) {
			Edge e = (Edge) o;
			return (((this.start == e.start) && (this.end == e.end) && (this.weigth == e.weigth)) 
					|| 
					((this.start == e.end) && (this.end == e.start) && (this.weigth == e.weigth)));
		}
		return false;
	}

	public String toString() {
		return "edge : " + this.start + " -> " + this.end + " = " + this.weigth + "\n";
	}
}
