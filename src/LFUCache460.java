/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least
 * frequently used item before inserting a new item. For the purpose of this
 * problem, when there is a tie (i.e., two or more keys that have the same
 * frequency), the least recently used key would be evicted.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * LFUCache cache = new LFUCache( 2 ); // capacity
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

public class LFUCache460 {
    class LFUCache {
        // freq -> Linked List
        private TreeMap<Integer, Node> treeMap = new TreeMap<>();
        // key -> Node
        private Map<Integer, Node> map = new HashMap<>();
        private int capacity;
        
        public LFUCache(int capacity) {
            this.capacity = capacity;
        }
        
        public int get(int key) {
            if (!this.map.containsKey(key)) return -1;
            Node node = map.get(key);
            deleteFromTreeMap(node);
            node.freq++;
            addOnTreeMap(node);
            return node.val;
        }
        
        private void deleteFromList(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void addToListEnd(Node head, Node node) {
            node.prev = head.prev;
            node.next = head;
            head.prev.next = node;
            head.prev = node;
        }
        
        private void addOnTreeMap(Node node) {
            Node head = treeMap.get(node.freq);
            if (head == null) {
                head = newHead();
            }
            addToListEnd(head, node);
            this.treeMap.put(node.freq, head);
        }
        
        private void deleteFromTreeMap(Node node) {
            deleteFromList(node);
            Node head = treeMap.get(node.freq);
            if (head.next.freq == -1) {
                this.treeMap.remove(node.freq);
            }
        }
        
        public void put(int key, int value) {
            if (this.capacity == 0) return; 
            Node node = this.map.get(key);
            if (node == null) {
                node = new Node(key, value, 1);
                if (this.map.size() == this.capacity) {
                    Map.Entry<Integer, Node> first = this.treeMap.firstEntry();
                    Integer firstKey = first.getKey();
                    Node firstHead = first.getValue();
                    Node toBeRemoved = firstHead.next;
                    deleteFromTreeMap(toBeRemoved);
                    this.map.remove(toBeRemoved.key);
                }
                this.map.put(key, node);
            } else {
                node.val = value;
                deleteFromTreeMap(node);
                node.freq++;
            }
            addOnTreeMap(node);
        }
        
        private Node newHead() {
            Node head = new Node(0, 0, -1);
            head.prev = head;
            head.next = head;
            return head;
        }
        
        class Node {
            Node next;
            Node prev;
            int key;
            int val;
            int freq;
            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }
    }


    class LFUCache2 {
        private FreqNode head;
        private Map<Integer, FreqNode> freqMap = new HashMap<>();
        private Map<Integer, ItemNode> itemMap = new HashMap<>();
        private int capacity;
    
        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.head = new FreqNode();
            this.head.next = this.head;
            this.head.prev = this.head;
        }
        
        public int get(int key) {
            if (!this.itemMap.containsKey(key)) return -1;
            ItemNode item = this.itemMap.get(key);
            updateNodeFreq(item);
            return item.val;
        }
        
        private void updateNodeFreq(ItemNode item) {
            dislinkNode(item);
            Integer freq = item.freq;
            item.freq++;
            FreqNode currFreqNode = freqMap.get(freq);
            FreqNode newFreqNode = freqMap.get(freq+1);
            if (newFreqNode == null) {
                newFreqNode = new FreqNode(freq+1);
                freqMap.put(freq+1, newFreqNode);
                addAfter(currFreqNode, newFreqNode);
            }
            addBefore(newFreqNode.itemHead, item);
    
            if (itemNodeIsEmpty(currFreqNode.itemHead)) {
                removeNode(currFreqNode);
            }
        }
        
        private boolean itemNodeIsEmpty(ItemNode node) {
            return node.next == node;
        }
        
        private void removeNode(FreqNode node) {
            dislinkNode(node);
            this.freqMap.remove(node.freq);
        }
        
        private void removeNode(ItemNode node) {
            dislinkNode(node);
            this.itemMap.remove(node.key);
        }
        
        private void dislinkNode(FreqNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void dislinkNode(ItemNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void addAfter(FreqNode currNode, FreqNode newNode) {
            newNode.next = currNode.next;
            newNode.prev = currNode;
            currNode.next.prev = newNode;
            currNode.next = newNode;
        }
        
        private void addAfter(ItemNode currNode, ItemNode newNode) {
            newNode.next = currNode.next;
            newNode.prev = currNode;
            currNode.next.prev = newNode;
            currNode.next = newNode;
        }
        
        private void addBefore(ItemNode currNode, ItemNode newNode) {
            newNode.next = currNode;
            newNode.prev = currNode.prev;
            currNode.prev.next = newNode;
            currNode.prev = newNode;
        }
        
        public void put(int key, int value) {
            if (this.capacity == 0) return;
            if (this.itemMap.containsKey(key)) {
                ItemNode item = this.itemMap.get(key);
                item.val = value;
                updateNodeFreq(item);
                return;
            }
    
            if (this.itemMap.size() == this.capacity) {
                FreqNode first = this.head.next;
                ItemNode toBeRemoved = first.itemHead.next;
                removeNode(toBeRemoved);
                if (itemNodeIsEmpty(first.itemHead)) {
                    removeNode(first);
                }
            }
            
            FreqNode newFreqNode = freqMap.get(1);
            if (newFreqNode == null) {
                newFreqNode = new FreqNode(1);
                freqMap.put(1, newFreqNode);
                addAfter(this.head, newFreqNode);
            }
            ItemNode newItem = new ItemNode(key, value, 1);
            addBefore(newFreqNode.itemHead, newItem);
            this.itemMap.put(key, newItem);
        }
        
        private ItemNode newItemHead() {
            ItemNode head = new ItemNode();
            head.prev = head;
            head.next = head;
            return head;
        }
    
        class ItemNode {
            ItemNode next;
            ItemNode prev;
            int key;
            int val;
            int freq;
            ItemNode(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
            ItemNode() {
                this.key = -1;
                this.val = -1;
                this.freq = -1;
            }
        }
        
        class FreqNode {
            FreqNode next;
            FreqNode prev;
            int freq;
            ItemNode itemHead;
            FreqNode(int freq) {
                this.freq = freq;
                this.itemHead = newItemHead();
            }
            FreqNode() {
                this.freq = -1;
            }
        }
    }


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}
