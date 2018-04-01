/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class PalindromeLinkedList234 {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(0);

        ListNode f = head;
        ListNode s = head;
        while (s != null && f != null && f.next != null) {
            ListNode t = s;
            s = s.next;
            f = f.next.next;
            t.next = dummy.next;
            dummy.next = t;
        }

        if (f != null) {
            s = s.next;
        }
        dummy = dummy.next;
        while (s != null && dummy != null) {
            if (dummy.val != s.val) return false;
            s = s.next;
            dummy = dummy.next;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
     */
    public boolean isPalindrome2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

}
