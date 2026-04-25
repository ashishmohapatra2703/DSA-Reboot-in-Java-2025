/* (LC hard) https://leetcode.com/problems/minimum-window-substring/description/
https://www.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1

Given two strings s and t of lengths m and n respectively, return the 
minimum window substring of s such that every character in t (including duplicates) is included 
in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.*/

class Solution {
    public String minWindow(String s, String t) {
        int n = t.length();
        HashMap<Character, Integer> tStrCharCountMap = new HashMap<>();
        for(Character ch: t.toCharArray()) {
            tStrCharCountMap.put(ch, tStrCharCountMap.getOrDefault(ch,0) + 1);
        }

        int i = 0;
        int j = 0; // i>j; window = (i-j+1)
        String minSSubStrWithAtLeastAllTStrChar = "";
        int minSSubStrWithAtLeastAllTStrCharLen = Integer.MAX_VALUE;
        int m = s.length();
        HashMap<Character, Integer> sStrCharCountMap = new HashMap<>();
        int numOfMatchingCharsBwSSubStrAndT = 0;

        while(i<m) {
            //add i
            Character iChar = s.charAt(i);
            sStrCharCountMap.put(iChar, sStrCharCountMap.getOrDefault(iChar,0) + 1);
            //We only count supply until demand is fulfilled
            if(tStrCharCountMap.containsKey(iChar) && 
                    sStrCharCountMap.get(iChar) <= tStrCharCountMap.get(iChar)) {
                numOfMatchingCharsBwSSubStrAndT++;
            }

            if(numOfMatchingCharsBwSSubStrAndT < n) { 
                i++;
            } else if(numOfMatchingCharsBwSSubStrAndT == n) {
                while(numOfMatchingCharsBwSSubStrAndT == n)
                {
                    if((i-j+1) < minSSubStrWithAtLeastAllTStrCharLen) {
                        minSSubStrWithAtLeastAllTStrCharLen = (i-j+1);
                        minSSubStrWithAtLeastAllTStrChar = s.substring(j, i+1); //beginIdx(inclusive), endIndex(exclusive)
                    }

                    //remove j
                    Character jChar = s.charAt(j);
                    sStrCharCountMap.put(jChar, sStrCharCountMap.getOrDefault(jChar,0) - 1);
                    //check Did we remove a REQUIRED occurrence :(
                    if(tStrCharCountMap.containsKey(jChar) && 
                            sStrCharCountMap.get(jChar) < tStrCharCountMap.get(jChar)) {
                        numOfMatchingCharsBwSSubStrAndT--;
                    }
                    j++;    
                }
                i++;
            }
        }
        return minSSubStrWithAtLeastAllTStrChar;
    }
}