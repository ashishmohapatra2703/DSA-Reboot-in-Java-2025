/* https://leetcode.com/problems/as-far-from-land-as-possible/description/ 

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
find a water cell such that its distance to the nearest land cell is maximized, and return the distance. 
If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: 
the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2. */

class Solution {
    public int maxDistance(int[][] grid) {
        // 0 = water
        // 1 = land
        // find Manhattan distance for ALL water (0) cell to nearest land (1) cell ==
        // find Manhattan distance for ALL land (1) cell to nearest water (0) cell
        // => return the MAX distance
        int m = grid.length;
        int n = grid[0].length;
        int[][] distanceFromCurr1ToNearest0 = new int[m][n];
        int maxDistanceFromAny1ToNearest0 = Integer.MIN_VALUE;

        Queue<int[]> que = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    distanceFromCurr1ToNearest0[i][j] = 0; // distance from 1 to 1 = 0
                    que.add(new int[]{i, j}); // add ALL the 1th cell co-ordinates
                } else if(grid[i][j] == 0) {
                    distanceFromCurr1ToNearest0[i][j] = -1; // to find in bfs (mark of unvisited)
                }
            }
        }

        if (que.isEmpty() || que.size() == m*n) //no land/all water/all 0's OR no water/all land/all 1's
            return -1;

        while(!que.isEmpty()) {
            int[] currCell = que.poll(); //pop a 1th cell + put distance to its 1 cell neighbours on neighbour's cell (1's)
            int currCellI = currCell[0];
            int currCellJ = currCell[1];

            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}}; // right, left, up, down
            for(int[] dir: directions) {
                int newI = currCellI + dir[0];
                int newJ = currCellJ + dir[1];

                if(newI >= 0 && newI < m && newJ >=0 && newJ < n &&
                                            distanceFromCurr1ToNearest0[newI][newJ] == -1) {
                        // change the result matrix cell value, added distance
                        distanceFromCurr1ToNearest0[newI][newJ] = 1 + distanceFromCurr1ToNearest0[currCellI][currCellJ];
                        // add in queue to propagate
                        que.add(new int[]{newI, newJ});
                        // check if it max
                        maxDistanceFromAny1ToNearest0 = 
                            Math.max(maxDistanceFromAny1ToNearest0, distanceFromCurr1ToNearest0[newI][newJ]);
                    }
            }
        }

        return maxDistanceFromAny1ToNearest0;
    }
}