/*https://leetcode.com/problems/longest-increasing-subsequence/description/
https://www.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1

Given an integer array nums, return the 
length of the longest strictly increasing subsequence.

Input: arr[] = [5, 8, 3, 7, 9, 1]
Output: 3
Explanation: The longest strictly increasing subsequence could be [5, 7, 9], which has a length of 3.
Input: arr[] = [10, 6, 3, 11, 7, 15]
Output: 3
Explanation: One of the possible longest strictly increasing subsequences is [10, 11, 15], which has a length of 3. */

//M-1
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return lenOfLIS(nums, 0, -1, n, dp);
    }

    private int lenOfLIS(int[] nums, int i, int prevIdx, int n, int[][] dp) {
        if(i == n)
            return 0;

        if(prevIdx != -1 && dp[i][prevIdx] != -1)
            return dp[i][prevIdx];

        int takeIth = 0;
        if(prevIdx == -1 || nums[prevIdx] < nums[i])
            takeIth = 1 + lenOfLIS(nums, i+1, i, n, dp);
        int skipIth = lenOfLIS(nums, i+1, prevIdx, n, dp);

        if(prevIdx != -1)
            dp[i][prevIdx] = Math.max(takeIth, skipIth);
        return Math.max(takeIth, skipIth);
    }
}

//M-2
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; //dp[i] = max len LIS ending at ith index
        Arrays.fill(dp, 1); //single element of that index = SS itself
        int maxLIS = 1; //base answer anyways

        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i])  //increasing SS left to right
                {
                    int takeJ = 1 + dp[j];
                    int skipJ = dp[i];
                    dp[i] = Math.max(takeJ, skipJ);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        return maxLIS;
    }
}