public class GraphAdjMatrix implements Graph {
	int[][] edges;
	public GraphAdjMatrix(int size) {
		edges = new int[size][size];	
		for (int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				edges[i][j] = -1;
	}

	public void addEdge(int v1, int v2) {
		edges[v1][v2] = 1;

	}

	public void topologicalSort() {
		int[] visted = new int[edges.length];
		for (int i = 0; i < edges.length; i++){
			for (int j = 0; j < edges.length; j++){
				if(edges[i][j]!= -1) {
					visted[i] += 1;
				}
			}
		}
		int count = 0;
		while(count< visted.length){
			int temp = -1;
			int index = 0;
			for (int i = 0; i < visted.length; i++){
				if( temp < visted[i]){
					temp = visted[i];
					index = i;
				}	
			}			
			System.out.println(index); 
			visted[index] = -1;
			count++;
		}
	}



	public void addEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;


	}

	public int getEdge(int v1, int v2) {
		return edges[v1][v2];
	}
	
	//Prims Algorithm
	public int createSpanningTree(){
		int V = edges.length;
		int vertex[] = new int[V];
		vertex[0] = -1;
		int tree[] = new int [V];
		Boolean visited[] = new Boolean[V];
		for (int i = 0; i < V; i++)
		{
			tree[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		tree[0] = 0;
		for (int i = 0; i < V-1; i++)
		{
			int index = minValue(tree, visited);
			visited[index] = true;
			for (int target = 0; target < V; target++)
				if (edges[index][target]!= -1 && visited[target] == false && edges[index][target] < tree[target])
				{
					vertex[target]  = index;
					tree[target] = edges[index][target];
				}
		}
		int result = 0;
		for (int i = 1; i < V; i++) {
			result += edges[i][vertex[i]];
		}

		return result;

	}
	public int minValue(int tree[], Boolean visited[])
	{
		int V = edges.length;
		int minVal = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < V; i++)
			if (visited[i] == false && tree[i] < minVal)
			{
				minVal = tree[i];
				minIndex = i;
			}

		return minIndex;
	}
}
