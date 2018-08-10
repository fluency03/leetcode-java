/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *
 * return 1->4->3->2->5->NULL.
 *
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ? m ? n ? length of list.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class ReverseLinkedListII92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        int i = 0;
        while (i < m-1) {
            p = p.next;
            i++;
        }

        ListNode before = p;
        p = p.next;
        i++;

        ListNode start = new ListNode(0);
        start.next = p;

        while (i < n) {
            ListNode r = p.next;
            p.next = p.next.next;
            r.next = start.next;
            start.next = r;
            i++;
        }

        before.next = start.next;

        return dummy.next;
    }


    /**
     * https://discuss.leetcode.com/topic/8976/simple-java-solution-with-clear-explanation
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for(int i = 0; i<m-1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for(int i=0; i<n-m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }


    public ListNode reverseBetween3(ListNode head, int m, int n) {
        int i = 1;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode h = head;
        while (i < m) {
            p.next = h;
            h = h.next;
            p = p.next;
            p.next = null;
            i++;
        }

        ListNode tail = h;
        while (i <= n) {
            ListNode in = h;
            h = h.next;
            in.next = p.next;
            p.next = in;
            i++;
        }
        
        while (h != null) {
            tail.next = h;
            h = h.next;
            tail = tail.next;
            tail.next = null;
        }
        return dummy.next;
    }


}
