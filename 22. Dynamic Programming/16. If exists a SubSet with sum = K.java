/* https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1 
Given an array of positive integers arr[] and a value sum, 
determine if there is a subset of arr[] with sum equal to given sum. 

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: true 
Explanation: Here there exists a subset with target sum = 9, 4+3+2 = 9. */

class Solution {
    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        Boolean[][] dp = new Boolean[n][sum+1];
            
        return isASubSetExistsHavingSumK(arr, n-1, sum, dp);
    }
    
    private static Boolean isASubSetExistsHavingSumK(int[] arr, int n, int sum, Boolean[][] dp) {
        if(sum < 0)
            return false;
        if(n < 0)
            return (sum==0) ? true : false;
            
        if(dp[n][sum] != null)
            return dp[n][sum];
            
        boolean takeNthOption = isASubSetExistsHavingSumK(arr, n-1, sum-arr[n], dp);
        boolean skipNthOption = isASubSetExistsHavingSumK(arr, n-1, sum, dp);
        
        return dp[n][sum] = takeNthOption || skipNthOption;
    }
}