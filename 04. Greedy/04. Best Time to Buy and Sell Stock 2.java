class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profitBeingGreedy = 0;
        // Summing all positive day-to-day increases always gives the maximum global profit.

        for(int i=1; i<n; i++) {
            if(prices[i] > prices[i-1])
                profitBeingGreedy += prices[i] - prices[i-1]; 
        }
        return profitBeingGreedy;
    }
}