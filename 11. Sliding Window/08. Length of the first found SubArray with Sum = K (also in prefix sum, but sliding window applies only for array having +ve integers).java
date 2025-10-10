/* https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1
Given an array arr[] containing only non-negative integers, 
your task is to find a continuous subarray (a contiguous sequence of elements) 
whose sum equals a specified value target. 
You need to return the 1-based indices of the leftmost and rightmost elements of this subarray. 
You need to find the first subarray whose sum is equal to the target.
Note: If no such array is possible then, return [-1].

Input: arr[] = [1, 2, 3, 7, 5], target = 12
Output: [2, 4]
Explanation: The sum of elements from 2nd to 4th position is 12.

Input: arr[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], target = 15
Output: [1, 5]
Explanation: The sum of elements from 1st to 5th position is 15.

Input: arr[] = [5, 3, 4], target = 2
Output: [-1]
Explanation: There is no subarray with sum 2.  */

class Solution {
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        ArrayList<Integer> windowSumK = new ArrayList<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = arr.length;
        int sumOfSubArr = 0;

        while(i<n)
        {
            sumOfSubArr += arr[i];

            if(sumOfSubArr < target) { 
                i++;
            } else if(sumOfSubArr == target) { 
                windowSumK.add(j+1);
                windowSumK.add(i+1);
                return windowSumK;
                // i++; // redundant
            } else if(sumOfSubArr > target) {
                while(sumOfSubArr > target)
                {
                    sumOfSubArr -= arr[j];
                    j++;    
                }
                if(sumOfSubArr == target) {
                    windowSumK.add(j+1);
                    windowSumK.add(i+1);
                    return windowSumK;
                }
                i++;
            }
        }

        windowSumK.add(-1); // base condition if none found
        return windowSumK;
    }
}
