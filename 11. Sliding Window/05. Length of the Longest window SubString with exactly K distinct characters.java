/* https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
You are given a string s consisting only lowercase alphabets and an integer k. 
Your task is to find the length of the longest substring that contains exactly k distinct characters.
Note : If no such substring exists, return -1. 

Input: s = "aabacbebebe", k = 3
Output: 7
Explanation: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.

Input: s = "aaaa", k = 2
Output: -1
Explanation: There's no substring with 2 distinct characters.

Input: s = "aabaaab", k = 2
Output: 7
Explanation: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b', making it the longest valid substring.*/

class Solution {
    public int longestKSubstr(String s, int k) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = s.length();
        int maxWindow = -1;

        while(i<n)
        {
            Character iChar = s.charAt(i);
            charCount.put(iChar, charCount.getOrDefault(iChar, 0) + 1);

            if(charCount.size() < k) { 
                i++;
            } else if(charCount.size() == k) { // => k distint chars in this window 
                maxWindow = Math.max(i-j+1, maxWindow);
                i++;
            } else if(charCount.size() > k) {
                while(charCount.size() > k)
                {
                    Character jChar = s.charAt(j);
                    charCount.put(jChar, charCount.getOrDefault(jChar, 0) - 1);
                    if(charCount.get(jChar) == 0) {
                        charCount.remove(jChar);
                    }
                    j++;    
                }
                i++;
            }
        }

        return maxWindow;
    }
}