/* https://leetcode.com/problems/01-matrix/
https://leetcode.com/problems/map-of-highest-peak/description/ TODO 

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
The distance between two cells sharing a common edge is 1.

Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]] */

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // for each (1) cell, ditance of nearest 0 ==
        // for each (0) cell, distance of nearest 1
        int m = mat.length;
        int n = mat[0].length;
        int[][] distanceFromCurr0ToNearest1 = new int[m][n];

        Queue<int[]> que = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j] == 0) {
                    distanceFromCurr0ToNearest1[i][j] = 0; // distance from 0 to 0 = 0
                    que.add(new int[]{i, j}); // add ALL the 0th cell co-ordinates
                } else if(mat[i][j] == 1) {
                    distanceFromCurr0ToNearest1[i][j] = -1; // to find in bfs (mark of unvisited)
                }
            }
        }

        while(!que.isEmpty()) {
            int[] currCell = que.poll(); //pop a 0th cell + put distance to its 1 cell neighbours on neighbour's cell (1's)
            int currCellI = currCell[0];
            int currCellJ = currCell[1];

            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}}; // right, left, up, down
            for(int[] dir: directions) {
                int newI = currCellI + dir[0];
                int newJ = currCellJ + dir[1];

                if(newI >= 0 && newI < m && newJ >=0 && newJ < n &&
                                            distanceFromCurr0ToNearest1[newI][newJ] == -1) {
                        // change the result matrix cell value, added distance
                        distanceFromCurr0ToNearest1[newI][newJ] = 1 + distanceFromCurr0ToNearest1[currCellI][currCellJ];
                        // add in queue to propagate
                        que.add(new int[]{newI, newJ});
                    }
            }
        }

        return distanceFromCurr0ToNearest1;
    }
}