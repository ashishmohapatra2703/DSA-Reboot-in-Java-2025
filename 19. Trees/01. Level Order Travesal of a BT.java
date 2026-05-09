/** https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 * 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> LOT = new ArrayList<>();
        if (root == null)
            return LOT;
        
        Queue<TreeNode> que = new ArrayDeque<>();
        que.add(root); //first push the root

        while(!que.isEmpty()) {
            int currlevelWidth = que.size();
            List<Integer> currLevelNodes = new ArrayList<>();

            for(int i=0; i<currlevelWidth; i++) { //traversing horizontally nodes at each level
                TreeNode currNode = que.poll();
                currLevelNodes.add(currNode.val);

                if(currNode.left != null)
                    que.add(currNode.left);
                if(currNode.right != null)
                    que.add(currNode.right);
            }
            LOT.add(currLevelNodes);
        }
        return LOT;
    }
}