/* Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, 
be stored in the input character array chars. 
Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only *constant extra space*.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".*/ 

// M-1  T.C= O(n) , S.C = O(n)
class Solution {
    public int compress(char[] chars) {
        if (chars.length == 1)
            return 1;

        // if length >= 2
        StringBuilder compressed = new StringBuilder();

        for(int i=0; i<chars.length; ) 
        {
            int count = 1;
            char currentChar = chars[i];
            int j=i+1;
            for( ; j<chars.length && chars[j]==currentChar; j++) {
                count++;
            }

            compressed.append(currentChar);
            if(count > 1)
                compressed.append(count);

            i = j; //jump for the next group of consecutive characters
        }

        for(int i=0; i<compressed.length(); i++) {
            chars[i] = compressed.charAt(i);
        }
        return compressed.length();
    }
}


//M-2  T.C= O(n) , S.C = O(1)
class Solution {
    public int compress(char[] chars) {
        if (chars.length == 1)
            return 1;

        // if length >= 2
        int newIdx = 0; // will end giving the compressed string length

        for (int i = 0; i < chars.length; ) {
            int count = 1;
            char currentChar = chars[i];
            int j = i + 1;
            for (; j < chars.length && chars[j] == currentChar; j++) {
                count++;
            }

            chars[newIdx++] = currentChar;
            if (count > 1) {
                String multiDigitCountString = Integer.toString(count);
                for (char digit : multiDigitCountString.toCharArray()) {
                    chars[newIdx++] = digit;
                }
            }

            i = j; // jump for the next group of consecutive characters
        }

        return newIdx;
    }
}