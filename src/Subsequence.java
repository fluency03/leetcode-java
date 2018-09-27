/**
 * 
 */

import java.util.*;

public class Subsequence {

    public List<String> subsequences(String target, String[] dict) {
        List<String> res = new ArrayList<>();
        for (String word: dict) {
            if (target.length() <= word.length() && isSubsequence(target, word)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        while (i < chars.length && j < chart.length) {
            if (chars[i] == chart[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == chars.length;
    }

    public static void main(String[] args) {
        Insertables ins = new Insertables();
        System.out.println(ins.insertables("google", new String[]{"goooooogle", "ddgoogle", "abcd", "googles"}));
    }

}
