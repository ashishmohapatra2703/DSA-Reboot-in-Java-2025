/* https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
Given an array arr[] containing integers and an integer k, 
your task is to find the length of the longest subarray 
where the sum of its elements is equal to the given value k. 
If there is no subarray with sum equal to k, return 0.

Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
Output: 6
Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.

Input: arr[] = [-5, 8, -14, 2, 4, 12], k = -5
Output: 5
Explanation: Only subarray with sum = -5 is [-5, 8, -14, 2, 4] of length 5. */


// Logic = 
// (1) if PrefixSum till ith index = "X" and PrefixSum till jth index = "X-k" where i > j
//     => then the subarray b/w (j to i] has sum equal to k. 
// (2) if PrefixSum till ith index = "k"
//     => then the subarray b/w [0 to i] has sum equal to k. 
class Solution {
    public int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> prefSumToIdxMap = new HashMap<>();
        int n = arr.length;
        int prefSum = 0;
        int maxWindow = 0;
        
        for(int i=0; i<n; i++)
        {
            prefSum += arr[i];
            if(prefSumToIdxMap.containsKey(prefSum) == false) { // first occurrences only should be put, since we want longest subarray
                prefSumToIdxMap.put(prefSum, i);
            } 
            
            if(prefSum == k) {
                maxWindow = Math.max(maxWindow , i-0+1); //subarray [0....to...i] = sum K
            }
            if(prefSumToIdxMap.containsKey(prefSum-k) == true) { 
                int j = prefSumToIdxMap.get(prefSum-k); //subarray [(j+1)...to... i] = sum K
                maxWindow = Math.max(maxWindow , i-(j+1)+1);
            }
        }
        
        return maxWindow;
    }
}


// Similar problem:- Length of the largest subarray with Sum 0
/* https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
Given an array arr[] containing both positive and negative integers, 
the task is to find the length of the longest subarray with a sum equals to 0.

Examples:
Input: arr[] = [15, -2, 2, -8, 1, 7, 10, 23]
Output: 5
Explanation: The longest subarray with sum equals to 0 is [-2, 2, -8, 1, 7].

Input: arr[] = [2, 10, 4]
Output: 0
Explanation: There is no subarray with a sum of 0.

Input: arr[] = [1, 0, -4, 3, 1, 0]
Output: 5
Explanation: The longest subarray with sum equals to 0 is [0, -4, 3, 1, 0].
*/

class Solution {
    int maxLength(int arr[]) {
        HashMap<Integer, Integer> prefSumToIdxMap = new HashMap<>();
        int n = arr.length;
        int prefSum = 0;
        int maxWindow = 0;
        
        for(int i=0; i<n; i++)
        {
            prefSum += arr[i];
            
            if(prefSumToIdxMap.containsKey(prefSum) == false) { // first occurrences only should be put, since we want longest subarray
                prefSumToIdxMap.put(prefSum, i);
            } else if(prefSumToIdxMap.containsKey(prefSum) == true) { 
                int j = prefSumToIdxMap.get(prefSum); //subarray [(j+1)...to... i] = sum 0
                maxWindow = Math.max(maxWindow , i-(j+1)+1);
            }
            
            if(prefSum == 0) {
                maxWindow = Math.max(maxWindow , i-0+1); //subarray [0....to...i] = sum 0
            }
        }
        return maxWindow;
    }
}