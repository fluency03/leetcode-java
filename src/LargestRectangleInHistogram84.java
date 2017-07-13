/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * https://leetcode.com/static/images/problemset/histogram.png
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * https://leetcode.com/static/images/problemset/histogram_area.png
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 *
 */


public class LargestRectangleInHistogram84 {
    // Too slow for large ALL-1 input
    public int largestRectangleArea(int[] heights) {
        int N = heights.length;
        int maxArea = 0;
        for (int i=0; i<N; i++) {
            System.out.println("-------- " + i);
            int l=i;
            while (l > 0 && heights[i] <= heights[l-1]) {
                System.out.println("l: " + l);
                l--;
            }
            int r=i;
            while (r < N-1 && heights[i] <= heights[r+1]) {
                System.out.println("r: " + r);
                r++;
            }
            System.out.println(maxArea + "; " + (heights[i]*(r-l+1)));
            maxArea = Math.max(maxArea, heights[i]*(r-l+1));
        }
        return maxArea;
    }







}
