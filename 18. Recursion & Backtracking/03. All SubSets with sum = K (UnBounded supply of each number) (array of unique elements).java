/* https://leetcode.com/problems/combination-sum/ 
Given an array of distinct integers candidates and a target integer target, 
return a list of all unique combinations of candidates where the chosen numbers sum to target. 
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. 
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations 
that sum up to target is less than 150 combinations for the given input.

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.  */

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        List<List<Integer>> allSubSetsHavingSumK = new ArrayList<>();
        List<Integer> subSetSoFar = new ArrayList<>();
        getAllSubSetsHavingSumK(candidates, 0, n, target, subSetSoFar, allSubSetsHavingSumK);
        return allSubSetsHavingSumK;
    }
    private void getAllSubSetsHavingSumK(int[] candidates, int i, int n, int targetSumK,
                                List<Integer> subSetSoFar, List<List<Integer>> allSubSetsHavingSumK) {
        if(targetSumK < 0)
            return;
        if(i == n) {
            if(targetSumK == 0)
                allSubSetsHavingSumK.add(new ArrayList<>(subSetSoFar));
            return;
        }

        //option take i_thEle (unbounded)
        int ithEle = candidates[i];
        subSetSoFar.add(ithEle);
        getAllSubSetsHavingSumK(candidates, i, n, targetSumK-ithEle, subSetSoFar, allSubSetsHavingSumK);
        subSetSoFar.removeLast();
        //option skip i_thEle
        getAllSubSetsHavingSumK(candidates, i+1, n, targetSumK, subSetSoFar, allSubSetsHavingSumK);
    }
}