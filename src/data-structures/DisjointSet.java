/**
 * A.K.A.: Union-Find with Path Compression and Union by Rank
 */

public class DisjointSet {

    private int[] parent;
    private int[] rank;

    public DisjointSet(int v) {
        this.parent = new int[v];
        for (int i=0; i<v; i++) {
            this.parent[i] = i;
        }
        this.rank = new int[v];
    }

    public int find(int x) {
        if (this.parent[x] != x) {
            this.parent[x] = find(this.parent[x]);
        }
        return this.parent[x];
    }

    public void union(int x, int y) {
        int xx = find(x);
        int yy = find(y);

        if (this.rank[xx] < this.rank[yy]) {
            this.parent[xx] = yy;
        } else if (this.rank[xx] > this.rank[yy]) {
            this.parent[yy] = xx;
        } else {
            this.parent[xx] = yy;
            this.parent[yy]++;
        }
    }

}
