/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so
 * that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there are multiple such minimum-length windows, return
 * the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 *
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 */


public class MinimumWindowSubsequence727 {
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) return "";
        char[] schars = S.toCharArray();
        char[] tchars = T.toCharArray();
        int sl = schars.length;
        int tl = tchars.length;

        int[][] n = new int[tl+1][sl+1];
        for (int j=0; j<=sl; j++) n[0][j] = 0;
        for (int i=1; i<=tl; i++) n[i][0] = -1;

        for (int i=1; i<=tl; i++) {
            for (int j=1; j<=sl; j++) {
                n[i][j] = Math.max(
                    (schars[j-1] == tchars[i-1] && n[i-1][j-1] != -1) ? j : -1,
                    n[i][j-1]
                );
            }
        }

        int[] edges = new int[2];
        int diff = Integer.MAX_VALUE;
        int j = 1;
        while (j <= sl && n[tl][j] == -1) j++;
        while (j <= sl) {
            int lastJ = j;
            int i = tl;
            while (i > 1 && j > 1) {
                i--;
                j--;
                j = n[i][j];
            }
            if (i == 1 && j > 0 && diff > lastJ - j + 1) {
                edges[0] = j;
                edges[1] = lastJ;
                diff = lastJ - j + 1;
            }
            j = lastJ;
            while (j <= sl && n[tl][j] == lastJ) j++;
        }

        return (diff == Integer.MAX_VALUE) ? "" : S.substring(edges[0]-1, edges[1]);
    }


    /**
     * https://leetcode.com/problems/minimum-window-subsequence/solution/
     */
    public String minWindow2s(String S, String T) {
        int[][] dp = new int[2][S.length()];

        for (int i = 0; i < S.length(); ++i)
            dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        /*At time j when considering T[:j+1],
          the smallest window [s, e] where S[e] == T[j]
          is represented by dp[j & 1][e] = s, and the
          previous information of the smallest window
          [s, e] where S[e] == T[j-1] is stored as
          dp[~j & 1][e] = s.
        */
        for (int j = 1; j < T.length(); ++j) {
            int last = -1;
            Arrays.fill(dp[j & 1], -1);
            //Now we would like to calculate the candidate windows
            //"dp[j & 1]" for T[:j+1].  'last' is the last window seen.
            for (int i = 0; i < S.length(); ++i) {
                if (last >= 0 && S.charAt(i) == T.charAt(j))
                    dp[j & 1][i] = last;
                if (dp[~j & 1][i] >= 0)
                    last = dp[~j & 1][i];
            }
        }

        //Looking at the window data dp[~T.length & 1],
        //choose the smallest length window [s, e].
        int start = 0, end = S.length();
        for (int e = 0; e < S.length(); ++e) {
            int s = dp[~T.length() & 1][e];
            if (s >= 0 && e - s < end - start) {
                start = s;
                end = e;
            }
        }
        return end < S.length() ? S.substring(start, end+1) : "";
    }


    /**
     * https://leetcode.com/problems/minimum-window-subsequence/solution/
     */
    public String minWindow3(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] nxt = new int[N][26];
        Arrays.fill(last, -1);

        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                nxt[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); ++j) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                    window[1] = nxt[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};
        for (int[] window: windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }

        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }


    /**
     * https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
     */
    public String minWindow4(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        return len == n + 1 ? "" : S.substring(start, start + len);
    }


    public String minWindow5(String S, String T) {
        if (S == null || T == null || T.length() > S.length()) return "";
        int lenS = S.length();
        int lenT = T.length();
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        int[][] pos = new int[lenT][lenS];
        for (int i=0; i<lenT; i++) {
            for (int j=0; j<lenS; j++) {
                if (charT[i] == charS[j]) {
                    pos[i][j] = i == 0 ? j : (j == 0 ? -1 : pos[i-1][j-1]);
                } else {
                    pos[i][j] = j == 0 ? -1 : pos[i][j-1];
                }
            }
        }

        int start = -1;
        int minSize = Integer.MAX_VALUE;
        for (int j=lenS-1; j>=0; j--) {
            if (pos[lenT-1][j] == -1) break;
            if (j - pos[lenT-1][j] + 1 <= minSize) {
                start = pos[lenT-1][j];
                minSize = j - pos[lenT-1][j] + 1;
            }
        }
        return start == -1 ? "" : S.substring(start, start + minSize);
    }


    /**
     * https://leetcode.com/problems/minimum-window-subsequence/discuss/109373/m-pointer-technique-Java-O(S.length()-*-T.length())-solution
     */
    public String minWindow6(String S, String T) {
        int n = S.length(), m = T.length();
        int l = 0, r = n;
            
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) != T.charAt(0)) continue;  // No group of mapping is found, so continue
            
            int k = i + 1;
                
            for (int j = 1; j < m; j++, k++) {  // Greedy algorithm to find the greedy mapping
                while (k < n && S.charAt(k) != T.charAt(j)) k++;
                if (k == n) return (r == n ? "" : S.substring(l, r + 1));  // Link for T[j] does not exist, so return
            }
            
            if (k - 1 - i < r - l) {  // Update the result substring if one with a smaller length is found
                l = i; r = k - 1;
            }
        }
        
        return (r == n ? "" : S.substring(l, r + 1));
    }
    /**
     * https://leetcode.com/problems/minimum-window-subsequence/discuss/109373/m-pointer-technique-Java-O(S.length()-*-T.length())-solution
     */
    public String minWindow7(String S, String T) {
        int n = S.length(), m = T.length();
        int l = 0, r = n;
        
        int[] p = new int[m];
            
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) != T.charAt(0)) continue;  // No group of mapping is found, so continue
            
            p[0] = i;  // Group of mapping is found, so update the first link
            
            for (int j = 1, k = i + 1; j < m; j++, k++) {  // Greedy algorithm to find the greedy mapping
                if (k <= p[j]) break;    // Early termination, since the remaining links have been computed in previous groups
                while (k < n && S.charAt(k) != T.charAt(j)) k++;
                if (k == n) return (r == n ? "" : S.substring(l, r + 1));  // Link for T[j] does not exist, so return
                p[j] = k;  // Else update the link for T[j]
            }
                
            if (p[m - 1] - p[0] < r - l) {  // Update the result substring if one with a smaller length is found
                l = p[0]; r = p[m - 1];
            }
        }
            
        return (r == n ? "" : S.substring(l, r + 1));
    }



    public String minWindow8(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        
        int left = 0;
        int right = 0;
        int flag = 0;
        
        int start = -1;
        int minLen = lenS + 1;
        while (right < lenS) {
            while (left <= lenS - lenT && charS[left] != charT[0]) left++;
            if (left > lenS - lenT) break;
            right = left;
            flag = 0;
            while (flag < lenT && right < lenS) {
                if (charS[right] == charT[flag]) {
                    flag++;
                }
                right++;
            }
            if (flag != lenT) break;
            if (right - left < minLen) {
                minLen = right - left;
                start = left;
            }
            left++;
            right = left;
        }
        return start == -1 ? "" : S.substring(start, start + minLen);
    }


    public String minWindow9(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        
        int left = 0;
        int right = 0;
        int flag = 0;
        
        int start = -1;
        int minLen = lenS + 1;
        while (right < lenS) {
            while (left <= lenS - lenT && charS[left] != charT[0]) left++;
            if (left > lenS - lenT) break;
            right = left;
            flag = 0;
            while (flag < lenT && right < lenS) {
                if (charS[right] == charT[flag]) {
                    flag++;
                }
                right++;
            }
            if (flag != lenT) break;
            int newStart = backwards(charS, charT, left, right-1);
            left = Math.max(left, newStart);
            if (right - left < minLen) {
                minLen = right - left;
                start = left;
            }
            left++;
            right = left;
        }
        return start == -1 ? "" : S.substring(start, start + minLen);
    }
    
    private int backwards(char[] charS, char[] charT, int begin, int end) {
        int flagS = end;
        int flagT = charT.length - 1;
        while (true) {
            if (charS[flagS] == charT[flagT]) {
                if (flagT == 0) return flagS;
                flagT--;
            }
            flagS--;
        }
    }

}
