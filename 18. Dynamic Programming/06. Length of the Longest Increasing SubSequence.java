/*https://leetcode.com/problems/longest-increasing-subsequence/description/
https://www.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1

Given an integer array nums, return the 
length of the longest strictly increasing subsequence.

Input: arr[] = [5, 8, 3, 7, 9, 1]
Output: 3
Explanation: The longest strictly increasing subsequence could be [5, 7, 9], which has a length of 3.
Input: arr[] = [10, 6, 3, 11, 7, 15]
Output: 3
Explanation: One of the possible longest strictly increasing subsequences is [10, 11, 15], which has a length of 3. */

//M-1 Top Down Recur + Memo
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return lenOfLIS(nums, 0, -1, n, dp);
    }

    private int lenOfLIS(int[] nums, int i, int prevIdx, int n, int[][] dp) {
        if(i == n)
            return 0;

        if(prevIdx != -1 && dp[i][prevIdx] != -1)
            return dp[i][prevIdx];

        int takeIth = 0;
        if(prevIdx == -1 || nums[prevIdx] < nums[i])
            takeIth = 1 + lenOfLIS(nums, i+1, i, n, dp);
        int skipIth = lenOfLIS(nums, i+1, prevIdx, n, dp);

        if(prevIdx != -1)
            dp[i][prevIdx] = Math.max(takeIth, skipIth);
        return Math.max(takeIth, skipIth);
    }
}

//M-2 Bottom up
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; //dp[i] = max len LIS ending at ith index
        Arrays.fill(dp, 1); //single element of that index = SS itself
        int maxLIS = 1; //base answer anyways

        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i])  //increasing SS left to right
                {
                    int takeJ = 1 + dp[j];
                    int skipJ = dp[i];
                    dp[i] = Math.max(takeJ, skipJ);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        return maxLIS;
    }
}

/*
template

dp[i] = base_value

for j < i:
    if valid:
        dp[i] = max(dp[i], dp[j] + value[i])
*/

//M-2 T.C = O(n.logn) using TreeMap
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> treemap = new TreeMap<>(); //<nums[i], lenLISEndingWith_numIth>
        int maxLenLIS = 0;

        for (int i=0; i<n; i++) {
            //Step1: find key in treemap / closest predecessor value which is "just lesser" than numIth
            int numIth = nums[i];
            Integer justLesserThanNumIth = treemap.lowerKey(numIth);

            //Step2: Update treemap[numIth] with the best computed LIS length ending at numIth
            int lenLISEndingWith_justLesserThanNumIth_takePrev = (justLesserThanNumIth != null) ? (1 + treemap.get(justLesserThanNumIth)) : 1;
            int lenLISEndingWith_numIth_SkipPrev = treemap.getOrDefault(numIth, 0);
            treemap.put(numIth, Math.max(lenLISEndingWith_numIth_SkipPrev , lenLISEndingWith_justLesserThanNumIth_takePrev)); //take or skip

            //Step3: lenLISEndingWithnumIth = Updated numIth's treemap value
            int lenLISEndingWith_numIth = treemap.get(numIth); 

            //Step4: Delete the useless elements whose key = "just higher" than nums[i] and value <= lenLISEndingWith_numIth
            //Logic = "If I can get the same LIS length with a smaller ending value, I should discard the bigger one"
            Integer justHigherThanNumIth = treemap.higherKey(numIth);
            while (justHigherThanNumIth != null && treemap.get(justHigherThanNumIth) <= lenLISEndingWith_numIth) {
                treemap.remove(justHigherThanNumIth);
                justHigherThanNumIth = treemap.higherKey(numIth);
            }

            //Step5: Update global answer
            maxLenLIS = Math.max(maxLenLIS, lenLISEndingWith_numIth);
        }
        return maxLenLIS;
    }
}

//M-3 T.C = O(n.logn) using Patience Sorting
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        List<Integer> tailsSorted = new ArrayList<>(); //tailsSorted[i] = smallest ending value of LIS of length i+1
        
        // Patience Sorting = "Keep subsequences as flexible as possible by minimizing their ending values"
        // 1. Iterate on nums O(n)
        // 2. Have to place the numbers in LISsorted piles, 
        //    2.i.) first occurence from left, pile's top number >= nums[i] => Replace with nums[i]
        //    2.ii.) else create new pile
        // 3. the result array = LISsorted

        for(int i=0; i<n; i++) {
            int idx = getLowerBoundIdx(tailsSorted, nums[i]);  //first occurence idx with tailsSorted[idx] >= nums[i]

            // If idx is larger than all tails size → extend LIS/ create new pile
            if(idx == tailsSorted.size()) {
                tailsSorted.add(nums[i]);
            } 
            else {
                tailsSorted.set(idx, nums[i]);
            }
        }
        return tailsSorted.size();
    }

    private int getLowerBoundIdx(List<Integer> tailsSorted, int target) {
        int left = 0;
        int right = tailsSorted.size()-1;

        while(left <= right) 
        {
            int mid = left + (right-left)/2;

            if(tailsSorted.get(mid) < target) {
                // Current mid is too small, look to the right
                left = mid+1;
            } else if(tailsSorted.get(mid) >= target){
                // Potential lower bound found, look further left
                right = mid-1;
            }
        }
        return left;
    }
}