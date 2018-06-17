/**
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to
 * find the kth smallest frequently? How would you optimize the kthSmallest
 * routine?
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


public class KthSmallestElementInABST230 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<Integer> st = new Stack<>();
        kthSmallest(root, k, st);
        return st.pop();
    }

    public void kthSmallest(TreeNode root, int k, Stack<Integer> st) {
        if (root == null) return;

        kthSmallest(root.left, k, st);
        if (st.size() == k) return;

        st.push(root.val);
        if (st.size() == k) return;

        kthSmallest(root.right, k, st);
    }


    public int kthSmallest2(TreeNode root, int k) {
        int[] i = new int[]{0, 0};
        kthSmallest(root, k, i);
        return i[1];
    }

    public void kthSmallest(TreeNode root, int k, int[] i) {
        if (root.left != null) kthSmallest(root.left, k, i);
        if (i[0] == k) return ;

        i[0] = i[0] + 1;
        i[1] = root.val;
        if (i[0] == k) return;

        if (root.right != null) kthSmallest(root.right, k, i);
    }


    public int kthSmallest3(TreeNode root, int k) {
        int l = kth(root.left);
        if (l >= k) {
            return kthSmallest3(root.left, k);
        } else if (l + 1 == k) {
            return root.val;
        } else {
            return kthSmallest3(root.right, k-l-1);
        }
    }

    public int kth(TreeNode root) {
        if (root == null) return 0; 
        return kth(root.left) + kth(root.right) + 1;
    }


}
