/* https://www.geeksforgeeks.org/problems/k-largest-elements4206/1
Given an array of N positive integers, 
print k largest elements from the array in descending order.

Input:
N = 7, k = 3
arr[] = {1,23,12,9,30,2,50}
Output: 50 30 23
Explanation: Three Largest element in the array are 50, 30 and 23.  */

class Solution
{
    //Function to return k largest elements from an array.
    public static ArrayList<Integer> kLargest(int arr[], int n, int k)
    {
        ArrayList<Integer> topK = new ArrayList<>();
        PriorityQueue<Integer> minH = new PriorityQueue<>(); //min heap
        
        for(int num : arr)
        {
            minH.add(num);
            
            if(minH.size() > k)
                minH.remove(); // remove smallest, keep only k largest
        }
        
        while(! minH.isEmpty()) // dump whatever from min heap (in reverse order)
        {
            int top = minH.poll(); // poll =  peek + remove
            topK.add(top);
        }
        Collections.reverse(topK);
        
        return topK;
    }
}