/**
 * There is a garden with N slots. In each slot, there is a flower. The N
 * flowers will bloom one by one in N days. In each day, there will be exactly
 * one flower blooming and it will be in the status of blooming since then.
 * 
 * Given an array flowers consists of number from 1 to N. Each number in the
 * array represents the place where the flower will open in that day.
 * 
 * For example, flowers[i] = x means that the unique flower that blooms at day
 * i will be at position x, where i and x will be in the range from 1 to N.
 * 
 * Also given an integer k, you need to output in which day there exists two
 * flowers in the status of blooming, and also the number of flowers between
 * them is k and these flowers are not blooming.
 * 
 * If there isn't such day, output -1.
 * 
 * Example 1:
 * Input: 
 * flowers: [1,3,2]
 * k: 1
 * Output: 2
 * Explanation: In the second day, the first and the third flower have become blooming.
 * 
 * Example 2:
 * Input: 
 * flowers: [1,2,3]
 * k: 1
 * Output: -1
 * 
 * Note:
 * The given array will be in the range [1, 20000].
 */


public class KEmptySlots683 {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        if (n <= 1) return -1;
        int[] slot2days = new int[n+1];
        for (int i=0; i<n; i++) {
            slot2days[flowers[i]] = i+1;
        }

        int i = n-1;
        int j = n;
        int min = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        while (i > 0) {
            if (slot2days[i] < slot2days[j]) {
                if ((j - i - 1) == k) ans = Math.min(ans, Math.max(slot2days[i], slot2days[j]));
                j--;
                i = j-1;
                min = Integer.MAX_VALUE;
            } else if (slot2days[i] < min && slot2days[i] > slot2days[j]) {
                if ((j - i - 1) == k) ans = Math.min(ans, Math.max(slot2days[i], slot2days[j]));
                min = slot2days[i];
                i--;
            } else {
                i--;
            }
            if ((j - i - 1) > k) {
                j--;
                i = j-1;
                min = Integer.MAX_VALUE;
            }
        }
        
        return ans < Integer.MAX_VALUE ? ans : -1;
    }

    /**
     * https://leetcode.com/problems/k-empty-slots/solution/
     */
    public int kEmptySlots2(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = k+1;

        search: while (right < days.length) {
            for (int i = left+1; i < right; ++i) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    left = i;
                    right = i + k + 1;
                    continue search;
                }
            }
            ans = Math.min(ans, Math.max(days[left], days[right]));
            left = right;
            right = left + k + 1;
        }

        return ans < Integer.MAX_VALUE ? ans : -1;
    }

}
