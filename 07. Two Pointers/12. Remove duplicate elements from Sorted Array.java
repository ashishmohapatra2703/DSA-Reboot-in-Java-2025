/*https://www.geeksforgeeks.org/problems/remove-duplicate-elements-from-sorted-array/1 
You are given a sorted array arr[] containing positive integers. 
Your task is to remove all duplicate elements from this array such that each element appears only once. 
Return an array containing these distinct elements in the same order as they appeared.

Input: arr[] = [2, 2, 2, 2, 2]
Output: [2]
Explanation: After removing all the duplicates only one instance of 2 will remain i.e. [2] 
so modified array will contains 2 at first position and you should return array containing [2] after modifying the array.*/

class Solution {
    // Function to remove duplicates from the given array.
    ArrayList<Integer> removeDuplicates(int[] arr) {
        ArrayList<Integer> uniques = new ArrayList<>();
        uniques.add(arr[0]); //0th element always exists
        int i = 1;
        int n = arr.length;
        
        while(i < n)
        {
            if(arr[i] == arr[i-1]) {
                i++;
            } else {  //unique found
                uniques.add(arr[i]);
                i++;
            }
        }
        
        return uniques;
    }
}

/*https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/ 
Given an integer array nums sorted in non-decreasing order, 
remove the duplicates in-place such that each unique element appears only once. 
The relative order of the elements should be kept the same.
Consider the number of unique elements in nums to be k. 
After removing duplicates, return the number of unique elements k.
The first k elements of nums should contain the unique numbers in sorted order. 
The remaining elements beyond index k - 1 can be ignored.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores). */

class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 1;
        int writeIdx = 1; //0th element always exists
        int n = nums.length;
        
        while(i < n)
        {
            if(nums[i] == nums[i-1]) {
                i++;
            } else {  //unique found
                nums[writeIdx] = nums[i]; // iterate the updated modified array on the go
                writeIdx++; 
                i++;
            }
        }
        return writeIdx; // = count of distinct elements
    }
}