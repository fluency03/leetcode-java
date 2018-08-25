/**
 * Design a logger system that receive stream of messages along with its
 * timestamps, each message should be printed if and only if it is not printed
 * in the last 10 seconds.
 * 
 * Given a message and a timestamp (in seconds granularity), return true if
 * the message should be printed in the given timestamp, otherwise returns
 * false.
 * 
 * It is possible that several messages arrive roughly at the same time.
 * 
 * Example:
 * 
 * Logger logger = new Logger();
 * 
 * // logging string "foo" at timestamp 1
 * logger.shouldPrintMessage(1, "foo"); returns true; 
 * 
 * // logging string "bar" at timestamp 2
 * logger.shouldPrintMessage(2,"bar"); returns true;
 * 
 * // logging string "foo" at timestamp 3
 * logger.shouldPrintMessage(3,"foo"); returns false;
 * 
 * // logging string "bar" at timestamp 8
 * logger.shouldPrintMessage(8,"bar"); returns false;
 * 
 * // logging string "foo" at timestamp 10
 * logger.shouldPrintMessage(10,"foo"); returns false;
 * 
 * // logging string "foo" at timestamp 11
 * logger.shouldPrintMessage(11,"foo"); returns true;
 */

public class LoggerRateLimiter359 {
    class Logger {
        Map<String, Integer> map = new HashMap<>();
        
        /** Initialize your data structure here. */
        public Logger() {
            
        }
        
        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
            If this method returns false, the message will not be printed.
            The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!map.containsKey(message) || map.get(message) + 10 <= timestamp) {
                map.put(message, timestamp);
                return true;
            }
            return false;
        }
    }

    /**
     * https://leetcode.com/problems/logger-rate-limiter/discuss/83254/Java-with-a-LinkedHashMap-and-using-removeEldestEntry
     */
    class Logger2 {
        public Map<String, Integer> map;
        int lastSecond = 0;

        /** Initialize your data structure here. */
        public Logger() {
            map = new LinkedHashMap<String, Integer>(100, 0.6f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                    return lastSecond - eldest.getValue() > 10;
                }
            };
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
            If this method returns false, the message will not be printed.
            The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            lastSecond = timestamp;
            if(!map.containsKey(message)||timestamp - map.get(message) >= 10){
                map.put(message,timestamp);
                return true;
            }
            return false;
        }
    }


/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

}
