/* https://leetcode.com/problems/maximum-alternating-subsequence-sum/description/
The alternating sum of a 0-indexed array is defined as the 
sum of the elements at even indices minus the sum of the elements at odd indices.

For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).

A subsequence of an array is a new array generated from the original array by deleting some elements 
(possibly none) without changing the remaining elements' relative order. 
For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.

Input: nums = [4,2,5,3]
Output: 7
Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.

Input: nums = [6,2,1,2,4,5]
Output: 10
Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10. */

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxAlternatingSum(nums, 0, true, dp);
    }
    private long maxAlternatingSum(int[] nums, int i, boolean isStartIdxOfSSEven, long[][] dp) {
        if(i == nums.length)
            return 0;

        int isEven = isStartIdxOfSSEven ? 1 : 0;
        if(dp[i][isEven] != -1)
            return dp[i][isEven];

        int ithNumValueWithSign = (isStartIdxOfSSEven) ? nums[i] : -nums[i];
        long takeIthIdx = ithNumValueWithSign + maxAlternatingSum(nums, i+1, !isStartIdxOfSSEven, dp);
        long skipIthIdx = maxAlternatingSum(nums, i+1, isStartIdxOfSSEven, dp);

        return dp[i][isEven] = Math.max(takeIthIdx, skipIthIdx);
    }
}