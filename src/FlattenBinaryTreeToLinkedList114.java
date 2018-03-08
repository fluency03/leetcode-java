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

    public void flatten2(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        TreeNode leftLast = helper(root.left);

        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        return helper(root.right);
    }


    /**
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/37010/Share-my-simple-NON-recursive-solution-O(1)-space-complexity!
     */
    public void flatten3(TreeNode root) {
    	TreeNode cur = root;
    	while (cur != null) {
    		if (cur.left != null) {
    			TreeNode last = cur.left;
    			while (last.right != null) last = last.right;
    			last.right = cur.right;
    			cur.right = cur.left;
    			cur.left = null;
    		}
    		cur = cur.right;
    	}
    }


    /**
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share
     */
    public void flatten4(TreeNode root) {
        flatten(root,null);
    }

    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if(root==null) return pre;
        pre=flatten(root.right,pre);
        pre=flatten(root.left,pre);
        root.right=pre;
        root.left=null;
        pre=root;
        return pre;
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
