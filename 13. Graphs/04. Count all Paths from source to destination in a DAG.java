/* https://www.geeksforgeeks.org/problems/possible-paths-between-2-vertices-1587115620/1 
Given a Directed Acyclic Graph (DAG) with V nodes labeled from 0 to V-1, and a list of directed edges, 
count the total number of distinct paths from a given start node to a destination node. 
Each edge is represented as edges[i] = [u, v], indicating a directed edge from u to v.

Input: edges[][] = [[0,1], [0,3], [2,0], [2,1], [1,3]], V = 4, src = 2, dest = 3
Output: 3
Explanation: There are three ways to reach at 3 from 2. 
These are: 2 -> 1 -> 3, 2 -> 0 -> 3 and 2 -> 0 -> 1 -> 3. */

class Solution {
    // Function to count paths between two vertices in a directed graph.
    public int countPaths(int V, ArrayList<ArrayList<Integer>> adj, int source, int destination) {
        if(source == destination) {
            return 1;
        }

        int totalCount = 0;
        for(int srcNeighNode: adj.get(source)) {
            totalCount += countPaths(V, adj, srcNeighNode, destination);
        }
        return totalCount;
    }
}

/*https://www.geeksforgeeks.org/problems/count-the-paths4332/1
Given a Directed Acyclic Graph (DAG) with V nodes labeled from 0 to V-1, 
and a list of directed edges, count the total number of distinct paths 
from a given start node to a destination node. 
Each edge is represented as edges[i] = [u, v], indicating a directed edge from u to v.

Input: edges[][] = [[0,1], [0,3], [2,0], [2,1], [1,3]], V = 4, src = 2, dest = 3
Output: 3
Explanation: There are three ways to reach at 3 from 2. These are: 2 -> 1 -> 3, 2 -> 0 -> 3 and 2 -> 0 -> 1 -> 3. */

// this gives TLE // T.C = O(2^n)
class Solution {
    public int countPaths(int[][] edges, int V, int src, int dest) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        
        return countAllPathsFromSrcToDest(adjList, src, dest, 0);
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
    
    private int countAllPathsFromSrcToDest(ArrayList<ArrayList<Integer>> adjList, 
                                            int fromSrc, int toDest, int countSoFar) {
        if(fromSrc == toDest) {
            countSoFar++;
            return countSoFar;
        }

        for(int srcNeighNode: adjList.get(fromSrc)) {
            countSoFar = countAllPathsFromSrcToDest(adjList, srcNeighNode, toDest, countSoFar);
        }
        
        return countSoFar;
    }
}

//passed -> DP on graph // // T.C = O(V+E)
class Solution {
    public int countPaths(int[][] edges, int V, int src, int dest) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        int[] dp = new int[V]; //dpPathCountFromSrcToDest
        Arrays.fill(dp, -1);   
        return countAllPathsFromSrcToDest(adjList, src, dest, dp);
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
    
    private int countAllPathsFromSrcToDest(ArrayList<ArrayList<Integer>> adjList, 
                                            int fromSrc, int toDest, int[] dp) {
        if(dp[fromSrc] != -1) {
            return dp[fromSrc];
        }
        
        if(fromSrc == toDest) {
            return dp[fromSrc] = 1;
        }
        
        int totalCount = 0;
        for(int srcNeighNode: adjList.get(fromSrc)) {
            totalCount += countAllPathsFromSrcToDest(adjList, srcNeighNode, toDest, dp);
        }
        
        return dp[fromSrc] = totalCount;
    }
}