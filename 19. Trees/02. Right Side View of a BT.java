/*https://leetcode.com/problems/binary-tree-right-side-view/ */
 
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        if (root == null)
            return rightSideView;
        
        Queue<TreeNode> que = new ArrayDeque<>();
        que.add(root); //first push the root

        while(!que.isEmpty()) {
            int currlevelWidth = que.size();

            for(int i=0; i<currlevelWidth; i++) { //traversing horizontally nodes at each level
                TreeNode currNode = que.poll();
                if(i == currlevelWidth-1) // rightmost/last node element at each level
                    rightSideView.add(currNode.val);

                if(currNode.left != null)
                    que.add(currNode.left);
                if(currNode.right != null)
                    que.add(currNode.right);
            }
        }
        return rightSideView;
    }
}