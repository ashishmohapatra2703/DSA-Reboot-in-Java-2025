/* https://leetcode.com/problems/longest-repeating-character-replacement/
https://www.geeksforgeeks.org/problems/longest-repeating-character-replacement/1
You are given a string s and an integer k. You can choose any character of the string 
and change it to any other uppercase English character. 
You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get 
after performing the above operations.

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too. 
Input: s = "AABABCC", k = 2
Output: 5   
Input: s = "ADBD", k = 1
Output: 3
Explanation: Change 'B' into 'D'. now s = "ADDD" */

class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int i = 0;
        int j = 0; // i>j, window size = (i-j+1)
        HashMap<Character, Integer> charFreqMap = new HashMap<>();
        int maxFreqCharCountInWindow = 0;
        int maxSubStrAtMostKReplacement = 0;

        while(i<n) {
            Character iChar = s.charAt(i);
            charFreqMap.put(iChar, charFreqMap.getOrDefault(iChar,0) + 1);

            maxFreqCharCountInWindow = Math.max(charFreqMap.get(iChar), maxFreqCharCountInWindow);
            int window_size = (i-j+1);
            int charsNeedsToBeReplacedForSameInWindow = window_size - maxFreqCharCountInWindow;

            if(charsNeedsToBeReplacedForSameInWindow <= k) {
                maxSubStrAtMostKReplacement = Math.max(maxSubStrAtMostKReplacement, window_size);
                i++;
            }
            else if(charsNeedsToBeReplacedForSameInWindow > k) {
                while(charsNeedsToBeReplacedForSameInWindow > k && j<i) {
                    // shrink the window
                    Character jChar = s.charAt(j);
                    charFreqMap.put(jChar, charFreqMap.getOrDefault(jChar,0) - 1);
                    j++;
                    // since j is updated
                    charsNeedsToBeReplacedForSameInWindow = (i-j+1) - maxFreqCharCountInWindow;
                }
                i++;
            }
        }
        return maxSubStrAtMostKReplacement;
    }
}