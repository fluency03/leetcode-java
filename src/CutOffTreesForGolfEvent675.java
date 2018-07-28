/**
 * You are asked to cut off trees in a forest for a golf event. The forest is
 * represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 *
 * The place with number bigger than 1 represents a tree can be walked through,
 * and this positive number represents the tree's height.
 *
 * You are asked to cut off all the trees in this forest in the order of tree's
 * height - always cut off the tree with lowest height first. And after cutting,
 * the original place has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps
 * you need to walk to cut off all the trees. If you can't cut off all the trees,
 * output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at
 * least one tree needs to be cut off.
 *
 * Example 1:
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,4],
 *  [7,6,5]
 * ]
 * Output: 6
 *
 * Example 2:
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,0],
 *  [7,6,5]
 * ]
 * Output: -1
 *
 * Example 3:
 * Input:
 * [
 *  [2,3,4],
 *  [0,0,5],
 *  [8,7,6]
 * ]
 * Output: 6
 *
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 *
 * Hint: size of the given matrix will not exceed 50x50.
 *
 */


public class CutOffTreesForGolfEvent675 {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;
        PriorityQueue<TreePoint> pq = new PriorityQueue<>(1, Comparator.comparingInt(p -> p.h));
        int M = forest.size();
        int N = forest.get(0).size();
        int[][] golf = new int[M][N];
        int i = 0;
        for (List<Integer> level: forest) {
            int j = 0;
            for (Integer pos: level) {
                if (pos > 1) {
                    pq.add(new TreePoint(i, j, pos));
                }
                golf[i][j] = pos;
                j++;
            }
            i++;
        }

        Point pre = new Point(0, 0);
        int res = 0;
        
        while (pq.size() > 0) {
            TreePoint t = pq.poll();
            Point dest = new Point(t.x, t.y);
            int d = minDistance(golf, pre, dest, M, N);
            if (d == -1) return -1;
            res += d;
            golf[t.x][t.y] = 1;
            pre = dest;
        }
        
        return res;
    }

    private int minDistance(int[][] golf, Point start, Point end, int M, int N) {
        if (start.x == end.x && start.y == end.y) return 0;
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        boolean[][] visited = new boolean[M][N];
        visited[start.x][start.y] = true;
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i =0; i<size; i++) {
                Point curr = q.poll();
                if (curr.x == end.x && curr.y == end.y) return dist;
                for (int[] d: directions) {
                    int x = curr.x + d[0];
                    int y = curr.y + d[1];
                    if (x == end.x && y == end.y) return dist + 1;
                    if (x >= 0 && y >= 0 && x < M && y < N && !visited[x][y] && golf[x][y] != 0) {
                        q.add(new Point(x, y));
                        visited[x][y] = true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }
    
    class Point {
        int x;
        int y;
        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    class TreePoint {
        int x;
        int y;
        int h;
        TreePoint(int xx, int yy, int hh) {
            x = xx;
            y = yy;
            h = hh;
        }
    }



}
