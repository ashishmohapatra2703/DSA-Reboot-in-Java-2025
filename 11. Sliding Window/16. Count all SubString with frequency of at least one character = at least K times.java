/*https://leetcode.com/problems/count-substrings-with-k-frequency-characters-i/description/
Given a string s and an integer k, 
return the total number of substrings of s 
where at least one character appears at least k (>= k) times.

Input: s = "abacb", k = 2
Output: 4
Explanation:
The valid substrings are:
"aba" (character 'a' appears 2 times).
"abac" (character 'a' appears 2 times).
"abacb" (character 'a' appears 2 times).
"bacb" (character 'b' appears 2 times).

Input: s = "abcde", k = 1
Output: 15
Explanation:
All substrings are valid because every character appears at least once. */

class Solution {
    public int numberOfSubstrings(String s, int k) {
        HashMap<Character, Integer> charToFreqMap = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = s.length();
        int maxFreqValueInMap = 0;
        int countSubStrAtLeast1CharFreqAtLeastK = 0; 

        while(i<n)
        {
            Character iChar = s.charAt(i);
            Integer iCharValue = charToFreqMap.getOrDefault(iChar, 0) + 1;
            charToFreqMap.put(iChar, iCharValue);
            maxFreqValueInMap = Math.max(iCharValue, maxFreqValueInMap);

            if(maxFreqValueInMap < k) { 
                i++;
            } else if(maxFreqValueInMap >= k) {
                while(maxFreqValueInMap >= k)
                {
                    countSubStrAtLeast1CharFreqAtLeastK += (n-i); 

                    Character jChar = s.charAt(j);
                    Integer jCharValue = charToFreqMap.get(jChar);
                    if(jCharValue == maxFreqValueInMap) {
                        maxFreqValueInMap--;
                    }
                    charToFreqMap.put(jChar, jCharValue-1);
                    j++;    
                }
                i++;
            }
        }
        return countSubStrAtLeast1CharFreqAtLeastK;
    }
}

// M-2 using easier AtMostK pattern
// count(at least one char ≥ k) = total_substrings - count(all chars < k)
// total_substrings = n*(n+1) /2
