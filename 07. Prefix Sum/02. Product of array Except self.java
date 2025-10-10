/*Given an integer array nums, return an array answer such that 
answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]           */

// M-1  T.C= O(n) , S.C = O(n)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        int[] result = new int[n];

        prefixSum[0] = nums[0];
        for(int i=1; i<n; i++) {
            prefixSum[i] = prefixSum[i-1] * nums[i];
        }
        
        suffixSum[n-1] = nums[n-1];
        for(int j=n-2; j>=0; j--) {
            suffixSum[j] = suffixSum[j+1] * nums[j];
        }
        
        for(int i=0; i<n; i++) 
        {
            int leftNumsProduct = (i!=0) ? prefixSum[i-1] : 1;
            int rightNumsProduct = (i!=n-1) ? suffixSum[i+1] : 1;
            result[i] = leftNumsProduct * rightNumsProduct; //at index i
        }
        return result;
    }
}

//M-2  T.C= O(n) , S.C = O(1)
//(The output array does not count as extra space for space complexity analysis.)

// we don't actually need seperate array to store prefix product and suffix products, 
// we can do all the approach discussed in method 1 directly onto the final result array.

class Solution {
    public int[] productExceptSelf(int[] nums) 
    {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);

        int prefixSum = nums[0];
        for(int i=1; i<n; i++) {
            result[i] *= prefixSum;
            prefixSum *= nums[i];
        }
        
        int suffixSum = nums[n-1];
        for(int j=n-2; j>=0; j--) {
            result[j] *= suffixSum;
            suffixSum *= nums[j];
        }
        
        return result;
    }
}