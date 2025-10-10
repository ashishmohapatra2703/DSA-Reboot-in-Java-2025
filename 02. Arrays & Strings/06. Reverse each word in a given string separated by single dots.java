/*Given a String. Reverse each word in it where the words are separated by dots.

Input: S = "i.like.this.program.very.much"
Output:   i.ekil.siht.margorp.yrev.hcum  */

class Solution{
    String reverseWords(String S)
    {
        StringBuilder str = new StringBuilder(S);
        StringBuilder reverseWStr = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < str.length(); i++) 
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

        word.reverse();
        reverseWStr.append(word); //no more dot here , in the last word
        
        return reverseWStr.toString();
    }
}