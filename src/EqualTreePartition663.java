/**
 * Given a binary tree with n nodes, your task is to check if it's possible to
 * partition the tree to two trees which have the equal sum of values after
 * removing exactly one edge on the original tree.
 * 
 * Example 1:
 * Input:     
 *     5
 *    / \
 *   10 10
 *     /  \
 *    2   3
 * 
 * Output: True
 * 
 * Explanation: 
 *     5
 *    / 
 *   10
 * Sum: 15
 * 
 *    10
 *   /  \
 *  2    3
 * Sum: 15
 * 
 * Example 2:
 * 
 * Input:     
 *     1
 *    / \
 *   2  10
 *     /  \
 *    2   20
 * 
 * Output: False
 * 
 * Explanation: You can't split the tree into two trees with equal sum after
 * removing exactly one edge on the tree.
 * 
 * Note:
 * The range of tree node value is in the range of [-100000, 100000].
 * 1 <= n <= 10000
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

public class EqualTreePartition663 {
    public boolean checkEqualTree(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        int total = sum(root, set, true);
        return total % 2 == 0 && set.contains(total / 2);
    }

    private int sum(TreeNode root, Set<Integer> set, boolean isRoot) {
        if (root == null) return 0;
        int left = sum(root.left, set, false);
        int right = sum(root.right, set, false);
        int s = left + root.val + right;
        if (!isRoot) set.add(s);
        return s;
    }

}
