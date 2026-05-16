/* https://leetcode.com/problems/powx-n/description/
Implement pow(x, n), which calculates x raised to the power n  
Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25 */

class Solution {
    public double myPow(double x, int n) {
        return getXPowerN(x, (long)n);
    }
    private double getXPowerN(double x, long n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;

        if(n <0) { // n is negative
            return getXPowerN(1/x, -n);
        }
        if(n % 2 == 0) { // n is even
            return getXPowerN(x*x, n/2); // (x^2)^n/2
        } 
        else { // n is odd
            return x * getXPowerN(x*x, (n-1)/2);  // x * (x^2)^(n-1)/2
        }
    }
}
