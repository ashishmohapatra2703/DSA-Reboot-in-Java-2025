/*Given an array of length N consisting of only 0s and 1s in random order. 
Modify the array to segregate 0s on left side and 1s on the right side of the array.

Input:
N = 5
arr[] = {0, 0, 1, 1, 0}
Output: 0 0 0 1 1    */

class Solution {
    void segregate0and1(int[] arr, int n) 
    {
        int end = n-1;
        int i = 0;
        int j = 0;

        while (i <= end) 
        {
            if (arr[i] == 1) {
                i++;
            } 
            else if (arr[i] == 0) {
                swap(arr, i, j);
                i++;
                j++;
            } 
        }
    }
    // 0....j-1 = 0s region (left Type-A region)
    // j....i-1 = 1s region (right Type-B region)
    // i to (n-1) = region of unknown (which goes on decreasing in traversal)

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
