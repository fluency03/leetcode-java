/**
 * Given a singly linked list L: L0?L1?…?Ln-1?Ln,
 * reorder it to: L0?Ln?L1?Ln-1?L2?Ln-2?…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 *      Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class ReorderList143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode fast = head;
        ListNode slow = head;

        Stack<ListNode> st = new Stack<>();

        while (fast.next != null && fast.next.next != null) {
            st.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode half = new ListNode(0);
        if (fast.next == null) {
            half = slow.next;
            dummy.next = slow;
            dummy.next.next = null;
        } else if (fast.next.next == null) {
            half = slow.next.next;
            dummy.next = slow;
            dummy.next.next.next = null;
        }

        while (half != null) {
            ListNode newNode = half;
            half = half.next;
            newNode.next = dummy.next;
            dummy.next = newNode;
            newNode = st.pop();
            newNode.next = dummy.next;
            dummy.next = newNode;
        }

        head = dummy.next;
    }


    /**
     * https://discuss.leetcode.com/topic/13869/java-solution-with-3-steps
     */
    public void reorderList2(ListNode head) {
        if(head==null||head.next==null) return;

        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){
            p1=p1.next;
            p2=p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/18092/java-solution-with-3-steps
     */
    public void reorderList3(ListNode head) {
       if (head == null || head.next == null)
           return;

       // step 1. cut the list to two halves
       // prev will be the tail of 1st half
       // slow will be the head of 2nd half
       ListNode prev = null, slow = head, fast = head, l1 = head;

       while (fast != null && fast.next != null) {
         prev = slow;
         slow = slow.next;
         fast = fast.next.next;
       }

       prev.next = null;

       // step 2. reverse the 2nd half
       ListNode l2 = reverse(slow);

       // step 3. merge the two halves
       merge(l1, l2);
     }

     ListNode reverse(ListNode head) {
       ListNode prev = null, curr = head, next = null;

       while (curr != null) {
         next = curr.next;
         curr.next = prev;
         prev = curr;
         curr = next;
       }

       return prev;
     }

     void merge(ListNode l1, ListNode l2) {
       while (l1 != null) {
         ListNode n1 = l1.next, n2 = l2.next;
         l1.next = l2;

         if (n1 == null)
           break;

         l2.next = n1;
         l1 = n1;
         l2 = n2;
       }
     }

}
