/**
 * A gene string can be represented by an 8-character long string,
 * with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"),
 * where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations.
 * A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the
 * minimum number of mutations needed to mutate from "start" to "end". If there
 * is no such a mutation, return -1.
 *
 * Note:
 *
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 *
 * Example 1:
 *
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * return: 1
 *
 * Example 2:
 *
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * return: 2
 *
 * Example 3:
 *
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * return: 3
 */


public class MinimumGeneticMutation433 {
    public int minMutation(String start, String end, String[] bank) {
        int len = bank.length;
        boolean[] marked = new boolean[len];
        Integer res = helper(start, end, bank, len, marked, 0, Integer.MAX_VALUE);
        return res.equals(Integer.MAX_VALUE) ? -1 : res;
    }

    private int helper(String start, String end, String[] bank, int len, boolean[] marked, int step, Integer res) {
        for (Integer i=0; i<len; i++) {
            if (!marked[i] && diff(start, bank[i]) == 1) {
                if (bank[i].equals(end)) {
                    return Math.min(res, step+1);
                }
                marked[i] = true;
                int found = helper(bank[i], end, bank, len, marked, step+1, res);
                res = Math.min(res, found);
                marked[i] = false;
            }
        }

        return res;
    }

    private Integer diff(String a, String b) {
        int d = 0;
        for (int i=0; i<8; i++) {
            if (a.charAt(i) != b.charAt(i)) d++;
        }
        return d;
    }

}
