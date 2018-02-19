/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct
 * values 1, 2, ..., N), with one additional edge added. The added edge has two
 * different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges
 * is a pair [u, v] with u < v, that represents an undirected edge connecting
 * nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of
 * N nodes. If there are multiple answers, return the answer that occurs last
 * in the given 2D-array. The answer edge [u, v] should be in the same format,
 * with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N
 * is the size of the input array.
 *
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly
 * the graph is an undirected graph. For the directed graph follow up please
 * see Redundant Connection II). We apologize for any inconvenience caused.
 *
 */


public class RedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (int[] edge: edges) {
            visited.clear();
            Set<Integer> set0 = graph.getOrDefault(edge[0], new HashSet<Integer>());
            Set<Integer> set1 = graph.getOrDefault(edge[1], new HashSet<Integer>());
            if (!set0.isEmpty() && !set1.isEmpty() && dfs(graph, edge[0], edge[1], visited)) {
                return edge;
            }
            set0.add(edge[1]);
            graph.put(edge[0], set0);
            set1.add(edge[0]);
            graph.put(edge[1], set1);
        }

        return null;
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, int source, int target, Set<Integer> visited) {
        if (!visited.contains(source)) {
            visited.add(source);
            if (source == target) return true;
            for (int nei: graph.get(source)) {
                if (dfs(graph, nei, target, visited)) return true;
            }
        }
        return false;
    }



    /**
     * https://leetcode.com/problems/redundant-connection/solution/
     */
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection2(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }

    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/redundant-connection/solution/
     */
    public int[] findRedundantConnection3(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge: edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }


    /**
     * https://leetcode.com/problems/redundant-connection/discuss/107984/10-line-Java-solution-Union-Find
     */
    public int[] findRedundantConnection4(int[][] edges) {
        int[] parent = new int[1001];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (int[] edge: edges){
            int f = edge[0], t = edge[1];
            if (find(parent, f) == find(parent, t)) return edge;
            else parent[find(parent, f)] = find(parent, t);
        }

        return new int[2];
    }

    private int find(int[] parent, int f) {
        if (f != parent[f]) {
          parent[f] = find(parent, parent[f]);
        }
        return parent[f];
    }



}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
        rank = new int[size];
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}
