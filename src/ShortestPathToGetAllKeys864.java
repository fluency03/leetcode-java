/**
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@"
 * is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are
 * locks.
 * 
 * We start at the starting point, and one move consists of walking one space
 * in one of the 4 cardinal directions.  We cannot walk outside the grid, or
 * walk into a wall.  If we walk over a key, we pick it up.  We can't walk over
 * a lock unless we have the corresponding key.
 * 
 * For some 1 <= K <= 6, there is exactly one lowercase and one uppercase
 * letter of the first K letters of the English alphabet in the grid. This
 * means that there is exactly one key for each lock, and one lock for each
 * key; and also that the letters used to represent the keys and locks were
 * chosen in the same order as the English alphabet.
 * 
 * Return the lowest number of moves to acquire all keys.
 * If it's impossible, return -1.
 * 
 * Example 1:
 * Input: ["@.a.#","###.#","b.A.B"]
 * Output: 8
 * 
 * Example 2:
 * Input: ["@..aA","..B#.","....b"]
 * Output: 6
 * 
 * Note:
 * 1 <= grid.length <= 30
 * 1 <= grid[0].length <= 30
 * grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
 * The number of keys is in [1, 6].
 * Each key has a different letter and opens exactly one lock.
 */

public class ShortestPathToGetAllKeys864 {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPathAllKeys(String[] grid) {
        int M = grid.length;
        int N = grid[0].length();
        char[][] maze = new char[M][N];
        int numKeys = 0;
        int[] start = new int[2];
        for (int i=0; i<M; i++) {
            char[] row = grid[i].toCharArray();
            for (int j=0; j<N; j++) {
                maze[i][j] = row[j];
                if (row[j] == '@') {
                    start[0] = i;
                    start[1] = j;
                } else if (row[j] >= 'a' && row[j] <= 'f') {
                    numKeys++;
                }
            }
        }

        int[] res = new int[]{Integer.MAX_VALUE};
        Set<Character> keySet = new HashSet<>();
        helper(maze, start, numKeys, keySet, 0, M, N, res);
        return res[0] == Integer.MAX_VALUE ? -1 : res[0];
    }

    public void helper(char[][] maze, int[] start, int numKeys, Set<Character> keySet, int steps, int M, int N, int[] res) {
        if (numKeys == keySet.size()) {
            res[0] = Math.min(res[0], steps);
            return;
        }
        boolean[][] visited = new boolean[M][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        Set<int[]> newKeys = new HashSet<>();
        int level = 1;
        while (!q.isEmpty() && steps + level < res[0]) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                int[] curr = q.poll();
                for (int[] dir: directions) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x < 0 || y < 0 || x >= M || y >= N) continue;
                    char next = maze[x][y];
                    if (next == '#' || visited[x][y]) continue;
                    if (next == '@' || next == '.') {
                        q.add(new int[]{x, y});
                        visited[x][y] = true;
                    } else if (next >= 'a' && next <= 'f') {
                        if (!keySet.contains(next)) {
                            newKeys.add(new int[]{x, y, level});
                        } else {
                            q.add(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    } else if (next >= 'A' && next <= 'F') {
                        if (keySet.contains(Character.toLowerCase(next))) {
                            q.add(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                }
            }
            level++;
        }

        if (!newKeys.isEmpty()) {
            for (int[] newKey: newKeys) {
                int[] newKeyPos = new int[]{newKey[0], newKey[1]};
                char key = maze[newKey[0]][newKey[1]];
                keySet.add(key);
                helper(maze, newKeyPos, numKeys, keySet, steps + newKey[2], M, N, res);
                keySet.remove(key);
            }
        }
    }

}
