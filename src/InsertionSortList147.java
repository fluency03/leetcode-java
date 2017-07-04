/**
 * Sort a linked list using insertion sort.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class InsertionSortList147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode end = new ListNode(head.val);
        dummy.next = end;

        ListNode p = head.next;
        while (p != null) {
            ListNode n = new ListNode(p.val);
            if (n.val >= end.val) {
                end.next = n;
                end = n;
            } else {
                insert(dummy, n);
            }
            p = p.next;
        }

        return dummy.next;
    }

    private void insert(ListNode dummy, ListNode newNode) {
        ListNode pre = dummy;
        ListNode now = dummy.next;
        while (now != null) {
            if (now.val >= newNode.val) {
                pre.next = newNode;
                newNode.next = now;
                return;
            }
            now = now.next;
            pre = pre.next;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/8570/an-easy-and-clear-way-to-sort-o-1-space
     */
    public ListNode insertionSortList2(ListNode head) {
  		if( head == null ){
  			return head;
  		}

  		ListNode helper = new ListNode(0); //new starter of the sorted list
  		ListNode cur = head; //the node will be inserted
  		ListNode pre = helper; //insert node between pre and pre.next
  		ListNode next = null; //the next node will be inserted
  		//not the end of input list
  		while( cur != null ){
  			next = cur.next;
  			//find the right place to insert
  			while( pre.next != null && pre.next.val < cur.val ){
  				pre = pre.next;
  			}
  			//insert between pre and pre.next
  			cur.next = pre.next;
  			pre.next = cur;
  			pre = helper;
  			cur = next;
  		}

  		return helper.next;
  	}

}
