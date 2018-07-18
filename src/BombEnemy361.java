/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0'
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted
 * point until it hits the wall since the wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 * 
 * Example:
 * For the given grid
 * 
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * 
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

public class BombEnemy361 {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(killedByBomb(grid, i, j, n, m), res);
                }
            }
        }
        return res;
    }
    
    private int killedByBomb(char[][] grid, int i, int j, int n, int m) {
        int res = 0;
        // right
        for (int k=j+1; k<m && grid[i][k] != 'W'; k++) {
            if (grid[i][k] == 'E') res++;
        }
        
        // left
        for (int k=j-1; k>=0 && grid[i][k] != 'W'; k--) {
            if (grid[i][k] == 'E') res++;
        }
        
        // top
        for (int k=i-1; k>=0 && grid[k][j] != 'W'; k--) {
            if (grid[k][j] == 'E') res++;
        }
        
        // bottom
        for (int k=i+1; k<n && grid[k][j] != 'W'; k++) {
            if (grid[k][j] == 'E') res++;
        }
        
        return res;
    }
  


    private int[][] hori;
    private int[][] vert;
    public int maxKilledEnemies2(char[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        hori = new int[n][m];
        vert = new int[n][m];
        
        int res = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(killedByBomb2(grid, i, j, n, m), res);
                }
            }
        }
        return res;
    }
    
    private int killedByBomb2(char[][] grid, int i, int j, int n, int m) {
        int h = 0;
        boolean hf = false;
        // left
        for (int k=j-1; k>=0 && grid[i][k] != 'W'; k--) {
            if (grid[i][k] == 'E') h++;
            else if (grid[i][k] == '0') {
                h = hori[i][k];
                hf = true;
                break;
            } else {
                break;
            }
        }
        
        if (!hf) {
            // right
            for (int k=j+1; k<m && grid[i][k] != 'W'; k++) {
                if (grid[i][k] == 'E') h++;
            }
        }
        hori[i][j] = h;

        int v = 0;
        boolean vf = false;
        // top
        for (int k=i-1; k>=0 && grid[k][j] != 'W'; k--) {
            if (grid[k][j] == 'E') v++;
            else if (grid[k][j] == '0') {
                v = vert[k][j];
                vf = true;
                break;
            } else {
                break;
            }
        }

        if (!vf) {
            // bottom
            for (int k=i+1; k<n && grid[k][j] != 'W'; k++) {
                if (grid[k][j] == 'E') v++;
            }
        }
        
        vert[i][j] = v;

        return h + v;
    }


    public int maxKilledEnemies3(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int[][] top = new int[m][n];
        int[][] bottom = new int[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] != 'W') {
                    int add = grid[i][j] == 'E' ? 1 : 0;
                    left[i][j] = (j > 0 ? left[i][j-1] : 0) + add;
                    top[i][j] = (i > 0 ? top[i-1][j] : 0) + add;
                }
                if (grid[i][n-j-1] != 'W') {
                    right[i][n-j-1] = (j > 0 ? right[i][n-j] : 0) + (grid[i][n-j-1] == 'E' ? 1 : 0);
                }
                if (grid[m-i-1][j] != 'W') {
                    bottom[m-i-1][j] = (i > 0 ? bottom[m-i][j] : 0) + (grid[m-i-1][j] == 'E' ? 1 : 0);
                }
            }
        }

        int res = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '0') {
                    int local = left[i][j] + right[i][j] + top[i][j] + bottom[i][j];
                    if (local > res) {
                        res = local;
                    }
                }
            }
        }
        return res;
    }


    public int maxKilledEnemies4(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int rowKills = 0;
        int[] colKills = new int[n];
        
        int res = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (j == 0 || grid[i][j-1] == 'W') {
                    int tempRow = 0;
                    for (int k=j; k<n && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            tempRow++;
                        }
                    }
                    rowKills = tempRow;
                }
                
                if (i == 0 || grid[i-1][j] == 'W') {
                    int tempCol = 0;
                    for (int k=i; k<m && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            tempCol++;
                        }
                    }
                    colKills[j] = tempCol;
                }
                
                if (grid[i][j] == '0' && rowKills + colKills[j] > res) {
                    res = rowKills + colKills[j];
                }
            }
        }
        return res;
    }

}
