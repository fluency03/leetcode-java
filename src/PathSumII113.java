/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given the below binary tree and sum = 22,
 * 
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 * 
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
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

public class PathSumII113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSum(root, 0, sum, new ArrayList<>(), res);
        return res;
    }

    private void pathSum(TreeNode root, int sumSoFar, int target, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        int newSum = sumSoFar + root.val;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (newSum == target) res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        pathSum(root.left, newSum, target, path, res);
        pathSum(root.right, newSum, target, path, res);
        path.remove(path.size() - 1);
    }

}
