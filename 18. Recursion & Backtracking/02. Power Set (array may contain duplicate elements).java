/* https://leetcode.com/problems/subsets-ii/description/

Given an integer array nums that may contain duplicates, 
return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]  */

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> powerSetEachEle = new ArrayList<>(); // empty subset

        List<List<Integer>> powerSetResult = new ArrayList<>();
        findPowerSet(nums, 0, n, powerSetEachEle, powerSetResult);
        return powerSetResult;
    }
    private void findPowerSet(int[] nums, int i, int n, List<Integer> powerSetEachEle, List<List<Integer>> powerSetResult) {
        if(i == n) {
            if(powerSetResult.contains(powerSetEachEle))
                return;
            else
                powerSetResult.add(new ArrayList<>(powerSetEachEle));
            return;
        }

        powerSetEachEle.add(nums[i]); //take Ith
        findPowerSet(nums, i+1, n, powerSetEachEle, powerSetResult);
        powerSetEachEle.removeLast(); //skip Ith
        findPowerSet(nums, i+1, n, powerSetEachEle, powerSetResult);
    }
}