/* https://leetcode.com/problems/word-ladder/description/

A transformation sequence from word beginWord to word endWord using a dictionary 
wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, 
return the number of words in the shortest transformation sequence from beginWord to endWord, 
or 0 if no such sequence exists.

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long. */


//Core Idea = 
// BFS itself guarantees the first time you reach endWord, 
// you reached it with the MINIMUM number of transformations.
class Solution {
    public record WordDistCombo(String currWordInSeq, int distance) { }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> allWordsSet = new HashSet<>();
        allWordsSet.addAll(wordList);

        if(! allWordsSet.contains(endWord)) { //no way to reach to endWord, if endWord is itself not there in wordList
            return 0;
        }

        Queue<WordDistCombo> sequenceQueue = new ArrayDeque<>();
        sequenceQueue.offer(new WordDistCombo(beginWord, 1));  //pushing first beginWord in queue.
        allWordsSet.remove(beginWord); //either T/F -> if beginWordpresent => removed, if not => nothing // marks visited

        int wordLen = beginWord.length(); //every word have same length

        while(!sequenceQueue.isEmpty())
        {
            WordDistCombo currentLevel = sequenceQueue.poll();
            //find all its possible neighbours and add in queue
            String currentWord = currentLevel.currWordInSeq();
            int currentDistance = currentLevel.distance(); //from start

            //Generate every word that differs from currentWord by exactly one character.
            for(int i=0; i<wordLen; i++) {
                for(char replace='a'; replace<='z'; replace++) {
                    String adjacentString = currentWord.substring(0, i) + replace + currentWord.substring(i+1);

                    if(adjacentString.equals(currentWord))
                        continue; //skip if same as parent word
                    else if(allWordsSet.contains(adjacentString)) {
                        if(adjacentString.equals(endWord)) {
                            return currentDistance+1; // final answer
                        } 
                        else {
                            //found to be a actual neighbour/ present in allWordsSet
                            sequenceQueue.offer(new WordDistCombo(adjacentString, currentDistance+1));
                            allWordsSet.remove(adjacentString); //marks visited (to not come at it again)
                        }
                    }
                }
            }
        }
        return 0;
    }
}