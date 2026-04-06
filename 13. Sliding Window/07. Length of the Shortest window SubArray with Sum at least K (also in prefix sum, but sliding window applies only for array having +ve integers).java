/*https://leetcode.com/problems/minimum-size-subarray-sum/description/
https://www.geeksforgeeks.org/problems/smallest-subarray-with-sum-greater-than-x5651/1

Given an array of positive integers nums and a positive integer target, 
return the minimal length of a subarray whose sum is greater than or equal to target. 
If there is no such subarray, return 0 instead.

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.  */

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int minLenSubArrSumAtLeastK = Integer.MAX_VALUE;
        int sumOfSubArr = 0;
        int subArrLen = 0;

        while(i < n) {
            //add i
            sumOfSubArr += nums[i];
            if(sumOfSubArr < target) {
                i++;
            }
            else if (sumOfSubArr >= target) {
                while(sumOfSubArr >= target) {
                    subArrLen = (i-j+1);
                    minLenSubArrSumAtLeastK = Math.min(minLenSubArrSumAtLeastK, subArrLen);
                    //remove j
                    sumOfSubArr -= nums[j]; 
                    j++;
                }
                i++;
            }
        }

        return (minLenSubArrSumAtLeastK!=Integer.MAX_VALUE) ? minLenSubArrSumAtLeastK : 0;
    }
}