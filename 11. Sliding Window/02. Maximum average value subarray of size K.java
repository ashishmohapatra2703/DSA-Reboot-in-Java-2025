/*You are given an integer array nums consisting of n elements, and an integer k.
Find a contiguous subarray whose length is equal to k 
that has the maximum average value and return this value. 
Any answer with a calculation error less than 10^-5 will be accepted.

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75

Constraints:
n == nums.length
1 <= k <= n <= 10^5
-10^4 <= nums[i] <= 10^4  */

// same as max sum subarray of length k , only return avg. at the end
class Solution {
    public double findMaxAverage(int[] nums, int k) 
    {
        int sumofsubarr = 0;
        for(int i=0; i<k; i++)
        { 
            sumofsubarr += nums[i];  // create window of size K
        }

        int maxSum = sumofsubarr;
        for(int i=k; i<nums.length; i++)
        {
            sumofsubarr += nums[i];   //slide the window => front add + back subtract
            sumofsubarr -= nums[i-k];
            
            maxSum = Math.max(maxSum , sumofsubarr);
        }
    
        return maxSum * 1.0 / k; // or typecast =>(double) maxSum / k;
    }
}