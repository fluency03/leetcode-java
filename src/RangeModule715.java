/**
 * A Range Module is a module that tracks ranges of numbers. Your task is to
 * design and implement the following interfaces in an efficient manner.
 * 
 * - addRange(int left, int right) Adds the half-open interval [left, right),
 * tracking every real number in that interval. Adding an interval that
 * partially overlaps with currently tracked numbers should add any numbers in
 * the interval [left, right) that are not already tracked.
 * - queryRange(int left, int right) Returns true if and only if every real
 * number in the interval [left, right) is currently being tracked.
 * - removeRange(int left, int right) Stops tracking every real number
 * currently being tracked in the interval [left, right).
 * 
 * Example 1:
 * addRange(10, 20): null
 * removeRange(14, 16): null
 * queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 * queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are
 * not being tracked)
 * queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked,
 * despite the remove operation)
 * 
 * Note:
 * A half open interval [left, right) denotes all real numbers left <= x < right.
 * 0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
 * The total number of calls to addRange in a single test case is at most 1000.
 * The total number of calls to queryRange in a single test case is at most 5000.
 * The total number of calls to removeRange in a single test case is at most 1000.
 */


public class RangeModule715 {
    class RangeModule {
        private TreeMap<Integer, Integer> ranges;

        public RangeModule() {
            ranges = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Integer l = left;
            Integer r = right;
            Integer floor = ranges.floorKey(l);
            if (floor != null && l <= ranges.get(floor)) {
                if (r <= ranges.get(floor)) return;
                l = floor;
                r = Math.max(ranges.get(floor), r);
                ranges.remove(floor);
            }

            Integer higher = ranges.higherKey(l);
            while (higher != null && higher <= r) {
                r = Math.max(ranges.get(higher), r);
                ranges.remove(higher);
                higher = ranges.higherKey(l);
            }
           ranges.put(l, r);
        }

        public boolean queryRange(int left, int right) {
            Integer floor = ranges.floorKey(left);
            return floor != null && ranges.get(floor) >= right;
        }

        public void removeRange(int left, int right) {
            Integer l = left;
            Integer r = right;
            Map.Entry<Integer, Integer> lower = ranges.lowerEntry(l);
            if (lower != null && l < lower.getValue()) {
                ranges.remove(lower.getKey());
                ranges.put(lower.getKey(), l);
                if (lower.getValue() > r) {
                    ranges.put(r, lower.getValue());
                    return;
                } else if (lower.getValue() == r) {
                    return;
                } else {
                    l = lower.getValue();
                }
            }

            Map.Entry<Integer, Integer> ceiling = ranges.ceilingEntry(l);
            while (ceiling != null && ceiling.getKey() <= r) {
                ranges.remove(ceiling.getKey());
                if (ceiling.getValue() > r) {
                    ranges.put(r, ceiling.getValue());
                    return;
                }
                ceiling = ranges.ceilingEntry(l);
            }
        }
    }


    class RangeModule2 {
        private TreeSet<Range> ranges = new TreeSet<>((r1, r2) -> Integer.compare(r1.left, r2.left));
        public RangeModule() {
        }

        public void addRange(int left, int right) {
            Range newRange = new Range(left, right);
            Range floor = ranges.floor(newRange);
            if (floor != null && floor.right >= left) {
                if (floor.right >= right) return;
                newRange.left = floor.left;
                newRange.right = Math.max(floor.right, right);
                ranges.remove(floor);
            }

            while (true) {
                Range ceiling = ranges.ceiling(newRange);
                if (ceiling == null || ceiling.left > newRange.right) break;
                newRange.right = Math.max(ceiling.right, newRange.right);
                ranges.remove(ceiling);
            }
            ranges.add(newRange);
        }

        public boolean queryRange(int left, int right) {
            Range floor = ranges.floor(new Range(left, right));
            if (floor == null) return false;
            return floor.right >= right;
        }

        public void removeRange(int left, int right) {
            Range toBeRemoved = new Range(left, right);
            Range floor = ranges.floor(toBeRemoved);
            if (floor != null && floor.right >= left) {
                int fRight = floor.right;
                floor.right = left;
                if (fRight == right) return;
                if (fRight > right) {
                    Range add = new Range(right, fRight);
                    ranges.add(add);
                    return;
                }
            }

            while (true) {
                Range ceiling = ranges.ceiling(toBeRemoved);
                if (ceiling == null || ceiling.left >= toBeRemoved.right) break;
                ranges.remove(ceiling);
                if (ceiling.right > toBeRemoved.right) {
                    ceiling.left = toBeRemoved.right;
                    ranges.add(ceiling);
                    break;
                }
            }
        }

        class Range {
            int left;
            int right;
            Range (int l, int r) {
                left = l;
                right = r;
            }
        }
    }


/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */

}
