/* https://leetcode.com/problems/count-elements-with-maximum-frequency/description/
You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
(The frequency of an element is the number of occurrences of that element in the array.)

Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 
which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.  	*/

// M-1 Three passes
class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqHash = new HashMap<>();

        for (int n : nums) {
            freqHash.put(n, freqHash.getOrDefault(n, 0) + 1);
        }

        int maxFreq = 0;
        for (int freq : freqHash.values()) 
        {
            maxFreq = (freq > maxFreq) ? freq : maxFreq;
        }

        int countMaxFreqTotal = 0;
        for (int freq : freqHash.values()) 
        {
            if(freq == maxFreq)
                countMaxFreqTotal += maxFreq;
        }
        return countMaxFreqTotal;
    }
}

//M-2 One pass
class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqHash = new HashMap<>();
        int maxFreq = 0;
        int countMaxFreqTotal = 0;

        for (int n : nums) 
        {
            int nFreq = freqHash.getOrDefault(n, 0) + 1;
            freqHash.put(n, nFreq);

            if(nFreq > maxFreq) {
                maxFreq = nFreq;
                countMaxFreqTotal = maxFreq;
            } else if (nFreq == maxFreq) {
                countMaxFreqTotal += maxFreq;
            }
        }
        return countMaxFreqTotal;
    }
}