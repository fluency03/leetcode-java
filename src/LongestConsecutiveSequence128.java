/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4].
 * Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 *
 */


public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Range> ranges = new HashMap<>();
        Set<Integer> found = new HashSet<>();

        int res = 1;
        for (int i=0; i<nums.length; i++) {
            int val = nums[i];
            if (found.contains(val)) continue;
            found.add(val);
            Range left = ranges.get(val-1);
            Range right = ranges.get(val+1);

            Range newRange = new Range(val, val);
            if (left == null && right == null) {
                ranges.put(val, newRange);
                continue;
            }
            if (left == null) {
                newRange.r = right.r;
                ranges.remove(right.l);
            } else if (right == null) {
                newRange.l = left.l;
                ranges.remove(left.r);
            } else {
                newRange.l = left.l;
                newRange.r = right.r;
                ranges.remove(left.r);
                ranges.remove(right.l);
            }
            ranges.put(newRange.l, newRange);
            ranges.put(newRange.r, newRange);
            res = Math.max(res, newRange.r-newRange.l+1);
        }
        return res;
    }

    class Range {
        int l;
        int r;
        Range (int l0,int r0) { l=l0; r=r0; }
    }

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);

        int res = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) continue;
            if (nums[i] == nums[i-1]+1) {
                temp += 1;
            } else {
                res = Math.max(res, temp);
                temp = 1;
            }
        }

        return Math.max(res, temp);
    }


    public int longestConsecutive3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        int res = 1;
        for (int n : set) {
            if (set.contains(n-1)) continue;
            int curr = n;
            int temp = 1;
            while (set.contains(curr+1)) {
                curr += 1;
                temp += 1;
            }
            res = Math.max(res, temp);
        }

        return res;
    }

}
