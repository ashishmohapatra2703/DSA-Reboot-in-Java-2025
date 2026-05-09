/* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ */ 

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findIfPOrQInChildSubTree(root, p, q);
    }
    private TreeNode findIfPOrQInChildSubTree(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
        if(root == p || root == q) //any one first found in DFS, return
            return root;

        TreeNode optionLeft = findIfPOrQInChildSubTree(root.left, p, q);
        TreeNode optionRight = findIfPOrQInChildSubTree(root.right, p, q);

        if(optionLeft!=null && optionRight!=null)
            return root;
        else if(optionLeft!=null && optionRight==null)
            return optionLeft;
        else if(optionLeft==null && optionRight!=null)
            return optionRight;
        else if(optionLeft==null && optionRight==null)
            return null;
        return null;
    }
}