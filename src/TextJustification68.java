/**
 * Given an array of words and a width maxWidth, format the text such that each
 * line has exactly maxWidth characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * 
 * Example 1:
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 
 * Example 2:
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 * because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 * 
 * Example 3:
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to",
 * "explain","to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */

import java.util.*;

public class TextJustification68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;
        int N = words.length;
        while (i < N) {
            int spaceLeft = maxWidth;
            int wordsLen = 0;
            List<String> line = new ArrayList<>();
            while (i < N) {
                if (words[i].length() > spaceLeft) break;
                line.add(words[i]);
                wordsLen += words[i].length();
                spaceLeft -= words[i].length() + 1;
                i++;
            }
            StringBuilder sb = new StringBuilder();
            if (i == N || line.size() == 1) {
                res.add(leftJustify(line, maxWidth));
            } else {
                res.add(middleJustify(line, maxWidth, wordsLen));
            }
        }
        return res;
    }

    private String leftJustify(List<String> line, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (String w: line) {
            sb.append(w);
            if (sb.length() < maxWidth) sb.append(' ');
        }
        while (sb.length() < maxWidth) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private String middleJustify(List<String> line, int maxWidth, int wordsLen) {
        StringBuilder sb = new StringBuilder();
        int n = line.size();
        int spaces = maxWidth - wordsLen;
        int interval = spaces / (n-1);
        int rest = spaces - interval * (n-1);
        for (int k=0; k<n; k++) {
            sb.append(line.get(k));
            int p = interval;
            while (p > 0 && sb.length() < maxWidth) {
                sb.append(' ');
                p--;
            }
            if (rest > 0 && sb.length() < maxWidth) {
                sb.append(' ');
                rest--;
            }
        }
        return sb.toString();
    }

}
