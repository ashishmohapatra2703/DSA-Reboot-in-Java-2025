/* https://www.geeksforgeeks.org/problems/pair-in-array-whose-sum-is-closest-to-x1124/1
Given an array arr[] and a number target, find a pair of elements (a, b) in arr[], 
where a ≤ b whose sum is closest to target.
Note: Return the pair in sorted order and if there are multiple such pairs 
return the pair with maximum absolute difference. If no such pair exists return an empty array.

Input: arr[] = [10, 30, 20, 5], target = 25
Output: [5, 20]
Explanation: As 5 + 20 = 25 is closest to 25.

Input: arr[] = [5, 2, 7, 1, 4], target = 10
Output: [2, 7]
Explanation: As (4, 5), (2, 7) and (4, 7) both are closest to 10, but absolute difference of (4, 5) is 1, (2, 7) is 5 and (4, 7) is 3. Hence, [2, 7] has maximum absolute difference and closest to target.  */

class Solution {
    public ArrayList<Integer> sumClosest(int[] arr, int target) {
        int n = arr.length;
        if(n == 1)
            return new ArrayList<>(); 
            
        Arrays.sort(arr);
        int low = 0;
        int high = n-1;
        ArrayList<Integer> pairSumClosestToK = new ArrayList<>(2);
        pairSumClosestToK.add(0);
        pairSumClosestToK.add(0); //pre-fill
        
        int minClosenessSumToK = Integer.MAX_VALUE;
        int maxGapIntraPair = Integer.MIN_VALUE;
        
        while(low < high)
        {
            int pairSum = arr[low]+arr[high];
            int closeness = target - pairSum;
            int absCloseness = Math.abs(closeness);
            int gapIntraPair = arr[high] - arr[low];
            
            if(absCloseness < minClosenessSumToK || 
                	(minClosenessSumToK == absCloseness && gapIntraPair > maxGapIntraPair)) {
                pairSumClosestToK.set(0, arr[low]);
                pairSumClosestToK.set(1, arr[high]);
                maxGapIntraPair = gapIntraPair;
                minClosenessSumToK = absCloseness;
            }
            
            if(closeness == 0) {
                low++; // can do high-- either
            }
            else if(closeness > 0) {
                low++;
            }
            else if(closeness < 0) {
                high--;
            }
        }
        return pairSumClosestToK;
    }
}