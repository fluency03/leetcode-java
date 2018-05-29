/**
 * Given a non-empty special binary tree consisting of nodes with the
 * non-negative value, where each node in this tree has exactly two or zero
 * sub-node. If the node has two sub-nodes, then this node's value is the
 * smaller value among its two sub-nodes.
 * 
 * Given such a binary tree, you need to output the second minimum value in the
 * set made of all the nodes' value in the whole tree.
 * 
 * If no such second minimum value exists, output -1 instead.
 * 
 * Example 1:
 * Input: 
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 * 
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * 
 * Example 2:
 * Input: 
 *     2
 *    / \
 *   2   2
 * 
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest
 * value.
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

public class SecondMinimumNodeInABinaryTree671 {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        int[] cache = new int[]{-1, -1};
        dfs(root, cache, root.val);
        if (cache[0] == -1 || cache[1] == -1) return -1;
        return Math.max(Math.max(cache[0], -1), cache[1]);
    }

    private void dfs(TreeNode root, int[] cache, int min) {
        if (root == null) return;
        int val = root.val;
        if (cache[0] != val && cache[1] != val) {
            if (cache[0] == -1) {
                cache[0] = val;
            } else if (cache[1] == -1) {
                cache[1] = val;
            } else {
                if (cache[0] > val) {
                    cache[0] = val;
                } else if (cache[1] > val) {
                    cache[1] = val;
                }
            }
        }
        if (val > min) return;
        
        dfs(root.left, cache, min);
        dfs(root.right, cache, min);
    }

}

