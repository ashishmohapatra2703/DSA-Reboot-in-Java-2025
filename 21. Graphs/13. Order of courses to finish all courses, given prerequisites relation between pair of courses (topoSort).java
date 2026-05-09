/* https://leetcode.com/problems/course-schedule-ii/ 
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that 
you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. 
If there are many valid answers, return any of them. 
If it is impossible to finish all courses, return an empty array.

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished 
both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3]. */

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // adjacency list
        ArrayList<ArrayList<Integer>> adjList = buildAdjList(numCourses, prerequisites);
        // inDegree list of each Node
        int[] inDegree = new int[numCourses];
        buildInDegreeList(numCourses, adjList, inDegree);
        // live Queue and result
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> topoSortResult = new ArrayList<>();
        
        topoSortBFSUtil(numCourses, adjList, inDegree, queue, topoSortResult);

        // All nodes were processed → graph is DAG → NO cycle
        if(topoSortResult.size() == numCourses) 
            return topoSortResult.stream().mapToInt(Integer::intValue).toArray();
        return new int[]{}; //else
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