/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * For example, given a 3-ary tree:
 * https://leetcode.com/static/images/problemset/NaryTreeExample.png
 * 
 * We should return its level order traversal:
 * 
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 * 
 * Note:
 * The depth of the tree is at most 1000.
 * The total number of nodes is at most 5000.
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

public class NaryTreeLevelOrderTraversal429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i=0; i<size; i++) {
                Node curr = q.poll();
                level.add(curr.val);
                for (Node n: curr.children) {
                    q.add(n);
                }
            }
            res.add(level);
        }
        
        return res;
    }

}
