/* https://leetcode.com/problems/coin-change-ii/ 
You are given an integer array coins representing coins of different denominations and 
an integer amount representing a total amount of money.
Return the number of combinations that make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1  */

class Solution {
    public int change(int amount, int[] coins) {
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
            return 1;
        if (amountRemainingSoFarTo0 < 0 || i == n)
            return 0;
        
        // dp[i][remaining] = total num of ways of using coins[i..n-1] to make `remaining`
        if(dp[i][amountRemainingSoFarTo0] != -1)
            return dp[i][amountRemainingSoFarTo0];

        int takeIthCoinAndRepeat = minCoinUnBoundedSubSetToSumK(coins, i, n, amountRemainingSoFarTo0-coins[i], dp);
        int SkipIthCoin =  minCoinUnBoundedSubSetToSumK(coins, i+1, n, amountRemainingSoFarTo0, dp);

        return dp[i][amountRemainingSoFarTo0] = takeIthCoinAndRepeat + SkipIthCoin;
    }
}