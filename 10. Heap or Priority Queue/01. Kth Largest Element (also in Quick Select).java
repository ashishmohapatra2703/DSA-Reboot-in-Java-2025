class Solution {
    public int findKthLargest(int[] nums, int k) 
    {
        PriorityQueue<Integer> minH = new PriorityQueue<>(); //min heap

        for (int n : nums) 
        {
            minH.add(n);

            if (minH.size() > k)
                minH.remove();
        }
        
        return minH.peek();
    }
}