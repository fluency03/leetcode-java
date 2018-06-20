/**
 * Given a Binary Search Tree and a target number, return true if there exist
 * two elements in the BST such that their sum is equal to the given target.
 * 
 * Example 1:
 * Input: 
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * 
 * Target = 9
 * Output: True
 * 
 * Example 2:
 * Input: 
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * 
 * Target = 28
 * Output: False
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

public class TwoSumIVInputIsABST653 {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = treeToList(root);
        
        for (int i=0; i<list.size(); i++) {
            int remain = k - list.get(i);
            for (int j=i+1; j<list.size(); j++) {
                if (list.get(j) == remain) return true;
                else if (list.get(j) > remain) break;
            }
        }
        return false;
    }

    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> list = treeToList(root);
        if (list.size() <= 1) return false;
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k) return true;
            if (sum < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    private List<Integer> treeToList(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }


    public boolean findTarget3(TreeNode root, int k) {
        return dfs(root, new HashSet<Integer>(), k);
    }
    
    private boolean dfs(TreeNode root, Set<Integer> set, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }


    /**
     * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solution/
     */
    public boolean findTarget4(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        Queue < TreeNode > queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else
                queue.remove();
        }
        return false;
    }

}
