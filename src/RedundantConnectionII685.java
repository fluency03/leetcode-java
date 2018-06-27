/**
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of
 * this node, plus every node has exactly one parent, except for the root
 * node which has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with N
 * nodes (with distinct values 1, 2, ..., N), with one additional directed
 * edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges. Each element of edges
 * is a pair [u, v] that represents a directed edge connecting nodes u and v,
 * where u is a parent of child v.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of N nodes. If there are multiple answers, return the answer that
 * occurs last in the given 2D-array.
 * 
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 * 
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * 
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N,
 * where N is the size of the input array.
 */


public class RedundantConnectionII685 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int[] res1 = null;
        int[] res2 = null;
        
        int[] parent = new int[N+1];
        boolean twoParentsExist = false;
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            if (parent[v] != 0) {
                res1 = new int[]{parent[v], v};
                res2 = new int[]{u, v};
                twoParentsExist = true;
                break;
            }
            parent[v] = u;
        }
        
        DisjointSet djs = new DisjointSet(N);
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            if (twoParentsExist && ((u == res1[0] && v == res1[1]) || (u == res2[0] && v == res2[1]))) continue;
            int up = djs.find(u);
            int vp = djs.find(v);

            if (up == vp) {
                return edge;
            } else {
                djs.union(u, v);
            }
        }      
        if (twoParentsExist && djs.find(res1[0]) == djs.find(res1[1])) {
            return res1;
        }
        return res2;
    }

    
    class DisjointSet {
        int[] parent;
        int[] rank;
        DisjointSet(int N) {
            parent = new int[N+1];
            for (int i=0; i<N; i++) parent[i] = i;
            rank = new int[N+1];
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int xp = find(x);
            int yp = find(y);
            
            if (rank[xp] > rank[yp]) {
                parent[yp] = xp;
            } else if (rank[xp] < rank[yp]) {
                parent[xp] = yp;
            } else {
                parent[xp] = yp;
                rank[yp]++;
            }
        }
    }

}

