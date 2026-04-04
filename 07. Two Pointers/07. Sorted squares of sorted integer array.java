/* https://leetcode.com/problems/squares-of-a-sorted-array/description/
Given an integer array nums sorted in non-decreasing order, 
return an array of the squares of each number sorted in non-decreasing order.

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121] */

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] sortedSquaresArr = new int[n];
        int l = 0;
        int h = n-1;

        //index filing in result array starts from (n-1) to 0 backwards
        while(l <= h)
        {
            int left = Math.abs(nums[l]);
            int right = Math.abs(nums[h]);
            if(left < right) {
                sortedSquaresArr[n-1] = right * right;
                n--;
                h--;
            } 
            else if(left > right) {
                sortedSquaresArr[n-1] = left * left;
                n--;
                l++;
            }
            else if(left == right && l != h) {
                sortedSquaresArr[n-1] = left * left;
                sortedSquaresArr[n-2] = right * right;
                n-=2;
                l++;
                h--;
            } 
            else if(l == h) {
                sortedSquaresArr[n-1] = left * left;
                n--;
                l++;
            }
        }

        return sortedSquaresArr;
    }
}