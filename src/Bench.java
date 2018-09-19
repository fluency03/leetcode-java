/**
 * https://www.careercup.com/question?id=6204431937830912
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Bench {
    public static void sitFarestFromPeople(boolean[] bench, int k) {
        if (bench == null || bench.length == 0 || k <= 0) return;
        Comparator<Range> comp = (r1, r2) -> Integer.compare(r2.right - r2.left, r1.right - r1.left);
        PriorityQueue<Range> pq = new PriorityQueue<>(1, comp);
        int L = bench.length;
        int pre = -1;
        for (int i=0; i<L; i++) {
            if (!bench[i]) continue;
            if (pre == -1) {
                pq.add(new Range(-i, i));
            } else if (pre + 1 < i) {
                pq.add(new Range(pre, i));
            }
            pre = i;
        }
        if (pre != -1) {
            pq.add(new Range(pre, (L-1-pre)*2+pre));
        } else {
            pq.add(new Range(-(L-1), L-1));
        }

        for (int j=0; j<k; j++) {
            if (pq.isEmpty()) return;
            Range curr = pq.poll();
            int mid = (curr.right - curr.left) / 2 + curr.left;
            bench[mid] = true;
            if (curr.left >= 0 && curr.left + 1 < mid) {
                pq.add(new Range(curr.left, mid));
            }
            if (curr.right < L && mid + 1 < curr.right) {
                pq.add(new Range(mid, curr.right));
            }
        }
    }

    static class Range {
        int left;
        int right;
        Range (int l, int r) {
            this.left = l;
            this.right = r;
        }
    }


    public static void main(String[] args) {
        boolean[] bench1 = new boolean[]{true, false, false, false, true, false, true};
        sitFarestFromPeople(bench1, 1);
        System.out.println(Arrays.toString(bench1));

        boolean[] bench2 = new boolean[]{true, false, false, false, false, true, false, true};
        sitFarestFromPeople(bench2, 2);
        System.out.println(Arrays.toString(bench2));

        boolean[] bench3 = new boolean[]{false, false, false, true, false, true};
        sitFarestFromPeople(bench3, 2);
        System.out.println(Arrays.toString(bench3));
    }

}
