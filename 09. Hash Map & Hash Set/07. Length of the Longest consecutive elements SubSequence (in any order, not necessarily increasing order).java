/* https://leetcode.com/problems/longest-consecutive-sequence/description/
Given an unsorted array of integers nums, 
return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Input: nums = [1,0,1,2]
Output: 3  */

//M-1  T.C = O(n), S.C = O(n)
class Solution
{   
    // arr[] : the input array
    // N : size of the array arr[]
    
    //Function to return length of longest subsequence of consecutive integers.
    int findLongestConseqSubseq(int arr[], int N)
    {
        HashSet<Integer> uniqueEles = new HashSet<>();
        for(int x: arr)
        {
            uniqueEles.add(x);
        }
        
        int maxLenSS = 0;
        for(Integer ele: uniqueEles) //iterate in hashset
        {
            if(! uniqueEles.contains(ele-1)) // => this element is a starting point of a consecutive sub-sequence
            {
                int currentLenSS = 1;
                while(uniqueEles.contains(ele+currentLenSS))
                {
                    currentLenSS ++;
                }
                
                maxLenSS = Math.max(currentLenSS, maxLenSS);
            }
        }
        
        return maxLenSS;
    }
}

//M-2   T.C = Amortized O(n)
// the idea is to assume that the present value "i" is the center of the sequence in which it is present 
// then we will go left and right of it to find the length of its sequence
//// remove the elements because an element can only be part of only one consecutive sequence
class Solution {
    int longestConsecutive(int[] nums) 
    {
        HashSet<Integer> uniqueEles = new HashSet<>();
        for(int n: nums)
        {
            uniqueEles.add(n);
        }
        
        int maxLenSS = 0;
        for(int n: nums) // iterate in nums array , not in set because it will cause ConcurrentModificationException
        {
            if(uniqueEles.contains(n))
            {
                uniqueEles.remove(n);

                int prevEle = n-1;
                int currLeftLenSS = 0;
                int nextEle = n+1;
                int currRightLenSS = 0;

                while(uniqueEles.contains(prevEle)) //if prev present, check consecutively
                {
                    currLeftLenSS ++;
                    uniqueEles.remove(prevEle);
                    prevEle --;
                }

                while(uniqueEles.contains(nextEle)) //if next present, check consecutively
                {
                    currRightLenSS ++;
                    uniqueEles.remove(nextEle);
                    nextEle ++;
                }

                int currentLenSS = currLeftLenSS + 1 + currRightLenSS;  // +1 for element n itself
                maxLenSS = Math.max(currentLenSS, maxLenSS);
            }
        }
        
        return maxLenSS;
    }
}