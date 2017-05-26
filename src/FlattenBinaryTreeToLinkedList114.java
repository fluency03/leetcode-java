/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 *         1
 *        / \
 *       2   5
 *      / \   \
 *    3   4   6
 *
 * The flattened tree should look like:
 *  1
 *   \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
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

public class FlattenBinaryTreeToLinkedList114 {
    public void flatten(TreeNode root) {
        flattenNode(root);
    }

    private TreeNode flattenNode(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        if (left == null && right == null) {
            return root;
        } else if (left == null && right != null) {
            TreeNode rightEnd = flattenNode(right);
            return rightEnd;
        } else if (left != null && right == null) {
            TreeNode leftEnd = flattenNode(left);
            root.right = left;
            return leftEnd;
        } else {
            TreeNode leftEnd = flattenNode(left);
            TreeNode rightEnd = flattenNode(right);
            leftEnd.right = right;
            root.right = left;
            return rightEnd;
        }
    }

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList114 fbt2ll = new FlattenBinaryTreeToLinkedList114();

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;

        fbt2ll.flatten(n1);
        System.out.println("Out!");
        System.out.println(n1.val);
        System.out.println(n1.right.val);
        System.out.println(n1.right.right.val);
        System.out.println(n1.right.right.right.val);
        System.out.println(n1.right.right.right.right.val);
        System.out.println(n1.right.right.right.right.right.val);
    }

}
