/**
 * Given the root of a tree, you are asked to find the most frequent subtree
 * sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So
 * what is the most frequent subtree sum value? If there is a tie, return all
 * the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of
 * them in any order.
 *
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 *
 * Note: You may assume the sum of values in any subtree is in the range of
 * 32-bit signed integer.
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


public class MostFrequentSubtreeSum508 {
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> sums = new HashMap<>();
        if (root == null) return new int[0];

        int[] cache = new int[1];
        cache[0] = Integer.MIN_VALUE;
        helper(root, sums, cache);

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e: sums.entrySet()) {
            if (e.getValue().equals(cache[0])) {
                list.add(e.getKey());
            }
        }
        int[] res = new int[list.size()];
        for (int i=0; i<list.size(); i++) res[i] = list.get(i);
        return res;
    }

    private Integer helper(TreeNode n, Map<Integer, Integer> sums, int[] cache) {
        if (n == null) return null;

        Integer left = helper(n.left, sums, cache);
        Integer right = helper(n.right, sums, cache);

        int sum = ((left == null) ? 0 : left) + ((right == null) ? 0 : right) + n.val;
        int t = sums.getOrDefault(sum, 0) + 1;
        cache[0] = Math.max(cache[0], t);
        sums.put(sum, t);

        return sum;
    }


    public int[] findFrequentTreeSum2(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSum(root, map);
        
        int freq = 0;
        Set<Integer> sums = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == freq) {
                sums.add(entry.getKey());
            } else if (entry.getValue() > freq) {
                freq = entry.getValue();
                sums.clear();
                sums.add(entry.getKey());
            }
        }
        
        int[] res = new int[sums.size()];
        int i = 0;
        for (int n: sums) {
            res[i++] = n;
        }
        return res;
    }

    public int findFrequentTreeSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        
        int l = findFrequentTreeSum(root.left, map);
        int r = findFrequentTreeSum(root.right, map);
        int sum = l + r + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
