/* https://leetcode.com/problems/rotate-image/ 
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
DO NOT allocate another 2D matrix and do the rotation.

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]  */

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        doTransposeInPlace(matrix, n);
        doReverseEachRow(matrix, n);
    }

    private void doTransposeInPlace(int[][] matrix, int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    private void doReverseEachRow(int[][] matrix, int n) {
        for(int i=0; i<n; i++) {
            int l = 0;
            int h = n-1;
            while(l < h) {
                swap(matrix, i, l, i, h);
                l++;
                h--;
            }
        }
    }

    private void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}