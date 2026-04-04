/*https://www.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1

Given an array arr[] of distinct integers of size n and a value sum, 
the task is to find the count of triplets (i, j, k), 
having (i<j<k) with the sum of (arr[i] + arr[j] + arr[k]) smaller than the given value sum.

Input: n = 4, sum = 2, arr[] = {-2, 0, 1, 3}
Output:  2
Explanation: Below are triplets with sum less than 2 (-2, 0, 1) and (-2, 0, 3). 
Input: n = 5, sum = 12, arr[] = {5, 1, 3, 4, 7}
Output: 4
Explanation: Below are triplets with sum less than 12 (1, 3, 4), (5, 1, 3), (1, 3, 7) and (5, 1, 4). */

class Solution {
    long countTriplets(int n, int sum, long arr[]) {
        Arrays.sort(arr);
        long countTripletSumLessThanK = 0;
        
        for(int i=0; i<n; i++)
        {
            //for each num1, apply 2-pointer
            int low = i+1;
            int high = n-1;

            while(low < high)
            {
                long tripletSum = arr[i] + arr[low] + arr[high];
            
                if(tripletSum < sum) {
                    countTripletSumLessThanK += (high-low); // If the farthest (largest) works, everything in between also works.
                    low++; //move forward
                }
                else //must reduce sum
                    high--;
            }
        }
        return countTripletSumLessThanK;
    }
}
