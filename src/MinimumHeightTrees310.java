/**
 * For a undirected graph with tree characteristics, we can choose any node as
 * the root. The result graph is then a rooted tree. Among all possible rooted
 * trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list
 * of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be
 * given the number n and a list of undirected edges (each edge is a pair of
 * labels).
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 *
 * Example 1:
 *
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 * return [1]
 *
 * Example 2:
 *
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 * return [3, 4]
 *
 * Note:
 *
 * (1) According to the definition of tree on Wikipedia: “a tree is an
 * undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.”
 *
 * (2) The height of a rooted tree is the number of edges on the longest
 * downward path between the root and a leaf.
 *
 */

public class MinimumHeightTrees310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            map.put(i, new HashMap<>());
        }

        for (int[] e: edges) {
            map.get(e[0]).put(e[1], -1);
            map.get(e[1]).put(e[0], -1);
        }

        List<Integer> res = new ArrayList<>();
        int minH = Integer.MAX_VALUE;
        for (int p=0; p<n; p++) {
            boolean[] marked = new boolean[n];
            marked[p] = true;
            int newHeight = search(p, 0, map, marked);
            marked[p] = false;
            if (newHeight < minH) {
                res = new ArrayList<>();
                res.add(p);
                minH = newHeight;
            } else if (newHeight == minH) {
                res.add(p);
            }
        }

        return res;

    }

    private int search(Integer p, Integer h, Map<Integer, Map<Integer, Integer>> map, boolean[] marked) {
        Map<Integer, Integer> connects = map.get(p);
        int max = 0;
        for (Map.Entry<Integer, Integer> c: connects.entrySet()) {
            if (marked[c.getKey()]) {
                max = Math.max(max, h);
                continue;
            }
            if (c.getValue() != -1) {
                max = Math.max(max, c.getValue());
                continue;
            }
            marked[c.getKey()] = true;
            int cValue = search(c.getKey(), h, map, marked);
            marked[c.getKey()] = false;
            c.setValue(cValue);
            max = Math.max(max, cValue);
        }

        return max+1;
    }

    /**
     * https://discuss.leetcode.com/topic/30572/share-some-thoughts
     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1) leaves.add(i);

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }


    /**
     * https://discuss.leetcode.com/topic/30956/two-o-n-solutions
     */
    int n;
    List<Integer>[] e;

    private void bfs(int start, int[] dist, int[] pre) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        dist[start] = 0;
        visited[start] = true;
        pre[start] = -1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : e[u])
                if (!visited[v]) {
                    visited[v] = true;
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                    pre[v] = u;
                }
        }
    }

    public List<Integer> findMinHeightTree3(int n, int[][] edges) {
        if (n <= 0) return new ArrayList<>();
        this.n = n;
        e = new List[n];
        for (int i = 0; i < n; i++)
            e[i] = new ArrayList<>();
        for (int[] pair : edges) {
            int u = pair[0];
            int v = pair[1];
            e[u].add(v);
            e[v].add(u);
        }

        int[] d1 = new int[n];
        int[] d2 = new int[n];
        int[] pre = new int[n];
        bfs(0, d1, pre);
        int u = 0;
        for (int i = 0; i < n; i++)
            if (d1[i] > d1[u]) u = i;

        bfs(u, d2, pre);
        int v = 0;
        for (int i = 0; i < n; i++)
            if (d2[i] > d2[v]) v = i;

        List<Integer> list = new ArrayList<>();
        while (v != -1) {
            list.add(v);
            v = pre[v];
        }

        if (list.size() % 2 == 1) return Arrays.asList(list.get(list.size() / 2));
        else return Arrays.asList(list.get(list.size() / 2 - 1), list.get(list.size() / 2));
    }

    /**
     * https://discuss.leetcode.com/topic/30956/two-o-n-solutions
     */
    int n;
    List<Integer>[] e;
    int[] height1;
    int[] height2;
    int[] dp;

    private void dfs4(int u, int parent) {
        height1[u] = height2[u] = -Integer.MIN_VALUE / 10;
        for (int v : e[u])
            if (v != parent) {
                dfs4(v, u);
                int tmp = height1[v] + 1;
                if (tmp > height1[u]) {
                    height2[u] = height1[u];
                    height1[u] = tmp;
                } else if (tmp > height2[u]) {
                    height2[u] = tmp;
                }
            }
        height1[u] = Math.max(height1[u], 0); // in case u is a leaf.
    }

    private void dfs4(int u, int parent, int acc) {
        dp[u] = Math.max(height1[u], acc);
        for (int v : e[u])
            if (v != parent) {
                int newAcc = Math.max(acc + 1, (height1[v] + 1 == height1[u] ? height2[u] : height1[u]) + 1);
                dfs4(v, u, newAcc);
            }
    }

    public List<Integer> findMinHeightTrees4(int n, int[][] edges) {
        if (n <= 0) return new ArrayList<>();
        if (n == 1) return Arrays.asList(0);

        this.n = n;
        e = new List[n];
        for (int i = 0; i < n; i++)
            e[i] = new ArrayList<>();
        for (int[] pair : edges) {
            int u = pair[0];
            int v = pair[1];
            e[u].add(v);
            e[v].add(u);
        }

        height1 = new int[n];
        height2 = new int[n];
        dp = new int[n];

        dfs4(0, -1);
        dfs4(0, -1, 0);

        int min = dp[0];
        for (int i : dp)
            if (i < min) min = i;

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (dp[i] == min) ans.add(i);
        return ans;
    }


}
