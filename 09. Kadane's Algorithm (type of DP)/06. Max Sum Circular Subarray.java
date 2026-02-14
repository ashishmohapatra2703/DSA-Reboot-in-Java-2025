/* https://leetcode.com/problems/maximum-sum-circular-subarray/description/
https://www.geeksforgeeks.org/problems/max-circular-subarray-sum-1587115620/1
Given a circular integer array nums of length n, 
return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. 
Formally, the next element of nums[i] is nums[(i + 1) % n] and 
the previous element of nums[i] is nums[(i - 1 + n) % n].
A subarray may only include each element of the fixed buffer nums at most once. 
Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], 
there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10. */

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int sumOfWholeArr = 0;
        for(int ele : nums)
            sumOfWholeArr += ele;

        int maxSubArraySumStraight = maxSubArraySum(nums, n);
        int maxSubArraySumCircularWrapped = sumOfWholeArr - minSubArraySum(nums, n);

        if(maxSubArraySumCircularWrapped == 0) {//it means sumOfWholeArr == minSubArraySum, all the elements are -ve
            return maxSubArraySumStraight;
        } else {
            return Math.max(maxSubArraySumStraight, maxSubArraySumCircularWrapped);
        }
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
