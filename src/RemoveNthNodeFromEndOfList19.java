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


    public ListNode removeNthFromEnd3(ListNode head, int n) {
        int l = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            l++;
        }

        if (l < n) return head;
        if (l == n) {
            head = head.next;
            return head;
        }

        int k = l-n;
        int i = 1;
        p = head;
        while (i < k) {
            p = p.next;
            i++;
        }
        p.next = p.next.next;

        return head;
    }

    public ListNode removeNthFromEnd4(ListNode head, int n) {
        if (head == null) return null;
        int m = 1;
        ListNode f = head;
        ListNode s = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
            m++;
        }

        int len = f.next == null ? (m*2-1) : m*2;
        if (len < n) return head;
        if (len == n) {
            head = head.next;
            return head;
        }

        int k = len-n;
        if (m <= k) {
            while (m < k) {
                s = s.next;
                m++;
            }
            s.next = s.next.next;
        } else {
            int i=1;
            f = head;
            while (i < k) {
                f = f.next;
                i++;
            }
            f.next = f.next.next;
        }

        return head;
    }


    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/solution/
     */
    public ListNode removeNthFromEnd5(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }


    public ListNode removeNthFromEnd6(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

}
