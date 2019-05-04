/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally
 * connected group of 1s not connected to any other 1s.)
 * 
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * 
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 * 
 * Example 1:
 * Input: [[0,1],[1,0]]
 * Output: 1
 * 
 * Example 2:
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * Example 3:
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * Note:
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 */

public class ShortestBridge934 {
    private int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> edge = findOneIslandEdge(A, visited);
        int res = 0;
        while (!edge.isEmpty()) {
            int len = edge.size();
            for (int i=0; i<len; i++) {
                int[] curr = edge.remove();
                for (int k=0; k<4; k++) {
                    int ii = curr[0] + DIR[k][0];
                    int jj = curr[1] + DIR[k][1];
                    if (ii >= 0 && ii < m && jj >= 0 && jj < n && !visited[ii][jj]) {
                        if (A[ii][jj] == 0) {
                            edge.add(new int[]{ii, jj});
                            visited[ii][jj] = true;
                        } else {
                            return res;
                        }
                    }
                }
            }
            res++;
        }
        return res;
    }
    
    private Queue<int[]> findOneIslandEdge(int[][] A, boolean[][] visited) {
        int m = A.length;
        int n = A[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (A[i][j] == 1) {
                    return collectEdge(A, i, j, visited);
                }
            }
        }
        return null;
    }
    
    private Queue<int[]> collectEdge(int[][] A, int si, int sj, boolean[][] visited) {
        Queue<int[]> edge = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();
        int m = A.length;
        int n = A[0].length;
        visited[si][sj] = true;
        q.add(new int[]{si, sj});
        while (!q.isEmpty()) {
            int[] curr = q.remove();
            boolean isEdge = false;
            for (int k=0; k<4; k++) {
                int ii = curr[0] + DIR[k][0];
                int jj = curr[1] + DIR[k][1];
                if (ii >= 0 && ii < m && jj >= 0 && jj < n && !visited[ii][jj]) {
                    if (A[ii][jj] == 0) {
                        isEdge = true;
                    } else {
                        q.add(new int[]{ii, jj});
                        visited[ii][jj] = true;
                    }
                }
            }
            if (isEdge) {
                edge.add(curr);
            }
        }
        return edge;
    }
}
