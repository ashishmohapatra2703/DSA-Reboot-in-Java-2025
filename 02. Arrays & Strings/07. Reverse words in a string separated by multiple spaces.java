/* Given an input string s, reverse the order of the words. A word is defined as a 
sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. 
The returned string should only have a single space separating the words. Do not include any extra spaces.

Input: s = "      hello world   "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.*/ 

class Solution {
    public String reverseWords(String s) 
    {
        StringBuilder str = new StringBuilder(s);
        StringBuilder reverseWStr = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) 
        {
            if (str.charAt(i) != ' ') 
            {
                word.append(str.charAt(i));
            } 
            else if (str.charAt(i) == ' ' && word.length() > 0) // handle trailing zeros (at the end of given input string)
            {
                word.reverse();
                reverseWStr.append(word);
                word = new StringBuilder(); // reset

                while (i-1 >= 0 && str.charAt(i-1) == ' ') { // ignore multiple consecutive spaces in between
                    i--;
                }

                if(i!=0) {  //not the last word
                    reverseWStr.append(" "); 
                }
            }
        }

        // Append the last word (if any)
        word.reverse();
        reverseWStr.append(word); //no more space append here
        
        return reverseWStr.toString();
    }
}