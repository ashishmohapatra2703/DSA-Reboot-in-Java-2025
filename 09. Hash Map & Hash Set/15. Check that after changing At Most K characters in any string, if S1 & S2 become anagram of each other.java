/* https://www.geeksforgeeks.org/problems/check-if-two-strings-are-k-anagrams-or-not/1
Two strings are called k-anagrams if both of the below conditions are true.
1. Both have same number of characters.
2. Two strings can become anagram by changing at most k characters in a string.

Given two strings of lowercase alphabets and an integer value k, 
the task is to find if two strings are k-anagrams of each other or not.

Input: s1 = "fodr", s2 = "gork", k = 2
Output: true
Explanation: We can change 'f' -> 'g' and 'd' -> 'k' in s1.
Input: s1 = "geeks", s2 = "eggkf", k = 1
Output: false
Explanation: We can update or modify only 1 value but there is a need of modifying 2 characters i.e. 'g' and 'f' in s2.
Input: s1 = "adb", s2 = "fdab", k = 2
Output: false
Explanation: Both the strings have different numbers of characters. */

class Solution {
    boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length()) 
            return false;
            
        HashMap<Character, Integer> charFreqMap = new HashMap<>();  

        for(char c: s1.toCharArray())
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);

        for(char c: s2.toCharArray())
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) - 1);
        
        
        // sum of all the excess in s1 from s2
        int diffCount = 0;
        for(char c: charFreqMap.keySet()) {
            if(charFreqMap.get(c) > 0) 
            { 
            	// characters that are extra in s1 => need to be changed
                diffCount += charFreqMap.get(c);
            }
        }
        
        if(diffCount <= k)
            return true;
        return false;
    }
}

/* 
OR use sum of all the characters that are missing from s1
		int diffCount = 0;
        for(char c: charFreqMap.keySet()) {
            if(charFreqMap.get(c) < 0) 
            { 
                diffCount += - (charFreqMap.get(c)); // for absolute value
            }
        }
*/