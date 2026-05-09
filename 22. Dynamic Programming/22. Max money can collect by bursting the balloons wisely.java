/* https://leetcode.com/problems/burst-balloons/ 
You are given n balloons, indexed from 0 to n - 1. 
Each balloon is painted with a number on it represented by an array nums. 
You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. 
If i - 1 or i + 1 goes out of bounds of the array, 
then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167  */

//MCM Pattern
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return maxCoinsBurstingBallonsInInterval(nums, n, 0, n-1, dp);
    }
    private int maxCoinsBurstingBallonsInInterval(int[] nums, int n, int i, int j, int[][] dp) {
        // Assume nums[k] is the LAST balloon TO BURST in interval [i..j], everything inside is gone, 
        // so its neighbors are the outer boundaries of the interval. 
        // If the interval touches the array edge, we use 1.

        if(i > j) //invalid empty interval / base condition
            return 0;

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int maxCoins = 0;
        for(int k=i; k<=j; k++) { //try out all k possibilities
            int leftToInterval = (i == 0) ? 1 : nums[i-1];
            int rightToInterval = (j+1 == n) ? 1 : nums[j+1];
            int coinsFromBurstingWithKAsLast = maxCoinsBurstingBallonsInInterval(nums, n, i, k-1, dp) 
                        + (leftToInterval * nums[k] * rightToInterval)
                        + maxCoinsBurstingBallonsInInterval(nums, n, k+1, j, dp); 
            
            maxCoins = Math.max(maxCoins, coinsFromBurstingWithKAsLast);
        }
        return dp[i][j] = maxCoins;
    }
}