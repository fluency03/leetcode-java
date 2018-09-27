/**
 * Given string S and a dictionary of words words, find the number of words[i]
 * that is a subsequence of S.
 * 
 * Example :
 * Input: 
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of
 * S: "a", "acd", "ace".
 * 
 * Note:
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 */

public class NumberOfMatchingSubsequences792 {

    // public int numMatchingSubseq(String S, String[] words) {
    //     int res = 0;
    //     for (String w: words) {
    //         if (isSubsequence(w, S)) res++;
    //     }
    //     return res;
    // }

    // private boolean isSubsequence(String s, String t) {
    //     int i = 0;
    //     int j = 0;
    //     char[] chars = s.toCharArray();
    //     char[] chart = t.toCharArray();
    //     while (i < chars.length && j < chart.length) {
    //         if (chars[i] == chart[j]) {
    //             i++;
    //             j++;
    //         } else {
    //             j++;
    //         }
    //     }
    //     return i == chars.length;
    // }


    public int numMatchingSubseq2(String S, String[] words) {
        LinkedList<Mover>[] map = new LinkedList[26];
        for (char c = 'a'; c <= 'z'; c++) {
            map[c-'a'] = new LinkedList<Mover>();
        }
        for (String word : words) {
            map[word.charAt(0)-'a'].addLast(new Mover(word));
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            LinkedList<Mover> ll = map[c-'a'];
            int size = ll.size();
            for (int i = 0; i < size; i++) {
                Mover m = ll.removeFirst();
                m.move();
                if (m.hasEnded()) {
                    count++;
                } else {
                    map[m.currChar()-'a'].addLast(m);
                }
            }
        }
        return count;
    }
    
    class Mover {
        char[] word;
        int pos = 0;
        Mover (String w) {
            this.word = w.toCharArray();
        }
        char currChar() {
            return this.word[pos];
        }
        void move() {
            this.pos++;
        }
        boolean hasEnded() {
            return this.word.length == pos;
        }
    }

}
