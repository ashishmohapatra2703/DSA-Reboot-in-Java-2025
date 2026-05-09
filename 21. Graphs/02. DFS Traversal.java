/* https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
Given a connected undirected graph containing V vertices represented by a 2-d adjacency list adj[][], 
where each adj[i] represents the list of vertices connected to vertex i. 
Perform a Depth First Search (DFS) traversal starting from vertex 0, 
visiting vertices from left to right as per the given adjacency list, 
and return a list containing the DFS traversal of the graph.
Note: Do traverse in the same order as they are in the given adjacency list.

Input: adj[][] = [[2, 3, 1], [0], [0, 4], [0], [2]]
Output: [0, 2, 4, 3, 1]
Explanation: Starting from 0, the DFS traversal proceeds as follows:
Visit 0 → Output: 0 
Visit 2 (the first neighbor of 0) → Output: 0, 2 
Visit 4 (the first neighbor of 2) → Output: 0, 2, 4 
Backtrack to 2, then backtrack to 0, and visit 3 → Output: 0, 2, 4, 3 
Finally, backtrack to 0 and visit 1 → Final Output: 0, 2, 4, 3, 1

Input: adj[][] = [[1, 2], [0, 2], [0, 1, 3, 4], [2], [2]]
Output: [0, 1, 2, 3, 4]
Explanation: Starting from 0, the DFS traversal proceeds as follows: 
Visit 0 → Output: 0 
Visit 1 (the first neighbor of 0) → Output: 0, 1 
Visit 2 (the first neighbor of 1) → Output: 0, 1, 2 
Visit 3 (the first neighbor of 2) → Output: 0, 1, 2, 3 
Backtrack to 2 and visit 4 → Final Output: 0, 1, 2, 3, 4 */

//given 1 component graph
// Logic = we visit the source node and recursively call its neighbours which are not visited.
class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> dfsOfGraph = new ArrayList<>();
        int nodes = adj.size();
        boolean[] visited = new boolean[nodes];
        
        dfsUtil(0, adj, visited, dfsOfGraph);
        return dfsOfGraph;
    }
    
    private void dfsUtil(int currentNode, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfsOfGraph) 
    {
        visited[currentNode] = true;
        dfsOfGraph.add(currentNode);
    
        for (int nei: adj.get(currentNode)) {
            if (!visited[nei]) {
                dfsUtil(nei, adj, visited, dfsOfGraph);
            }
        }
    }
}

// Iterative Style using Stack
class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> dfsOfGraph = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        int nodes = adj.size();
        boolean[] visited = new boolean[nodes];
        
        st.push(0);   //pushing first vertex in stack.

	    while(!st.isEmpty())
	    {
	        int currentNode = st.pop();
	        if (visited[currentNode])
	            continue;   // skip if already processed
            visited[currentNode] = true;  // mark visited only AFTER popped
            
	        dfsOfGraph.add(currentNode);
	        
	        //traversing over all the neighbours of currentNode (IN REVERSE ORDER)
	        for(int i=adj.get(currentNode).size()-1; i>=0; i--) 
	        {
	            int neighbourNode = adj.get(currentNode).get(i);
	            if(!visited[neighbourNode])
	            {
	                st.push(neighbourNode);
	            }
	        }
	    }
	    return dfsOfGraph;
    }
}