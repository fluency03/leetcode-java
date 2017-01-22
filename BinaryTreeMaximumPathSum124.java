/**
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *
 *     1
 *    / \
 *   2   3
 *
 * Return 6.
 */


public class BinaryTreeMaximumPathSum124 {

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxBranch(root);
        return maxSum;
    }

    private int maxBranch(TreeNode root) {
        if (root == null) return 0;

        int valueLeft = Math.max(0, maxBranch(root.left));
        int valueRight = Math.max(0, maxBranch(root.right));
        maxSum = Math.max(maxSum, root.val + valueLeft + valueRight);

        return Math.max(valueLeft, valueRight) + root.val;
    }


    public static void main(String[] args) {
        BinaryTreeMaximumPathSum124 btmps = new BinaryTreeMaximumPathSum124();

        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(-3);

        System.out.println(btmps.maxPathSum(root1));
        System.out.println(btmps.maxPathSum(root2));

    }

}
