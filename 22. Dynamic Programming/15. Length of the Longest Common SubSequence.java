/* https://leetcode.com/problems/longest-common-subsequence/description/ 
Given two strings text1 and text2, return the length of their longest common subsequence. 
If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with 
some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3. */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1][len2];
        for(int[] row : dp) 
            Arrays.fill(row, -1);

        return lenLCS(text1, text2, 0, 0, len1, len2, dp);
    }
    private int lenLCS(String text1, String text2, int i, int j, int len1, int len2, int[][] dp) {
        if(i == len1 || j == len2)
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        int takeItakeJ = 0;
        int takeIskipJ = 0;
        int skipItakeJ = 0;
        if(text1.charAt(i) == text2.charAt(j)) {
            takeItakeJ = 1 + lenLCS(text1, text2, i+1, j+1, len1, len2, dp);
            return dp[i][j] = takeItakeJ;
        }
        else {  //if(text1.charAt(i) != text2.charAt(j))
            takeIskipJ = lenLCS(text1, text2, i, j+1, len1, len2, dp);
            skipItakeJ = lenLCS(text1, text2, i+1, j, len1, len2, dp);
            return dp[i][j] = Math.max(takeIskipJ, skipItakeJ);
        }
    }
}