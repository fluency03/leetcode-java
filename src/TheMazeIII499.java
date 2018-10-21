/**
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up (u), down (d), left (l) or right (r),
 * but it won't stop rolling until hitting a wall. When the ball stops, it
 * could choose the next direction. There is also a hole in this maze. The
 * ball will drop into the hole if it rolls on to the hole.
 * 
 * Given the ball position, the hole position and the maze, find out how the
 * ball could drop into the hole by moving the shortest distance. The distance
 * is defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the hole (included). Output the moving directions
 * by using 'u', 'd', 'l' and 'r'. Since there could be several different
 * shortest ways, you should output the lexicographically smallest way. If
 * the ball cannot reach the hole, output "impossible".
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The ball and the hole coordinates are represented by row and column indexes.
 * 
 * Example 1:
 * Input 1: a maze represented by a 2D array
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
 * 
 * Output: "lul"
 * 
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically
 * smaller because 'l' < 'u'. So the output is "lul".
 * 
 * Example 2:
 * Input 1: a maze represented by a 2D array
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 * 
 * Output: "impossible"
 * 
 * Explanation: The ball cannot reach the hole.
 * 
 * Note:
 * There is only one ball and one hole in the maze.
 * Both the ball and hole exist on an empty space, and they will not be at the
 * same position initially.
 * The given maze does not contain border (like the red rectangle in the
 * example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and the width and the height
 * of the maze won't exceed 30.
 */

public class TheMazeIII499 {
    private int[][] DIRECTIONS = new int[][]{{1,0}, {0,-1}, {0,1}, {-1,0}};
    private char[] LETTERS = new char[]{'d', 'l', 'r', 'u'};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int M = maze.length;
        int N = maze[0].length;
        int[][] distance = new int[M][N];
        for (int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[ball[0]][ball[1]] = 0;
        StringBuilder sb = new StringBuilder();
        String[] res = new String[1];
        int[] dis = new int[]{Integer.MAX_VALUE};
        roll(maze, ball[0], ball[1], hole[0], hole[1], distance, sb, M, N, res, dis);
        return res[0] == null ? "impossible" : res[0];
    }

    private void roll(int[][] maze, int i, int j, int hi, int hj, int[][] distance, StringBuilder sb, int M, int N, String[] res, int[] dis) {
        int len = sb.length();
        for (int k=0; k<4; k++) {
            int[] dir = DIRECTIONS[k];
            char ch = LETTERS[k];
            sb.append(ch);
            int x = i + dir[0];
            int y = j + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < M && y < N && maze[x][y] == 0) {
                if (x == hi && y == hj) break;
                x += dir[0];
                y += dir[1];
                count++;
            }
            int d = distance[i][j] + count;
            if (x == hi && y == hj) {
                if (d < dis[0]) {
                    dis[0] = d;
                    res[0] = sb.toString();
                } else if (d == dis[0]) {
                    String tmp = sb.toString();
                    if (res[0] == null || tmp.compareTo(res[0]) < 0) {
                        res[0] = tmp;
                    }
                }
                sb.setLength(len);
                continue;
            }
            x -= dir[0];
            y -= dir[1];
            if (d < distance[x][y]) {
                distance[x][y] = d;
                roll(maze, x, y, hi, hj, distance, sb, M, N, res, dis);
            }
            sb.setLength(len);
        }
    }



}
