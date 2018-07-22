/**
 * Given a rows x cols screen and a sentence represented by a list of non-empty
 * words, find how many times the given sentence can be fitted on the screen.
 * 
 * Note:
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word is greater than 0 and won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 * 
 * Example 1:
 * Input:
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 * Output: 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 * 
 * Example 2:
 * Input:
 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * Output: 2
 * Explanation:
 * a-bcd- 
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen.
 * 
 * Example 3:
 * Input:
 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * Output: 1
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 * The character '-' signifies an empty space on the screen.
 */

public class SentenceScreenFitting418 {
    /**
     * https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }


    /**
     * https://leetcode.com/problems/sentence-screen-fitting/discuss/90846/JAVA-optimized-solution-17ms
     */
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        int[] nextIndex = new int[sentence.length];
        int[] times = new int[sentence.length];
        for(int i=0;i<sentence.length;i++) {
            int curLen = 0;
            int cur = i;
            int time = 0;
            while(curLen + sentence[cur].length() <= cols) {
                curLen += sentence[cur++].length()+1;
                if(cur==sentence.length) {
                    cur = 0;
                    time ++;
                }
            }
            nextIndex[i] = cur;
            times[i] = time;
        }
        int ans = 0;
        int cur = 0;
        for(int i=0; i<rows; i++) {
            ans += times[cur];
            cur = nextIndex[cur];
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/sentence-screen-fitting/discuss/90874/12ms-Java-solution-using-DP
     */
    public int wordsTyping3(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that 
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
            dp[i] = prev;
        }
        int count = 0;
        for(int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it. 
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }


    public int wordsTyping4(String[] sentence, int rows, int cols) {
        char[] words = (String.join(" ", sentence) + " ").toCharArray();
        int len = words.length;
        int[] marks = new int[len];
        int j = 0;
        for (int i=0; i<len; i++) {
            if (words[i] == ' ') j = i + 1;
            marks[i] = j;
        }
        int row = 0;
        int idx = 0;
        while (row < rows) {
            idx += cols;
            idx = (idx / len) * len + marks[idx % len];
            row++;
        }
        return idx / len;
    }

}
