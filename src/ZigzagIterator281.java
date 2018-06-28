/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * 
 * Example:
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6] 
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, 
 *              the order of elements returned by next should be: [1,3,2,4,5,6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be
 * extended to such cases?
 * 
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
 * If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
 * 
 * For example:
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * Output: [1,4,8,2,5,9,3,6,7].
 */

public class ZigzagIterator281 {
    class ZigzagIterator {
        private int total;
        private int k;
        private int pos = 0;
        private int index = 0;
        private List<Integer>[] cache;
        
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.cache = new List[2];
            this.cache[0] = v1;
            this.cache[1] = v2;
            this.total = v1.size() + v2.size();
            this.k = 2;
        }
      
        public int next() {
            int x = this.index % k;
            int y = this.index / k;
            while (y >= this.cache[x].size()) {
                this.index++;
                x = this.index % k;
                y = this.index / k;
            }
            int res = this.cache[x].get(y);
            this.index++;
            this.pos++;
            return res;
        }
      
        public boolean hasNext() {
            return this.pos < this.total;
        }
    }

    /**
     * https://leetcode.com/problems/zigzag-iterator/discuss/71779/Simple-Java-solution-for-K-vector
     */
    class ZigzagIterator2 {
        LinkedList<Iterator> list;
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            list = new LinkedList<Iterator>();
            if(!v1.isEmpty()) list.add(v1.iterator());
            if(!v2.isEmpty()) list.add(v2.iterator());
        }
    
        public int next() {
            Iterator poll = list.remove();
            int result = (Integer)poll.next();
            if(poll.hasNext()) list.add(poll);
            return result;
        }
    
        public boolean hasNext() {
            return !list.isEmpty();
        }
    }

    /**
     * https://leetcode.com/problems/zigzag-iterator/discuss/71781/Short-Java-O(1)-space
     */
    class ZigzagIterator3 {
        private Iterator<Integer> i, j, tmp;
    
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            i = v2.iterator();
            j = v1.iterator();
        }
    
        public int next() {
            if (j.hasNext()) { tmp = j; j = i; i = tmp; }
            return i.next();
        }
    
        public boolean hasNext() {
            return i.hasNext() || j.hasNext();
        }
    }

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

}

