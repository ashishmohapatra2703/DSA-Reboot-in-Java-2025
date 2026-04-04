/* https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/
You are given an integer array nums and an integer k. 
Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
A subarray is a contiguous non-empty sequence of elements within an array.

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions 
Input: nums =[4,5,4,5,2,9,9,9,8], k = 3
Output: 16    {5,2,9} subarray   */

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> subArrFreqMap = new HashMap<>();

        long sumOfSubArr = 0;
        long maxSumOfAllSubArr = 0;

        for(int i=0; i<k; i++) { 
            sumOfSubArr += nums[i];  // create window of size K
            subArrFreqMap.put(nums[i], subArrFreqMap.getOrDefault(nums[i],0) + 1);
        }
        if (subArrFreqMap.size() == k) //if size k / all distinct in first window => valid subarr
            maxSumOfAllSubArr = sumOfSubArr;


        for(int i=k; i<n; i++) {
            int frontAdd = nums[i];
            sumOfSubArr += frontAdd;   //slide the window => front add + back subtract
            subArrFreqMap.put(frontAdd, subArrFreqMap.getOrDefault(frontAdd,0) + 1);

            int backRemove = nums[i-k];
            sumOfSubArr -= backRemove;
            subArrFreqMap.put(backRemove, subArrFreqMap.getOrDefault(backRemove,0) - 1);
            if(subArrFreqMap.get(backRemove) == 0) {
                subArrFreqMap.remove(backRemove);
            }
            
            if(subArrFreqMap.size() == k) {
                maxSumOfAllSubArr = Math.max(maxSumOfAllSubArr , sumOfSubArr);
            }
            //else -> invalid subarr, discard, dont do any calculation
        }
    
        return maxSumOfAllSubArr;
    }
}