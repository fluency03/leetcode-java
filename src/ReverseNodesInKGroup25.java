/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes in
 * the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class ReverseNodesInKGroup25 {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (k == 0 || k == 1) {
            return head;
        }

        if (head == null) {
            return null;
        }

        ListNode now = head;
        int i = 1;
        ListNode r = new ListNode(0);
        ListNode last = r;
        ListNode tempHead = null;
        ListNode tempTail = null;
        while (now != null) {
            ListNode temp = new ListNode(now.val);
            if (i == 1) {
                tempHead = temp;
                tempHead.next = null;
                tempTail = temp;
                tempTail.next = null;
                i++;
            } else if (i == k) {
                temp.next = tempHead;
                tempHead = temp;
                last.next = tempHead;
                last = tempTail;
                tempHead = null;
                tempTail = null;
                i = 1;
            } else {
                temp.next = tempHead;
                tempHead = temp;
                i++;
            }
            now = now.next;
        }

        ListNode tailHead = null;
        while (tempHead != null) {
            ListNode temp = new ListNode(tempHead.val);
            temp.next = tailHead;
            tailHead = temp;
            last.next = tailHead;
            tempHead = tempHead.next;
        }

        return r.next;
    }


    /**
     * https://discuss.leetcode.com/topic/7126/short-but-recursive-java-code-with-comments/27
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }


    /**
     * https://discuss.leetcode.com/topic/7126/short-but-recursive-java-code-with-comments/27
     */
    public ListNode reverseKGroup3(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/#/solutions
     */
    public ListNode reverseKGroup4(ListNode head, int k) {
        ListNode begin;
        if (head==null || head.next ==null || k==1) return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i=0;
        while (head != null){
          	i++;
          	if (i%k == 0){
            		begin = reverse(begin, head.next);
            		head = begin.next;
          	} else {
          		  head = head.next;
          	}
        }
        return dummyhead.next;

    }

    public ListNode reverse(ListNode begin, ListNode end){
      	ListNode curr = begin.next;
      	ListNode next, first;
      	ListNode prev = begin;
      	first = curr;
      	while (curr!=end){
        		next = curr.next;
        		curr.next = prev;
        		prev = curr;
        		curr = next;
      	}
      	begin.next = prev;
      	first.next = curr;
      	return first;
    }


}
