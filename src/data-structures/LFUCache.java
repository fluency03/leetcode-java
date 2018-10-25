/**
 * 
 */

public class LFUCache {
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

    class ItemNode extends Node {
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

    class FreqNode extends Node {
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

    abstract class Node {
        Node next;
        Node prev;
    }
}
