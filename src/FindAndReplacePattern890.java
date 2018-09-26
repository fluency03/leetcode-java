/**
 * You have a list of words and a pattern, and you want to know which words in
 * words matches the pattern.
 * 
 * A word matches the pattern if there exists a permutation of letters p so
 * that after replacing every letter x in the pattern with p(x), we get the
 * desired word.
 * 
 * (Recall that a permutation of letters is a bijection from letters to
 * letters: every letter maps to another letter, and no two letters map to
 * the same letter.)
 * 
 * Return a list of the words in words that match the given pattern. 
 * 
 * You may return the answer in any order.
 * 
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
 * since a and b map to the same letter.
 * 
 * Note:
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 */

public class FindAndReplacePattern890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        char[] pat = pattern.toCharArray();
        int N = pattern.length();
        for (String word: words) {
            if (isPermutation(word.toCharArray(), pat, N)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean isPermutation(char[] word, char[] pattern, int N) {
        Map<Character, Character> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            if (map.containsKey(word[i])) {
                if (map.get(word[i]) != pattern[i]) return false;
            } else {
                if (map.values().contains(pattern[i])) return false;
                map.put(word[i], pattern[i]);
            }
        }
        return true;
    }


    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        int code = encode(pattern);
        for (String w: words) {
            if (code == encode(w)) {
                res.add(w);
            }
        }
        return res;
    }

    public int encode(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        char first = chars[0];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int i = 1;
        for (int j=0; j<chars.length; j++) {
            int offset = chars[j] - first;
            if (map.containsKey(offset)) {
                res += map.get(offset) * j;
            } else {
                map.put(offset, i);
                res += i * j;
                i++;
            }
        }
        return res;
    }

}
