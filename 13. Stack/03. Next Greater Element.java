/* https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1 
You are given an array arr[] of integers, the task is to find the next greater element 
for each element of the array in order of their appearance in the array. 
Next greater element of an element in the array is the nearest element on the right 
which is greater than the current element.
If there does not exist next greater of current element, 
then next greater element for current element is -1.

Input: arr[] = [6, 8, 0, 1, 3]
Output: [8, -1, 1, 3, -1]
Explanation: The next larger element to 6 is 8, for 8 there is no larger elements hence it is -1, 
for 0 it is 1, for 1 it is 3 and then for 3 there is no larger element on right and hence -1. */

class Solution {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> recentNextGreaterEles = new Stack<>();
        ArrayList<Integer> nge = new ArrayList<>();
        
        for(int i=n-1; i>=0; i--) {
            while(!recentNextGreaterEles.empty() && recentNextGreaterEles.peek() <= arr[i]) {
                recentNextGreaterEles.pop();
            }
            
            int recentNextGreaterAtI = (recentNextGreaterEles.empty()) ? -1 : recentNextGreaterEles.peek();
            nge.add(recentNextGreaterAtI);

            recentNextGreaterEles.push(arr[i]);
        }
        
        Collections.reverse(nge);
        return nge;
    }
}