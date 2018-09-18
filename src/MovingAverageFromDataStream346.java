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


    class MovingAverage2 {
        private int[] window;
        private int head = 0;
        private int len = 0 ;
        private int sum = 0;

        /** Initialize your data structure here. */
        public MovingAverage2(int size) {
            this.window = new int[size + 1];
        }

        public double next(int val) {
            int nextPos = (this.head + this.len + 1) % this.window.length;
            this.window[nextPos] = val;
            this.len++;
            this.sum += val;
            
            if (this.len == this.window.length) {
                this.head++;
                this.head %= this.window.length;
                this.len--;
                this.sum -= this.window[this.head];
            }
            return this.sum * 1.0 / this.len;
        }
    }

}

/**
* Your MovingAverage object will be instantiated and called as such:
* MovingAverage obj = new MovingAverage(size);
* double param_1 = obj.next(val);
*/

