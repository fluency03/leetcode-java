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


    /**
     * https://discuss.leetcode.com/topic/2861/share-my-java-solution-with-explanation
     */
    public ListNode rotateRight2(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int i;
        for (i=0;fast.next!=null;i++)//Get the total length
        	fast=fast.next;

        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
        	slow=slow.next;

        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;

        return dummy.next;
    }


    /**
     * https://discuss.leetcode.com/topic/26364/clean-java-solution-with-brief-explanation
     */
    public ListNode rotateRight3(ListNode head, int k) {
        if (head == null)
            return head;

        ListNode copyHead = head;

        int len = 1;
        while (copyHead.next != null) {
            copyHead = copyHead.next;
            len++;
        }

        copyHead.next = head;

        for (int i = len - k % len; i > 1; i--)
            head = head.next;

        copyHead = head.next;
        head.next = null;

        return copyHead;

    }

}
