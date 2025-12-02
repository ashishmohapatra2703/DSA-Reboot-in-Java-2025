/* https://www.geeksforgeeks.org/problems/topological-sort/1
Given a Directed Acyclic Graph (DAG) of V (0 to V-1) vertices and E edges represented 
as a 2D list of edges[][], where each entry edges[i] = [u, v] denotes 
a directed edge u -> v. Return the topological sort for the given graph.

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices 
such that for every directed edge u -> v, vertex u comes before v in the ordering.  */

class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // adjacency list
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        // inDegree list of each Node
        int[] inDegree = new int[V];
        buildInDegreeList(V, adjList, inDegree);
        // live Queue and result
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> topoSortResult = new ArrayList<>();
        
        topoSortBFSUtil(V, adjList, inDegree, queue, topoSortResult);
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
    
    private int[] buildInDegreeList(int V, ArrayList<ArrayList<Integer>> adjList, int[] inDegree) {
        for(int i=0; i<V; i++){
            for(int outNode: adjList.get(i)) {
                inDegree[outNode]++;
            }
        }
        return inDegree;
    }
    
    private ArrayList<Integer> topoSortBFSUtil(int V, ArrayList<ArrayList<Integer>> adjList, 
                                            int[] inDegree, Queue<Integer> queue, ArrayList<Integer> topoSortResult)
    {
        for(int i=0; i<V; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);   //pushing all the vertices which has inDegree==0 in queue // starting element in result
            }
        }
	    
	    while(!queue.isEmpty())
	    {
	        int currentNode = queue.poll();
	        topoSortResult.add(currentNode);
	        
	        for(int neigNode: adjList.get(currentNode)) //traversing over all the neighbours of currentNode
	        {
	            if(--inDegree[neigNode] == 0) {
	                queue.add(neigNode);
	            }
	        }
	    }
	    return topoSortResult;
    }
}