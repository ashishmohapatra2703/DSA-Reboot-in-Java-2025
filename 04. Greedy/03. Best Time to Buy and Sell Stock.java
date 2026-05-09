/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ */ 

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int minBuyPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            minBuyPrice = Math.min(prices[i], minBuyPrice);
            int profitAtSellingToday = prices[i] - minBuyPrice; //sell only AFTER buy
            
            maxProfit = Math.max(maxProfit, profitAtSellingToday);
        }
        return maxProfit;
    }
}