/* https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, 
check whether it contains any cycle or not.
The graph is represented as a 2D vector edges[][], 
where each entry edges[i] = [u, v] denotes an edge from verticex u to v.

Input: V = 4, edges[][] = [[0, 1], [1, 2], [2, 0], [2, 3]]
Output: true
Explanation: The diagram clearly shows a cycle 0 → 1 → 2 → 0*/

//M-1 using DFS
class Solution {
    public boolean isCyclic(int V, int[][] edges) 
    {
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        boolean[] visited = new boolean[V];
        boolean[] isAncestor = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i])
                if(isCyclePresent(adjList, visited, isAncestor, i))
                    return true;
        }
        return false;
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
    
    /* Logic:- For each node:
            For each neighbor:
                1. If neighbor is unvisited → DFS(neighbor)
                   If DFS returns true → cycle exists.
        
                2. If neighbor is already in the CURRENT recursion path 
                   (i.e., recStack[neighbour] == true)
                   → cycle exists (back edge). */
    private boolean isCyclePresent(ArrayList<ArrayList<Integer>> adjList, 
                                    boolean[] visited, boolean[] isAncestor, int currentNode)
    {
        visited[currentNode] = true;
        isAncestor[currentNode] = true;
        
        for(int nearNode: adjList.get(currentNode))
        {
            if(!visited[nearNode]) {
                if(isCyclePresent(adjList, visited, isAncestor, nearNode))
                    return true;
            } else if (visited[nearNode] == true && isAncestor[nearNode] == true) {
                return true;
            }
        }
        
        isAncestor[currentNode] = false;
        return false;
    }
}

//M-2 using BFS
/* Just add this line in code of topo sort using BFS
// return (topoSortResult.size() == V) ? false : true; 

Topo sort = peeling layers of nodes with indegree 0
Cycle = nodes that can never be peeled */
class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        // adjacency list
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(V, edges);
        // inDegree list of each Node
        int[] inDegree = new int[V];
        buildInDegreeList(V, adjList, inDegree);
        // live Queue and result
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> topoSortResult = new ArrayList<>();
        
        topoSortBFSUtil(V, adjList, inDegree, queue, topoSortResult);
        // All nodes were processed → graph is DAG → NO cycle
        return (topoSortResult.size() == V) ? false : true;
    }
    
    private ArrayList<ArrayList<Integer>> buildAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v); //u -> v
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