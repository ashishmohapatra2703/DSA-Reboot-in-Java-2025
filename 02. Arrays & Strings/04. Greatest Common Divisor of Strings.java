/*https://leetcode.com/problems/greatest-common-divisor-of-strings/description/

For two strings s and t, we say "t divides s" if and only if s = t + ... + t 
(i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

Example 1:
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"

Example 2:
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"

Example 3:
Input: str1 = "LEET", str2 = "CODE"
Output: ""*/

//M-1
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int gcdOfStringsLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdOfStringsLength);
    }

    // a = dividend and b = divisor
    private int gcd(int a, int b) {  // gcd / hcf function (iterative)
        int aModb = a%b;
        while (aModb != 0) {
            a = b;
            b = aModb;
            aModb = a%b;
        }
        return b;
    }
}

//M-2 using eulid gcd formula recursive
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if( !(str1+str2).equals(str2+str1)) {
            return "";
        }
        
        int gcdOfLengthOf2Strings = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdOfLengthOf2Strings); // either can use str2.substring
    }

    private int gcd(int a, int b) {
        int aModb = a%b;
        if(aModb == 0)
            return b;

        return gcd(b, aModb);
    }
}
