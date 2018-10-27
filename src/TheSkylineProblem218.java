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
        int[][] verts = new int[N * 2][4];
        int t = 0;
        int f = 0;
        for (int[] b: buildings) {
            verts[t++] = new int[]{b[0], b[2], L, f};
            verts[t++] = new int[]{b[1], b[2], R, f};
            f++;
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

        Comparator<int[]> comp2 = (v1, v2) -> Integer.compare(v2[1], v1[1]);
        List<int[]> hs = new ArrayList<>();
        for (int[] vi: verts) {
            int xi = vi[0];
            int hi = vi[1];
            int Di = vi[2];
            int flag = vi[3];

            Collections.sort(hs, comp2);
            if (Di == L) { // L
                if (hs.isEmpty() || hs.get(0)[1] < hi) {
                    res.add(new int[]{xi, hi});
                }
                hs.add(vi);
            } else { // R
                int size = hs.size();
                for (int i=0; i<size; i++) {
                    if (hs.get(i)[3] == flag) {
                        hs.remove(i);
                        break;
                    }
                }
                if (hs.isEmpty() || hs.get(0)[1] < hi) {
                    int y = hs.isEmpty() ? 0 : hs.get(0)[1];
                    res.add(new int[]{xi, y});
                }
            }
        }
        return res;
    }










}
