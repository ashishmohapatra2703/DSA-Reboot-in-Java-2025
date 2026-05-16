/* https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/
You are given an integer array nums. 
The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is 
abs(numsl + numsl+1 + ... + numsr-1 + numsr).
Return the maximum absolute sum of any (possibly empty) subarray of nums.

Note that abs(x) is defined as follows:
If x is a negative integer, then abs(x) = -x.
If x is a non-negative integer, then abs(x) = x.

Input: nums = [2,-5,1,-4,3,-2]
Output: 8
Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.*/ 

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int len = nums.length;
        return Math.max(maxSubArraySum(nums, len), Math.abs(minSubArraySum(nums, len)));
    }

    private int maxSubArraySum(int[] nums, int n) {
        int maxSumEndingAti = 0;
        int maxSumGlobal = Integer.MIN_VALUE;
            
        for(int i=0; i<n; i++)
        {
            maxSumEndingAti = maxSumEndingAti + nums[i];
            maxSumEndingAti = Math.max(maxSumEndingAti, nums[i]);  
            
            maxSumGlobal = Math.max(maxSumEndingAti, maxSumGlobal);
        }
        return maxSumGlobal;
    }
    private int minSubArraySum(int[] nums, int n) {
        int minSumEndingAti = 0;
        int minSumGlobal = Integer.MAX_VALUE; 
            
        for(int i=0; i<n; i++)
        {
            minSumEndingAti = minSumEndingAti + nums[i];
            minSumEndingAti = Math.min(minSumEndingAti, nums[i]);  
            
            minSumGlobal = Math.min(minSumEndingAti, minSumGlobal);
        }
        return minSumGlobal;
    }
} 