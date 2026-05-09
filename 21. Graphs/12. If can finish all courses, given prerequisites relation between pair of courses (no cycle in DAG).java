/* https://leetcode.com/problems/course-schedule/description/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that 
you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.  */

class Solution {
    // Is Cycle Present in DG
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // adjacency list
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(numCourses, prerequisites);
        // inDegree list of each Node
        int[] inDegree = new int[numCourses];
        buildInDegreeList(numCourses, adjList, inDegree);
        // live Queue and result
        Queue<Integer> queue = new ArrayDeque<>();
        return topoSortBFSUtilCanCoverAllNodes(numCourses, adjList, inDegree, queue);
    }
    
    private ArrayList<ArrayList<Integer>> buildAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        
        //prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
        // edge = bi -> ai (direction)
        for(int[] e : edges) {
            int a = e[0];
            int b = e[1];
            adj.get(b).add(a); 
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
    
    private boolean topoSortBFSUtilCanCoverAllNodes(int V, ArrayList<ArrayList<Integer>> adjList, 
                                                            int[] inDegree, Queue<Integer> queue)
    {
        for(int i=0; i<V; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);   //pushing all the vertices which has inDegree==0 in queue // starting element in result
            }
        }
        

        int countProcessNodes = 0;
        while(!queue.isEmpty())
        {
            int currentNode = queue.poll();
            countProcessNodes++;
            
            for(int neigNode: adjList.get(currentNode)) //traversing over all the neighbours of currentNode
            {
                if(--inDegree[neigNode] == 0) {
                    queue.add(neigNode);
                }
            }
        }
        return (countProcessNodes == V) ? true : false;
    }
}