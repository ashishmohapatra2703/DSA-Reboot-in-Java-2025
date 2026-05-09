/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/1992863306/ */

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