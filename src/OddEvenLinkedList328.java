/**
 * Given a singly linked list, group all odd nodes together followed by the
 * even nodes. Please note here we are talking about the node number and not
 * the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 *
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 *
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
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


public class OddEvenLinkedList328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode evens = new ListNode(0);
        ListNode e = evens;

        ListNode p = dummy;
        while (p.next != null) {
            p = p.next;
            if (p.next == null) break;
            e.next = new ListNode(p.next.val);
            e = e.next;
            p.next = p.next.next;
        }
        p.next = evens.next;
        return dummy.next;
    }


    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode evens = new ListNode(0);
        ListNode e = evens;

        ListNode p = dummy;
        while (p.next != null) {
            p = p.next;
            if (p.next == null) break;
            e.next = p.next;
            e = e.next;
            p.next = p.next.next;
            e.next = null;
        }
        p.next = evens.next;
        return dummy.next;
    }


    /**
     * https://leetcode.com/problems/odd-even-linked-list/solution/
     */
    public ListNode oddEvenList3(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
