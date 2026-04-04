/* https://leetcode.com/problems/is-graph-bipartite/description/
https://www.geeksforgeeks.org/problems/bipartite-graph/1
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. 
You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. 
More formally, for each v in graph[u], there is an undirected edge between node u and node v. 

The graph has the following properties:
There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B 
such that every edge in the graph connects a node in set A and a node in set B.
Return true if and only if it is bipartite. */

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] marking = new int[n]; //either marking value is 0 / 1
        Arrays.fill(marking, -1);
        
        for(int i=0; i<n; i++) {
            if(marking[i] == -1) {
                marking[i] = 0; // start marking with 0
                if(!isEachNeighboursOfEveryNodeMarkedAlternate(graph, marking, i))
                    return false;
            }
        }
        return true;
    }
    
    private boolean isEachNeighboursOfEveryNodeMarkedAlternate(int[][] adjList, int[] marking, int currNode) 
    {
        for(int neighNode: adjList[currNode]) {
            if(marking[neighNode] == -1) {
                // mark them -> alternate
                marking[neighNode] = marking[currNode] ^ 1;
                if(! isEachNeighboursOfEveryNodeMarkedAlternate(adjList, marking, neighNode))
                    return false;
            }
            else if(marking[neighNode] != -1 && marking[neighNode] == marking[currNode]) {
                // found visited neighbour & with same marking
                return false;
            }
        }

        return true;
    }
}