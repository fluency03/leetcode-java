/**
 * Alice has a hand of cards, given as an array of integers.
 * 
 * Now she wants to rearrange the cards into groups so that each group is
 * size W, and consists of W consecutive cards.
 * 
 * Return true if and only if she can.
 * 
 * Example 1:
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * 
 * Example 2:
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 * 
 * Note:
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 */

public class HandOfStraights846 {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null) return false;
        if (hand.length == 0 && W == 0) return true;
        if (hand.length == 0 || W == 0 || hand.length % W != 0) return false; 
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int card: hand) {
            pq.add(card);
        }
    
        while (!pq.isEmpty()) {
            int first = pq.peek();
            for (int i = 0; i<W; i++) {
                if (!pq.remove(first + i)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/hand-of-straights/solution/
     */
    public boolean isNStraightHand2(int[] hand, int W) {
        TreeMap<Integer, Integer> count = new TreeMap();
        for (int card: hand) {
            if (!count.containsKey(card))
                count.put(card, 1);
            else
                count.replace(card, count.get(card) + 1);
        }

        while (count.size() > 0) {
            int first = count.firstKey();
            for (int card = first; card < first + W; ++card) {
                if (!count.containsKey(card)) return false;
                int c = count.get(card);
                if (c == 1) count.remove(card);
                else count.replace(card, c - 1);
            }
        }

        return true;
    }


    /**
     * https://leetcode.com/problems/hand-of-straights/discuss/153519/copy-from-the-quickest-java-solutions-with-explanation(10-ms-Beats-100)
     */
    public boolean isNStraightHand3(int[] hands, int W) {
        if (W == 1) return true;
        if (hands.length % W != 0) return false;

        int H = hands.length / W;
        int[][] buckets = new int[W][H];
        int[] bucketSize = new int[W];

        for (int h : hands) {
            int indexInBucket = h % W, bucketId = bucketSize[indexInBucket]++;
            if (bucketId >= H) return false;
            buckets[indexInBucket][bucketId] = h;
        }
        
        for (int i = 0; i < W; i++) Arrays.sort(buckets[i]);
        
        for (int i = 0; i < H; i++)
            for (int j = 1; j < W; j++)
                //consider case 3,1,2 and 3,4,2
                if (buckets[j][i] != buckets[j - 1][i] + 1 && buckets[j - 1][i] - buckets[j][i] != W - 1) return false;

        return true;
    }

}
