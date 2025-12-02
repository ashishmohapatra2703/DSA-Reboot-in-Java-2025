/* https://www.geeksforgeeks.org/problems/print-anagrams-together/1
https://leetcode.com/problems/group-anagrams/submissions/1827885188/
Given an array of strings, return all groups of strings that are anagrams. 
The strings in each group must be arranged in the order of their appearance in the original array. 

Input: arr[] = ["act", "god", "cat", "dog", "tac"]
Output: [["act", "cat", "tac"], ["god", "dog"]]
Explanation: There are 2 groups of anagrams "god", "dog" make group 1. "act", "cat", "tac" make group 2.

Input: arr[] = ["no", "on", "is"]
Output: [["is"], ["no", "on"]]
Explanation: There are 2 groups of anagrams "is" makes group 1. "no", "on" make group 2.

Input: arr[] = ["listen", "silent", "enlist", "abc", "cab", "bac", "rat", "tar", "art"]
Output: [["abc", "cab", "bac"], ["listen", "silent", "enlist"], ["rat", "tar", "art"]]
Explanation: 
Group 1: "abc", "bac", and "cab" are anagrams.
Group 2: "listen", "silent", and "enlist" are anagrams.
Group 3: "rat", "tar", and "art" are anagrams.*/

class Solution {
    public ArrayList<ArrayList<String>> anagrams(String[] arr) {
        ArrayList<ArrayList<String>> groupedAnagram = new ArrayList<>();
        HashMap<HashMap<Character, Integer> , ArrayList<String>> charFreqMap_To_GroupOfAnagramStr = new HashMap<>();
        
        for(int i=0; i<arr.length; i++)
        {
            String str = arr[i];
            HashMap<Character, Integer> charFreqMap = new HashMap<>();
            for(char ch: str.toCharArray()) { 
                charFreqMap.put(ch, charFreqMap.getOrDefault(ch,0) + 1);
            }
            
            if(! charFreqMap_To_GroupOfAnagramStr.containsKey(charFreqMap)) {
                ArrayList<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                charFreqMap_To_GroupOfAnagramStr.put(charFreqMap, newGroup);
            }
            else {
                ArrayList<String> exisitingGroup = charFreqMap_To_GroupOfAnagramStr.get(charFreqMap);
                exisitingGroup.add(str);
            }
        }

        
        // final result only from "values" of bigger HashMap
        for(ArrayList<String> group : charFreqMap_To_GroupOfAnagramStr.values()) {
            groupedAnagram.add(group);
        }
        
        return groupedAnagram;
    }
}