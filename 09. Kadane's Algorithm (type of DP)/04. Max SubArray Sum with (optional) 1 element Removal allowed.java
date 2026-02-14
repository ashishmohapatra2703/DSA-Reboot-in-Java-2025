/* https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
https://www.geeksforgeeks.org/problems/max-sum-subarray-by-removing-at-most-one-element/1
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) 
with at most one element deletion. In other words, you want to choose a subarray and 
optionally delete one element from it so that there is still at least one element left and 
the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.*/

class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int maxSumEndingAtiWithNoRemoval = arr[0];
        int maxSumEndingAtiWith1Removal = Integer.MIN_VALUE; // cannot delete the only 1 element → would become empty subarray
        int maxSumGlobal = arr[0]; //overall Global Max SubArray Sum
            
        for(int i=1; i<n; i++)
        {
            int option1Addingi = maxSumEndingAtiWithNoRemoval+arr[i];
            int option2i = arr[i];
            int option3With1RemovalAlready = (maxSumEndingAtiWith1Removal!=Integer.MIN_VALUE ? maxSumEndingAtiWith1Removal+arr[i] : arr[i]);
            int option4Deletingi = maxSumEndingAtiWithNoRemoval; //deleting ith => No +/- of arr[i] 
            //=> only keep unmodified value of maxSumEnding at (i-1) WithNoRemoval

            maxSumEndingAtiWithNoRemoval = Math.max(option1Addingi, option2i);  
            maxSumEndingAtiWith1Removal = Math.max(option3With1RemovalAlready, option4Deletingi);  

            maxSumGlobal = Math.max(Math.max(maxSumEndingAtiWithNoRemoval, maxSumEndingAtiWith1Removal), maxSumGlobal);
        }
        return maxSumGlobal;
    }
}