/* https://leetcode.com/problems/task-scheduler/description/
https://www.geeksforgeeks.org/problems/task-scheduler/1

You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. 
Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, 
but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
Return the minimum number of CPU intervals required to complete all tasks.

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
After completing task A, you must wait two intervals before doing A again. 
The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. 
By the 4th interval, you can do A again as 2 intervals have passed.

Input: tasks = ["A","A","A", "B","B","B"], n = 3
Output: 10
Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
There are only two types of tasks, A and B, which need to be separated by 3 intervals. 
This leads to idling twice between repetitions of these tasks.  */

class Solution {
    record FreqTimePair(int freq, int time) {} 
    // (remaining executions of the task, earliest time it can be scheduled again) in chronological order of FIFO

    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> charFreqMap = new HashMap<>();
        for (int i=0; i<tasks.length; i++) {
            charFreqMap.put(tasks[i], charFreqMap.getOrDefault(tasks[i],0) + 1);
        }
        
        PriorityQueue<Integer> maxFreqHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int freqOfChar: charFreqMap.values()) {
            maxFreqHeap.add(freqOfChar);
        } //greedily select the most highest (remaining) frequency task
        // to "start first" to minimize idle time overall

        int currTime = 0;
        Queue<FreqTimePair> cooldownQueue_freqToAddAttTime = new ArrayDeque<>();

        // Continue until:
        // 1. No executable task left in heap
        // 2. No task waiting in cooldown
        while (!maxFreqHeap.isEmpty() || !cooldownQueue_freqToAddAttTime.isEmpty()) {
            currTime++;

            if(!maxFreqHeap.isEmpty()) {
                int remainingFreq  = maxFreqHeap.poll();
                remainingFreq --;
                if(remainingFreq > 0) { //if needs more execution -> then only send to cooldown for next pull
                    cooldownQueue_freqToAddAttTime.offer(new FreqTimePair(remainingFreq , currTime+n));
                }
            }

            //check if cooldown has passed / next pull time arrived
            if(!cooldownQueue_freqToAddAttTime.isEmpty() && cooldownQueue_freqToAddAttTime.peek().time() == currTime) {
                maxFreqHeap.add(cooldownQueue_freqToAddAttTime.peek().freq());
                cooldownQueue_freqToAddAttTime.poll();
            }
        }

        return currTime;
    }
}