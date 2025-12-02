/*https://leetcode.com/problems/valid-anagram/description/
Given two strings s and t, 
return true if t is an anagram of s, and false otherwise.

Input: s = "anagram", t = "nagaram"
Output: true

Input: s = "rat", t = "car"
Output: false */

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
            
        HashMap<Character, Integer> charFreqMap = new HashMap<>();
        
        for(int i=0; i<s.length(); i++)
            charFreqMap.put(s.charAt(i), charFreqMap.getOrDefault(s.charAt(i),0) + 1);
        
        for(int i=0; i<t.length(); i++)
        {
            charFreqMap.put(t.charAt(i), charFreqMap.getOrDefault(t.charAt(i),0) - 1);
            if(charFreqMap.get(t.charAt(i)) < 0)
                return false;
        }
        return true;
    }
}