/* https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, 
check whether it contains any cycle or not.
The graph is represented as a 2D vector edges[][], 
where each entry edges[i] = [u, v] denotes an edge from verticex u to v.

Input: V = 4, edges[][] = [[0, 1], [1, 2], [2, 0], [2, 3]]
Output: true
Explanation: The diagram clearly shows a cycle 0 → 1 → 2 → 0*/
class Solution {
    public boolean isCyclic(int V, int[][] edges) 
    {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        boolean[] visited = new boolean[V];
        boolean[] isAncestor = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i])
                if(isCyclePresent(adjList, visited, isAncestor, i))
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
        }
        return adj;
    }
    
    /* Logic:- For each node:
            For each neighbor:
                1. If neighbor is unvisited → DFS(neighbor)
                   If DFS returns true → cycle exists.
        
                2. If neighbor is already in the CURRENT recursion path 
                   (i.e., recStack[neighbour] == true)
                   → cycle exists (back edge). */
    private boolean isCyclePresent(ArrayList<ArrayList<Integer>> adjList, 
                                    boolean[] visited, boolean[] isAncestor, int currentNode)
    {
        visited[currentNode] = true;
        isAncestor[currentNode] = true;
        
        for(int nearNode: adjList.get(currentNode))
        {
            if(!visited[nearNode]) {
                if(isCyclePresent(adjList, visited, isAncestor, nearNode))
                    return true;
            } else if (visited[nearNode] && isAncestor[nearNode]) {
                return true;
            }
        }
        
        isAncestor[currentNode] = false;
        return false;
    }
}