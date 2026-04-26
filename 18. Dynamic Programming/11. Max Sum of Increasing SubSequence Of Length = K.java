/*https://www.geeksforgeeks.org/problems/maximum-sum-subsequence-of-length-k3053/1
Given an array sequence [A1 , A2 ...An], the task is to find the 
maximum possible sum of increasing subsequence S of length K such that Si1<=Si2<=Si3.........<=Sin.
 
Input:
N = 8 K = 3
A[] = {8 5 9 10 5 6 19 8}
Output: 38
Explanation:
Possible increasing subsequence of
length 3 with maximum possible
sum is 9 10 19. */

class Solution {
    public int max_sum(int[] arr, int K) {
        int n = arr.length;
        int[][] dp = new int[n][K+1]; //dp[i] = MaxSum Of Increasing SubSequence Ending at ith index, having len = K
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        int maxSumISSofLenK = Integer.MIN_VALUE;

        //dp[i][0] is not used at all
        for(int i=0; i<n; i++) {
            dp[i][1] = arr[i]; //single element of that index = SS itself of len=1 => base case
            for(int j=0; j<i; j++) {
                if(arr[j] <= arr[i]) //not stricly increasing
                {
                    for(int k=2; k<=K; k++) {
                        if(dp[j][k-1] != -1) { 
                            int takeJ = arr[i] + dp[j][k-1];
                            int skipJ = dp[i][k];
                            dp[i][k] = Math.max(takeJ, skipJ);
                        }
                    }
                }
            }
            maxSumISSofLenK = Math.max(maxSumISSofLenK, dp[i][K]);
        }

        return maxSumISSofLenK;
    }
}
