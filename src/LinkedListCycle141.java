/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 * Can you solve it without using extra space?
 *
 */


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class LinkedListCycle141 {
    public boolean hasCycle(ListNode head) {
        // ListNode slow = head;
        // ListNode fast = head;

        // while(slow != null && fast != null && fast.next != null) {
        //     slow = slow.next;
        //     fast = fast.next.next;

        //     if (slow == fast) {
        //         return true;
        //     }
        // }

        // return false;
        // Approach 2:
        HashSet<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }
}
