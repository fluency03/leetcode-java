/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it.
 * 
 * Note:
 * A subtree must include all of its descendants.
 * 
 * Example:
 * Input: [10,5,15,1,8,null,7]
 * 
 *    10 
 *    / \ 
 *   5  15 
 *  / \   \ 
 * 1   8   7
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 *              The return value is the subtree's size, which is 3.
 * 
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
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

public class LargestBSTSubtree333 {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        return helper(root)[0];
    }

    public int[] helper(TreeNode root) {
        //   0       1         2      3
        // {res, isBST(0/1), lower, upper}
        if (root == null) return null;

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        if (left == null && right == null) {
            return new int[]{1, 1, root.val, root.val};
        } else if (left == null) {
            boolean isBST = right[1] == 1 && root.val < right[2];
            int res = right[0] + (isBST ? 1 : 0);
            int lower = isBST ? root.val : 0;
            int upper = isBST ? right[3] : 0;
            return new int[]{res, isBST ? 1 : 0, lower, upper};
        } else if (right == null) {
            boolean isBST = left[1] == 1 && root.val > left[3];
            int res = left[0] + (isBST ? 1 : 0);
            int lower = isBST ? left[2] : 0;
            int upper = isBST ? root.val : 0;
            return new int[]{res, isBST ? 1 : 0, lower, upper};
        } else {
            boolean isBST = left[1] == 1 && right[1] == 1 && root.val > left[3] && root.val < right[2];
            int res = isBST ? (left[0] + right[0] + 1) : Math.max(left[0], right[0]);
            int lower = isBST ? left[2] : 0;
            int upper = isBST ? right[3] : 0;
            return new int[]{res, isBST ? 1 : 0, lower, upper}; 
        }
    }

}
