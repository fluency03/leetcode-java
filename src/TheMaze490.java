/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, determine
 * whether the ball could stop at the destination.
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
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * https://leetcode.com/static/images/problemset/maze_1_example_1.png
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
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 * https://leetcode.com/static/images/problemset/maze_1_example_2.png
 * 
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not
 * be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example
 * pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of
 * the maze won't exceed 100.
 */

public class TheMaze490 {
    private boolean[][] visited;
      
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        visited = new boolean[maze.length][maze[0].length];
        if (hasPath(maze, start, destination, Direction.Left) ||
            hasPath(maze, start, destination, Direction.Right) ||
            hasPath(maze, start, destination, Direction.Top) ||
            hasPath(maze, start, destination, Direction.Down)) return true;
        
        return false;
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination, Direction d) {
        visited[start[0]][start[1]] = true;
        int[] now = new int[]{start[0], start[1]};
        Direction origin = d;
        while (true) {
            if (d == Direction.Left) {
                int nextLeft = now[1] - 1;
                if (nextLeft < 0 || maze[now[0]][nextLeft] == 1) break;
                now[1] = nextLeft;
                // System.out.println(tuple(now));
                origin = Direction.Right;
            } else if (d == Direction.Right) {
                int nextRight = now[1] + 1;
                if (nextRight >= maze[0].length || maze[now[0]][nextRight] == 1) break;
                now[1] = nextRight;
                origin = Direction.Left;
            } else if (d == Direction.Top) {
                int nextTop = now[0] - 1;
                if (nextTop < 0 || maze[nextTop][now[1]] == 1) break;
                now[0] = nextTop;
                origin = Direction.Down;
            } else {
                int nextDown = now[0] + 1;
                if (nextDown >= maze.length || maze[nextDown][now[1]] == 1) break;
                now[0] = nextDown;
                origin = Direction.Top;
            }
        }
        
        if (now[0] == destination[0] && now[1] == destination[1]) return true;
        if (visited[now[0]][now[1]]) return false;
        for (Direction nextDir: Direction.values()) {
            if (nextDir != origin && hasPath(maze, now, destination, nextDir)) return true;
        }
        visited[start[0]][start[1]] = false;
        
        return false;
    }
    
    public enum Direction {
        Left, Right, Top, Down
    }


    /**
     * https://leetcode.com/problems/the-maze/discuss/126353/Straight-forward-Java-DFS-recursive-solution
     */
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        int n=maze.length;
        int m=maze[0].length;
        boolean[][] visited = new boolean[n][m];
        return dfs(maze, start[0], start[1], destination, visited);
    }
    
    public boolean dfs(int[][] maze, int i, int j, int[] dest, boolean[][] visited) {
        int n=maze.length;
        int m=maze[0].length;
        if (dest[0]==i && dest[1]==j) return true; 
        visited[i][j]=true; 
        
        int x=i;
        int y=j;
        if (x-1>=0 && maze[x-1][y]==0) {
            while (x-1>=0 && maze[x-1][y]==0) x--;       
            if (!visited[x][y] && dfs(maze, x, y, dest, visited)) return true;
        }
        
        x=i;
        y=j;
        if (x+1<n && maze[x+1][y]==0) {
            while (x+1<n && maze[x+1][y]==0) x++;         
            if (!visited[x][y] && dfs(maze, x, y, dest, visited)) return true;
        }
        
        x=i;
        y=j;
        if (y-1>=0 && maze[x][y-1]==0) {
            while (y-1>=0 && maze[x][y-1]==0) y--; 
            if (!visited[x][y] && dfs(maze, x, y, dest, visited)) return true;
        }
        
        x=i;
        y=j;
        if (y+1<m && maze[x][y+1]==0) {
            while (y+1<m && maze[x][y+1]==0) y++; 
            if (!visited[x][y] && dfs(maze, x, y, dest, visited)) return true;
        }
        return false;
    }


    public boolean hasPath3(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 || start == null || destination == null) return false;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        int M = maze.length;
        int N = maze[0].length;
        boolean[][] visited = new boolean[M][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == destination[0] && curr[1] == destination[1]) return true;
            addNextSteps(maze, q, visited, curr, M, N);
        }
        
        return false;
    }
    
    private void addNextSteps(int[][] maze, Queue<int[]> q, boolean[][] visited, int[] curr, int M, int N) {
        int i = curr[0];
        while (i >= 0 && maze[i][curr[1]] == 0) {
            i--;
        }
        if (!visited[i+1][curr[1]]) {
            q.add(new int[]{i+1, curr[1]});
            visited[i+1][curr[1]] = true;
        }
        
        i = curr[0];
        while (i < M && maze[i][curr[1]] == 0) {
            i++;
        }
        if (!visited[i-1][curr[1]]) {
            q.add(new int[]{i-1, curr[1]});
            visited[i-1][curr[1]] = true;
        }
        
        i = curr[1];
        while (i >= 0 && maze[curr[0]][i] == 0) {
            i--;
        }
        if (!visited[curr[0]][i+1]) {
            q.add(new int[]{curr[0], i+1});
            visited[curr[0]][i+1] = true;
        }
        
        
        i = curr[1];
        while (i < N && maze[curr[0]][i] == 0) {
            i++;
        }
        if (!visited[curr[0]][i-1]) {
            q.add(new int[]{curr[0], i-1});
            visited[curr[0]][i-1] = true;
        }
    }

    /**
     * https://leetcode.com/problems/the-maze/solution/
     */
    public boolean hasPath4(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            if (s[0] == destination[0] && s[1] == destination[1])
                return true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }


}


