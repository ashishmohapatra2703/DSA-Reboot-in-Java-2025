/* https://www.geeksforgeeks.org/problems/group-shifted-string/1
Given an array of strings (all lowercase letters), 
the task is to group them in such a way that 
all strings in a group are shifted versions of each other.
Two strings s1 and s2 are called shifted if the following conditions are satisfied:

s1.length = s2.length
s1[i] = s2[i] + m for 1 <= i <= s1.length  for a constant integer m

Input: arr = ["acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"]
Output: [["acd", "dfg", "wyz", "yab", "mop"], ["bdfh", "moqs"], ["a", "x"]] 
Explanation: All shifted strings are grouped together.
Input: arr = ["geek", "for", "geeks"]
Output: [["for"], ["geek"], ["geeks"]]
Input: arr = ["aaa", "adb", "bbd", "dbc", "bca"]
Output: [["aaa"], ["adb"], ["bbd"], ["bca"], ["dbc"]]   */

class Solution {
    public ArrayList<Integer> getKey(String str) {
        ArrayList<Integer> diffOfConsecutiveChar = new ArrayList<>();
        for(int j=1; j<str.length(); j++)
        {
            int diff = (str.charAt(j) - str.charAt(j-1) + 26) % 26; // circular alphabet
            diffOfConsecutiveChar.add(diff);
        }
        return diffOfConsecutiveChar;
    }
    
    public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {
        ArrayList<ArrayList<String>> groupedShiftedString = new ArrayList<>();
        HashMap<ArrayList<Integer> , ArrayList<String>> diffOfConsecutiveChar_To_GroupOfShiftedStr = new HashMap<>();
        
        for(String str: arr)
        {
            ArrayList<Integer> diffOfConsecutiveChar = getKey(str); 

            if(! diffOfConsecutiveChar_To_GroupOfShiftedStr.containsKey(diffOfConsecutiveChar)) {
                ArrayList<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                diffOfConsecutiveChar_To_GroupOfShiftedStr.put(diffOfConsecutiveChar, newGroup);
            }
            else {
                ArrayList<String> exisitingGroup = diffOfConsecutiveChar_To_GroupOfShiftedStr.get(diffOfConsecutiveChar);
                exisitingGroup.add(str);
            }
        }
        
        return new ArrayList<>(diffOfConsecutiveChar_To_GroupOfShiftedStr.values());
    }
} 