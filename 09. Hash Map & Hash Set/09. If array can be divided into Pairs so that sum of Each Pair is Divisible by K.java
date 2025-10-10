/* https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/description/
https://www.geeksforgeeks.org/problems/array-pair-sum-divisibility-problem3257/1

Given an array of integers nums and a number k, write a function that returns true 
if given array can be divided into pairs such that sum of every pair is divisible by k.

Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).

Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way 
to divide arr into 3 pairs each with sum divisible by 10. */


// Logic = Pairs should be of format 
// (K*n + x) and (K*m + (K-x)) -> in equal numbers, to be disivible by K
// OR (K*n) and (K*m) -> in even numbers, to be disivible by K
// OR (K*n + K/2) and (K*m + K/2) -> in even numbers, to be disivible by K

class Solution {
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> kDivRemainderFreqMap = new HashMap();

        for(int num: arr) {
            int kDivRemainder = ((num % k) + k) % k; // to be in the range of [0, k-1] when num is negative
            kDivRemainderFreqMap.put(kDivRemainder , kDivRemainderFreqMap.getOrDefault(kDivRemainder,0)+1);
        }

        for(int kDivRemainder : kDivRemainderFreqMap.keySet()) 
        {
            int kDivRemainderFreq = kDivRemainderFreqMap.get(kDivRemainder);
            int kDivRemainderComplementFreq = kDivRemainderFreqMap.getOrDefault(k - kDivRemainder, 0);

            if(kDivRemainder == 0 && kDivRemainderFreq % 2 == 1) {
                return false;
            } else if(2 * kDivRemainder == k && kDivRemainderFreq % 2 == 1) { // do not check kDivRemainder == k/2 => will fail when k is odd
                return false;
            } else if (kDivRemainder != 0 && kDivRemainderFreq != kDivRemainderComplementFreq) {
                return false;
            }
        }

        return true;
    }
}