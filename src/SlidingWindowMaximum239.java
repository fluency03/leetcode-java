/**
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Example:
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7] 
 * Explanation: 
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for
 * non-empty array.
 * 
 * Follow up:
 * Could you solve it in linear time?
 */


public class SlidingWindowMaximum239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
        for (int i=0; i<k; i++) pq.add(nums[i]);
        int[] res = new int[nums.length - k + 1];
        for (int i=k; i<nums.length; i++) {
            res[i-k] = pq.peek();
            pq.remove(nums[i-k]);
            pq.add(nums[i]);
        }
        res[res.length-1] = pq.peek();
        return res;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        LinkedList<int[]> ll = new LinkedList<int[]>();
        for (int i=0; i<k; i++) {
            if (ll.isEmpty()) {
                ll.addLast(new int[]{i, nums[i]});
            } else {
                while (!ll.isEmpty() && ll.getLast()[1] <= nums[i]) {
                    ll.removeLast();
                }
                ll.addLast(new int[]{i, nums[i]});
            }
        }
        int[] res = new int[nums.length - k + 1];
        for (int i=k; i<nums.length; i++) {
            int[] head = ll.getFirst();
            res[i-k] = head[1];
            if (head[0] <= i - k) {
                ll.removeFirst();
            }
            while (!ll.isEmpty() && ll.getLast()[1] <= nums[i]) {
                ll.removeLast();
            }
            ll.addLast(new int[]{i, nums[i]});
        }
        res[res.length-1] = ll.getFirst()[1];
        return res;
    }


    /**
     * https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array
     */
    public int[] maxSlidingWindow3(int[] in, int w) {
        if (in == null || in.length == 0) return new int[0];
        int[] max_left = new int[in.length];
        int[] max_right = new int[in.length];
    
        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];
    
        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);
    
            int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }
    
        int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }
    
        return sliding_max;
    }

}
