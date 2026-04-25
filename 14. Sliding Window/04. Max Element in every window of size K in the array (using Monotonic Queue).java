/* https://leetcode.com/problems/sliding-window-maximum/
https://www.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1

You are given an array of integers nums, there is a sliding window of size k 
which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7     */

//M-1 using sliding window + max heap -> T.C = O(n.k) => TLE in both gfg & Leetcode
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxH = new PriorityQueue<>(Collections.reverseOrder()); //max heap to store k elements
        int n = nums.length;
        int[] maxInEachKSizeSubArray = new int[n-k+1]; // [0 ...to ....(n-k)] index

        for(int i=0; i<k; i++)
        { 
            maxH.add(nums[i]);  // create window of size K
        }
        maxInEachKSizeSubArray[0] = maxH.peek();

        for(int i=k; i<n; i++)
        {
            //slide the window => front add + back subtract
            maxH.add(nums[i]);  // O(logk)
            maxH.remove(nums[i-k]); // O(k) -> linear scan to find the element & remove -> TLE
            
            maxInEachKSizeSubArray[i-k+1] = maxH.peek();
        }
    
        return maxInEachKSizeSubArray;
    }
}

//M-2 using sliding window + Deque -> T.C = O(n) 
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<Integer>(); // Decreasing Order Monotonic Queue => MAX AT FRONT, MIN AT REAR
        int n = nums.length;
        int[] maxInEachKSizeSubArray = new int[n-k+1]; // [0 ...to ....(n-k)] index

        for(int i=0; i<k; i++)
        { 
            while(!dq.isEmpty() && nums[i] > dq.peekLast()) {
                dq.pollLast();   // remove not useful elements
            }
            dq.addLast(nums[i]); // create window of size K & only add potential max elements
        }
        maxInEachKSizeSubArray[0] = dq.peekFirst();

        for(int i=k; i<n; i++)
        {
            if(nums[i-k] == dq.peekFirst()) {
                dq.pollFirst();  // back subtract going out of the window
            }
            while (!dq.isEmpty() && nums[i] > dq.peekLast()) { // front add
                dq.pollLast(); // Remove all elements (from the back) smaller than the current number == only add potential max elements
            }
            dq.addLast(nums[i]);
            
            maxInEachKSizeSubArray[i-k+1] = dq.peekFirst(); // max of the window is @ dq front
        }
    
        return maxInEachKSizeSubArray;
    }
}
