/**
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */




public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int n1 = l1.val;
        int n2 = l2.val;
        int sum = n1 + n2;
        l1 = (l1 == null) ? null : l1.next;
        l2 = (l2 == null) ? null : l2.next;

        ListNode result = new ListNode(sum % 10);
        ListNode pointer = result;
        sum /= 10;

        while (l1 != null || (l2 != null)) {
            n1 = (l1 == null) ? 0 : l1.val;
            n2 = (l2 == null) ? 0 : l2.val;

            sum += n1 + n2;
            pointer.next = new ListNode(sum % 10);
            pointer = pointer.next;
            sum /= 10;

            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }

        if (sum == 1) {
            pointer.next = new ListNode(1);
        }

        return result;
    }

}
