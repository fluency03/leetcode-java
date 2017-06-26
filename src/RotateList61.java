/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class RotateList61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode first = head;
        ListNode second = head;

        int i = 0;
        while (i < k && first.next != null) {
            first = first.next;
            i++;
        }

        if (first.next == null && i < k) {
            return rotateRight(head, k%(i+1));
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        ListNode returned = second.next;
        first.next = head;
        second.next = null;

        return returned;
    }




}
