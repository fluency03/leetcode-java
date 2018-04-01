/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
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


public class BinaryTreeZigzagLevelOrderTraversal103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> level = new LinkedList<>();
        level.add(root);
        zigzagLevelOrder(level, true, res);
        return res;
    }

    private void zigzagLevelOrder(LinkedList<TreeNode> level, boolean left, List<List<Integer>> res) {
        if (level.isEmpty()) return;
        LinkedList<Integer> one = new LinkedList<>();
        LinkedList<TreeNode> newLevel = new LinkedList<>();
        while (!level.isEmpty()) {
            TreeNode t = level.removeFirst();
            if (t == null) continue;
            if (left) one.add(t.val);
            else one.addFirst(t.val);
            newLevel.add(t.left);
            newLevel.add(t.right);
        }
        if (!one.isEmpty()) res.add(one);
        zigzagLevelOrder(newLevel, !left, res);
    }

}
