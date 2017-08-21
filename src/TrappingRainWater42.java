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
    public int trap(int[] height) {
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
    public int trap(int[] A){
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

}
