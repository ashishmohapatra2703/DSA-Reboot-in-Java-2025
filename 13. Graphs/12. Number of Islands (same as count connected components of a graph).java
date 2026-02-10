/*https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1
https://leetcode.com/problems/number-of-islands/description/
Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid) 
consisting of 'W's (Water) and 'L's (Land). Find the number of islands.
Note: An island is either surrounded by water or the boundary of a grid and is formed by 
connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.

Input: grid[][] = [['L', 'L', 'W', 'W', 'W'], 
                ['W', 'L', 'W', 'W', 'L'], 
                ['L', 'W', 'W', 'L', 'L'], 
                ['W', 'W', 'W', 'W', 'W'], 
                ['L', 'W', 'L', 'L', 'W']]
Output: 4                                         */

class Solution {
    public int countIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int countIslands = 0;
        boolean[][] visited = new boolean[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visited[i][j] && grid[i][j] == 'L') {
                    dfsUtil(grid, visited, i, j);
                    countIslands++;
                }
            }
        }
        return countIslands;
    }

    private void dfsUtil(char[][] grid, boolean[][] visited, int i, int j) 
    {
        visited[i][j] = true;
    
        // visit all neighbours // north, east, west, south + 4 diagonals
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i-1, j))                 
            dfsUtil(grid, visited, i-1, j);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i, j+1))                 
            dfsUtil(grid, visited, i, j+1);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i, j-1))                 
            dfsUtil(grid, visited, i, j-1);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i+1, j))                 
            dfsUtil(grid, visited, i+1, j);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i-1, j-1))                 
            dfsUtil(grid, visited, i-1, j-1);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i-1, j+1))                 
            dfsUtil(grid, visited, i-1, j+1);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i+1, j-1))                 
            dfsUtil(grid, visited, i+1, j-1);
        if(isNeighbourValidIdxNotVisitedNotWater(grid, visited, i+1, j+1))                 
            dfsUtil(grid, visited, i+1, j+1);
    }

    private boolean isNeighbourValidIdxNotVisitedNotWater(char[][] grid, boolean[][] visited, int nextRidx, int nextCidx) {
        int n = grid.length;
        int m = grid[0].length;

        return (nextRidx >= 0 && nextRidx <= n-1 &&
                nextCidx >=0 && nextCidx <= m-1 &&
                !visited[nextRidx][nextCidx] && grid[nextRidx][nextCidx] == 'L');
    }
}