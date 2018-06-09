/**
 * Given a stream of integers and a window size, calculate the moving average
 * of all integers in the sliding window.
 * 
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */


public class MovingAverageFromDataStream346 {
    class MovingAverage {
        private int size;
        private Queue<Integer> cache;
        private long sum;
        
        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            this.size = size;
            this.cache = new LinkedList<Integer>();
            this.sum = 0L;
        }
      
        public double next(int val) {
            if (this.cache.size() >= this.size) {
                this.sum -= this.cache.remove();
            }
            this.sum += val;
            this.cache.add(val);
            return this.sum * 1.0 / this.cache.size();
        }
    }

}

/**
* Your MovingAverage object will be instantiated and called as such:
* MovingAverage obj = new MovingAverage(size);
* double param_1 = obj.next(val);
*/

