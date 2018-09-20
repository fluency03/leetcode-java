/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split
 * everyone into two groups of any size.
 * 
 * Each person may dislike some other people, and they should not go into the
 * same group. 
 * 
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the
 * people numbered a and b into the same group.
 * 
 * Return true if and only if it is possible to split everyone into two groups
 * in this way.
 * 
 * Example 1:
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * 
 * Example 2:
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 * Example 3:
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 * 
 * Note:
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */

public class PossibleBipartition886 {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Set<Integer>[] nonGraph = new Set[N+1];
        for (int[] d: dislikes) {
            if (nonGraph[d[0]] == null) {
                nonGraph[d[0]] = new HashSet<>();
            }
            if (nonGraph[d[1]] == null) {
                nonGraph[d[1]] = new HashSet<>();
            }
            nonGraph[d[0]].add(d[1]);
            nonGraph[d[1]].add(d[0]);
        }

        int[] visited = new int[N+1];
        for (int i=1; i<=N; i++) {
            if (visited[i] == 0) {
                if (!helper(i, nonGraph, N, visited, 1)) return false;
            }
        }
        return true;
    }

    private boolean helper(int curr, Set<Integer>[] nonGraph, int N, int[] visited, int pre) {
        if (visited[curr] != 0) return visited[curr] == pre;
        visited[curr] = pre;
        if (nonGraph[curr] == null) return true;
        for (int next: nonGraph[curr]) {
            if (visited[next] == pre) return false;
            if (visited[next] == 0) {
                if (!helper(next, nonGraph, N, visited, -pre)) return false;
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/possible-bipartition/discuss/159085/java-graph
     */
    public boolean possibleBipartition2(int N, int[][] dislikes) {
        Set<Integer>[] nonGraph = new Set[N+1];
        for (int[] d: dislikes) {
            if (nonGraph[d[0]] == null) {
                nonGraph[d[0]] = new HashSet<>();
            }
            if (nonGraph[d[1]] == null) {
                nonGraph[d[1]] = new HashSet<>();
            }
            nonGraph[d[0]].add(d[1]);
            nonGraph[d[1]].add(d[0]);
        }

        int[] visited = new int[N+1];
        for (int i=1; i<=N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    if (nonGraph[curr] == null) continue;
                    for (int next: nonGraph[curr]) {
                        if (visited[next] == 0) {
                            visited[next] = -visited[curr];
                            q.add(next);
                        } else {
                            if (visited[next] == visited[curr]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
