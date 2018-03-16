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

// space is O(n) but not O(h)
public class BSTIterator {

    Queue<Integer> q = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        construct(root);
    }

    private void construct(TreeNode node) {
        if (node == null) return;
        construct(node.left);
        q.add(node.val);
        construct(node.right);
    }


    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !q.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return q.remove();
    }

}

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/discuss/52525/My-solutions-in-3-languages-with-Stack
 */
// space is O(h), hasNext is (1), but next is O(h), on average O(1)
// public class BSTIterator {
//     private Stack<TreeNode> stack = new Stack<TreeNode>();
//
//     public BSTIterator(TreeNode root) {
//         pushAll(root);
//     }
//
//     /** @return whether we have a next smallest number */
//     public boolean hasNext() {
//         return !stack.isEmpty();
//     }
//
//     /** @return the next smallest number */
//     public int next() {
//         TreeNode tmpNode = stack.pop();
//         pushAll(tmpNode.right);
//         return tmpNode.val;
//     }
//
//     private void pushAll(TreeNode node) {
//         for (; node != null; stack.push(node), node = node.left);
//     }
// }


/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
