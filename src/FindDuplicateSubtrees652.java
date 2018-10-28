/**
 * Given a binary tree, return all duplicate subtrees. For each kind of
 * duplicate subtrees, you only need to return the root node of any one of them.
 * 
 * Two trees are duplicate if they have the same structure with same node values.
 * 
 * Example 1:
 * 
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 
 * The following are two duplicate subtrees:
 * 
 *       2
 *      /
 *     4
 * 
 * and
 * 
 *     4
 * 
 * Therefore, you need to return above trees' root in the form of a list.
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

public class FindDuplicateSubtrees652 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Map<String, Boolean> visited = new HashMap<>();
        findDuplicates(root, visited, res);
        return res;
    }

    private String findDuplicates(TreeNode root, Map<String, Boolean> visited, List<TreeNode> res) {
        if (root == null) {
            return "N";
        }
        String self = Integer.toString(root.val);
        String left = findDuplicates(root.left, visited, res);
        String right = findDuplicates(root.right, visited, res);
        String curr = self + "-" + left + "-" + right;
        if (!visited.containsKey(curr)) {
            visited.put(curr, false);
        } else {
            if (!visited.get(curr)) {
                res.add(root);
                visited.put(curr, true);
            }
        }
        return curr;
    }

}
