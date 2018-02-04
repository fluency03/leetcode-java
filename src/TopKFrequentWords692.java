/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 *
 */


public class TopKFrequentWords692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String w: words) {
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        }

        return sortByValue(counts, k);
    }

    private List<String> sortByValue(Map<String, Integer> unsortMap, int k) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        List<String> topK = new ArrayList<>();
        int i = 0;
        while (i < k) {
            topK.add(list.get(i).getKey());
            i++;
        }

        return topK;
    }



    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String w: words) {
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        }

        Map<Integer, List<String>> freqs = new HashMap<>();
        for (Map.Entry<String, Integer> c: counts.entrySet()) {
            List<String> l = freqs.getOrDefault(c.getValue(), new ArrayList<>());
            l.add(c.getKey());
            freqs.put(c.getValue(), l);
        }
        List<Map.Entry<Integer, List<String>>> buckets = sortByKey(freqs);

        List<String> topK = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (j < buckets.size() && i < k) {
            List<String> curr = buckets.get(j).getValue();
            Collections.sort(curr);
            int p = 0;
            while (p < curr.size() && i < k) {
                topK.add(curr.get(p));
                p++;
                i++;
            }
            j++;
        }

        return topK;
    }

    private List<Map.Entry<Integer, List<String>>> sortByKey(Map<Integer, List<String>> unsortMap) {
        List<Map.Entry<Integer, List<String>>> list =
                new LinkedList<Map.Entry<Integer, List<String>>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, List<String>>>() {
            public int compare(Map.Entry<Integer, List<String>> o1, Map.Entry<Integer, List<String>> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });

        return list;
    }



    /**
     * https://discuss.leetcode.com/topic/107751/my-simple-java-solution-using-hashmap-priorityqueue-o-nlogk-time-o-n-space
     */
    public List<String> topKFrequent3(String[] words, int k) {

        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++)
        {
            if(map.containsKey(words[i]))
                map.put(words[i], map.get(words[i])+1);
            else
                map.put(words[i], 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                 (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for(Map.Entry<String, Integer> entry: map.entrySet())
        {
            pq.offer(entry);
            if(pq.size()>k)
                pq.poll();
        }

        while(!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }


    /**
     * https://discuss.leetcode.com/topic/108675/java-hashmap-maxheap-o-nlogn
     */
    public List<String> topKFrequent4(String[] words, int k) {
        HashMap<String, Integer > map = new HashMap<>();
        for (String s : words)  map.put(s, map.getOrDefault(s,0) + 1);  // Frequent hashmap

        PriorityQueue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>(k, (a,b) ->
            a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue());
        // if same frequency, then sort alphabetical .

        for (Map.Entry<String,Integer> entry : map.entrySet() ) maxHeap.add(entry);

        List<String> res = new ArrayList<>();
        while (res.size() < k) res.add(maxHeap.poll().getKey());  //add top k
        return res;
    }


    /**
     * https://discuss.leetcode.com/topic/107069/java-o-n-solution-using-hashmap-bucketsort-and-trie-22ms-beat-81
     */
    public List<String> topKFrequent5(String[] words, int k) {
        // calculate frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        for(String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        // build the buckets
        TrieNode[] count = new TrieNode[words.length + 1];
        for(String word : freqMap.keySet()) {
            int freq = freqMap.get(word);
            if(count[freq] == null) {
                count[freq] = new TrieNode();
            }
            addWord(count[freq], word);
        }
        // get k frequent words
        List<String> list = new LinkedList<>();
        for(int f = count.length - 1; f >= 1 && list.size() < k; f--) {
            if(count[f] == null) continue;
            getWords(count[f], list, k);
        }
        return list;
    }

    private void getWords(TrieNode node, List<String> list, int k) {
        if(node == null) return;
        if(node.word != null) {
            list.add(node.word);
        }
        if(list.size() == k) return;
        for(int i = 0; i < 26; i++) {
            if(node.next[i] != null) {
                getWords(node.next[i], list, k);
            }
        }
    }

    private boolean addWord(TrieNode root, String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()) {
            if(curr.next[c - 'a'] == null) {
                curr.next[c - 'a'] = new TrieNode();
            }
            curr = curr.next[c - 'a'];
        }
        curr.word = word;
        return true;
    }

    class TrieNode {
        TrieNode[] next;
        String word;
        TrieNode() {
            this.next = new TrieNode[26];
            this.word = null;
        }
    }


}
