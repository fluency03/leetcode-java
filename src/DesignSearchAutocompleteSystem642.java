/**
 * Design a search autocomplete system for a search engine. Users may input a
 * sentence (at least one word and end with a special character '#'). For each
 * character they type except '#', you need to return the top 3 historical hot
 * sentences that have prefix the same as the part of sentence already typed.
 * Here are the specific rules:
 * 
 * The hot degree for a sentence is defined as the number of times a user typed
 * the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is
 * the hottest one). If several sentences have the same degree of hot, you need
 * to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this
 * case, you need to return an empty list.
 * Your job is to implement the following functions:
 * 
 * The constructor function:
 * 
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
 * The input is historical data. Sentences is a string array consists of
 * previously typed sentences. Times is the corresponding times a sentence
 * has been typed. Your system should record these historical data.
 * 
 * Now, the user wants to input a new sentence. The following function will
 * provide the next character the user types:
 * 
 * List<String> input(char c): The input c is the next character typed by the
 * user. The character will only be lower-case letters ('a' to 'z'), blank
 * space (' ') or a special character ('#'). Also, the previously typed
 * sentence should be recorded in your system. The output will be the top 3
 * historical hot sentences that have prefix the same as the part of sentence
 * already typed.
 * 
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
 * The system have already tracked down the following sentences and their corresponding times: 
 * "i love you" : 5 times 
 * "island" : 3 times 
 * "ironman" : 2 times 
 * "i love leetcode" : 2 times 
 * Now, the user begins another search: 
 * 
 * Operation: input('i') 
 * Output: ["i love you", "island","i love leetcode"] 
 * Explanation: 
 * There are four sentences that have prefix "i". Among them, "ironman" and
 * "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r'
 * has ASCII code 114, "i love leetcode" should be in front of "ironman". Also
 * we only need to output top 3 hot sentences, so "ironman" will be ignored. 
 * 
 * Operation: input(' ') 
 * Output: ["i love you","i love leetcode"] 
 * Explanation: 
 * There are only two sentences that have prefix "i ". 
 * 
 * Operation: input('a') 
 * Output: [] 
 * Explanation: 
 * There are no sentences that have prefix "i a". 
 * 
 * Operation: input('#') 
 * Output: [] 
 * Explanation: 
 * The user finished the input, the sentence "i a" should be saved as a
 * historical sentence in system. And the following input will be counted as a
 * new search. 
 * 
 * Note:
 * The input sentence will always start with a letter and end with '#', and
 * only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100. The
 * length of each sentence including those in the historical data won't exceed
 * 100.
 * Please use double-quote instead of single-quote when you write test cases
 * even for a character input.
 * Please remember to RESET your class variables declared in class
 * AutocompleteSystem, as static/class variables are persisted across multiple
 * test cases. Please see here for more details.
 */

public class DesignSearchAutocompleteSystem642 {
    // Trie
    class AutocompleteSystem {
        private Map<String, Integer> freq;
        private StringBuilder currSent;
        private Trie trie;
        private Trie currTrie;

        public AutocompleteSystem(String[] sentences, int[] times) {
            this.freq = new HashMap<>();
            this.trie = new Trie();
            for (int i=0; i<sentences.length; i++) {
                this.freq.put(sentences[i], times[i]);
                this.trie.add(sentences[i]);
            }
            this.currTrie = this.trie;
            this.currSent = new StringBuilder();
        }

        public List<String> input(char c) {
            if (c == '#') {
                String newSent = this.currSent.toString();
                this.currSent = new StringBuilder();
                this.freq.put(newSent, this.freq.getOrDefault(newSent, 0) + 1);
                this.currTrie.sentence = newSent;
                this.currTrie = this.trie;
                return new ArrayList<>();
            } else {
                this.currSent.append(c);
                int pos = charPos(c);
                if (this.currTrie.children[pos] == null) {
                    this.currTrie.children[pos] = new Trie();
                }
                this.currTrie = this.currTrie.children[pos];
                PriorityQueue<String> pq = new PriorityQueue<>(3, (s1, s2) -> {
                    int freqDiff = Integer.compare(this.freq.get(s1), this.freq.get(s2));
                    if (freqDiff != 0) return freqDiff;
                    return -asciiOrder(s1, s2);
                });
                getAllSents(this.currTrie, pq);
                LinkedList<String> res = new LinkedList<>();
                while (!pq.isEmpty()) {
                    res.addFirst(pq.poll());
                }
                return res;
            }
        }

        private void getAllSents(Trie t, PriorityQueue<String> pq) {
            if (t.sentence != null) {
                pq.add(t.sentence);
                if (pq.size() > 3) pq.poll();
            }
            for (Trie child: t.children) {
                if (child != null) {
                    getAllSents(child, pq);
                }
            }
        }

        private int asciiOrder(String s1, String s2) {
            int i = 0;
            int len1 = s1.length();
            int len2 = s2.length();
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();
            while (i < len1 && i < len2) {
                if (chars1[i] == chars2[i]) {
                    i++;
                    continue;
                }
                return Integer.compare(chars1[i], chars2[i]);
            }
            return Integer.compare(len1, len2);
        }

        private int charPos(char ch) {
            if (ch == ' ') return 26;
            return ch - 'a';
        }

        class Trie {
            Trie[] children = new Trie[27];
            String sentence;

            void add(String sen) {
                add(sen.toCharArray(), 0);
            }

            void add(char[] chars, int i) {
                if (i == chars.length) {
                    this.sentence = new String(chars);
                    return;
                }
                int pos = charPos(chars[i]);
                if (this.children[pos] == null) {
                    this.children[pos] = new Trie();
                }
                this.children[pos].add(chars, i+1);
            }
        }
    }


    // Tenary Search Tree
    class AutocompleteSystem2 {
        private StringBuilder sb;
        private Map<String, Integer> freq;
        private Node root;
        private Node currNode;

        Comparator<String> comp = (s1, s2) -> {
            int freqDiff = Integer.compare(this.freq.get(s1), this.freq.get(s2));
            if (freqDiff != 0) return freqDiff;
            return -s1.compareTo(s2);
        };

        public AutocompleteSystem2(String[] sentences, int[] times) {
            int N = sentences.length;
            this.freq = new HashMap<>();
            for (int i=0; i<N; i++) {
                this.freq.put(sentences[i], times[i]);
                this.root = addNode(this.root, sentences[i]);
            }
            this.currNode = this.root;
            this.sb = new StringBuilder();
        }

        private Node addNode(Node n, String s) {
            return addNode(n, s.toCharArray(), 0);
        }

        private Node addNode(Node n, char[] chars, int i) {
            if (i == chars.length) {
                return n;
            }
            char ch = chars[i];
            if (n == null) {
                n = new Node(ch);
            }

            if (ch < n.ch) {
                n.left = addNode(n.left, chars, i);
            } else if (ch > n.ch) {
                n.right = addNode(n.right, chars, i);
            } else {
                if (i+1 < chars.length) {
                    n.eq = addNode(n.eq, chars, i+1);
                } else {
                    n.sent = new String(chars);
                }
            }
            return n;
        }

        public List<String> input(char c) {
            if (c == '#') {
                String newSent = this.sb.toString();
                this.sb = new StringBuilder();
                this.freq.put(newSent, this.freq.getOrDefault(newSent, 0) + 1);
                this.root = addNode(this.root, newSent);
                this.currNode = this.root;
                return new ArrayList<>();
            } else {
                this.sb.append(c);
                if (this.currNode == null) {
                    return new ArrayList<>();
                }
                Node nn = next(this.currNode, c);
                this.currNode = nn;
                PriorityQueue<String> pq = new PriorityQueue<>(3, comp);
                if (nn != null) {
                    this.currNode = nn.eq;
                    if (nn.sent != null) {
                        pq.add(nn.sent);
                    }
                    nn = nn.eq;
                }
                getAllSents(nn, pq);
                LinkedList<String> res = new LinkedList<>();
                while (!pq.isEmpty()) {
                    res.addFirst(pq.poll());
                }
                return res;
            }
        }

        private Node next(Node n, char c) {
            if (n == null) return null;
            if (c < n.ch) {
                return next(n.left, c);
            } else if (c > n.ch) {
                return next(n.right, c);
            } else {
                return n;
            }
        }

        private void getAllSents(Node n, PriorityQueue<String> pq) {
            if (n == null) return;
            if (n.sent != null) {
                pq.add(n.sent);
                if (pq.size() > 3) pq.poll();
            }
            getAllSents(n.left, pq);
            getAllSents(n.eq, pq);
            getAllSents(n.right, pq);
        }

        class Node {
            char ch;
            Node left;
            Node eq;
            Node right;
            String sent;
            Node (char c) {
                this.ch = c;
            }
        }
    }


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

}
