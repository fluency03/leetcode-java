/**
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 * Note: You may assume the string contain only lowercase letters.
 *
 */


public class FirstUniqueCharacterInAString387 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        if (s.length() == 1) return 0;
        int len = s.length();

        int[] pos = new int[26];
        boolean[] bs = new boolean[26];
        for (int j=0; j<26; j++) pos[j] = -1;

        char[] arr = s.toCharArray();
        for (int i=0; i<len; i++) {
            int p = arr[i] - 'a';
            if (pos[p] == -1) {
                pos[p] = i;
            } else {
                bs[p] = true;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j=0; j<26; j++) {
            if (!bs[j] && pos[j] != -1) {
                res = Math.min(res, pos[j]);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86348/Java-7-lines-solution-29ms
     */
    public int firstUniqChar2(String s) {
        int[] alp =new int[26];
        char[] arr =s.toCharArray();

        for(char c : arr ){
            alp[c-'a']++;
        }

        for(int i=0;i<arr.length;i++){
            if (alp[arr[i]-'a']==1) return i;
        }

        return -1;
    }


    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86340/Java-two-pointers-(slow-and-fast)-solution-(18-ms)
     */
    public int firstUniqChar3(String s) {
        if (s==null || s.length()==0) return -1;
        int len = s.length();
        if (len==1) return 0;
        char[] cc = s.toCharArray();
        int slow =0, fast=1;
        int[] count = new int[256];
        count[cc[slow]]++;
        while (fast < len) {
            count[cc[fast]]++;
            // if slow pointer is not a unique character anymore, move to the next unique one
            while (slow < len && count[cc[slow]] > 1) slow++;
            if (slow >= len) return -1; // no unique character exist
            if (count[cc[slow]]==0) { // not yet visited by the fast pointer
                count[cc[slow]]++;
                fast=slow; // reset the fast pointer
            }
            fast++;
        }
        return slow;
    }


    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86359/my-4-lines-Java-solution
     */
    public static int firstUniqChar4(String s) {
        char[] a = s.toCharArray();

        for(int i=0; i<a.length;i++){
            if(s.indexOf(a[i])==s.lastIndexOf(a[i])){return i;}
        }
        return -1;
    }


    public int firstUniqChar5(String s) {
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int slow = 0;
        int fast = 0;
        int N = chars.length;
        while (slow < N) {
            while (slow < fast && map[chars[slow]-'a'] > 1) slow++;
            if (fast == N) break;
            map[chars[fast]-'a']++;
            fast++;
        }
        return slow == N ? -1 : slow;
    }


    public int firstUniqChar6(String s) {
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int slow = 0;
        int fast = 0;
        int N = chars.length;
        while (slow < N) {
            map[chars[fast]-'a']++;
            fast++;
            while (slow < fast && map[chars[slow]-'a'] > 1) slow++;
            if (fast == N) break;
        }
        return slow == N ? -1 : slow;
    }

}
