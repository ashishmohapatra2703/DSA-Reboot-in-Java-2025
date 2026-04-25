/* https://leetcode.com/problems/valid-palindrome-ii/
Given a string s, return true if the s can be palindrome 
after deleting at most one character from it.

Input: s = "aba"
Output: true
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'. */

class Solution {
    public boolean validPalindrome(String s) {
        int n = s.length();
        int l = 0;
        int h = n-1;

        while(l < h) {
            if(s.charAt(l) == s.charAt(h)) {
                l++;
                h--;
            } else {
                return isPalindromeSubString(s, l+1, h) ||
                        isPalindromeSubString(s, l, h-1);
            }
        }
        return true;
    }

    private boolean isPalindromeSubString(String s, int l, int h) {
        while(l < h) {
            if(s.charAt(l) == s.charAt(h)) {
                l++;
                h--;
            } else {
                return false;
            }
        }
        return true;
    }
}