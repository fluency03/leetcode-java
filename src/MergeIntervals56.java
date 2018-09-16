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


    public List<Interval> merge2(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        int N = intervals.size();
        if (N == 0) return res;
        int[] starts = new int[N];
        int[] ends = new int[N];
        int i = 0;
        for (Interval inv: intervals) {
            starts[i] = inv.start;
            ends[i] = inv.end;
            i++;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int s = 0;
        for (int e=1; e<N; e++) {
            if (starts[e] > ends[e-1]) {
                res.add(new Interval(starts[s], ends[e-1]));
                s = e;
            }
        }
        res.add(new Interval(starts[s], ends[N-1]));
        return res;
    }


    public List<Interval> merge3(List<Interval> intervals) {
        Comparator<Interval> comp = (in1, in2) -> Integer.compare(in1.start, in2.start);
        Collections.sort(intervals, comp);

        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        Interval tmp = intervals.get(0);
        for (Interval inv: intervals) {
            if (inv.start <= tmp.end) {
                tmp.start = Math.min(tmp.start, inv.start);
                tmp.end = Math.max(tmp.end, inv.end);
            } else {
                res.add(tmp);
                tmp = inv;
            }
        }
        res.add(tmp);
        return res;
    }

}
