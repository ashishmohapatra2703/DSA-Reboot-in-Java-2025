/* https://leetcode.com/problems/combination-sum-ii/description/
Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]  */

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // sort the given input (to keep equal/duplicate numbers adjacent)
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
        getAllSubSetsHavingSumK(candidates, i+1, n, targetSumK-ithEle, subSetSoFar, allSubSetsHavingSumK);
        subSetSoFar.removeLast();

        // Skip duplicate i_thEle
        while(i+1<=n-1 && ithEle == candidates[i+1]) {
            i++;
        }
        //option skip i_thEle
        getAllSubSetsHavingSumK(candidates, i+1, n, targetSumK, subSetSoFar, allSubSetsHavingSumK);
    }
}