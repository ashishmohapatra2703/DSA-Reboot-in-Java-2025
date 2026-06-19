/* https://www.geeksforgeeks.org/problems/previous-greater-element/1 
You are given an integer array arr[]. 
For every element in the array, determine its Previous Greater Element (PGE).
The Previous Greater Element (PGE) of an element x 
is the first element that appears to the left of x in the array and is strictly greater than x.
Note: If no such element exists, assign -1 as the PGE for that position.

Input: arr[] = [10, 4, 2, 20, 40, 12, 30]
Output: [-1, 10, 4, -1, -1, 40, 40]
Explanation:
For 10, no elements on the left, so answer is -1.
For 4, previous greater element is 10.
For 2, previous greater element is 4.
For 20, no element on the left greater than 20, so answer is -1.
For 40, no element on the left greater than 40, so answer is -1.
For 12, previous greater element is 40.
For 30, previous greater element is 40.   */

class Solution {
    public ArrayList<Integer> preGreaterEle(int[] arr) {
        int n = arr.length;
        Stack<Integer> recentPrevGreaterEles = new Stack<>();
        ArrayList<Integer> pge = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            while(!recentPrevGreaterEles.empty() && recentPrevGreaterEles.peek() <= arr[i]) {
                recentPrevGreaterEles.pop();
            }
            
            int recentPrevGreaterEleAtI = (recentPrevGreaterEles.empty()) ? -1 : recentPrevGreaterEles.peek();
            pge.add(recentPrevGreaterEleAtI);

            recentPrevGreaterEles.push(arr[i]);
        }
        
        return pge;
    }
}