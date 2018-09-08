/**
 * Given a string s and a list of strings dict, you need to add a closed pair
 * of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If
 * two such substrings overlap, you need to wrap them together by only one pair
 * of closed bold tag. Also, if two substrings wrapped by bold tags are
 * consecutive, you need to combine them.
 * 
 * Example 1:
 * 
 * Input: 
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * 
 * Example 2:
 * Input: 
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * 
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 */

public class AddBoldTagInString616 {
    public String addBoldTag(String s, String[] dict) {
        Trie trie = constructTrie(dict);        
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        char[] chars = s.toCharArray();
        for (int i=0; i<s.length(); i++) {
            int len = trie.search(chars, i);
            if (len == 0) {
                if (i >= r) {
                    if (r > l) {
                        sb.append("<b>");
                        sb.append(s.substring(l, r));
                        sb.append("</b>");
                    }
                    r = i + 1;
                    l = r;
                    sb.append(s.charAt(i));
                }
            } else {
                r = Math.max(r, i+len);
            }
        }

        if (r > l) {
            sb.append("<b>");
            sb.append(s.substring(l, r));
            sb.append("</b>");
        }

        return sb.toString();
    }
    
    private Trie constructTrie(String[] dict) {
        Trie res = new Trie();
        for (String word: dict) {
            res.addWord(word);
        }
        return res;
    }

    public String addBoldTag2(String s, String[] dict) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        for (int i=0; i<s.length(); i++) {
            int len = 0;
            for (String word: dict) {
                if (s.startsWith(word, i) && word.length() > len) {
                    len = word.length();
                }
            }
            if (len == 0) {
                if (i >= r) {
                    if (r > l) {
                        sb.append("<b>");
                        sb.append(s.substring(l, r));
                        sb.append("</b>");
                    }
                    r = i + 1;
                    l = r;
                    sb.append(s.charAt(i));
                }
            } else {
                r = Math.max(r, i+len);
            }
        }

        if (r > l) {
            sb.append("<b>");
            sb.append(s.substring(l, r));
            sb.append("</b>");
        }

        return sb.toString();
    }


    /**
     * https://leetcode.com/problems/add-bold-tag-in-string/discuss/104262/short-java-solution
     */
    public String addBoldTag3(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n+1];
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i+1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) sb.append("<b>");
            if (cur == 0 && sum > 0) sb.append("</b>");
            if (i == n) break;
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }


    /**
     * https://leetcode.com/problems/add-bold-tag-in-string/discuss/104263/Java-solution-Same-as-Merge-Interval.
     */
    public String addBoldTag4(String s, String[] dict) {
        List<Interval> intervals = new ArrayList<>();
        for (String str : dict) {
            int index = -1;
            index = s.indexOf(str, index);
            while (index != -1) {
                intervals.add(new Interval(index, index + str.length()));
                index +=1;
                index = s.indexOf(str, index);
            }
        }
        intervals = merge(intervals);
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (Interval interval : intervals) {
            sb.append(s.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        if (prev < s.length()) {
        	sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    class Interval {
        int start, end;
        public Interval(int s, int e) {
            start = s;
            end = e;
        }
        
        public String toString() {
            return "[" + start + ", " + end + "]" ;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.start <= end) {
                end = Math.max(end, i.end);
            } else {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }


    /**
     * https://leetcode.com/problems/add-bold-tag-in-string/discuss/104262/short-java-solution
     */
    public String addBoldTag5(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n+1];
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i+1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) sb.append("<b>");
            if (cur == 0 && sum > 0) sb.append("</b>");
            if (i == n) break;
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }


    public String addBoldTag6(String s, String[] dict) {
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        int len = s.length();
        for (int i=0; i<len; i++) {
            for (String word: dict) {
                if (s.startsWith(word, i)) {
                    r = Math.max(r, i+word.length());
                }
            }
            if (i < r) continue;
            if (l != r) {
                sb.append("<b>").append(s.substring(l, r)).append("</b>");
            }
            sb.append(s.charAt(i));
            l = i+1;
            r = i+1;
        }
        if (l != r) {
            sb.append("<b>").append(s.substring(l, r)).append("</b>");
        }
        return sb.toString();
    }

}

class Trie {
    Map<Character, Trie> children = new HashMap<>();
    boolean end = false;

    public void addWord(String word) {
        addWord(word.toCharArray(), 0);
    }

    public void addWord(char[] chars, int i) {
        if (i == chars.length) {
            end = true;
            return;
        }
        char c = chars[i];
        if (children.containsKey(c)) {
            children.get(c).addWord(chars, i+1);
        } else {
            Trie child = new Trie();
            children.put(c, child);
            child.addWord(chars, i+1);
        }
    }

    public int search(char[] chars, int i) {
        return search(chars, i, i, 0);
    }

    private int search(char[] chars, int i, int x, int len) {
        if (x >= chars.length) return end ? x-i : len;
        char c = chars[x];
        if (!children.containsKey(c)) return end ? x-i : len;
        return children.get(c).search(chars, i, x+1, len);
    }

    // public void print() {
    //     for (char c: children.keySet()) {
    //         System.out.println(c);
    //         children.get(c).print();
    //     }
    // }
    
}
