/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 *
 * Each rectangle is defined by its bottom left corner and top right corner as
 * shown in the figure.
 *
 * https://leetcode.com/static/images/problemset/rectangle_area.png
 *
 * Rectangle Area
 * Assume that the total area is never beyond the maximum possible value of int.
 *
 */


public class RectangleArea223 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return (int) computeArea((long) A, (long) B, (long) C, (long) D, (long) E, (long) F, (long) G, (long) H);
    }

    public long computeArea(long A, long B, long C, long D, long E, long F, long G, long H) {
        boolean sign = true;
        long t1 = Math.min(G, C) - Math.max(E, A);
        if (t1 < 0) sign = false;
        long t2 = Math.min(H, D) - Math.max(F, B);
        if (t2 < 0 || !sign) sign = false;
        long overlap = t1*t2;
        overlap = (sign) ? overlap : 0;
        long area1 = (D-B)*(C-A);
        long area2 = (H-F)*(G-E);
        return area1+area2-overlap;
    }

}
