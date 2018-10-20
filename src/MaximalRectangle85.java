/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 *
 */

public class MaximalRectangle85 {
    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        if (N == 0) return 0;
        int M = matrix[0].length;
        if (M == 0) return 0;

        int[][] left = new int[N+1][M+1];
        int[][] top = new int[N+1][M+1];
        int maxArea = 0;

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (matrix[i-1][j-1] == '1') {
                    left[i][j] = left[i][j-1] + 1;
                    top[i][j] = top[i-1][j] + 1;
                    maxArea = Math.max(maxArea, helper(left, top, i, j));
                }
            }
        }

        return maxArea;
    }

    private int helper(int[][] left, int[][] top, int i, int j) {
        int maxArea = 0;

        int move = 0;
        int minH = top[i][j];
        while (move < left[i][j]) {
            minH = Math.min(minH, top[i][j-move]);
            maxArea = Math.max(maxArea, (move+1) * minH);
            move++;
        }

        return maxArea;
    }


    /**
     * https://discuss.leetcode.com/topic/1634/a-o-n-2-solution-based-on-largest-rectangle-in-histogram
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;
        int cLen = matrix[0].length;    // column length
        int rLen = matrix.length;       // row length
        // height array
        int[] h = new int[cLen+1];
        h[cLen]=0;
        int max = 0;

        for (int row=0;row<rLen;row++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int i=0;i<cLen+1;i++) {
                if (i<cLen)
                    if(matrix[row][i]=='1')
                        h[i]+=1;
                    else h[i]=0;

                if (s.isEmpty()||h[s.peek()]<=h[i])
                    s.push(i);
                else {
                    while(!s.isEmpty()&&h[i]<h[s.peek()]){
                        int top = s.pop();
                        int area = h[top]*(s.isEmpty()?i:(i-s.peek()-1));
                        if (area>max)
                            max = area;
                    }
                    s.push(i);
                }
            }
        }
        return max;
    }


    /**
     * https://discuss.leetcode.com/topic/21772/my-java-solution-based-on-maximum-rectangle-in-histogram-with-explanation
     */
    public int maximalRectangle3(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] height = new int[matrix[0].length];
        for(int i = 0; i < matrix[0].length; i ++){
            if(matrix[0][i] == '1') height[i] = 1;
        }
        int result = largestInLine(height);
        for(int i = 1; i < matrix.length; i ++){
            resetHeight(matrix, height, i);
            result = Math.max(result, largestInLine(height));
        }

        return result;
    }

    private void resetHeight(char[][] matrix, int[] height, int idx){
        for(int i = 0; i < matrix[0].length; i ++){
            if(matrix[idx][i] == '1') height[i] += 1;
            else height[i] = 0;
        }
    }

    public int largestInLine(int[] height) {
        if(height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }


    /**
     * https://discuss.leetcode.com/topic/42761/java-7ms-solution-beats-100-using-largest-rectangle-in-histogram-solved-by-stack-simulation
     */
    public int maximalRectangle4(char[][] matrix) {
        /**
         * idea: using [LC84 Largest Rectangle in Histogram]. For each row
         * of the matrix, construct the histogram based on the current row
         * and the previous histogram (up to the previous row), then compute
         * the largest rectangle area using LC84.
         */
        int m = matrix.length, n;
        if (m == 0 || (n = matrix[0].length) == 0)
            return 0;

        int i, j, res = 0;
        int[] heights = new int[n];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (matrix[i][j] == '0')
                    heights[j] = 0;
                else
                    heights[j] += 1;
            }
            res = Math.max(res, largestRectangleArea(heights));
        }

        return res;
    }

    public int largestRectangleArea(int[] heights) {
        /**
         * idea: scan and store if a[i-1]<=a[i] (increasing), then as long
         * as a[i]<a[i-1], then we can compute the largest rectangle area
         * with base a[j], for j<=i-1, and a[j]>a[i], which is a[j]*(i-j).
         * And meanwhile, all these bars (a[j]'s) are already done, and thus
         * are throwable (using pop() with a stack).
         *
         * We can use an array nLeftGeq[] of size n to simulate a stack.
         * nLeftGeq[i] = the number of elements to the left of [i] having
         * value greater than or equal to a[i] (including a[i] itself). It
         * is also the index difference between [i] and the next index on
         * the top of the stack.
         */
        int n = heights.length;
        if (n == 0)
            return 0;

        int[] nLeftGeq = new int[n]; // the number of elements to the left
                                        // of [i] with value >= heights[i]
        nLeftGeq[0] = 1;

        // preIdx=the index of stack.peek(), res=max area so far
        int preIdx = 0, res = 0;

        for (int i = 1; i < n; i++) {
            nLeftGeq[i] = 1;

            // notice that preIdx = i - 1 = peek()
            while (preIdx >= 0 && heights[i] < heights[preIdx]) {
                res = Math.max(res, heights[preIdx] * (nLeftGeq[preIdx] + i - preIdx - 1));
                nLeftGeq[i] += nLeftGeq[preIdx]; // pop()

                preIdx = preIdx - nLeftGeq[preIdx]; // peek() current top
            }

            if (preIdx >= 0 && heights[i] == heights[preIdx])
                nLeftGeq[i] += nLeftGeq[preIdx]; // pop()
            // otherwise nothing to do

            preIdx = i;
        }

        // compute the rest largest rectangle areas with (indices of) bases
        // on stack
        while (preIdx >= 0 && 0 < heights[preIdx]) {
            res = Math.max(res, heights[preIdx] * (nLeftGeq[preIdx] + n - preIdx - 1));
            preIdx = preIdx - nLeftGeq[preIdx]; // peek() current top
        }

        return res;
    }


    /**
     * https://discuss.leetcode.com/topic/20902/my-solution-on-java-using-dp
     */
    public int maximalRectangle5(char[][] matrix) {
        int area = 0, new_area, r, l;
        if(matrix.length > 0){
            int[] line = new int[matrix[0].length];
            boolean[] is_processed = new boolean[matrix[0].length];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[i].length; j++){
                    if (matrix[i][j] == '1') {
                        line[j]++;
                        is_processed[j] = false;
                    } else {
                        line[j] = 0;
                        is_processed[j] = true;
                    }
                }
                for(int j = 0; j < matrix[i].length; j++){
                    if(is_processed[j]) continue;
                    r = l = 1;
                    while((j + r < line.length)&&(line[j + r] >= line[j])){
                        if(line[j + r] == line[j]) is_processed[j + r] = true;
                        r++;
                    }
                    while((j - l >= 0)&&(line[j - l] >= line[j])) l++;
                    new_area = (r + l - 1)*line[j];
                    if (new_area > area) area = new_area;
                }
            }
        } return area;
    }


    public int maximalRectangle6(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N+1];
        int res = 0;
        for (int i=0; i<M; i++) {
            for (int j=1; j<=N; j++) {
                if (matrix[i][j-1] == '1') {
                    int local = 0;
                    dp[i][j] = dp[i][j-1] + 1;
                    int width = Integer.MAX_VALUE;
                    for (int k=i; k>=0; k--) {
                        width = Math.min(width, dp[k][j]);
                        local = Math.max(local, width * (i - k + 1));
                    }
                    res = Math.max(res, local);
                }
            }
        }
        return res;
    }


    public int maximalRectangle7(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int M = matrix.length;
        int N = matrix[0].length;
        int[] left = new int[N];
        int[] right = new int[N];
        Arrays.fill(right, N);
        int[] height = new int[N];
        int res = 0;
        for (int i=0; i<M; i++) {
            int currLeft = 0;
            int currRight = N;
            for (int j=0; j<N; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            for (int j=0; j<N; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(currLeft, left[j]);
                } else {
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }
            for (int j=N-1; j>=0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(currRight, right[j]);
                } else {
                    right[j] = N;
                    currRight = j;
                }
            }
            for (int j=0; j<N; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        MaximalRectangle85 mr = new MaximalRectangle85();

        char[][] matrix = new char[][]{
            { '0', '1', '1', '0', '1' },
            { '1', '1', '0', '1', '0' },
            { '0', '1', '1', '1', '0' },
            { '1', '1', '1', '1', '0' },
            { '1', '1', '1', '1', '1' },
        };

        System.out.println(mr.maximalRectangle(matrix));
    }

}
