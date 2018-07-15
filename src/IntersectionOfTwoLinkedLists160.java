/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class IntersectionOfTwoLinkedLists160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lA = 0;
        ListNode tA = headA;
        while (tA != null) {
            lA++;
            tA = tA.next;
        }

        int lB = 0;
        ListNode tB = headB;
        while (tB != null) {
            lB++;
            tB = tB.next;
        }

        if (lA < lB) {
            for (int i=1; i<=(lB-lA); i++) headB = headB.next;
        } else if (lB < lA) {
            for (int i=1; i<=(lA-lB); i++) headA = headA.next;
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != null && b != null) {
            if (a == b) return a;
            a = a.next;
            b = b.next;
        }
        if (a == null && b == null) return null;
        if (a == null) {
            a = headB;
        } else {
            b = headA;
        }
        while (a != null && b != null) {
            a = a.next;
            b = b.next;
        }
        if (a == null) {
            a = headB;
        } else {
            b = headA;
        }
        while (a != null && b != null) {
            if (a == b) return a;
            a = a.next;
            b = b.next;
        }
        
        return null;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
     *
     * You can prove that: say A length = a + c, B length = b + c, after
     * switching pointer, pointer A will move another b + c steps, pointer B
     * will move a + c more steps, since a + c + b + c = b + c + a + c, it
     * does not matter what value c is. Pointer A and B must meet after
     * a + c + b (b + c + a) steps. If c == 0, they meet at NULL.
     *
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
        	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }


    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            pa = pa.next;
            pb = pb.next;
            if (pa == pb) return pa;
            if (pa == null) pa = headB;
            if (pb == null) pb = headA; 
        }
        return pa;
    }

}
