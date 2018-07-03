/**
 * Given two sentences words1, words2 (each represented as an array of strings),
 * and a list of similar word pairs pairs, determine if two sentences are similar.
 * 
 * For example, words1 = ["great", "acting", "skills"] and
 * words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs
 * are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"],
 * ["skills","talent"]].
 * 
 * Note that the similarity relation is transitive. For example, if "great"
 * and "good" are similar, and "fine" and "good" are similar, then "great" and
 * "fine" are similar.
 * 
 * Similarity is also symmetric. For example, "great" and "fine" being similar
 * is the same as "fine" and "great" being similar.
 * 
 * Also, a word is always similar with itself. For example, the sentences
 * words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though
 * there are no specified similar word pairs.
 * 
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to
 * words2 = ["doubleplus","good"].
 * 
 * Note:
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */

public class SentenceSimilarityII737 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        DisjointSet djs = new DisjointSet(pairs);
        
        int len = words1.length;
        for (int i=0; i<len; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) continue;
            String p1 = djs.find(w1);
            String p2 = djs.find(w2);
            if (p1 == null || p2 == null || !p1.equals(p2)) return false;
        }
        return true;
        
    }

    class DisjointSet {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        
        public DisjointSet(String[][] pairs) {
            for (String[] pair: pairs) {
                if (!parent.containsKey(pair[0])) {
                    parent.put(pair[0], pair[0]);
                    rank.put(pair[0], 0);
                }
                if (!parent.containsKey(pair[1])) {
                    parent.put(pair[1], pair[1]);
                    rank.put(pair[1], 0);
                }
                union(pair[0], pair[1]);
            }
        }

        public String find(String word) {
            if (parent.containsKey(word) && !parent.get(word).equals(word)) {
                parent.put(word, find(parent.get(word)));
            }
            return parent.get(word);
        }
        
        public void union(String w1, String w2) {
            String p1 = find(w1);
            String p2 = find(w2);
            
            int r1 = rank.get(p1);
            int r2 = rank.get(p2);
            if (r1 > r2) {
                parent.put(p2, p1);
            } else if (r1 < r2) {
                parent.put(p1, p2);
            } else {
                parent.put(p1, p2);
                rank.put(p2, r2+1);
            }
        }
        
    }


    /**
     * https://leetcode.com/problems/sentence-similarity-ii/solution/
     */
    public boolean areSentencesSimilarTwo2(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Integer> index = new HashMap();
        int count = 0;
        DSU dsu = new DSU(2 * pairs.length);
        for (String[] pair: pairs) {
            for (String p: pair) if (!index.containsKey(p)) {
                index.put(p, count++);
            }
            dsu.union(index.get(pair[0]), index.get(pair[1]));
        }

        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i], w2 = words2[i];
            if (w1.equals(w2)) continue;
            if (!index.containsKey(w1) || !index.containsKey(w2) ||
                    dsu.find(index.get(w1)) != dsu.find(index.get(w2)))
                return false;
        }
        return true;
    }

    class DSU {
        int[] parent;
        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

}
