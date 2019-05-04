/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s)
 * (the most frequently occurred element) in the given BST.
 * 
 * Assume a BST is defined as follows:
 *  - The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *  - The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *  - Both the left and right subtrees must also be binary search trees.
 * For example:
 * Given BST [1,null,2,2],
 * 
 *    1
 *     \
 *      2
 *     /
 *    2
 *  
 * return [2].
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
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

public class FindModeInBinarySearchTree501 {
    public int[] findMode(TreeNode root) {
        Result result = new Result();
        findMode(root, result);
        int[] output = new int[result.res.size()];
        int i = 0;
        for (int r: result.res) {
            output[i++] = r;
        }
        return output;
    }

    private void findMode(TreeNode root, Result result) {
        if (root == null) return;

        findMode(root.left, result);
        
        if (result.maxCount == -1) {
            result.maxCount = 1;
            result.currCount = 1;
            result.curr = root.val;
            result.res = new HashSet<>();
            result.res.add(root.val);
        } else if (result.curr != root.val) {
            result.currCount = 1;
            result.curr = root.val;
            if (result.maxCount == result.currCount) {
                result.res.add(root.val);
            }
        } else {
            result.currCount++;
            if (result.maxCount == result.currCount) {
                result.res.add(root.val);
            } else if (result.maxCount < result.currCount) {
                result.maxCount = result.currCount;
                result.res = new HashSet<>();
                result.res.add(root.val);
            }
        }

        findMode(root.right, result);
    }

    class Result {
        int maxCount;
        int currCount;
        int curr;
        Set<Integer> res;
        Result() {
            this.maxCount = -1;
            this.currCount = -1;
            this.curr = 0;
            this.res = new HashSet<>();
        }
    }
    

    /**
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98101/Proper-O(1)-space
     */
    public int[] findMode2(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    
    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

}
