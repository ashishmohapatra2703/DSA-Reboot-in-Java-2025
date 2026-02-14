/* https://www.geeksforgeeks.org/problems/print-k-smallest-elements-in-their-original-order5407/1
Given an array, the task is to print K smallest elements from the array 
0but they must be in the same order as they are in a given array.

Input: arr[] = [1, 2, 2, 3, 1], k = 3
Output: [1 2 1]
Explanation: In an array arr[] = [1, 2, 2, 3, 1] 
the smallest element is 1, the second smallest element is also 1 and the third smallest element is 2. 
So, we will return [1, 2, 1] as an answer.

Expected Time Complexity: O(n*log(n)).
Expected Auxiliary Space: O(n).
*/

// M-1
class Solution
{
    private class ValIdxPair // custom Object Pair<value, idx>
    {
        int value;
        int index;
    
        public ValIdxPair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    
    private class MaxComparator implements Comparator<ValIdxPair> 
    {
        @Override
        public int compare(ValIdxPair ele1, ValIdxPair ele2) 
        {
            if(ele1.value != ele2.value)
                return ele2.value - ele1.value; // max heap by value
            return ele2.index - ele1.index; // tie-breaker duplicate: larger index out first
        }
    }
    private class OrderComparator implements Comparator<ValIdxPair> 
    {
        @Override
        public int compare(ValIdxPair ele1, ValIdxPair ele2) 
        {
            return ele1.index - ele2.index;
        }
    }
    
    public List<Integer> kSmallestElements(int[] arr, int k) 
    {
        int n = arr.length;
        List<ValIdxPair> bottomKPair = new ArrayList<>();
        List<Integer> bottomKValue = new ArrayList<>();
        PriorityQueue<ValIdxPair> maxH = new PriorityQueue<>(new MaxComparator()); 
        
        for(int i=0; i<n; i++) {
            maxH.add(new ValIdxPair(arr[i], i));
            
            if(maxH.size() > k)
                maxH.poll();
        }
        
        while(! maxH.isEmpty()) { // dump whatever from max heap (in reverse order)
            bottomKPair.add(maxH.poll()); // poll =  peek + remove
        }
        Collections.sort(bottomKPair, new OrderComparator());
        
        
        for(ValIdxPair valIdx: bottomKPair)
            bottomKValue.add(valIdx.value);

        return bottomKValue;
    }
}


//M-2
class Solution
{
	public List<Integer> kSmallestElements(int[] arr, int k) 
    {
        List<Integer> smallestK = new ArrayList<>();
        PriorityQueue<Integer> maxH = new PriorityQueue<>(Collections.reverseOrder()); 
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for(int num: arr) {
            maxH.add(num);
            
            if(maxH.size() > k)
                maxH.poll();
        }
        
        while(! maxH.isEmpty()) { // dump the k smallest from max heap into frequency hashmap
        	int val = maxH.poll();
            freqMap.put(val, freqMap.getOrDefault(val, 0)+1);
        }
         
        for (int num : arr) {  // Traverse original array and collect result from freqMap's key in input order
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                smallestK.add(num);
                freqMap.put(num, freqMap.get(num)-1); // decrease frequency
            }
        }

        return smallestK;
    }
}