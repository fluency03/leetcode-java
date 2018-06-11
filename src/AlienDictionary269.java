/**
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of non-empty words
 * from the dictionary, where words are sorted lexicographically by the rules of
 * this new language. Derive the order of letters in this language.
 * 
 * Example 1:
 * 
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 
 * Output: "wertf"
 * Example 2:
 * 
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 * 
 * Output: "zx"
 * Example 3:
 * 
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ] 
 * 
 * Output: "" 
 * 
 * Explanation: The order is invalid, so return "".
 * 
 * Note:
 * 
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in
 * the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */


public class AlienDictionary269 {
    
    private Set<Character> vertices = new HashSet<>();
    private Set<Character>[] graph = new Set[26];

    /**
     * DFS based topological sort
     */
    public String alienOrder(String[] words) {
        initMap(words);
        updateOrderMap(words);
        
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        for (char key: vertices) {
            if (!visited[key - 'a']) {
                if (!helper(key, new HashSet<Character>(), visited, sb)) return "";
            }
        }

        return sb.reverse().toString();
    }

    private boolean helper(Character key, Set<Character> path, boolean[] visited, StringBuilder sb) {
        visited[key - 'a'] = true;
        path.add(key);
        for (Character c: graph[key - 'a']) {
            if (path.contains(c)) return false;
            if (!visited[c - 'a']) {
                if (!helper(c, path, visited, sb)) return false;
            }
        }
        sb.append(key);
        path.remove(key);
        return true;
    }
    
    private void initMap(String[] words) {    
        for (String word: words) {
            for (char c: word.toCharArray()) {
                if (!vertices.contains(c)) {
                    vertices.add(c);
                    graph[c - 'a'] = new HashSet<Character>();
                }
            }
        }
    }

    private void updateOn(String word1, String word2) {
        int len = Math.min(word1.length(), word2.length());
        for (int i=0; i<len; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                graph[word1.charAt(i) - 'a'].add(word2.charAt(i));
                return;
            }
        }
    }

    private void updateOrderMap(String[] dicts) {
        initMap(dicts);
        for (int i=0; i<dicts.length-1; i++) {
            updateOn(dicts[i], dicts[i+1]);
        }
    }


    /**
     * Kahn’s algorithm for Topological Sorting
     */
    private int[] incomming = new int[26];
    public String alienOrder2(String[] words) {
        initMap(words);
        updateOrderMap(words);

        for (Character v: vertices) {
            Set<Character> adjs = graph[v - 'a'];
            if (adjs != null) {
                for (Character c: adjs) {
                    incomming[c - 'a']++;
                }
            }
        }
        
        Queue<Character> q = new LinkedList<>();
        for (Character c: vertices) {
            if (incomming[c - 'a'] == 0) {
                q.add(c);
            }
        }

        int visited = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.remove();
            sb.append(c);
            for (Character next: graph[c - 'a']) {
                incomming[next - 'a']--;
                if (incomming[next - 'a'] == 0) {
                    q.add(next);
                }
            }
            visited++;
        }
        if (visited != vertices.size()) return "";
        
        return sb.toString();
    }


    /**
     * https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
     */
    private final int N = 26;
    public String alienOrder3(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        buildGraph(words, adj, visited);
    
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            if(visited[i] == 0) {                 // unvisited
                if(!dfs(adj, visited, sb, i)) return "";
            }
        }
        return sb.reverse().toString();
    }
    
    public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
        visited[i] = 1;                            // 1 = visiting
        for(int j = 0; j < N; j++) {
            if(adj[i][j]) {                        // connected
                if(visited[j] == 1) return false;  // 1 => 1, cycle   
                if(visited[j] == 0) {              // 0 = unvisited
                    if(!dfs(adj, visited, sb, j)) return false;
                }
            }
        }
        visited[i] = 2;                           // 2 = visited
        sb.append((char) (i + 'a'));
        return true;
    }
    
    public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        Arrays.fill(visited, -1);                 // -1 = not even existed
        for(int i = 0; i < words.length; i++) {
            for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
            if(i > 0) {
                String w1 = words[i - 1], w2 = words[i];
                int len = Math.min(w1.length(), w2.length());
                for(int j = 0; j < len; j++) {
                    char c1 = w1.charAt(j), c2 = w2.charAt(j);
                    if(c1 != c2) {
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }

}
