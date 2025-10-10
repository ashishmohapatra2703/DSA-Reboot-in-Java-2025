/* Given a String S, reverse the string without reversing its individual words. 
Words are separated by dots.

Input:  S = i.like.this.program.very.much
Output: much.very.program.this.like.i      */

class Solution 
{
    //Function to reverse words in a given string.
    String reverseWords(String S)
    {
        StringBuilder str = new StringBuilder(S);
        StringBuilder reverseWStr = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) 
        {
            if(str.charAt(i) != '.')
            {
                word.append(str.charAt(i));
            }
            else if(str.charAt(i) == '.')
            {
                word.reverse();
                reverseWStr.append(word).append('.');
                word = new StringBuilder();
            }
        }

        // Append the last word outside the loop (if any)
        // OR can have condition if(i==0) inside the loop
        word.reverse();
        reverseWStr.append(word); //no more dot here
        
        return reverseWStr.toString();
    }
}

//Note - Strings in Java are immutable, so used StringBuilder