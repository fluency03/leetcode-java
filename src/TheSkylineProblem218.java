/**
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are
 * given the locations and height of all the buildings as shown on a cityscape
 * photo (Figure A), write a program to output the skyline formed by these
 * buildings collectively (Figure B).
 * 
 * https://leetcode.com/static/images/problemset/skyline2.jpg
 * 
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left
 * and right edge of the ith building, respectively, and Hi is its height. It
 * is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an
 * absolutely flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format
 * of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A
 * key point is the left endpoint of a horizontal line segment. Note that the
 * last key point, where the rightmost building ends, is merely used to mark
 * the termination of the skyline, and always has zero height. Also, the
 * ground in between any two adjacent buildings should be considered part of
 * the skyline contour.
 * 
 * For instance, the skyline in Figure B should be represented as:
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 * Notes:
 * 
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output
 * skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not
 * acceptable; the three lines of height 5 should be merged into one in the
 * final output as such: [...[2 3], [4 5], [12 7], ...]
 */

public class TheSkylineProblem218 {
    private static int L = 0;
    private static int R = 1;
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        int N = buildings.length;
        if (N == 0) return res;
        
        // xi, hi, L/R
        int[][] verts = new int[N * 2][3];
        int t = 0;
        for (int[] b: buildings) {
            verts[t++] = new int[]{b[0], b[2], L};
            verts[t++] = new int[]{b[1], b[2], R};
        }
        Comparator<int[]> comp1 = new Comparator<int[]>() {
            @Override
            public int compare(int[] v1, int[] v2) {
                int xd = Integer.compare(v1[0], v2[0]);
                if (xd != 0) return xd;

                if (v1[2] == L && v2[2] == L) {
                    return Integer.compare(v2[1], v1[1]);
                } else if (v1[2] == R && v2[2] == R) {
                    return Integer.compare(v1[1], v2[1]);
                } else {
                    return Integer.compare(v1[2], v2[2]);
                }
            }
        };
        Arrays.sort(verts, comp1);
        
        Comparator<Integer> comp2 = (h1, h2) -> Integer.compare(h2, h1);
        PriorityQueue<Integer> pq = new PriorityQueue<>(comp2);
        for (int[] vi: verts) {
            int xi = vi[0];
            int hi = vi[1];
            int Di = vi[2];

            if (Di == L) { // L
                if (pq.isEmpty() || pq.peek() < hi) {
                    res.add(new int[]{xi, hi});
                }
                pq.add(hi);
            } else { // R
                pq.remove(hi);
                if (pq.isEmpty() || pq.peek() < hi) {
                    int y = pq.isEmpty() ? 0 : pq.peek();
                    res.add(new int[]{xi, y});
                }
            }
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/the-skyline-problem/discuss/61192/Once-for-all-explanation-with-clean-Java-code(O(n2)time-O(n)-space)
     */
    public List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            // start point has negative height value
            height.add(new int[]{b[0], -b[2]});
            // end point has normal height value
            height.add(new int[]{b[1], b[2]}); 
        }

        // sort $height, based on the first value, if necessary, use the second to
        // break ties
        Collections.sort(height, (a, b) -> {
                if(a[0] != b[0]) 
                    return a[0] - b[0];
                return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        // Provide a initial value to make it more consistent
        pq.offer(0);

        // Before starting, the previous max height is 0;
        int prev = 0;

        // visit all points in order
        for(int[] h:height) {
            if(h[1] < 0) { // a start point, add height
                pq.offer(-h[1]);
            } else {  // a end point, remove height
                pq.remove(h[1]);
            }
            int cur = pq.peek(); // current max height;
      
            // compare current max height with previous max height, update result and 
            // previous max height if necessary
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/the-skyline-problem/discuss/61281/Java-divide-and-conquer-solution-beats-96
     */
    public List<int[]> getSkyline3(int[][] buildings) {
        return merge(buildings, 0, buildings.length-1);
    }

    private LinkedList<int[]> merge(int[][] buildings, int lo, int hi) {
        LinkedList<int[]> res = new LinkedList<>();
        if(lo > hi) {
            return res;
        } else if(lo == hi) {
            res.add(new int[]{buildings[lo][0], buildings[lo][2]});
            res.add(new int[]{buildings[lo][1], 0});
            return res;
        } 
        int mid = lo+(hi-lo)/2;
        LinkedList<int[]> left = merge(buildings, lo, mid);
        LinkedList<int[]> right = merge(buildings, mid+1, hi);
        int leftH = 0, rightH = 0;
        while(!left.isEmpty() || !right.isEmpty()) {
            long x1 = left.isEmpty()? Long.MAX_VALUE: left.peekFirst()[0];
            long x2 = right.isEmpty()? Long.MAX_VALUE: right.peekFirst()[0];
            int x = 0;
            if(x1 < x2) {
                int[] temp = left.pollFirst();
                x = temp[0];
                leftH = temp[1];
            } else if(x1 > x2) {
                int[] temp = right.pollFirst();
                x = temp[0];
                rightH = temp[1];
            } else {
                x = left.peekFirst()[0];
                leftH = left.pollFirst()[1];
                rightH = right.pollFirst()[1];
            }
            int h = Math.max(leftH, rightH);
            if(res.isEmpty() || h != res.peekLast()[1]) {
                res.add(new int[]{x, h});
            }
        }
        return res;
    }

}
