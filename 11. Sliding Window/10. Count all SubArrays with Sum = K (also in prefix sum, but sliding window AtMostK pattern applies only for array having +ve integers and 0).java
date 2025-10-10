/* https://leetcode.com/problems/binary-subarrays-with-sum/description/
Given a binary array nums and an integer goal, 
return the number of non-empty subarrays with a sum goal.
A subarray is a contiguous part of the array.

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Input: nums = [0,0,0,0,0], goal = 0
Output: 15 */

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return countSubArrSumAtMostK(nums, goal) - countSubArrSumAtMostK(nums, goal-1);
    }

    private int countSubArrSumAtMostK(int[] nums, int k) {
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int sumOfSubArr = 0;
        int countSubArrSumAtMostK = 0;

        while(i<n)
        {
            sumOfSubArr += nums[i];

            if(sumOfSubArr <= k) { 
                // at any i, countSubArrSumAtMostK = window_size 
                // because all subarrays starting anywhere between j and i are valid for sumAtMostK 
                // [j...i], [j+1...i], ..., [i...i]
                countSubArrSumAtMostK += (i-j+1);
                i++;
            } else if(sumOfSubArr > k) {
                while(sumOfSubArr > k && j <= i)
                {
                    sumOfSubArr -= nums[j];
                    j++;    
                }
                if(sumOfSubArr == k) {
                    countSubArrSumAtMostK += (i-j+1);
                }
                i++;
            }
        }

        return countSubArrSumAtMostK;
    }
}