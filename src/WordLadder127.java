/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 *
 * Given:
 *    beginWord = "hit"
 *    endWord = "cog"
 *    wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 *    Return 0 if there is no such transformation sequence.
 *    All words have the same length.
 *    All words contain only lowercase alphabetic characters.
 *    You may assume no duplicates in the word list.
 *    You may assume beginWord and endWord are non-empty and are not the same.
 *
 */


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;


public class WordLadder127 {
    // DFS too slow

    // BFS
    public int ladderLength(String start, String end, List<String> dictL) {
        Set<String> dict = new HashSet<>(dictL);
        if (!dict.contains(end)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);

        // Mark visited word
        Set<String> visited = new HashSet<String>();
        visited.add(start);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q=0; q<size; q++) {
                String str = queue.poll(); 
                // Modify str's each character (so word distance is 1)
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String word = new String(chars);
                        // Found the end word
                        if (word.equals(end)) return level + 1;
                        // Put it to the queue
                        if (dict.contains(word) && !visited.contains(word)) {
                            queue.add(word);
                            visited.add(word);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    /**
     * https://discuss.leetcode.com/topic/17890/another-accepted-java-solution-bfs
     */
    public int ladderLength2(String start, String end, List<String> dictL) {
        Set<String> dict = new HashSet<>(dictL);
        if (!dict.contains(end)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        queue.add(null);

        // Mark visited word
        Set<String> visited = new HashSet<String>();
        visited.add(start);

        int level = 1;

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (str != null) {
                // Modify str's each character (so word distance is 1)
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;

                        String word = new String(chars);

                        // Found the end word
                        if (word.equals(end)) return level + 1;

                        // Put it to the queue
                        if (dict.contains(word) && !visited.contains(word)) {
                            queue.add(word);
                            visited.add(word);
                        }
                    }
                }
            } else {
                level++;

                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }

        return 0;
    }

    /**
     * Two-end BFS in Java 31ms.
     * https://discuss.leetcode.com/topic/29303/two-end-bfs-in-java-31ms
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordL) {
        Set<String> wordList = new HashSet<>(wordL);
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }


    /**
     * https://discuss.leetcode.com/topic/20965/java-solution-using-dijkstra-s-algorithm-with-explanation
     */
    public int ladderLength4(String beginWord, String endWord, List<String> dictL) {
        Set<String> wordDict = new HashSet<>(dictL);
        if (!wordDict.contains(endWord)) {
            return 0;
        }
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);

        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }

    public static void main(String[] args) {
        WordLadder127 wl = new WordLadder127();

        System.out.println("--------");
        System.out.println(wl.ladderLength2("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog","lot","log","cog"})));
        System.out.println("--------");
        System.out.println(wl.ladderLength2("hot", "dog", Arrays.asList(new String[]{"hot","cog","dog","tot","hog","hop","pot","dot"})));
        System.out.println("--------");
        System.out.println(wl.ladderLength2("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log"})));
    }


}
