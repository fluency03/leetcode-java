/**
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * 
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * 
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * 
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are
 * both in the list.
 */

public class ShortestWordDistance243 {
    public int shortestDistance(String[] words, String word1, String word2) {
        int last1 = -1;
        int last2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i=0; i<words.length; i++) {
            if (words[i].equals(word1)) {
                if (last2 != -1) {
                    res = Math.min(res, i - last2);
                }
                last1 = i;
            } else if (words[i].equals(word2)) {
                if (last1 != -1) {
                    res = Math.min(res, i - last1);
                }
                last2 = i;
            }
        }
        return res;
    }
}
