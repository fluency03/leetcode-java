/**
 * Given a sorted integer array where the range of elements are in the
 * inclusive range [lower, upper], return its missing ranges.
 *
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return
 * ["2", "4->49", "51->74", "76->99"].
 */

public class MissingRanges163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();

        long mark = (long) lower;
        for (int i=0; i<nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            long n = (long) nums[i];
            if (n > mark) {
                String s = String.valueOf(mark);
                res.add((mark == n-1) ? s : s + "->" + String.valueOf(n-1));
            }
            mark = n+1;
        }
        if (mark == upper) res.add(String.valueOf(mark));
        else if (mark < upper) res.add(String.valueOf(mark) + "->" + String.valueOf(upper));
        return res;
    }

}
