/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous
 * solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class PopulatingNextRightPointersInEachNodeII117 {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        LinkedList<TreeLinkNode> level = new LinkedList<>();
        level.add(root);
        connect(level);
    }

    private void connect(LinkedList<TreeLinkNode> level) {
        LinkedList<TreeLinkNode> newLevel = new LinkedList<>();
        if (level.isEmpty()) return;
        while (!level.isEmpty()) {
            TreeLinkNode n = level.remove();
            if (n == null) return;
            n.next = level.peek();
            if (n.left != null) newLevel.add(n.left);
            if (n.right != null) newLevel.add(n.right);
        }

        connect(newLevel);
    }


    /**
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37811/Simple-solution-using-constant-space/35836
     */
    public void connect2(TreeLinkNode root) {
        TreeLinkNode tempChild = new TreeLinkNode(0);
        while (root != null) {
            TreeLinkNode currentChild = tempChild;
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
            tempChild.next = null;
        }
    }


    public void connect3(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeLinkNode pre = q.remove();
            if (pre.left != null) q.add(pre.left);
            if (pre.right != null) q.add(pre.right);
            TreeLinkNode curr = null;
            for (int i=1; i<size; i++) {
                curr = q.remove();
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
                pre.next = curr;
                pre = curr;
            }
        }
    }

}
