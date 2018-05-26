/**
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has
 * index h if h of his/her N papers have at least h citations each, and the
 * other N − h papers have no more than h citations each."
 *
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations
 * respectively. Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, his h-index is 3.
 *
 * Note: If there are several possible values for h, the maximum one is taken
 * as the h-index.
 */


public class HIndex274 {
    public int hIndex(int[] citations) {
        int hIndex = 0;
        int L = citations.length;
        for (int i=0; i<L; i++) {
            int now = citations[i];
            int high = 0;
            int same = 0;
            for (int j=0; j<L; j++) {
                high += (citations[j] > now) ? 1:0;
                same += (citations[j] == now) ? 1:0;
            }
            for (int h = high+same; h >= high; h--) {
                if (now >= h) {
                    hIndex = Math.max(hIndex, h);
                    break;
                }
            }
        }

        return hIndex;
    }

    public int hIndex2(int[] citations) {
        int L = citations.length;
        for (int i=L; i>=0; i--) {
            int high = 0;
            int low = 0;
            for (int j=0; j<L && low<=(L-i); j++) {
                high += (citations[j] > i) ? 1:0;
                low += (citations[j] < i) ? 1:0;
            }

            if (i >= high && i <= L-low) return i;
        }

        return 0;
    }


    public int hIndex3(int[] citations) {
        final int L = citations.length;
        List<Integer> less = new ArrayList<>();

        int i = L;
        int more = 0;
        for (int j=0; j<L; j++) {
            int now = citations[j];
            if (now < i) {
                less.add(now);
            } else if (now > i) {
                more++;
            }
        }
        if (i >= more && i <= L-less.size()) return L;

        for (i=L-1; i>=0; i--) {
            int S = less.size();
            more = L-S;
            List<Integer> newLess = new ArrayList<>();
            for (int j=0; j<S; j++) {
                int now = less.get(j);
                if (now < i) {
                    newLess.add(now);
                } else if (now > i) {
                    more++;
                }
            }

            less = newLess;
            if (i >= more && i <= L-less.size()) return i;
        }

        return 0;
    }


    /**
     * https://discuss.leetcode.com/topic/23307/my-o-n-time-solution-use-java
     */
    public int hIndex4(int[] citations) {
        int length = citations.length;
        if (length == 0) {
        	return 0;
        }

        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
        	if (citations[i] > length) {
        		array2[length] += 1;
        	} else {
        		array2[citations[i]] += 1;
        	}
        }
        int t = 0;
        int result = 0;

        for (int i = length; i >= 0; i--) {
        	t = t + array2[i];
        	if (t >= i) {
        		return i;
        	}
        }
        return 0;
    }


    /**
     * https://discuss.leetcode.com/topic/23310/my-easy-solution
     */
    public int hIndex5(int[] citations) {
        Arrays.sort(citations);
        int len=citations.length;
        for(int i=0;i<len;i++){
            if(citations[i]>=len-i) return len-i;
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/h-index/solution/
     */
    public int hIndex6(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // counting papers for each citation number
        for (int c: citations)
            papers[Math.min(n, c)]++;
        // finding the h-index
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public int hIndex7(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        
        if (citations[citations.length-1] == 0) return 0;
        int i = 1;
        while (i < citations.length) {
            if (citations[citations.length-i] >= i && citations[citations.length-i-1] <= i) {
                return i;
            }
            i++;
        }
        
        return citations.length;
    }

}
