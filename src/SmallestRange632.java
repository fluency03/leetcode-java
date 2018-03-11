/**
 * You have k lists of sorted integers in ascending order. Find the smallest
 * range that includes at least one number from each of the k lists.
 *
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 * Example 1:
 *    Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 *    Output: [20,24]
 *    Explanation:
 *      List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 *      List 2: [0, 9, 12, 20], 20 is in range [20,24].
 *      List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Note:
 *    - The given list may contain duplicates, so ascending order means >= here.
 *    - 1 <= k <= 3500
 *    - 105 <= value of elements <= 105.
 *    - For Java users, please note that the input type has been changed to
 *      List<List<Integer>>. And after you reset the code template, you'll see
 *      this point.
 *
 */


public class SmallestRange632 {
    /**
     * https://leetcode.com/problems/smallest-range/solution/
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int minx = 0, miny = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];
        boolean flag = true;
        PriorityQueue<Integer> min_queue = new PriorityQueue<>((i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        for (int i = 0; i < nums.size(); i++) {
            min_queue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int min_i = min_queue.poll();
                if (miny - minx > max - nums.get(min_i).get(next[min_i])) {
                    minx = nums.get(min_i).get(next[min_i]);
                    miny = max;
                }
                next[min_i]++;
                if (next[min_i] == nums.get(min_i).size()) {
                    flag = false;
                    break;
                }
                min_queue.offer(min_i);
                max = Math.max(max, nums.get(min_i).get(next[min_i]));
            }
        }
        return new int[] {minx, miny};
    }


    /**
     * https://discuss.leetcode.com/topic/94445/java-code-using-priorityqueue-similar-to-merge-k-array
     */
    public int[] smallestRange2(List<List<Integer>> nums) {
    		PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
      			public int compare(Element a, Element b) {
      				    return a.val - b.val;
      			}
    		});
    		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    		for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
      			Element e = new Element(i, 0, value);
      			pq.offer(e);
      			max = Math.max(max, value);
    		}
    		int range = Integer.MAX_VALUE;
    		int start = -1, end = -1;
    		while (pq.size() == nums.size()) {
      			Element curr = pq.poll();
      			if (max - curr.val < range) {
        				range = max - curr.val;
        				start = curr.val;
        				end = max;
      			}
      			if (curr.idx + 1 < nums.get(curr.row).size()) {
        				curr.idx = curr.idx + 1;
        				curr.val = nums.get(curr.row).get(curr.idx);
        				pq.offer(curr);
        				if (curr.val > max) {
        					  max = curr.val;
        				}
      			}
    		}

    		return new int[] { start, end };
  	}

    class Element {
    		int val;
    		int idx;
    		int row;

    		public Element(int r, int i, int v) {
      			val = v;
      			idx = i;
      			row = r;
    		}
  	}


    // Time Limit Exceeded
    public int[] smallestRange3(List<List<Integer>> nums) {
        int minx = 0, miny = Integer.MAX_VALUE;
        int[] next = new int[nums.size()];
        while (true) {
            int min_i = 0, max_i = 0;
            for (int k = 0; k < nums.size(); k++) {
                if (nums.get(min_i).get(next[min_i]) > nums.get(k).get(next[k]))
                    min_i = k;
                if (nums.get(max_i).get(next[max_i]) < nums.get(k).get(next[k]))
                    max_i = k;
            }
            if (miny - minx > nums.get(max_i).get(next[max_i]) - nums.get(min_i).get(next[min_i])) {
                miny = nums.get(max_i).get(next[max_i]);
                minx = nums.get(min_i).get(next[min_i]);
            }
            next[min_i]++;
            if (next[min_i] == nums.get(min_i).size()) break;
        }
        return new int[] {minx, miny};
    }

    /**
     * https://leetcode.com/problems/smallest-range/solution/
     */
    public int[] smallestRange4(List<List<Integer>> nums) {
        int minx = 0, miny = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(
            (i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j])
        );
        for (int i = 0; i < nums.size(); i++) {
            q.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        while (true) {
            int min_i = q.poll();
            if (miny - minx > max - nums.get(min_i).get(next[min_i])) {
                miny = max;
                minx = nums.get(min_i).get(next[min_i]);
            }
            next[min_i]++;
            if (next[min_i] == nums.get(min_i).size()) {
                // flag = false;
                break;
            }
            q.offer(min_i);
            max = Math.max(max, nums.get(min_i).get(next[min_i]));
        }
        return new int[] {minx, miny};
    }








}
