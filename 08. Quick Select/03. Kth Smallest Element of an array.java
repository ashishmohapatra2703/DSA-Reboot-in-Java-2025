/*Given an array arr[] and an integer K where K is smaller than size of array, 
the task is to find the Kth smallest element in the given array. 
It is given that all array elements are distinct.

Note :-  l and r denotes the starting and ending index of the array.

Input: N = 6 ,  arr[] = 7 10 4 3 20 15
K = 3
L=0 R=5

Output : 7
Explanation : 3rd smallest element in the given array is 7.  */

class Solution{
    private static final Random random = new Random();
    
    public static int kthSmallest(int[] arr, int l, int r, int k)  //to find element at (k-1)th sorted index
    { 
        if(l == r)
            return arr[l];

        int sIdx = k-1;
        int pi = partition(arr, l, r); // partitioning index / return the sorted index of the pivot

        if (sIdx == pi)
            return arr[pi];

        else if (sIdx < pi)
            return kthSmallest(arr, l, pi-1, k);
        else // if (sIdx > pi)
            return kthSmallest(arr, pi+1, r, k);
    } 
    
    private static int partition(int[] arr, int low, int high) 
    {
        int randomIdx = low + random.nextInt(high - low + 1); //random pivot partitioning optimization
        swap(arr, randomIdx, high);
        int pivot = arr[high]; 

        int j = low;
        for (int i = low; i <= high; i++) 
        {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                j++;
            }
        }
        
        return j-1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

