/* https://leetcode.com/problems/maximum-balanced-subsequence-sum/description/ 

You are given a 0-indexed integer array nums.
A subsequence of nums having length k and consisting of indices i0 < i1 < ... < ik-1 is balanced if the following holds:
nums[ij] - nums[ij-1] >= ij - ij-1, for every j in the range [1, k - 1].
A subsequence of nums having length 1 is considered balanced.

Return an integer denoting the maximum possible sum of elements in a balanced subsequence of nums.
A subsequence of an array is a new non-empty array that is formed from the original array 
by deleting some (possibly none) of the elements 
without disturbing the relative positions of the remaining elements.

Input: nums = [3,3,5,6]
Output: 14
Explanation: In this example, the subsequence [3,5,6] consisting of indices 0, 2, and 3 can be selected.
nums[2] - nums[0] >= 2 - 0.
nums[3] - nums[2] >= 3 - 2.
Hence, it is a balanced subsequence, and its sum is the maximum among the balanced subsequences of nums.
The subsequence consisting of indices 1, 2, and 3 is also valid.
It can be shown that it is not possible to get a balanced subsequence with a sum greater than 14. */

//M-1
//TLE in Leetcode, 341 / 346 testcases passed
// T.C = O(n^2)
class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int k = nums.length;
        // nums[i at j] - nums[i at j-1]    =>    i at j - i at j-1 
        // => nums[i at j] - i at j    =>      nums[i at j-1] - i at j-1 
        // prepare array = nums[i] - i
        int[] numsValueMinusIdx = new int[k];
        for(int i=0; i<k; i++) {
            numsValueMinusIdx[i] = nums[i] - i;
        }
        return findMaxSumOfIncreasingSubSequence(numsValueMinusIdx, k, nums); //not strictly increasing
    }

    private long findMaxSumOfIncreasingSubSequence(int[] arr, int n, int[] nums) {
        long[] dp = new long[n]; //dp[i] = MaxSum Of Increasing SubSequence Ending at ith index
        long maxSumIS = Long.MIN_VALUE;

        for(int i=0; i<n; i++) {
            dp[i] = nums[i]; //single element of that index = SS itself == base case
            for(int j=0; j<i; j++) {
                if(arr[j] <= arr[i]) 
                {
                    long takeJ = nums[i] + dp[j];
                    long skipJ = dp[i];
                    dp[i] = Math.max(takeJ, skipJ);
                }
            }
            maxSumIS = Math.max(maxSumIS, dp[i]);
        }

        return maxSumIS;
    }  
}

//M-2,  T.C = O(n.logn)
class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int k = nums.length;
        int[] numsValueMinusIdx = new int[k];
        for(int i=0; i<k; i++) {
            numsValueMinusIdx[i] = nums[i] - i;
        }
        return findMaxSumOfIncreasingSubSequence(numsValueMinusIdx, k, nums); //not strictly increasing
    }

    private long findMaxSumOfIncreasingSubSequence(int[] nums, int n, int[] originalValueNums) {
        TreeMap<Integer, Long> treemap = new TreeMap<>(); //<nums[i], maxSumISEndingWith_numIth>
        long maxSumIS = Long.MIN_VALUE;

        for (int i=0; i<n; i++) {
            //Step1: find key in treemap / closest predecessor value which is "just lesser" than numIth
            int numIth = nums[i];
            Integer justLesserThanNumIth = treemap.floorKey(numIth); 

            //Step2: Update treemap[numIth] with the best computed maxSumIS ending at numIth
            long maxSumISEndingWith_justLesserThanNumIth_takePrev = 
                                (justLesserThanNumIth != null) ? (originalValueNums[i] + treemap.get(justLesserThanNumIth)) : Long.MIN_VALUE;
            long maxSumISEndingWith_numIth_SkipPrev = (long)originalValueNums[i];
            treemap.put(numIth, Math.max(maxSumISEndingWith_numIth_SkipPrev, maxSumISEndingWith_justLesserThanNumIth_takePrev)); 

            //Step3: maxSumISEndingWith_numIth = Updated numIth's treemap value
            long maxSumISEndingWith_numIth = treemap.get(numIth); 

            //Step4: Delete the useless elements whose key = "just higher" than nums[i] and value < maxSumISEndingWith_numIth
            Integer justHigherThanNumIth = treemap.higherKey(numIth);
            while (justHigherThanNumIth != null && treemap.get(justHigherThanNumIth) < maxSumISEndingWith_numIth) {
                treemap.remove(justHigherThanNumIth);
                justHigherThanNumIth = treemap.higherKey(numIth);
            }

            //Step5: Update global answer
            maxSumIS = Math.max(maxSumIS, maxSumISEndingWith_numIth);
        }
        return maxSumIS;
    }  
}