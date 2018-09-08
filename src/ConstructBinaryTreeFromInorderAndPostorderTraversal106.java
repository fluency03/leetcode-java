/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
        int len = inorder.length;
        return buildTree(inorder, 0, len-1, postorder, 0, len-1);
    }

    private TreeNode buildTree(int[] inorder, int ii, int ij, int[] postorder, int pi, int pj) {
        if (ii > ij) return null;
        int midVal = postorder[pj];
        TreeNode curr = new TreeNode(midVal);
        if (pi == pj) return curr;
        
        int mid = ii;
        while (inorder[mid] != midVal) mid++;
        
        curr.left = buildTree(inorder, ii, mid-1, postorder, pi, pi+(mid-1-ii));
        curr.right = buildTree(inorder, mid+1, ij, postorder, pi+(mid-ii), pj-1);
        return curr;
    }

}
