/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
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

public class BinaryTreePaths257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePaths(root, new StringBuilder(), res);
        return res;
    }

    public void binaryTreePaths(TreeNode root, StringBuilder path, List<String> res) {
        if (root == null) return;
        path.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(path.toString());
            return;
        }

        path.append("->");
        int l = path.length();
        binaryTreePaths(root.left, path, res);
        path.delete(l, path.length());
        binaryTreePaths(root.right, path, res);
        path.delete(l, path.length());
    }


    /**
     * https://leetcode.com/problems/binary-tree-paths/discuss/68278/My-Java-solution-in-DFS-BFS-recursion
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> sList=new LinkedList<String>();
        if (root==null) return sList;
        if (root.left==null && root.right==null) {
            sList.add(Integer.toString(root.val));
            return sList;
        }

        for (String s: binaryTreePaths(root.left)) {
            sList.add(Integer.toString(root.val)+"->"+s);
        }
        for (String s: binaryTreePaths(root.right)) {
            sList.add(Integer.toString(root.val)+"->"+s);
        }
        return sList;
    }

}
