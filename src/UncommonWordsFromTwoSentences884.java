/**
 * We are given two sentences A and B.  (A sentence is a string of space separated
 * words.  Each word consists only of lowercase letters.)
 * 
 * A word is uncommon if it appears exactly once in one of the sentences, and
 * does not appear in the other sentence.
 * 
 * Return a list of all uncommon words.
 * 
 * You may return the list in any order.
 * 
 * Example 1:
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 * 
 * Example 2:
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 * 
 * Note:
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 */

public class UncommonWordsFromTwoSentences884 {
    public String[] uncommonFromSentences(String A, String B) {
        Set<String> once = new HashSet<>();
        Set<String> mult = new HashSet<>();
        String[] as = A.split(" ");
        for (String s: as) {
            if (once.contains(s)) {
                once.remove(s);
                mult.add(s);
            } else if (!mult.contains(s)) {
                once.add(s);
            }
        }
        
        String[] bs = B.split(" ");
        Set<String> resCand = new HashSet<>();
        Set<String> resMult = new HashSet<>();
        for (String s: bs) {
            if (!once.contains(s) && !mult.contains(s)) {
                if (resCand.contains(s)) {
                    resCand.remove(s);
                    resMult.add(s);
                } else if (!resMult.contains(s)) {
                    resCand.add(s);
                }
            } else if (once.contains(s)) {
                once.remove(s);
                mult.add(s);
            }
        }
        
        String[] res = new String[resCand.size() + once.size()];
        int i = 0;
        for (String c: resCand) {
            res[i++] = c;
        }
        for (String c: once) {
            res[i++] = c;
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/uncommon-words-from-two-sentences/solution/
     */
    public String[] uncommonFromSentences2(String A, String B) {
        Map<String, Integer> count = new HashMap();
        for (String word: A.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);
        for (String word: B.split(" "))
            count.put(word, count.getOrDefault(word, 0) + 1);

        List<String> ans = new LinkedList();
        for (String word: count.keySet())
            if (count.get(word) == 1)
                ans.add(word);

        return ans.toArray(new String[ans.size()]);
    }
}
