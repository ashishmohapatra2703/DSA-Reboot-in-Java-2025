/* https://www.geeksforgeeks.org/problems/isomorphic-strings-1587115620/1
https://leetcode.com/problems/isomorphic-strings/

Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t & vice-versa.
All occurrences of a character must be replaced with another character 
while preserving the order of characters. No two characters may map to the same character, 
but a character may map to itself.

Input: s = "egg", t = "add"
Output: true
Explanation:
The strings s and t can be made identical by:
Mapping 'e' to 'a'.
Mapping 'g' to 'd'.

Input: s = "foo", t = "bar"
Output: false
Explanation:
The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'. */

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())
            return false;

        int n = s.length();
        HashMap<Character, Character> sTotMapping = new HashMap<>();
        HashMap<Character, Character> tToSMapping = new HashMap<>();

        for(int i=0; i<n; i++) 
        {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if(! sTotMapping.containsKey(sChar)) {
                sTotMapping.put(sChar, tChar);
            } else {
                if(sTotMapping.get(sChar) != tChar)
                    return false;
            }

            if(! tToSMapping.containsKey(tChar)) {
                tToSMapping.put(tChar, sChar);
            } else {
                if(tToSMapping.get(tChar) != sChar)
                    return false;
            }
        }
        return true;
    }
}


//Similar Question
/*https://leetcode.com/problems/word-pattern/description/
Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between 
a letter in pattern and a non-empty word in s. 
Specifically:
Each letter in pattern maps to exactly one unique word in s.
Each unique word in s maps to exactly one letter in pattern.
No two letters map to the same word, and no two words map to the same letter.

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Explanation:
The bijection can be established as:
'a' maps to "dog".
'b' maps to "cat".*/

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) 
            return false;

        int n = pattern.length();
        ArrayList<String> str = new ArrayList<>(Arrays.asList(s.split(" ")));
        HashMap<Character, String> patternToStrMapping = new HashMap<>();
        HashMap<String, Character> strToPatternMapping = new HashMap<>();

        for(int i=0; i<n; i++) 
        {
            char pChar = pattern.charAt(i);
            String word = words[i];
            if(! patternToStrMapping.containsKey(pChar)) {
                patternToStrMapping.put(pChar, word);
            } else {
                if(patternToStrMapping.get(pChar).equals(word) == false)
                    return false;
            }

            if(! strToPatternMapping.containsKey(word)) {
                strToPatternMapping.put(word, pChar);
            } else {
                if(strToPatternMapping.get(word) != pChar)
                    return false;
            }
        }
        return true;
    }
}