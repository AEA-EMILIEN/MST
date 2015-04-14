package graph;

public class Edge {
	public int start ;
	public int end ;
	public int weight ;
	
	public Edge(int s, int e, int w) {
		this.start = s;
		this.end = e;
		this.weight = w;
	}

	public boolean equals (Object o) {
		if (o instanceof Edge ) {
			Edge e = (Edge) o;
			return (((this.start == e.start) && (this.end == e.end) && (this.weight == e.weight)) 
					|| 
					((this.start == e.end) && (this.end == e.start) && (this.weight == e.weight)));
		}
		return false;
	}

	public String toString() {
		return "edge : " + this.start + " -> " + this.end + " = " + this.weight + "\n";
	}
}
