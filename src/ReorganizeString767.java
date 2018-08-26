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

}
