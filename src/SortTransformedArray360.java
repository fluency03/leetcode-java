/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply
 * a quadratic function of the form f(x) = ax2 + bx + c to each element x in
 * the array.
 * 
 * The returned array must be in sorted order.
 * 
 * Expected time complexity: O(n)
 * 
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * 
 * Result: [3, 9, 15, 33]
 * 
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * 
 * Result: [-23, -5, 1, 7]
 */

public class SortTransformedArray360 {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n];
        int i = 0;
        int j = n - 1;
        int index = a >= 0 ? n - 1 : 0;
        while (i <= j) {
            int ii = result(nums[i], a, b, c);
            int jj = result(nums[j], a, b, c);
            if (a >= 0) {
                if (ii >= jj) {
                    res[index] = ii;
                    i++;
                } else {
                    res[index] = jj;
                    j--;
                }
                index--;
            } else {
                if (ii >= jj) {
                    res[index] = jj;
                    j--;
                } else {
                    res[index] = ii;
                    i++;
                }
                index++;
            }
        }
        return res;
    }

    private int result(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }


    public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x: nums) {
            pq.add(transfer(x, a, b, c));
        }
        int[] res = new int[nums.length];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }

    private int transfer(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }

}
