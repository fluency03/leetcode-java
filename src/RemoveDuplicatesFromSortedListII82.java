/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
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


public class RemoveDuplicatesFromSortedListII82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        boolean isDuplicate = false;
        int dup = 0;
        while (p.next != null && p.next.next != null) {
            if (isDuplicate) {
                do {
                    p.next = p.next.next;
                } while (p.next != null && p.next.val == dup);
                isDuplicate = false;
            } else {
                if (p.next.val != p.next.next.val) {
                    p = p.next;
                } else {
                    isDuplicate = true;
                    dup = p.next.val;
                }
            }
        }
        return dummy.next;
    }


    /**
     * https://discuss.leetcode.com/topic/3890/my-accepted-java-code
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                pre=pre.next;
            }
            else{
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }


    /**
     * https://discuss.leetcode.com/topic/5206/my-recursive-java-solution
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) return null;

        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates3(head.next);
        } else {
            head.next = deleteDuplicates3(head.next);
        }
        return head;
    }


    /**
     * https://discuss.leetcode.com/topic/24470/java-simple-and-clean-code-with-comment
     */
    public ListNode deleteDuplicates4(ListNode head) {
    	  //use two pointers, slow - track the node before the dup nodes,
    	  // fast - to find the last node of dups.
        ListNode dummy = new ListNode(0), fast = head, slow = dummy;
        slow.next = fast;
        while(fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;    //while loop to find the last node of the dups.
            }
            if (slow.next != fast) { //duplicates detected.
                slow.next = fast.next; //remove the dups.
                fast = slow.next;     //reposition the fast pointer.
            } else { //no dup, move down both pointer.
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }


}
