/*Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', 
and they can appear in both lower and upper cases, more than once.

Input: s = "leetcode"
Output: "leotcede"   */

class Solution {
    public String reverseVowels(String s) {
        StringBuilder str = new StringBuilder(s);
        int n = str.length();

        int i = 0;
        int j = n - 1;

        while (i <= j) 
        {
            if (!isVowel(str.charAt(i)))
                i++;
            else if (!isVowel(str.charAt(j)))
                j--;
            else if (isVowel(str.charAt(i)) && isVowel(str.charAt(j))) {
                swap(str, i, j);
                i++;
                j--;
            }
        }
        
        return str.toString();
    }

    private void swap(StringBuilder str, int i, int j) {
        char temp = str.charAt(i);
        str.setCharAt(i, str.charAt(j));
        str.setCharAt(j, temp);
    }

    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
}