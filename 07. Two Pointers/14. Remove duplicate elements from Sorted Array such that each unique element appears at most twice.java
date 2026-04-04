/* https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

Given an integer array nums sorted in non-decreasing order, 
remove some duplicates in-place such that each unique element appears at most twice. 
The relative order of the elements should be kept the same.
Since it is impossible to change the length of the array in some languages, 
you must instead have the result be placed in the first part of the array nums. 
More formally, if there are k elements after removing the duplicates, 
then the first k elements of nums should hold the final result. 
It does not matter what you leave beyond the first k elements.
Return k after placing the final result in the first k slots of nums. 

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores). */

class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 2;
        int writeIdx = 2; //0th and 1st element always exists
        int n = nums.length;
        
        while(i < n)
        {
            if(nums[i] == nums[writeIdx-2]) {
                i++;
            } else if (nums[i] != nums[writeIdx-2]) {  
                nums[writeIdx] = nums[i]; // iterate the updated modified array on the go
                writeIdx++; 
                i++;
            }
        }
        return writeIdx; //count of elements after allowing at most 2 duplicates
    }
}