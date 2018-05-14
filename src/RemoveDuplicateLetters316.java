/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every
 * letter appear once and only once. You must make sure your result is the smallest in
 * lexicographical order among all possible results.
 * 
 * Example:
 * Given "bcabc"
 * Return "abc"
 * 
 * Given "cbacdcbc"
 * Return "acdb"
 */

public class RemoveDuplicateLetters316 {

    /**
     * https://leetcode.com/problems/remove-duplicate-letters/discuss/76766/Easy-to-understand-iterative-Java-solution
     */
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        Map<Character, Integer> lastPosMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosMap.put(s.charAt(i), i);
        }

        char[] result = new char[lastPosMap.size()];
        int begin = 0, end = findMinLastPos(lastPosMap);

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int k = begin; k <= end; k++) {
                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    begin = k+1;
                }
            }

            result[i] = minChar;
            if (i == result.length-1) break;

            lastPosMap.remove(minChar);
            if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
        }

        return new String(result);
    }

    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : lastPosMap.values()) {
             minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }


    /**
     * https://leetcode.com/problems/remove-duplicate-letters/discuss/76762/Java-O(n)-solution-using-stack-with-detail-explanation
     */
    public String removeDuplicateLetters2(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for(char c : arr) {
            count[c-'a']++;
        }
        boolean[] visited = new boolean[26];
        for(char c : arr) {
            count[c-'a']--;
            if(visited[c-'a']) {
                continue;
            }
            while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) {
                visited[stack.peek()-'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c-'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

}