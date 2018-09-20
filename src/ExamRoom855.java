/**
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 * 
 * When a student enters the room, they must sit in the seat that maximizes the
 * distance to the closest person.  If there are multiple such seats, they sit
 * in the seat with the lowest number.  (Also, if no one is in the room, then
 * the student sits at seat number 0.)
 * 
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat()
 * returning an int representing what seat the student sat in, and
 * ExamRoom.leave(int p) representing that the student in seat number p now
 * leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have
 * a student sitting in seat p.
 * 
 * Example 1:
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * 
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student​​​​​​​ sits at the last seat number 5.
​​​​​​​ * 
 * Note:
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */


public class ExamRoom855 {
    class ExamRoom {
        private PriorityQueue<Range> pq;
        private Map<Integer, Range> lefts;
        private Map<Integer, Range> rights;
        private int N;

        public ExamRoom(int N) {
            this.pq = new PriorityQueue(1, new Comparator<Range>() {
                @Override
                public int compare(Range r1, Range r2) {
                    int rangeDiff = Integer.compare(dis(r2), dis(r1));
                    if (rangeDiff != 0) return rangeDiff;
                    return Integer.compare(r1.left, r2.left);
                }
            });
            this.lefts = new HashMap<>();
            this.rights = new HashMap<>();
            this.N = N;
            
            Range first = new Range(-1, N);
            this.pq.add(first);
            this.lefts.put(first.left, first);
            this.rights.put(first.right, first);
        }

        public int seat() {
            if (this.pq.isEmpty()) return -1;
            Range curr = this.pq.poll();
            this.lefts.remove(curr.left);
            this.rights.remove(curr.right);

            int pos = sitPos(curr);
            Range l = new Range(curr.left, pos);
            this.lefts.put(l.left, l);
            this.rights.put(l.right, l);
            if (curr.left + 1 < pos) {
                this.pq.add(l);
            }

            Range r = new Range(pos, curr.right);
            this.lefts.put(r.left, r);
            this.rights.put(r.right, r);
            if (pos + 1 < curr.right) {
                this.pq.add(r);
            }
            return pos;
        }

        public void leave(int p) {
            Range r = this.lefts.get(p);
            Range l = this.rights.get(p);
            this.pq.remove(r);
            this.pq.remove(l);

            Range merged = new Range(l.left, r.right);
            this.pq.add(merged);
            this.lefts.put(merged.left, merged);
            this.rights.put(merged.right, merged);
        }

        private int sitPos(Range r) {
            return sitPos(r.left, r.right);
        }

        private int sitPos(int left, int right) {
            if (left + 1 >= right) return -1;
            if (left < 0) return 0;
            if (right >= this.N) return this.N - 1;
            return (right - left) / 2 + left;
        }

        private int dis(Range r) {
            return dis(r.left, r.right);
        }

        private int dis(int left, int right) {
            if (left < 0) return right;
            if (right >= this.N) return this.N - 1 - left;
            return (right - left) / 2;
        }
    }

    class Range {
        int left;
        int right;
        Range (int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */

}
