/* https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
Given two strings s and p, 
return an array of all the start indices of p's anagrams in s. 
You may return the answer in any order.

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab" */

// ANAGRAMS == CharFreqMap are equal
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        List<Integer> startIdxOfPAnagramInS = new ArrayList<>();
        if(pLen > sLen) // p should be always smaller than s
            return startIdxOfPAnagramInS; //if not => return blank

        // create p's charFreqMap
        HashMap<Character, Integer> pCharFreqMap = new HashMap<>();
        for(int i=0; i<pLen; i++) {
            pCharFreqMap.put(p.charAt(i), pCharFreqMap.getOrDefault(p.charAt(i), 0) + 1);
        }

        HashMap<Character, Integer> sCharFreqMap = new HashMap<>();
        // fixed PLen size sliding window pattern
        for(int i=0; i<pLen; i++) { 
            Character iChar = s.charAt(i);
            sCharFreqMap.put(iChar, sCharFreqMap.getOrDefault(iChar, 0) + 1); // create window of size pLen first
        }
        if(pCharFreqMap.equals(sCharFreqMap)) {
            startIdxOfPAnagramInS.add(0);
        }

        for(int i=pLen; i<sLen; i++)
        {
            Character frontChar = s.charAt(i);
            Character backChar = s.charAt(i-pLen); //slide the window => front add + back subtract
            sCharFreqMap.put(frontChar, sCharFreqMap.getOrDefault(frontChar, 0) + 1);
            sCharFreqMap.put(backChar, sCharFreqMap.get(backChar) - 1);
            if(sCharFreqMap.get(backChar) == 0) {
                sCharFreqMap.remove(backChar);
            }

            if(pCharFreqMap.equals(sCharFreqMap)) {
                startIdxOfPAnagramInS.add(i-pLen+1); //start index of the current window
            }
        }

        return startIdxOfPAnagramInS;
    }
}