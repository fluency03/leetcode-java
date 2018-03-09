/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
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


public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || inorder.length == 0) return null;
        if (inorder.length == 1) return new TreeNode(inorder[0]);
        int val = preorder[0];
        int index = search(inorder, val);
        TreeNode root = new TreeNode(val);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, index+1), Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, index+1, preorder.length), Arrays.copyOfRange(inorder, index+1, inorder.length));
        return root;
    }

    private int search(int[] order, int val) {
        for (int i=0; i<order.length; i++) {
            if (order[i] == val) return i;
        }
        return -1;
    }



    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (inorder == null || inorder.length == 0) return null;
        if (inorder.length == 1) return new TreeNode(inorder[0]);
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 > e1 || s2 > e2) return null;
        if (s1 == e1 || s2 == e2) return new TreeNode(preorder[s1]);
        int val = preorder[s1];
        int index = search(inorder, val, s2, e2);
        int len = index-s2;
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder, s1+1, len+s1, inorder, s2, index-1);
        root.right = buildTree(preorder, len+s1+1, e1, inorder, index+1, e2);
        return root;
    }

    private int search(int[] order, int val, int start, int end) {
        for (int i=start; i<=end; i++) {
            if (order[i] == val) return i;
        }
        return -1;
    }



    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return buildTree(preorder, 0, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int p, int[] inorder, int i, int j) {
        if (p >= inorder.length || i > j) return null;

        int curr = preorder[p];
        TreeNode res = new TreeNode(curr);

        int mid = i;
        while (inorder[mid] != curr) mid++;

        res.left = buildTree(preorder, p+1, inorder, i, mid-1);
        res.right = buildTree(preorder, p+(mid-i)+1, inorder, mid+1, j);

        return res;
    }



    /**
     * https://discuss.leetcode.com/topic/29838/5ms-java-clean-solution-with-caching
     */
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

}
