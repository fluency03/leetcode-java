/**
 * Given a singly linked list L: L0?L1?…?Ln-1?Ln,
 * reorder it to: L0?Ln?L1?Ln-1?L2?Ln-2?…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 *      Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class ReorderList143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode fast = head;
        ListNode slow = head;

        Stack<ListNode> st = new Stack<>();

        while (fast.next != null && fast.next.next != null) {
            st.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode half = new ListNode(0);
        if (fast.next == null) {
            half = slow.next;
            dummy.next = slow;
            dummy.next.next = null;
        } else if (fast.next.next == null) {
            half = slow.next.next;
            dummy.next = slow;
            dummy.next.next.next = null;
        }

        while (half != null) {
            ListNode newNode = half;
            half = half.next;
            newNode.next = dummy.next;
            dummy.next = newNode;
            newNode = st.pop();
            newNode.next = dummy.next;
            dummy.next = newNode;
        }

        head = dummy.next;
    }





}
