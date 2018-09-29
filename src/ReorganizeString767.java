/**
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result.  If not possible, return the
 * empty string.
 * 
 * Example 1:
 * Input: S = "aab"
 * Output: "aba"
 * 
 * Example 2:
 * Input: S = "aaab"
 * Output: ""
 * 
 * Note:
 * S will consist of lowercase letters and have length in range [1, 500].
 */

public class ReorganizeString767 {
    public String reorganizeString(String S) {
        int[] hash = new int[26];
        for(char c : S.toCharArray()) hash[c-'a']++;
        int max = 0;
        for(int i = 0; i<26; ++i){
            if(hash[i] > hash[max]) max = i;
        }
        if(hash[max] > (S.length()+1)/2) return "";
        char[] res = new char[S.length()];
        int ptr = 0; 
        while(hash[max]-- > 0){
            res[ptr] = (char)(max + 'a');
            ptr += 2;
        }

        for(int i = 0; i<26; ++i){
            while(hash[i]-- > 0){
                if(ptr >= S.length()) ptr = 1;
                res[ptr] = (char)(i + 'a');
                ptr += 2;
            }
        }

        return new String(res);
    }


    /**
     * https://leetcode.com/problems/reorganize-string/solution/
     */
    public String reorganizeString2(String S) {
        int N = S.length();
        int[] count = new int[26];
        for (char c: S.toCharArray()) count[c-'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
            a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i) if (count[i] > 0) {
            if (count[i] > (N + 1) / 2) return "";
            pq.add(new MultiChar(count[i], (char) ('a' + i)));
        }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) pq.add(mc1);
            if (--mc2.count > 0) pq.add(mc2);
        }

        if (pq.size() > 0) ans.append(pq.poll().letter);
        return ans.toString();
    }

    class MultiChar {
        int count;
        char letter;
        MultiChar(int ct, char ch) {
            count = ct;
            letter = ch;
        }
    }

    // use 358. Rearrange String k Distance Apart
    public String reorganizeString3(String S) {
        return rearrangeString(S, 2);
    }

    public String rearrangeString(String str, int k) {
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
     * https://leetcode.com/problems/reorganize-string/discuss/113451/7-ms-Java-O(n)-Solution.-no-Sorting
     */
    public String reorganizeString4(String S) {
        int n = S.length();
        int[] cnt = new int[128];
        char mc = 'a';
        for (char c : S.toCharArray()) {
            cnt[c]++;
            mc = (cnt[c] > cnt[mc]) ? c : mc;
        }
        if (cnt[mc] == 1) {
            return S;
        }
        if (n - cnt[mc] <= cnt[mc] - 2) {
            return "";
        }
        StringBuilder[] sb = new StringBuilder[cnt[mc]];
        for (int i = 0; i < sb.length; i ++) {
            sb[i] = new StringBuilder();
            sb[i].append(mc);
        }
        int k = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            while (c != mc && cnt[c] > 0) {
                sb[k++].append(c);
                cnt[c]--;
                k %= sb.length;
            }
        }
        for (int i = 1; i < sb.length; i++) {
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }

}
