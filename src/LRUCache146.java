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


public class LRUCache {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }
    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if(null==n){
            return -1;
        }
        update(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if(null==n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
        }
        else{
            n.value = value;
            update(n);
        }
        if(map.size()>capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
        }
    }

    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void remove(Node node){
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}




/**
 * Easy LinkedHashMap Implementation
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Integer> map;
    private Integer capacity;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Integer i = map.get(key);
            map.remove(key);
            map.put(key, i);
            return i;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (value < 0) return;

        if (map.containsKey(key)) {
            map.remove(key);
        }

        map.put(key, value);
    }
}


// class LRUCache {
//
//     private Map<Integer, Node> map = new HashMap<>();
//     private Node head = new Node(-1, -1);
//     private Node tail = new Node(-1, -1);
//     private int capacity;
//
//     public LRUCache(int capacity) {
//         this.capacity = capacity;
//         head.next = tail;
//         tail.pre = head;
//     }
//
//     public int get(int key) {
//         Node n = map.get(key);
//         if (n == null) return -1;
//         detach(n);
//         toEnd(n);
//         return n.val;
//     }
//
//     public void put(int key, int value) {
//         Node n = map.get(key);
//         if (n == null) {
//             n = new Node(key, value);
//             if (map.size() >= capacity) {
//                 Node first = head.next;
//                 if (first.val != -1) {
//                     detach(first);
//                     map.remove(first.key);
//                 }
//             }
//             toEnd(n);
//             map.put(key, n);
//         } else {
//             n.key = key;
//             n.val = value;
//             detach(n);
//             toEnd(n);
//         }
//     }
//
//     private void detach(Node n) {
//         n.pre.next = n.next;
//         n.next.pre = n.pre;
//     }
//
//     private void toEnd(Node n) {
//         n.pre = tail.pre;
//         tail.pre.next = n;
//         tail.pre = n;
//         n.next = tail;
//     }
//
//     class Node {
//         int val;
//         int key;
//         Node pre;
//         Node next;
//         public Node(int key, int val) {
//             this.key = key;
//             this.val = val;
//         }
//     }
//
// }
