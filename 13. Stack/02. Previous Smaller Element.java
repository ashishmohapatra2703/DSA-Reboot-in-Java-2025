/* https://www.geeksforgeeks.org/problems/previous-smaller-element/1 
You are given an integer array arr[ ]. For every element in the array, 
your task is to determine its Previous Smaller Element (PSE).
The Previous Smaller Element (PSE) of an element x 
is the first element that appears to the left of x in the array and is strictly smaller than x.
Note: If no such element exists, assign -1 as the PSE for that position.

Input: arr[] = [1, 6, 2]
Output: [-1, 1, 1]
Explanation:
For 1, there is no element on the left, so answer is -1.
For 6, previous smaller element is 1.
For 2, previous smaller element is 1. */

class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        Stack<Integer> recentPrevSmallerEles = new Stack<>();
        ArrayList<Integer> pse = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            while(!recentPrevSmallerEles.empty() && recentPrevSmallerEles.peek() >= arr[i]) {
                recentPrevSmallerEles.pop();
            }
            
            int recentPrevSmallerEleAtI = (recentPrevSmallerEles.empty()) ? -1 : recentPrevSmallerEles.peek();
            pse.add(recentPrevSmallerEleAtI);

            recentPrevSmallerEles.push(arr[i]);
        }
        
        return pse;
    }
}