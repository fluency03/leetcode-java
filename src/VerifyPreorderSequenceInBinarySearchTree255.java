/**
 * Given an array of numbers, verify whether it is the correct preorder
 * traversal sequence of a binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Consider the following binary search tree: 
 * 
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 
 * Example 1:
 * Input: [5,2,6,1,3]
 * Output: false
 * 
 * Example 2:
 * Input: [5,2,1,3,6]
 * Output: true
 * 
 * Follow up:
 * Could you do it using only constant space complexity?
 */

public class VerifyPreorderSequenceInBinarySearchTree255 {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) return true;
        Stack<Integer> stack = new Stack<>();
        int lower = Integer.MIN_VALUE;
        for (int n: preorder) {
            if (n < lower) return false;
            while (!stack.isEmpty() && stack.peek() < n) {
                int top = stack.pop();
                if (top > lower) lower = top;
            }
            stack.push(n);
        }
        return true;
    }


    public boolean verifyPreorder2(int[] preorder) {
        if (preorder == null || preorder.length <= 1) return true;
        return helper(preorder, 0, preorder.length-1, Integer.MIN_VALUE);
    }

    private boolean helper(int[] preorder, int i, int j, int lower) {
        if (i > j) return true;
        if (i == j) return preorder[i] > lower;
        
        int root = preorder[i];
        if (root < lower) return false;
        int mid = i + 1;
        while (mid <= j && preorder[mid] < root) {
            mid++;
        }
        return helper(preorder, i+1, mid-1, lower) && helper(preorder, mid, j, root);
    }



    public boolean verifyPreorder3(int[] preorder) {
        if (preorder == null || preorder.length <= 1) return true;
        int i = -1;
        int lower = Integer.MIN_VALUE;
        for (int n: preorder) {
            if (n < lower) return false;
            while (i != -1 && preorder[i] < n) {
                int top = preorder[i--];
                if (top > lower) lower = top;
            }
            preorder[++i] = n;
        }
        return true;
    }








}
