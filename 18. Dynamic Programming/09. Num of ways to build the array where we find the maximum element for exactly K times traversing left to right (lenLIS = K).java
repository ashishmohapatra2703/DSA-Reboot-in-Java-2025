/* https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/ */

class Solution {
    private final int mod = 1000000007;
    public int numOfArrays(int n, int m, int k) {
        Integer[][][] dp = new Integer[n][m+1][k+1];
        return findNumOfArrays(0, 0, 0, n, m, k, dp);
    }
    private int findNumOfArrays(int idx, int maxValueSoFar, int searchCost, int n, int m, int k, Integer[][][] dp) {
        if(idx == n) {
            if(searchCost == k)
                return 1;
            else 
                return 0;
        }
        if(searchCost > k)
            return 0;


        if(dp[idx][maxValueSoFar][searchCost] != null)
            return dp[idx][maxValueSoFar][searchCost];


        int totalNumOfPossibleArrays = 0;
        for(int num=1; num<=m; num++) {
            if(num > maxValueSoFar) // as per given algorithm
                totalNumOfPossibleArrays = (totalNumOfPossibleArrays + findNumOfArrays(idx+1, num, searchCost+1, n, m, k, dp)) % mod;
            else 
                totalNumOfPossibleArrays = (totalNumOfPossibleArrays + findNumOfArrays(idx+1, maxValueSoFar, searchCost, n, m, k, dp)) % mod;
        }
        return dp[idx][maxValueSoFar][searchCost] = totalNumOfPossibleArrays % mod;
    }
}

