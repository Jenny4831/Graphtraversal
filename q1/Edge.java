public class Edge implements Comparable<Edge> {
	int v1, v2;
	double weight;

	public Edge(int v1, int v2) {
		this.v1 = v1; 
		this.v2 = v2;
	}

	public Edge(int v1, int v2, double weight) {
		this(v1, v2);
		this.weight = weight;
	}

	public int compareTo(Edge other) {
		if (this.weight < other.weight) return -1;
		if (this.weight > other.weight) return 1;
		return 0;
	}
}

