/*Given an array arr[] and an integer K where K is smaller than size of array, 
the task is to find the Kth smallest element in the given array. 

It is given that all array elements are distinct.
Note :-  l and r denotes the starting and ending index of the array.

Input:
N = 6,  arr[] = 7 10 4 3 20 15
K = 3
L=0 R=5

Output : 7
Explanation :
3rd smallest element in the given array is 7.    */

class Solution{
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        PriorityQueue<Integer> maxH = new PriorityQueue<>((a,b) -> b - a); // or use (Collections.reverseOrder())

        for (int i=l; i<=r; i++) 
        {
            maxH.add(arr[i]);

            if (maxH.size() > k)
                maxH.poll();
        }
        
        return maxH.peek();
    } 
}
