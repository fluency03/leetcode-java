/**
 * Implement an iterator to flatten a 2d vector.
 * 
 * Example:
 * 
 * Input: 2d vector =
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * Output: [1,2,3,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, 
 *              the order of elements returned by next should be: [1,2,3,4,5,6].
 * 
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or
 * iterators in Java.
 */

public class Flatten2DVector251 {
    class Vector2D implements Iterator<Integer> {
        private Iterator<List<Integer>> iter2d;
        private Iterator<Integer> iter;
        
        public Vector2D(List<List<Integer>> vec2d) {
            iter2d = vec2d.iterator();
            if (iter2d.hasNext()) {
                iter = iter2d.next().iterator();
            } else {
                iter = (new ArrayList<Integer>()).iterator();
            }
        }

        @Override
        public Integer next() {
            hasNext();
            return iter.next();
        }

        @Override
        public boolean hasNext() {
            while (!iter.hasNext()) {
                if (!iter2d.hasNext()) return false;
                iter = iter2d.next().iterator();
            }
            return iter.hasNext();
        }
    }

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

}

