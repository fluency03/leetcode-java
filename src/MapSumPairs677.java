/**
 * Implement a MapSum class with insert, and sum methods.
 * 
 * For the method insert, you'll be given a pair of (string, integer). The
 * string represents the key and the integer represents the value. If the key
 * already existed, then the original key-value pair will be overridden to
 * the new one.
 * 
 * For the method sum, you'll be given a string representing the prefix, and
 * you need to return the sum of all the pairs' value whose key starts with
 * the prefix.
 * 
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 *
 */

public class MapSumPairs677 {

    class MapSum {
        private Trie trie = new Trie();

        /** Initialize your data structure here. */
        public MapSum() {
        }

        public void insert(String key, int val) {
            trie.add(key, val);
        }

        public int sum(String prefix) {
            return trie.getSum(prefix);
        }

        class Trie {
            Trie[] children = new Trie[256];
            Integer value;
            Integer sum = 0;

            public void add(String key, int val) {
                add(key.toCharArray(), val, 0);
            }

            private Integer add(char[] chars, int val, int i) {
                if (i == chars.length) {
                    int diff = (value == null) ? val : val - value;
                    value = val;
                    sum += diff;
                    return diff;
                }
                int nextI = (int) (chars[i] - 'a');
                if (children[nextI] == null) {
                    children[nextI] = new Trie();
                }
                Integer pre = sum;
                Integer diff = children[nextI].add(chars, val, i+1);
                sum = pre + diff;
                return diff;
            }

            public int getSum(String prefix) {
                int s = getSum(prefix.toCharArray(), 0);
                return s;
            }

            private int getSum(char[] chars, int i) {
                if (i == chars.length) return sum;
                if (children[chars[i] - 'a'] == null) return 0;
                return children[chars[i] - 'a'].getSum(chars, i+1);
            }
        }
    }



    class MapSum2 {
        private Map<String, Integer> map = new HashMap<>();
        private Trie trie = new Trie();

        /** Initialize your data structure here. */
        public MapSum() {
        }

        public void insert(String key, int val) {
            int now = map.getOrDefault(key, 0);
            trie.add(key, val - now);
            map.put(key, val);
        }

        public int sum(String prefix) {
            return trie.getSum(prefix);
        }

        class Trie {
            Trie[] children = new Trie[256];
            Integer sum = 0;

            public void add(String key, int diff) {
                add(key.toCharArray(), diff, 0);
            }

            private void add(char[] chars, int diff, int i) {
                if (i == chars.length) {
                    sum += diff;
                    return;
                }
                int nextI = (int) (chars[i] - 'a');
                if (children[nextI] == null) {
                    children[nextI] = new Trie();
                }
                sum += diff;
                children[nextI].add(chars, diff, i+1);
            }

            public int getSum(String prefix) {
                return getSum(prefix.toCharArray(), 0);
            }

            private int getSum(char[] chars, int i) {
                if (i == chars.length) return sum;
                if (children[chars[i] - 'a'] == null) return 0;
                return children[chars[i] - 'a'].getSum(chars, i+1);
            }
        }
    }


/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

}
