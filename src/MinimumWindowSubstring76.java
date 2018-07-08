/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".
 *
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.
 */


public class MinimumWindowSubstring76 {
    /**
     * https://discuss.leetcode.com/topic/12492/share-my-neat-java-solution
     */
    public String minWindow(String S, String T) {
        if(S==null||S.isEmpty()||T==null||T.isEmpty()) return "";
        int i=0, j=0;
        int[] Tmap=new int[256];
        int[] Smap=new int[256];
        for(int k=0; k< T.length(); k++){
            Tmap[T.charAt(k)]++;
        }
        int found=0;
        int length=Integer.MAX_VALUE;
        String res="";
        while(j<S.length()){
            if(found<T.length()){
                if(Tmap[S.charAt(j)]>0){
                    Smap[S.charAt(j)]++;
                    if(Smap[S.charAt(j)]<=Tmap[S.charAt(j)]){
                        found++;
                    }
                }
                j++;
            }
            while(found==T.length()){
                if(j-i<length){
                    length=j-i; res=S.substring(i,j);
                }
                if(Tmap[S.charAt(i)]>0){
                    Smap[S.charAt(i)]--;
                    if(Smap[S.charAt(i)]<Tmap[S.charAt(i)]){
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }

    /**
     * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
     */
    public String minWindow2(String s, String t) {
        int start = 0 , end = 0;
        int map[] = new int[256];
        for (int i = 0; i < t.length() ; i++) {
            map[t.charAt(i)]++;
        }
        int min = Integer.MAX_VALUE;
        int counter = 0;
        int ts = 0, te = 0;
        while (end < s.length()) {

            if (map[s.charAt(end++)]-- >= 1)
                counter++;

            while (counter == t.length()) {
                if (end - start < min) {
                    min = end - start;
                    ts = start; te = end;
                }
                if (map[s.charAt(start++)]++ >= 0)
                    counter--;
            }

        }

        return min == Integer.MAX_VALUE? "": s.substring(ts,te);
    }

}
