/* https://leetcode.com/problems/intersection-of-two-arrays/description/
Given two integer arrays nums1 and nums2, return an array of their intersection. 
Each element in the result must be unique and you may return the result in any order.

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.   */

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        List<Integer> intersectionUniqueNums = new ArrayList<Integer>();

        for (int n1 : nums1) {
            nums1Set.add(n1);
        }

        for (int n2 : nums2) 
        {
            if (nums1Set.contains(n2)) 
            {
                intersectionUniqueNums.add(n2);
                nums1Set.remove(n2);
            }
        }

        int n3 = intersectionUniqueNums.size();
        int[] intersectionUniqueInts = new int[n3];
        for (int i=0; i<n3; i++) {
            intersectionUniqueInts[i] = intersectionUniqueNums.get(i);
        }
        
        return intersectionUniqueInts;
    }
}


// Similar Ques.  //Sum the common elements
/* You are given two arrays of size n1 and n2. Your task is to find 
all the elements that are common to both the arrays and sum them. 
If there are no common elements the output would be 0.

Note: The arrays may contain duplicate elements. 
However, you need to sum only unique elements that are common to both arrays and 
answer may be too large so return it with modulo (109+7) .

Input:
n1 = 5 , n2 = 6
arr1 = [1 2 2 3 5]
arr2 = [3 3 2 2 6 5]
Output: 10
Explanation: Common unique elements in both arrays are 2, 3 and 5 so answer will be 2+3+5 = 10  */
class Geeks {
public
    static int mod = 1000000007;
    static int commonSum(int n1, int n2, int arr1[], int arr2[]) 
    {
        Set<Integer> nums1Set = new HashSet<>();
        int intersectionSumUniqueNums = 0;

        for (int n : arr1) {
            nums1Set.add(n);
        }

        for (int m : arr2) 
        {
            if (nums1Set.contains(m)) 
            {
                intersectionSumUniqueNums = (intersectionSumUniqueNums + m) % mod;
                nums1Set.remove(m);
            }
        }

        return intersectionSumUniqueNums;
    }
}