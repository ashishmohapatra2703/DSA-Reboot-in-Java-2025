/* https://leetcode.com/problems/subarray-sums-divisible-by-k/description/
https://www.geeksforgeeks.org/problems/sub-array-sum-divisible-by-k2617/1
Given an integer array nums and an integer k, 
return the number of non-empty subarrays that have a sum divisible by k.
A subarray is a contiguous part of an array.

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]  */


//Core logic: If two prefix sums have the same remainder modulo = k, their difference is divisible by k (%k == 0)
class Solution {
    // Function to count the number of subarrays with a sum that is divisible by K
    public int subCount(int[] arr, int k) {
        HashMap<Long, Integer> prefSumModKFreqMap = new HashMap<>(); 
        int n = arr.length;
        int totalSubArrSumModK0 = 0;

        long prefSum = 0;
        long prefSumModK=0;
        prefSumModKFreqMap.put(0L, 1); //at index 0, empty subaray prefix, count 1 for prefSumModK=0

        for(int i=1; i<=n; i++) {
            prefSum += arr[i-1];
            prefSumModK = (prefSum%k<0) ? prefSum%k+k : prefSum%k;
            
            Long keyToFind = prefSumModK; //CHECK IF APPEARED BEFORE
            if (prefSumModKFreqMap.containsKey(keyToFind) == true) {
                totalSubArrSumModK0 += prefSumModKFreqMap.get(keyToFind);
            }
            prefSumModKFreqMap.put(prefSumModK, 
                                prefSumModKFreqMap.getOrDefault(prefSumModK,0)+1); //CURRENT SAVING
        }
        return totalSubArrSumModK0;
    }
}
