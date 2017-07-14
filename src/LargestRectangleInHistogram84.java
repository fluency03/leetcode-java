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
    /**
     * https://discuss.leetcode.com/topic/7599/o-n-stack-based-java-solution
     */
    public int largestRectangleArea(int[] height) {
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
     * https://discuss.leetcode.com/topic/39151/5ms-o-n-java-solution-explained-beats-96
     */
    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }


    /**
     * https://discuss.leetcode.com/topic/2424/my-modified-answer-from-geeksforgeeks-in-java
     */
    public int largestRectangleArea3(int[] height) {
        if (height==null) return 0;//Should throw exception
        if (height.length==0) return 0;

        Stack<Integer> index= new Stack<Integer>();
        index.push(-1);
        int max=0;

        for  (int i=0;i<height.length;i++){
                //Start calculate the max value
            while (index.peek()>-1)
                if (height[index.peek()]>height[i]){
                    int top=index.pop();
                    max=Math.max(max,height[top]*(i-1-index.peek()));
                }else break;

            index.push(i);
        }
        while(index.peek()!=-1){
        	int top=index.pop();
            max=Math.max(max,height[top]*(height.length-1-index.peek()));
        }
        return max;
    }


    /**
     * https://discuss.leetcode.com/topic/39836/share-my-2ms-java-solution-beats-100-java-submissions
     */
    public int largestRectangleArea4(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return getMax(heights, 0, heights.length);
    }
    private int getMax(int[] heights, int s, int e) {
        if (s + 1 >= e) return heights[s];
        int min = s;
        boolean sorted = true;
        for (int i = s; i < e; i++) {
            if (i > s && heights[i] < heights[i - 1]) sorted = false;
            if (heights[min] > heights[i]) min = i;
        }
        if (sorted) {
            int max = 0;
            for (int i = s; i < e; i++) {
                max = Math.max(max, heights[i] * (e - i));
            }
            return max;
        }
        int left = (min > s) ? getMax(heights, s, min) : 0;
        int right = (min < e - 1) ? getMax(heights, min + 1, e) : 0;
        return Math.max(Math.max(left, right), (e - s) * heights[min]);
    }
}
