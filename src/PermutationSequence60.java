/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, we get the
 * following sequence for n = 3:
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * 
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 * 
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 */

public class PermutationSequence60 {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[1];
        boolean[] used = new boolean[n];
        helper(sb, n, k, count, used);
        return sb.toString();
    }

    private boolean helper(StringBuilder sb, int n, int k, int[] count, boolean[] used) {
        if (sb.length() == n) {
            count[0]++;
            return count[0] == k;
        }
        for (int i=0; i<n; i++) {
            if (used[i]) continue;
            sb.append((char) (i + '1'));
            used[i] = true;
            if (helper(sb, n, k, count, used)) return true;
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
        return count[0] == k;
    }


    public String getPermutation2(int n, int k) {
        int[] factors = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (i == 0) {
                factors[0] = 1;
            } else {
                factors[i] = factors[i-1] * i;
            }
            list.add(i+1);
        }
        StringBuilder sb = new StringBuilder();
        k--;
        for (int i=n; i>0; i--) {
            int idx = k / factors[i - 1];
            k = k % factors[i - 1];
            int val = list.get(idx);
            list.remove(idx);
            sb.append(val);
        }
        return sb.toString();
    }

}
