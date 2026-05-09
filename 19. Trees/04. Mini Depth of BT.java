/* https://leetcode.com/problems/minimum-depth-of-binary-tree/ */

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        int leftChildTreeMinDepth = minDepth(root.left);
        int rightChildTreeMinDepth = minDepth(root.right);

        // if right skewed
        if(root.left == null) 
            return 1 + rightChildTreeMinDepth;
        // if left skewed
        if(root.right == null) 
            return 1 + leftChildTreeMinDepth;

        return 1 + Math.min(leftChildTreeMinDepth, rightChildTreeMinDepth);
    }
}