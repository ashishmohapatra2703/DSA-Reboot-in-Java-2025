/* https://leetcode.com/problems/count-complete-subarrays-in-an-array/
https://www.geeksforgeeks.org/problems/equivalent-sub-arrays3731/1
You are given an array nums consisting of positive integers.
We call a subarray of an array complete if the following condition is satisfied:
The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
Return the number of complete subarrays.
A subarray is a contiguous non-empty part of an array.

Input: nums = [1,3,1,2,2]
Output: 4
Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].

Input: nums = [5,5,5,5]
Output: 10
Explanation: The array consists only of the integer 5, so any subarray is complete. 
The number of subarrays that we can choose is 10. */ 

class Solution {
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> distinctEleFromArr = new HashSet<>();
        for(int num: nums) {
            distinctEleFromArr.add(num);
        }
        int countDistinctEle = distinctEleFromArr.size();

        return countSubArrWithKDistinctEle(nums, countDistinctEle);
    }

    private int countSubArrWithKDistinctEle(int[] nums, int k) {
        return countSubArrWithAtMostKDistinctEle(nums, k) - countSubArrWithAtMostKDistinctEle(nums, k-1);
    }

    private int countSubArrWithAtMostKDistinctEle(int[] nums, int k)
    {
        HashMap<Integer, Integer> arrEleToFreqMap = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = nums.length;
        int countSubArrWithAtMostKDistinctEle = 0;

        while(i<n)
        {
            arrEleToFreqMap.put(nums[i], arrEleToFreqMap.getOrDefault(nums[i], 0) + 1);

            if(arrEleToFreqMap.size() <= k) { 
                countSubArrWithAtMostKDistinctEle += (i-j+1);
                i++;
            } else if(arrEleToFreqMap.size() > k) {
                while(arrEleToFreqMap.size() > k)
                {
                    arrEleToFreqMap.put(nums[j], arrEleToFreqMap.getOrDefault(nums[j], 0) - 1);
                    if(arrEleToFreqMap.get(nums[j]) == 0) {
                        arrEleToFreqMap.remove(nums[j]);
                    }
                    j++;    
                }
                if(arrEleToFreqMap.size() == k) {
                    countSubArrWithAtMostKDistinctEle += (i-j+1);
                }
                i++;
            }
        }
        return countSubArrWithAtMostKDistinctEle;
    }
}