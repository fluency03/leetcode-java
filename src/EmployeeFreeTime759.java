/**
 * We are given a list schedule of employees, which represents the working time
 * for each employee.
 * 
 * Each employee has a list of non-overlapping Intervals, and these intervals
 * are in sorted order.
 * 
 * Return the list of finite intervals representing common, positive-length
 * free time for all employees, also in sorted order.
 * 
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * 
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * (Even though we are representing Intervals in the form [x, y], the objects
 * inside are Intervals, not lists or arrays. For example,
 * schedule[0][0].start = 1, schedule[0][0].end = 2, and
 * schedule[0][0][0] is not defined.)
 * 
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have
 * zero length.
 * 
 * Note:
 * schedule and schedule[i] are lists with lengths in range [1, 50].
 * 0 <= schedule[i].start < schedule[i].end <= 10^8.
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

public class EmployeeFreeTime759 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) return new ArrayList<>();
        LinkedList<Integer> starts = new LinkedList<>();
        LinkedList<Integer> ends = new LinkedList<>();
        for (List<Interval> emp: schedule) {
            for (Interval inv: emp) {
                starts.add(inv.start);
                ends.add(inv.end);
            }
        }
        Collections.sort(starts);
        Collections.sort(ends);
        List<Interval> res = new ArrayList<>();
        int count = 0;
        int time = Integer.MIN_VALUE;
        while (!starts.isEmpty() && !ends.isEmpty()) {
            if (starts.getFirst() <= ends.getFirst()) {
                count++;
                time = starts.getFirst();
                starts.removeFirst();
            } else {
                count--;
                time = ends.getFirst();
                ends.removeFirst();
            }

            if (count == 0 && !starts.isEmpty() && time < starts.getFirst()) {
                res.add(new Interval(time, starts.getFirst()));
            }
        }
        return res;
    }



    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) return new ArrayList<>();
        Comparator<Interval> comp = (i1, i2) -> Integer.compare(i1.start ,i2.start);
        PriorityQueue<Interval> pq = new PriorityQueue<>(1, comp);
        for (List<Interval> emp: schedule) {
            for (Interval inv: emp) {
                pq.add(inv);
            }
        }
        List<Interval> res = new ArrayList<>();
        if (pq.isEmpty()) return res;
        int maxEnd = pq.poll().end;
        while (!pq.isEmpty()) {
            Interval curr = pq.poll();
            if (curr.start <= maxEnd) {
                maxEnd = Math.max(maxEnd, curr.end);
            } else {
                res.add(new Interval(maxEnd, curr.start));
                maxEnd = curr.end;
            }
        }
        return res;
    }

}
