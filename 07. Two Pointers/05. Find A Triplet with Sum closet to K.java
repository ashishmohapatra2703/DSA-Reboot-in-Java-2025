/* https://leetcode.com/problems/3sum-closest/description/
Given an integer array nums of length n and an integer target, 
find three integers at distinct indices in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2). */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int minClosenessSumToK = Integer.MAX_VALUE;
        int tripletSumClosetToK = 0;

        for(int i=0; i<n; i++) {
            //for each num[i], apply 2-pointer
            int low = i+1;
            int high = n-1;
            
            while(low < high)
            {
                int tripletSum = nums[i] + nums[low] + nums[high];
                int closeness = target - tripletSum;
                int absCloseness = Math.abs(closeness);
                
                if(absCloseness <= minClosenessSumToK) {
                    tripletSumClosetToK = tripletSum;
                    minClosenessSumToK = absCloseness;
                }
                
                if(closeness == 0) {
                    low++; // can do high-- either
                }
                else if(closeness > 0) {
                    low++;
                }
                else if(closeness < 0) {
                    high--;
                }
            }
        }

        return tripletSumClosetToK;
    }
}