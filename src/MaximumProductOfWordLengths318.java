/**
 * Given a string array words, find the maximum value of
 * length(word[i]) * length(word[j]) where the two words do not share common
 * letters. You may assume that each word will contain only lower case letters.
 * If no such two words exist, return 0.
 * 
 * Example 1:
 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16 
 * Explanation: The two words can be "abcw", "xtfn".
 * 
 * Example 2:
 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4 
 * Explanation: The two words can be "ab", "cd".
 * 
 * Example 3:
 * Input: ["a","aa","aaa","aaaa"]
 * Output: 0 
 * Explanation: No such pair of words.
 */

public class MaximumProductOfWordLengths318 {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;
        int len = words.length;
        int[] map = new int[len];
        for (int i=0; i<len; i++) map[i] = bits(words[i]);
        
        int max = 0;
        for (int i=0; i<len; i++) {
            String a = words[i];
            int bitsA = map[i];
            for (int j=i+1; j<len; j++) {
                String b = words[j];
                int bitsB = map[j];
                if ((bitsA & bitsB) == 0) {
                    max = Math.max(max, a.length() * b.length());
                }
            }
        }
        
        return max;
    }
    
    private int bits(String word) {
        int res = 0;
        for (char c: word.toCharArray()) {
            res |= 1 << (c - 'a');
        }
        return res;
    }

}
