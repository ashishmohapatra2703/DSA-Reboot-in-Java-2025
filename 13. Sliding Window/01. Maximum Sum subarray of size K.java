/*Given an array of integers Arr of size N and a number K. 
Return the maximum sum of a subarray of size K.
NOTE*: A subarray is a contiguous part of any given array.

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
            k = 4 
   Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.     */


class Solution{
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N)
    {
        long sumofsubarr = 0;
        for(int i=0; i<K; i++)
        { 
            sumofsubarr += Arr.get(i);  // create window of size K
        }

        long maxSum = sumofsubarr;
        for(int i=K; i<N; i++)
        {
            sumofsubarr += Arr.get(i);   //slide the window => front add + back subtract
            sumofsubarr -= Arr.get(i-K);
            
            maxSum = Math.max(maxSum , sumofsubarr);
        }
    
        return maxSum;
    }
}