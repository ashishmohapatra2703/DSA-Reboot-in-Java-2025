/* https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
Given an undirected, weighted graph with V vertices numbered from 0 to V-1 and E edges, 
represented by 2d array edges[][], where edges[i]=[u, v, w] represents 
the edge between the nodes u and v having w edge weight.
You have to find the shortest distance of all the vertices from the source vertex src, 
and return an array of integers where the ith element denotes the shortest distance between ith node and source vertex src.

Note: The Graph is connected and doesn't contain any negative weight edge.
It is guaranteed that all the shortest distance will fit in a 32-bit integer.

Input: V = 3, edges[][] = [[0, 1, 1], [1, 2, 3], [0, 2, 6]], src = 2
Output: [4, 3, 0]  */

// Dijkstra = greedy
// T.C = O(V * (V+E)*logV) -> run Dijkstra V times, once for each destination TLE
class Solution {
    static class Pair {
        int toNode;
        int weight;
    
        Pair(int toNode, int weight) {
            this.toNode = toNode;
            this.weight = weight;
        }
    }
    
    public int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<Pair>> adjList = buildAdjList(V, edges);
        int[] allShortestDistanceFromSrc = new int[V];
        
        for(int i=0; i<V; i++) {
            allShortestDistanceFromSrc[i] = getShortestDistanceFromSrcToi(adjList, V, src, i);
        }
        return allShortestDistanceFromSrc;
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
            adj.get(v).add(new Pair(u, wt));
        }
        return adj;
    }
    private int getShortestDistanceFromSrcToi(ArrayList<ArrayList<Pair>> adjList, int n, int src, int dest) 
    {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> minH = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // min heap = storing pairs of (currNodeSoFarInPath, pathDistance) <- to keep the shortest path always on top
        minH.add(new int[]{src, 0}); // from source to source distance = 0
        
        while(!minH.isEmpty())
        {
            int[] currNodeAndDistanceInPathSoFarCombo = minH.poll(); 
            int currNode = currNodeAndDistanceInPathSoFarCombo[0];
            int minDistanceSoFar = currNodeAndDistanceInPathSoFarCombo[1];
            
            if(visited[currNode] == true)
            {
                // it means the shortest path is already found to this node
                // we should not recompute again / we can not get any better min
                continue;
            }
            visited[currNode] = true; //A node is marked visited 
            // => that path is finalized as to be included part of shortest path
            // => ONLY when it is popped from the priority queue with the minimum distance.
            
            if(currNode == dest) {
                return minDistanceSoFar;
            }
            
            for(Pair neigh: adjList.get(currNode)) 
            {
                int neighNode = neigh.toNode;
                int neighDistance = neigh.weight;
                
                if(!visited[neighNode])
                {
                    minH.add(new int[]{neighNode , minDistanceSoFar + neighDistance});
                }
            }
        }
        return -1;
    }
}


// T.C = O((V+E)*logV) Single Run with memoize
class Solution {
    static class Pair {
        int toNode;
        int weight;
    
        Pair(int toNode, int weight) {
            this.toNode = toNode;
            this.weight = weight;
        }
    }
    
    public int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<Pair>> adjList = buildAdjList(V, edges);
        // memoize
        int[] allShortestDistanceFromSrc = new int[V];
        Arrays.fill(allShortestDistanceFromSrc, 0);
        
        getShortestDistanceFromSrcToEachDest(adjList, V, src, allShortestDistanceFromSrc);
        return allShortestDistanceFromSrc;
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
            adj.get(v).add(new Pair(u, wt));
        }
        return adj;
    }
    private void getShortestDistanceFromSrcToEachDest(ArrayList<ArrayList<Pair>> adjList, int n, int src, int[] shortestDistanceFromSrc) 
    {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> minH = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // min heap = storing pairs of (currNodeSoFarInPath, pathDistance) <- to keep the shortest path always on top
        minH.add(new int[]{src, 0}); // from source to source distance = 0
        shortestDistanceFromSrc[src] = 0;
        
        while(!minH.isEmpty())
        {
            int[] currNodeAndDistanceInPathSoFarCombo = minH.poll(); 
            int currNode = currNodeAndDistanceInPathSoFarCombo[0];
            int minDistanceSoFar = currNodeAndDistanceInPathSoFarCombo[1];
            
            if(visited[currNode] == true)
            {
                // it means the shortest path is already found to this node
                // we should not recompute again / we can not get any better min
                continue;
            }
            visited[currNode] = true; //A node is marked visited 
            // => that path is finalized as to be included part of shortest path
            // => ONLY when it is popped from the priority queue with the minimum distance (not when it is pushed)
            shortestDistanceFromSrc[currNode] = minDistanceSoFar; // currNode ~= destinationSoFar (GREEDY of MINIMUM)
            
            for(Pair neigh: adjList.get(currNode)) 
            {
                int neighNode = neigh.toNode;
                int neighDistance = neigh.weight;
                
                if(!visited[neighNode])
                {
                    minH.add(new int[]{neighNode , minDistanceSoFar + neighDistance});
                }
            }
        }
    }
}