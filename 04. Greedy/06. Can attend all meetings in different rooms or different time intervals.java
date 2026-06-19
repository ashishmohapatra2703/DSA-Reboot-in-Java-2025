/* https://www.geeksforgeeks.org/problems/attend-all-meetings/1 
Given a 2D array arr[][], where arr[i][0] is the starting time of ith meeting and 
arr[i][1] is the ending time of ith meeting, the task is to check if it is possible for a person 
to attend all the meetings such that he can attend only one meeting at a particular time.
Note: A person can attend a meeting if its starting time is greater than or 
equal to the previous meeting's ending time.

Input: arr[][] = [[1, 4], [10, 15], [7, 10]]
Output: true
Explanation: Since all the meetings are held at different times, it is possible to attend all the meetings.

Input: arr[][] = [[2, 4], [9, 12], [6, 10]]
Output: false
Explanation: Since the second and third meeting overlap, a person cannot attend all the meetings.*/

class Solution {
    static boolean canAttend(int[][] arr) {
        int n = arr.length;
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        
        int firstMeetStart = arr[0][0];
        int firstMeetEnd = arr[0][1];
            
        for(int i=1; i<n; i++) {
            int secondMeetStart = arr[i][0];
            int secondMeetEnd = arr[i][1];
            
            if(firstMeetEnd > secondMeetStart)
                return false;
            
            // firstMeetStart = secondMeetStart;
            firstMeetEnd = secondMeetEnd;
        }
        return true;
    }
}