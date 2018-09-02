/**
 * This question is the same as "Max Chunks to Make Sorted" except the integers
 * of the given array are not necessarily distinct, the input array could be
 * up to length 2000, and the elements could be up to 10**8.
 * 
 * Given an array arr of integers (not necessarily distinct), we split the
 * array into some number of "chunks" (partitions), and individually sort
 * each chunk.  After concatenating them, the result equals the sorted array.
 * 
 * What is the most number of chunks we could have made?
 * 
 * Example 1:
 * Input: arr = [5,4,3,2,1]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [5, 4], [3, 2, 1] will result in
 * [4, 5, 1, 2, 3], which isn't sorted.
 * 
 * Example 2:
 * Input: arr = [2,1,3,4,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [2, 1], [3, 4, 4].
 * However, splitting into [2, 1], [3], [4], [4] is the highest number of
 * chunks possible.
 * 
 * Note:
 * arr will have length in range [1, 2000].
 * arr[i] will be an integer in range [0, 10**8].
 */

public class MaxChunksToMakeSortedII768 {
    public int maxChunksToSorted(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<arr.length; i++) {
            if (stack.isEmpty() || stack.peek() <= arr[i]) {
                stack.push(arr[i]);
            } else {
                int top = stack.peek();
                while (!stack.isEmpty() && stack.peek() > arr[i]) {
                    stack.pop();
                }
                stack.push(top);
            }
        }
        return stack.size();
    }


    /**
     * https://leetcode.com/problems/max-chunks-to-make-sorted-ii/discuss/113462/Java-solution-left-max-and-right-min.
     */
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
        }

        return res + 1;
    }

}
