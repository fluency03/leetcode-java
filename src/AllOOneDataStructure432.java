/**
 * Implement a data structure supporting the following operations:
 * 
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key
 * by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise
 * decrements an existing key by 1. If the key does not exist, this function
 * does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element
 * exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element
 * exists, return an empty string "".
 * 
 * Challenge: Perform all these in O(1) time complexity.
 */


public class AllOOneDataStructure432 {

    class AllOne {
        private Map<String, KeyNode> keyMap;
        private Map<Integer, FreqNode> freqMap;
        private FreqNode head;

        /** Initialize your data structure here. */
        public AllOne() {
            this.keyMap = new HashMap<>();
            this.freqMap = new HashMap<>();
            this.head = new FreqNode();
            this.head.next = this.head;
            this.head.prev = this.head;
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            KeyNode node = keyMap.get(key);
            if (node == null) {
                node = new KeyNode(key);
                FreqNode freqNode = freqMap.get(node.freq);
                if (freqNode == null) {
                    freqNode = new FreqNode(node.freq);
                    this.freqMap.put(freqNode.freq, freqNode);
                    addAfter(this.head, freqNode);
                }
                addAfter(freqNode.keyHead, node);
                this.keyMap.put(node.key, node);
            } else {
                dislink(node);
                FreqNode preFreqNode = freqMap.get(node.freq);
                node.freq++;
                FreqNode nextFreqNode = freqMap.get(node.freq);
                if (nextFreqNode == null) {
                    nextFreqNode = new FreqNode(node.freq);
                    this.freqMap.put(nextFreqNode.freq, nextFreqNode);
                    addAfter(preFreqNode, nextFreqNode);
                }
                addAfter(nextFreqNode.keyHead, node);
                if (isEmpty(preFreqNode.keyHead)) {
                    dislink(preFreqNode);
                    this.freqMap.remove(preFreqNode.freq);
                }
            }
        }

        private void dislink(KeyNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        private void dislink(FreqNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        private void addAfter(KeyNode node, KeyNode newNode) {
            newNode.next = node.next;
            newNode.prev = node;
            node.next.prev = newNode;
            node.next = newNode;
        }

        private void addAfter(FreqNode node, FreqNode newNode) {
            newNode.next = node.next;
            newNode.prev = node;
            node.next.prev = newNode;
            node.next = newNode;
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            KeyNode node = keyMap.get(key);
            if (node == null) return;

            dislink(node);
            FreqNode preFreqNode = freqMap.get(node.freq);
            node.freq--;
            if (node.freq > 0) {
                FreqNode nextFreqNode = freqMap.get(node.freq);
                if (nextFreqNode == null) {
                    nextFreqNode = new FreqNode(node.freq);
                    this.freqMap.put(nextFreqNode.freq, nextFreqNode);
                    addBefore(preFreqNode, nextFreqNode);
                }
                addAfter(nextFreqNode.keyHead, node);
            } else {
                this.keyMap.remove(node.key);
            }
            if (isEmpty(preFreqNode.keyHead)) {
                dislink(preFreqNode);
                this.freqMap.remove(preFreqNode.freq);
            }
        }

        private void addBefore(FreqNode node, FreqNode newNode) {
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (isEmpty()) return "";
            return this.head.prev.keyHead.next.key;
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (isEmpty()) return "";
            return this.head.next.keyHead.next.key;
        }

        private boolean isEmpty() {
            return isEmpty(this.head);
        }

        private boolean isEmpty(KeyNode node) {
            return node.next == node;
        }

        private boolean isEmpty(FreqNode node) {
            return node.next == node;
        }

        class KeyNode {
            KeyNode prev;
            KeyNode next;
            String key;
            int freq;
            KeyNode() {
            }
            KeyNode(String key) {
                this.key = key;
                this.freq = 1;
            }
            KeyNode(String key, int freq) {
                this.key = key;
                this.freq = freq;
            }
        }

        class FreqNode {
            FreqNode prev;
            FreqNode next;
            int freq;
            KeyNode keyHead;
            FreqNode () {
            }
            FreqNode (int freq) {
                this.freq = freq;
                this.keyHead = new KeyNode();
                this.keyHead.next = this.keyHead;
                this.keyHead.prev = this.keyHead;
            }
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

}

