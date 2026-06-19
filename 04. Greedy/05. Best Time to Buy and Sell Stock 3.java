/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/1992863306/ 

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously 
(i.e., you must sell the stock before you buy again).

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, 
as you are engaging multiple transactions at the same time. 
You must sell before buying again. */

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int minBuyPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = Integer.MIN_VALUE;
        int minBuyPrice2 = Integer.MAX_VALUE;
        int maxProfit2 = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            minBuyPrice1 = Math.min(minBuyPrice1, prices[i]);
            int profit1AtSellingToday = prices[i] - minBuyPrice1; 
            maxProfit1 = Math.max(maxProfit1, profit1AtSellingToday); //Max profit from 1st transaction

            int buyPrice2EffectiveAfterMaxProfit1 = prices[i] - maxProfit1; //use 1st (max) profit to offset next buy
            minBuyPrice2 = Math.min(minBuyPrice2, buyPrice2EffectiveAfterMaxProfit1); //2nd buy depends on profit from 1st transaction
            int profit2AtSellingToday = prices[i] - minBuyPrice2; 
            maxProfit2 = Math.max(maxProfit2, profit2AtSellingToday); //best total profit after 2 transactions
        }
        return maxProfit2;
    }
}