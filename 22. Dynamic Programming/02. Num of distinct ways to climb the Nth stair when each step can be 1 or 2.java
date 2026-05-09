/* https://leetcode.com/problems/climbing-stairs/description/

You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step */

//Same as fibonacci
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return climbStairs(n, dp);
    }
    private int climbStairs(int n, int[] dp) {
        if(n<=2)
            return n;
        if(dp[n] != -1) 
            return dp[n];
        
        return dp[n] = climbStairs(n-1, dp) + climbStairs(n-2, dp);
    }
}