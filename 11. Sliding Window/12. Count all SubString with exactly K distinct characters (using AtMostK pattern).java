/* (LC hard) https://leetcode.com/problems/subarrays-with-k-different-integers/description/
https://www.geeksforgeeks.org/problems/subarrays-with-k-different-integers/1
https://www.geeksforgeeks.org/problems/count-number-of-substrings4528/1 

Given an POSTIVE integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: 
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4]. */

// sliding window only tracks one current window, not all possible substrings ending at each i. (overlapping substrings)
// In SUBSTRING-COUNTING problems, every valid smaller window also counts — not just the current one.

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return countSubArrAtMostKDistinct(nums, k) - countSubArrAtMostKDistinct(nums, k-1);
    }

    private int countSubArrAtMostKDistinct(int[] nums, int k) {
        HashMap<Integer, Integer> arrayEleCount = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int countSubArr = 0;

        while(i<n)
        {
            arrayEleCount.put(nums[i], arrayEleCount.getOrDefault(nums[i], 0) + 1);

            if(arrayEleCount.size() <= k) { // => at most k distint integers in this window 
                countSubArr += (i-j+1);
                i++;
            } else if(arrayEleCount.size() > k) {
                while(arrayEleCount.size() > k)
                {
                    arrayEleCount.put(nums[j], arrayEleCount.getOrDefault(nums[j], 0) - 1);
                    if(arrayEleCount.get(nums[j]) == 0) {
                        arrayEleCount.remove(nums[j]);
                    }
                    j++;    
                }
                if(arrayEleCount.size() <= k) {
                    countSubArr += (i-j+1);
                }
                i++;
            }
        }
        return countSubArr;
    }
}