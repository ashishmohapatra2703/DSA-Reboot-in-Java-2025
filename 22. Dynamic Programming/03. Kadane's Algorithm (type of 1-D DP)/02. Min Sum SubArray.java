/* https://www.geeksforgeeks.org/problems/smallest-sum-contiguous-subarray/1
Given an array arr[] of N integers. 
Find the contiguous sub-array(containing at least one number) which has 
the minimum sum and return its sum.

Input: 
arr[] = {3,-4, 2,-3,-1, 7,-5}
Output: -6
Explanation: sub-array which has smallest 
sum among all the sub-array is {-4,2,-3,-1} = -6    */

class Solution {
    static int smallestSumSubarray(int a[], int size) {
        int minSumEndingAti = 0;
        int minSumGlobal = Integer.MAX_VALUE; //overall Global Min SubArray Sum
            
        for(int i=0; i<size; i++)
        {
            minSumEndingAti = minSumEndingAti + a[i];
            minSumEndingAti = Math.min(minSumEndingAti, a[i]);  
            
            minSumGlobal = Math.min(minSumEndingAti, minSumGlobal);
        }
        return minSumGlobal;
    }
}