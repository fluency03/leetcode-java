/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Example 1:
 * Input:
 *
 *     2
 *    / \
 *   1   3
 *
 * Output:
 * 1
 *
 * Example 2:
 * Input:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * Output:
 * 7
 *
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
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


public class FindBottomLeftTreeValue513 {
    public int findBottomLeftValue(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Queue<TreeNode> ql = new LinkedList<>();
            System.out.println(q.peek().val);
            res = q.peek().val;
            while (!q.isEmpty()) {
                TreeNode n = q.poll();
                if (n.left != null) ql.offer(n.left);
                if (n.right != null) ql.offer(n.right);
            }
            q = ql;
        }

        return res;
    }


    public int findBottomLeftValue2(TreeNode root) {
        int res = root.val;
        Map<Integer, Integer> lefts = new HashMap<>();
        int level = helper(root, 0, lefts);
        return lefts.get(level);
    }

    private int helper(TreeNode node, int level, Map<Integer, Integer> lefts) {
        if (node == null) return level-1;
        if (!lefts.containsKey(level)) lefts.put(level, node.val);
        int leftLevel = helper(node.left, level+1, lefts);
        int rightLevel = helper(node.right, level+1, lefts);
        return Math.max(leftLevel, rightLevel);
    }

    /**
     * https://discuss.leetcode.com/topic/78962/simple-java-solution-beats-100-0
     */
    public int findBottomLeftValue3(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[]{0,0});
    }
    public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
        if (res[1]<depth) {res[0]=root.val;res[1]=depth;}
        if (root.left!=null) findBottomLeftValue(root.left, depth+1, res);
        if (root.right!=null) findBottomLeftValue(root.right, depth+1, res);
        return res[0];
    }


}
