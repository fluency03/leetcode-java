/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */


import java.util.ArrayList;
import java.util.List;


/*
  The following classes are defined in seperate files.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


public class ConvertSortedListToBST109 {
    public TreeNode sortedListToBST(ListNode head) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        storeBSTNodes(head, nodes);

        int n = nodes.size();
        return buildBST(nodes, 0, n-1);
    }

    private void storeBSTNodes(ListNode head, List<TreeNode> nodes) {
        while (head != null) {
            nodes.add(new TreeNode(head.val));
            head = head.next;
        }
    }

    private TreeNode buildBST(List<TreeNode> nodes, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode node = nodes.get(mid);

        node.left = buildBST(nodes, start, mid - 1);
        node.right = buildBST(nodes, mid + 1, end);

        return node;
    }

    /** -------------------------------------------------------------------
     * Top Solution: O(n logn)
     * https://discuss.leetcode.com/topic/35997/share-my-java-solution-1ms-very-short-and-concise
     * --------------------------------------------------------------------
     */

    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head == tail) return null;

        while(fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head, slow);
        thead.right = toBST(slow.next, tail);
        return thead;
    }


    /** -------------------------------------------------------------------
     * Top Solution: O(n)
     * https://discuss.leetcode.com/topic/35997/share-my-java-solution-1ms-very-short-and-concise
     * --------------------------------------------------------------------
     */

    private ListNode node;

    public TreeNode sortedListToBST3(ListNode head) {
      	if(head == null){
      		  return null;
      	}

      	int size = 0;
      	ListNode runner = head;
      	node = head;

      	while(runner != null){
      		  runner = runner.next;
      		      size ++;
      	}

      	return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end){
    	  if(start > end){
    		    return null;
    	  }

        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(start, mid - 1);

        TreeNode treenode = new TreeNode(node.val);
        treenode.left = left;
        node = node.next;

        TreeNode right = inorderHelper(mid + 1, end);
        treenode.right = right;

        return treenode;
    }


    public TreeNode sortedListToBST4(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode fast = head;
        ListNode slow = head;
        ListNode beforeSlow = null;
        while (slow != null && fast != null && fast.next != null) {
            fast = fast.next.next;
            beforeSlow = slow;
            slow = slow.next;
        }

        beforeSlow.next = null;
        TreeNode res = new TreeNode(slow.val);
        res.left = sortedListToBST(head);
        res.right = sortedListToBST(slow.next);

        return res;
    }

    public TreeNode sortedListToBST5(ListNode head) {
        if (head == null) return null;
        
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null) {
            return new TreeNode(head.val);
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }


}
