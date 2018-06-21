/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class SerializeAndDeserializeBST449 {
    // Level-Order, BFS, Iteratively
    class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode curr = q.poll();
                if (curr == null) {
                    sb.append("#,");
                } else {
                    sb.append(curr.val + ",");
                    q.add(curr.left);
                    q.add(curr.right);
                }
            }
            return sb.substring(0, sb.length()-1).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strs = data.split(",");
            TreeNode root = strs[0].equals("#") ? null : new TreeNode(Integer.valueOf(strs[0]));
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int i = 1;
            while (i<=strs.length-2) {
                TreeNode p = q.poll();
                if (p != null) {
                    TreeNode left = strs[i].equals("#") ? null : new TreeNode(Integer.valueOf(strs[i]));
                    TreeNode right = strs[i+1].equals("#") ? null : new TreeNode(Integer.valueOf(strs[i+1]));
                    p.left = left;
                    p.right = right;
                    q.add(left);
                    q.add(right);
                    i += 2;
                }
            }
            return root;
        }
    }

    // Pre-Order, DFS, Resursively
    class Codec2 {
        // Encodes a tree to a single string.
        public String serialize2(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.substring(0, sb.length()-1).toString();
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) return;
            sb.append(root.val + ",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize2(String data) {
            String[] strs = data.split(",");
            if (strs.length == 0 || strs[0].equals("")) return null;
            return deserialize(strs, 0, strs.length-1);
        }
        
        private TreeNode deserialize(String[] strs, int lo, int hi) {
            if (lo > hi) return null;
            if (lo == hi) return new TreeNode(Integer.valueOf(strs[lo]));
            int rootVal = Integer.valueOf(strs[lo]);
            TreeNode root = new TreeNode(Integer.valueOf(strs[lo]));
            int rightIdx = rightStart(strs, lo+1, hi, rootVal);
            root.left = deserialize(strs, lo+1, rightIdx-1);
            root.right = deserialize(strs, rightIdx, hi);
            return root;
        }

        private int rightStart(String[] strs, int lo, int hi, int rootVal) {
            for (int i=lo; i<=hi; i++) {
                if (Integer.valueOf(strs[i]) > rootVal) return i;
            }
            return hi+1;
        }
    }

    // https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93175/Java-PreOrder-+-Queue-solution
    // Pre-Order, DFS, Iteratively
    class Codec3 {
        private static final String SEP = ",";
        private static final String NULL = "null";
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) return NULL;
            //traverse it recursively if you want to, I am doing it iteratively here
            Stack<TreeNode> st = new Stack<>();
            st.push(root);
            while (!st.empty()) {
                root = st.pop();
                sb.append(root.val).append(SEP);
                if (root.right != null) st.push(root.right);
                if (root.left != null) st.push(root.left);
            }
            return sb.toString();
        }
    
        // Decodes your encoded data to tree.
        // pre-order traversal
        public TreeNode deserialize(String data) {
            if (data.equals(NULL)) return null;
            String[] strs = data.split(SEP);
            Queue<Integer> q = new LinkedList<>();
            for (String e : strs) {
                q.offer(Integer.parseInt(e));
            }
            return getNode(q);
        }
        
        // some notes:
        //   5
        //  3 6
        // 2   7
        private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
            if (q.isEmpty()) return null;
            TreeNode root = new TreeNode(q.poll());//root (5)
            Queue<Integer> samllerQueue = new LinkedList<>();
            while (!q.isEmpty() && q.peek() < root.val) {
                samllerQueue.offer(q.poll());
            }
            //smallerQueue : 3,2   storing elements smaller than 5 (root)
            root.left = getNode(samllerQueue);
            //q: 6,7   storing elements bigger than 5 (root)
            root.right = getNode(q);
            return root;
        }
        
    }

    // https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93170/pre-or-post-order-with-only-keeping-one-bound(beat-98-and-95)
    // Pre-Order, DFS, Resursively
    class Codec4 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }
        
        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val).append(" ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] nodes = data.split(" ");
            int[] index = new int[] {0};
            return deserialize(nodes, index, Integer.MAX_VALUE);
        }
        
        private TreeNode deserialize(String[] nodes, int[] index, int max) {
            if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]++]));
            root.left = deserialize(nodes, index, root.val);
            root.right = deserialize(nodes, index, max);
            return root;
        }
    }

    // https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93170/pre-or-post-order-with-only-keeping-one-bound(beat-98-and-95)
    // Post-Order, DFS, Resursively
    class Codec5 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }
        
        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            serialize(root.left, sb);
            serialize(root.right, sb);
            sb.append(Integer.valueOf(root.val)).append(" ");
        }
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] nodes = data.split(" ");
            int[] index = new int[] {nodes.length - 1};
            return deserialize(nodes, index, Integer.MIN_VALUE);
        }
        
        private TreeNode deserialize(String[] nodes, int[] index, int min) {
            if (index[0] < 0 || Integer.valueOf(nodes[index[0]]) <= min) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]--]));
            root.right = deserialize(nodes, index, root.val);
            root.left = deserialize(nodes, index, min);
            return root;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

}


