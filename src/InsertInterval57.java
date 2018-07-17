/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class InsertInterval57 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        int start = newInterval.start;
        int end = newInterval.end;

        List<Interval> result = new ArrayList<>();
        boolean newAdded = false;

        for (Interval in: intervals) {
            if (in.end < start) {
                result.add(in);
            } else if (in.start > end) {
                if (!newAdded) {
                    result.add(new Interval(start, end));
                    newAdded = true;
                }
                result.add(in);
            } else {
                start = Math.min(in.start, start);
                end = Math.max(in.end, end);
            }
        }

        if (!newAdded) {
            result.add(new Interval(start, end));
        }

        return result;
    }


    public static void main(String[] args) {
        InsertInterval57 ii = new InsertInterval57();
        System.out.println(ii.insert(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(6, 9))), new Interval(2, 5)));
    }


}
