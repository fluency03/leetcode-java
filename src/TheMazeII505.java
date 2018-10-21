/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, find the
 * shortest distance for the ball to stop at the destination. The distance is
 * defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the destination (included). If the ball cannot stop
 * at the destination, return -1.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 * 
 * Example 1
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * 
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * https://leetcode.com/static/images/problemset/maze_1_example_1.png
 * 
 * 
 * Example 2
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * 
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 * https://leetcode.com/static/images/problemset/maze_1_example_2.png
 * 
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not
 * be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the
 * example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of
 * the maze won't exceed 100.
 */

public class TheMazeII505 {
    private int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int M = maze.length;
        int N = maze[0].length;
        if (isSame(start, destination)) return 0;
        int[][] visited = new int[M][N];
        for (int[] row: visited) {
            Arrays.fill(row, -1);
        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start, 0));
        visited[start[0]][start[1]] = 0;
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            Point curr = q.poll();
            if (isSame(curr.pos, destination)) {
                min = Math.min(min, curr.dist);
                continue;
            }
            for (int[] d: DIRECTIONS) {
                int x = curr.pos[0] + d[0];
                int y = curr.pos[1] + d[1];
                int more = 1;
                while (x >= 0 && y >= 0 && x < M && y < N && maze[x][y] == 0) {
                    x += d[0];
                    y += d[1];
                    more++;
                }
                x -= d[0];
                y -= d[1];
                more--;
                int[] newPos = new int[]{x, y};
                if (visited[x][y] == -1 || visited[x][y] > curr.dist + more) {
                    q.add(new Point(newPos, curr.dist + more));
                    visited[x][y] = curr.dist + more;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private boolean isSame(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }
    
    class Point {
        int[] pos;
        int dist;
        Point(int[] p, int d) {
            pos = p;
            dist = d;
        }
    }


    /**
     * https://leetcode.com/problems/the-maze-ii/discuss/98418/Simple-Java-Solution-BFS
     */
    public int shortestDistance2(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> q = new LinkedList<>();
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] { 0, 1, 0, -1};
        q.offer(start);
        dist[start[0]][start[1]] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dx[i], y = p[1] + dy[i];
                int cnt = 1;
                
                while (x >=0 && x < m && y >= 0 && y < n && maze[x][y] != 1) {
                    x += dx[i];
                    y += dy[i];
                    cnt++;
                }
                x -= dx[i];
                y -= dy[i];
                cnt--;
                if (dist[p[0]][p[1]] + cnt < dist[x][y]) {
                    dist[x][y] = dist[p[0]][p[1]] + cnt;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }


    public int shortestDistance3(int[][] maze, int[] start, int[] destination) {
        int M = maze.length;
        int N = maze[0].length;
        int[][] memo = new int[M][N];
        for (int[] row: memo) Arrays.fill(row, Integer.MAX_VALUE);
        memo[start[0]][start[1]] = 0;
        roll(maze, start[0], start[1], memo, M, N);
        return memo[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : memo[destination[0]][destination[1]];
    }

    private void roll(int[][] maze, int i, int j, int[][] memo, int M, int N) {
        // top
        int t = i - 1;
        int rt = 1;
        while (t >= 0 && maze[t][j] == 0) {
            t--;
            rt++;
        }
        t++;
        rt--;
        if (memo[i][j] + rt < memo[t][j]) {
            memo[t][j] = memo[i][j] + rt;
            roll(maze, t, j, memo, M, N);
        }

        // bottom
        int b = i + 1;
        int rb = 1;
        while (b < M && maze[b][j] == 0) {
            b++;
            rb++;
        }
        b--;
        rb--;
        if (memo[i][j] + rb < memo[b][j]) {
            memo[b][j] = memo[i][j] + rb;
            roll(maze, b, j, memo, M, N);
        }

        // left
        int l = j - 1;
        int rl = 1;
        while (l >= 0 && maze[i][l] == 0) {
            l--;
            rl++;
        }
        l++;
        rl--;
        if (memo[i][j] + rl < memo[i][l]) {
            memo[i][l] = memo[i][j] + rl;
            roll(maze, i, l, memo, M, N);
        }

        // right
        int r = j + 1;
        int rr = 1;
        while (r < N && maze[i][r] == 0) {
            r++;
            rr++;
        }
        r--;
        rr--;
        if (memo[i][j] + rr < memo[i][r]) {
            memo[i][r] = memo[i][j] + rr;
            roll(maze, i, r, memo, M, N);
        }
    }


    /**
     * https://leetcode.com/problems/the-maze-ii/solution/
     */
    public int shortestDistance4(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
            }
        }
    }


    /**
     * https://leetcode.com/problems/the-maze-ii/solution/
     */
    public int shortestDistance5(int[][] maze, int[] start, int[] destination) {
        int M = maze.length;
        int N = maze[0].length;
        int[][] distances = new int[M][N];
        for (int[] row: distances) Arrays.fill(row, Integer.MAX_VALUE);
        distances[start[0]][start[1]] = 0;
        boolean[][] visited = new boolean[M][N];
        roll(maze, distances, visited, M, N);
        int res = distances[destination[0]][destination[1]];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void roll(int[][] maze, int[][] distances, boolean[][] visited, int M, int N) {
        while (true) {
            int[] m = minDistance(distances, visited, M, N);
            if (m == null) return;
            visited[m[0]][m[1]] = true;
            for (int[] dir: DIRECTIONS) {
                int x = m[0] + dir[0];
                int y = m[1] + dir[1];
                int dis = 0;
                while (x >= 0 && y >= 0 && x < M && y < N && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    dis++;
                }
                x -= dir[0];
                y -= dir[1];
                int d = distances[m[0]][m[1]] + dis;
                if (d < distances[x][y]) {
                    distances[x][y] = d;
                    
                }
            }  
        }
    }

    private int[] minDistance(int[][] distances, boolean[][] visited, int M, int N) {
        int[] res = null;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j] && distances[i][j] < min) {
                    res = new int[]{i, j};
                    min = distances[i][j];
                }
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/the-maze-ii/solution/
     */
    public int shortestDistance6(int[][] maze, int[] start, int[] destination) {
        int M = maze.length;
        int N = maze[0].length;
        int[][] distances = new int[M][N];
        for (int[] row: distances) Arrays.fill(row, Integer.MAX_VALUE);
        distances[start[0]][start[1]] = 0;
        roll(maze, distances, start, M, N);
        int res = distances[destination[0]][destination[1]];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void roll(int[][] maze, int[][] distances, int[] start, int M, int N) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{start[0], start[1], 0});
        while (!pq.isEmpty()) {
            int[] md = pq.poll();
            if (distances[md[0]][md[1]] < md[2]) continue; 
            for (int[] dir: DIRECTIONS) {
                int x = md[0] + dir[0];
                int y = md[1] + dir[1];
                int dis = 0;
                while (x >= 0 && y >= 0 && x < M && y < N && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    dis++;
                }
                x -= dir[0];
                y -= dir[1];
                int d = distances[md[0]][md[1]] + dis;
                if (d < distances[x][y]) {
                    distances[x][y] = d;
                    pq.add(new int[]{x, y, d});
                }
            }  
        }
    }

}
