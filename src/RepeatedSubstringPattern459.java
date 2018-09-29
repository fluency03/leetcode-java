/**
 * Given a non-empty string check if it can be constructed by taking a
 * substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only
 * and its length will not exceed 10000.
 * 
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * 
 * Example 2:
 * Input: "aba"
 * Output: False
 * 
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

public class RepeatedSubstringPattern459 {

    /**
     * https://leetcode.com/problems/repeated-substring-pattern/discuss/94352/Java-Simple-Solution-with-Explanation
     */
    public boolean repeatedSubstringPattern(String s) {
        if(s==null || s.length()<=1) return false;
        
        for(int i=1;i<=s.length()/2;i++){   
            if(s.length()%i!=0) continue;      
            String sub = s.substring(0,i);
            if(dfs(s,sub,i)) return true;
        }
        return false;
    }

    private boolean dfs(String s,String sub,int i){
        if(i==s.length()) return true;
        if(!s.startsWith(sub,i)) return false;
        return dfs(s,sub,i+sub.length());
    }


    /**
     * https://leetcode.com/problems/repeated-substring-pattern/discuss/94344/Simple-Java-solution-2-lines
     */
    public boolean repeatedSubstringPattern2(String str) {
        String s = str + str;
        return s.substring(1, s.length() - 1).contains(str);
    }



    /**
     * https://leetcode.com/problems/repeated-substring-pattern/discuss/94340/Java-and-O(n)
     */
    public boolean repeatedSubstringPattern3(String str) {
        //This is the kmp issue
        int[] prefix = kmp(str);
        int len = prefix[str.length()-1];
        int n = str.length();
        return (len > 0 && n%(n-len) == 0);
    }

    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length){
            if(ch[j] == ch[i]){
                res[j] = i+1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    i = res[i-1];
                }
            }
        }
        return res;
    }

}
