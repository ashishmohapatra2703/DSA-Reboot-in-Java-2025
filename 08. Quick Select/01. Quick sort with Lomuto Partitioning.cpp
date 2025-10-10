// Given an array of integers nums, sort the array in ascending order and return it.

/* Lomuto Partitioning works by selecting a pivot, rearranging the elements in the array so that 
elements smaller than the pivot come before it, 
and elements greater than the pivot come after it. */

class Solution 
{
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int pi = partition(nums, low, high); // pi = partitioning Index

        quickSort(nums, low, pi-1);
        quickSort(nums, pi+1, high);
    }

    private int partition(int[] nums, int low, int high) 
    {
        int randomIdx = low + new Random().nextInt(high - low + 1); //optimization
        swap(nums, randomIdx, high);
        int pivot = nums[high]; //pivot = last element always (in lomuto partitioning)

        int j = low;
        for (int i = low; i <= high-1; i++) 
        {
            if (nums[i] < pivot) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, high, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// using this partition() gives TLE
// bcoz if too many same continuous elements are present => too many swaps for (nums[i] <= pivot) == condition
// better do swap at last for only the element arr[high] == pivot , like above
private int partition(int[] nums, int low, int high) 
    {
        Random rand = new Random();
        int randomIdx = low + rand.nextInt(high - low + 1); //optimization
        swap(nums, randomIdx, high);
        int pivot = nums[high]; //pivot = last element always (in lomuto partitioning)

        int j = low;
        for (int i = low; i <= high; i++) 
        {
            if (nums[i] <= pivot) {
                swap(nums, i, j);
                j++;
            }
        }
        return j-1;
    }
