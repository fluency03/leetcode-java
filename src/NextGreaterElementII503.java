/**
 * Given a circular array (the next element of the last element is the first
 * element of the array), print the Next Greater Number for every element. The
 * Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly
 * to find its next greater number. If it doesn't exist, output -1 for this
 * number.
 * 
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * 
 * Explanation: The first 1's next greater number is 2; 
 * The number 2 can't find next greater number; 
 * The second 1's next greater number needs to search circularly, which is also 2.
 * 
 * Note: The length of given array won't exceed 10000.
 */

public class NextGreaterElementII503 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        if (nums.length == 1) return new int[]{-1};
        int len = nums.length;
        
        int[] res = new int[len];
        
        int i = 0;        
        while (i < len) {
            int j = (i+1)%len;
            boolean found = false;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    found = true;
                    break;
                }
                j = (j+1)%len;
            }
            if (!found) {
                res[i] = -1;
            }
            i++;
        }
        
        return res;
    }

    public int[] nextGreaterElements2(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        if (nums.length == 1) return new int[]{-1};
        int len = nums.length;
        
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> st = new Stack<>();
        for (int i=0; i<len; i++) {
            while (!st.empty() && nums[i] > nums[st.peek()]) {
                res[st.pop()] = nums[i];
            }
            st.push(i);
        }
        
        for (int i=0; i<len; i++) {
            while (!st.empty() && nums[i] > nums[st.peek()]) {
                int idx = st.pop();
                if (res[idx] == -1) {
                    res[idx] = nums[i];
                }
            }
            st.push(i);
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/next-greater-element-ii/solution/
     */
    public int[] nextGreaterElements3(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

}