/* https://leetcode.com/problems/most-frequent-even-element/description/
Given an integer array nums, return the most frequent even element.
If there is a tie, return the smallest one. If there is no such element, return -1.

Input: nums = [0,1,2,2,4,4,1]
Output: 2
Explanation: The even elements are 0, 2, and 4. 
Of these, 2 and 4 appear the most. We return the smallest one, which is 2.     */

// M-1  Two pass
class Solution {
    public int mostFrequentEven(int[] nums) 
    {
        Map<Integer, Integer> freqHash = new HashMap<>();
        
        for(int n : nums)
        {
            if(n % 2 == 0) //if even
                freqHash.put(n, freqHash.getOrDefault(n, 0)+1);
        }
        
        int maxfNum = -1;
        int maxfNumFreq = 0;
        for(int n : freqHash.keySet())
        {
            int nFreq = freqHash.get(n);
            
            if(nFreq > maxfNumFreq ||  
                (nFreq == maxfNumFreq && n < maxfNum))
                {
                    maxfNum = n;
                    maxfNumFreq = nFreq;
                }
        }
        return maxfNum;
    }
}

//M-2  One pass
class Solution {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> freqHash = new HashMap<>(); // {n , freq}
        int maxfNum = -1;
        int maxfNumFreq = 0;

        for (int n : nums) 
        {
            if (n % 2 == 0) // if even
            {
                int nFreq = freqHash.getOrDefault(n, 0) + 1;
                freqHash.put(n, nFreq);

                if (nFreq > maxfNumFreq ||
                        (nFreq == maxfNumFreq && n < maxfNum)) {
                    maxfNum = n;
                    maxfNumFreq = nFreq;
                }
            }
        }
        return maxfNum;
    }
}
