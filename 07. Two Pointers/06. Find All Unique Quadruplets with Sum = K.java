/*https://leetcode.com/problems/4sum/description/
https://www.geeksforgeeks.org/problems/find-all-four-sum-numbers1732/1

Given an array nums of n integers, return an array of all the 
unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
0 <= a, b, c, d < n     a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]  */

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> quadrupletSum0 = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int num1 = nums[i];
            if(i>0 && num1 == nums[i-1]) {
                continue; // Skip duplicate num1
            }
            for(int j=i+1; j<n; j++) {
                int num2 = nums[j]; //for each (num1, num2) apply 2-Sum
                if(j>i+1 && num2 == nums[j-1]) {
                    continue; // Skip duplicate num2
                }

                int low = j+1;
                int high = n-1;
                long k = (long) target-(num1 + num2);
                while(low < high)
                {
                    long pairSum = nums[low] + nums[high];
                    if(pairSum == k) {
                        quadrupletSum0.add(Arrays.asList(num1, num2, nums[low], nums[high]));
                        low++;
                        high--;

                        // Skip duplicate num3 (low)
                        while(low<high && nums[low] == nums[low-1]) {
                            low++;
                        }
                        // Skip duplicate num4 (high)
                        while(low<high && nums[high] == nums[high+1]) {
                            high--;
                        }
                    }
                    else if(pairSum > k)
                        high--;
                    else if(pairSum < k)
                        low++;
                }
            }
        }

        return quadrupletSum0;
    }
}

// Note:
// Arrays.sort(nums); => Ensures quadruples are internally sorted automatically