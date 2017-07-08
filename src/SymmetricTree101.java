/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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


public class SymmetricTree101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        int i = 1;
        while (!queue.isEmpty()) {
            Stack<TreeNode> st = new Stack<>();
            i = queue.size() >> 1;

            int k = 0;
            while (k<i) {
                if (queue.isEmpty()) return false;
                TreeNode node = queue.remove();
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
                st.push(node);
                k++;
            }

            k = 0;
            while (k<i) {
                if (queue.isEmpty()) return false;
                TreeNode node = queue.remove();
                TreeNode pre = st.pop();
                if (node != null) {
                    if (pre == null || node.val != pre.val) return false;
                    queue.add(node.left);
                    queue.add(node.right);
                } else if (pre != null) {
                    return false;
                }
                k++;
            }
        }

        return true;
    }


    /**
     * https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root==null)  return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left, right;
        if (root.left != null) {
            if (root.right == null) return false;
            stack.push(root.left);
            stack.push(root.right);
        } else if (root.right != null) {
            return false;
        }

        while (!stack.empty()) {
            if (stack.size()%2 != 0)   return false;
            right = stack.pop();
            left = stack.pop();
            if(right.val != left.val) return false;

            if (left.left != null) {
                if(right.right == null)   return false;
                stack.push(left.left);
                stack.push(right.right);
            } else if(right.right != null){
                return false;
            }

            if (left.right != null) {
                if(right.left == null)   return false;
                stack.push(left.right);
                stack.push(right.left);
            } else if(right.left != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java
     */
    public boolean isSymmetric3(TreeNode root) {
        return root==null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left==null || right==null)
            return left==right;
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

}
