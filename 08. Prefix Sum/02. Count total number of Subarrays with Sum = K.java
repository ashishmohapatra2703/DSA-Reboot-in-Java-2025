/* https://leetcode.com/problems/subarray-sum-equals-k/description/
https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
Given an unsorted array arr[] of integers, 
find the number of subarrays whose sum exactly equal to a given number k.

Examples:
Input: arr[] = [10, 2, -2, -20, 10], k = -10
Output: 3
Explaination: Subarrays: arr[0...3], arr[1...4], arr[3...4] have sum exactly equal to -10.

Input: arr[] = [9, 4, 20, 3, 10, 5], k = 33
Output: 2
Explaination: Subarrays: arr[0...2], arr[2...4] have sum exactly equal to 33.

Input: arr[] = [1, 3, 5], k = 0
Output: 0
Explaination: No subarray with 0 sum. */ 

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefSumFreqMap = new HashMap<>(); 
        //Each key = prefix sum value
        //Each value = how many times that prefix sum has occurred
        int n = nums.length;
        int totalSubArrSumK = 0;

        int prefSum = 0;
        prefSumFreqMap.put(0, 1); //at index 0, empty subaray prefix, count 1 for prefSum=0

        for(int i=1; i<=n; i++) {
            prefSum += nums[i-1];

            int keyToFind = prefSum-k; //CHECK IF APPEARED BEFORE
            if (prefSumFreqMap.containsKey(keyToFind) == true) {
                totalSubArrSumK += prefSumFreqMap.get(keyToFind);
            }
            prefSumFreqMap.put(prefSum, prefSumFreqMap.getOrDefault(prefSum,0)+1); //CURRENT SAVING
        }
        
        return totalSubArrSumK;
    }
}