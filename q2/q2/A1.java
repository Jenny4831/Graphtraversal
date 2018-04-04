
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class A1 {

	//global variables.
	private LinkedList<Integer> adjList[];
	private int vertices;
	
	//graph constructor
	//adj list: http://www.geeksforgeeks.org/graph-and-its-representations/
	@SuppressWarnings("unchecked")
	public A1(int n){
		adjList = new LinkedList[n];
		this.vertices = n;
		for(int i = 0; i < n; i++){
			adjList[i] = new LinkedList<Integer>();
		}	
	}

	//helper method to build the graph
	public void addEdge(int v1, int v2){
		adjList[v1].add(v2);
		adjList[v2].add(v1);
	}

	//bfs. reference http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
	//plus own modification
	public void findPath(int v1, int v2){

		if(v1 == v2){
			System.out.println(1);
			return;
		}

		//initiate a boolean array to track explored vertices
		boolean[] visited = new boolean[vertices];
		@SuppressWarnings("rawtypes")

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(v1);
		
		while(!q.isEmpty()){
			
			int current;
			int neighbour;
			current = (int) q.remove();
			Iterator<Integer> i = adjList[current].listIterator();
			//add neighbours that are not visited to the queue
			while(i.hasNext()){
				neighbour = i.next();
				if(visited[neighbour] == false){
					visited[neighbour] = true;
					q.add(neighbour);
				}	
			}
			
			if(visited[v2] == true){
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
		return;
	}
	
    public static void main(String[] args) {
    	int n;//vertices
    	int m;//edges
        Scanner input = new Scanner(System.in);
       
        n = input.nextInt();
        m = input.nextInt();

        //create empty graph
        A1 graph = new A1(n);

        for(int i = 0; i < m; i++){
        	int v1 = input.nextInt();
        	int v2 = input.nextInt();
       		graph.addEdge(v1, v2);
        	@SuppressWarnings("unused")
			double weight = input.nextDouble();
        }

        int pairs = input.nextInt();
        int[] start = new int[pairs];
        int[] end = new int[pairs];
        for(int j = 0; j < pairs; j++){
        	start[j] = input.nextInt();
        	end[j] = input.nextInt();
        }
        
       for(int q = 0; q < pairs; q++){
    	   graph.findPath(start[q], end[q]);
       }

        input.close();       
    }

}

