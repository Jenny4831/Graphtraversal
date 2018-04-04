import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class A1 {
	//http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
	//is used as reference to modify my own union-find and kruskal
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Edge> mstEdges = new ArrayList<Edge>();
	private List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
	//Union-find data structure
	public Set<Integer> findVertexSet(Integer vertex) {
		for (Set<Integer> set : sets) {
			if (set.contains(vertex)) {
				return set;
			}
		}
		return null;
	}

	public Edge findEdge(Integer v1, Integer v2) {
		for (Edge edge : edges) {
			if (edge.v1 == v1 && edge.v2 == v2) {
				return edge;
			}
			if (edge.v1 == v2 && edge.v2 == v1) {
				return edge;
			}
		}
		return null;
	}

	public void mergeTwoSets(Set<Integer> set1, Set<Integer> set2) {
		set1.addAll(set2);
		sets.remove(set2);
	}

	public A1() {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		for (int i = 0; i < m; i++) {
			this.edges.add(new Edge(input.nextInt(), input.nextInt(), input.nextDouble()));
		}

		int pairs = input.nextInt();
		List<Edge> preSelectedEdges = new ArrayList<Edge>();
		for (int q = 0; q < pairs; q++) {
			preSelectedEdges.add(new Edge(input.nextInt(), input.nextInt()));
		}
		//Kruskal algorithm is used
		// 0. sort
		Collections.sort(this.edges);

		// 1. create a set for each node, node name is just a integer
		for (int i = 0; i < n; i++) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(i);
			sets.add(set);
		}

		// 2. select preSelectedEdges
		for (Edge edge : preSelectedEdges) {
			Set<Integer> set1 = this.findVertexSet(edge.v1);
			Set<Integer> set2 = this.findVertexSet(edge.v2);

			// find the actual edge in the graph
			Edge actualEdge = findEdge(edge.v1, edge.v2);
			// remove it from edges collection
			this.edges.remove(actualEdge);

			if (set1 != set2) {
				this.mergeTwoSets(set1, set2);
				this.mstEdges.add(actualEdge);
			}
		}

		// 3. find min weight edge
		while (!this.edges.isEmpty() && this.sets.size() != 1) {
			// select the min weight edge
			Edge edge = this.edges.get(0);
			this.edges.remove(0);

			// remove it from edges collection
			Set<Integer> set1 = this.findVertexSet(edge.v1);
			Set<Integer> set2 = this.findVertexSet(edge.v2);

			if (set1 != set2) {
				this.mergeTwoSets(set1, set2);
				this.mstEdges.add(edge);
			}
		}
		
		// 4. sum weight
		double minWeight = 0;
		for (Edge edge : this.mstEdges) {
			minWeight += edge.weight;
		}

		System.out.printf("%.2f\n", minWeight);
		input.close();
	}

	public static void main(String[] args) {
		new A1();
	}

}
