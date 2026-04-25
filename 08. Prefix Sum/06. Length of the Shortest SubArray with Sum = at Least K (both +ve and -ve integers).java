/*(LC Hard) https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/ 

Given an integer array nums and an integer k, return the 
length of the shortest non-empty subarray of nums with a sum of at least k. 
If there is no such subarray, return -1.
A subarray is a contiguous part of an array.

Input: nums = [1,2], k = 4
Output: -1
Input: nums = [2,-1,2], k = 3
Output: 3
Input: arr[] = {2, 4, 6, 10, 2, 1}, K = 12 
Output: 2 
Explanation: All possible subarrays with sum 12 are {2, 4, 6} and {10, 2}.
Input: arr[] = {-8, -8, -3, 8}, K = 5 
Output: 2   */

// M-1
class Solution {
    class Pair {
        long value;
        int index;

        Pair(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int shortestSubarray(int[] nums, int k) {
        PriorityQueue<Pair> minPrefSumHeap = new PriorityQueue<>((a, b) -> Long.compare(a.value, b.value));
        int n = nums.length;
        long prefSumI = 0;
        minPrefSumHeap.offer(new Pair(prefSumI, -1)); //prefSum = 0 at index = -1
        int minLenWindowSumAtLeastK = Integer.MAX_VALUE;
        
        // SubArrSum of window [(j+1) ... i]  (i>j) 
        // prefSum[i] - prefSum[j] >= K
        // For current index i 
        // => Find out any index (before i) j, such that it is so LESS to get ">= K result"
        // LESS because it is substracted 
        // Out of all the options, keep the global minLenWindowSumAtLeastK
        for(int i=0; i<n; i++)
        {
            prefSumI += nums[i];
            minPrefSumHeap.offer(new Pair(prefSumI, i));
            
            if(prefSumI >= k) {
                minLenWindowSumAtLeastK = Math.min(minLenWindowSumAtLeastK , i-0+1); //subarray [0....to...i]  sum >= K
            }
            long prefSumJ = minPrefSumHeap.peek().value;
            if(prefSumI - prefSumJ >= k) { 
                while(!minPrefSumHeap.isEmpty() && prefSumI - minPrefSumHeap.peek().value >= k) {
                    int j = minPrefSumHeap.peek().index; //subarray [(j+1)...to... i]  sum >= K
                    minLenWindowSumAtLeastK = Math.min(minLenWindowSumAtLeastK , i-(j+1)+1);
                    minPrefSumHeap.poll(); 
                    //earliest/higher j index preferred for shorter window length answer, 
                    // so try out ALL prefSumJ which satisfy (prefSumJ <= prefSumI - k) and have highest index
                }
            }
        }
        
        return (minLenWindowSumAtLeastK==Integer.MAX_VALUE) ? -1 : minLenWindowSumAtLeastK;
    }
}
//Min-Heap prioritizes: smallest prefSumJ
//But shortest minLenWindowSumAtLeastK depends on: largest j
//These are conflicting goals, so use monotonic increasing Deque



//M-2
//Find best/highest j such that (pref[i] - pref[j]) >= k
// => pref[j] small ENOUGH AND j as LARGE AS POSSIBLE
//Monotonic increasing Deque = Maintains ONLY useful candidates + keeps them ordered for optimal j selection
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<Integer>();
        // Stores INDEX; 
        // Increasing Order (pref[dq[0]] <= pref[dq[1]] <= pref[dq[2]]) Monotonic Queue 
        // Front → index of smallest prefix sum (best candidate)
        // Back  → index of larger prefix sums (worse candidates)
        int n = nums.length;
        long[] prefSum = new long[n];
        int minLenWindowSumAtLeastK = Integer.MAX_VALUE;

        for(int i=0; i<n; i++)
        {
            if(i==0)
                prefSum[0] = nums[0];
            else 
                prefSum[i] = prefSum[i-1] + nums[i];
            
            
            if(prefSum[i] >= k) {
                minLenWindowSumAtLeastK = Math.min(minLenWindowSumAtLeastK , i-0+1); //subarray [0....to...i]  sum >= K
            }
            // j = dq front (smallest prefix)
            while(!dq.isEmpty() && prefSum[i] - prefSum[dq.peekFirst()] >= k) { //subarray [(j+1)...to... i]  sum >= K
                int j = dq.peekFirst();
                minLenWindowSumAtLeastK = Math.min(minLenWindowSumAtLeastK , i-(j+1)+1);
                dq.pollFirst();   
            }

            // Maintain monotonic increasing prefix
            while(!dq.isEmpty() && prefSum[i] <= prefSum[dq.peekLast()]) {
                dq.pollLast();   // remove the index (from the dq back) which shows dip in the order
            }
            dq.addLast(i);
        }
        
        return (minLenWindowSumAtLeastK==Integer.MAX_VALUE) ? -1 : minLenWindowSumAtLeastK;
    }
}