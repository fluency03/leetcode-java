/**
 * We are given a binary tree (with root node root), a target node, and an
 * integer value K.
 * 
 * Return a list of the values of all nodes that have a distance K from the
 * target node.  The answer can be returned in any order.
 * 
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 * 
 * Explanation: 
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 * 
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/06/28/sketch0.png
 * 
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 * 
 * Note:
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
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

public class AllNodesDistanceKInBinaryTree863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (K == 0) {
            res.add(target.val);
            return res;
        }
        distanceToRoot(root, target, K, res, 0);
        return res;
    }

    private int distanceToRoot(TreeNode root, TreeNode target, int K, List<Integer> res, int level) {
        if (root == null) return -1;
        if (root.val == target.val) {
            distanceKChildren(root, K, res);
            return level;
        }
        int leftFlag = distanceToRoot(root.left, target, K, res, level + 1);
        if (leftFlag != -1) {
            if (leftFlag - level == K) {
                res.add(root.val);
            } else if (leftFlag - level < K) {
                distanceKChildren(root.right, K - (leftFlag - level) - 1, res);
            }
        }
        int rightFlag = distanceToRoot(root.right, target, K, res, level + 1);
        if (rightFlag != -1) {
            if (rightFlag - level == K) {
                res.add(root.val);
            } else if (rightFlag - level < K) {
                distanceKChildren(root.left, K - (rightFlag - level) - 1, res);
            }
        }
        return leftFlag != -1 ? leftFlag : rightFlag;
    }

    private void distanceKChildren(TreeNode root, int K, List<Integer> res) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 0;
        while (!q.isEmpty() && i < K) {
            int size = q.size();
            for (int j=0; j<size; j++) {
                TreeNode curr = q.poll();
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            i++;
        }
        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }
    }


    /**
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solution/
     */
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }


    /**
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solution/
     */
    List<Integer> ans;
    TreeNode target;
    int K;
    public List<Integer> distanceK3(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList();
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }

    // Return distance from node to target if exists, else -1
    public int dfs(TreeNode node) {
        if (node == null)
            return -1;
        else if (node == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.val);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.val);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist) {
        if (node == null) return;
        if (dist == K)
            ans.add(node.val);
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    }


    Map<TreeNode, Integer> map = new HashMap<>();
    public List<Integer> distanceK4(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target, K);
        dfs(root, target, K, map.get(root), res);
        return res;
    }

    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target, int K) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target, K);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target, K);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }

    private void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) length = map.get(root);
        if (length == K) res.add(root.val);
        dfs(root.left, target, K, length + 1, res);
        dfs(root.right, target, K, length + 1, res);
    }

}
