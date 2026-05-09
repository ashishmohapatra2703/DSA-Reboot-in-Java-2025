/* https://leetcode.com/problems/maximum-depth-of-binary-tree/ */


class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        int leftChildTreeMaxDepth = maxDepth(root.left);
        int rightChildTreeMaxDepth = maxDepth(root.right);
        return 1 + Math.max(leftChildTreeMaxDepth, rightChildTreeMaxDepth);
    }
}