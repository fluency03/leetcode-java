/**
 * 146. LRU Cache
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 *
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 *            exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 *                   When the cache reached its capacity, it should invalidate
 *                   the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 ); // capacity
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Node> map = new HashMap<>();
    private Node head = new Node(0, 0);
    private int capacity;
    
    public LRUCache(int capacity) {
        head.next = head;
        head.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node curr = map.get(key);
        if (curr == null) {
            return -1;
        }
        
        delete(curr);
        moveToEnd(curr);
        return curr.value;
    }
    
    private void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
        
    private void moveToEnd(Node node) {
        head.prev.next = node;
        node.next = head;
        node.prev = head.prev;
        head.prev = node;
    }
    
    public void put(int key, int value) {
        Node curr = map.get(key);
        if (curr == null) {
            Node newNode = new Node(key, value);
            moveToEnd(newNode);
            map.put(key, newNode);
            if (map.size() > capacity) {
                Node toRemove = head.next;
                delete(toRemove);
                map.remove(toRemove.key);
            }
            return;
        }
    
        curr.value = value;
        delete(curr);
        moveToEnd(curr);
    }
    
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        Node (int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}
