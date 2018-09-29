/**
 * 
 */

public class KMP {

    /**
     * https://www.jianshu.com/p/e2bd1ee482c3
     */
    public static int find(String txt, String pat, int[] next) {
        int M = txt.length();
        int N = pat.length();
        int i = 0;
        int j = 0;
        while (i < M && j < N) {
            if (j == -1 || txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == N) return i - j;
        }
        return -1;
    }

    public static int[] getNext(String pat) {
        int N = pat.length();
        int[] next = new int[N];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < N - 1) {
            if (k == -1 || pat.charAt(j) == pat.charAt(k)) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            } 
        }
        return next;
    }


    public static void main(String[] args) {
        String txt = "BBC ABCDAB CDABABCDABCDABDE";
        String pat = "ABCDABD";
        int[] next = getNext(pat);
        System.out.println(find(txt, pat, next));
    }

}
