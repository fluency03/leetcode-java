/**
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 *
 * 1. Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 * return its vertical order traversal as:
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 *
 * 2. Given binary tree [3,9,8,4,0,1,7],
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 * return its vertical order traversal as:
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 *
 * 3. Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 * return its vertical order traversal as:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
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

public class BinaryTreeVerticalOrderTraversal314 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> vs = new HashMap<>();
        q.offer(root);
        vs.put(root, 0);
        int min = 0;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            int i = vs.get(curr);
            if (curr.left != null) {
                vs.put(curr.left, i-1);
                q.offer(curr.left);
            }
            if (curr.right != null) {
                vs.put(curr.right, i+1);
                q.offer(curr.right);
            }
            map.computeIfAbsent(i, p -> new ArrayList<Integer>()).add(curr.val);
            min = Math.min(min, i);
        }

        List<List<Integer>> res = new ArrayList<>();

        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }

        return res;
    }



    public List<List<Integer>> verticalOrder2(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> qi = new LinkedList<>();
        q.add(root);
        qi.add(0);
        int min = 0;
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            Integer i = qi.remove();
            if (curr.left != null) {
                qi.add(i-1);
                q.add(curr.left);
            }
            if (curr.right != null) {
                qi.add(i+1);
                q.add(curr.right);
            }
            if (!map.containsKey(i)) {
                map.put(i, new ArrayList<Integer>());
            }
            map.get(i).add(curr.val);
            // map.computeIfAbsent(i, p -> new ArrayList<Integer>()).add(curr.val);
            min = Math.min(min, i);
        }

        List<List<Integer>> res = new ArrayList<>();

        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76401/5ms-Java-Clean-Solution
     */
    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> cols = new ArrayList<>();
        if (root == null) {
            return cols;
        }

        int[] range = new int[] {0, 0};
        getRange(root, range, 0);

        for (int i = range[0]; i <= range[1]; i++) {
            cols.add(new ArrayList<Integer>());
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        queue.add(root);
        colQueue.add(-range[0]);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = colQueue.poll();

            cols.get(col).add(node.val);

            if (node.left != null) {
                queue.add(node.left);
                colQueue.add(col - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                colQueue.add(col + 1);
            }
        }

        return cols;
    }

    public void getRange(TreeNode root, int[] range, int col) {
        if (root == null) {
            return;
        }
        range[0] = Math.min(range[0], col);
        range[1] = Math.max(range[1], col);

        getRange(root.left, range, col - 1);
        getRange(root.right, range, col + 1);
    }

}
