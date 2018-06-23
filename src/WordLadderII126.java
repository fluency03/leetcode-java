/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible
 * transformation.
 */


public class WordLadderII126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(endWord)) return new ArrayList<>();
        wordDict.remove(beginWord);
        Set<String> visited = new HashSet<>();
        
        List<String> first = new ArrayList<>();
        first.add(beginWord);
        List<List<String>> res = new ArrayList<>();
        res.add(first);
        while (true) {
            List<List<String>> newRes = new ArrayList<>();
            Set<String> localVisited = new HashSet<>();
            boolean found = false;
            for (int i=0; i<res.size(); i++) {
                List<String> path = res.get(i);
                String word = path.get(path.size()-1);
                for (int j=0; j<word.length(); j++) {
                    char[] chars = word.toCharArray();
                    for (char ch='a'; ch<='z'; ch++) {
                        chars[j] = ch;
                        String newWord = new String(chars);
                        if (newWord.equals(endWord)) found = true;
                        if (!visited.contains(newWord) && wordDict.contains(newWord)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(newWord);
                            newRes.add(newPath);
                            if (!newWord.equals(endWord)) localVisited.add(newWord);
                            // wordDict.remove(newWord);
                        }
                    }
                }
            }
            if (newRes.size() == 0) return newRes;
            if (found) {
                List<List<String>> returned = new ArrayList<>();
                for (List<String> p: newRes) {
                    if (p.get(p.size()-1).equals(endWord)) {
                        returned.add(p);
                    }
                }
                return returned;
            }
            res = newRes;
            visited.addAll(localVisited);
        }
    }


    /**
     * https://leetcode.com/problems/word-ladder-ii/discuss/40477/Super-fast-Java-solution-(two-end-BFS)
     */
    public List<List<String>> findLadders2(String start, String end, List<String> wordList) {
      Set<String> dict = new HashSet<String>(wordList);
        if (!dict.contains(end)) return new ArrayList<>();
      // hash set for both ends
      Set<String> set1 = new HashSet<String>();
      Set<String> set2 = new HashSet<String>();
      
      // initial words in both ends
      set1.add(start);
      set2.add(end);
      
      // we use a map to help construct the final result
      Map<String, List<String>> map = new HashMap<String, List<String>>();
      
      // build the map
      helper(dict, set1, set2, map, false);
      
      List<List<String>> res = new ArrayList<List<String>>();
      List<String> sol = new ArrayList<String>(Arrays.asList(start));
      
      // recursively build the final result
      generateList(start, end, map, sol, res);
      
      return res;
    }
    
    private boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
        if (set1.isEmpty()) {
            return false;
        }
        
        if (set1.size() > set2.size()) {
            return helper(dict, set2, set1, map, !flip);
        }
        
        // remove words on current both ends from the dict
        dict.removeAll(set1);
        dict.removeAll(set2);
        
        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;
        
        // set for the next level
        Set<String> set = new HashSet<String>();
        
        // for each string in end 1
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                
                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    
                    String word = new String(chars);
                    
                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;
                        
                    List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();
                        
                    if (set2.contains(word)) {
                        done = true;
                        
                        list.add(val);
                        map.put(key, list);
                    } 
                    
                    if (!done && dict.contains(word)) {
                        set.add(word);
                        
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }
        
        // early terminate if done is true
        return done || helper(dict, set2, set, map, !flip);
    }
    
    private void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<String>(sol));
            return;
        }
        
        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!map.containsKey(start)) {
            return;
        }
        
        for (String word : map.get(start)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }


    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        // for each level,
        // should NOT use the words in upper level
        // should NOT add same words into queue
        // however, should use the same word in current level
        
        Map<String, List<String>> map = new HashMap<>();
        Set<String> unvisited = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        unvisited.remove(beginWord);
        boolean found = false;
        while(!found && !queue.isEmpty()){
            
            // begin each level
            int size = queue.size();
            visited.clear();
            
            for(int k = 0; k < size; k++){
                String word = queue.poll();
                for(int i = 0; i < word.length(); i++){
                    StringBuilder sb = new StringBuilder(word);
                    for(char c = 'a'; c < 'z'; c++){
                        sb.setCharAt(i, c);
                        String newWord = sb.toString();
                        if(unvisited.contains(newWord)){
                            if(visited.add(newWord)){
                                queue.offer(newWord);
                            }
                            
                            if(map.containsKey(newWord)){
                                map.get(newWord).add(word);
                            }else{
                                List<String> adj = new ArrayList<>();
                                adj.add(word);
                                map.put(newWord, adj);
                            }
                            if(newWord.equals(endWord)){
                                found = true;
                            }
                        }
                    }
                }
            }
            
            // end of each level
            unvisited.removeAll(visited);
        }
        
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(beginWord, path, map);
        return ans;
        
    }
    
    private void dfs(String beginWord, List<String> path, Map<String, List<String>> map){
        String word = path.get(0);
        if(word.equals(beginWord)){
            ans.add(new ArrayList<>(path));
            return;
        }
        if(map.containsKey(word)){
            for(String prevWord : map.get(word)){
                path.add(0, prevWord);
                dfs(beginWord, path, map);
                path.remove(0);
            }
        }
        
    }

}


