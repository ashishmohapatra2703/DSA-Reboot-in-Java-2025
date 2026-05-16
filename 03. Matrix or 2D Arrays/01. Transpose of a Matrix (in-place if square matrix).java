/* https://leetcode.com/problems/transpose-matrix/description/
Given a 2D integer array matrix, return the transpose of matrix.
The transpose of a matrix is the matrix flipped over its main diagonal, 
switching the matrix's row and column indices.

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]    */

class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if(m == n)
            return getTransposeInPlace(matrix, m);
        else 
            return getTransposeUsingSpaceMxN(matrix, m, n);
    }

    private int[][] getTransposeUsingSpaceMxN(int[][] matrix, int m, int n) {
        int[][] transposeMatrix = new int[n][m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }
        return transposeMatrix;
    }
    private int[][] getTransposeInPlace(int[][] matrix, int m) {
        for(int i=0; i<m; i++) {
            for(int j=0; j<i; j++) {
                int temp = matrix[i][j]; //swap
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp; 
            }
        }
        return matrix;
    }
}