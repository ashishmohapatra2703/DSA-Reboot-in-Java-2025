/* You are given two strings word1 and word2. 
Merge the strings by adding letters in alternating order, starting with word1. 
If a string is longer than the other, append the additional letters onto the end of the merged string.
Return the merged string.

Example :
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s */

class Solution {
    public String mergeAlternately(String word1, String word2)
    {
        StringBuilder mergeAlt = new StringBuilder();
        int n = word1.length();
        int m = word2.length();

        int i,j;
        for(i=0,j=0; i<n && j<m; i++,j++)
        {
            mergeAlt.append(word1.charAt(i));
            mergeAlt.append(word2.charAt(j));
        }

        for(; i<n; i++) 
            mergeAlt.append(word1.charAt(i));
            
        for(; j<m; j++) 
            mergeAlt.append(word2.charAt(j));
        
        return mergeAlt.toString();
    }
}