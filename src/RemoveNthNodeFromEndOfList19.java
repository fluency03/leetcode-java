/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 *    Given linked list: 1->2->3->4->5, and n = 2.
 *
 *    After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
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


public class RemoveNthNodeFromEndOfList19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        int k = 1;
        ListNode q = head;
        while (q.next != null) {
            k++;
            q = q.next;
        }
        if (n == k) return head.next;

        ListNode dummy = new ListNode(0);
        ListNode p = head;
        dummy.next = p;

        int i = 1;
        while (i < k-n) {
            p = p.next;
            i++;
        }

        p.next = p.next.next;

        return dummy.next;
    }


    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode f = dummy;
        ListNode s = dummy;
        int i = 0;
        while (i < n) {
            f = f.next;
            i++;
        }

        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        s.next = s.next.next;

        return dummy.next;
    }

}
