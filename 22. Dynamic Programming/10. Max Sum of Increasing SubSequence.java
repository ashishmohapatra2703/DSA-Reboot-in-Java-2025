/* https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1 
Given an array of positive integers arr[], find the maximum sum of a subsequence such that the elements of the subsequence form a strictly increasing sequence.
In other words, among all strictly increasing subsequences of the array, return the one with the largest possible sum.

Input: arr[] = [1, 101, 2, 3, 100]
Output: 106
Explanation: The maximum sum of an increasing sequence is obtained from [1, 2, 3, 100]. */

class Solution {
    public int maxSumIS(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n]; //dp[i] = MaxSum Of Increasing SubSequence Ending at ith index
        int maxSumISS = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            dp[i] = arr[i]; //single element of that index = SS itself == base case
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) //stricly increasing
                {
                    int takeJ = arr[i] + dp[j];
                    int skipJ = dp[i];
                    dp[i] = Math.max(takeJ, skipJ);
                }
            }
            maxSumISS = Math.max(maxSumISS, dp[i]);
        }

        return maxSumISS;
    }
}