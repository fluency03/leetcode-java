/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a
 * binary tree (https://leetcode.com/faq/#binary-tree). You do not necessarily
 * need to follow this format, so please be creative and come up with different
 * approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 *
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

public class SerializeAndDeserializeBinaryTree297 {
    class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append('[');

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int nulls = 0;
            while (!q.isEmpty()) {
                TreeNode n = q.remove();
                if (n == null) {
                    nulls++;
                    sb.append("null,");
                } else {
                    nulls = 0;
                    sb.append(n.val + ",");
                    q.add(n.left);
                    q.add(n.right);
                }
            }

            while (nulls > 0) {
                sb.delete(sb.length()-5, sb.length());
                nulls--;
            }

            sb.setCharAt(sb.length()-1, ']');
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nums = data.substring(1, data.length()-1).split(",");
            if (nums.length <= 0 || nums[0].equals("")) return null;
            TreeNode res = new TreeNode(Integer.valueOf(nums[0]));

            Queue<TreeNode> q = new LinkedList<>();
            q.add(res);
            int i = 1;
            while (i < nums.length) {
                int add = 1;
                TreeNode parent = q.remove();
                TreeNode left = null;
                if (!nums[i].equals("null")) {
                    left = new TreeNode(Integer.valueOf(nums[i]));
                    q.add(left);
                }

                TreeNode right = null;
                if (i+1 < nums.length) {
                    add++;
                    if (!nums[i+1].equals("null")) {
                        right = new TreeNode(Integer.valueOf(nums[i+1]));
                        q.add(right);
                    }
                }

                parent.left = left;
                parent.right = right;
                i += add;
            }

            return res;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



// // preorder, DFS
// public class Codec {
//
//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         StringBuilder sb = new StringBuilder();
//         serialize(root, sb);
//         System.out.println(sb.toString());
//         return sb.toString();
//     }
//
//     private void serialize(TreeNode root, StringBuilder sb) {
//         if (root == null) {
//             sb.append('N').append(',');
//         } else {
//             sb.append(root.val).append(',');
//             serialize(root.left, sb);
//             serialize(root.right, sb);
//         }
//     }
//
//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         return deserialize(data.split(","), new int[]{0});
//     }
//
//     public TreeNode deserialize(String[] data, int[] is) {
//         if (is[0] >= data.length) return null;
//         String val = data[is[0]];
//         is[0]++;
//         if (val.equals("N")) {
//             return null;
//         } else {
//             TreeNode node = new TreeNode(Integer.valueOf(val));
//             node.left = deserialize(data, is);
//             node.right = deserialize(data, is);
//             return node;
//         }
//     }
// }
