/**
 * Design a HashMap without using any built-in hash table libraries.
 * 
 * To be specific, your design should include these functions:
 * 
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value
 * already exists in the HashMap, update the value.
 * 
 * get(key): Returns the value to which the specified key is mapped, or -1 if
 * this map contains no mapping for the key.
 * 
 * remove(key) : Remove the mapping for the value key if this map contains the
 * mapping for the key.
 * 
 * Example:
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);          
 * hashMap.put(2, 2);         
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1 
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found) 
 * 
 * Note:
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 */

public class DesignHashMap706 {
    class MyHashMap {
        private Integer[] cache = new Integer[1000000];
        
        /** Initialize your data structure here. */
        public MyHashMap() {
        }
        
        /** value will always be non-negative. */
        public void put(int key, int value) {
            cache[key] = value;
        }
        
        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            if (cache[key] == null) return -1;
            return cache[key];
        }
        
        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            cache[key] = null;
        }
    }
  

    class MyHashMap2 {

        private static final int K = 100000;
        private ListNode[] cache = new ListNode[100000];
        
        /** Initialize your data structure here. */
        public MyHashMap() {
        }
        
        /** value will always be non-negative. */
        public void put(int key, int value) {
            int k = key % K;
            if (cache[k] == null) {
                cache[k] = new ListNode(key, value);
            } else {
                ListNode head = cache[k];
                while (true) {
                    if (head.key == key) {
                        head.value = value;
                        return;
                    }
                    if (head.next == null) {
                        ListNode newNode = new ListNode(key, value);
                        head.next = newNode;
                        newNode.prev = head;
                        return;
                    }
                    head = head.next;
                }
            }
        }
        
        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int k = key % K;
            if (cache[k] == null) return -1;
            ListNode head = cache[k];
            while (true) {
                if (head.key == key) {
                    return head.value;
                }
                if (head.next == null) {
                    return -1;
                }
                head = head.next;
            }
        }
        
        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int k = key % K;
            if (cache[k] == null) return;
            ListNode head = cache[k];
            while (true) {
                if (head.key == key) {
                    if (head.prev == null) {
                        cache[k] = head.next;
                    } else {
                        if (head.next != null) head.next.prev = head.prev;
                        head.prev.next = head.next;
                    }
                    return;
                }
                if (head.next == null) {
                    return;
                }
                head = head.next;
            }
        }
        
        class ListNode {
            ListNode prev;
            ListNode next;
            Integer key;
            Integer value;
            ListNode (int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }


    class MyHashMap3 {

        private static final int K = 100000;
        private TreeNode[] cache = new TreeNode[100000];
        
        /** Initialize your data structure here. */
        public MyHashMap() {
        }
        
        /** value will always be non-negative. */
        public void put(int key, int value) {
            int k = key % K;
            if (cache[k] == null) {
                cache[k] = new TreeNode(key, value);
            } else {
                TreeNode head = cache[k];
                while (true) {
                    if (head.key == key) {
                        head.value = value;
                        return;
                    }
                    if (head.key > key) {
                        if (head.left == null) {
                            head.left = new TreeNode(key, value);
                            return;
                        }
                        head = head.left;
                    } else {
                        if (head.right == null) {
                            head.right = new TreeNode(key, value);
                            return;
                        }
                        head = head.right;
                    }
                    if (head == null) return;
                }
            }
        }
        
        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int k = key % K;
            if (cache[k] == null) return -1;
            TreeNode head = cache[k];
            while (true) {
                if (head.key == key) {
                    return head.value;
                }
                if (head.key > key) {
                    if (head.left == null) {
                        return -1;
                    }
                    head = head.left;
                } else {
                    if (head.right == null) {
                        return -1;
                    }
                    head = head.right;
                }
                if (head == null) return -1;
            }
        }
        
        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int k = key % K;
            cache[k] = delete(cache[k], key);
        }
    
        private TreeNode delete(TreeNode head, int key) {
            if (head == null)  return head;
    
            if (key < head.key) {
                head.left = delete(head.left, key);
            } else if (key > head.key) {
                head.right = delete(head.right, key);
            } else {
                if (head.left == null) {
                    return head.right;
                } else if (head.right == null) {
                    return head.left;
                }
    
                head.key = minValue(head.right);
                head.right = delete(head.right, head.key);
            }
    
            return head;
        }
     
        private int minValue(TreeNode head) {
            int minv = head.key;
            while (head.left != null) {
                minv = head.left.key;
                head = head.left;
            }
            return minv;
        }
        
        class TreeNode {
            TreeNode left;
            TreeNode right;
            Integer key;
            Integer value;
            TreeNode (int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

}
