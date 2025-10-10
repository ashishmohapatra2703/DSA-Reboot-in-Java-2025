/* Given an m x n integer matrix matrix, 
if an element is 0, set its entire row and column to 0's.
You must do it in place. S.C = O(1) */

// T.C = O(m*n)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean isAnyFirstRowEleZero = false;
        boolean isAnyFirstColEleZero = false;

        //First Pass: Save the 0th row and 0th column information
        for(int j=0; j<n; j++) {
            if(matrix[0][j] == 0) {
                isAnyFirstRowEleZero = true;
                break;
            }
        }
        for(int i=0; i<m; i++) {
            if(matrix[i][0] == 0) {
                isAnyFirstColEleZero = true;
                break;
            }
        }

        //Second Pass: Use 0th row and 0th column as markers for inner matrix
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0; // that row start ele marked
                    matrix[0][j] = 0; // that col start ele marked
                }
            }
        }

        //Third Pass: Using markers, update the inner matrix elements
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0; 
                }
            }
        }

        //Fourth Pass: set 0th row & 0th col elements checking from original saved information
        if(isAnyFirstRowEleZero) {
            for(int j=0; j<n; j++) {
                matrix[0][j] = 0;
            }
        }
        if(isAnyFirstColEleZero) {
            for(int i=0; i<m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}