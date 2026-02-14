/* https://leetcode.com/problems/maximum-subarray/description/
https://www.geeksforgeeks.org/problems/kadanes-algorithm-1587115620/1

Given an integer array nums, find the subarray with the largest sum, and return its sum.
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6. */




// Core Logic:
// Best ending at i = Either Best ending at (i-1) + ith   OR ith element itself
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSumEndingAti = 0;
        int maxSumGlobal = Integer.MIN_VALUE; //overall Global Max SubArray Sum
            
        for(int i=0; i<n; i++)
        {
            // option1 = maxSumEndingAti + nums[i]
            // option2 = nums[i]
            maxSumEndingAti = maxSumEndingAti + nums[i];
            maxSumEndingAti = Math.max(maxSumEndingAti, nums[i]);  
            
            maxSumGlobal = Math.max(maxSumEndingAti, maxSumGlobal);
        }
        return maxSumGlobal;
    }
}