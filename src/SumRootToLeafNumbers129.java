/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *     1
 *    / \
 *   2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 *
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


public class SumRootToLeafNumbers129 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return helper(root, new ArrayList<Integer>(), 0);
    }

    public int helper(TreeNode node, List<Integer> path, int sum) {
        if (node == null) {
            return sum;
        }

        path.add(node.val);
        if (node.left == null && node.right == null) {
            int newSum = 0;
            for (int i = 0; i < path.size(); i++) {
                newSum += path.get(i) * (int) Math.pow(10, (path.size() - 1 - i));
            }
            path.remove(path.size() - 1);
            return sum + newSum;
        }

        sum = helper(node.left, path, sum);
        sum = helper(node.right, path, sum);

        path.remove(path.size() - 1);
        return sum;
    }


    /**
     * https://discuss.leetcode.com/topic/6731/short-java-solution-recursion/17
     */
    public int sumNumbers(TreeNode root) {
    	 return sum(root, 0);
    }

    public int sum(TreeNode n, int s){
    	  if (n == null) return 0;
    	  if (n.right == null && n.left == null) return s*10 + n.val;
    	  return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }


}
