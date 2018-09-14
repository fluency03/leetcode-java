/**
 * Design your implementation of the circular double-ended queue (deque).
 * 
 * Your implementation should support following operations:
 * 
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not. 
 * isFull(): Checks whether Deque is full or not.
 * 
 * Example:
 * 
 * MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
 * circularDeque.insertLast(1);			// return true
 * circularDeque.insertLast(2);			// return true
 * circularDeque.insertFront(3);			// return true
 * circularDeque.insertFront(4);			// return false, the queue is full
 * circularDeque.getRear();  			// return 2
 * circularDeque.isFull();				// return true
 * circularDeque.deleteLast();			// return true
 * circularDeque.insertFront(4);			// return true
 * circularDeque.getFront();			// return 4 
 * 
 * Note:
 * 
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Deque library.
 */

public class DesignCircularDeque641 {

    class MyCircularDeque {
        private Node head;
        private int capacity;
        private int size;
        
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.head = new Node(-1);
            this.head.next = this.head;
            this.head.prev = this.head;
            this.capacity = k;
            this.size = 0;
        }
        
        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (this.size >= this.capacity) return false;
            Node newNode = new Node(value);
            newNode.next = this.head.next;
            newNode.prev = this.head;
            this.head.next.prev = newNode;
            this.head.next = newNode;
            this.size++;
            return true;
        }
        
        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (this.size >= this.capacity) return false;
            Node newNode = new Node(value);
            newNode.next = this.head;
            newNode.prev = this.head.prev;
            this.head.prev.next = newNode;
            this.head.prev = newNode;
            this.size++;
            return true;
        }
        
        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (isEmpty()) return false;
            Node toBeRemoved = this.head.next;
            dislink(toBeRemoved);
            this.size--;
            return true;
        }
        
        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (isEmpty()) return false;
            Node toBeRemoved = this.head.prev;
            dislink(toBeRemoved);
            this.size--;
            return true;
        }
        
        private void dislink(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        
        /** Get the front item from the deque. */
        public int getFront() {
            if (isEmpty()) return -1;
            return this.head.next.value;
        }
        
        /** Get the last item from the deque. */
        public int getRear() {
            if (isEmpty()) return -1;
            return this.head.prev.value;
        }
        
        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return this.size >= this.capacity;
        }
        
        class Node {
            Node prev;
            Node next;
            int value;
            Node(int value) {
                this.value = value;
            }
        }
    }


    class MyCircularDeque2 {
        private int[] cache;
        private int capacity;
        private int size;
        private int head;
        
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.cache = new int[k+1];
            this.capacity = k;
            this.size = 0;
            this.head = 0;
        }
    
        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (this.size >= this.capacity) return false;
            this.cache[this.head--] = value;
            if (this.head < 0) this.head += this.cache.length;
            this.size++;
            return true;
        }
    
        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (this.size >= this.capacity) return false;
            this.cache[(this.head + this.size + 1) % this.cache.length] = value;
            this.size++;
            return true;
        }
        
        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (isEmpty()) return false;
            this.head++;
            this.head %= this.cache.length;
            this.size--;
            return true;
        }
        
        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (isEmpty()) return false;
            this.size--;
            return true;
        }
    
        /** Get the front item from the deque. */
        public int getFront() {
            if (isEmpty()) return -1;
            return this.cache[(this.head + 1) % this.cache.length];
        }
        
        /** Get the last item from the deque. */
        public int getRear() {
            if (isEmpty()) return -1;
            return this.cache[(this.head + this.size) % this.cache.length];
        }
        
        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return this.size >= this.capacity;
        }
    
    }


/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

}


