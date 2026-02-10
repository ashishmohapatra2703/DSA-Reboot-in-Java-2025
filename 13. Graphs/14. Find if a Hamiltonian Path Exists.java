/* https://www.geeksforgeeks.org/problems/hamiltonian-path2522/1
Given an undirected graph with n vertices and m edges, 
your task is to determine if a Hamiltonian path exists in the graph.
A Hamiltonian path is a path in an undirected graph that visits each vertex exactly once.

Input: n = 4, m = 4
edges[][]= { {1,2}, {2,3}, {3,4}, {2,4} }
Output: 1 
Explanation: There is a hamiltonian path: 1 -> 2 -> 3 -> 4  */

class Solution {
    boolean check(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(n, edges);
        boolean[] visited = new boolean[n+1]; //1-based indexing
        int[] countOfAllVisitedNodes = {0};
        
        for(int i=1; i<=n; i++) {
            if(ifAllNodesMarksVisitedInDFSStartingFromI(adjList, visited, i, countOfAllVisitedNodes, n))
                    return true;
        }
        return false;
    }
    
    private ArrayList<ArrayList<Integer>> buildAdjList(int V, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V+1);
        for(int i=0; i<=V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0; i<edges.size(); i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
    private boolean ifAllNodesMarksVisitedInDFSStartingFromI(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                                        int currNode, int[] countOfAllVisitedNodes, int totalNumNodes) {
        visited[currNode] = true;
        countOfAllVisitedNodes[0]++;
        
        if(countOfAllVisitedNodes[0] == totalNumNodes) {
            return true;
        }
        
        for(int currNeighNode: adjList.get(currNode)) {
            if(!visited[currNeighNode]) {
                if(ifAllNodesMarksVisitedInDFSStartingFromI(adjList, visited, currNeighNode, countOfAllVisitedNodes, totalNumNodes))
                    return true;
            }
        }
        countOfAllVisitedNodes[0]--;
        visited[currNode] = false;
        
        return false;
    }
}