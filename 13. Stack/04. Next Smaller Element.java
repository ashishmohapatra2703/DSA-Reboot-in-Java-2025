/* https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1 
You are given an integer array arr[ ]. For every element in the array, 
your task is to determine its Next Smaller Element (NSE).
The Next Smaller Element (NSE) of an element x is the 
first element that appears to the right of x in the array and is strictly smaller than x.
If no such element exists, assign -1 as the NSE for that position.

Input: arr[] = [4, 8, 5, 2, 25]
Output: [2, 5, 2, -1, -1]
Explanation: 
The first element smaller than 4 having index > 0 is 2.
The first element smaller than 8 having index > 1 is 5.
The first element smaller than 5 having index > 2 is 2.
There are no elements smaller than 4 having index > 3.
There are no elements smaller than 4 having index > 4.  */

class Solution {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int n = arr.length;
        Stack<Integer> recentNextSmallerEles = new Stack<>();
        ArrayList<Integer> nse = new ArrayList<>();
        
        for(int i=n-1; i>=0; i--) {
            while(!recentNextSmallerEles.empty() && recentNextSmallerEles.peek() >= arr[i]) {
                recentNextSmallerEles.pop();
            }
            
            int recentNextSmallerAtI = (recentNextSmallerEles.empty()) ? -1 : recentNextSmallerEles.peek();
            nse.add(recentNextSmallerAtI);

            recentNextSmallerEles.push(arr[i]);
        }
        
        Collections.reverse(nse);
        return nse;
    }
}