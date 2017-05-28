/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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




public class PathSum112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        return sum(root, sum, 0);
    }

    private boolean sum(TreeNode root, int sum, int sumForNow) {
        if (root == null) {
            return false;
        }

        sumForNow += root.val;

        if (sumForNow == sum && root.left == null && root.right == null) {
            return true;
        }

        if (sum(root.left, sum, sumForNow)) {
            return true;
        }

        return sum(root.right, sum, sumForNow);
    }


    /**
     * https://discuss.leetcode.com/topic/3149/accepted-my-recursive-solution-in-java
     */
     public boolean hasPathSum2(TreeNode root, int sum) {
         if(root == null) return false;

         if(root.left == null && root.right == null && sum - root.val == 0) return true;

         return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
     }

}
