/**
 * Given n processes, each process has a unique PID (process id) and its PPID
 * (parent process id).
 * 
 * Each process only has one parent process, but may have one or more children
 * processes. This is just like a tree structure. Only one process has PPID
 * that is 0, which means this process has no parent process. All the PIDs will
 * be distinct positive integers.
 * 
 * We use two list of integers to represent a list of processes, where the
 * first list contains PID for each process and the second list contains the
 * corresponding PPID.
 * 
 * Now given the two lists, and a PID representing a process you want to kill,
 * return a list of PIDs of processes that will be killed in the end. You
 * should assume that when a process is killed, all its children processes will
 * be killed. No order is required for the final answer.
 * 
 * Example 1:
 * Input: 
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation: 
 *            3
 *          /   \
 *         1     5
 *              /
 *             10
 * Kill 5 will also kill 10.
 * 
 * Note:
 * The given kill id is guaranteed to be one of the given PIDs.
 * n >= 1.
 */

public class KillProcess582 {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        TreeNode root = constructTree(pid, ppid);
        List<Integer> res = new ArrayList<>();
        findNode(root, kill, res);
        return res;
    }

    private TreeNode constructTree(List<Integer> pid, List<Integer> ppid) {
        Map<Integer, TreeNode> map = new HashMap<>();
        int size = pid.size();
        TreeNode root = null;
        for (int i=0; i<size; i++) {
            int curr = pid.get(i);
            TreeNode currNode = null;
            if (!map.containsKey(curr)) {
                currNode = new TreeNode(curr);
                map.put(curr, currNode);
            } else {
                currNode = map.get(curr);
            }
            int parent = ppid.get(i);
            if (parent == 0) {
                root = currNode;
            } else {
                TreeNode parentNode = null;
                if (!map.containsKey(parent)) {
                    parentNode = new TreeNode(parent);
                    map.put(parent, parentNode);
                } else {
                    parentNode = map.get(parent);
                }
                if (!parentNode.children.containsKey(curr)) {
                    parentNode.children.put(curr, currNode);
                }
            }
        }
        return root;
    }

    // BFS
    private void findNode(TreeNode root, int kill, List<Integer> res) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currNode = q.poll();
            if (currNode.value == kill) {
                gatherResults(currNode, res);
                return;
            }
            for (TreeNode child: currNode.children.values()) {
                q.add(child);
            }
        }
    }

    private void gatherResults(TreeNode found, List<Integer> res) {
        if (found == null) return;
        res.add(found.value);
        for (TreeNode child: found.children.values()) {
            gatherResults(child, res);
        }
    }

    class TreeNode {
        int value;
        Map<Integer, TreeNode> children = new HashMap<>();
        TreeNode(int x) {
            this.value = x;
        }
    }


    public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
        TreeNode found = findKill(pid, ppid, kill);
        List<Integer> res = new ArrayList<>();
        gatherResults(found, res);
        return res;
    }

    private TreeNode findKill(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, TreeNode> map = new HashMap<>();
        int size = pid.size();
        TreeNode found = null;
        for (int i=0; i<size; i++) {
            int curr = pid.get(i);
            TreeNode currNode = null;
            if (!map.containsKey(curr)) {
                currNode = new TreeNode(curr);
                map.put(curr, currNode);
            } else {
                currNode = map.get(curr);
            }
            if (curr == kill) {
                found = currNode;
            }
            int parent = ppid.get(i);
            if (parent != 0) {
                TreeNode parentNode = null;
                if (!map.containsKey(parent)) {
                    parentNode = new TreeNode(parent);
                    map.put(parent, parentNode);
                } else {
                    parentNode = map.get(parent);
                }
                if (!parentNode.children.containsKey(curr)) {
                    parentNode.children.put(curr, currNode);
                }
            }
        }
        return found;
    }


    /**
     * https://leetcode.com/problems/kill-process/solution/
     */
    class Node {
        int val;
        List < Node > children = new ArrayList < > ();
    }
    public List < Integer > killProcess3(List < Integer > pid, List < Integer > ppid, int kill) {
        HashMap < Integer, Node > map = new HashMap < > ();
        for (int id: pid) {
            Node node = new Node();
            node.val = id;
            map.put(id, node);
        }
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                Node par = map.get(ppid.get(i));
                par.children.add(map.get(pid.get(i)));
            }
        }
        List < Integer > l = new ArrayList < > ();
        l.add(kill);
        getAllChildren(map.get(kill), l);
        return l;
    }
    public void getAllChildren(Node pn, List < Integer > l) {
        for (Node n: pn.children) {
            l.add(n.val);
            getAllChildren(n, l);
        }
    }


    /**
     * https://leetcode.com/problems/kill-process/discuss/103176/Java-Solution-HashMap
     */
    public List<Integer> killProcess4(List<Integer> pid, List<Integer> ppid, int kill) {
        // Kill root, return all nodes
        if (kill == 0) return pid;
        
        int n = pid.size();
        Map<Integer, Set<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!tree.containsKey(ppid.get(i))) {
                tree.put(ppid.get(i), new HashSet<>());
            }
            tree.get(ppid.get(i)).add(pid.get(i));
        }
        
        List<Integer> result = new ArrayList<>();
        traverse(tree, result, kill);
        
        return result;
    }
    
    private void traverse(Map<Integer, Set<Integer>> tree, List<Integer> result, int pid) {
        result.add(pid);
        if (!tree.containsKey(pid)) {
            return;
        }
        
        for (Integer child : tree.get(pid)) {
            traverse(tree, result, child);
        }
    }


    /**
     * https://leetcode.com/problems/kill-process/solution/
     */
    public List < Integer > killProcess5(List < Integer > pid, List < Integer > ppid, int kill) {
        HashMap < Integer, List < Integer >> map = new HashMap < > ();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List < Integer > l = map.getOrDefault(ppid.get(i), new ArrayList < Integer > ());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }
        Queue < Integer > queue = new LinkedList < > ();
        List < Integer > l = new ArrayList < > ();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int r = queue.remove();
            l.add(r);
            if (map.containsKey(r))
                for (int id: map.get(r))
                    queue.add(id);
        }
        return l;
    }

}
