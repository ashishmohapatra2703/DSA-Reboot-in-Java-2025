/* https://leetcode.com/problems/house-robber/description/
https://www.geeksforgeeks.org/problems/stickler-theif-1587115621/1

Stickler the thief wants to loot money from the houses arranged in a line. 
He cannot loot two consecutive houses and aims to maximize his total loot.
Given an array, arr[] where arr[i] represents the amount of money in the i-th house. 
Determine the maximum amount he can loot.

Input: arr[] = [6, 7, 1, 3, 8, 2, 4]
Output: 19
Explanation: Maximum amount he can get by looting 1st, 3rd, 5th and 7th house, which is 6 + 1 + 8 + 4 = 19.
Input: arr[] = [5, 3, 4, 11, 2]
Output: 16
Explanation: Maximum amount he can get by looting 1st and 4th house, which is 5 + 11 = 16. */

//M-1
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return maxMoneyRob(nums, 0, n, dp);
    }
    private int maxMoneyRob(int[] nums, int i, int n, int[] dp) {
        if(i>=n)
            return 0;
        if(dp[i] != -1) 
            return dp[i];

        int optionSkip = maxMoneyRob(nums, i+1, n, dp);
        int optionSteal = nums[i] + maxMoneyRob(nums, i+2, n, dp); //skip adjacent => i+2
        return dp[i] = Math.max(optionSkip, optionSteal);
    }
}

//M-2
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1)
            return nums[0];
            
        int[] dp = new int[n]; //dp[i] = max loot till (i)th index house 
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2; i<n; i++) {
            int optionSkip = dp[i-1];
            int optionSteal = nums[i] + dp[i-2];

            dp[i] = Math.max(optionSkip, optionSteal);
        }
        return dp[n-1]; //max loot till (n-1)th / last index house 
    }
}