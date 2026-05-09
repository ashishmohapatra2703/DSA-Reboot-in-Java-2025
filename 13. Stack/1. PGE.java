/* https://www.geeksforgeeks.org/problems/previous-greater-element/1 */

class Solution {
    public ArrayList<Integer> preGreaterEle(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> PGRToLeft = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            while(!stack.empty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            
            if(stack.empty()) {
                PGRToLeft.add(-1);
            } else { 
                PGRToLeft.add(stack.peek());
            }
            stack.push(arr[i]);
        }
        
        return PGRToLeft;
    }
}