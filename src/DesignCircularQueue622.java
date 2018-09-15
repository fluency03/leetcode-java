/**
 * Design your implementation of the circular queue. The circular queue is a
 * linear data structure in which the operations are performed based on FIFO
 * (First In First Out) principle and the last position is connected back to
 * the first position to make a circle. It is also called "Ring Buffer".
 * 
 * One of the benefits of the circular queue is that we can make use of the
 * spaces in front of the queue. In a normal queue, once the queue becomes
 * full, we cannot insert the next element even if there is a space in front
 * of the queue. But using the circular queue, we can use the space to store
 * new values.
 * 
 * Your implementation should support following operations:
 * 
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 * 
 * Example:
 * 
 * MyCircularQueue circularQueue = new MycircularQueue(3); // set the size to be 3
 * circularQueue.enQueue(1);  // return true
 * circularQueue.enQueue(2);  // return true
 * circularQueue.enQueue(3);  // return true
 * circularQueue.enQueue(4);  // return false, the queue is full
 * circularQueue.Rear();  // return 3
 * circularQueue.isFull();  // return true
 * circularQueue.deQueue();  // return true
 * circularQueue.enQueue(4);  // return true
 * circularQueue.Rear();  // return 4
 * 
 * Note:
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Queue library.
 */

public class DesignCircularQueue622 {

    class MyCircularQueue {
        private Node head;
        private int capacity;
        private int size;
        
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.head = new Node();
            this.head.prev = head;
            this.head.next = head;
            this.capacity = k;
            this.size = 0;
        }
        
        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()) return false;
            Node newNode = new Node(value);
            newNode.next = this.head;
            newNode.prev = this.head.prev;
            this.head.prev.next = newNode;
            this.head.prev = newNode;
            this.size++;
            return true;
        }
        
        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) return false;
            dislink(this.head.next);
            this.size--;
            return true;
        }
        
        private void dislink(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        
        /** Get the front item from the queue. */
        public int Front() {
            if (isEmpty()) return -1;
            return this.head.next.value;
        }
        
        /** Get the last item from the queue. */
        public int Rear() {
            if (isEmpty()) return -1;
            return this.head.prev.value;
        }
        
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return this.size == this.capacity;
        }
        
        class Node {
            Node prev;
            Node next;
            int value;
            Node() {
                this.value = -1;
            }
            Node(int value) {
                this.value = value;
            }
        }
    }


    class MyCircularQueue2 {
        private int[] cache;
        private int head;
        private int capacity;
        private int size;
        
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.cache = new int[k + 1];
            this.head = 0;
            this.capacity = k;
            this.size = 0;
        }
        
        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()) return false;
            this.size++;
            this.cache[(this.head + this.size) % this.cache.length] = value;
            return true;
        }
        
        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) return false;
            this.head++;
            this.head = this.head % this.cache.length;
            this.size--;
            return true;
        }
        
        /** Get the front item from the queue. */
        public int Front() {
            if (isEmpty()) return -1;
            return this.cache[(this.head + 1) % this.cache.length];
        }
        
        /** Get the last item from the queue. */
        public int Rear() {
            if (isEmpty()) return -1;
            return this.cache[(this.head + this.size) % this.cache.length];
        }
        
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return this.size == this.capacity;
        }
    
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

}


