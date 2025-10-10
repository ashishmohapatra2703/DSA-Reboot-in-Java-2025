/* https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
https://www.geeksforgeeks.org/problems/longest-distinct-characters-in-string5848/1
Given a string s, find the length of the longest substring without duplicate characters.

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.  */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = s.length();
        int maxWindow = 0;

        // We expand i to include new characters.
        // If we see a duplicate, we shrink from j until that duplicate is removed, in window [j....i]
        while(i<n)
        {
            Character iChar = s.charAt(i);
            charCount.put(iChar, charCount.getOrDefault(iChar, 0) + 1);

            if(i-j+1 == charCount.size()) { // => all distint in this window 
                maxWindow = Math.max(i-j+1, maxWindow);
                i++;
            } else {
                while(i-j+1 > charCount.size())
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


/* Note:  template of Variable Size sliding window problem

while(i < size()){

    // Calculation's happen's here
-----------------------------------------------
    if(condition < k){
        i++;
    }
-----------------------------------------------

-----------------------------------------------
    else if(condition == k){
        // ans <-- calculation
        i++;
    }
----------------------------------------------

----------------------------------------------
    else if(condition > k){
        while(condition > k){
            // remove calculation for i
            j++;
        }
        i++;
    }
----------------------------------------------
}
return ans; */