/**
 * Design a HashSet without using any built-in hash table libraries.
 * 
 * To be specific, your design should include these functions:
 * add(value): Insert a value into the HashSet. 
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist
 * in the HashSet, do nothing.
 * 
 * Example:
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);         
 * hashSet.add(2);         
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);          
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);          
 * hashSet.contains(2);    // returns false (already removed)
 * 
 * Note:
 * All values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashSet library.
 */

public class DesignHashSet705 {

    class MyHashSet {
        private static final int K = 10000;
        private List<Integer>[] cache = new List[K];
        
        /** Initialize your data structure here. */
        public MyHashSet() {
            
        }
        
        public void add(int key) {
            int k = key % K;
            if (cache[k] == null) {
                cache[k] = new ArrayList<>();
            }
            List<Integer> list = cache[k];
            for (int el: list) {
                if (el == key) return;
            }
            list.add(key);
        }
        
        public void remove(int key) {
            int k = key % K;
            if (cache[k] == null) return;
            List<Integer> list = cache[k];
            
            for (int i=0; i<list.size(); i++) {
                if (list.get(i) == key) {
                    list.remove(i);
                    return;
                }
            }
        }
        
        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int k = key % K;
            if (cache[k] == null) return false;
            List<Integer> list = cache[k];
            for (int el: list) {
                if (el == key) return true;
            }
            return false;
        }
    }



    class MyHashSet2 {
        private static final int K = 10000;
        private TreeNode[] cache = new TreeNode[K];
        
        /** Initialize your data structure here. */
        public MyHashSet2() {
            
        }
        
        public void add(int key) {
            int k = key % K;
            cache[k] = addNode(cache[k], key);
        }
        
        TreeNode addNode(TreeNode root, int key) {
            if (root == null) return new TreeNode(key);
            if (root.key > key) {
                root.left = addNode(root.left, key);
            } else if (root.key < key) {
                root.right = addNode(root.right, key);
            }
            return root;
        }
        
        public void remove(int key) {
            int k = key % K;
            cache[k] = removeNode(cache[k], key);
        }
        
        TreeNode removeNode(TreeNode root, int key) {
            if (root == null) return null;
            if (root.key > key) {
                root.left = removeNode(root.left, key);
            } else if (root.key < key) {
                root.right = removeNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    root.key = findMin(root.right);
                    root.right = removeNode(root.right, root.key);
                }
            }
            return root;
        }
        
        int findMin(TreeNode root) {
            while (root.left != null) {
                root = root.left;
            }
            return root.key;
        }
        
        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return containNode(cache[key % K], key);
        }
        
        boolean containNode(TreeNode root, int key) {
            if (root == null) return false;
            if (root.key == key) return true;
            return containNode(root.left, key) || containNode(root.right, key);
        }
        
        class TreeNode {
            TreeNode left;
            TreeNode right;
            int key;
            TreeNode(int key) {
                this.key = key;
            }
        }
        
    }


/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

}
