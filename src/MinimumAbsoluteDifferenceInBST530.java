/**
 * Given a binary search tree with non-negative values, find the minimum
 * absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * Input:
 * 
 *    1
 *     \
 *      3
 *     /
 *    2
 * 
 * Output:
 * 1
 * 
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2
 * and 1 (or between 2 and 3).
 * 
 * Note: There are at least two nodes in this BST.
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

public class MinimumAbsoluteDifferenceInBST530 {
    public int getMinimumDifference(TreeNode root) {
        int[] res = new int[]{Integer.MAX_VALUE};
        helper(root, res);
        return res[0];
    }

    public int[] helper(TreeNode root, int[] res) {
        int[] bound = new int[]{root.val, root.val};
        if (root.left == null && root.right == null) return bound;
        if (root.left != null) {
            int[] leftRes = helper(root.left, res);
            bound[0] = Math.min(bound[0], leftRes[0]);
            bound[1] = Math.max(bound[1], leftRes[1]);
            res[0] = Math.min(res[0], Math.abs(leftRes[1] - root.val));
        }
        if (root.right != null) {
            int[] rightRes = helper(root.right, res);
            bound[0] = Math.min(bound[0], rightRes[0]);
            bound[1] = Math.max(bound[1], rightRes[1]);
            res[0] = Math.min(res[0], Math.abs(rightRes[0] - root.val));
        }
        return bound;
    }

}
