/* https://leetcode.com/problems/subarray-product-less-than-k/description/
Given an array of integers nums and an integer k, return the number of contiguous subarrays 
where the product of all the elements in the subarray is strictly less than k.

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k. */

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int countSubArrProductLessThanK = 0;
        int currProduct = 1;

        while(i<n)
        {
            currProduct *= nums[i];

            if(currProduct < k) {
                //all subarrays ending at i => [j...i], [j+1...i], ..., [i...i]
                countSubArrProductLessThanK += (i-j+1); 
                i++;
            } else if (currProduct >= k) {
                while(currProduct >= k && j<=i) {
                    currProduct /= nums[j];
                    j++;    
                }
                countSubArrProductLessThanK += (i-j+1); //after currProduct has become < k, out of the while loop
                i++;
            }
        }

        return countSubArrProductLessThanK;
    }
}