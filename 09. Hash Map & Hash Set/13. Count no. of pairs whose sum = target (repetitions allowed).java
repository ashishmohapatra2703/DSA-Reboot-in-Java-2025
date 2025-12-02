/*Given an array of N integers, and an integer K, 
find the number of pairs of elements in the array whose sum is equal to K.

Input: N = 4, K = 6 , arr[] = {1, 5, 7, 1}
Output: 2
Explanation:  arr[0] + arr[1] = 1 + 5 = 6  and arr[1] + arr[3] = 5 + 1 = 6.

Input: N = 4, X = 2 , arr[] = {1, 1, 1, 1}
Output: 6
Explanation: Each 1 will produce sum 2 with any 1.

Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)*/

class Solution {
    int getPairsCount(int[] arr, int n, int k) 
    {
        int countPairsSumk = 0;
        HashMap<Integer, Integer> hash = new HashMap();

        for(int num: arr)
        {
            int complement = k - num;
            if(hash.containsKey(complement)) 
            {
                countPairsSumk += hash.get(complement); // how many previous numbers have value (k - num)
            }
            
            hash.put(num , hash.getOrDefault(num , 0)+1);
        }

        return countPairsSumk;
    }
}
