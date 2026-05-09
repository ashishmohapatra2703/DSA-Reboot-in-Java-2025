/* https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1
Given an undirected graph with V vertices numbered from 0 to V-1 and E edges, represented as a 2D array edges[][], 
where each entry edges[i] = [u, v] denotes an edge between vertices u and v.
Your task is to return a list of all connected components. 
Each connected component should be represented as a list of its vertices, 
with all components returned in a collection where each component is listed separately.
Note: You can return the components in any order, driver code will print the components in sorted order. */

class Solution {
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        
        ArrayList<ArrayList<Integer>> getComponents = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                ArrayList<Integer> comp = new ArrayList<>();
                dfsUtil(adjList, visited, i, comp);
                getComponents.add(comp);
            }
        }
        return getComponents;
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
    
    private void dfsUtil(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                        int currentNode, ArrayList<Integer> compSoFar) 
    {
        visited[currentNode] = true;
        compSoFar.add(currentNode);
    
        for (int currNeighNode: adjList.get(currentNode)) {
            if (!visited[currNeighNode]) {
                dfsUtil(adjList, visited, currNeighNode, compSoFar);
            }
        }
    }
}