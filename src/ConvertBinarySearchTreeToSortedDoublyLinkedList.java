/**
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the
 * left and right pointers as synonymous to the previous and next pointers in a
 * doubly-linked list.
 *
 * Let's take the following BST as an example, it may help you understand the problem better:
 *
 * https://leetcode.com/static/images/problemset/BSTDLLOriginalBST.png
 *
 * We want to transform this BST into a circular doubly linked list. Each node
 * in a doubly linked list has a predecessor and successor. For a circular
 * doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 *
 * The figure below shows the circular doubly linked list for the BST above.
 * The "head" symbol means the node it points to is the smallest element of
 * the linked list.
 *
 * https://leetcode.com/static/images/problemset/BSTDLLReturnDLL.png
 *
 * Specifically, we want to do the transformation in place. After the
 * transformation, the left pointer of the tree node should point to its
 * predecessor, and the right pointer should point to its successor. We should
 * return the pointer to the first element of the linked list.
 *
 * The figure below shows the transformed BST. The solid line indicates the
 * successor relationship, while the dashed line means the predecessor relationship.
 *
 * https://leetcode.com/static/images/problemset/BSTDLLReturnBST.png
 *
 */


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node[] edges = dfs(root);
        edges[0].left = edges[1];
        edges[1].right = edges[0];
        return edges[0];
    }

    private Node[] dfs(Node n) {
        if (n.left == null && n.right == null) return new Node[]{n, n};
        Node head;
        Node tail;
        if (n.left == null) {
            head = n;
            head.left = null;
        } else {
            Node[] left = dfs(n.left);
            head = left[0];
            head.left = null;
            Node leftTail = left[1];
            leftTail.right = n;
            n.left = leftTail;
        }
        if (n.right == null) {
            tail = n;
            tail.right = null;
        } else {
            Node[] right = dfs(n.right);
            tail = right[1];
            tail.right = null;
            Node rightHead = right[0];
            rightHead.left = n;
            n.right = rightHead;
        }

        return new Node[]{head, tail};
    }


}
