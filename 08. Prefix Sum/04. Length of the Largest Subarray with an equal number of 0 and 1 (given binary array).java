/* https://leetcode.com/problems/contiguous-array/description/ 
https://www.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1
Given a binary array nums, return the maximum length of a contiguous subarray 
with an equal number of 0 and 1.

Input: nums = [0,1,1,1,1,1,0,0,0]
Output: 6
Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1. */

class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> countGap01_EndIdx_Map = new HashMap<>(); 
        int n = nums.length;
        int maxLenSubArrWithEqualCount01 = 0; 

        int count0EndingAti = 0;
        int count1EndingAti = 0;
        countGap01_EndIdx_Map.put(0, -1); //at index -1, empty subaray => countOf0's - countOf1's = 0
        int countGap01EndingAti = 0;

        for(int i=0; i<n; i++) {
            if(nums[i] == 0)
                count0EndingAti++;
            else if(nums[i] == 1)
                count1EndingAti++;
            countGap01EndingAti = count0EndingAti - count1EndingAti;
            
            int keyToFind = countGap01EndingAti; //CHECK AT WHAT END_INDEX (=map's value), KEY=countGap01EndingAti APPEARED BEFORE
            if (countGap01_EndIdx_Map.containsKey(keyToFind) == true) {
                int beforeFoundIdx = countGap01_EndIdx_Map.get(keyToFind);
                int countGap01Equal0SubArrLen = (i-beforeFoundIdx); // when (beforeFoundIdx==-1) => assigns i+1
                //whole subarray ending at i /OR/ subarray trimmed as required
                maxLenSubArrWithEqualCount01 = Math.max(countGap01Equal0SubArrLen, maxLenSubArrWithEqualCount01);
            } else {
                countGap01_EndIdx_Map.put(countGap01EndingAti, i); //CURRENT SAVE; ONLY FIRST OCCURENCE OF ANY KEY; TO MAXIMIZE
            }
        }
        
        return maxLenSubArrWithEqualCount01;
    }
}