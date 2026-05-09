/* https://www.geeksforgeeks.org/problems/topological-sort/1
Given a Directed Acyclic Graph (DAG) of V (0 to V-1) vertices and E edges represented 
as a 2D list of edges[][], where each entry edges[i] = [u, v] denotes 
a directed edge u -> v. Return the topological sort for the given graph.

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices 
such that for every directed edge u -> v, vertex u comes before v in the ordering.  */

class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        ArrayList<Integer> topoSortResult = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i])
                topoSortUtil(adjList, visited, i, topoSortResult);
        }
        
        Collections.reverse(topoSortResult); // or use stack for natural reverse while poping out into result
        return topoSortResult;
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
    
    private void topoSortUtil(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                            int currentNode, ArrayList<Integer> topoSortResult)
    {
        visited[currentNode] = true;
        
        for (int neigNode: adjList.get(currentNode)) {
            if(!visited[neigNode])
                topoSortUtil(adjList, visited, neigNode, topoSortResult);
        }
        
        topoSortResult.add(currentNode);
        //  Post Order Traversal: 
        // Note that a vertex is pushed to stack only when all of its adjacent vertices
        // (and their adjacent vertices and so on) are already in the stack.
    }
}