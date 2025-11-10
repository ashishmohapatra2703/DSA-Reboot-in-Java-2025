/* https://www.naukri.com/code360/problems/distinct-characters_2221410
https://takeuforward.org/plus/dsa/problems/longest-substring-with-at-most-k-distinct-characters
Given a string s and an integer k.
Find the length of the longest substring with at most k distinct characters.

Examples:
Input : s = "aababbcaacc" , k = 2
Output : 6
Explanation : The longest substring with at most two distinct characters is "aababb".
The length of the string 6.

Input : s = "abcddefg" , k = 3
Output : 4
Explanation : The longest substring with at most three distinct characters is "bcdd".
The length of the string 4. */

import java.util.HashMap;

public class Solution {
	public static int kDistinctChars(int k, String str) {
		HashMap<Character, Integer> eleCount = new HashMap<>();
        int i = 0;
        int j = 0; // i>j, window size = i-j+1
        int n = str.length();
        int maxSubStrAtMostKDistinct = 0;

        while(i<n)
        {
			Character iChar = str.charAt(i);
            eleCount.put(iChar, eleCount.getOrDefault(iChar, 0) + 1);

            if(eleCount.size() <= k) { 
                maxSubStrAtMostKDistinct = Math.max(i-j+1, maxSubStrAtMostKDistinct);
                i++;
            } else if(eleCount.size() > k) {
                while(eleCount.size() > k)
                {
					Character jChar = str.charAt(j);
                    eleCount.put(jChar, eleCount.getOrDefault(jChar, 0) - 1);
					if(eleCount.get(jChar) == 0) {
                        eleCount.remove(jChar);
                    }
                    j++;    
                }
                i++;
            }
        }

        return maxSubStrAtMostKDistinct;
	}
}
