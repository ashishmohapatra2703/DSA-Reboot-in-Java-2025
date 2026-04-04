/*https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters 
Given a string s and an integer k, 
return the length of the longest substring of s 
such that the frequency of each character in this substring is greater than or equal to k.
if no such substring exists, return 0.

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.*/

// Typical sliding window does NOT work here, Not monotonic condition
// i.e, the logic of = if it becomes invalid, shrinking fixes it
class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        HashMap<Character, Integer> charToFreqMap = new HashMap<>();

        for(int i=0; i<n; i++) {
            Character iChar = s.charAt(i);
            Integer iCharValue = charToFreqMap.getOrDefault(iChar, 0) + 1;
            charToFreqMap.put(iChar, iCharValue);
        }

        for(int i=0; i<n; i++) {
            Character iChar = s.charAt(i);
            if(charToFreqMap.get(iChar) < k) {
                // Split and recurse 
                // Split AT the character whose freq<k => It can not be the part of the result substring
                int leftLongestSubStr = longestSubstring(s.substring(0, i), k);
                int rightLongestSubStr = longestSubstring(s.substring(i+1, n), k);
                return Math.max(leftLongestSubStr, rightLongestSubStr);
            }
        } 
        return n; //when no splitChar position found
    }
}