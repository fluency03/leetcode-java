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
    // brute force
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


    /**
     * https://leetcode.com/problems/next-greater-element-ii/discuss/130338/Why-need-to-use-a-stack-for-index
     */
    public int[] nextGreaterElements4(int[] nums) {
        Stack<Integer> st = new Stack<>();
        for (int e = nums.length -1; e >= 0; e --) {
            st.push(nums[e]);
        }
        int[] r = new int[nums.length];
        for (int i = nums.length -1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i]) st.pop();
            r[i] = st.isEmpty() ? -1 : st.peek();
            st.push(nums[i]);
        }
        return r;
    }


    /**
     * https://leetcode.com/problems/next-greater-element-ii/discuss/98276/21ms-Java-Solution-beats-99.84-with-comments-and-explanations
     */
    public int[] nextGreaterElements5(int[] nums) {
        if (nums==null||nums.length==0) return new int[0];
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;  //find the largest element
            }
        }  
        int[] index = new int[nums.length]; //declare an array that holds the index of next greater element
        index[maxIndex] = -1; //set the max element's value to -1
        for (int i = (maxIndex - 1 + nums.length) % nums.length; i != maxIndex; i = (i - 1 + nums.length) % nums.length) { //the array is circular. pay attention to 'i'
            if (nums[i] < nums[(i + 1) % nums.length]) {
                index[i] = (i + 1) % nums.length; //set index[i] = (i+1)%nums.length if index[(i+1)%nums.length]>index[i]
            } else {
                int res = index[(i + 1 + nums.length) % nums.length]; //res = index of the cloest element whose value greater than nums[(i+1)%nums.length]
                while (res != -1 && index[res] != -1 && nums[i] >= nums[res]) {  //find the closet index makes nums[index] > nums[i]
                    res = index[res]; //if nums[i] >= nums[res], try nums[index[res]], index[res] is the index of the closest element whose value is greater than nums[res]. repeat the process until we find such an element. 
                }
                if (res != -1 && nums[res] == nums[i]) { //res==-1 or index[res]==-1 means current element is another largest element, just set index[i] = -1.
                    res = -1;
                }
                index[i] = res;
            }
        }
        int[] result = new int[nums.length]; // retrieve value with index recorded previously
        for (int i = 0; i < result.length; i++) {
            int temp = index[i] != -1 ? nums[index[i]] : -1;
            result[i] = temp;
        }
        return result;
    }


    public int[] nextGreaterElements6(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};

        int len = nums.length;
        int[] idx = new int[len];
        Arrays.fill(idx, -1);
        for (int i=2*len-1; i>=0; i--) {
            int nextIdx = (i + 1) % len;
            while (idx[nextIdx] != -1 && nums[nextIdx] <= nums[i % len]) {
                nextIdx = idx[nextIdx];
            }
            if (nums[nextIdx] > nums[i % len]) {
                idx[i % len] = nextIdx;
            }
        }
        
        int[] res = new int[len];
        for (int i=0; i<len; i++) {
            if (idx[i] == -1) {
                res[i] = -1;
            } else {
                res[i] = nums[idx[i]];
            }
        }
        return res;
    }

}