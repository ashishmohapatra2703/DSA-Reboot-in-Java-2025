/* https://leetcode.com/problems/count-number-of-nice-subarrays/description/
https://www.geeksforgeeks.org/problems/count-subarray-with-k-odds/1
Given an array of integers nums and an integer k. 
A continuous subarray is called nice if there are k odd numbers on it.
Return the number of nice sub-arrays.

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.

Input: arr[] = [2, 2, 5, 6, 9, 2, 11], k = 2
Output: 8
Explanation: There are 8 subarrays with 2 odds: [2, 2, 5, 6, 9], [2, 5, 6, 9], 
[5, 6, 9], [2, 2, 5, 6, 9, 2], [2, 5, 6, 9, 2], [5, 6, 9, 2], [6, 9, 2, 11] and [9, 2, 11].  */

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return countSubArrWithAtMostKOdd(nums, k) - countSubArrWithAtMostKOdd(nums, k-1);
    }

    private int countSubArrWithAtMostKOdd(int[] nums, int k) {
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int oddNumInSubArr = 0;
        int countSubArrWithAtMostKOdd = 0;

        while(i<n)
        {
            if(nums[i] % 2 != 0) { // oddNumber
                oddNumInSubArr++;
            }

            if(oddNumInSubArr <= k) { 
                // all subarrays starting anywhere between j and i are valid for countSubArrWithAtMostKOdd 
                // [j...i], [j+1...i], ..., [i...i]
                countSubArrWithAtMostKOdd += (i-j+1);
                i++;
            } else if (oddNumInSubArr > k) {
                while(oddNumInSubArr > k)
                {
                    if(nums[j] % 2 != 0) {
                        oddNumInSubArr--;
                    }
                    j++;    
                }
                if(oddNumInSubArr == k) { 
                    countSubArrWithAtMostKOdd += (i-j+1);
                }
                i++;
            }
        }

        return countSubArrWithAtMostKOdd;
    }
}