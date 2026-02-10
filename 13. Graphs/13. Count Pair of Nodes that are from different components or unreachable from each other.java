/* https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/
You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. 
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that 
there exists an undirected edge connecting nodes ai and bi.
Return the number of pairs of different nodes that are unreachable from each other.*/

class Solution {
    public long countPairs(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(n, edges);
        boolean[] visited = new boolean[n];

        ArrayList<Integer> getComponentSizes = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                int[] nodesInComp = {0};
                getNodesCountInAComponent(adjList, visited, i, nodesInComp);
                getComponentSizes.add(nodesInComp[0]);
            }
        }
        
        // product of every element with each other in getComponentSizes[] (no pairs repeititon)
        long countPairsWhereBothFromDifferentComponents = 0;
        // O(n^2) -> TLE
        // for (int i=0; i<getComponentSizes.size(); i++) {
        //     for (int j=i+1; j<getComponentSizes.size(); j++) {
        //         countPairsWhereBothFromDifferentComponents += 
        //                 (long)getComponentSizes.get(i)* (long)getComponentSizes.get(j);
        //     }
        // }

        // O(n) -> Take one element*(all the sum of other) + next element*(all the sum of its right elements) ...
        int suffixSumExceptSelf = n;
        for(int i=0; i<getComponentSizes.size(); i++) {
            suffixSumExceptSelf -= getComponentSizes.get(i);
            countPairsWhereBothFromDifferentComponents += (long)getComponentSizes.get(i) * suffixSumExceptSelf;
        }
        return countPairsWhereBothFromDifferentComponents;
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

    private void getNodesCountInAComponent(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, 
                        int currentNode, int[] totalNodes) 
    {
        visited[currentNode] = true;
        totalNodes[0]++;
    
        for (int currNeighNode: adjList.get(currentNode)) {
            if (!visited[currNeighNode]) {
                getNodesCountInAComponent(adjList, visited, currNeighNode, totalNodes);
            }
        }
    }
}