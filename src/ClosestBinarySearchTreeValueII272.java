/**
 * Given a non-empty binary search tree and a target value, find k values in
 * the BST that are closest to the target.
 * 
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that
 * are closest to the target.
 * 
 * Example:
 * 
 * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
 * 
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * 
 * Output: [4,3]
 * 
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n)
 * runtime (where n = total nodes)?
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

public class ClosestBinarySearchTreeValueII272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        helper(root, target, k, res);
        return res;
    }

    private void helper(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;
        helper(root.left, target, k, res);
        res.addLast(root.val);
        helper(root.right, target, k, res);
        cut(target, k, res);
    }
    
    private void cut(double target, int k, LinkedList<Integer> res) {
        if (res.size() > k) {
            if (Math.abs(res.getFirst() * 1.0 - target) >= Math.abs(res.getLast() * 1.0 - target)) {
                res.removeFirst();
            } else {
                res.removeLast();
            }
        }
    }

}
