/* https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1 
Given an array arr of non-negative integers and an integer target, 
the task is to count all subsets of the array whose sum is equal to the given target.

Input: arr[] = [5, 2, 3, 10, 6, 8], target = 10
Output: 3
Explanation: The subsets {5, 2, 3}, {2, 8}, and {10} sum up to the target 10.
Input: arr[] = [2, 5, 1, 4, 3], target = 10
Output: 3
Explanation: The subsets {2, 1, 4, 3}, {5, 1, 4}, and {2, 5, 3} sum up to the target 10. */

class Solution {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target+1];
        for(int[] row: dp)
            Arrays.fill(row, -1);
            
        return countSubSetsHavingSumK(nums, n-1, target, dp);
    }
    
    private int countSubSetsHavingSumK(int[] arr, int n, int sum, int[][] dp) {
        if(sum < 0)
            return 0;
        if(n < 0)
            return (sum==0) ? 1 : 0;
            
        if(dp[n][sum] != -1)
            return dp[n][sum];
            
        int takeNthOption = countSubSetsHavingSumK(arr, n-1, sum-arr[n], dp);
        int skipNthOption = countSubSetsHavingSumK(arr, n-1, sum, dp);
        
        return dp[n][sum] = takeNthOption + skipNthOption;
    }
}