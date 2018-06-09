/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * http://www.leetcode.com/static/images/problemset/rainwatertrap.png
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 */

public class TrappingRainWater42 {

    /**
     * https://leetcode.com/problems/trapping-rain-water/solution/
     */
    // brute force
    public int trap(int[] height) {
        int res = 0;
        for (int i=1; i<height.length-1; i++) {
            int l = 0;
            int r = 0;
            for (int j = i; j >= 0; j--) l = Math.max(l, height[j]);
            for (int j = i; j < height.length; j++) r = Math.max(r, height[j]);
            res += Math.min(l, r) - height[i];
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        Stack<Integer> st = new Stack<>();
        st.push(0);
        int p = 1;

        int sum = 0;
        while(p < height.length) {
            while(!st.empty() && height[p] > height[st.peek()]) {
                Integer now = st.pop();
                if (st.empty()) {
                    break;
                }
                sum += (Math.min(height[p], height[st.peek()]) - height[now]) * (p-st.peek()-1);
            }
            st.push(p++);
        }

        return sum;
    }

    /**
     * https://discuss.leetcode.com/topic/3016/share-my-short-solution
     */
    public int trap3(int[] A){
        int a=0;
        int b=A.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,A[a]);
            rightmax=Math.max(rightmax,A[b]);
            if(leftmax<rightmax){
                max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-A[b]);
                b--;
            }
        }
        return max;
    }


    /**
     * https://leetcode.com/problems/trapping-rain-water/solution/
     */
    // DP
    public int trap4(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int res = 0;
        int[] l = new int[height.length];
        l[0] = height[0];
        for (int i=1; i<height.length; i++) l[i] = Math.max(l[i-1], height[i]);

        int[] r = new int[height.length];
        r[height.length-1] = height[height.length-1];
        for (int i=height.length-2; i>=0; i--) r[i] = Math.max(r[i+1], height[i]);

        for (int i=1; i<height.length-1; i++) res += Math.min(l[i], r[i]) - height[i];

        return res;
    }


    public int trap5(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int res = 0;
        int l = 1;
        int r = 0;
        Stack<Integer> st = new Stack<>();
        while (l < height.length) {
            if (height[l] > height[l-1] && !st.isEmpty()) {
                int top = st.pop();
                res += (l-top-1) * (Math.min(height[l], height[top]) - height[l-1]);

            }

            if (l <= height.length-3 && height[l] > height[l+1]) {
                st.add(l);
            }
            l++;
        }

        return res;
    }

}
