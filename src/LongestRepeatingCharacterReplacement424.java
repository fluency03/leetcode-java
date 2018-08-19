/**
 * Given a string that consists of only uppercase English letters, you can
 * replace any letter in the string with another letter at most k times. Find
 * the length of a longest substring containing all repeating letters you can
 * get after performing the above operations.
 * 
 * Note:
 * Both the string's length and k will not exceed 104.
 * 
 * Example 1:
 * Input:
 * s = "ABAB", k = 2
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * 
 * Example 2:
 * Input:
 * s = "AABABBA", k = 1
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */

public class LongestRepeatingCharacterReplacement424 {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        int[] map = new int[26];
        Comparator<Point> comp = (p1, p2) -> Integer.compare(p2.count, p1.count);
        PriorityQueue<Point> pq = new PriorityQueue(26, comp);
        int res = 0;
        int left = 0;
        int right = 0;
        while (right < N) {
            char c = chars[right++];
            pq.remove(new Point(c, map[c-'A']));
            pq.add(new Point(c, map[c-'A'] + 1));
            map[c-'A']++;

            while (right - left - pq.peek().count > k) {
                char leftC = chars[left++];
                pq.remove(new Point(leftC, map[leftC-'A']));
                pq.add(new Point(leftC, map[leftC-'A'] - 1));
                map[leftC-'A']--;
            }
            if (right - left > res) {
                res = right - left;
            }
        }
        
        return res;
    }

    class Point {
        char ch;
        int count;
        Point(char c, int cnt) {
            ch = c;
            count = cnt;
        }
    }


    public int characterReplacement2(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        int[] map = new int[26];
        int res = 0;
        int left = 0;
        int right = 0;
        while (right < N) {
            char c = chars[right++];
            map[c-'A']++;

            while (right - left - getMax(map) > k) {
                char leftC = chars[left++];
                map[leftC-'A']--;
            }
            if (right - left > res) {
                res = right - left;
            }
        }
        
        return res;
    }

    private int getMax(int[] map) {
        int res = 0;
        for (int c: map) {
            if (c > res) {
                res = c;
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
     * 
     * maxCount may be invalid at some points, but this doesn't matter, because
     * it was valid earlier in the string, and all that matters is finding the
     * max window that occurred anywhere in the string. Additionally, it will
     * expand if and only if enough repeating characters appear in the window
     * to make it expand. So whenever it expands, it's a valid expansion.
     */
    public int characterReplacement3(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

}
