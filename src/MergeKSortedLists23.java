/**
 * Merge k sorted linked lists and return it as one sorted list.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class MergeKSortedLists23 {
    /**
     * https://discuss.leetcode.com/topic/26095/my-simple-java-solution-use-recursion
     */
    public static ListNode mergeKLists(ListNode[] lists){
        return partion(lists,0,lists.length-1);
    }

    public static ListNode partion(ListNode[] lists,int s,int e){
        if(s==e)  return lists[s];
        if(s<e){
            int q=(s+e)/2;
            ListNode l1=partion(lists,s,q);
            ListNode l2=partion(lists,q+1,e);
            return merge(l1,l2);
        }else
            return null;
    }

    //This function is from Merge Two Sorted Lists.
    public static ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/2780/a-java-solution-based-on-priority-queue
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }


    /**
     * https://discuss.leetcode.com/topic/26095/my-simple-java-solution-use-recursion
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        return mL(lists, 0, lists.length - 1);
    }

    private ListNode mL(ListNode[] lists, int l, int r) {
        if (r < l) return null;
        if (r == l) return lists[r];

        int mid = (l + r) / 2;
        ListNode a = mL(lists, l, mid), b = mL(lists, mid + 1, r);
        ListNode dmHead = new ListNode(0), cur = dmHead;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        cur.next = (a != null) ? a : b;

        return dmHead.next;
    }


    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>((l1, l2) -> Integer.compare(l1.val, l2.val));
        for (ListNode node: lists) {
            if (node != null) q.add(node);
        }
        while (!q.isEmpty()) {
            ListNode curr = q.poll();
            if (curr.next != null) q.add(curr.next);
            curr.next = null;
            p.next = curr;
            p = p.next;
        }

        return dummy.next;
    }


    public ListNode mergeKLists5(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int len = lists.length;
        int interval = 1;
        while (interval < len) {
            for (int i=0; i < len-interval; i+=interval*2)
                lists[i] = mergeTwoLists5(lists[i], lists[i + interval]);
            interval *= 2;
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                p.next = l2;
                l2 = l2.next;
            } else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
            p.next = null;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;
    }


    public ListNode mergeKLists6(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length-1);
    }


    public ListNode mergeKLists(ListNode[] lists, int i, int j) {
        if (i == j) return lists[i];
        else if (j - i == 1) return mergeTwoLists(lists[i], lists[j]);
        else {
            int mid = (i + j) / 2;
            return mergeTwoLists(mergeKLists(lists, i, mid), mergeKLists(lists, mid+1, j));
        }
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

}
