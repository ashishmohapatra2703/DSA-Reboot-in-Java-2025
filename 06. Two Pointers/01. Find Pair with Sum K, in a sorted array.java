/*https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, 
find two numbers such that they add up to a specific target number. 
Let these two numbers be numbers[index1] and numbers[index2] 
where 1 <= index1 < index2 <= numbers.length.
Return the indices of the two numbers, index1 and index2, 
added by one as an integer array [index1, index2] of length 2.
The tests are generated such that there is exactly one solution. 
You may not use the same element twice.
Your solution must use only constant extra space.

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].*/

class Solution {
    public int[] twoSum(int[] numbers, int target) 
    {
        int[] twoEleSumK = new int[2];
        int low = 0;
        int high = numbers.length-1;
        
        while(low < high)
        {
            int pairSum = numbers[low] + numbers[high];
            
            if(pairSum == target) {
                twoEleSumK[0] = low+1;
                twoEleSumK[1] = high+1;
                return twoEleSumK;
            }
            else if(pairSum > target)
                high--;
            else if(pairSum < target)
                low++;
        }
        
        return twoEleSumK;
    }
}