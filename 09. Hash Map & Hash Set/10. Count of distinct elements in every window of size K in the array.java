/* https://www.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1
Given an integer array arr[] and a number k. 
Find the count of distinct elements in every window of size k in the array.

Examples:
Input: arr[] = [1, 2, 1, 3, 4, 2, 3], k = 4
Output: [3, 4, 4, 3]
Explanation:
First window is [1, 2, 1, 3], count of distinct numbers is 3.
Second window is [2, 1, 3, 4] count of distinct numbers is 4.
Third window is [1, 3, 4, 2] count of distinct numbers is 4.
Fourth window is [3, 4, 2, 3] count of distinct numbers is 3.

Input: arr[] = [4, 1, 1], k = 2
Output: [2, 1]
Explanation:
First window is [4, 1], count of distinct numbers is 2.
Second window is [1, 1], count of distinct numbers is 1.

Input: arr[] = [1, 1, 1, 1, 1], k = 3
Output: [1, 1, 1]
Explanation: Every window of size 3 in the array [1, 1, 1, 1, 1], 
contains only the element 1, so the number of distinct elements in each window is 1.
*/ 

class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        ArrayList<Integer> dintinctCountInEachKSizeSubArray = new ArrayList<>();
        HashMap<Integer, Integer> kSizeFreqMap = new HashMap<>();

        for(int i=0; i<k; i++)
        { 
            kSizeFreqMap.put(arr[i], kSizeFreqMap.getOrDefault(arr[i],0)+1);  // create window of size K
        }
        dintinctCountInEachKSizeSubArray.add(kSizeFreqMap.size());

        for(int i=k; i<arr.length; i++)
        {
            kSizeFreqMap.put(arr[i], kSizeFreqMap.getOrDefault(arr[i],0)+1); //slide the window => front add + back subtract
            kSizeFreqMap.put(arr[i-k], kSizeFreqMap.getOrDefault(arr[i-k],0)-1);
            if (kSizeFreqMap.get(arr[i-k]) == 0) {
                kSizeFreqMap.remove(arr[i-k]);
            }

            dintinctCountInEachKSizeSubArray.add(kSizeFreqMap.size());
        }
    
        return dintinctCountInEachKSizeSubArray;
    }
}