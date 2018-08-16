/**
 * Design your implementation of the linked list. You can choose to use the
 * singly linked list or the doubly linked list. A node in a singly linked list
 * should have two attributes: val and next. val is the value of the current
 * node, and next is a pointer/reference to the next node. If you want to use
 * the doubly linked list, you will need one more attribute prev to indicate
 * the previous node in the linked list. Assume all nodes in the linked list
 * are 0-indexed.
 * 
 * Implement these functions in your linked list class:
 * 
 * get(index) : Get the value of the index-th node in the linked list. If the
 * index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the
 * linked list. After the insertion, the new node will be the first node of
 * the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the
 * linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node
 * in the linked list. If index equals to the length of linked list, the node
 * will be appended to the end of linked list. If index is greater than the
 * length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the
 * index is valid.
 * 
 * Example:
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 * 
 * Note:
 * All values will be in the range of [1, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in LinkedList library.
 */

public class DesignLinkedList707 {
    class MyLinkedList {
        private Node head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
            head = new Node(0);
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            int i = 0;
            Node p = head.next;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null) return -1;
            return p.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node newNode = new Node(val);
            newNode.next = head.next;
            head.next = newNode;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            Node newNode = new Node(val);
            newNode.next = p.next;
            p.next = newNode;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            int i = 0;
            Node p = head;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null) return;
            Node newNode = new Node(val);
            newNode.next = p.next;
            p.next = newNode;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            int i = 0;
            Node p = head;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null || p.next == null) return;
            p.next = p.next.next;
        }

        class Node {
            Node next;
            int val;
            Node (int x) {
                this.val = x;
            }
        }

    }


    class MyLinkedList2 {
        private Node head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
            head = new Node(0);
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            int i = 0;
            Node p = head.next;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null) return -1;
            return p.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node newNode = new Node(val);
            newNode.next = head.next;
            if (head.next != null) head.next.prev = newNode;
            head.next = newNode;
            newNode.prev = head;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            Node newNode = new Node(val);
            newNode.next = p.next;
            if (p.next != null) p.next.prev = newNode;
            p.next = newNode;
            newNode.prev = p;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            int i = 0;
            Node p = head;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null) return;
            Node newNode = new Node(val);
            newNode.next = p.next;
            if (p.next != null) p.next.prev = newNode;
            p.next = newNode;
            newNode.prev = p;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            int i = 0;
            Node p = head;
            while (i < index && p != null) {
                p = p.next;
                i++;
            }
            if (p == null || p.next == null) return;
            p.next = p.next.next;
            if (p.next != null) p.next.prev = p;
        }

        class Node {
            Node prev;
            Node next;
            int val;
            Node (int x) {
                this.val = x;
            }
        }
    }


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

}
