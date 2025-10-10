/* https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

Given two integer arrays nums1 and nums2, return an array of their intersection. 
Each element in the result must appear as many times as it shows in both arrays and 
you may return the result in any order.

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) 
    {
        Map<Integer, Integer> nums1FreqHash = new HashMap<>();
        List<Integer> intersection = new ArrayList<Integer>();

        for (int n1 : nums1) {
            nums1FreqHash.put(n1, nums1FreqHash.getOrDefault(n1, 0)+1);
        }

        for (int n2 : nums2) 
        {
            if (nums1FreqHash.containsKey(n2) && nums1FreqHash.get(n2) > 0) // n2 == any n1
            {
                intersection.add(n2);
                nums1FreqHash.put(n2, nums1FreqHash.get(n2)-1); // handles the case if nums2 has more duplicates than nums1
            }
        }

        int n3 = intersection.size();
        int[] intersectionInts = new int[n3];
        for (int i=0; i<n3; i++) {
            intersectionInts[i] = intersection.get(i);
        }
        
        return intersectionInts;
    }
}