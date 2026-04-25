/*https://leetcode.com/problems/house-robber-ii/
https://www.geeksforgeeks.org/problems/house-robber-ii/1

You are given an array arr[] which represents houses arranged in a circle, 
where each house has a certain value. 
A thief aims to maximize the total stolen value without robbing two adjacent houses.
Determine the maximum amount the thief can steal.
Note: Since the houses are in a circle, the first and last houses are also considered adjacent.

Input: arr[] = [2, 3, 2]
Output: 3
Explanation: arr[0] and arr[2] can't be robbed because they are adjacent houses. Thus, 3 is the maximum value thief can rob.
Input: arr[] = [1, 2, 3, 1]
Output: 4
Explanation: Maximum stolen value: arr[0] + arr[2] = 1 + 3 = 4
Input: arr[] = [2, 2, 3, 1, 2]
Output: 5
Explanation: Maximum stolen value: arr[0] + arr[2] = 2 + 3 = 5 or arr[2] + arr[4] = 3 + 2 = 5 */

//M-1 using Housing Robber 1
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1)
            return nums[0];

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int startFrom0 = maxMoneyRob(nums, 0, n-2, dp);
        Arrays.fill(dp, -1);
        int startFrom1 = maxMoneyRob(nums, 1, n-1, dp);

        return Math.max(startFrom0, startFrom1);
    }
    private int maxMoneyRob(int[] nums, int startIdx, int endIdx, int[] dp) {
        if(startIdx > endIdx)
            return 0;
        if(dp[startIdx] != -1) 
            return dp[startIdx];

        int optionSkip = maxMoneyRob(nums, startIdx+1, endIdx, dp);
        int optionSteal = nums[startIdx] + maxMoneyRob(nums, startIdx+2, endIdx, dp); //skip adjacent => i+2
        return dp[startIdx] = Math.max(optionSkip, optionSteal);
    }
}

//M-2 
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1)
            return nums[0];
            
        int[] dp = new int[n]; //dp[i] = max loot till ith index house 
        Arrays.fill(dp, -1);

        int maxLootStartFrom0thIdxEndAtNminus2Idx = 0; 
        dp[0] = nums[0];
        for(int i=1; i<=n-2; i++) {
            int optionSkip = dp[i-1];
            int optionSteal = nums[i] + ((i-2>=0) ? dp[i-2] : 0);

            dp[i] = Math.max(optionSkip, optionSteal);
        }
        maxLootStartFrom0thIdxEndAtNminus2Idx = dp[n-2]; //max loot till (n-2)th / last index house 

        Arrays.fill(dp, -1);
        int maxLootStartFrom1stIdxEndAtNminus1Idx = 0; 
        dp[0] = 0; //not num[0]
        dp[1] = nums[1];
        for(int i=2; i<=n-1; i++) {
            int optionSkip = dp[i-1];
            int optionSteal = nums[i] + dp[i-2];

            dp[i] = Math.max(optionSkip, optionSteal);
        }
        maxLootStartFrom1stIdxEndAtNminus1Idx = dp[n-1]; //max loot till (n-2)th / last index house 

        return Math.max(maxLootStartFrom0thIdxEndAtNminus2Idx, maxLootStartFrom1stIdxEndAtNminus1Idx);
    }
}