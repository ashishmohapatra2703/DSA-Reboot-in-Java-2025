/**/

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        int[] indexInPOT = {0};
        return buildBT(preorder, inorder, 0, n-1, indexInPOT);
    }
    private TreeNode buildBT(int[] preorder, int[] inorder, int start, int end, int[] idx) {
        if(start > end)
            return null;

        int rootVal = preorder[idx[0]];
        idx[0]++;
        //Search for idx in inorder[], either Linear Search, Or use HashMap
        int i=start;
        for(; i<=end; i++) {
            if(rootVal == inorder[i])
                break;
        }

        TreeNode root = new TreeNode(rootVal);
        root.left = buildBT(preorder, inorder, start, i-1, idx); //leftChildSubTree
        root.right = buildBT(preorder, inorder, i+1, end, idx);// rightChildSubTree
        return root;
    }
}