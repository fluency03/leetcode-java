/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among
 * them, there may exist one celebrity. The definition of a celebrity is that
 * all the other n - 1 people know him/her but he/she does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not
 * one. The only thing you are allowed to do is to ask questions like:
 * "Hi, A. Do you know B?" to get information of whether A knows B. You need to
 * find out the celebrity (or verify there is not one) by asking as few
 * questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A
 * knows B. Implement a function int findCelebrity(n), your function should
 * minimize the number of calls to knows.
 *
 * Note: There will be exactly one celebrity if he/she is in the party. Return
 * the celebrity's label if there is a celebrity in the party. If there is no
 * celebrity, return -1.
 */

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class FindTheCelebrity277 {
    // brutal force, accepted
    public int findCelebrity(int n) {
        boolean[] notCelebrity = new boolean[n];
        int[] known = new int[n];

        for (int a=0; a<n; a++) {
            for (int b=0; b<n; b++) {
                if (a != b && knows(a, b)) {
                    notCelebrity[a] = true;
                    known[b]++;
                }
            }
        }

        for (int i=0; i<n; i++) {
            if (!notCelebrity[i] && known[i] == n-1) {
                return i;
            }
        }
        return -1;
    }


    public int findCelebrity2(int n) {
        Set<Integer> celebrities = new HashSet<>();
        for (int i=0; i<n; i++) celebrities.add(i);

        for (int a=0; a<n; a++) {
            for (int b=0; b<n; b++) {
                if (a != b && knows(a, b)) {
                    celebrities.remove(a);
                    break;
                }
            }
        }

        if (celebrities.size() != 1) return -1;

        int possibleC = celebrities.iterator().next();
        for (int i=0; i<n; i++) {
            if (i == possibleC) continue;
            if (!knows(i, possibleC)) {
                return -1;
            }
        }
        return possibleC;
    }


    /**
     * https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass
     */
    public int findCelebrity3(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }


    /**
     * https://leetcode.com/problems/find-the-celebrity/discuss/71228/JavaPython-O(n)-calls-O(1)-space-easy-to-understand-solution
     */
    public int findCelebrity4(int n) {
        int x = 0;
        for (int i = 0; i < n; ++i) if (knows(x, i)) x = i;
        for (int i = 0; i < x; ++i) if (knows(x, i)) return -1;
        for (int i = 0; i < n; ++i) if (!knows(i, x)) return -1;
        return x;
    }


    public int findCelebrity5(int n) {
        if (n == 0) return -1;
        if (n == 1) return 0;
        
        int left = 0;
        int right = 1;
        while (left < n) {
            if (!knows(left, right) && knows(right, left)) {
                right++;
            } else {
                left = right;
                right++;
            }
            while (right == n) {
                boolean found = true;
                for (int i=0; i<left; i++) {
                    if (knows(left, i) || !knows(i, left)) {
                        found = false;
                        break;
                    }
                }
                if (found) return left;
                left++;
                right = left + 1;
            }
        }
        
        return -1;
    }

}
