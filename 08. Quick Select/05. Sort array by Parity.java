/*Given an integer array nums, 
move all the EVEN integers at the beginning of the array followed by all the ODD integers.
Return any array that satisfies this condition.

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.*/

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int j = 0;

        for (int i=0; i<n; i++) 
        {
            if (nums[i] % 2 != 0) { // odd
                continue;
            } else if (nums[i] % 2 == 0) { // even
                swap(nums, i, j);
                j++;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/* Another checking method
if((nums[i]&1) == 1) //odd
if((nums[i]&1) == 0) //even    */