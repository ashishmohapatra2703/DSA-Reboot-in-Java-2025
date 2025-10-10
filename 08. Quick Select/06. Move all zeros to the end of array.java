/*Given an integer array nums, move all 0's to the end of it 
while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]            */

//T.C = O(n), S.C = O(1)
class Solution {
    public void moveZeroes(int[] nums) 
    {
        int n = nums.length;
        int j = 0;
        
        for(int i=0; i<n; i++)
        {
            if (nums[i] == 0) {
                continue;
            } else if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


//0....j-1 = non-zero region (left Type-A region -> note: only here relative order is preserved)
//j....i-1 = zero region (right Type-B region)
//i to n-1 = region of unknown (which goes on decreasing in traversal)