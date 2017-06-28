/**
 * The Hamming distance (https://en.wikipedia.org/wiki/Hamming_distance) between
 * two integers is the number of positions at which the corresponding bits are different.
 *
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 *
 * Example:
 * Input: 4, 14, 2
 *
 * Output: 6
 *
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is
 * 0010 (just showing the four bits relevant in this case). So the answer will
 * be: HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2)
 * = 2 + 2 + 2 = 6.
 *
 * Note:
 *      Elements of the given array are in the range of 0 to 10^9
 *      Length of the array will not exceed 10^4.
 */


public class TotalHammingDistance477 {
    /**
     * https://discuss.leetcode.com/topic/72092/java-o-n-time-o-1-space
     */
    public int totalHammingDistance2(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }


    /**
     * https://discuss.leetcode.com/topic/72104/java-solution-with-explanation
     */
    public int totalHammingDistance2(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }

}
