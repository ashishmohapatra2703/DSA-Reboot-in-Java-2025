/*You are given an integer array nums and an integer k.
In one operation, you can pick two numbers from the array whose sum equals k 
and ** REMOVE them from the array **.
Return the maximum number of operations you can perform on the array.

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.   */

//M-1 T.C = O(n.logn) , S.C = O(1)  //use this when already given sorted array
class Solution {
    public int maxOperations(int[] nums, int k) 
    {
        int countPairsSumk = 0;
        Arrays.sort(nums);

        int i = 0;
        int j = nums.length-1;

        while(i < j)
        {
            int pairSum = nums[i] + nums[j];
            if(pairSum == k) {
                countPairsSumk++;
                i++;
                j--;
            } 
            else if(pairSum < k) {
                i++;
            } 
            else if(pairSum > k) {
                j--;
            }
        }

        return countPairsSumk;
    }
}

//M-2 Optimised , T.C = O(n), S.C = O(n)
class Solution {
    public int maxOperations(int[] nums, int k) 
    {   
        int countPairsSumk = 0;
        HashMap<Integer, Integer> hash = new HashMap();

        for(int num: nums)
        {
            int complement = k - num;

            if(hash.containsKey(complement))
            {
                countPairsSumk++;
                
                // find a complement => immediately consume both numbers: num and complement
                if(hash.get(complement) == 1) 
                    hash.remove(complement);
                else if(hash.get(complement) > 1)
                    hash.put(complement, hash.get(complement)-1);
            }
            else { 
                // store num for future pairing => only if no valid complement found
                hash.put(num , hash.getOrDefault(num , 0)+1);
            }
        }

        return countPairsSumk;
    }
}