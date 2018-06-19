/**
 * Given a non-empty binary search tree and a target value, find the value in
 * the BST that is closest to the target.
 * 
 * Note:
 * 
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest
 * to the target.
 * 
 * Example:
 * 
 * Input: root = [4,2,5,1,3], target = 3.714286
 * 
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * 
 * Output: 4
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

public class ClosestBinarySearchTreeValue270 {
    public int closestValue(TreeNode root, double target) {
        return closestValue(root, target, null, null);
    }

    private int closestValue(TreeNode root, double target, Integer mi, Integer ma) {
        if (root == null) {
            if (mi == null) return ma;
            else if (ma == null) return mi;
            else return (ma - target > target - mi) ? mi : ma;
        } 

        double val = root.val * 1.0;
        if (val == target) {
            return root.val;
        } else if (val > target) {
            return closestValue(root.left, target, mi, root.val);
        } else {
            return closestValue(root.right, target, root.val, ma);
        }
    }


    public int closestValue2(TreeNode root, double target) {
        Integer mi = null;
        Integer ma = null;

        while (root != null) {
            double val = root.val * 1.0;
            if (val == target) {
                return root.val;
            } else if (val > target) {
                ma = root.val;
                root = root.left;
            } else {
                mi = root.val;
                root = root.right;
            }
        }

        if (mi == null) return ma;
        else if (ma == null) return mi;
        else return (ma - target > target - mi) ? mi : ma;
    }
    

    /**
     * https://leetcode.com/problems/closest-binary-search-tree-value/discuss/70327/4-7-lines-recursiveiterative-RubyC++JavaPython
     */
    public int closestValue3(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }


    /**
     * https://leetcode.com/problems/closest-binary-search-tree-value/discuss/70331/Clean-and-concise-java-solution
     */
    public int closestValue4(TreeNode root, double target) {
        int ret = root.val;   
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }      
            root = root.val > target? root.left: root.right;
        }     
        return ret;
    }

}
