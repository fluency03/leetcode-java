/**
 * Reverse a singly linked list.
 *
 * Hint:
 * A linked list can be reversed either iteratively or recursively.
 * Could you implement both?
 *
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class ReverseLinkedList206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode tail = null;

        while (head != null) {
            ListNode t = head;
            head = head.next;
            tail = dummy.next;
            dummy.next = t;
            dummy.next.next = tail;
        }

        return dummy.next;
    }


    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        helper(head, dummy);
        return dummy.next;
    }

    private void helper(ListNode head, ListNode dummy) {
        if (head == null) return;
        ListNode curr = head;
        head = head.next;
        ListNode tail = dummy.next;
        dummy.next = curr;
        dummy.next.next = tail;
        helper(head, dummy);
    }


    public ListNode reverseList3(ListNode head) {
        return helper2(head, null);
    }

    private ListNode helper2(ListNode head, ListNode h) {
        if (head == null) return h;
        ListNode tail = head.next;
        head.next = h;
        return helper2(tail, head);
    }


    /**
     * https://leetcode.com/problems/reverse-linked-list/solution/
     */
    public ListNode reverseList4(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


    public ListNode reverseList5(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
