/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * 
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * Input: [3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */

public class MaximumXOROfTwoNumbersInAnArray421 {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        for (int n: nums) trie.add(n);
        
        int res = Integer.MIN_VALUE;
        for (int n: nums) {
            int d = trie.search(n);
            if ((n ^ d) > res) res = n ^ d;
        }
        return Math.max(res, 0);
    }

    class Trie {
        Trie zero;
        Trie one;
        Integer num;

        void add(int n) {
            add(n, 31);
        }

        void add(int n, int i) {
            if (i == -1) {
                num = n;
                return;
            }
            int k = (n >> i) & 1;
            if (k == 0) {
                if (zero == null) zero = new Trie();
                zero.add(n, i-1);
            } else {
                if (one == null) one = new Trie();
                one.add(n, i-1);
            }
        }

        int search(int n) {
            return search(n, 31);
        }

        int search(int n, int i) {
            if (i == -1) {
                return num;
            }
            int k = (n >> i) & 1;
            if (zero == null) return one.search(n, i-1);
            if (one == null) return zero.search(n, i-1);
            if (k == 0) return one.search(n, i-1);
            return zero.search(n, i-1);
        }
    }


    /**
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
     */
    public int findMaximumXOR2(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public int findMaximumXOR3(int[] nums){
        int N = nums.length;
        if (N == 1) return 0;
        int res = Integer.MIN_VALUE;
        for (int i=0; i<N-1; i++) {
            for (int j=i+1; j<N; j++) {
                int newRes = nums[i] ^ nums[j];
                if (newRes > res) res = newRes;
            }
        }
        return Math.max(res, 0);
    }

}
