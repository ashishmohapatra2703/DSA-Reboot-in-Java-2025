/* https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1
Given a string str of lowercase alphabets. The task is to
find the maximum occurring character in the string str. 

If more than one character occurs the maximum number of time 
then print the lexicographically smaller character.

Input:str = output
Output: t
Explanation:  t and u are the characters with the same frequency, 
            but t is lexicographically smaller.*/


//M-1
class Solution {
    //Function to find the maximum occurring character in a string.
    public static char getMaxOccuringChar(String line)
    {
        int[] charCount = new int[26]; 

        for(char ch : line.toCharArray()) 
        {
            charCount[ch-'a']++;
        }

        int mocResultIdx = 0;
        for(int i=0; i<26; i++)
        {
            mocResultIdx = (charCount[i] > charCount[mocResultIdx]) ? i : mocResultIdx;
        }

        return (char) (mocResultIdx+'a');
    }
    
}

//M-2 Optimised Space Complexity
class Solution
{
    //Function to find the maximum occurring character in a string.
    public char getMaxOccuringChar(String line)
    {
        HashMap<Character, Integer> charFreqMap = new HashMap<>();
        
        for(char ch : line.toCharArray())
        {
            charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0)+1);
        }
        
        char mocResult = line.charAt(0);
        for(char chKey : charFreqMap.keySet())
        {
            if(charFreqMap.get(chKey) > charFreqMap.get(mocResult) || 
                (charFreqMap.get(chKey) == charFreqMap.get(mocResult) && chKey < mocResult))
                {
                    mocResult = chKey;
                }
        }
        return mocResult;
    }
}