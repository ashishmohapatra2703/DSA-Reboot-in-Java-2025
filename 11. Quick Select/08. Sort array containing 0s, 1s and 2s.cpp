/*Given an array nums with n objects colored red, white, or blue,sort them in-place so that 
objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up: Could you come up with a one-pass algorithm using only constant extra space? */

// M-1 - 3 part Lomuto Partitioning
// T.C =  O(N) , S.C = O(1)
class Solution {
    public void sortColors(int[] nums) {
        int end = nums.length-1;
        int r = 0;
        int w = 0;
        int b = 0;

        // here pivot / pivot band = 1
        while (b <= end) 
        {
            if (nums[b] > 1) {
                b++;
            } 
            else if (nums[b] == 1) {
                swap(nums, b, w);
                b++;
                w++;
            } 
            else if (nums[b] < 1) {
                swap(nums, b, w);
                b++;
                swap(nums, w, r);
                w++;
                r++;
            }
        }
    }
    // 0....r-1 = red region (left Type-A region)
    // r....w-1 = white region (middle Type-B region)
    // w to b-1 = blue region (right Type-C region)
    // b to (n-1) = region of unknown (which goes on decreasing in traversal)

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


//M-2 - 3 pointers Dutch national flag algorithm
class Solution {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void sortColors(int[] nums) {
        // The dutch national flag algorithm 
        // to sort the array of size n using three pointers: lo=0, mid=0 and hi=n-1 
        // such that the array is divided into 4 parts -
        
        // arr[0 .. lo - 1] → All 0s
        // arr[lo .. mid - 1] → All 1s
        // arr[mid .. hi] → Unprocessed elements (unknown)
        // arr[hi + 1 .. n - 1] → All 2s

        int low = 0;
        int high = nums.length-1;
        int mid = 0;
        
        while(mid <= high) {
            if(nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 1) {
                mid++;
            }
            else if(nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }
    }
}