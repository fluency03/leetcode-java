/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */


public class MergeIntervals56 {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        Collections.sort(intervals, new SortByLeft());

        List<Interval> res = new ArrayList<>();
        Interval acc = intervals.get(0);
        int i=1;
        while (i < intervals.size()) {
            Interval curr = intervals.get(i);
            if (curr.start > acc.end) {
                res.add(acc);
                acc = curr;
            } else {
                acc = new Interval(acc.start, Math.max(acc.end, curr.end));
            }
            i++;
        }
        res.add(acc);

        return res;
    }

    class SortByLeft implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/38628/beat-98-java-sort-start-end-respectively
     */
    public List<Interval> merge2(List<Interval> intervals) {
      	// sort start&end
      	int n = intervals.size();
      	int[] starts = new int[n];
      	int[] ends = new int[n];
      	for (int i = 0; i < n; i++) {
        		starts[i] = intervals.get(i).start;
        		ends[i] = intervals.get(i).end;
      	}
      	Arrays.sort(starts);
      	Arrays.sort(ends);
      	// loop through
      	List<Interval> res = new ArrayList<Interval>();
      	for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
        		if (i == n - 1 || starts[i + 1] > ends[i]) {
          			res.add(new Interval(starts[j], ends[i]));
          			j = i + 1;
        		}
      	}
      	return res;
    }


}
