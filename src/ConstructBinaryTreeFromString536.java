/**
 * You need to construct a binary tree from a string consisting of parenthesis
 * and integers.
 * 
 * The whole input represents a binary tree. It contains an integer followed
 * by zero, one or two pairs of parenthesis. The integer represents the root's
 * value and a pair of parenthesis contains a child binary tree with the same
 * structure.
 * 
 * You always start to construct the left child node of the parent first if
 * it exists.
 * 
 * Example:
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 * 
 *        4
 *      /   \
 *     2     6
 *    / \   / 
 *   3   1 5   
 * 
 * Note:
 * There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * An empty tree is represented by "" instead of "()".
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

public class ConstructBinaryTreeFromString536 {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        return str2tree(s.toCharArray(), 0, s.length()-1);
    }
    
    public TreeNode str2tree(char[] chars, int i, int j) {
        if (i > j) return null;
        int len = 0;
        while (i+len <= j && chars[i+len] != '(' && chars[i+len] != ')') {
            len++;
        }
        TreeNode root = new TreeNode(Integer.parseInt(new String(chars, i, len)));
        if (i + len - 1 == j) return root;

        int m = i + len;
        int count = 0;
        while (m <= j) {
            if (chars[m] == '(') {
                count++;
            } else if (chars[m] == ')') {
                count--;
            }
            m++;
            if (count == 0) break;
        }
        root.left = str2tree(chars, i+len+1, m-2);
        root.right = str2tree(chars, m+1, j-1);
        return root;
    }

}
