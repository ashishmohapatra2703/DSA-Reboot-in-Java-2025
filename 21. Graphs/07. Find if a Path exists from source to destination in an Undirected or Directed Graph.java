/* https://leetcode.com/problems/find-if-path-exists-in-graph/description/
https://www.geeksforgeeks.org/problems/path-exists/1
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
The edges in the graph are represented as a 2D integer array edges, 
where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. 
Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, 
return true if there is a valid path from source to destination, or false otherwise. */

class Solution {
    public boolean checkPath(int V, int[][] edges, int src, int dest) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        boolean[] visited = new boolean[V];
        return doesPathExist(adjList, visited, src, dest);
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
    
    private boolean doesPathExist(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                                    int fromSrc, int toDest) {
        if(fromSrc == toDest)
            return true;
        visited[fromSrc] = true;
        
        for(int srcNeighNode: adjList.get(fromSrc)) {
            if(!visited[srcNeighNode] && doesPathExist(adjList, visited, srcNeighNode, toDest))
                return true;
        }
        return false;
    }
}

/* https://www.interviewbit.com/problems/path-in-directed-graph/
Given an directed graph having A nodes labelled from 1 to A containing M edges 
given by matrix B of size M x 2such that there is a edge directed from node B[i][0] to node B[i][1].
Find whether a path exists from node 1 to node A.
Return 1 if path exists else return 0.

NOTE:
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them. */

public class Solution {
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(A, B);
        boolean[] visited = new boolean[A+1]; //1-based indexing
        return doesPathExist(adjList, visited, 1, A) ? 1 : 0; // srcNode=1 and destNode=A
    }
    
    private ArrayList<ArrayList<Integer>> buildAdjList(int V, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V+1; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(ArrayList<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            adj.get(u).add(v); // directed graph
        }
        return adj;
    }
    
    private boolean doesPathExist(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                                    int fromSrc, int toDest) {
        if(fromSrc == toDest)
            return true;
        visited[fromSrc] = true;
        
        for(int srcNeighNode: adjList.get(fromSrc)) {
            if(!visited[srcNeighNode] && doesPathExist(adjList, visited, srcNeighNode, toDest))
                return true;
        }
        return false;
    }
}