/**
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * 
 * For example, given a 3-ary tree:
 * https://leetcode.com/static/images/problemset/NaryTreeExample.png
 * 
 * Return its postorder traversal as: [5,6,3,2,4,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
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

public class NaryTreePostorderTraversal590 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root == null) return;
        for (Node child: root.children) {
            helper(child, res);
        }
        res.add(root.val);
    }


    public List<Integer> postorder2(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.addFirst(curr.val);
            for (Node child: curr.children) {
                stack.add(child);
            }
        }
        return res;
    }

}
