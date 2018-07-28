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
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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
            for (int[] d: directions) {
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

}
