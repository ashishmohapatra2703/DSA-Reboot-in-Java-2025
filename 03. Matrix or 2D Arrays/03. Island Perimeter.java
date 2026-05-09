/* https://leetcode.com/problems/island-perimeter/ 
You are given row x col grid representing a map where 
grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island 
(i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. 
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
Determine the perimeter of the island.

Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16 */

//M-1 DFS
class Solution {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length ;
        int c = grid[0].length ;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(grid[i][j] == 1) {
                    //any first 1 found, return
                    return countPerimeterDFS(grid, i, j, r, c);
                }
            }
        }
        return 0;
    }

    private int countPerimeterDFS(int[][] grid, int i, int j, int r, int c) {
        if(i<0 || i>=r || j<0 || j>=c || grid[i][j]==0) {
            return 1;
        }
        if(grid[i][j] == -1)
            return 0;
            
        grid[i][j] = -1; //mark visited
        return countPerimeterDFS(grid, i+1, j, r, c) +
        countPerimeterDFS(grid, i-1, j, r, c) +
        countPerimeterDFS(grid, i, j+1, r, c) +
        countPerimeterDFS(grid, i, j-1, r, c);
    }
}

//M-2 BFS
class Solution {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length ;
        int c = grid[0].length ;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(grid[i][j] == 1) {
                    //any first 1 found, return
                    return countPerimeterBFS(grid, i, j, r, c);
                }
            }
        }
        return 0;
    }
    private int countPerimeterBFS(int[][] grid, int i, int j, int r, int c) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{i, j});
        grid[i][j] = -1;
        int perimeter = 0;

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while(!que.isEmpty()) {
            int[] currCell = que.poll();

            for(int[] dir: directions) {
                int newI = currCell[0] + dir[0];
                int newJ = currCell[1] + dir[1];

                if(newI<0 || newI>=r || newJ<0 || newJ>=c || grid[newI][newJ]==0) {
                    perimeter++;
                }
                else if(grid[newI][newJ] == -1) {
                    continue;
                } else {
                    que.add(new int[]{newI, newJ});
                    grid[newI][newJ] = -1; //mark visited
                }
            }
        }
        return perimeter;
    }
}

//M-3 Greedy (iterative)
class Solution {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length ;
        int c = grid[0].length ;

        int perimeter = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                //for every 1 found, see all 4 directions and count boundary
                if(grid[i][j] == 1) 
                {
                    if(i-1 < 0 || grid[i-1][j] == 0)
                        perimeter++;
                    if(i+1 >= r || grid[i+1][j] == 0)
                        perimeter++;
                    if(j-1 < 0 || grid[i][j-1] == 0)
                        perimeter++;
                    if(j+1 >= c || grid[i][j+1] == 0)
                        perimeter++;
                }
            }
        }
        return perimeter;
    }
}