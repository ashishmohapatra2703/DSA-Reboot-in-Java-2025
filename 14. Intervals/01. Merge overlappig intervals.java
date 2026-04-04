/* https://leetcode.com/problems/merge-intervals/
https://www.geeksforgeeks.org/problems/overlapping-intervals--170633/1
Given an array of Intervals arr[][], where arr[i] = [starti, endi]. 
The task is to merge all of the overlapping Intervals.

Input: arr[][] = [[1, 3], [2, 4], [6, 8], [9, 10]]
Output: [[1, 4], [6, 8], [9, 10]]
Explanation: In the given intervals we have only two overlapping intervals here, 
[1, 3] and [2, 4] which on merging will become [1, 4]. Therefore we will return [[1, 4], [6, 8], [9, 10]].
Input: arr[][] = [[6, 8], [1, 9], [2, 4], [4, 7]]
Output: [[1, 9]]
Explanation: In the given intervals all the intervals overlap with the interval [1, 9]. 
Therefore we will return [1, 9]. */

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n == 1)
            return intervals;

        Arrays.sort(intervals, (a,b) -> (a[0]-b[0])); //Sorting based on intvStart element
        int firstIntvStart = intervals[0][0];
        int firstIntvEnd = intervals[0][1];
        List<int[]> mergedIntv = new ArrayList<>();

        for(int i=1; i<n; i++) {
            int secondIntvStart = intervals[i][0];
            int secondIntvEnd = intervals[i][1];

            if(secondIntvStart <= firstIntvEnd) { // overlapping => merge
                int newOverlappedRangeStart = firstIntvStart; //always firstIntvStart < secondIntvStart since sorted
                int newOverlappedRangeEnd = Math.max(firstIntvEnd, secondIntvEnd);
                if(mergedIntv.isEmpty()) {
                    mergedIntv.add(new int[]{newOverlappedRangeStart, newOverlappedRangeEnd});
                }
                else {
                    int lastIdx = mergedIntv.size()-1; //replace the last index with the new bigger overlap newOverlappedRange
                    mergedIntv.set(lastIdx, new int[]{newOverlappedRangeStart, newOverlappedRangeEnd});
                }
                firstIntvStart = newOverlappedRangeStart;
                firstIntvEnd = newOverlappedRangeEnd;
            } else if (firstIntvEnd < secondIntvStart) {  // non-overlapping => keep it as it is
                if(mergedIntv.isEmpty())
                    mergedIntv.add(new int[]{firstIntvStart, firstIntvEnd});
                mergedIntv.add(new int[]{secondIntvStart, secondIntvEnd});
                firstIntvStart = secondIntvStart;
                firstIntvEnd = secondIntvEnd;
            }
        }

        int resultArraySize = mergedIntv.size();
        return mergedIntv.toArray(new int[resultArraySize][2]);
    }
}