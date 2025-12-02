/* https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
Given an undirected graph with V vertices and E edges, 
represented as a 2D vector edges[][], where each entry edges[i] = [u, v] 
denotes an edge between vertices u and v, 
determine whether the graph contains a cycle or not.
Note: The graph can have multiple component.

Input: V = 4, E = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]
Output: true
1 -> 2 -> 0 -> 1 is a cycle.

Input: V = 4, E = 3, edges[][] = [[0, 1], [1, 2], [2, 3]]
Output: false
Explanation: No cycle in the graph. */

class Solution {
    public boolean isCycle(int V, int[][] edges) 
    {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        boolean[] visited = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i])
                if(isCyclePresent(adjList, visited, i, -1))
                    return true;
        }
        return false;
    }
    
    private ArrayList<ArrayList<Integer>> buildAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
    
    /* Logic:- For each node:
			For every neighbor:
			1. If unvisited: DFS into it; if DFS returns true, a cycle exists.
			2. If visited && not the parent: cycle exists. */
    private boolean isCyclePresent(ArrayList<ArrayList<Integer>> adjList, 
                                    boolean[] visited, int currentNode, int parentNode)
    {
        visited[currentNode] = true;
        
        for(int nearNode: adjList.get(currentNode))
        {
            if(!visited[nearNode]) {
                if(isCyclePresent(adjList, visited, nearNode, currentNode))
                    return true;
            } else if (visited[nearNode] && nearNode != parentNode) {
                return true;
            }
        }
        return false;
    }
}