/* https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1
Given an array arr[], where each element is at most k away from its target position, you need to sort the array optimally.
Note: You need to change the given array arr[] in place.

Examples:
Input: arr[] = [6, 5, 3, 2, 8, 10, 9], k = 3
Output: [2, 3, 5, 6, 8, 9, 10]
Explanation: The sorted array will be 2 3 5 6 8 9 10
Input: arr[]= [1, 4, 5, 2, 3, 6, 7, 8, 9, 10], k = 2
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Explanation: The sorted array will be 1 2 3 4 5 6 7 8 9 10
*/

// M-1 T.C = O(n.logk), S.C = O(k) + O(n)
class Solution {
    public void nearlySorted(int[] arr, int k) {
        List<Integer> sorted = new ArrayList<>();
        PriorityQueue<Integer> minH = new PriorityQueue<>(); //min heap
        
    	for(int num : arr) {
    	    minH.add(num);
    	        
            if(minH.size() > k)
            {
                sorted.add(minH.poll());
            }
    	}
    	
    	while(! minH.isEmpty()) { // finally dump the last k size from min heap
    	    sorted.add(minH.poll()); // poll =  peek + remove
    	}
    	
    	// copy back into arr 
        for (int i=0; i<arr.length; i++) {
            arr[i] = sorted.get(i);
        }
    }
}

//M-2 T.C = O(n.logk), S.C = O(k) IN-PLACE
class Solution {
    public void nearlySorted(int[] arr, int k) {
        PriorityQueue<Integer> minH = new PriorityQueue<>(); //min heap
        int i = 0;
        
        for(int num : arr)
        {
            minH.add(num);
                
            if(minH.size() > k) {
                arr[i++] = minH.poll();
            }
        }
        
        while(! minH.isEmpty()) // finally dump the last remaining k size from min heap
        {
            arr[i++] = minH.poll(); 
        }
    }
}
