/**
 * Given a node from a cyclic linked list which is sorted in ascending order,
 * write a function to insert a value into the list such that it remains a
 * cyclic sorted list. The given node can be a reference to any single node in
 * the list, and may not be necessarily the smallest value in the cyclic list.
 * 
 * If there are multiple suitable places for insertion, you may choose any
 * place to insert the new value. After the insertion, the cyclic list should
 * remain sorted.
 * 
 * If the list is empty (i.e., given node is null), you should create a new
 * single cyclic list and return the reference to that single node.
 * 
 * Otherwise, you should return the original given node.
 * 
 * The following example may help you understand the problem better:
 * https://leetcode.com/static/images/problemset/InsertCyclicBefore.png
 * 
 * In the figure above, there is a cyclic sorted list of three elements. You
 * are given a reference to the node with value 3, and we need to insert 2
 * into the list.
 * https://leetcode.com/static/images/problemset/InsertCyclicAfter.png
 * 
 * The new node should insert between node 1 and node 3. After the insertion,
 * the list should look like this, and we should still return node 3.
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/

public class InsertIntoACyclicSortedList {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal, null);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        
        Node p = head;
        boolean inserted = false;
        do {
            if (p == p.next || p.val == insertVal || (p.val < insertVal && p.next.val > insertVal) ||
                    (((p.val > insertVal && p.next.val > insertVal) || (p.val < insertVal && p.next.val < insertVal)) && p.next.val < p.val)) {
                newNode.next = p.next;
                p.next = newNode;
                inserted = true;
                break;
            }
            p = p.next;
        } while (p != head);
        if (!inserted) {
            newNode.next = p.next;
            p.next = newNode;
        }
        return head;
    }


    public Node insert2(Node head, int insertVal) {
        Node newNode = new Node();
        newNode.val = insertVal;
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        Node p = head;
        Node dec = null;
        while (true) {
            if (p.val <= insertVal && p.next.val >= insertVal) {
                newNode.next = p.next;
                p.next = newNode;
                return head;
            }
            if (p.val > p.next.val) {
                dec = p;
            }
            p = p.next;
            if (p == head) break;
        }
        if (dec == null) dec = head;
        newNode.next = dec.next;
        dec.next = newNode;
        return head;
    }

}
