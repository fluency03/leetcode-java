/**
 * Given a time represented in the format "HH:MM", form the next closest time
 * by reusing the current digits. There is no limit on how many times a digit
 * can be reused.
 * 
 * You may assume the given input string is always valid. For example, "01:34",
 * "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * Example 1:
 * 
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39,
 * which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours
 * and 59 minutes later.
 * 
 * Example 2:
 * 
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is
 * smaller than the input time numerically.
 */


public class NextClosestTime681 {
    public String nextClosestTime(String time) {
        int hours = Integer.valueOf(time.substring(0, 2));
        int mins = Integer.valueOf(time.substring(3, 5));
        int allmins = hours * 60 + mins;
        boolean[] set = new boolean[10];
        for (char c: time.toCharArray()) {
            if (c != ':') {
                set[c - '0'] = true;
            }
        }
        
        while (true) {
            allmins = (allmins + 1) % (24 * 60);
            int currHours = allmins / 60;
            int a = currHours / 10;
            if (!set[a]) continue;
            int b = currHours % 10;
            if (!set[b]) continue;
            int currMins = allmins % 60;
            int c = currMins / 10;
            if (!set[c]) continue;
            int d = currMins % 10;
            if (!set[d]) continue;
            return String.format("%d%d:%d%d", a, b, c, d);
        }
    }


    /**
     * https://leetcode.com/problems/next-closest-time/solution/
     */
    public String nextClosestTime2(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        start += Integer.parseInt(time.substring(3));
        int ans = start;
        int elapsed = 24 * 60;
        Set<Integer> allowed = new HashSet();
        for (char c: time.toCharArray()) if (c != ':') {
            allowed.add(c - '0');
        }

        for (int h1: allowed) for (int h2: allowed) if (h1 * 10 + h2 < 24) {
            for (int m1: allowed) for (int m2: allowed) if (m1 * 10 + m2 < 60) {
                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                int candElapsed = Math.floorMod(cur - start, 24 * 60);
                if (0 < candElapsed && candElapsed < elapsed) {
                    ans = cur;
                    elapsed = candElapsed;
                }
            }
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }


}
