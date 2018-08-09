/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 * 
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class RemoveDuplicatesFromSortedList83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head; 
        ListNode fast = head.next;
        ListNode slow = head;
        slow.next = null;
        while (fast != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
            } else {
                slow.next = fast;
                fast = fast.next;
                slow = slow.next;
                slow.next = null;
            }
        }
        return head;
    }


    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list/solution/
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }


    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list/discuss/28625/3-Line-JAVA-recursive-solution
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates3(head.next);
        return head.val == head.next.val ? head.next : head;
    }

}
