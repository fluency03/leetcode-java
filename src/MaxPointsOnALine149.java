/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */


public class MaxPointsOnALine149 {
    /**
     * Fail: [[0,0],[94911151,94911150],[94911152,94911151]]
     */
    public int maxPoints(Point[] points) {
        if (points.length <= 2) return points.length;

        int len = points.length;
        int maxP = 0;
        for (int i=0; i<len-1; i++) {
            Map<Double, Integer> lines = new HashMap<>();
            Point p1 = points[i];
            int same = 0;
            int localMax = 0;
            for (int j=i+1; j<len; j++) {
                Point p2 = points[j];
                if (pointsEqual(p1, p2)) {
                    same += 1;
                    continue;
                }
                Double k = getSlope(p1, p2);
                Integer ps = lines.getOrDefault(k, 0) + 1;
                localMax = Math.max(localMax, ps);
                lines.put(k, ps);
            }
            maxP = Math.max(maxP, localMax + same + 1);
        }

        return maxP;
    }

    private Double getSlope(Point p1, Point p2) {
        if (p1.y == p2.y) return 0.0;
        if (p1.x == p2.x) return Double.POSITIVE_INFINITY;
        return (double) (p1.y - p2.y) / (double) (p1.x - p2.x);
    }

    private boolean pointsEqual(Point p1, Point p2) {
        return (p1.y == p2.y) && (p1.x == p2.x);
    }



    public int maxPoints2(Point[] points) {
        if (points.length <= 2) return points.length;

        int len = points.length;
        int maxP = 0;
        for (int i=0; i<len-1; i++) {
            Map<String, Integer> lines = new HashMap<>();
            Point p1 = points[i];
            int same = 0;
            int localMax = 0;
            for (int j=i+1; j<len; j++) {
                Point p2 = points[j];
                if (pointsEqual(p1, p2)) {
                    same += 1;
                    continue;
                }
                int dx = p1.x - p2.x;
                int dy = p1.y - p2.y;
                int d = gcd(dx, dy);
                dx /= d;
                dy /= d;
                String k = String.valueOf(dx) + String.valueOf(dy);
                Integer ps = lines.getOrDefault(k, 0) + 1;
                localMax = Math.max(localMax, ps);
                lines.put(k, ps);
            }
            maxP = Math.max(maxP, localMax + same + 1);
        }

        return maxP;
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }



}
