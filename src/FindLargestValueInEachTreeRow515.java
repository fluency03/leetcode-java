/**
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * Input: 
 * 
 *           1
 *          / \
 *         3   2
 *        / \   \  
 *       5   3   9 
 * 
 * Output: [1, 3, 9]
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

public class FindLargestValueInEachTreeRow515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int maxVal = Integer.MIN_VALUE;
            for (int i=0; i<size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
                maxVal = Math.max(maxVal, curr.val);
            }
            res.add(maxVal);
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/find-largest-value-in-each-tree-row/discuss/98971/9ms-JAVA-DFS-solution
     */
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res, int d){
        if (root == null) return;
        if (d == res.size()) { //expand list size
            res.add(root.val);
        } else{ //or set value
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d+1);
        helper(root.right, res, d+1);
    }

}
