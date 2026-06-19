/* https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/
Given an integer num, return three consecutive integers 
(as a sorted array) that sum to num. 
If num cannot be expressed as the sum of three consecutive integers, 
return an empty array.

Input: num = 33
Output: [10,11,12]
Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12]. */

class Solution {
    public long[] sumOfThree(long num) {
        /* let the numbers be (n-2) , (n-1) , n
        
          (sum of 1 to n) - (sum of 1 to n-3) = num
        => n * (n+1) /2 - (n-3) * (n-2) / 2 = num
        => n² + n - (n² - 5n + 6) = 2*num
        => 6 (n - 1) = 2*num
        => n = 1 + num/3   
        => num must be a mutiple of 3 */

        if(num % 3 != 0)
            return new long[]{};
        if(num == 0)
            return new long[]{-1, 0, 1}; //base case
        
        long n = 1 + (num / 3);
        return new long[]{n-2, n-1, n};
    }
}