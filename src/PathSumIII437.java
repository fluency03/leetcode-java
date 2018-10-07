/**
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * 
 * Example:
 *  * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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

public class PathSumIII437 {
    public int pathSum(TreeNode root, int sum) {
        int[] res = new int[1];
        pathSum(root, sum, res, new ArrayList<>());
        return res[0];
    }

    private void pathSum(TreeNode root, int target, int[] res, List<Integer> path) {
        if (root == null) return;
        int last = path.isEmpty() ? root.val : path.get(path.size() - 1) + root.val;
        if (last == target) res[0]++;
        for (int s: path) {
            if (last - s == target) {
                res[0]++;
            }
        }
        path.add(last);
        pathSum(root.left, target, res, path);
        pathSum(root.right, target, res, path);
        path.remove(path.size() - 1);
    }


    /**
     * https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
     */
    public int pathSum2(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

}
