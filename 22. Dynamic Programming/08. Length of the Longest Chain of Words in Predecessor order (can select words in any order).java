/* https://leetcode.com/problems/longest-string-chain/description/
https://www.geeksforgeeks.org/problems/longest-string-chain/1

You are given an array of words where each word consists of lowercase English letters.
wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA 
without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, 
word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
Return the length of the longest possible word chain with words chosen from the given list of words.

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"]. */

//passed in LC, failed in gfg
//t.C = O(nlogn + n²M) where n = the number of words and M = maximum word length.
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        int n = words.length;

        int[] dp = new int[n]; //max length of word chain ending at i
        Arrays.fill(dp, 1);
        int maxLPOS = 1; // longest predecessor order subseq.

        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                //Check all previous words to see if they are predecessors
                if(isJthWordPredecessorOfIthWord(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
            maxLPOS = Math.max(maxLPOS, dp[i]);
        }

        return maxLPOS;
    }

    private boolean isJthWordPredecessorOfIthWord(String prevWord, String currWord) {
        int m = prevWord.length(), n = currWord.length();
        if(n != m+1)
            return false;
        
        int i = 0, j = 0;
        int extraCharCountAtCurrWord = 0;
        while(i < m && j < n) {
            if(prevWord.charAt(i) == currWord.charAt(j)) {
                i++;
                j++;
            } else if(prevWord.charAt(i) != currWord.charAt(j)) {
                if(extraCharCountAtCurrWord == 0) {
                    extraCharCountAtCurrWord++;
                    j++;
                } else if (extraCharCountAtCurrWord > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

//Optimized
//t.C = O(nlogn + nM²) where n = the number of words and M = maximum word length.
class Solution {
    public int longestStringChain(String words[]) {
        Arrays.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        int n = words.length;

        int[] dp = new int[n]; //max length of word chain ending at i
        Arrays.fill(dp, 1);
        int maxLPOS = 1; // longest predecessor order subseq.

        HashMap<String, Integer> wordToIdxMap = new HashMap<>();
    
        for(int i=0; i<n; i++) {
            String currWord = words[i];
            // Generate all possible predecessors of current word words[i] and 
            // lookup for each of them they exist as words[j] and jump directly to valid j (in O(1) operation)
            for(int k=0; k<currWord.length(); k++) {
                String predecessor = currWord.substring(0, k) + currWord.substring(k + 1);
                // O(k) + O(M - k) = Total per iteration: O(M) inside inner loop of O(M)
                
                if(wordToIdxMap.containsKey(predecessor)) {
                    int j = wordToIdxMap.get(predecessor); // words[j] = valid predecessor having 1 char less //j<i always since sorted based on length
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
            wordToIdxMap.put(currWord, i);
            maxLPOS = Math.max(maxLPOS, dp[i]);
        }

        return maxLPOS;
    }
}