/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * 
 * Example 1:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *      0          3
 *      |          |
 *      1 --- 2    4 
 * Output: 2
 * 
 * Example 2:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 * Output:  1
 * 
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 */

public class NumberOfConnectedComponentsInAnUndirectedGraph323 {
    public int countComponents(int n, int[][] edges) {
        if (n <= 0) return 0;
        if (edges == null || edges.length == 0) return n;
        UnionFind uf = new UnionFind(n);
        int res = n;
        for (int[] e: edges) {
            int pa = uf.find(e[0]);
            int pb = uf.find(e[1]);
            if (pa != pb) res--;
            uf.union(e[0], e[1]);
        }
        return res;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            this.parent = new int[n];
            for (int i=0; i<n; i++) this.parent[i] = i;
            this.rank = new int[n];
        }

        int find(int x) {
            if (this.parent[x] != x) {
                this.parent[x] = find(this.parent[x]);
            }
            return this.parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            
            if (this.rank[px] < this.rank[py]) {
                this.parent[px] = py;
            } else if (this.rank[px] > this.rank[py]) {
                this.parent[py] = px;
            } else {
                this.parent[py] = px;
                this.rank[px]++;
            }
        }
    }

}
