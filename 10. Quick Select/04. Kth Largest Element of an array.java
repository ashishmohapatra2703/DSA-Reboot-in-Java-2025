/*Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting? 

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5               */

// M-1 Recursive Quick select (can be done iteratively also using while(low <= high)loop)
class Solution {
    public int findKthLargest(int[] nums, int k) // to find element at (n-k)th sorted index
    {
        int n = nums.length;
        return kthLargestQS(nums, 0, n-1, n-k);
    }

    private int kthLargestQS(int[] nums, int low, int high, int sIdx)
    {
        if(low == high)
            return nums[low];

        int pi = partition(nums, low, high); // partitioning index / return the sorted index of the pivot

        if (sIdx == pi)
            return nums[sIdx];

        else if (sIdx < pi)
            return kthLargestQS(nums, low, pi-1, sIdx);
        else // if (sIdx > pi)
            return kthLargestQS(nums, pi+1, high, sIdx);
    }

    private int partition(int[] nums, int low, int high) {
        int randomIdx = low + new Random(0).nextInt(high - low + 1); //optimization
        swap(nums, randomIdx, high);
        int pivot = nums[high]; 

        int j = low;
        for (int i = low; i <= high-1; i++) //upto 2nd last element
        {
            if(nums[i] >= pivot) {
                continue;
            }
            else if(nums[i] < pivot) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, high, j); //when i is at high/ at the end => nums[i] == pivot
    
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}