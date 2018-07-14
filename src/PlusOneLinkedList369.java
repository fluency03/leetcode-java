/**
 * Given a non-negative integer represented as non-empty a singly linked list
 * of digits, plus one to the integer.
 * 
 * You may assume the integer do not contain any leading zero, except the
 * number 0 itself.
 * 
 * The digits are stored such that the most significant digit is at the head
 * of the list.
 * 
 * Example:
 * Input:
 * 1->2->3
 * 
 * Output:
 * 1->2->4
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class PlusOneLinkedList369 {
    public ListNode plusOne(ListNode head) {
        if (head == null) return null;
        int carry = helper(head);
        if (carry == 0) return head;
        ListNode h = new ListNode(carry);
        h.next = head;
        return h;
    }

    public int helper(ListNode head) {
        if (head == null) return 1;
        int carry = helper(head.next);
        int sum = carry + head.val;
        head.val = sum % 10;
        return sum / 10;
    }


    /**
     * https://leetcode.com/problems/plus-one-linked-list/discuss/84150/Two-Pointers-Java-Solution:-O(n)-time-O(1)-space
     */
    public ListNode plusOne2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }
        // i = index of last non-9 digit
    
        i.val++;
        i = i.next;
        while (i != null) {
            i.val = 0;
            i = i.next;
        }
        
        if (dummy.val == 0) return dummy.next;
        return dummy;
    }

}
