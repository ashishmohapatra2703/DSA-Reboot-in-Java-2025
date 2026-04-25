/* https://leetcode.com/problems/sum-of-unique-elements/description/
You are given an integer array nums. 
The unique elements of an array are the elements that appear exactly once in the array.
Return the sum of all the unique elements of nums.

Input: nums = [1,2,3,2]
Output: 4
Explanation: The unique elements are [1,3], and the sum is 4.

Input: nums = [1,1,1,1,1]
Output: 0
Explanation: There are no unique elements, and the sum is 0.  */


//M-1 Two Pass
class Solution {
    public int sumOfUnique(int[] nums) 
    {
        Map<Integer, Integer> freqHash = new HashMap<>();
        int sumofFreq1 = 0;

        for(int n : nums) 
        {
            freqHash.put(n, freqHash.getOrDefault(n, 0) + 1);
        }

        for(int n: freqHash.keySet())
        {
            int nFreq = freqHash.get(n);
            if (nFreq == 1) {
                sumofFreq1 += n;
            }
        }
        return sumofFreq1;    
    }
}

//M-2 One pass
class Solution {
    public int sumOfUnique(int[] nums) 
    {
        Map<Integer, Integer> freqHash = new HashMap<>();
        int sumofFreq1 = 0;

        for(int n : nums) 
        {
            int nFreq = freqHash.getOrDefault(n, 0) + 1;
            freqHash.put(n, nFreq);

            if (nFreq == 1) {
                sumofFreq1 += n;
            } else if (nFreq == 2) { // number was previously added but is no longer unique.
                sumofFreq1 -= n;
            }
        }

        return sumofFreq1;    
    }
}