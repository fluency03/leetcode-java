/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum
 * difference between the values of any two different nodes in the tree.
 * 
 * Example :
 * 
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 * 
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 * 
 *           4
 *         /   \
 *       2      6
 *      / \    
 *     1   3  
 * 
 * while the minimum difference in this tree is 1, it occurs between node 1 and
 * node 2, also between node 3 and node 2.
 * 
 * Note:
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's
 * value is different.
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

public class MinimumDistanceBetweenBSTNodes783 {
    public int minDiffInBST(TreeNode root) {        
        int[] res = new int[]{Integer.MAX_VALUE};
        minDiffInBST(root, new LinkedList<>(), res);
        return res[0];
    }

    public void minDiffInBST(TreeNode root, LinkedList<Integer> list, int[] res) {
        if (root.left != null) minDiffInBST(root.left, list, res);
        if (!list.isEmpty() && root.val - list.getLast() < res[0]) {
            res[0] = root.val - list.getLast();
        }
        list.add(root.val);
        if (root.right != null) minDiffInBST(root.right, list, res);
    }


    public int minDiffInBST2(TreeNode root) {        
        int[] res = new int[]{Integer.MAX_VALUE};
        Integer[] pre = new Integer[]{null};
        minDiffInBST2(root, new LinkedList<>(), pre, res);
        return res[0];
    }

    public void minDiffInBST2(TreeNode root, LinkedList<Integer> list, Integer[] pre, int[] res) {
        if (root.left != null) minDiffInBST2(root.left, list, pre, res);
        if (pre[0] != null && root.val - pre[0] < res[0]) {
            res[0] = root.val - pre[0];
        }
        pre[0] = root.val;
        if (root.right != null) minDiffInBST2(root.right, list, pre, res);
    }

}
