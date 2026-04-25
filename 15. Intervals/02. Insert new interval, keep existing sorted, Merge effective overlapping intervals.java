/* https://leetcode.com/problems/insert-interval/description/
https://www.geeksforgeeks.org/problems/insert-interval-1666733333/1

Geek has an array of non-overlapping intervals intervals[][] where intervals[i] = [starti , endi] represent 
the start and the end of the ith event and intervals is sorted in ascending order by starti . He wants to add a 
new interval newInterval[] = [newStart, newEnd] where newStart and newEnd represent the start and end of this interval.
Help Geek to insert newInterval into intervals such that intervals is still sorted in ascending order by starti and 
intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Input: intervals[][] = [[1, 3], [4, 5], [6, 7], [8, 10]], newInterval[] = [5, 6]
Output: [[1, 3], [4, 7], [8, 10]]
Explanation: The newInterval [5, 6] overlaps with [4, 5] and [6, 7]. So, they are merged into one interval [4, 7].*/

class Solution {
    public ArrayList<int[]> insertInterval(int[][] intervals, int[] newInterval) {
        int[][] newSortedIntervals = buildNewSortedArrayAfterInsertion(intervals, newInterval);
        return mergeOverlappingIntervals(newSortedIntervals);
    }
    
    private int[][] buildNewSortedArrayAfterInsertion(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if(n==0)
            return new int[][]{newInterval};

        int newIntervalStart = newInterval[0];
        List<int[]> newSortedIntervals = new ArrayList<>();

        int i=0;
        while(i<n && intervals[i][0] < newIntervalStart) {
            newSortedIntervals.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }
        // insert new interval exactly once
        newSortedIntervals.add(new int[]{newIntervalStart, newInterval[1]});
        // add remaining
        while(i<n) {
            newSortedIntervals.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        return newSortedIntervals.toArray(new int[n+1][2]);
    }
    
    private ArrayList<int[]> mergeOverlappingIntervals(int[][] arr) {
        int n = arr.length;
        if(n == 1)
            return new ArrayList<>(Arrays.asList(arr));;
            
        Arrays.sort(arr, (a,b) -> (a[0]-b[0])); //Sorting based on intvStart element
        int firstIntvStart = arr[0][0];
        int firstIntvEnd = arr[0][1];
        ArrayList<int[]> mergedIntv = new ArrayList<>();

        for(int i=1; i<n; i++) {
            int secondIntvStart = arr[i][0];
            int secondIntvEnd = arr[i][1];

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

        return mergedIntv;
    }
}
