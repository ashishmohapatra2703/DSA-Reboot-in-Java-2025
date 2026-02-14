/* https://leetcode.com/problems/maximum-product-subarray/description/
https://www.geeksforgeeks.org/problems/maximum-product-subarray3604/1
Given an integer array nums, find a subarray that has the largest product, 
and return the product.

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6. */

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProductEndingAti = 1;
        int minProductEndingAti = 1;
        int maxProductGlobal = Integer.MIN_VALUE; //overall Global Max SubArray Product
            
        for(int i=0; i<n; i++)
        {
            int option1 = maxProductEndingAti * nums[i]; // +ve
            int option2 = minProductEndingAti * nums[i]; 
            // minProductEndingAti can be -ve 
            // => after multiplying to -ve nums[i] 
            // => option2 can give us a better maxProductEndingAti
            int option3 = nums[i];
            
            maxProductEndingAti = Math.max(Math.max(option1, option2), option3);  
            minProductEndingAti = Math.min(Math.min(option1, option2), option3);  
            
            maxProductGlobal = Math.max(maxProductEndingAti, maxProductGlobal);
        }
        return maxProductGlobal;
    }
}