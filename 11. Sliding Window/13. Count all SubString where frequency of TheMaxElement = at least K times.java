/* https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/
You are given an integer array nums and a positive integer k.
Return the number of subarrays where 
the maximum element of nums appears at least k (>=k) times in that subarray.
A subarray is a contiguous sequence of elements within an array.

Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: 
[1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3]. */

class Solution {
    public long countSubarrays(int[] nums, int k) {
        long maxEle = Long.MIN_VALUE;
        for(int num: nums) {
            maxEle = Math.max(num, maxEle);
        }

        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int maxEleFreq = 0;
        long countSubArrMaxEleFreqAtLeastK = 0;

        while(i<n)
        {
            if(nums[i] == maxEle)
                maxEleFreq++;

            if(maxEleFreq < k) { 
                i++;
            } else if(maxEleFreq >= k) { // When we hit the enough k times max elements
                while(maxEleFreq >= k && j <= i) 
                {
                    // current window [j…i] is valid, any extension [j…i+1], [j…i+2], … [j…n-1] will also remain valid
                    // (n-i) = counts all windows subarray starting@j + ending@i/afteri till the end
                    countSubArrMaxEleFreqAtLeastK += (n-i); 
                    
                    if(nums[j] == maxEle) {
                        maxEleFreq--;
                    }
                    j++;
                }
                i++;
            }
        }

        return countSubArrMaxEleFreqAtLeastK;
    }
}