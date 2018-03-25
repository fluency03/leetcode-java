/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class SortList148 {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode s = head;
        ListNode f = head;
        ListNode pre = s;
        while (s != null && f != null && f.next != null) {
            pre = s;
            s = s.next;
            f = f.next.next;
        }

        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(s);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode res = new ListNode(-1);
        ListNode p = res;
        ListNode l = left;
        ListNode r = right;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                p.next = l;
                l = l.next;
            } else {
                p.next = r;
                r = r.next;
            }
            p = p.next;
        }
        if (l != null) p.next = l;
        if (r != null) p.next = r;
        return res.next;
    }


}
