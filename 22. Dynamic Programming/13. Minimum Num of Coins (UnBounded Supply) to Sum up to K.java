/*https://leetcode.com/problems/coin-change/description/ 

You are given an integer array coins representing coins of different denominations and 
an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1   */ 

class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int countCoins = 0;
        
        int[][] dp = new int[n][amount+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = minCoinUnBoundedSubSetToSumK(coins, 0, n, amount, dp);
        return (result==Integer.MAX_VALUE) ? -1 : result;
    }
    private int minCoinUnBoundedSubSetToSumK(int[] coins, int i, int n, int amountRemainingSoFarTo0, int[][] dp) {
        if (amountRemainingSoFarTo0 == 0)
            return 0;
        if (amountRemainingSoFarTo0 < 0 || i == n)
            return Integer.MAX_VALUE;
        
        // dp[i][remaining] = min coins needed using coins[i..n-1] to make `remaining`
        if(dp[i][amountRemainingSoFarTo0] != -1)
            return dp[i][amountRemainingSoFarTo0];

        int optionToTakeIthCoinAndRepeat = minCoinUnBoundedSubSetToSumK(coins, i, n, amountRemainingSoFarTo0-coins[i], dp);
        int takeIthCoinAndRepeat = Integer.MAX_VALUE;
        if(optionToTakeIthCoinAndRepeat != Integer.MAX_VALUE)
            takeIthCoinAndRepeat = 1 + optionToTakeIthCoinAndRepeat;
        int SkipIthCoin =  minCoinUnBoundedSubSetToSumK(coins, i+1, n, amountRemainingSoFarTo0, dp);
        return dp[i][amountRemainingSoFarTo0] = Math.min(takeIthCoinAndRepeat, SkipIthCoin);
    }
}