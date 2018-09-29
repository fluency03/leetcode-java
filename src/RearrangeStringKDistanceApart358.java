/**
 * Given a non-empty string s and an integer k, rearrange the string such that
 * the same characters are at least distance k from each other.
 * 
 * All input strings are given in lowercase letters. If it is not possible to
 * rearrange the string, return an empty string "".
 * 
 * Example 1:
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc" 
 * Explanation: The same letters are at least distance 3 from each other.
 * 
 * Example 2:
 * Input: s = "aaabc", k = 3
 * Output: "" 
 * Explanation: It is not possible to rearrange the string.
 * 
 * Example 3:
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 */

public class RearrangeStringKDistanceApart358 {
    /**
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83192/Java-7-version-of-PriorityQueue-O(nlogn)-with-comments-and-explanations
     */
    public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        int N = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        Comparator<Map.Entry<Character, Integer>> comp = (e1, e2) -> Integer.compare(e2.getValue(), e1.getValue());
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(1, comp);
        for (Map.Entry<Character, Integer> e: map.entrySet()) {
            pq.add(e);
        }

        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> cache = new LinkedList<>();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> curr = pq.poll();
            sb.append(curr.getKey());
            curr.setValue(curr.getValue() - 1);
            cache.add(curr);
            // intial k-1 chars, cache not full yet
            if (cache.size() < k) continue;
            // release from cache if char is already k apart
            Map.Entry<Character, Integer> next = cache.poll();
            //note that char with 0 count still needs to be placed in cache as a place holder
            if (next.getValue() > 0) {
                pq.add(next);
            }
        }
        return sb.length() == N ? sb.toString() : "";
    }


    public String rearrangeString2(String s, int k) {
        if (k == 0) return s;
        int N = s.length(); 
        int[] map = new int[26];
        for (char ch: s.toCharArray()) {
            map[ch-'a']++;
        }

        Comparator<Character> comp = (c1, c2) -> Integer.compare(map[c2-'a'], map[c1-'a']);
        PriorityQueue<Character> pq = new PriorityQueue<>(1, comp);
        for (char c='a'; c<='z'; c++) {
            if (map[c-'a'] > 0) {
                pq.add(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> cache = new LinkedList<>();
        while (!pq.isEmpty()) {
            char curr = pq.poll();
            sb.append(curr);
            map[curr-'a']--;
            cache.add(curr);
            if (cache.size() < k) continue;
            char next = cache.poll();
            if (map[next-'a'] > 0) {
                pq.add(next);
            }
        }
        return  sb.length() == N ? sb.toString() : "";
    }


    /**
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83193/Java-15ms-Solution-with-Two-auxiliary-array.-O(N)-time.
     */
    public String rearrangeString3(String str, int k) {
        int length = str.length();
        int[] count = new int[26];
        int[] nextIndex = new int[26];
        char[] chars = str.toCharArray();
        for (char ch: chars){
            count[ch-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<length; i++) {
            int idx = findValidMax(count, nextIndex, i);
            if (idx == -1) return "";
            sb.append((char) (idx + 'a'));
            count[idx]--;
            nextIndex[idx] = i+k;
        }
        return sb.toString();
    }

    private int findValidMax(int[] count, int[] nextIndex, int index){
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int i=0; i<26; i++) {
            if (count[i] != 0 && count[i] > max && index >= nextIndex[i]) {
                max = count[i];
                idx = i;
            }
        }
        return idx;
    }


    /**
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83205/Java_solution_in_12_ms-O(N)-time-and-space
     */
    public String rearrangeString4(String str, int k) {
        if (k < 2) return str;
        int[][] times = new int[26][2];
        for(int i = 0; i < 26; i++) times[i][1] = 'a'+i;
        for (int i = 0; i < str.length(); i++) {
            times[str.charAt(i) - 'a'][0]++;
        }
        Comparator<int[]> comp = (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]); 
        Arrays.sort(times, comp);

        int len = str.length(), maxlen = (len + k - 1)/k, count = 0, i = 0;
        if(times[0][0] > maxlen) return "";

        char[] res = new char[len];
        if((count = (len % k)) != 0){
            for(i = 0; i < 26; i++){
                if(times[i][0] < maxlen) break;
                if(i >= count) return "";
                for(int j = i; j < len; j += k) res[j] = (char)times[i][1];
            }
        }

        count = i; maxlen = i;
        for(int j = 25; j >= maxlen; j--){
            for(int t = 0; t < times[j][0]; t++){
                res[count] = (char)times[j][1];
                count += k;
                if(count >= len) count = ++i;
            }
        }
        return new String(res);
    }

}
