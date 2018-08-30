/**
 * Given a grid where each entry is only 0 or 1, find the number of corner
 * rectangles.
 * 
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned
 * rectangle. Note that only the corners need to have the value 1. Also, all
 * four 1s used must be distinct.
 * 
 * Example 1:
 * Input: grid = 
 * [[1, 0, 0, 1, 0],
 *  [0, 0, 1, 0, 1],
 *  [0, 0, 0, 1, 0],
 *  [1, 0, 1, 0, 1]]
 * Output: 1
 * Explanation: There is only one corner rectangle, with corners
 * grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 * 
 * Example 2:
 * Input: grid = 
 * [[1, 1, 1],
 *  [1, 1, 1],
 *  [1, 1, 1]]
 * Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles,
 * and one 3x3 rectangle.
 * 
 * Example 3:
 * Input: grid = 
 * [[1, 1, 1, 1]]
 * Output: 0
 * Explanation: Rectangles must have four distinct corners.
 * 
 * Note:
 * The number of rows and columns of grid will each be in the range [1, 200].
 * Each grid[i][j] will be either 0 or 1.
 * The number of 1s in the grid will be at most 6000.
 */

public class NumberOfCornerRectangles750 {
    public int countCornerRectangles(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int res = 0;
        for (int i=0; i<M-1; i++) {
            for (int j=0; j<N-1; j++) {
                if (grid[i][j] == 0) continue;
                for (int p=i+1; p<M; p++) {
                    if (grid[p][j] == 0) continue;
                    for (int q=j+1; q<N; q++) {
                        if (grid[i][q] == 0) continue;
                        if (grid[p][q] == 1) res++;
                    }
                }
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/number-of-corner-rectangles/solution/
     */
    public int countCornerRectangles2(int[][] grid) {
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row: grid) {
            for (int c1 = 0; c1 < row.length; ++c1) if (row[c1] == 1) {
                for (int c2 = c1+1; c2 < row.length; ++c2) if (row[c2] == 1) {
                    int pos = c1 * 200 + c2;
                    int c = count.getOrDefault(pos, 0);
                    ans += c;
                    count.put(pos, c+1);
                }
            }
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/number-of-corner-rectangles/discuss/110196/short-JAVA-AC-solution-(O(m2-*-n))-with-explanation.
     */
    public int countCornerRectangles3(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = i + 1; j < grid.length; j++) {
                int counter = 0;
                for (int k = 0; k < grid[0].length; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) counter++;
                }
                if (counter > 0) ans += counter * (counter - 1) / 2;
            }
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/number-of-corner-rectangles/discuss/110200/Summary-of-three-solutions-based-on-three-different-ideas
     */
    public int countCornerRectangles4(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                
                for (int q = j + 1; q < n; q++) {
                    if (grid[i][q] == 0) continue;
                    res += dp[j][q]++;
                }
            }
        }
        
        return res;
    }

}
