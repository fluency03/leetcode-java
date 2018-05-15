/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

public class CopyListWithRandomPointer138 {
    public RandomListNode copyRandomList(RandomListNode head) {
        return deepCopy(head, new HashMap<RandomListNode, RandomListNode>());
    }

    public RandomListNode deepCopy(RandomListNode node, Map<RandomListNode, RandomListNode> map) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);

        RandomListNode copy = new RandomListNode(node.label);
        map.put(node, copy);
        copy.next = deepCopy(node.next, map);
        copy.random = deepCopy(node.random, map);

        return copy;
    }


    /**
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43488/Java-O(n)-solution
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
      if (head == null) return null;

      Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

      // loop 1. copy all the nodes
      RandomListNode node = head;
      while (node != null) {
        map.put(node, new RandomListNode(node.label));
        node = node.next;
      }

      // loop 2. assign next and random pointers
      node = head;
      while (node != null) {
        map.get(node).next = map.get(node.next);
        map.get(node).random = map.get(node.random);
        node = node.next;
      }

      return map.get(head);
    }


    /**
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
     */
    public RandomListNode copyRandomList3(RandomListNode head) {
    	RandomListNode iter = head, next;

    	// First round: make copy of each node,
    	// and link them together side-by-side in a single list.
    	while (iter != null) {
    		next = iter.next;

    		RandomListNode copy = new RandomListNode(iter.label);
    		iter.next = copy;
    		copy.next = next;

    		iter = next;
    	}

    	// Second round: assign random pointers for the copy nodes.
    	iter = head;
    	while (iter != null) {
    		if (iter.random != null) {
    			iter.next.random = iter.random.next;
    		}
    		iter = iter.next.next;
    	}

    	// Third round: restore the original list, and extract the copy list.
    	iter = head;
    	RandomListNode pseudoHead = new RandomListNode(0);
    	RandomListNode copy, copyIter = pseudoHead;

    	while (iter != null) {
    		next = iter.next.next;

    		// extract the copy
    		copy = iter.next;
    		copyIter.next = copy;
    		copyIter = copy;

    		// restore the original list
    		iter.next = next;

    		iter = next;
    	}

    	return pseudoHead.next;
    }



}
