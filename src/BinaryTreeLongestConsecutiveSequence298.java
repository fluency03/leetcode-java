/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path
 * need to be from parent to child (cannot be the reverse).
 * 
 * Example 1:
 * 
 * Input:
 * 
 *   1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * 
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * 
 * Example 2:
 * 
 * Input:
 * 
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * 
 * Output: 2 
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
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

public class BinaryTreeLongestConsecutiveSequence298 {
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return helper(root, 0);
    }

    public int helper(TreeNode root, int i) {
        if (root == null) return i;
        
        int l = i + 1;
        if (root.left != null) {
            if (root.val + 1 == root.left.val) {
                l = helper(root.left, i+1);
            } else {
                l = helper(root.left, 0);
            }
        }

        int r = i + 1;
        if (root.right != null) {
            if (root.val + 1 == root.right.val) {
                r = helper(root.right, i+1);
            } else {
                r = helper(root.right, 0);
            }
        }

        return Math.max(Math.max(l, r), i+1);
    }


    /**
     * Bottom-up
     * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/solution/
     */
    private int maxLength = 0;
    public int longestConsecutive2(TreeNode root) {
        dfs(root);
        return maxLength;
    }
    
    private int dfs(TreeNode p) {
        if (p == null) return 0;
        int L = dfs(p.left) + 1;
        int R = dfs(p.right) + 1;
        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }
        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }
        int length = Math.max(L, R);
        maxLength = Math.max(maxLength, length);
        return length;
    }

}
