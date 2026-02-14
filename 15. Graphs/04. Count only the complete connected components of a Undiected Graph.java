/* https://leetcode.com/problems/count-the-number-of-complete-components/description/
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. 
You are given a 2D integer array edges where edges[i] = [ai, bi] 
denotes that there exists an undirected edge connecting vertices ai and bi.
Return the number of complete connected components of the graph.
A connected component is a subgraph of a graph in which there exists a path between any two vertices, 
and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
A connected component is said to be complete 
if there exists an edge between every pair of its vertices.

Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete. */


// LOGIC => complete connected component means 
// n = num of nodes => num of edges should be = n * (n-1) / 2  (if not counted bi-directional)
class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(n, edges);
        boolean[] visited = new boolean[n];
        int countCompleteComponents = 0;

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                //for A component
                int[] nodeEdgeCombo =  new int[2];
                getNumOfNodeEdgeComboInAComponent(adjList, visited, i, nodeEdgeCombo);

                int node = nodeEdgeCombo[0];
                int degreeSum = nodeEdgeCombo[1]; // this denotes sum of every node's "in-out degree"
                if(degreeSum == node * (node-1))
                    countCompleteComponents++;
            }
        }
        return countCompleteComponents;
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

    private void getNumOfNodeEdgeComboInAComponent(ArrayList<ArrayList<Integer>> adjList, 
                                boolean[] visited, int currentNode, int[] nodeEdgeCombo) {
        visited[currentNode] = true;
        nodeEdgeCombo[0]++;

        for (int currNeighNode: adjList.get(currentNode)) {
            nodeEdgeCombo[1]++;
            if (!visited[currNeighNode]) {
                getNumOfNodeEdgeComboInAComponent(adjList, visited, currNeighNode, nodeEdgeCombo);
            }
        }
    }
}