/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
 * memory, where h is the height of the tree.
 *
 */


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BinarySearchTreeIterator173 {
    class BSTIterator {
        private Stack<TreeNode> stack = new Stack();

        public BSTIterator(TreeNode root) {
            if (root != null) {
                addAllLeft(root);
            }
        }

        private void addAllLeft(TreeNode node) {
            while (true) {
                this.stack.push(node);
                node = node.left;
                if (node == null) return;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode tmp = this.stack.pop();
            int returned = tmp.val;
            if (tmp.right != null) {
                addAllLeft(tmp.right);
            }
            return returned;
        }
    }

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

}


