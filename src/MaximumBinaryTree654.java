/**
 * Given an integer array with no duplicates. A maximum tree building on this
 * array is defined as follow:
 * 
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray
 * divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray
 * divided by the maximum number.
 * 
 * Construct the maximum tree by the given array and output the root node of
 * this tree.
 * 
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 * 
 *       6
 *     /   \
 *    3     5
 *     \    / 
 *      2  0   
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
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

public class MaximumBinaryTree654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length-1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        if (lo == hi) return new TreeNode(nums[lo]);
        
        int idx = maxIndex(nums, lo, hi);
        TreeNode root = new TreeNode(nums[idx]);
        root.left = constructMaximumBinaryTree(nums, lo, idx-1);
        root.right = constructMaximumBinaryTree(nums, idx+1, hi);
        return root;
    }

    private int maxIndex(int[] nums, int lo, int hi) {
        int res = lo;
        int max = nums[lo];
        for (int i=lo; i<=hi; i++) {
            if (nums[i] > max) {
                max = nums[i];
                res = i;
            }
        }
        return res;
    }


    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i=0; i<nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            if (stack.isEmpty() || stack.peek().val > nums[i]) {
                stack.push(curr);
                continue;
            }

            TreeNode left = null;
            while (!stack.isEmpty() && stack.peek().val < nums[i]) {
                TreeNode temp = stack.pop();
                temp.right = left;
                left = temp;                
            }
            curr.left = left;
            stack.push(curr);
        }
    
        TreeNode res = null;
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            temp.right = res;
            res = temp;                
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/maximum-binary-tree/discuss/106156/Java-worst-case-O(N)-solution
     */
    public TreeNode constructMaximumBinaryTree3(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i]) {
                curr.left = stack.pop();
            }
            if(!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        
        return stack.isEmpty() ? null : stack.removeLast();
    }

}

