/* https://leetcode.com/problems/maximum-length-of-pair-chain/description/
https://www.geeksforgeeks.org/problems/maximum-length-chain-of-pairs/1

You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. 
A chain of pairs can be formed in this fashion.
Return the length longest chain which can be formed.
You do not need to use up all the given intervals. You can select pairs in any order.

Input: pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8]. */

// LIS template
class Solution {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a,b) -> Integer.compare(a[0], b[0])); //sort by pair's 1st element

        int[] dp = new int[n]; //max len of pair chain ending at i
        Arrays.fill(dp, 1);
        int maxLenLIS = 1;

        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(pairs[j][1] < pairs[i][0]) { //previous.end < current.start
                    dp[i] = Math.max(1+dp[j], dp[i]);
                }
            }
            maxLenLIS = Math.max(maxLenLIS, dp[i]);
        }
        return maxLenLIS;
    }
}