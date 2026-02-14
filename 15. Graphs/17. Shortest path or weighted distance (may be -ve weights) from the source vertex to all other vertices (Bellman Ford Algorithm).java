/* https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
Given an weighted graph with V vertices numbered from 0 to V-1 and E edges, represented by a 2d array edges[][], 
where edges[i] = [u, v, w] represents a direct edge from node u to v having w edge weight. You are also given a source vertex src.

Your task is to compute the shortest distances from the source to all other vertices. 
If a vertex is unreachable from the source, its distance should be marked as 10^8. 
Additionally, if the graph contains a negative weight cycle, 
return [-1] to indicate that shortest paths cannot be reliably computed.  */

// Dijkstra doesn't revisit those nodes which have already been marked as visited. 
// If a shorter path exists through a longer route with negative edges, Dijkstra's will fail to handle it.

// Dijkstra: “The first time I reach a node optimally, I’m done.”
// Bellman–Ford: “I don’t trust anything until I’ve tried all possibilities enough times.”

// Any shortest path has AT MOST V-1 edges. (e.g, straight line)
// => in that case, Each iteration allows paths one edge longer to be considered. 
// => 1st iteration = 1st level neighbour from source relaxed, 2nd iteration  = 2nd level neighbour from source relaxed & so on ...
// => repeating the relaxation process (V-1) times ensures that all possible paths b/w source and any other node have been covered.
// No further improvement should be possible unless there is a negative cycle.

class Solution {
    static class Pair {
        int toNode;
        int weight;
    
        Pair(int toNode, int weight) {
            this.toNode = toNode;
            this.weight = weight;
        }
    }
    
    public int[] bellmanFord(int V, int[][] edges, int src) {
        ArrayList<ArrayList<Pair>> adjList = buildAdjList(V, edges);
        int[] shortestDistanceFromSrcToEachVertex = new int[V];
        Arrays.fill(shortestDistanceFromSrcToEachVertex, (int)1e8);
        shortestDistanceFromSrcToEachVertex[src] = 0; // from source to source distance = 0
        
        return fillShortestDistanceFromSrcToEachVertexArray(adjList, V, shortestDistanceFromSrcToEachVertex);    
    }  
    
    private ArrayList<ArrayList<Pair>> buildAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(V);
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e: edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.get(u).add(new Pair(v, wt));
        }
        return adj;
    }
    
    private int[] fillShortestDistanceFromSrcToEachVertexArray(ArrayList<ArrayList<Pair>> adjList, 
                                                            int V, int[] shortestDistanceFromSrcToEachVertex) 
    {
        for(int iter=0; iter<V-1; iter++) // Relaxation of all the edges in each iteraton (iteration total = V-1 times)
        {
            for(int i=0; i<V; i++) {
                for(Pair neigh:adjList.get(i)) {
                    relaxEdge(i, neigh, shortestDistanceFromSrcToEachVertex);
                }
            }
        }
        
        // Negative cycle detection
        for(int i=0; i<V; i++) {
            for(Pair neigh:adjList.get(i)) {
                if (relaxEdge(i, neigh, shortestDistanceFromSrcToEachVertex) == true) {
                    return new int[]{-1};
                }
            }
        }
        
        return shortestDistanceFromSrcToEachVertex;
    }
    
    private boolean relaxEdge(int currNode, Pair neigh, int[] shortestDistanceFromSrcToEachVertex) {
        int neighNode = neigh.toNode;
        int neighDistance = neigh.weight;
        
        //reachability check + relax if found short
        if (shortestDistanceFromSrcToEachVertex[currNode] != (int)1e8 &&
                shortestDistanceFromSrcToEachVertex[currNode] + neighDistance < shortestDistanceFromSrcToEachVertex[neighNode]) 
        {
            shortestDistanceFromSrcToEachVertex[neighNode] = shortestDistanceFromSrcToEachVertex[currNode] + neighDistance;
            return true;
        }
        
        return false;
    }
}
