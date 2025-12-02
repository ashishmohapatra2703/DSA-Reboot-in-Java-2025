/*https://www.geeksforgeeks.org/problems/key-pair5616/1 
Given an array arr[] of integers and another integer target. 
Determine if there exist two distinct indices such that 
the sum of their elements is equal to the target.

Input: arr[] = [0, -1, 2, -3, 1], target = -2
Output: true
Explanation: arr[3] + arr[4] = -3 + 1 = -2
Input: arr[] = [1, -2, 1, 0, 5], target = 0
Output: false
Explanation: None of the pair makes a sum of 0 */

//Type1 = Check if a pair exists
class Solution {
    boolean twoSum(int arr[], int target) {
        Arrays.sort(arr);
        
        int low = 0;
        int high = arr.length-1;
        while(low < high)
        {
            int pairSum = arr[low] + arr[high];
            
            if(pairSum == target) {
                return true;
            }
            else if(pairSum > target)
                high--;
            else if(pairSum < target)
                low++;
        }
        return false;
    }
}

/*https://leetcode.com/problems/two-sum/
Given an array of integers nums and an integer target, 
return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, 
and you may not use the same element twice.
You can return the answer in any order.

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Input: nums = [3,2,4], target = 6
Output: [1,2]  */

//Type2 = Find the pair
//M-1 using sorting with index tied up
class Solution {
    private int[][] numIdxPairs;
    private void fillThePairsArray(int[] nums, int n) {
        numIdxPairs = new int[n][2];

        for (int i=0; i<n; i++) {
            numIdxPairs[i][0] = nums[i];
            numIdxPairs[i][1] = i;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        fillThePairsArray(nums, n);
        Arrays.sort(numIdxPairs, (a,b) -> a[0]-b[0]); //value based sorting
        
        int low = 0;
        int high = n-1;
        while(low < high)
        {
            int pairSum = numIdxPairs[low][0] + numIdxPairs[high][0];
            
            if(pairSum == target) {
                return new int[]{numIdxPairs[low][1], numIdxPairs[high][1]}; //return idx
            }
            else if(pairSum > target)
                high--;
            else if(pairSum < target)
                low++;
        }
        return new int[]{};
    }
}
//M-2 using hashing
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numberToIdxHash = new HashMap();

        for(int i=0; i<nums.length; i++) 
        {
            int complement = target - nums[i];
            if(numberToIdxHash.containsKey(complement)) {
                int complementIdx = numberToIdxHash.get(complement);
                return new int[]{i, complementIdx};
            }
            
            numberToIdxHash.put(nums[i], i);
        }

        return new int[]{};
    }
}
