/*https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1

Given an array of integers Arr of size N and a number K. 
Return the maximum sum of a subarray of size K.
NOTE*: A subarray is a contiguous part of any given array.

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
            k = 4 
   Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.     */


class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        int sumOfSubArr = 0;
        
        for(int i=0; i<k; i++) { 
            sumOfSubArr += arr[i];  // create window of size K
        }

        int maxSum = sumOfSubArr;
        for(int i=k; i<n; i++) {
            sumOfSubArr += arr[i];   //slide the window => front add + back subtract
            sumOfSubArr -= arr[i-k];
            
            maxSum = Math.max(maxSum , sumOfSubArr);
        }
    
        return maxSum;
    }
}