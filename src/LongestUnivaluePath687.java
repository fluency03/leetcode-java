/**
 * Given a binary tree, find the length of the longest path where each node in
 * the path has the same value. This path may or may not pass through the root.
 * 
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * 
 * Example 1:
 * Input:
 * 
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 * 
 * Example 2:
 * Input:
 * 
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 * 
 * Note: The given binary tree has not more than 10000 nodes. The height of the
 * tree is not more than 1000.
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

public class LongestUnivaluePath687 {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        return helper(root)[1];
    }

    private int[] helper(TreeNode root) {
        int[] res = new int[]{0, 0};
        if (root.left != null && root.right != null) {
            int[] left = helper(root.left);
            int[] right = helper(root.right);
            if (root.val == root.left.val && root.val == root.right.val) {
                res[0] = Math.max(left[0], right[0]) + 1;
                res[1] = left[0] + right[0] + 2;
            } else if (root.val == root.left.val) {
                res[0] = left[0] + 1;
            } else if (root.val == root.right.val) {
                res[0] = right[0] + 1;
            }
            res[1] = Math.max(Math.max(Math.max(left[1], right[1]), res[1]), res[0]);
        } else if (root.left != null) {
            int[] left = helper(root.left);
            res[0] = root.val == root.left.val ? left[0] + 1 : 0;
            res[1] = Math.max(left[1], res[0]);
        } else if (root.right != null) {
            int[] right = helper(root.right);
            res[0] = root.val == root.right.val ? right[0] + 1 : 0;
            res[1] = Math.max(right[1], res[0]);
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-univalue-path/solution/
     */
    int ans;
    public int longestUnivaluePath2(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }


}
