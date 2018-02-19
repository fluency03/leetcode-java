/**
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want
 * to know the minimum number of swaps so that every couple is sitting side by
 * side. A swap consists of choosing any two people, then they stand up and
 * switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1, the
 * couples are numbered in order, the first couple being (0, 1), the second
 * couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row[i] being the value of the
 * person who is initially sitting in the i-th seat.
 *
 * Example 1:
 *
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 *
 * Example 2:
 *
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 *
 * Note:
 * len(row) is even and in the range of [4, 60].
 * row is guaranteed to be a permutation of 0...len(row)-1.
 */


/**
 * https://leetcode.com/problems/couples-holding-hands/discuss/113362/JavaC++-O(N)-solution-using-cyclic-swapping
 */

public class CouplesHoldingHands765 {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[row[i]] = i;

        int count = 0;
        for (int i = 0; i < n; i += 2) {
            int j = row[i] % 2 == 0 ? row[i] + 1 : row[i] - 1;
            if (row[i + 1] != j) {
                swap(row, i + 1, pos[j]);
                swap(pos, row[i + 1], row[pos[j]]);
                count++;
            }
        }
        return count;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
