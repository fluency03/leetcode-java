/**
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called the "root." Besides the root, each house
 * has one and only one parent house. After a tour, the smart thief realized
 * that "all houses in this place forms a binary tree". It will automatically
 * contact the police if two directly-linked houses were broken into on the
 * same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
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

public class HouseRobberIII337 {
    public int rob(TreeNode root) {
        return helper(root).last;
    }

    private DP helper(TreeNode root) {
        if (root == null) {
            return new DP(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new DP(root.val, 0);
        }

        DP leftDP = helper(root.left);
        DP rightDP = helper(root.right);

        return new DP(
            Math.max(root.val+leftDP.prev+rightDP.prev, leftDP.last+rightDP.last),
            leftDP.last+rightDP.last
        );
    }

    class DP {
        Integer last;
        Integer prev;
        DP(Integer last, Integer prev) {
            this.last = last;
            this.prev = prev;
        }
    }


    public int rob2(TreeNode root) {
        int[] res = helper2(root);
        return res[1];
    }

    public int[] helper2(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        return new int[]{left[1] + right[1], Math.max(left[0] + right[0] + root.val, left[1] + right[1])};
    }

}
