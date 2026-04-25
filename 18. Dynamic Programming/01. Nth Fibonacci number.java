/* https://leetcode.com/problems/fibonacci-number/description/
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, 
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n). */

//M-1 Top Down
class Solution {
    public int fib(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return fibonacci(n, dp);
    }
    private int fibonacci(int n, int[] dp) {
        if(n==0 || n==1)
            return dp[n] = n;
        if(dp[n] != -1) 
            return dp[n];
        
        return dp[n] = fibonacci(n-1, dp) + fibonacci(n-2, dp);
    }
}

//M-2 Bottom Up
class Solution {
    public int fib(int n) {
        if(n==0 || n==1)
            return n;

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}

//M-3 Bottom Up with S.C = O(1)
class Solution {
    public int fib(int n) {
        if(n==0 || n==1)
            return n;
        
        int nMinus2th = 0;
        int nMinus1th = 1;
        int nth = 0;
        for(int i=2; i<=n; i++) {
            nth = nMinus1th + nMinus2th;
            nMinus2th = nMinus1th;
            nMinus1th = nth;
        }

        return nth;
    }
}