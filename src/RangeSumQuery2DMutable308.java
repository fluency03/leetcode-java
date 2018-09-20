/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner
 * (row2, col2).
 * 
 * Range Sum Query 2D
 * https://leetcode.com/static/images/courses/range_sum_query_2d.png
 * 
 * The above rectangle (with the red border) is defined by
 * (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * 
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * 
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is
 * distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

public class RangeSumQuery2DMutable308 {
    class NumMatrix {
        private Node root;
      
        public NumMatrix(int[][] matrix) {
            if (matrix != null && matrix.length != 0 && matrix[0].length != 0) {
                this.root = constructTree(matrix, 0, 0, matrix.length-1, matrix[0].length-1);
            }
        }
        
        private Node constructTree(int[][] matrix, int row1, int col1, int row2, int col2) {
            if (row1 > row2 || col1 > col2) return null;
            Node res = new Node(row1, col1, row2, col2, matrix[row1][col1]);
            if (row1 == row2 && col1 == col2) return res;
            
            int midRow = (row1 + row2) / 2;
            int midCol = (col1 + col2) / 2;
            Node tl = constructTree(matrix, row1, col1, midRow, midCol);
            Node tr = constructTree(matrix, row1, midCol+1, midRow, col2);
            Node bl = constructTree(matrix, midRow+1, col1, row2, midCol);
            Node br = constructTree(matrix, midRow+1, midCol+1, row2, col2);
            res.tl = tl;
            res.tr = tr;
            res.bl = bl;
            res.br = br;
            res.sum = (tl == null ? 0 : tl.sum) +
                    (tr == null ? 0 : tr.sum) +
                    (bl == null ? 0 : bl.sum) +
                    (br == null ? 0 : br.sum);
            return res;   
        }
        
        public void update(int row, int col, int val) {
            update(this.root, row, col, val);
        }
        
        private void update(Node root, int row, int col, int val) {
            if (root.row1 == root.row2 && root.row1 == row && root.col1 == root.col2 && root.col1 == col) {
                root.sum = val;
                return;
            }
            int rowMid = (root.row1 + root.row2) / 2;
            int colMid = (root.col1 + root.col2) / 2;
            Node next;
            if (row <= rowMid) {
                if (col <= colMid) {
                    next = root.tl;
                } else {
                    next = root.tr;
                }
            } else {
                if (col <= colMid) {
                    next = root.bl;
                } else {
                    next = root.br;
                }
            }
            root.sum -= next.sum;
            update(next, row, col, val);
            root.sum += next.sum;
        }
        
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegion(this.root, row1, col1, row2, col2);
        }
        
        private int sumRegion(Node root, int row1, int col1, int row2, int col2) {
            if (root == null || root.row2 < row1 || root.row1 > row2 || root.col2 < col1 || root.col1 > col2) return 0;
            if (root.row1 >= row1 && root.row2 <= row2 && root.col1 >= col1 && root.col2 <= col2) return root.sum;
            return sumRegion(root.tl, row1, col1, row2, col2) +
                sumRegion(root.tr, row1, col1, row2, col2) +
                sumRegion(root.bl, row1, col1, row2, col2) +
                sumRegion(root.br, row1, col1, row2, col2);
        }
    
        class Node {
            Node tl;
            Node tr;
            Node bl;
            Node br;
            int sum;
            int row1;
            int col1;
            int row2;
            int col2;
            Node (int row1, int col1, int row2, int col2, int sum) {
                this.row1 = row1;
                this.col1 = col1;
                this.row2 = row2;
                this.col2 = col2;
                this.sum = sum;
            }
        }
    }

    /**
     * https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75870/Java-2D-Binary-Indexed-Tree-Solution-clean-and-short-17ms
     */
    // time should be O(log(m) * log(n)) 
    class NumMatrix2 {
        int[][] tree;
        int[][] nums;
        int m;
        int n;

        public NumMatrix2(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m+1][n+1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            int delta = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i <= m; i += i & (-i)) {
                for (int j = col + 1; j <= n; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }
    }


    class NumMatrix3 {
        private int[][] sum;
        private int[][] board;

        public NumMatrix3(int[][] matrix) {
            this.board = matrix;
            if (matrix.length != 0 && matrix[0].length != 0) {
                this.sum = new int[matrix.length][matrix[0].length + 1];
                for (int i=0; i<matrix.length; i++) {
                    for (int j=1; j<=matrix[0].length; j++) {
                        this.sum[i][j] += this.sum[i][j-1] + this.board[i][j-1];
                    }
                }
            }
        }

        public void update(int row, int col, int val) {
            int curr = this.board[row][col];
            int diff = val - curr;
            for (int j=col+1; j<=this.board[0].length; j++) {
                this.sum[row][j] += diff;
            }
            this.board[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int i=row1; i<=row2; i++) {
                res += this.sum[i][col2+1] - this.sum[i][col1];
            }
            return res;
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

}

