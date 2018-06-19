/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * 
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0,1] is the same as [1,0] and thus will not appear
 * together in edges.
 */

public class GraphValidTree261 {
    // 1. construct Graph
    // 2. detection cycle -- DFS
    // 3. check not more than one tree
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (n == 1) return true;
        Map<Integer, Set<Integer>> graph = constructGraph(n, edges);
        boolean[] visited = new boolean[n];
        
        // if cycle detected, return false
        if (!isValid(graph, visited, 0, -1)) return false;
        for (int i=0; i<n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
    
    // return false if cycle detected
    private boolean isValid(Map<Integer, Set<Integer>> graph, boolean[] visited, int curr, int parent) {
        if (visited[curr]) {
            return false;
        }
        
        visited[curr] = true;
        if (!graph.containsKey(curr)) return true;
        for (int i: graph.get(curr)) {
            if (i != parent && !isValid(graph, visited, i, curr)) return false;
        }
        return true;
    }
    
    private Map<Integer, Set<Integer>> constructGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i=0; i<n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
  
    
    public boolean validTree2(int n, int[][] edges) {
        DisjointSet djs = new DisjointSet(n);
        for (int[] edge: edges) {
            int x = djs.find(edge[0]);
            int y = djs.find(edge[1]);
            if (x == y) return false;
            djs.union(x, y);
        }
        int root = djs.find(0);
        for (int i=1; i<n; i++) {
            if (djs.find(i) != root) return false;
        }
        return true;
    }

    class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            this.parent = new int[n];
            for (int i=0; i<n; i++) this.parent[i] = i;
            this.rank = new int[n];
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int xx = find(x);
            int yy = find(y);
            if (rank[xx] > rank[yy]) {
                parent[yy] = xx;
            } else if (rank[xx] < rank[yy]) {
                parent[xx] = yy;
            } else {
                parent[xx] = yy;
                rank[yy]++;
            }
        }
    }


    /**
     * https://leetcode.com/problems/graph-valid-tree/discuss/69018/AC-Java-Union-Find-solution
     */
    public boolean validTree3(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            
            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;
            
            // union
            nums[x] = y;
        }
        
        return edges.length == n - 1;
    }
    
    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }

}
