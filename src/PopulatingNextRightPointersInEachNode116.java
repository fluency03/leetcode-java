/**
 * Given a binary tree
 *
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 *
 *
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 *
 */


/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */


public class PopulatingNextRightPointersInEachNode116 {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        LinkedList<TreeLinkNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);

        int num = 2;
        while (!q.isEmpty()) {
            TreeLinkNode pre = q.remove();
            if (pre == null) return;
            q.add(pre.left);
            q.add(pre.right);
            int i = 0;
            if (q.isEmpty()) return;
            while (i<num-1) {
                TreeLinkNode now = q.remove();
                if (now == null) return;
                q.add(now.left);
                q.add(now.right);
                pre.next = now;
                pre = now;
                i++;
            }
            num <<= 1;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/6221/java-solution-with-o-1-memory-o-n-time
     */
    public void connect2(TreeLinkNode root) {
        TreeLinkNode level_start=root;
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){
                if(cur.left!=null) cur.left.next=cur.right;
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;

                cur=cur.next;
            }
            level_start=level_start.left;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/12241/my-recursive-solution-java
     */
    public void connect3(TreeLinkNode root) {
        if(root == null)
            return;

        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect3(root.left);
        connect3(root.right);
    }


    public void connect4(TreeLinkNode root) {
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
            if (n.left != null) {
                newLevel.add(n.left);
                newLevel.add(n.right);
            }
        }

        connect(newLevel);
    }

}
