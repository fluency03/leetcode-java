/**
 * Given a root node reference of a BST and a key, delete the node with the given
 * key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * Example:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 *
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * Another valid answer is [5,2,6,null,4,null,7].
 *
 *     5
 *    / \
 *   2   6
 *   \   \
 *     4   7
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



public class DeleteNodeInABST450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        return delete(root);
    }

    private TreeNode delete(TreeNode node) {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        return moveNext(node);
    }

    private TreeNode moveNext(TreeNode node) {
        TreeNode min = node.right;
        if (min.left == null) {
            node.val = min.val;
            node.right = min.right;
            return node;
        }

        while (min.left != null && min.left.left != null) {
            System.out.println(min.val);
            min = min.left;
        }

        node.val = min.left.val;
        min.left = delete(min.left);
        return node;
    }



    public TreeNode deleteNode2(TreeNode root, int key) {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;

        /* Otherwise, recur down the tree */
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.val = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }

}
