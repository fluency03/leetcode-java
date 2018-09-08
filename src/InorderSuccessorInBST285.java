/**
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST.
 *
 * Note: If the given node has no in-order successor in the tree, return null.
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

public class InorderSuccessorInBST285 {
    // Iteratively
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null)
            return findMin(p.right);

        TreeNode succ = null;
        while (root != null) {
            if (root.val == p.val) break;
            if (root.val > p.val) {
                succ = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return succ;
    }

    private TreeNode findMin(TreeNode n) {
        if (n == null) return null;
        TreeNode t = n;
        while (t.left != null) t = t.left;
        return t;
    }


    // Recursively
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode x) {
        if (x.right != null)
            return findMin(x.right);
        return inorderSuccessor2(root, x, null);
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode x, TreeNode succ) {
        if (root == null || x == null || root.val == x.val) return succ;
        if (root.val > x.val) {
            return inorderSuccessor2(root.left, x, root);
        } else {
            return inorderSuccessor2(root.right, x, succ);
        }
    }


    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        return inorderSuccessor3(root, p, null);
    }

    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p, TreeNode pre) {
        if (root.val == p.val) {
            if (root.right == null) {
                return pre;
            } else {
                return findMin(root.right);
            }
        } else if (root.val > p.val) {
            return inorderSuccessor3(root.left, p, root);
        } else {
            return inorderSuccessor3(root.right, p, pre);
        }
    }


    /**
     * https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
     */
    public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }

}
