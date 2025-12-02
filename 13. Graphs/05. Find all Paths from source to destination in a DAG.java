/* https://leetcode.com/problems/all-paths-from-source-to-target/description/
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
find all possible paths from node 0 to node n - 1 and return them in any order.
The graph is given as follows: graph[i] is a list of all nodes you can visit from node i 
(i.e., there is a directed edge from node i to node graph[i][j]).

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]  */

// here given graph is a DAG, so visited[] is not required
// We should rely purely on pathSoFar and backtracking.
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> allPathsResult = new ArrayList<>();
        int V = graph.length;
        boolean[] visited = new boolean[V];

        ArrayList<Integer> pathSoFar = new ArrayList<>();
        pathSoFar.add(0);

        getAllPathsFromSrcToDest(graph, visited, 0, V-1, allPathsResult, pathSoFar);
        return allPathsResult;
    }
    private void getAllPathsFromSrcToDest(int[][] adjList, boolean[] visited, 
                                    int fromSrc, int toDest, 
                                    List<List<Integer>> allPaths, ArrayList<Integer> pathSoFar) {
        if(fromSrc == toDest) {
            allPaths.add(new ArrayList<>(pathSoFar));
            return;
        }

        visited[fromSrc] = true;
        for(int srcNeighNode: adjList[fromSrc]) {
            if(!visited[srcNeighNode]) {
                pathSoFar.add(srcNeighNode);
                getAllPathsFromSrcToDest(adjList, visited, srcNeighNode, toDest, allPaths, pathSoFar);
                pathSoFar.remove(pathSoFar.size()-1);
            }
        }
        visited[fromSrc] = false;
    }
}