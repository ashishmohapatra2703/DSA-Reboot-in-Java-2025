/* https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
https://www.geeksforgeeks.org/problems/length-unsorted-subarray3022/1 
Given an integer array nums, you need to find one continuous subarray 
such that if you only sort this subarray in non-decreasing order, 
then the whole array will be sorted in non-decreasing order.
Return the shortest such subarray and output its length.

Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make 
the whole array sorted in ascending order.
Input: nums = [1,3,2,2,2]
Output: 4
Input: nums = [1,3,5,4,2]
Output: 4
Input: nums = [2,1]
Output: 2  
Input: nums = [1,2,3,4]
Output: 0   */

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int firstDipFromL = -1;
        for(int i=0; i<n-1; i++) {
            if(nums[i] > nums[i+1]) {
                firstDipFromL = i;
                break;
            }
        }
        if(firstDipFromL == -1) {
            return 0; //dip never came => array is alreay sorted
        }

        int firstSpikeFromR = n-1;
        for(int i=n-1; i>0; i--) {
            if(nums[i-1] > nums[i]) {
                firstSpikeFromR = i;
                break;
            }
        }
        //these 2 gives the range of unsorted array -> to sort.
        //But Sorting that middle range introduces new values at its boundaries (min & max)
        //expand both ways and check if global array has become sorted 
        int minOfUnSortedRange = Integer.MAX_VALUE;
        int maxOfUnSortedRange = Integer.MIN_VALUE;
        for(int i=firstDipFromL; i<=firstSpikeFromR; i++) {
            minOfUnSortedRange = Math.min(minOfUnSortedRange, nums[i]);
            maxOfUnSortedRange = Math.max(maxOfUnSortedRange, nums[i]);
        } 

        while(firstDipFromL > 0 && nums[firstDipFromL-1] > minOfUnSortedRange) {
            firstDipFromL--;
        }
        while(firstSpikeFromR < n-1 && maxOfUnSortedRange > nums[firstSpikeFromR+1]) {
            firstSpikeFromR++;
        }
        return (firstSpikeFromR - firstDipFromL + 1);
    }
}