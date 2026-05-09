/*https://leetcode.com/problems/subsets/ */ 

//T.C = O(2^n)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<Integer> powerSetEachEle = new ArrayList<>(); // empty subset
        List<List<Integer>> powerSetResult = new ArrayList<>();
        findPowerSet(nums, 0, n, powerSetEachEle, powerSetResult);
        return powerSetResult;
    }
    private void findPowerSet(int[] nums, int i, int n, List<Integer> powerSetEachEle, List<List<Integer>> powerSetResult) {
        if(i == n) {
            powerSetResult.add(new ArrayList<>(powerSetEachEle));
            return;
        }

        powerSetEachEle.add(nums[i]); //take Ith
        findPowerSet(nums, i+1, n, powerSetEachEle, powerSetResult);
        powerSetEachEle.removeLast(); //skip Ith
        findPowerSet(nums, i+1, n, powerSetEachEle, powerSetResult);
    }
}