/* https://leetcode.com/problems/max-consecutive-ones-iii/description/
https://www.geeksforgeeks.org/problems/maximize-number-of-1s0905/1
Given a binary array arr[] containing only 0s and 1s and an integer k, 
you are allowed to flip at most k 0s to 1s. 
Find the maximum number of consecutive 1's that can be obtained in the array 
after performing the operation at most k times.

Input: arr[] = [1, 0, 1], k = 1
Output: 3
Explanation: By flipping the zero at index 1, we get the longest subarray from index 0 to 2 containing all 1’s.
Input: arr[] = [1, 0, 0, 1, 0, 1, 0, 1], k = 2
Output: 5
Explanation: By flipping the zeroes at indices 4 and 6, we get the longest subarray from index 3 to 7 containing all 1’s.
Input: arr[] = [1, 1], k = 2
Output: 2
Explanation: Since the array is already having the max consecutive 1's, hence we dont need to perform any operation. Hence the answer is 2. */

class Solution {
    public int longestOnes(int[] nums, int k) {
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int flipCount = 0;
        int maxSubArrOf1sWithAtMostKFlip0s = 0;

        while(i < n) 
        {
            if(nums[i] == 0) {
                flipCount++;
            }

            if (flipCount <= k) {
                int windowSize = i-j+1;
                maxSubArrOf1sWithAtMostKFlip0s = Math.max(windowSize, maxSubArrOf1sWithAtMostKFlip0s);
                i++;
            } else if (flipCount > k) {
                while(flipCount > k) {
                    if(nums[j] == 0) {
                        flipCount--;
                    }
                    j++;
                }
                i++;
            }
        } 
        return maxSubArrOf1sWithAtMostKFlip0s;
    }
}