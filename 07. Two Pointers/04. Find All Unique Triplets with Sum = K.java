/* Given an array arr[] and an integer target, 
determine if there exists a triplet in the array whose sum equals the given target.
Return true if such a triplet exists, otherwise, return false.

Input: arr[] = [1, 4, 45, 6, 10, 8], target = 13
Output: true 
Explanation: The triplet {1, 4, 8} sums up to 13.
Input: arr[] = [1, 2, 4, 3, 6, 7], target = 10
Output: true 
Explanation: The triplets {1, 3, 6} and {1, 2, 7} both sum to 10.  */

//Easy version Type1 = Check If Triplet exists
class Solution {
    public boolean hasTripletSum(int arr[], int target) {
        Arrays.sort(arr);
        int n = arr.length;

        for(int i=0; i<n; i++)
        {
            int num1 = arr[i]; //for each num1, apply 2-pointer
            int low = i+1;
            int high = n-1;

            while(low < high)
            {
                int pairSum = arr[low] + arr[high];
                int k = target-num1;
            
                if(pairSum == k) {
                    return true;
                }
                else if(pairSum > k)
                    high--;
                else if(pairSum < k)
                    low++;
            }
        }

        return false;
    }
}

/* https://leetcode.com/problems/3sum/description/
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].

Notice that the order of the output and the order of the triplets does not matter.*/

//Harder version Type2 = Find all Triplets + No duplicate triplets in result
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> tripletSum0 = new ArrayList<>();

        for(int i=0; i<n; i++)
        {
            int num1 = nums[i]; //for each num1, apply 2-pointer
            // Skip duplicate num1
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }

            int low = i+1;
            int high = n-1;

            while(low < high)
            {
                int pairSum = nums[low] + nums[high];
                int target = -num1;
            
                if(pairSum == target) {
                    tripletSum0.add(Arrays.asList(num1, nums[low], nums[high]));
                    low++;
                    high--;

                    // Skip duplicate num2
                    while(low<high && nums[low] == nums[low-1]) {
                        low++;
                    }
                    // Skip duplicate num3
                    while(low<high && nums[high] == nums[high+1]) {
                        high--;
                    }
                }
                else if(pairSum > target)
                    high--;
                else if(pairSum < target)
                    low++;
            }
        }

        return tripletSum0;
    }
}